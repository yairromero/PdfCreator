package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.Constantes;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportPoblacionHistoricaExp extends AbstractSimpleExpression<JasperReportBuilder> {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
	
		   ArrayList<PobHistoricaValues> srt = reportParameters.getValue("columns");		   
		   
		         JasperReportBuilder report = report();		         
		         report.setTemplate(Estilos.reportConditionalPorcentajeTemplate)	
		         		.title(cmp.text(Constantes.POBLACION_HIST_TABLA_TITULO).setStyle(Estilos.chartTitleStyle)
		         																.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
		         		.addColumn(col.column("Vigencia", "vigencia", type.stringType()).setWidth(150));
		         for (PobHistoricaValues srp : srt) {		
		           report.addColumn(col.column(srp.getPeriodo(), srp.getPeriodo(), type.stringType()));	
		         }				             
		         return report;
		      }
	
	
	}




