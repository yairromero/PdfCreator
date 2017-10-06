package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;
import java.util.Locale;

import com.meltsan.pdfcreator.beans.ParticipacionAsegurado;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportParticipacionAseguradoExp extends AbstractSimpleExpression<JasperReportBuilder> {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
	
		   ArrayList<ParticipacionAsegurado> srt = reportParameters.getValue("columns");		   
		   
		   TextColumnBuilder<String> vigenciaColumn = col.column("Vigencia","vigencia", type.stringType()).setFixedWidth(70);
		   TextColumnBuilder<String> montoTotalColumn = col.column("Monto","montototal", type.stringType());
		   TextColumnBuilder<String> porCienTotalColumn = col.column("%","porcientotal", type.stringType());
	   		ArrayList<Columns> columns = new ArrayList<Columns>();
	   		
	   		for(int i=0;i<srt.size();i++) {		   			
	   			columns.add(new Columns(col.column("Monto","monto"+i, type.stringType()).setFixedWidth(80),
	   															col.column("%","porcien"+i, type.stringType())));		   			
	   		}
	   		
	   		
	   		ArrayList<ColumnTitleGroupBuilder> groups = new ArrayList<ColumnTitleGroupBuilder>();
	   		
	   		for(int i=0;i<srt.size();i++) {		   			
	   			groups.add(grid.titleGroup(srt.get(i).getPeriodo(),columns.get(i).getMonto(),columns.get(i).getPorCien()));
	   		}
	   		groups.add(grid.titleGroup("TOTAL",montoTotalColumn,porCienTotalColumn));
	   		
	   		HorizontalColumnGridListBuilder gridList = grid.horizontalColumnGridList();		   		
	   		gridList.add(vigenciaColumn);
	   		
	   		for(ColumnTitleGroupBuilder ctb: groups) {
	   			gridList.add(ctb);
	   		}
	   		
	         JasperReportBuilder report = report();		
	         report.setLocale(Locale.US);
	         report.setTemplate(Estilos.reportConditionalPorcentajeTemplate);
	         report.columnGrid(gridList);
	         report.addColumn(vigenciaColumn);
	         for(Columns col: columns){
	        	 	report.addColumn(col.getMonto(),col.getPorCien());
	         }
	         report.addColumn(montoTotalColumn);
	         report.addColumn(porCienTotalColumn);
	         return report;
	      }

	private class Columns{
		
		private TextColumnBuilder<String> monto;
		private TextColumnBuilder<String> porcien;		
		
		public Columns(TextColumnBuilder<String> monto,TextColumnBuilder<String> porcien) {
			this.monto = monto;
			this.porcien = porcien;			
		}

		public TextColumnBuilder<String> getMonto() {
			return monto;
		}

		public TextColumnBuilder<String> getPorCien() {
			return porcien;
		}
		
	}
	
	}




