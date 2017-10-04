package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;
import java.util.Locale;

import com.meltsan.pdfcreator.beans.PadCronicosMontos;
import com.meltsan.pdfcreator.util.Estilos;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportPadecimientosCronicosExp extends AbstractSimpleExpression<JasperReportBuilder>{

	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
		
		   ArrayList<PadCronicosMontos> srt = reportParameters.getValue("columns");		   
		   		
		   		TextColumnBuilder<String> vigenciaColumn = col.column("Vigencia","vigencia", type.stringType()).setFixedWidth(90);
		   		ArrayList<Columns> columns = new ArrayList<Columns>();
		   		
		   		for(int i=0;i<srt.size();i++) {		   			
		   			columns.add(new Columns(col.column("Monto Pagado","monto"+i, type.stringType()),
		   															col.column("No. Siniestros","siniestro"+i, type.stringType()),
		   																col.column("Costo Promedio","costo"+i, type.stringType())));		   			
		   		}
		   		
		   		ArrayList<ColumnTitleGroupBuilder> groups = new ArrayList<ColumnTitleGroupBuilder>();
		   		
		   		for(int i=0;i<srt.size();i++) {		   			
		   			groups.add(grid.titleGroup(srt.get(i).getPeriodo(),columns.get(i).getMonto(),columns.get(i).getSiniestro(),columns.get(i).getCosto()));
		   		}
		   		
		   		HorizontalColumnGridListBuilder gridList = grid.horizontalColumnGridList();		   		
		   		gridList.add(vigenciaColumn);
		   		
		   		for(ColumnTitleGroupBuilder ctb: groups) {
		   			gridList.add(ctb);
		   		}
		   		
		         JasperReportBuilder report = report();		
		         report.setLocale(Locale.US);
		         report.setTemplate(Estilos.reportSmallTemplate);
		         report.columnGrid(gridList);
		         report.addColumn(vigenciaColumn);
		         for(Columns col: columns){
		        	 	report.addColumn(col.getMonto(),col.getSiniestro(),col.getCosto());
		         }
		         				             
		         return report;
		      }
	
		private class Columns{
			
			private TextColumnBuilder<String> monto;
			private TextColumnBuilder<String> siniestro;
			private TextColumnBuilder<String> costo;
 			
			public Columns(TextColumnBuilder<String> monto,TextColumnBuilder<String> siniestro,TextColumnBuilder<String> costo) {
				this.monto = monto;
				this.siniestro = siniestro;
				this.costo = costo;
			}

			public TextColumnBuilder<String> getMonto() {
				return monto;
			}

			public TextColumnBuilder<String> getSiniestro() {
				return siniestro;
			}

			public TextColumnBuilder<String> getCosto() {
				return costo;
			}
			
		}

}
