package com.meltsan.pdfcreator;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportSiniestroRangoExp extends AbstractSimpleExpression<JasperReportBuilder> {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
	
		   ArrayList<SiniestroRangoPeriodo> srt = reportParameters.getValue("columns");							
			
			CustomGroupBuilder group = grp.group("categoria",String.class);
			
		         JasperReportBuilder report = report();		         
		         report.setTemplate(Estilos.reportSmallTemplate);		
		         report.addField("categoria", String.class);
		         report.addColumn(col.column("Vigencia", "vigencia", type.stringType()));
		         for (SiniestroRangoPeriodo srp : srt) {		
		           report.addColumn(col.column(srp.getPeriodo(), srp.getPeriodo(), type.stringType()));	
		         }		
		         report.groupBy(group);		         
		         return report;
		      }
	
	
	}




