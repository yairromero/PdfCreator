package com.meltsan.pdfcreator.util;

import java.awt.Color;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.LineStyle;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class Estilos {
	
	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder boldJustifedStyle;
	public static final StyleBuilder centeredStyle;
	public static final StyleBuilder boldWhiteStyle;	
	public static final StyleBuilder reportTitleStyle;
	public static final StyleBuilder reportSubTitleStyle;
	public static final StyleBuilder reportSubTitleCenteredStyle;
	public static final StyleBuilder reportHeadStyle;
	public static final StyleBuilder columnStyle;
	public static final StyleBuilder columnSmallStyle;
	public static final StyleBuilder columnConditionalPorcentajeStyle;
	public static final StyleBuilder columnConditionSmallStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder groupStyle;
	public static final StyleBuilder groupSmallStyle;
	public static final StyleBuilder subtotalStyle;
	public static final StyleBuilder textAreaStyle;
	public static final StyleBuilder reportBigTextAreaStyle;
	public static final StyleBuilder reportMediumTextAreaStyle;	
	public static final StyleBuilder reportSmallTextAreaStyle;
	public static final StyleBuilder reportTextAreaStyle;	
	public static final StyleBuilder misionTitleStyle;	
	public static final StyleBuilder misionStyle;
	public static final StyleBuilder misionSmallStyle;
	public static final StyleBuilder chartTitleStyle;
	public static final ReportTemplateBuilder reportTemplate;
	public static final ReportTemplateBuilder reportSmallTemplate;
	public static final ReportTemplateBuilder reportConditionalPorcentajeTemplate;
	public static final ReportTemplateBuilder reportConditionalGroupTemplate;
	public static final CurrencyType currencyType;
	public static final FontBuilder chartFontStyle;
	public static final FontBuilder chartFontSmallStyle;
	public static final FontBuilder chartFontBoldMediumStyle;
	public static final FontBuilder chartFontMediumStyle;
	public static final Color colorNavy;
	public static final Color colorBlueLight;
	public static final Color colorGreenLight;
	public static final Color colorGreenDark;
	public static final Color colorOrange;
	public static final Color colorRedDark;
	public static final Color colorRed;
	public static final Color colorCatastrofe;
	public static final Color colorSevero;
	public static final Color colorFrecuencias;
	public static final Color colorYellow;
	public static final Color colorWine;
	
	
	static {
				
			  colorBlueLight = Color.getHSBColor(0.5868056f, 0.6315789f, 0.59607846f);
			  colorNavy = Color.getHSBColor(0.6763285f, 0.6764706f, 0.4f);
			  colorGreenLight = Color.getHSBColor(0.25694445f, 0.57416266f, 0.81960785f);
			  colorGreenDark = Color.getHSBColor( 0.4138577f, 1.0f, 0.69803923f);
			  colorOrange = Color.getHSBColor(0.11713836f, 0.848f, 0.98039216f);
			  colorRed = Color.getHSBColor(0.0f, 1.0f, 0.7490196f);
			  colorRedDark = Color.getHSBColor(0.95555556f, 0.8f, 0.5882353f);
			  colorYellow = Color.getHSBColor(0.23431373f, 0.79812205f, 0.8352941f);
			  colorWine = Color.getHSBColor(0.95555556f, 0.8f, 0.5882353f);
			  
			  colorCatastrofe = Color.getHSBColor(0.0f, 0.09411765f, 1.0f);
			  colorSevero = Color.getHSBColor(0.12916666f, 0.15748031f, 0.99607843f);
			  colorFrecuencias = Color.getHSBColor(0.25490198f, 0.06827309f, 0.9764706f);
			  
			  ConditionalStyleBuilder bajaTitle = stl.conditionalStyle(new BajaExp()).setBackgroundColor(colorGreenLight);
		      ConditionalStyleBuilder altaTitle = stl.conditionalStyle(new AltaExp()).setBackgroundColor(colorGreenDark).setForegroundColor(Color.WHITE);
		      ConditionalStyleBuilder severaTitle = stl.conditionalStyle(new SeveraExp()).setBackgroundColor(colorOrange);
		      ConditionalStyleBuilder catasTitle = stl.conditionalStyle(new CatastroficaExp()).setBackgroundColor(colorRed).setForegroundColor(Color.WHITE);
		      
		      ConditionalStyleBuilder bajaCol = stl.conditionalStyle(new BajaExp()).setBackgroundColor(colorFrecuencias);
		      ConditionalStyleBuilder altaCol = stl.conditionalStyle(new AltaExp()).setBackgroundColor(colorFrecuencias);
		      ConditionalStyleBuilder severaCol = stl.conditionalStyle(new SeveraExp()).setBackgroundColor(colorSevero);
		      ConditionalStyleBuilder catasCol = stl.conditionalStyle(new CatastroficaExp()).setBackgroundColor(colorCatastrofe);
		      
		      ConditionalStyleBuilder porcentajeHL = stl.conditionalStyle(new PorcentajeHighLightExp()).setBackgroundColor(Color.lightGray);
		      		      
			  
			  chartFontStyle		= stl.fontArialBold().setFontSize(12);
			  chartFontBoldMediumStyle = stl.fontArialBold().setFontSize(9);
			  chartFontMediumStyle = stl.fontArial().setFontSize(7);
			  chartFontSmallStyle = stl.fontArialBold().setFontSize(4);
		      rootStyle           = stl.style().setPadding(2).setFontSize(12);
		      boldStyle           = stl.style(rootStyle).bold();
		      boldJustifedStyle           = stl.style(rootStyle).bold().setTextAlignment(HorizontalTextAlignment.JUSTIFIED,VerticalTextAlignment.JUSTIFIED);
		      boldCenteredStyle   = stl.style(rootStyle).bold().setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
		      centeredStyle         = stl.style(rootStyle).setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);		
		      boldWhiteStyle   = stl.style(boldStyle)		
		                               .setForegroundColor(Color.WHITE);		
		      
		      chartTitleStyle = stl.style(chartFontStyle).bold()
		    		  						.setForegroundColor(colorBlueLight);
		      
		      reportTitleStyle = stl.style(boldWhiteStyle)		    		  					
		                             .setFontSize(18);		      
		      		      
		      reportSubTitleStyle = stl.style(rootStyle)		
        				.setFontSize(18)
        				.setForegroundColor(colorBlueLight);
		      
		      reportTextAreaStyle = stl.style(boldStyle)
		    		  					.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE)
      								.setFontSize(13)
      								.setForegroundColor(colorBlueLight);
		      
		      reportBigTextAreaStyle = stl.style(boldStyle)
	  					.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE)
					.setFontSize(13)
					.setForegroundColor(colorBlueLight);
		      
		      reportMediumTextAreaStyle = stl.style(boldStyle)
	  					.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE)
					.setFontSize(10)
					.setForegroundColor(colorBlueLight);
		      
		      reportSmallTextAreaStyle = stl.style(boldStyle)
	  					.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE)
					.setFontSize(7)
					.setForegroundColor(colorBlueLight);
		                  
		      
		      reportSubTitleCenteredStyle = stl.style(boldCenteredStyle)		
                      				.setFontSize(13)
                      				.setForegroundColor(colorBlueLight);
		      
		      misionTitleStyle = stl.style(boldCenteredStyle)		
        								.setFontSize(20)
        								.setForegroundColor(colorNavy);	 
		      
		      misionStyle = stl.style(centeredStyle)		    		  				
      							.setFontSize(13)
      							.setForegroundColor(colorNavy);
		      
		      misionSmallStyle = stl.style(centeredStyle)		    		  				
						.setFontSize(8)
						.setForegroundColor(colorNavy);
		      
		      textAreaStyle = stl.style()		    		  			
		    		  			.setRadius(10)
		    		  			.setBackgroundColor(Color.WHITE)
		    		  			.setLinePen(stl.pen().setLineColor(Color.ORANGE));
		      
		      reportHeadStyle = stl.style(rootStyle)
		    		  					.setForegroundColor(Color.GRAY)
		    		  					.setFontSize(15);		     
		      
		      columnStyle         = stl.style(rootStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
		    		  					.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER) 
		    		  					.setBorder(stl.pen1Point());		      
	  									
		      columnTitleStyle    = stl.style(columnStyle)		
		                               .setBorder(stl.pen1Point())		
		                               .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)		
		                               .setBackgroundColor(colorNavy)
		                               .setForegroundColor(Color.WHITE);		                             		
		      
		      groupStyle          = stl.style(boldStyle)		    		  						
		    		  					.setFontSize(12);
		      		      
		      
		      groupSmallStyle          = stl.style(boldStyle)		    		  						
		                               .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
		                               .conditionalStyles(bajaTitle,altaTitle,severaTitle,catasTitle);	
		      
		      columnConditionalPorcentajeStyle         = stl.style().setFontSize(10)
										.setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
										.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
										.conditionalStyles(porcentajeHL)
										.setBottomBorder(stl.penThin());
		      
		      columnSmallStyle         = stl.style().setFontSize(10)
										.setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
										.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)	
										.setBorder(stl.pen(0f, LineStyle.SOLID))
										.setBottomBorder(stl.penThin());
		      
		      columnConditionSmallStyle         = stl.style().setFontSize(10)
	  										.setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
	  										.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
	  										.conditionalStyles(bajaCol,altaCol,severaCol,catasCol)
	  										.setBorder(stl.pen1Point());
		      
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
		                         .highlightDetailEvenRows();	
		      
		      reportSmallTemplate = template()		    		  				
                      				.setColumnStyle(columnSmallStyle)		
                      				.setColumnTitleStyle(columnTitleStyle);
		      
		      reportConditionalPorcentajeTemplate = template()		    		  				
        								.setColumnStyle(columnConditionalPorcentajeStyle)		
        								.setColumnTitleStyle(columnTitleStyle);
		      
		      
		      reportConditionalGroupTemplate = template()
		    		  				.setColumnStyle(columnConditionSmallStyle)		
		    		  				.setColumnTitleStyle(columnTitleStyle)
		    		  				.setGroupStyle(groupSmallStyle)				    		  				
		    		  				.setSubtotalStyle(subtotalStyle);	
		                         
		
		      currencyType = new CurrencyType();
		
		   }
		

	public static class CurrencyType extends BigDecimalType {
		
	      private static final long serialVersionUID = 1L;

	      @Override
	      public String getPattern() {
	         return "$ #,###.00";
	      }
	   }
	
	private static class BajaExp extends AbstractSimpleExpression<Boolean> {
		  
		private static final long serialVersionUID = 1L;

		public Boolean evaluate(ReportParameters reportParameters) {
		    String cat = reportParameters.getFieldValue("categoria");		    
		    if(cat.equals("FRECUENCIA BAJA ($0-$50,000)"))		    	
		    		return true;
		    else 
		    		return false;
		  }
		}
	
	private static class AltaExp extends AbstractSimpleExpression<Boolean> {
		  
		private static final long serialVersionUID = 1L;

		public Boolean evaluate(ReportParameters reportParameters) {
		    String cat = reportParameters.getFieldValue("categoria");		    
		    if(cat.equals("FRECUENCIA ALTA ($50,001-$150,000)"))		    	
		    		return true;
		    else 
		    		return false;
		  }
		}
	
	private static class SeveraExp extends AbstractSimpleExpression<Boolean> {
		  
		private static final long serialVersionUID = 1L;

		public Boolean evaluate(ReportParameters reportParameters) {
		    String cat = reportParameters.getFieldValue("categoria");		    
		    if(cat.equals("SEVERIDAD ($150,001-$400,000)"))		    
		    		return true;
		    else 
		    		return false;
		  }
		}
	
	private static class CatastroficaExp extends AbstractSimpleExpression<Boolean> {
		  
		private static final long serialVersionUID = 1L;

		public Boolean evaluate(ReportParameters reportParameters) {
		    String cat = reportParameters.getFieldValue("categoria");		   
		    if(cat.equals("CATASTRÓFICO ($400,001 o más)"))		    	
		    		return true;
		    else 
		    		return false;
		  }
		}
	
	private static class PorcentajeHighLightExp extends AbstractSimpleExpression<Boolean> {
		  
		private static final long serialVersionUID = 1L;

		public Boolean evaluate(ReportParameters reportParameters) {
		    		    
		    String celda = reportParameters.getFieldValue("vigencia");
		    
		    if( celda.contains("Variaci"))		    	
		    		return true;
		    else 
		    		return false;
		  }
		}
	
}



