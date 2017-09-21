package com.meltsan.pdfcreator.util;

import java.awt.Color;

import org.apache.avalon.framework.logger.ConsoleLogger;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

public class Estilos {
	
	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder italicStyle;
	public static final StyleBuilder boldWhiteStyle;	
	public static final StyleBuilder reportTitleStyle;
	public static final StyleBuilder reportSubTitleStyle;
	public static final StyleBuilder reportSubTitleCenteredStyle;
	public static final StyleBuilder reportHeadStyle;
	public static final StyleBuilder columnStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder groupStyle;
	public static final StyleBuilder subtotalStyle;
	public static final StyleBuilder textAreaStyle;
	public static final ReportTemplateBuilder reportTemplate;
	public static final CurrencyType currencyType;
	public static final FontBuilder chartFontStyle;
	public static final Color colorNavy;
	public static final Color colorBlueLight;
	public static final Color colorGreenLight;
	public static final Color colorGrenDark;
	public static final Color colorOrange;
	public static final Color colorRedDark;
	
	
	static {
			  colorBlueLight = Color.getHSBColor(0.5868056f, 0.6315789f, 0.59607846f);
			  colorNavy = Color.getHSBColor(0.6763285f, 0.6764706f, 0.4f);
			  colorGreenLight = Color.getHSBColor(0.25694445f, 0.57416266f, 0.81960785f);
			  colorGrenDark = Color.getHSBColor( 0.4138577f, 1.0f, 0.69803923f);
			  colorOrange = Color.getHSBColor(0.11713836f, 0.848f, 0.98039216f);
			  colorRedDark = Color.getHSBColor(0.0f, 1.0f, 0.7490196f);
			  chartFontStyle		= stl.fontArialBold().setFontSize(12);
		      rootStyle           = stl.style().setPadding(2).setFontSize(12);
		      boldStyle           = stl.style(rootStyle).bold();		
		      boldCenteredStyle   = stl.style(rootStyle).bold().setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
		      italicStyle         = stl.style(rootStyle).italic();		
		      boldWhiteStyle   = stl.style(boldStyle)		
		                               .setForegroundColor(Color.WHITE);				
		      reportTitleStyle = stl.style(boldWhiteStyle)		
		                             .setFontSize(25);
		      		      
		      reportSubTitleStyle = stl.style(boldStyle)		
        				.setFontSize(18)
        				.setForegroundColor(Color.getHSBColor(0.5868056f, 0.6315789f, 0.59607846f));
		      
		      reportSubTitleCenteredStyle = stl.style(boldCenteredStyle)		
                      				.setFontSize(13)
                      				.setForegroundColor(Color.getHSBColor(0.5868056f, 0.6315789f, 0.59607846f));	 
		      textAreaStyle = stl.style()
		    		  			.setRadius(10)
		    		  			.setBackgroundColor(Color.WHITE)
		    		  			.setLinePen(stl.pen().setLineColor(Color.ORANGE));
		      reportHeadStyle = stl.style(boldStyle)
		    		  					.setForegroundColor(Color.GRAY)
		    		  					.setFontSize(15);
		      columnStyle         = stl.style(rootStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
		    		  					.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER) 
		    		  					.setBorder(stl.pen1Point());		
		      columnTitleStyle    = stl.style(columnStyle)		
		                               .setBorder(stl.pen1Point())		
		                               .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)		
		                               .setBackgroundColor(Color.getHSBColor(0.6763285f, 0.6764706f, 0.4f))
		                               .setForegroundColor(Color.WHITE)
		                               .bold();		
		      groupStyle          = stl.style(boldStyle)		
		                               .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);		
		      subtotalStyle       = stl.style(boldStyle)		
		                               .setTopBorder(stl.pen1Point());
		
		 
		      StyleBuilder crosstabGroupStyle      = stl.style(columnTitleStyle);
		      StyleBuilder crosstabGroupTotalStyle = stl.style(columnTitleStyle)		
		                                                .setBackgroundColor(new Color(170, 170, 170));
		      StyleBuilder crosstabGrandTotalStyle = stl.style(columnTitleStyle)
		                                                .setBackgroundColor(new Color(140, 140, 140));
		      StyleBuilder crosstabCellStyle       = stl.style(columnStyle)
		                                                .setBorder(stl.pen1Point());
				
		      reportTemplate = template()	
		                         .setColumnStyle(columnStyle)		
		                         .setColumnTitleStyle(columnTitleStyle)		
		                         .setGroupStyle(groupStyle)		
		                         .setGroupTitleStyle(groupStyle)
		                         .setSubtotalStyle(subtotalStyle)		
		                         .highlightDetailEvenRows()		
		                         .crosstabHighlightEvenRows()		
		                         .setCrosstabGroupStyle(crosstabGroupStyle)		
		                         .setCrosstabGroupTotalStyle(crosstabGroupTotalStyle)		
		                         .setCrosstabGrandTotalStyle(crosstabGrandTotalStyle)		
		                         .setCrosstabCellStyle(crosstabCellStyle);
		
		      currencyType = new CurrencyType();
		
		   }
		

	public static class CurrencyType extends BigDecimalType {
		
	      private static final long serialVersionUID = 1L;

	      @Override
	      public String getPattern() {
	         return "$ #,###.00";
	      }
	   }
}



