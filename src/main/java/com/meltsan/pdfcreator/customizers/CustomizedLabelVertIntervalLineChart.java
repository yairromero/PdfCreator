package com.meltsan.pdfcreator.customizers;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;


 
public class CustomizedLabelVertIntervalLineChart implements DRIChartCustomizer {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) chart.getCategoryPlot().getRenderer();
        Shape defaultShape = new Rectangle2D.Double(0.0, 0.0, 0.0, 0.0);
       renderer.setSeriesShape(0, defaultShape);
        renderer.setSeriesShape(1, defaultShape);
        renderer.setSeriesShape(2, defaultShape);
        
		chart.getLegend().setFrame(BlockBorder.NONE);
		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();  
	     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/2));
	     
	     NumberAxis valueAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
			valueAxis.setTickUnit(new NumberTickUnit(.5));
	   }  
	
 	   		  
}