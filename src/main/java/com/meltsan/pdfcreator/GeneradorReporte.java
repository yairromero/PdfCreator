package com.meltsan.pdfcreator;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;

import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.Constantes;
import com.meltsan.pdfcreator.beans.CostoPerCapitaTarifas;
import com.meltsan.pdfcreator.beans.CostoPromedioSiniestro;
import com.meltsan.pdfcreator.beans.IndicadoresSiniestros;
import com.meltsan.pdfcreator.beans.InflacionSectorSalud;
import com.meltsan.pdfcreator.beans.MisionObjetivo;
import com.meltsan.pdfcreator.beans.MontosPagados;
import com.meltsan.pdfcreator.beans.PadecimientosFrecuencia;
import com.meltsan.pdfcreator.beans.PoblacionHistorica;
import com.meltsan.pdfcreator.beans.SiniestralidadEsperada;
import com.meltsan.pdfcreator.beans.SiniestroPadecimiento;
import com.meltsan.pdfcreator.beans.SiniestroRangoGrafica;
import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.beans.values.CausaValues;
import com.meltsan.pdfcreator.beans.values.CostoPromedioSiniestroValues;
import com.meltsan.pdfcreator.beans.values.IndicadoresSiniestroValues;
import com.meltsan.pdfcreator.beans.values.InflacionSSValues;
import com.meltsan.pdfcreator.beans.values.ParentescoValues;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.values.SexoValues;
import com.meltsan.pdfcreator.beans.values.TipoPagoValues;
import com.meltsan.pdfcreator.customizers.CustomizedBarChart;
import com.meltsan.pdfcreator.customizers.CustomizedCurrencyLineChart;
import com.meltsan.pdfcreator.customizers.CustomizedLabelVertBarChart;
import com.meltsan.pdfcreator.customizers.CustomizedLabelVertLineChart;
import com.meltsan.pdfcreator.customizers.CustomizedNoBorderLineChart;
import com.meltsan.pdfcreator.customizers.CustomizedPercentageBarChart;
import com.meltsan.pdfcreator.customizers.CustomizedPercentageLineChart;
import com.meltsan.pdfcreator.customizers.CustomizedPercentagePieChart;
import com.meltsan.pdfcreator.subreports.SubreportCostoPromedioSinExp;
import com.meltsan.pdfcreator.subreports.SubreportIndicadoresSinExp;
import com.meltsan.pdfcreator.subreports.SubreportPoblacionHistoricaExp;
import com.meltsan.pdfcreator.subreports.SubreportSiniestroRangoExp;
import com.meltsan.pdfcreator.subreports.SubreportSiniestrosMayoresExp;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.RectangleBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;


public class GeneradorReporte {
	
	private DataSources ds;
	
	private Antecedentes reporteAntecedentes;
	private SiniestralidadEsperada reporteSiniestralidadEsperada;
	private PoblacionHistorica reportePoblacionHistorica;
	private IndicadoresSiniestros reporteIndicadoresSiniestralidad;
	private InflacionSectorSalud reporteInflacionSectorSalud;	
	private SiniestroPadecimiento reporteSiniestrosPadecimientos;
	private PadecimientosFrecuencia reportePadecimientosFrecuentes;
	private MontosPagados reporteMontosPagados;
	private MisionObjetivo reporteMisionVision;
	private CostoPromedioSiniestro reporteCostoPromedio;
	private ArrayList<SiniestrosMayores> reporteSiniestrosMayores;
	private ArrayList<SiniestroRangoGrafica> reporteSiniestroRangoGrafica;
	private ArrayList<SiniestroRangoPeriodo> reporteSiniestroRangoTabla;	
	private ArrayList<CostoPerCapitaTarifas> reporteCostoVsTarifasFemenino;
	private ArrayList<CostoPerCapitaTarifas> reporteCostoVsTarifasMasculino;
	
	private ArrayList<JasperReportBuilder> listaReportes;
	private BufferedImage img = null;
	private ImageBuilder imgHeader = null;
	private BufferedImage imgLogo = null;
	private BufferedImage imgCal = null;
	private BufferedImage imgHAnte = null;
	private File file = null;
	private String path;
	
