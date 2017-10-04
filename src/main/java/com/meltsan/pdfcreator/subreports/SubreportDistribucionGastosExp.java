package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;
import java.util.Locale;

import com.meltsan.pdfcreator.beans.values.DistribucionGastosValues;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportDistribucionGastosExp extends AbstractSimpleExpression<JasperReportBuilder> {
	 
		private static final long serialVersionUID = 1L;
		
		@Override
		public JasperReportBuilder evaluate(ReportParameters reportParameters) {
						  
			   ArrayList<DistribucionGastosValues> srt = reportParameters.getValue("columns");			   
			         JasperReportBuilder report = report();		
			         report.setLocale(Locale.US);
			         report.setTemplate(Estilos.reportSmallTemplate)		         		
			         		.addColumn(col.column("", "vigencia", type.stringType()).setWidth(120));
			         for (DistribucionGastosValues srp : srt) {			        	 
			           report.addColumn(col.column(srp.getPeriodo(), srp.getPeriodo(), type.integerType()).setPattern("$ ###,###,###"));			           
			         }				             
			         return report;
			      }

}
