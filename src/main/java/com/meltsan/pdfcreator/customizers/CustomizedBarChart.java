package com.meltsan.pdfcreator.customizers;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;


 
public class CustomizedBarChart implements DRIChartCustomizer {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		 
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot(); 
		BarRenderer barRenderer = (BarRenderer) categoryPlot.getRenderer();
		barRenderer.setItemMargin(0);
		chart.getLegend().setFrame(BlockBorder.NONE);
		//NumberAxis valueAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
		//valueAxis.setTickUnit(new NumberTickUnit(2));		

	}

}