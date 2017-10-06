package com.meltsan.pdfcreator.customizers;

import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategorySeriesLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.TextAnchor;

import com.meltsan.pdfcreator.beans.ParticipacionAsegurado;
import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;


 
public class CustomizedPercentageBarIntervalChart implements DRIChartCustomizer {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
				
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot(); 
		BarRenderer barRenderer = (BarRenderer) categoryPlot.getRenderer(); 
		barRenderer.setBaseItemLabelGenerator(new CustomLabelGenerator()); 
		barRenderer.setBaseItemLabelsVisible(true);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
		barRenderer.setPositiveItemLabelPositionFallback(p);
		barRenderer.setItemMargin(0);
		chart.getLegend().setFrame(BlockBorder.NONE);
		
				
		NumberAxis valueAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
		valueAxis.setTickUnit(new NumberTickUnit(5));
	}
 
	// Clase customizada, para implementar la visibilidad de los valores del grafico
	static class CustomLabelGenerator extends AbstractCategoryItemLabelGenerator implements CategoryItemLabelGenerator {
 
 
		private static final long serialVersionUID = 1L;
	
		   public CustomLabelGenerator() {
		      super("", NumberFormat.getPercentInstance());
 
		   } 

		   public String generateLabel(CategoryDataset dataset, int series,int category) {
 
		      String result = null;		      
		      Number value = dataset.getValue(series, category);	
		      NumberFormat formatoEntero = NumberFormat.getInstance(Locale.US);
		      result = formatoEntero.format(value)+ " %";
		      return result;   
		   }		   		   
	}

}