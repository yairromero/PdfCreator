package com.meltsan.pdfcreator.customizers;

import java.text.NumberFormat;
import java.util.Locale;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;


 
public class CustomizedPercentageLineChart implements DRIChartCustomizer {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot(); 
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
 
		lineAndShapeRenderer.setBaseItemLabelGenerator(new CustomLabelGenerator());
 
		lineAndShapeRenderer.setBaseItemLabelsVisible(true);
		
		chart.getLegend().setFrame(BlockBorder.NONE);
	}
 
	// Clase customizada, para implementar la visibilidad de los valores del grafico
	static class CustomLabelGenerator extends AbstractCategoryItemLabelGenerator implements CategoryItemLabelGenerator {
 
		private static final long serialVersionUID = 1L;
		/**
		   * Creates a new generator that only displays labels that are greater
		   * than or equal to the threshold value.
		   *
		   * @param threshold the threshold value.
		   */
		   public CustomLabelGenerator() {
		      super("", NumberFormat.getPercentInstance());
 
		   }
		   /**
		   * Generates a label for the specified item. The label is typically a
		   * formatted version of the data value, but any text can be used.
		   *
		   * @param dataset the dataset (<code>null</code> not permitted).
		   * @param series the series index (zero-based).
		   * @param category the category index (zero-based).
		   *
		   * @return the label (possibly <code>null</code>).
		   */
		   public String generateLabel(CategoryDataset dataset, int series,int category) {
 
			   String result = null;	
			   	  NumberFormat formatoEntero = NumberFormat.getInstance(Locale.US);
			      Number value = dataset.getValue(series, category);		      
			      result = formatoEntero.format(value) + "%";
			      return result;
		   }		   		   
	}
}