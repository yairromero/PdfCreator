package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;
import java.util.Locale;

import com.meltsan.pdfcreator.beans.values.TiempoRespuesta;
import com.meltsan.pdfcreator.util.Estilos;
import com.meltsan.pdfcreator.util.Utilidades;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportTiempoRespuesta extends AbstractSimpleExpression<JasperReportBuilder> {
	 
		private static final long serialVersionUID = 1L;
		
		@Override
		public JasperReportBuilder evaluate(ReportParameters reportParameters) {
						  
			   ArrayList<TiempoRespuesta> srt = reportParameters.getValue("columns");				   
			   ArrayList<String> periodos = Utilidades.getPeriodoTiempoRespuesta(srt);
			   
			  TextColumnBuilder<String> diasColumn = col.column("Días","periodo",type.stringType());			         

			         JasperReportBuilder report = report();		
			         report.setLocale(Locale.US);
			         report.setTemplate(Estilos.reportTemplate);			         
			         report.columns(diasColumn);
			         report.addColumn(col.column("<"+periodos.get(0),periodos.get(0),type.stringType()));
			         
			         for (int i=1;i<periodos.size()-1;i++) {			        	 
			        	 	report.addColumn(col.column(periodos.get(i),periodos.get(i),type.stringType()));			           
			         }
			         
			         report.addColumn(col.column(periodos.get(periodos.size()-1)+">",periodos.get(periodos.size()-1),type.stringType()));
			         return report;
			      }

}
