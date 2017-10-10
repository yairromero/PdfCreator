package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

import java.util.ArrayList;
import java.util.Locale;

import com.meltsan.pdfcreator.beans.values.DistribucionGastosValues;
import com.meltsan.pdfcreator.beans.values.TiempoRespuesta;
import com.meltsan.pdfcreator.util.Estilos;
import com.meltsan.pdfcreator.util.Utilidades;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ComponentColumnBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportTiempoRespuesta extends AbstractSimpleExpression<JasperReportBuilder> {
	 
		private static final long serialVersionUID = 1L;
		
		@Override
		public JasperReportBuilder evaluate(ReportParameters reportParameters) {
						  
			   ArrayList<TiempoRespuesta> srt = reportParameters.getValue("columns");	
			   ArrayList<String> meses = Utilidades.getEtiquetasTiempoRespuesta(srt);
			   ArrayList<String> periodos = Utilidades.getPeriodoTiempoRespuesta(srt);
			   
			   FieldBuilder<String> siniestroField = field("siniestro",type.stringType());
			   FieldBuilder<String> porcienField = field("porcien",type.stringType());
			  
			         VerticalListBuilder nameList = cmp.verticalList();
			         
			        		 for(String mes: meses) {
			        			nameList.add(cmp.text(mes));
			        			nameList.add(cmp.text("%Acumulado"));
			        		 }

			         VerticalListBuilder valueList = cmp.verticalList();		   
			         	valueList.add(cmp.text(siniestroField));
			         	valueList.add(cmp.text(porcienField));
			  
			         ComponentColumnBuilder diasColumn = col.componentColumn("Días", nameList);			         

			         JasperReportBuilder report = report();		
			         report.setLocale(Locale.US);
			         report.setTemplate(Estilos.reportSmallTemplate);	
			         report.fields(siniestroField, porcienField);
			         report.columns(diasColumn);		
			         for (String periodo : periodos) {			        	 
			          report.addColumn(col.componentColumn(periodo,valueList));			           
			         }				             
			         return report;
			      }

}
