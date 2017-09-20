package com.meltsan.pdfcreator;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;


import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.Constantes;
import com.meltsan.pdfcreator.beans.InflacionSS;
import com.meltsan.pdfcreator.beans.InflacionSSValues;
import com.meltsan.pdfcreator.beans.PerCapita;
import com.meltsan.pdfcreator.beans.PerCapitaValues;
import com.meltsan.pdfcreator.beans.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.PoblacionHistorica;
import com.meltsan.pdfcreator.beans.SiniestralidadEsperada;
import com.meltsan.pdfcreator.util.CustomizedLineChart;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.RectangleBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class GeneradorReporte {
	
	private DataSources ds;
	
	private Antecedentes antecedentes;
	private PerCapita sinPerCapita;
	private InflacionSS inflacionSS;
	private SiniestralidadEsperada sinEsperada;	
	
	private ArrayList<JasperReportBuilder> listaReportes;
	private BufferedImage img = null;
	private ImageBuilder imgHeader = null;
	private ImageBuilder imgFooter = null;
	private BufferedImage imgCal = null;
	private File file = null;
	
	public GeneradorReporte(Antecedentes antecedentes, PerCapita sinPerCapita,
							InflacionSS inflacionSS, SiniestralidadEsperada sinEsperada,
							PoblacionHistorica pobHistorica) {
		
		ds = new DataSources();
		this.antecedentes = antecedentes;
		this.sinEsperada = sinEsperada;		
		this.sinPerCapita = sinPerCapita;
		this.inflacionSS = inflacionSS;		
		
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
 	   
 	  if(this.sinEsperada != null) {
		   listaReportes.add(reporteSiniestralidadEsperada());
	   }
 	   
 	  if(pobHistorica != null) {
 		  listaReportes.add(reportePoblacionHistorica(pobHistorica));
 	  }
 	  
 	   if(this.sinPerCapita != null) {
 		   listaReportes.add(reporteSinPerCapita());
 	   }
 	   
 	   if(this.inflacionSS != null){
 		   listaReportes.add(reporteInflacionSectorSalud());
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
  	      .setDataSource(ds.crearAntecedentesDS(this.antecedentes.getTablaVigencias()));
    	    
    	    reporteAntecedentes    	    	
    	    		
    	   		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
    	   		.setTitleBackgroundComponent(imgHeader)    	   		
    	   		.title(cmp.text(Constantes.ANTE_TITULO).setStyle(Estilos.reportTitleStyle))    	   		
    	   		.addPageHeader(content)    	   		
    	   		.summary(
    	   				cmp.horizontalList(cmp.subreport(subreport),cmp.image(imgCal)))      	   		
    	   		.setColumnStyle(Estilos.columnStyle)    	   		
    	   		.build();
    	    		  
    	    return reporteAntecedentes;
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
		
		
		for(PerCapitaValues pc: this.sinPerCapita.getGrafica()) {			
			costos.add(pc.getCostoPerCapita());
			primas.add(pc.getPrimaPerCapita());
		}
		
		BigDecimal maxRange = getMaxBD(costos).max(getMaxBD(primas));
		BigDecimal minRange = getMinBD(costos).min(getMinBD(primas));				
		
		maxRange = maxRange.add(new BigDecimal(2000));
		minRange = minRange.subtract(new BigDecimal(2000));						
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<BigDecimal> costoColumn = col.column("Costo Per Cápita", "costo", type.bigDecimalType());
		TextColumnBuilder<BigDecimal> primaColumn = col.column("Prima Per Cápita", "prima", type.bigDecimalType());
		
		TextFieldBuilder<String> textField = cmp.text(sinPerCapita.getTexto())
											.setFixedHeight(100)											
											.setStyle(Estilos.reportSubTitleStyle);
		
		RectangleBuilder rectangulo = cmp.rectangle().setStyle(Estilos.textAreaStyle);
		
		HorizontalListBuilder textoInferior = cmp.horizontalList()
									.add(textField)
									.setBackgroundComponent(rectangulo);

													 
		reportePerCapita
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.PERCAPITA_TITULO).setStyle(Estilos.reportTitleStyle))   		    	   	
   		.summary(cmp.verticalList(
   				cht.lineChart()   				
   				.customizers(new CustomizedLineChart())
   				.setTitle(Constantes.PERCAPITA_GRAFICA_TITULO)
   				.setTitleColor(Color.getHSBColor(0.5868056f, 0.6315789f, 0.59607846f))
   				.setTitleFont(Estilos.chartFontStyle)   				
   				.setCategory(periodoColumn)   				
   				.series(
   					cht.serie(costoColumn), cht.serie(primaColumn))
   					.setCategoryAxisFormat(
   					cht.axisFormat().setLabel("Periodo"))
   					.setValueAxisFormat(   							
   							cht.axisFormat().setTickLabelMask("$ #,###.##").setRangeMinValueExpression(minRange).setRangeMaxValueExpression(maxRange)
   							)  
   				.setDataSource(ds.crearPerCapitaDS(this.sinPerCapita.getGrafica())),
   				cmp.text(""),
   				textoInferior
   				)   			
   			)   		
   		.build();
		
		return reportePerCapita;
	}
	
	/**
	 * Genera el reporte de Inflacion en Sector Salud utilizando
	 * el atributo grafica del objeto InflacionSSValues 
	 * @return JasperReportBuilder con la hoja de inflacion
	 * del sector salud
	 */
	private JasperReportBuilder reporteInflacionSectorSalud() {
	
		JasperReportBuilder reporteInflacion = new JasperReportBuilder();
		JRDataSource jrds = ds.crearInflacionSSDS(this.inflacionSS.getGrafica());
		
		TextFieldBuilder<String> textField = cmp.text(inflacionSS.getTexto())
				.setFixedHeight(100)											
				.setStyle(Estilos.rootStyle);
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());						
		TextColumnBuilder<Float> costoColumn = col.column("Índice de Inflación del Sector Salud", "indice", type.floatType());		
		
		ArrayList<Float> indices = new ArrayList<Float>();  		
		for(InflacionSSValues pc: this.inflacionSS.getGrafica()) {			
			indices.add(pc.getInflacion());
		}
		
		Float maxRange = getMaxDouble(indices) + 2;
		Float minRange = getMinDouble(indices) - 2;
					
		reporteInflacion
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.INFLACIONSS_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					textField,   					
   					cht.lineChart()   	
   					.customizers(new CustomizedLineChart())
   	   				.setTitle(Constantes.INFLACIONSS_GRAFICA_TITULO)
   	   				.setTitleColor(Color.getHSBColor(0.5868056f, 0.6315789f, 0.59607846f))
   	   				.setTitleFont(Estilos.chartFontStyle)   				
   	   				.setCategory(periodoColumn)   				
   	   				.series(
   	   					cht.serie(costoColumn))   	   				
   	   					.setValueAxisFormat(   							
   	   							cht.axisFormat().setTickLabelMask("##,## %").setRangeMinValueExpression(minRange).setRangeMaxValueExpression(maxRange)
   	   							)  
   	   				.setDataSource(jrds)   				
   					)
   				)
   		.build();
		return reporteInflacion;
	}
	
	/**
	 * Genera el reporte de Siniestralidad Esperada utilizando
	 * el objeto ******** 
	 * @return JasperReportBuilder con la hoja de siniestralidad
	 * esperada
	 */
	private JasperReportBuilder reporteSiniestralidadEsperada() {
	
		JasperReportBuilder reporteSinEsperada = new JasperReportBuilder();
		
		reporteSinEsperada
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.SIN_ESPERADA_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					cmp.text(sinEsperada.getTexto()).setStyle(Estilos.reportSubTitleStyle)
   					)
   				)
   		.build();		
		return reporteSinEsperada;
	}
	
	/**
	 * Genera el reporte de Poblacion Historica
	 * @param pob PoblacionHistorica con los datos para alimentar reporte
	 * @return JasperReportBuilder con la hoja de poblacion
	 * historica
	 */
	private JasperReportBuilder reportePoblacionHistorica(PoblacionHistorica pob) {
	
		JasperReportBuilder reportePobHistorica = new JasperReportBuilder();
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<Integer> aseguradosColumn = col.column("Asegurados", "asegurados", type.integerType());				
		
		TextFieldBuilder<String> textField = cmp.text(pob.getTexto())
				.setFixedHeight(50)											
				.setStyle(Estilos.reportSubTitleCenteredStyle);

		RectangleBuilder rectangulo = cmp.rectangle().setStyle(Estilos.textAreaStyle);

		HorizontalListBuilder textoLateral = cmp.horizontalList()
												.add(textField)
												.setBackgroundComponent(rectangulo);
		
		Map<String, Color> seriesColors = new HashMap<String, Color>();
		seriesColors.put("Asegurados", Color.getHSBColor(0.6763285f, 0.6764706f, 0.4f));
		
		ArrayList<Integer> intPH = new ArrayList<Integer>();
		
		for(PobHistoricaValues pc:pob.getGrafica()) {
			intPH.add(pc.getAsegurados());			
		}
		
		Integer maxRange = getMaxInteger(intPH) + 100;
		Integer minRange = getMinInteger(intPH) - 100;	
		 
		
		reportePobHistorica
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.POBLACION_HIST_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   						cmp.horizontalList(
   								cht.barChart()
   									.setDataSource(ds.crearPobHistoricoDS(pob.getGrafica()))
   									//.customizers(new CustomizedLineChart())
   									.setTitle(Constantes.POBLACION_HIST_GRAFICA_TITULO)
   									.setTitleFont(Estilos.chartFontStyle)
   									.setTitleColor(Color.getHSBColor(0.5868056f, 0.6315789f, 0.59607846f))
   									.seriesColorsByName(seriesColors)
   									.setCategory(periodoColumn)
   										.series(
   												cht.serie(aseguradosColumn))
   													.setValueAxisFormat(
   															cht.axisFormat().setRangeMinValueExpression(minRange).setRangeMaxValueExpression(maxRange)
   															),
   													textoLateral
   									)   								
   						)
   				 )
   		.build();		
		return reportePobHistorica;
	}
	
	/**
	 * Genera el reporte de Indicadores Siniestralidad utilizando
	 * el objeto ******** 
	 * @return JasperReportBuilder con la hoja de indicadores
	 * de siniestralidad
	 */
	private JasperReportBuilder reporteIndicadoresSiniestro() {
	
		JasperReportBuilder reporteIndicadoresSin = new JasperReportBuilder();
		
		reporteIndicadoresSin
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.INDICADORES_SIN_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reporteIndicadoresSin;
	}
	
	
	/**
	 * Genera el reporte de Costo promedio de siniestro
	 * utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de costo
	 * promedio de siniestro
	 */
	private JasperReportBuilder reporteCostoPromedioSin() {
	
		JasperReportBuilder reporteCostoPromedioSin = new JasperReportBuilder();
		
		reporteCostoPromedioSin
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.COSTO_PROMEDIO_SIN_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reporteCostoPromedioSin;
	}
	
	
	/**
	 * Genera el reporte de Grafica de Siniestralidad por
	 * rangos de monto pagado utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de 
	 * siniestralidad por rangos de monto pagado
	 */
	private JasperReportBuilder reporteRangosSinGrafica() {
	
		JasperReportBuilder reporteRangosSin = new JasperReportBuilder();
		
		reporteRangosSin
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.RANGOS_SIN_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reporteRangosSin;
	}
	
	
	/**
	 * Genera el reporte de Tabla de Siniestralidad por
	 * rangos de monto pagado utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de 
	 * siniestralidad por rangos de monto pagado
	 */
	private JasperReportBuilder reporteRangosSinTabla() {
	
		JasperReportBuilder reporteRangosSin = new JasperReportBuilder();
		
		reporteRangosSin
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.RANGOS_SIN_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reporteRangosSin;
	}
	
	
	/**
	 * Genera el reporte de siniestros mayores
	 * rangos de monto pagado utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de 
	 * siniestros mayores
	 */
	private JasperReportBuilder reporteSiniestrosMayores() {
	
		JasperReportBuilder reporteSinMayores = new JasperReportBuilder();
		
		reporteSinMayores
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.SIN_MAYORES_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reporteSinMayores;
	}
	
	
	/**
	 * Genera el reporte de Grafica de padecimientos
	 * cronicos utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de 
	 * la grafica de padecimientos cronicos 
	 */
	private JasperReportBuilder reportePadecimientosGrafica() {
	
		JasperReportBuilder reportePadecimientos = new JasperReportBuilder();
		
		reportePadecimientos
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.PADECIMIENTOS_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reportePadecimientos;
	}
	
	
	/**
	 * Genera el reporte de Tabla de padecimientos
	 * cronicos utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de 
	 * la tabla de padecimientos cronicos 
	 */
	private JasperReportBuilder reportePadecimientosTabla() {
	
		JasperReportBuilder reportePadecimientos = new JasperReportBuilder();
		
		reportePadecimientos
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.PADECIMIENTOS_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reportePadecimientos;
	}
	
	
	/**
	 * Genera el reporte de Siniestros padecimientos
	 * cronicos utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de 
	 * la tabla de siniestros padecimientos
	 */
	private JasperReportBuilder reporteSinPadecimiento() {
	
		JasperReportBuilder reporteSinPadecimiento = new JasperReportBuilder();
		
		reporteSinPadecimiento
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title()   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reporteSinPadecimiento;
	}
	
	
	/**
	 * Genera el reporte de Top de padecimientos
	 * utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de 
	 * la Top 5 Padecimientos
	 */
	private JasperReportBuilder reporteTopPadecimientos() {
	
		JasperReportBuilder reporteTopPadecimientos = new JasperReportBuilder();
		
		reporteTopPadecimientos
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.TOP_PADECIMIENTOS_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					)
   				)
   		.build();		
		return reporteTopPadecimientos;
	}
	
	
	/**
	 * Funcion para obtener el numero maximo
	 * de una lista
	 * @param data Lista con objetos de tipo BigDecimal
	 * @return BigDecimal mayor de la lista
	 */
	private BigDecimal getMaxBD(ArrayList<BigDecimal> data) {
				
		BigDecimal max = data.get(0);	
				
		for(BigDecimal i: data) {			
			max = i.max(max);
		}
		
		return max;
	}
	
	
	
	
	/**
	 * Funcion para obtener el numero minimo
	 * de una lista
	 * @param data Lista con objetos de tipo BigDecimal
	 * @return BigDecimal menor de la lista
	 */
	private BigDecimal getMinBD(ArrayList<BigDecimal> data) {
		
		BigDecimal min = data.get(0);	
				
		for(BigDecimal i: data) {			
			min = min.min(i);
		}
		
		return min;
	}
	
	
	
	private Float getMaxDouble(ArrayList<Float> data) {
		Float min = data.get(0);
        for (Float i : data){
            min = min > i ? min : i;
        }
        return min;
	}
	
	
	private Float getMinDouble(ArrayList<Float> data) {
		Float min = data.get(0);
        for (Float i : data){
            min = min < i ? min : i;
        }
        return min;
	}
	
	private Integer getMaxInteger(ArrayList<Integer> data) {
		Integer max = data.get(0);
        for (Integer i : data){
            max = max > i ? max : i;
        }
        return max;
	}
	
	private Integer getMinInteger(ArrayList<Integer> data) {
		Integer min = data.get(0);
        for (Integer i : data){
            min = min < i ? min : i;
        }
        return min;
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
