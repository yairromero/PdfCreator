package com.meltsan.pdfcreator;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;


import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.Constantes;
import com.meltsan.pdfcreator.beans.PerCapita;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class GeneradorReporte {
	
	private Antecedentes antecedentes;
	private ArrayList<PerCapita> sinPerCapita;
	private ArrayList<JasperReportBuilder> listaReportes;
	private BufferedImage img = null;
	private ImageBuilder imgHeader = null;
	private ImageBuilder imgFooter = null;
	private BufferedImage imgCal = null;
	private File file = null;
	
	public GeneradorReporte(Antecedentes antecedentes, ArrayList<PerCapita> sinPerCapita) {
		
		this.antecedentes = antecedentes;			
		this.sinPerCapita = sinPerCapita;
		
		try {
			
    	   		ClassLoader classLoader = getClass().getClassLoader();
    	   		file = new File(classLoader.getResource("head.png").getFile());
    	   		img = ImageIO.read(file);    	
    	   		imgHeader = cmp.image(img).setImageScale(ImageScale.FILL_FRAME);
    	   		file = new File(classLoader.getResource("foot.png").getFile());
    	   		img = ImageIO.read(file);    	
    	   		imgFooter = cmp.image(img);
    	   		file = new File(classLoader.getResource("cal.png").getFile());
    	   		imgCal = ImageIO.read(file);    	   		
    	   		
       } catch (IOException e) {
       		e.printStackTrace();
       }
					
 	 //imgBackgroud.setPrintWhenExpression(exp.printInFirstPage());
 	   	
 	   listaReportes = new ArrayList<JasperReportBuilder>();
 	   
 	   if(this.antecedentes != null) {
 		   listaReportes.add(reporteAntecedentes());
 	   }
 	   
 	   if(!this.sinPerCapita.isEmpty()) {
 		   listaReportes.add(reporteSinPerCapita());
 	   }
 	   
 	   ejecutarReporte(listaReportes);
	}
	
	/**
	 * Genera el reporte de Antecedentes utilizando
	 * la información del objeto Antecedentes.
	 * @return JasperReportBuilder con la hoja de antecedentes.
	 */
	private JasperReportBuilder reporteAntecedentes() {       
       
    	    JasperReportBuilder reporteAntecedentes = new JasperReportBuilder();
    	         	       			       			
    	    VerticalListBuilder content = cmp.verticalList(
    	    			cmp.text(Constantes.ANTE_HEADER).setStyle(Estilos.reportHeadStyle),
     	    		cmp.text(""),
   		        cmp.text(Constantes.ANTE_VIGENCIA).setStyle(Estilos.reportSubTitleStyle),
   		        cmp.text(antecedentes.getVigenciaCompleta()).setStyle(Estilos.reportHeadStyle),
   		        cmp.text(Constantes.ANTE_PERIODO).setStyle(Estilos.reportSubTitleStyle),
   		        cmp.text(antecedentes.getPeriodoAnalisis()).setStyle(Estilos.reportHeadStyle),
   		        cmp.text("")
   		      );
    	    
    	    JasperReportBuilder subreport = report();	
  	      subreport  	
  	      	.setTemplate(Estilos.reportTemplate) 
  	        .columns(
	    			col.column("Vigencia", "vigencia", type.stringType()),
	    			col.column("Periodo", "periodo",  type.stringType())
	    		)
  	      .setDataSource(crearAntecedentesDS(this.antecedentes.getTablaVigencias()));
    	    
    	    reporteAntecedentes    	    	
    	    		
    	   		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
    	   		.setTitleBackgroundComponent(imgHeader)    	   		
    	   		.title(cmp.text("Antecedentes").setStyle(Estilos.reportTitleStyle))    	   		
    	   		.addPageHeader(content)    	   		
    	   		.summary(
    	   				cmp.horizontalList(cmp.subreport(subreport),cmp.image(imgCal)))      	   		
    	   		.setColumnStyle(Estilos.columnStyle)    	   		
    	   		.build();
    	    		  
    	    return reporteAntecedentes;
	}
	
	/**
	 * Genera datos para llenar tabla de antecedentes
	 * 	
	 * @param vigencias Mapa con perido y vigencias
	 * @return JRDataSource para alimentar tabla
	 */
	private JRDataSource crearAntecedentesDS(HashMap<String,String> vigencias) {		
	       DRDataSource dataSource = new DRDataSource("vigencia", "periodo");		
	       Iterator<Entry<String, String>> itAnte = vigencias.entrySet().iterator();
	       while (itAnte.hasNext()) {
	    	   		Map.Entry<String, String> entry = itAnte.next();
	    	   		dataSource.add(entry.getKey(),entry.getValue());
	       } 
	       return dataSource;
	    }

	/**
	 * Genera el reporte de Siniestralidad Per Capita utilizando
	 * una lista de objetos PerCapita
	 * @return JasperReportBuilder con la hoja de inidicadores
	 * de siniestralidad per capita.
	 */
	private JasperReportBuilder reporteSinPerCapita() {
		
		JasperReportBuilder reportePerCapita = new JasperReportBuilder();
		
		ArrayList<BigDecimal> costos = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> primas = new ArrayList<BigDecimal>();
		
		
		for(PerCapita pc: this.sinPerCapita) {			
			costos.add(pc.getCostoPerCapita());
			primas.add(pc.getPrimaPerCapita());
		}
		
		BigDecimal maxRange = getMax(costos).max(getMax(primas));
		BigDecimal minRange = getMin(costos).min(getMin(primas));				
		
		maxRange = maxRange.add(new BigDecimal(2000));
		minRange = minRange.subtract(new BigDecimal(2000));						
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<BigDecimal> costoColumn = col.column("Costo Per Cápita", "costo", type.bigDecimalType());
		TextColumnBuilder<BigDecimal> primaColumn = col.column("Prima Per Cápita", "prima", type.bigDecimalType());
				 
		reportePerCapita
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text("Indicadores de siniestralidad").setStyle(Estilos.reportTitleStyle))   		    	   	
   		.summary(
   				cht.lineChart()
   				.customizers(new CustomizedLineChart())
   				.setTitle("Costo per cápita Vs Prima per cápita")
   				.setTitleColor(Color.BLUE)
   				.setTitleFont(Estilos.chartFontStyle)
   				.setCategory(periodoColumn)   				
   				.series(
   					cht.serie(costoColumn), cht.serie(primaColumn))
   					.setCategoryAxisFormat(
   					cht.axisFormat().setLabel("Periodo"))
   					.setValueAxisFormat(   							
   							cht.axisFormat().setTickLabelMask("$ #,###.##").setRangeMinValueExpression(minRange).setRangeMaxValueExpression(maxRange)
   							)
   				)      	   		   		
   		.setDataSource(crearPerCapitaDS(this.sinPerCapita)) 	   		
   		.build();
		
		return reportePerCapita;
	}
	
	private BigDecimal getMax(ArrayList<BigDecimal> data) {
				
		BigDecimal max = data.get(0);	
				
		for(BigDecimal i: data) {			
			max = i.max(max);
		}
		
		return max;
	}
	
	private BigDecimal getMin(ArrayList<BigDecimal> data) {
				
		BigDecimal min = data.get(0);	
				
		for(BigDecimal i: data) {			
			min = min.min(i);
		}
		
		return min;
	}
	
	/**
	 * Genera datos para crear grafica de Siniestralidad
	 * per capita
	 * @param indicadores lista con objetos PerCapita 
	 * @return JRDataSource para alimentar grafica 
	 */
	private JRDataSource crearPerCapitaDS(ArrayList<PerCapita> indicadores) {
		DRDataSource dataSource = new DRDataSource("periodo", "costo","prima");
		
		for(PerCapita pc : indicadores) {
			dataSource.add(pc.getPeriodo(),pc.getCostoPerCapita(),pc.getPrimaPerCapita());
		}
		
		return dataSource;
	}
	
	/**
	 * Concatena los reportes a crear y genera el archivo PDF
	 * @param listaReportes Lista con los reportes a concatenar
	 */
	public void ejecutarReporte(ArrayList<JasperReportBuilder> listaReportes) {
		
		JasperConcatenatedReportBuilder report = new JasperConcatenatedReportBuilder();
		Iterator<JasperReportBuilder> itr = listaReportes.iterator();
		while(itr.hasNext()) {
			report			
	    		.concatenate(itr.next());
		}
		try {			
				report.toPdf(Exporters.pdfExporter("/Users/Meltsan/Desktop/report.pdf"));			    			
			 } catch (DRException e) {			
				 e.printStackTrace();			
			 }

	}	
}