	public GeneradorReporte(String path) {
		
		this.path = path;
		
		try {
			
	   		ClassLoader classLoader = getClass().getClassLoader();
	   		file = new File(classLoader.getResource("head.png").getFile());	   		
	   		imgHAnte = ImageIO.read(new File(classLoader.getResource("HeadAnte.png").getFile()));
	   		img = ImageIO.read(file);
	   		imgHeader = cmp.image(img).setImageScale(ImageScale.FILL_FRAME);
	   		file = new File(classLoader.getResource("Logo13.png").getFile());
	   		imgLogo = ImageIO.read(file);	   		
	   		file = new File(classLoader.getResource("cal.png").getFile());
	   		imgCal = ImageIO.read(file);    	   		
	   		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		public void generaReporte() {
		
		ds = new DataSources();	
			
 	   	
 	   listaReportes = new ArrayList<JasperReportBuilder>();
 	   
 	   if(this.getReporteAntecedentes() != null) {
 		   listaReportes.add(reporteAntecedentes(this.getReporteAntecedentes()));
 	   }
 	   
 	  if(this.getReporteSiniestralidadEsperada() != null) {
		   listaReportes.add(reporteSiniestralidadEsperada(this.getReporteSiniestralidadEsperada()));
	   }
 	   
 	  if(this.getReportePoblacionHistorica() != null) {
 		  listaReportes.add(reportePoblacionHistorica(this.getReportePoblacionHistorica()));
 	  }
 	  
 	   if(this.getReporteIndicadoresSiniestralidad() != null) { 		   
 		  listaReportes.add(reporteIndicadoresSiniestro(this.getReporteIndicadoresSiniestralidad()));
 		  listaReportes.add(reporteIndicadoresSiniestroGrafica(this.getReporteIndicadoresSiniestralidad()));
 	   }
 	   
 	   if(this.getReporteInflacionSectorSalud() != null){
 		   listaReportes.add(reporteInflacionSectorSalud(this.getReporteInflacionSectorSalud()));
 	   } 	  
 	 
 	   if(this.getReporteCostoPromedio() != null) {
 		   listaReportes.add(reporteCostoPromedioSiniestro(this.getReporteCostoPromedio()));
 	   }
 	   
 	   if(this.getReporteSiniestroRangoGrafica() != null && !this.getReporteSiniestroRangoGrafica().isEmpty()){
 		  listaReportes.add(reporteRangosSinGrafica(this.getReporteSiniestroRangoGrafica()));
 	   }
 	   
 	   if(this.getReporteSiniestroRangoTabla() != null && !this.getReporteSiniestroRangoTabla().isEmpty()) {
 		  listaReportes.add(reporteRangosSinTabla(this.getReporteSiniestroRangoTabla()));
 	   }
 	   
 	   if(this.getReporteSiniestrosMayores() != null && !this.getReporteSiniestrosMayores().isEmpty()) {
		   listaReportes.add(reporteSiniestrosMayores(this.getReporteSiniestrosMayores()));
	   }
 	  
 	   if(this.getReporteSiniestrosPadecimientos() != null) {
		   listaReportes.add(reporteSinPadecimiento(this.getReporteSiniestrosPadecimientos()));
	   }
 	
 	   if(this.getReportePadecimientosFrecuentes() != null) {
		   listaReportes.add(reportePadecimientosFrecuentes(getReportePadecimientosFrecuentes()));
	   }
 	  	    	   	    	   	   	   
 	   if(this.getReporteMontosPagados() != null){
 		   listaReportes.add(reporteMontosPagados(this.getReporteMontosPagados()));
 	   }
 	   
 	   if(this.getReporteCostoVsTarifasFemenino() != null && !this.getReporteCostoVsTarifasFemenino().isEmpty()) {
 		   listaReportes.add(reporteCostoVsTarifasFem(this.getReporteCostoVsTarifasFemenino()));
 	   }
 	   
 	  if(this.getReporteCostoVsTarifasMasculino() != null && !this.getReporteCostoVsTarifasMasculino().isEmpty()) {
		   listaReportes.add(reporteCostoVsTarifasMasc(this.getReporteCostoVsTarifasMasculino()));
	   }
 	  
 	  
 	  
 	  if(this.getReporteMisionVision() != null) {
 		  listaReportes.add(reporteMisionVision(this.getReporteMisionVision()));
 	  }
 	   ejecutarReporte(listaReportes,this.path);
	}
	
	

	/**
	 * Genera el reporte de Antecedentes utilizando
	 * la información del objeto Antecedentes.
	 * @return JasperReportBuilder con la hoja de antecedentes.
	 */
	private JasperReportBuilder reporteAntecedentes(Antecedentes antecedentes) {       
       
    	    JasperReportBuilder reporteAntecedentes = new JasperReportBuilder();
    	         	       			       			
    	    JasperReportBuilder subreport = report();	
  	      subreport  	
  	      	.setTemplate(Estilos.reportTemplate) 
  	        .columns(
	    			col.column("Vigencia", "vigencia", type.stringType()),
	    			col.column("Periodo", "periodo",  type.stringType())
	    		)
  	      .setDataSource(ds.crearAntecedentesDS(antecedentes.getTablaVigencias()));
    	    
    	    reporteAntecedentes    	    	    	    		
    	   		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
    	   		.title(cmp.image(imgHAnte))    	   		   	   	
    	   		.summary(cmp.verticalList(
    	   					cmp.text(Constantes.ANTE_HEADER).setStyle(Estilos.reportHeadStyle),
    	   					cmp.verticalGap(10),
    	   					cmp.text(Constantes.ANTE_VIGENCIA).setStyle(Estilos.reportSubTitleStyle),
    	   					cmp.text("	"+antecedentes.getVigenciaCompleta()).setStyle(Estilos.reportHeadStyle),
    	   					cmp.verticalGap(10),
    	   					cmp.text(Constantes.ANTE_PERIODO).setStyle(Estilos.reportSubTitleStyle),
    	   					cmp.text("	"+antecedentes.getPeriodoAnalisis()).setStyle(Estilos.reportHeadStyle),
    	   					cmp.verticalGap(25)
    	   				),
    	   				cmp.horizontalList(cmp.subreport(subreport),
    	   									cmp.horizontalGap(10),
    	   									cmp.image(imgCal)
    	   									)
    	   				)      	   		
    	   		.setColumnStyle(Estilos.columnStyle)    	   		
    	   		.build();
    	    		  
    	    return reporteAntecedentes;
	}
	
	/**
	 * Genera el reporte de Siniestralidad Esperada utilizando
	 * el objeto SiniestralidadEsperada
	 * @return JasperReportBuilder con la hoja de siniestralidad
	 * esperada
	 */
	private JasperReportBuilder reporteSiniestralidadEsperada(SiniestralidadEsperada sinEsperada) {
	
		JasperReportBuilder reporteSinEsperada = new JasperReportBuilder();
		
		reporteSinEsperada
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.SIN_ESPERADA_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					cmp.text(sinEsperada.getTexto())
   							.setStyle(Estilos.reportSubTitleStyle)
   							.setHorizontalTextAlignment(HorizontalTextAlignment.JUSTIFIED)
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
		
		SubreportBuilder subreport = cmp.subreport(new SubreportPoblacionHistoricaExp())
				.setDataSource(ds.crearPobHistoricoTablaDS(pob.getValores()));
		
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
		seriesColors.put("Asegurados", Estilos.colorNavy);
		
		ArrayList<Integer> intPH = new ArrayList<Integer>();
		
		for(PobHistoricaValues pc:pob.getValores()) {
			intPH.add(pc.getAsegurados());			
		}
		
		Integer maxRange = getMaxInteger(intPH) + 100;
		Integer minRange = getMinInteger(intPH) - 100;	
		 
		reportePobHistorica
		.addParameter("columns",pob.getValores())
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.POBLACION_HIST_TITULO).setStyle(Estilos.reportTitleStyle))  
   		.setDataSource(new JREmptyDataSource(1))
   		.summary(cmp.verticalList(
   						subreport,
   						cmp.horizontalList(
   								cht.barChart()   									
   									.setDataSource(ds.crearPobHistoricoGraficaDS(pob.getValores()))   									
   									.setTitle(Constantes.POBLACION_HIST_GRAFICA_TITULO)
   									.setTitleFont(Estilos.chartFontStyle)
   									.setTitleColor(Estilos.colorBlueLight)
   									.seriesColorsByName(seriesColors)
   									.setCategory(periodoColumn)
   										.series(
   												cht.serie(aseguradosColumn))
   													.setValueAxisFormat(
   															cht.axisFormat().setRangeMinValueExpression(minRange).setRangeMaxValueExpression(maxRange)
   															),
   													cmp.verticalList(cmp.text(""),textoLateral)
   									)   								
   						)
   				 )
   		.build();		
		return reportePobHistorica;
	}
	
