package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportSiniestroRango extends AbstractSimpleExpression<JasperReportBuilder> {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
	
		   ArrayList<SiniestroRangoPeriodo> srt = reportParameters.getValue("columns");							
			
			CustomGroupBuilder group = grp.group("categoria",String.class);
			
		         JasperReportBuilder report = report();		         
		         report.setTemplate(Estilos.reportConditionalGroupTemplate);		
		         report.addField("categoria", String.class);
		         report.addColumn(col.column("Vigencia", "vigencia", type.stringType()));
		         for (SiniestroRangoPeriodo srp : srt) {		
		           report.addColumn(col.column(srp.getPeriodo(), srp.getPeriodo(), type.stringType()));	
		         }		
		         report.groupBy(group);		         
		         return report;
		      }
	
	
	}




