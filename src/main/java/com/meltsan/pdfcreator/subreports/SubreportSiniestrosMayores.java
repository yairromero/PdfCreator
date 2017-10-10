package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;
import java.util.Locale;

import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.util.Estilos;
import com.meltsan.pdfcreator.util.Utilidades;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportSiniestrosMayores extends AbstractSimpleExpression<JasperReportBuilder> {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
	
		 		ArrayList<SiniestrosMayores> sm = reportParameters.getValue("columns");							
		 		ArrayList<String> periodos = Utilidades.getPeriodosSinMayores(sm);
		 		
		         JasperReportBuilder report = report();		         
		         report.setTemplate(Estilos.reportSmallTemplate);
		         report.setLocale(Locale.US);
		         report.addColumn(col.reportRowNumberColumn("No.").setFixedWidth(30));
		         report.addColumn(col.column("Siniestro","siniestro",type.stringType()));
		         report.addColumn(col.column("Padecimiento","padecimiento",type.stringType()).setFixedWidth(175));
		         		         		         
		         for (String entity : periodos) {
		        	 
		           report.addColumn(col.column(entity, entity, type.stringType()));	
		         }				                 
		         report.addColumn(col.column("Acumulado "+periodos.size()+" años","acumulado",type.stringType()));

		         return report;
		      }
	
	}