	/**
	 * Genera el reporte de Tabla de Indicadores Siniestralidad utilizando
	 * el objeto IndicadoresSiniestros
	 * @return JasperReportBuilder con la hoja de indicadores
	 * de siniestralidad
	 */
	private JasperReportBuilder reporteIndicadoresSiniestro(IndicadoresSiniestros siniestros) {
	
		JasperReportBuilder reporteIndicadoresSin = new JasperReportBuilder();
		
		SubreportBuilder subreport = cmp.subreport(new SubreportIndicadoresSinExp())
				.setDataSource(ds.crearIndicadoresSiniestroDS(siniestros.getValores()));
		
		reporteIndicadoresSin
		.addParameter("columns",siniestros.getValores())
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.INDICADORES_SIN_TITULO).setStyle(Estilos.reportTitleStyle)) 
   		.setDataSource(new JREmptyDataSource(2))
   		.summary(subreport
   				)
   		.build();		
		return reporteIndicadoresSin;
	}
	
	/**
	 * Genera el reporte de Grafica de Indicadores Siniestralidad utilizando
	 * una lista de objetos IndicadoresSiniestros
	 * @return JasperReportBuilder con la hoja de inidicadores
	 * de siniestralidad per capita.
	 */
	private JasperReportBuilder reporteIndicadoresSiniestroGrafica(IndicadoresSiniestros sinPerCapita) {
		
		JasperReportBuilder reportePerCapita = new JasperReportBuilder();
		
		ArrayList<Integer> costos = new ArrayList<Integer>();
		ArrayList<Integer> primas = new ArrayList<Integer>();
		
		
		for(IndicadoresSiniestroValues pc: sinPerCapita.getValores()) {			
			costos.add(pc.getCostoPerCapita());
			primas.add(pc.getPrimaPerCapita());
		}
		
		Integer maxRange = getMaxInteger(costos) > getMaxInteger(primas) ? getMaxInteger(costos) : getMaxInteger(primas);
		Integer minRange = getMinInteger(costos) < getMinInteger(primas) ? getMinInteger(costos) : getMinInteger(primas);			
		
		maxRange = maxRange + 2000;
		minRange = minRange - 2000;						
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<BigDecimal> costoColumn = col.column("Costo Per Cápita", "costo", type.bigDecimalType());
		TextColumnBuilder<BigDecimal> primaColumn = col.column("Prima Per Cápita", "prima", type.bigDecimalType());
		
		TextFieldBuilder<String> textField = cmp.text(sinPerCapita.getTexto())
											.setFixedHeight(100)											
											.setStyle(Estilos.reportTextAreaStyle);
		
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
   				.customizers(new CustomizedCurrencyLineChart())
   				.setTitle(Constantes.PERCAPITA_GRAFICA_TITULO)
   				.setTitleColor(Estilos.colorBlueLight)
   				.setTitleFont(Estilos.chartFontStyle)   				
   				.setCategory(periodoColumn)   				
   				.series(
   					cht.serie(costoColumn), cht.serie(primaColumn))
   					.setCategoryAxisFormat(
   					cht.axisFormat().setLabel("Periodo"))
   					.setValueAxisFormat(   							
   							cht.axisFormat().setTickLabelMask("$ #,###.##").setRangeMinValueExpression(minRange).setRangeMaxValueExpression(maxRange)
   							)  
   				.setDataSource(ds.crearIndicadoresSiniestroGraficaDS(sinPerCapita.getValores())),
   				cmp.verticalGap(20),
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
	private JasperReportBuilder reporteInflacionSectorSalud(InflacionSectorSalud inflacionSS) {
	
		JasperReportBuilder reporteInflacion = new JasperReportBuilder();
		JRDataSource jrds = ds.crearInflacionSSDS(inflacionSS.getValores());
		
		TextFieldBuilder<String> textField = cmp.text(inflacionSS.getTexto())
				.setFixedHeight(100)											
				.setStyle(Estilos.boldCenteredStyle);
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());						
		TextColumnBuilder<Float> costoColumn = col.column("Índice de Inflación del Sector Salud", "indice", type.floatType());		
		
		ArrayList<Float> indices = new ArrayList<Float>();  		
		for(InflacionSSValues pc: inflacionSS.getValores()) {			
			indices.add(pc.getInflacion());
		}
		
		Float maxRange = getMaxFloat(indices) + 2;
		Float minRange = getMinFloat(indices) - 2;
					
		reporteInflacion
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.INFLACIONSS_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					textField,   					
   					cht.lineChart()   	
   					.customizers(new CustomizedPercentageLineChart())
   	   				.setTitle(Constantes.INFLACIONSS_GRAFICA_TITULO)
   	   				.setTitleColor(Estilos.colorBlueLight)
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
	 * Genera el reporte de Costo promedio de siniestro
	 * utilizando el objeto ******** 
	 * @return JasperReportBuilder con la hoja de costo
	 * promedio de siniestro
	 */
	private JasperReportBuilder reporteCostoPromedioSiniestro(CostoPromedioSiniestro siniestros) {
	
		JasperReportBuilder reporteCostoPromedioSin = new JasperReportBuilder();
				
		SubreportBuilder subreport = cmp.subreport(new SubreportCostoPromedioSinExp())
				.setDataSource(ds.crearCostoPromedioTablaDS(siniestros.getValores()));
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<Integer> costoColumn = col.column("Costo Promedio de siniestro", "costo", type.integerType());
		TextColumnBuilder<Integer> costoSinCatasColumn = col.column("Costo Promedio de siniestro (sin catastróficos)", "costosinc", type.integerType());
		TextColumnBuilder<Integer> costoInflSSColumn = col.column("Costo Promedio de siniestro considerando la inflación del sector salud", "costoinfl", type.integerType());
		
		ArrayList<Integer> costos = new ArrayList<Integer>();
		ArrayList<Integer> costossin = new ArrayList<Integer>();
		ArrayList<Integer> costosinf = new ArrayList<Integer>();
		
		Map<String, Color> seriesColors = new HashMap<String, Color>();
		seriesColors.put("Costo Promedio de siniestro", Estilos.colorBlueLight);
		seriesColors.put("Costo Promedio de siniestro (sin catastróficos)", Estilos.colorRedDark);
		seriesColors.put("Costo Promedio de siniestro considerando la inflación del sector salud", Estilos.colorGreenLight);		
		
		for(CostoPromedioSiniestroValues pc: siniestros.getValores()) {			
			costos.add(pc.getCostoPromedio());
			costossin.add(pc.getCostoPromSinCatastrofe());
			costosinf.add(pc.getCostoPromInflacionSS());
		}
		
		int maxcostos = getMaxInteger(costos);
		int maxcostossin = getMaxInteger(costossin);
		int maxcostosinfl = getMaxInteger(costosinf);
		int maxRange = 0;
		
		int mincostos = getMinInteger(costos);
		int mincostossin = getMinInteger(costossin);
		int mincostosinfl = getMinInteger(costosinf);
		int minRange = 0;
		
		if(maxcostos > maxcostossin)
	           if(maxcostos>maxcostosinfl)
	        	   		maxRange = maxcostos;
	        	   		else	
	        	   			maxRange = maxcostosinfl;
		else if(maxcostossin>maxcostosinfl)
					maxRange = maxcostossin;
				else
					maxRange = maxcostosinfl;
		
		if(mincostos < mincostossin)
	           if(mincostos < mincostosinfl)
	        	   		minRange = mincostos;
	        	   		else	
	        	   			minRange = mincostosinfl;
		else if(mincostossin < mincostosinfl)
					minRange = mincostossin;
				else
					minRange = mincostosinfl;
		
		
		maxRange = maxRange + 5000;
		minRange = minRange - 5000;
		
		TextFieldBuilder<String> textField = cmp.text(siniestros.getTexto())
				.setFixedHeight(100)											
				.setStyle(Estilos.reportTextAreaStyle);

		RectangleBuilder rectangulo = cmp.rectangle().setStyle(Estilos.textAreaStyle);

		HorizontalListBuilder textoInferior = cmp.horizontalList()
												.add(textField)
												.setBackgroundComponent(rectangulo);	
		
		reporteCostoPromedioSin
		.addParameter("columns",siniestros.getValores())
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.COSTO_PROMEDIO_SIN_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(subreport,
   					cmp.verticalGap(10),
   					cmp.horizontalList(
   							cht.lineChart()
   							.setHeight(190)
   							.customizers(new CustomizedNoBorderLineChart())
   							.setTitle(Constantes.COSTO_PROMEDIO_GRAFICA_TITULO)
   							.setTitleColor(Estilos.colorBlueLight)
   							.setTitleFont(Estilos.chartFontStyle)   				
   							.setCategory(periodoColumn)
   							.seriesColorsByName(seriesColors)
   							.setLegendFont(Estilos.chartFontMediumStyle)
   							.series(
   									cht.serie(costoColumn), cht.serie(costoSinCatasColumn), cht.serie(costoInflSSColumn))
   										.setCategoryAxisFormat(
   												cht.axisFormat().setLabel("Periodo"))
   															.setValueAxisFormat(   							
   																	cht.axisFormat()
   																		.setTickLabelMask("$ #,###.##")
   																		.setRangeMinValueExpression(minRange)
   																		.setRangeMaxValueExpression(maxRange)
   																	)  
   															.setDataSource(
   																	ds.crearCostoPromedioGraficaDS(siniestros.getValores())
   																), 
   															
   								   							textoInferior
   							)
   					)
   				)
   		.build();		
		return reporteCostoPromedioSin;
	}
	
	/**
	 * Genera el reporte de Grafica de Siniestralidad por
	 * rangos de monto pagado utilizando el objeto SiniestroRango 
	 * @return JasperReportBuilder con la hoja de 
	 * siniestralidad por rangos de monto pagado
	 */
	private JasperReportBuilder reporteRangosSinGrafica(ArrayList<SiniestroRangoGrafica> siniestros) {
	
		JasperReportBuilder reporteRangosSin = new JasperReportBuilder();
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<Float> bajaColumn = col.column("Frecuencia Baja", "baja", type.floatType());
		TextColumnBuilder<Float> altaColumn = col.column("Frecuencia Alta", "alta", type.floatType());
		TextColumnBuilder<Float> severidadColumn = col.column("Severidad", "severa", type.floatType());
		TextColumnBuilder<Float> catastrofeColumn = col.column("Catastrófico", "catastrofe", type.floatType());				
		
		Map<String, Color> seriesColors = new HashMap<String, Color>();
		seriesColors.put("Frecuencia Baja", Estilos.colorGreenLight);
		seriesColors.put("Frecuencia Alta", Estilos.colorGreenDark);
		seriesColors.put("Severidad", Estilos.colorOrange);
		seriesColors.put("Catastrófico", Estilos.colorRed);
		
		reporteRangosSin
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.RANGOS_SIN_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(
   				cht.stackedBarChart()
   					.setHeight(350)
   					.addCustomizer(new CustomizedPercentageBarChart())
   					.setTitle(Constantes.RANGOS_SIN_GRAFICA)
   					.setTitleFont(Estilos.chartFontStyle)
   					.setTitleColor(Estilos.colorNavy)
   					.seriesColorsByName(seriesColors)
   					.setDataSource(ds.crearSiniestroRangoDS(siniestros))
   					.setCategory(periodoColumn)
   					.series(
   							cht.serie(bajaColumn), cht.serie(altaColumn), cht.serie(severidadColumn), cht.serie(catastrofeColumn))   							
   				)
   		.build();		
		return reporteRangosSin;
	}
	
	/**
	 * Genera el reporte de Tabla de Siniestralidad por
	 * rangos de monto pagado utilizando el objeto SiniestroRangoTabla 
	 * @return JasperReportBuilder con la hoja de 
	 * siniestralidad por rangos de monto pagado
	 */
	private JasperReportBuilder reporteRangosSinTabla(ArrayList<SiniestroRangoPeriodo> sinRangoTabla) {

			
		JasperReportBuilder reporteRangosSin = new JasperReportBuilder();
		
		SubreportBuilder subreport = cmp.subreport(new SubreportSiniestroRangoExp())
									.setDataSource(ds.crearSiniestroRangoDSTabla(sinRangoTabla));
	      
		reporteRangosSin
		.addParameter("columns",sinRangoTabla)
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.RANGOS_SIN_TITULO).setStyle(Estilos.reportTitleStyle))   
   		.detail(subreport)
   		.setDataSource(new JREmptyDataSource(1))
   		.build();
		
		return reporteRangosSin;
	}
	
	/**
	 * Genera el reporte de siniestros mayores
	 * rangos de monto pagado 
	 * @param siniestros ArrayList de objetos de tipo
	 * SiniestrosMayores
	 * @return JasperReportBuilder con la hoja de 
	 * siniestros mayores
	 */
	private JasperReportBuilder reporteSiniestrosMayores(ArrayList<SiniestrosMayores> siniestros) {
	
		JasperReportBuilder reporteSinMayores = new JasperReportBuilder();
		
		SubreportBuilder subreport = cmp.subreport(new SubreportSiniestrosMayoresExp())
				.setDataSource(ds.crearSiniestrosMayoresDS(siniestros));
		
		reporteSinMayores
		.addParameter("columns",siniestros)
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.SIN_MAYORES_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.detail(subreport)
   		.setDataSource(new JREmptyDataSource(1))
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
	private JasperReportBuilder reporteSinPadecimiento(SiniestroPadecimiento siniestroPadecimiento) {
	
		JasperReportBuilder reporteSinPadecimiento = new JasperReportBuilder();
		
		TextColumnBuilder<String> padecimientoColumn = col.column("Padecimiento", "padecimiento", type.stringType());
		TextColumnBuilder<Long> siniestroColumn = col.column("Siniestro", "siniestro", type.longType()).setPattern("########0");
		
		JasperReportBuilder subreport = report();		
	      subreport		
	         .setTemplate(Estilos.reportTemplate)		
	         .columns(siniestroColumn, padecimientoColumn)		
	         .setDataSource(ds.crearSiniestroPadecimientoDS(siniestroPadecimiento.getSiniestros()));
		
		
		reporteSinPadecimiento
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.SIN_PADECIMIENTO_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   					cmp.text(""),
   					cmp.text(siniestroPadecimiento.getTexto()).setStyle(Estilos.reportSubTitleStyle),
   					cmp.text(""),
   					cmp.subreport(subreport)
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
	private JasperReportBuilder reportePadecimientosFrecuentes(PadecimientosFrecuencia padecimientos) {
	
		JasperReportBuilder reporteTopPadecimientos = new JasperReportBuilder();
		
		TextColumnBuilder<String> padecimientoColumn = col.column("Padecimiento", "padecimiento", type.stringType());
		TextColumnBuilder<Float> freqColumn = col.column("Frecuencia", "frecuencia", type.floatType());
		
		TextFieldBuilder<String> textField = cmp.text(padecimientos.getTexto())
				.setFixedHeight(100)											
				.setStyle(Estilos.reportTextAreaStyle);

		RectangleBuilder rectangulo = cmp.rectangle().setStyle(Estilos.textAreaStyle);

		HorizontalListBuilder textoInferior = cmp.horizontalList()
														.add(textField)
														.setBackgroundComponent(rectangulo);	
		
		reporteTopPadecimientos
		.setLocale(Locale.US)
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.TOP_PADECIMIENTOS_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalGap(20),
   				cmp.verticalList(
   					cmp.horizontalGap(20),	
   					cmp.horizontalList(
   							cht.pieChart()
   							.setDataSource(ds.crearPadecimientoFrecuenciaDS(padecimientos.getPadecimientos(),1))
   							.customizers(new CustomizedPercentagePieChart())   							
   							.setLabelFormat("{2}")  
   							.setTitle("Total de Padecimientos")   	
   							.setTitleColor(Estilos.colorBlueLight)
   			   				.setTitleFont(Estilos.chartFontStyle) 
   							.setKey(padecimientoColumn)
   							.series(   				
   									cht.serie(freqColumn)),
   							cht.pieChart()
   							.setDataSource(ds.crearPadecimientoFrecuenciaDS(padecimientos.getPadecimientos(),2))
   							.customizers(new CustomizedPercentagePieChart())						 
   							.setLabelFormat("{2}")  
   							.setTitle("Top "+padecimientos.getPadecimientos().size()+" padecimientos")   
   							.setTitleColor(Estilos.colorBlueLight)
   			   				.setTitleFont(Estilos.chartFontStyle) 
   							.setKey(padecimientoColumn)
   							.series(   				
   									cht.serie(freqColumn))
   							),
   					cmp.text(""),
   	   				textoInferior
   				  	)
   				)
   		.build();		
		return reporteTopPadecimientos;
	}
			

	/**
	 * Genera el reporte de Tablas de Montos Pagados
	 * @param montos Objeto tipo MontosPagados
	 * @return JasperReportBuilder con la hoja de 
	 * la tabla de padecimientos cronicos 
	 */
	private JasperReportBuilder reporteMontosPagados(MontosPagados montos) {
	
		JasperReportBuilder reportePadecimientos = new JasperReportBuilder();
		
		ArrayList<Float> maxmin = new ArrayList<Float>();
		
		for(TipoPagoValues pc:montos.getMontoTipoPago()) {
			maxmin.add(pc.getMontoPagoDirecto());
			maxmin.add(pc.getMontoReembolso());
		}
		
		Float maxRangeTP = getMaxFloat(maxmin) + 2;
		Float minRangeTP = getMinFloat(maxmin) - 2;
		
		maxmin.clear();
		
		for(CausaValues pc:montos.getMontoCausa()) {
			maxmin.add(pc.getMontoAccidente());
			maxmin.add(pc.getMontoEnfermedad());
			maxmin.add(pc.getMontoParto());
		}
		
		Float maxRangeC = getMaxFloat(maxmin) + 2;
		Float minRangeC = getMinFloat(maxmin) - 2;
		
		maxmin.clear();
		
		for(SexoValues pc:montos.getMontoSexo()) {
			maxmin.add(pc.getMontoMasculino());
			maxmin.add(pc.getMontoFemenino());			
		}
		
		Float maxRangeS = getMaxFloat(maxmin) + 2;
		Float minRangeS = getMinFloat(maxmin) - 2;
		
		maxmin.clear();
		
		for(ParentescoValues pc:montos.getMontoParentesco()) {
			maxmin.add(pc.getMontoTitular());
			maxmin.add(pc.getMontoDependiente());			
		}
		
		Float maxRangeP = getMaxFloat(maxmin) + 2;
		Float minRangeP = getMinFloat(maxmin) - 2;
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<Float> pagoDirectoColumn = col.column("Pago Directo", "pagodirecto", type.floatType());
		TextColumnBuilder<Float> reembolsoColumn = col.column("Reembolso", "reembolso", type.floatType());
		TextColumnBuilder<Float> accidenteColumn = col.column("Accidente", "accidente", type.floatType());
		TextColumnBuilder<Float> enfermedadColumn = col.column("Enfermedad", "enfermedad", type.floatType());
		TextColumnBuilder<Float> partoColumn = col.column("Parto", "parto", type.floatType());
		TextColumnBuilder<Float> femeninoColumn = col.column("Femenino", "femenino", type.floatType());
		TextColumnBuilder<Float> masculinoColumn = col.column("Masculino", "masculino", type.floatType());
		TextColumnBuilder<Float> titularColumn = col.column("Titular", "titular", type.floatType());
		TextColumnBuilder<Float> dependienteColumn = col.column("Dependiente", "dependiente", type.floatType());						
		
		Map<String, Color> seriesColors = new HashMap<String, Color>();
		seriesColors.put("Pago Directo", Estilos.colorRedDark);
		seriesColors.put("Reembolso", Estilos.colorNavy);
		seriesColors.put("Femenino", Estilos.colorRedDark);
		seriesColors.put("Masculino", Estilos.colorNavy);
		seriesColors.put("Accidente", Estilos.colorRedDark);
		seriesColors.put("Enfermedad", Estilos.colorNavy);
		seriesColors.put("Dependiente", Estilos.colorRedDark);
		seriesColors.put("Titular", Estilos.colorNavy);
		seriesColors.put("Parto", Color.GRAY);
		
		reportePadecimientos
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(" "))   		
   		.summary(cmp.verticalList(
   					cmp.horizontalList(
   							//Grafica Tipo Pago
   							cht.barChart()
   								.setHeight(190)
   								.customizers(new CustomizedBarChart())
								.setDataSource(ds.crearMontosPagadosTipoPagoDS(montos.getMontoTipoPago()))   									
								.setTitle(Constantes.TIPO_PAGO_GRAFICA_TITULO)
								.setTitleFont(Estilos.chartFontStyle)
								.setTitleColor(Estilos.colorBlueLight)
								.seriesColorsByName(seriesColors)
								.setCategory(periodoColumn)
									.series(
											cht.serie(pagoDirectoColumn),cht.serie(reembolsoColumn))												
												.setValueAxisFormat(
														cht.axisFormat().setLabel("Millones")
																		.setTickLabelMask("$ #0")
																		.setRangeMinValueExpression(minRangeTP)
																		.setRangeMaxValueExpression(maxRangeTP)
														),   							
   							//Grafica Causa
							cht.barChart()  
								.setHeight(190)
								.customizers(new CustomizedBarChart())
								.setDataSource(ds.crearMontosPagadosCausaDS(montos.getMontoCausa()))   									
								.setTitle(Constantes.CAUSA_GRAFICA_TITULO)
								.setTitleFont(Estilos.chartFontStyle)
								.setTitleColor(Estilos.colorBlueLight)
								.seriesColorsByName(seriesColors)
								.setCategory(periodoColumn)
									.series(
											cht.serie(accidenteColumn),cht.serie(enfermedadColumn),cht.serie(partoColumn))
												.setValueAxisFormat(
													cht.axisFormat().setLabel("Millones")
																.setTickLabelMask("$ #0")
																.setRangeMinValueExpression(minRangeC)
																.setRangeMaxValueExpression(maxRangeC)
												)
   							),
   					cmp.verticalGap(5),
   					cmp.horizontalList(
   							//Grafica Sexo
   							cht.barChart()
   							.setHeight(190)
   							.customizers(new CustomizedBarChart())
							.setDataSource(ds.crearMontosPagadosSexoDS(montos.getMontoSexo()))   									
							.setTitle(Constantes.SEXO_GRAFICA_TITULO)
							.setTitleFont(Estilos.chartFontStyle)
							.setTitleColor(Estilos.colorBlueLight)
							.seriesColorsByName(seriesColors)
							.setCategory(periodoColumn)							
								.series(
										cht.serie(femeninoColumn),cht.serie(masculinoColumn))
											.setShowTickLabels(true)
											.setValueAxisFormat(
													cht.axisFormat().setLabel("Millones")
															.setTickLabelMask("$ #0")
															.setRangeMinValueExpression(minRangeS)
															.setRangeMaxValueExpression(maxRangeS)
													),   							
							//Grafica Parentesco
						cht.barChart()
							.setHeight(190) 
							.customizers(new CustomizedBarChart())
							.setDataSource(ds.crearMontosPagadosParentescoDS(montos.getMontoParentesco()))   									
							.setTitle(Constantes.PARENTESCO_GRAFICA_TITULO)
							.setTitleFont(Estilos.chartFontStyle)
							.setTitleColor(Estilos.colorBlueLight)
							.seriesColorsByName(seriesColors)
							.setCategory(periodoColumn)
								.series(
										cht.serie(dependienteColumn),cht.serie(titularColumn))
											.setValueAxisFormat(
												cht.axisFormat().setLabel("Millones")
																.setTickLabelMask("$ #0")
																.setRangeMinValueExpression(minRangeP)
																.setRangeMaxValueExpression(maxRangeP)
											)
   							)
   					)
   				)
   		.build();		
		return reportePadecimientos;
	}
	
	/**
	 * Genera el reporte de Costo Per Capita vs Tarifas
	 * Femenino
	 * @param montos Lista de objetos tipo CostoPerCapitaTarifas
	 * @return JasperReportBuilder con la hoja de 
	 * la tabla de padecimientos cronicos 
	 */
	private JasperReportBuilder reporteCostoVsTarifasFem(ArrayList<CostoPerCapitaTarifas> montos) {
		
		JasperReportBuilder reporteCPCT = new JasperReportBuilder();
		
		ArrayList<Float> cpc = new ArrayList<Float>();
		ArrayList<Float> morb = new ArrayList<Float>();
		
		for(CostoPerCapitaTarifas pc:montos) {
			cpc.add(pc.getCosto());
			cpc.add(pc.getTarifas());
			morb.add(pc.getMorbilidad());
		}
		
		Float maxRangeCPC = getMaxFloat(cpc) + 10;
		Float maxRangeMorb = getMaxFloat(morb) + 10;								
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<Float> costoPCColumn = col.column("Costo Per Cápita", "costopc", type.floatType());
		TextColumnBuilder<Float> tarifasColumn = col.column("Tarifas", "tarifas", type.floatType());
		TextColumnBuilder<Float> morbilidadColumn = col.column("Morbilidad", "morbilidad", type.floatType());
		
		Map<String, Color> seriesColors = new HashMap<String, Color>();
		seriesColors.put("Costo Per Cápita", Estilos.colorRedDark);
		seriesColors.put("Tarifas", Estilos.colorNavy);
		seriesColors.put("Morbilidad", Color.GRAY);
		
		reporteCPCT
		.setLocale(Locale.US)
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.COSTOS_TARIFAS_FEM_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   				cht.barChart()	
   					.setHeight(180)
					.customizers(new CustomizedLabelVertBarChart())
					.setDataSource(ds.crearCostoVsTarifasDS(montos))   									
					.setTitle(Constantes.COSTO_TARIFAS_GRAFICA_TITULO)
					.setTitleFont(Estilos.chartFontStyle)
					.setTitleColor(Estilos.colorBlueLight)
					.seriesColorsByName(seriesColors)
					.setCategory(periodoColumn)
					.series(
							cht.serie(costoPCColumn),cht.serie(tarifasColumn))												
								.setValueAxisFormat(
										cht.axisFormat().setLabel("Millares")
														.setTickLabelMask("$ #0")														
														.setRangeMaxValueExpression(maxRangeCPC)
										),
								cmp.verticalGap(5),
				cht.lineChart()					
					.setHeight(180) 
					.customizers(new CustomizedLabelVertLineChart())
					.setDataSource(ds.crearMorbilidadDS(montos))   									
					.setTitle(Constantes.MORBILIDAD_GRAFICA_TITULO)
					.setTitleFont(Estilos.chartFontStyle)
					.setTitleColor(Estilos.colorBlueLight)
					.seriesColorsByName(seriesColors)
					.setCategory(periodoColumn)
					.series(
							cht.serie(morbilidadColumn))	
								.setCategoryAxisFormat(
										cht.axisFormat().setLabelFont(Estilos.chartFontSmallStyle)
										)
								.setValueAxisFormat(
										cht.axisFormat()													
														.setRangeMaxValueExpression(maxRangeMorb)
													)
   					)
   				)
   		.build();
		
		return reporteCPCT;
	}
	
	/**
	 * Genera el reporte de Costo Per Capita vs Tarifas
	 * Masculino
	 * @param montos Lista de objetos tipo CostoPerCapitaTarifas
	 * @return JasperReportBuilder con la hoja de 
	 * la tabla de padecimientos cronicos 
	 */
	private JasperReportBuilder reporteCostoVsTarifasMasc(ArrayList<CostoPerCapitaTarifas> montos) {
		
		JasperReportBuilder reporteCPCT = new JasperReportBuilder();
		
		ArrayList<Float> cpc = new ArrayList<Float>();
		ArrayList<Float> morb = new ArrayList<Float>();
		
		for(CostoPerCapitaTarifas pc:montos) {
			cpc.add(pc.getCosto());
			cpc.add(pc.getTarifas());
			morb.add(pc.getMorbilidad());
		}
		
		Float maxRangeCPC = getMaxFloat(cpc) + 10;
		Float maxRangeMorb = getMaxFloat(morb) + 10;								
		
		TextColumnBuilder<String> periodoColumn = col.column("Periodo", "periodo", type.stringType());
		TextColumnBuilder<Float> costoPCColumn = col.column("Costo Per Cápita", "costopc", type.floatType());
		TextColumnBuilder<Float> tarifasColumn = col.column("Tarifas", "tarifas", type.floatType());
		TextColumnBuilder<Float> morbilidadColumn = col.column("Morbilidad", "morbilidad", type.floatType());
		
		Map<String, Color> seriesColors = new HashMap<String, Color>();
		seriesColors.put("Costo Per Cápita", Estilos.colorRedDark);
		seriesColors.put("Tarifas", Estilos.colorNavy);
		seriesColors.put("Morbilidad", Color.GRAY);
		
		reporteCPCT
		.setLocale(Locale.US)
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.setTitleBackgroundComponent(imgHeader)    	   		
   		.title(cmp.text(Constantes.COSTOS_TARIFAS_MASC_TITULO).setStyle(Estilos.reportTitleStyle))   		
   		.summary(cmp.verticalList(
   				cht.barChart()	
   					.setHeight(180)
					.customizers(new CustomizedLabelVertBarChart())
					.setDataSource(ds.crearCostoVsTarifasDS(montos))   									
					.setTitle(Constantes.COSTO_TARIFAS_GRAFICA_TITULO)
					.setTitleFont(Estilos.chartFontStyle)
					.setTitleColor(Estilos.colorBlueLight)
					.seriesColorsByName(seriesColors)
					.setCategory(periodoColumn)
					.series(
							cht.serie(costoPCColumn),cht.serie(tarifasColumn))												
								.setValueAxisFormat(
										cht.axisFormat().setLabel("Millares")
														.setTickLabelMask("$ #0")														
														.setRangeMaxValueExpression(maxRangeCPC)
										),
								cmp.verticalGap(5),
				cht.lineChart()					
					.setHeight(180) 
					.customizers(new CustomizedLabelVertLineChart())
					.setDataSource(ds.crearMorbilidadDS(montos))   									
					.setTitle(Constantes.MORBILIDAD_GRAFICA_TITULO)
					.setTitleFont(Estilos.chartFontStyle)
					.setTitleColor(Estilos.colorBlueLight)
					.seriesColorsByName(seriesColors)
					.setCategory(periodoColumn)
					.series(
							cht.serie(morbilidadColumn))	
								.setCategoryAxisFormat(
										cht.axisFormat().setLabelFont(Estilos.chartFontSmallStyle)
										)
								.setValueAxisFormat(
										cht.axisFormat()													
														.setRangeMaxValueExpression(maxRangeMorb)
													)
   					)
   				)
   		.build();
		
		return reporteCPCT;
	}
	
	/**
	 * Genera el reporte de Mision y Vision 
	 * @param info Objeto tipo MisionVision
	 * @return JasperReportBuilder con la hoja de 
	 * la tabla de padecimientos cronicos 
	 */
	private JasperReportBuilder reporteMisionVision(MisionObjetivo info){
		JasperReportBuilder reporteMision = new JasperReportBuilder();
		
		reporteMision		
		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
   		.summary(cmp.verticalList(
   					cmp.verticalGap(50),
   					cmp.text(info.getMisionTitulo()).setStyle(Estilos.misionTitleStyle),
   					cmp.verticalGap(10),
   					cmp.text(info.getMision()).setStyle(Estilos.misionStyle),
   					cmp.verticalGap(50),
   					cmp.text(info.getObjetivoTitulo()).setStyle(Estilos.misionTitleStyle),
   					cmp.verticalGap(10),
   					cmp.text(info.getObjetivo()).setStyle(Estilos.misionStyle)   					
   					)		
   				)
   		.pageFooter(cmp.verticalList(
   					cmp.image(imgLogo).setHorizontalImageAlignment(HorizontalImageAlignment.CENTER),
   					cmp.verticalGap(5),
   					cmp.text(Constantes.WWW_LOCKTON_LINK).setStyle(Estilos.misionSmallStyle),
   					cmp.text(Constantes.LOCKTON_RIGHTS).setStyle(Estilos.misionSmallStyle)
   					)
   				);
		return reporteMision;
	}
	
	
	/**
	 * Concatena los reportes a crear y genera el archivo PDF
	 * @param listaReportes Lista con los reportes a concatenar
	 */
	private void ejecutarReporte(ArrayList<JasperReportBuilder> listaReportes,String path) {
		
		JasperConcatenatedReportBuilder report = new JasperConcatenatedReportBuilder();
		Iterator<JasperReportBuilder> itr = listaReportes.iterator();
		while(itr.hasNext()) {
			report			
	    		.concatenate(itr.next());
		}
		try {			
				report.toPdf(Exporters.pdfExporter(path));			    			
			 } catch (DRException e) {			
				 e.printStackTrace();			
			 }

	}	
	
	
	private ArrayList<SiniestrosMayores> getReporteSiniestrosMayores() {
		return reporteSiniestrosMayores;
	}
	

	public void setReporteSiniestrosMayores(ArrayList<SiniestrosMayores> reporteSiniestrosMayores) {
		this.reporteSiniestrosMayores = reporteSiniestrosMayores;
	}
	
	private Antecedentes getReporteAntecedentes() {
		return reporteAntecedentes;
	}

	public void setReporteAntecedentes(Antecedentes reporteAntecedentes) {
		this.reporteAntecedentes = reporteAntecedentes;
	}
	
	private SiniestralidadEsperada getReporteSiniestralidadEsperada() {
		return reporteSiniestralidadEsperada;
	}

	public void setReporteSiniestralidadEsperada(SiniestralidadEsperada reporteSiniestralidadEsperada) {
		this.reporteSiniestralidadEsperada = reporteSiniestralidadEsperada;
	}
	
	private PoblacionHistorica getReportePoblacionHistorica() {
		return reportePoblacionHistorica;
	}

	public void setReportePoblacionHistorica(PoblacionHistorica reportePoblacionHistorica) {
		this.reportePoblacionHistorica = reportePoblacionHistorica;
	}
	
	private IndicadoresSiniestros getReporteIndicadoresSiniestralidad() {
		return reporteIndicadoresSiniestralidad;
	}

	public void setReporteIndicadoresSiniestralidad(IndicadoresSiniestros reporteIndicadoresSiniestralidad) {
		this.reporteIndicadoresSiniestralidad = reporteIndicadoresSiniestralidad;
	}
	
	private InflacionSectorSalud getReporteInflacionSectorSalud() {		
		return reporteInflacionSectorSalud;
	}

	public void setReporteInflacionSectorSalud(InflacionSectorSalud reporteInflacionSectorSalud) {
		this.reporteInflacionSectorSalud = reporteInflacionSectorSalud;
	}
	
	private ArrayList<SiniestroRangoGrafica> getReporteSiniestroRangoGrafica() {
		return reporteSiniestroRangoGrafica;
	}

	public void setReporteSiniestroRangoGrafica(ArrayList<SiniestroRangoGrafica> reporteSiniestroRangoGrafica) {
		this.reporteSiniestroRangoGrafica = reporteSiniestroRangoGrafica;
	}
	
	private ArrayList<SiniestroRangoPeriodo> getReporteSiniestroRangoTabla() {
		return reporteSiniestroRangoTabla;
	}

	public void setReporteSiniestroRangoTabla(ArrayList<SiniestroRangoPeriodo> reporteSiniestroRangoTabla) {
		this.reporteSiniestroRangoTabla = reporteSiniestroRangoTabla;
	}

	private SiniestroPadecimiento getReporteSiniestrosPadecimientos() {
		return reporteSiniestrosPadecimientos;
	}
	

	public void setReporteSiniestrosPadecimientos(SiniestroPadecimiento reporteSiniestrosPadecimientos) {
		this.reporteSiniestrosPadecimientos = reporteSiniestrosPadecimientos;
	}

	private PadecimientosFrecuencia getReportePadecimientosFrecuentes() {
		return reportePadecimientosFrecuentes;
	}

	public void setReportePadecimientosFrecuentes(PadecimientosFrecuencia reportePadecimientosFrecuentes) {
		this.reportePadecimientosFrecuentes = reportePadecimientosFrecuentes;
	}
	
	private MontosPagados getReporteMontosPagados() {
		return reporteMontosPagados;
	}

	public void setReporteMontosPagados(MontosPagados reporteMontosPagados) {
		this.reporteMontosPagados = reporteMontosPagados;
	}

	private ArrayList<CostoPerCapitaTarifas> getReporteCostoVsTarifasFemenino() {
		return reporteCostoVsTarifasFemenino;
	}

	public void setReporteCostoVsTarifasFemenino(ArrayList<CostoPerCapitaTarifas> reporteCostoVsTarifasFemenino) {
		this.reporteCostoVsTarifasFemenino = reporteCostoVsTarifasFemenino;
	}

	private ArrayList<CostoPerCapitaTarifas> getReporteCostoVsTarifasMasculino() {
		return reporteCostoVsTarifasMasculino;
	}

	public void setReporteCostoVsTarifasMasculino(ArrayList<CostoPerCapitaTarifas> reporteCostoVsTarifasMasculino) {
		this.reporteCostoVsTarifasMasculino = reporteCostoVsTarifasMasculino;
	}
	private CostoPromedioSiniestro getReporteCostoPromedio() {
		return reporteCostoPromedio;
	}

	public void setReporteCostoPromedio(CostoPromedioSiniestro reporteCostoPromedio) {
		this.reporteCostoPromedio = reporteCostoPromedio;
	}
		
	private MisionObjetivo getReporteMisionVision() {
		return reporteMisionVision;
	}

	public void setReporteMisionVision(MisionObjetivo reporteMisionVision) {
		this.reporteMisionVision = reporteMisionVision;
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
	
	
	private Float getMaxFloat(ArrayList<Float> data) {
		Float min = data.get(0);
        for (Float i : data){
            min = min > i ? min : i;
        }
        return min;
	}
	
	
	private Float getMinFloat(ArrayList<Float> data) {
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
	
	private void getColor(int r, int g, int b) {
		float[] hbsvals = new float[3];
		Color.RGBtoHSB(r, g, b, hbsvals);
		System.out.println("h: "+hbsvals[0]);
		System.out.println("b: "+hbsvals[1]);
		System.out.println("s: "+hbsvals[2]);		
	}
}
