package com.meltsan.pdfcreator.customizers;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;


 
public class CustomizedLabelVertBarChart implements DRIChartCustomizer {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		 
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot(); 
		BarRenderer barRenderer = (BarRenderer) categoryPlot.getRenderer();
		barRenderer.setItemMargin(0);
		chart.getLegend().setFrame(BlockBorder.NONE);
		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();  
	    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/2));	

	}

}