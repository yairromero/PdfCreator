package com.meltsan.pdfcreator;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportSiniestrosMayoresExp extends AbstractSimpleExpression<JasperReportBuilder> {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
	
		 ArrayList<SiniestrosMayores> sm = reportParameters.getValue("columns");							
		 int noPeridos = sm.size();
		         JasperReportBuilder report = report();		         
		         report.setTemplate(Estilos.reportTemplate);			         
		         report.addColumn(col.reportRowNumberColumn("No."));
		         report.addColumn(col.column("Siniestro","siniestro",type.stringType()));
		         report.addColumn(col.column("Padecimiento","padecimiento",type.stringType()));
		         for (SiniestrosMayores entity : sm) {		
		           report.addColumn(col.column(entity.getPeriodo(), entity.getPeriodo(), type.stringType()));	
		         }				                 
		         report.addColumn(col.column("Acumulado "+noPeridos+" años", new ExpressionColumn()));

		         return report;
		      }
	
	private class ExpressionColumn extends AbstractSimpleExpression<String> {
		
		      private static final long serialVersionUID = 1L;

		      @Override		
		      public String evaluate(ReportParameters reportParameters) {		
		         return		
		            "Item = ";		            		
		      }
		   }
	
	}




