package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.CostoPromedioSiniestroValues;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportCostoPromedioSin extends AbstractSimpleExpression<JasperReportBuilder> {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
	
		   ArrayList<CostoPromedioSiniestroValues> srt = reportParameters.getValue("columns");		   
		   
		         JasperReportBuilder report = report();		         
		         report.setTemplate(Estilos.reportConditionalPorcentajeTemplate)		         		
		         		.addColumn(col.column("Vigencia", "vigencia", type.stringType()).setWidth(120));
		         for (CostoPromedioSiniestroValues srp : srt) {		
		           report.addColumn(col.column(srp.getPeriodo(), srp.getPeriodo(), type.stringType()));	
		         }				             
		         return report;
		      }
	
	
	}




