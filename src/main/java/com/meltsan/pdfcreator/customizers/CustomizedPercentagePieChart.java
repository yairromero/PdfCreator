package com.meltsan.pdfcreator.customizers;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;


 
public class CustomizedPercentagePieChart implements DRIChartCustomizer {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		
		PiePlot plot = (PiePlot)chart.getPlot();
        plot.setLabelOutlinePaint(null); 
        plot.setLabelShadowPaint(null);
        chart.getLegend().setFrame(BlockBorder.NONE);
                        
	}
   		   
}