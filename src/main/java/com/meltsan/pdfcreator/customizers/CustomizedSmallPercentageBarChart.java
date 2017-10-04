package com.meltsan.pdfcreator.customizers;

import java.text.NumberFormat;
import java.util.Locale;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;

import com.meltsan.pdfcreator.util.Estilos;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;


 
public class CustomizedSmallPercentageBarChart implements DRIChartCustomizer {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot(); 
		BarRenderer barRenderer = (BarRenderer) categoryPlot.getRenderer(); 
		barRenderer.setBaseItemLabelGenerator(new CustomLabelGenerator()); 
		barRenderer.setBaseItemLabelsVisible(true);
		barRenderer.setItemMargin(0);
		chart.getLegend().setFrame(BlockBorder.NONE);
				
		NumberAxis valueAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
		valueAxis.setTickUnit(new NumberTickUnit(.5));
		
		CategoryAxis domainaxis = categoryPlot.getDomainAxis();		
        domainaxis.setTickMarksVisible(true);
        domainaxis.setMaximumCategoryLabelLines(5);
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
		      Number value = dataset.getValue(series, category);	
		      NumberFormat formatoEntero = NumberFormat.getInstance(Locale.US);
		      result = formatoEntero.format(value)+ " %";
		      return result;   
		   }		   		   
	}
}