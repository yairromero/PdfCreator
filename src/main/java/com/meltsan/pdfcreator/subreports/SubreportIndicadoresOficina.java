package com.meltsan.pdfcreator.subreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;
import java.util.Locale;

import com.meltsan.pdfcreator.beans.IndicadoresOficina;
import com.meltsan.pdfcreator.util.Estilos;
import com.meltsan.pdfcreator.util.Utilidades;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class SubreportIndicadoresOficina extends AbstractSimpleExpression<JasperReportBuilder> {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public JasperReportBuilder evaluate(ReportParameters reportParameters) {
	
		   ArrayList<IndicadoresOficina> srt = reportParameters.getValue("columns");		  
		   ArrayList<String> periodos = Utilidades.getPeriodosIndicadoresOficina(srt);
		   
		   //TextColumnBuilder<String> vigenciaColumn = col.column("Vigencia","vigencia", type.stringType()).setFixedWidth(70);	
		   TextColumnBuilder<String> oficinaColumn = col.column("Oficina","oficina", type.stringType()).setFixedWidth(70);		   
	   		ArrayList<Columns> columns = new ArrayList<Columns>();
	   		
	   		for(int i=0;i<periodos.size();i++) {		   			
	   			columns.add(new Columns(col.column("Morbilidad","morbo"+i, type.stringType()).setFixedWidth(60),
	   															col.column("Costo Promedio","costo"+i, type.stringType()).setFixedWidth(60),
	   																	col.column("Costo Per Capita","percapita"+i, type.stringType())));		   			
	   		}
	   		
	   		
	   		ArrayList<ColumnTitleGroupBuilder> groups = new ArrayList<ColumnTitleGroupBuilder>();
	   		
	   		for(int i=0;i<periodos.size();i++) {		   			
	   			groups.add(grid.titleGroup(srt.get(i).getPeriodo(),columns.get(i).getMorbo(),columns.get(i).getCosto(),columns.get(i).getPercapita()));
	   		}
	   			   		
	   		HorizontalColumnGridListBuilder gridList = grid.horizontalColumnGridList();		   		
	   		gridList.add(oficinaColumn);
	   		
	   		for(ColumnTitleGroupBuilder ctb: groups) {
	   			gridList.add(ctb);
	   		}
	   		
	         JasperReportBuilder report = report();		
	         report.setLocale(Locale.US);
	         report.setTemplate(Estilos.reportSmallTemplate);
	         report.columnGrid(gridList);
	         report.addColumn(oficinaColumn);
	         for(Columns col: columns){
	        	 	report.addColumn(col.getMorbo(),col.getCosto(),col.getPercapita());
	         }
	        
	         return report;
	      }

	private class Columns{
		
		private TextColumnBuilder<String> morbo;
		private TextColumnBuilder<String> costo;
		private TextColumnBuilder<String> percapita;
		
		public Columns(TextColumnBuilder<String> morbo,TextColumnBuilder<String> costo,TextColumnBuilder<String> percapita) {
			this.morbo = morbo;
			this.percapita = percapita;
			this.costo = costo;
		}

		public TextColumnBuilder<String> getMorbo() {
			return morbo;
		}

		public TextColumnBuilder<String> getCosto() {
			return costo;
		}

		public TextColumnBuilder<String> getPercapita() {
			return percapita;
		}		
	}
	
	}




