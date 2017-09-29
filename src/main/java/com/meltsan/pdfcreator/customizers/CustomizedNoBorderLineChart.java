package com.meltsan.pdfcreator.customizers;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;


 
public class CustomizedNoBorderLineChart implements DRIChartCustomizer {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		
		chart.getLegend().setFrame(BlockBorder.NONE);
		
	}
 
}