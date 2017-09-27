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
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

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
	public static final StyleBuilder columnSmallStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder groupStyle;
	public static final StyleBuilder groupSmallStyle;
	public static final StyleBuilder subtotalStyle;
	public static final StyleBuilder textAreaStyle;
	public static final StyleBuilder reportTextAreaStyle;	
	public static final ReportTemplateBuilder reportTemplate;
	public static final ReportTemplateBuilder reportSmallTemplate;
	public static final CurrencyType currencyType;
	public static final FontBuilder chartFontStyle;
	public static final Color colorNavy;
	public static final Color colorBlueLight;
	public static final Color colorGreenLight;
	public static final Color colorGreenDark;
	public static final Color colorOrange;
	public static final Color colorRedDark;
	public static final Color colorCatastrofe;
	public static final Color colorSevero;
	public static final Color colorFrecuencias;
	
	
	static {
		
			
		
			  colorBlueLight = Color.getHSBColor(0.5868056f, 0.6315789f, 0.59607846f);
			  colorNavy = Color.getHSBColor(0.6763285f, 0.6764706f, 0.4f);
			  colorGreenLight = Color.getHSBColor(0.25694445f, 0.57416266f, 0.81960785f);
			  colorGreenDark = Color.getHSBColor( 0.4138577f, 1.0f, 0.69803923f);
			  colorOrange = Color.getHSBColor(0.11713836f, 0.848f, 0.98039216f);
			  colorRedDark = Color.getHSBColor(0.0f, 1.0f, 0.7490196f);
			  
			  colorCatastrofe = Color.getHSBColor(0.0f, 0.09411765f, 1.0f);
			  colorSevero = Color.getHSBColor(0.12916666f, 0.15748031f, 0.99607843f);
			  colorFrecuencias = Color.getHSBColor(0.25490198f, 0.06827309f, 0.9764706f);
			  
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
		      
		      reportTextAreaStyle = stl.style(boldStyle)
		    		  					.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE)
      								.setFontSize(15)
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
                      .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);                      
		      
		      ConditionalStyleBuilder bajaTitle = stl.conditionalStyle(new BajaExp()).setBackgroundColor(colorGreenLight);
		      ConditionalStyleBuilder altaTitle = stl.conditionalStyle(new AltaExp()).setBackgroundColor(colorGreenDark).setForegroundColor(Color.WHITE);
		      ConditionalStyleBuilder severaTitle = stl.conditionalStyle(new SeveraExp()).setBackgroundColor(colorOrange);
		      ConditionalStyleBuilder catasTitle = stl.conditionalStyle(new CatastroficaExp()).setBackgroundColor(colorRedDark).setForegroundColor(Color.WHITE);
		      
		      ConditionalStyleBuilder bajaCol = stl.conditionalStyle(new BajaExp()).setBackgroundColor(colorFrecuencias);
		      ConditionalStyleBuilder altaCol = stl.conditionalStyle(new AltaExp()).setBackgroundColor(colorFrecuencias);
		      ConditionalStyleBuilder severaCol = stl.conditionalStyle(new SeveraExp()).setBackgroundColor(colorSevero);
		      ConditionalStyleBuilder catasCol = stl.conditionalStyle(new CatastroficaExp()).setBackgroundColor(colorCatastrofe);
		      
		      groupSmallStyle          = stl.style(boldStyle)		    		  						
		                               .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
		                               .conditionalStyles(bajaTitle,altaTitle,severaTitle,catasTitle);	
		      
		      columnSmallStyle         = stl.style().setFontSize(10)
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
	

}



