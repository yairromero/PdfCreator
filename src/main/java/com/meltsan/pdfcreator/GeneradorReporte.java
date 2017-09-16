package com.meltsan.pdfcreator;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.imageio.ImageIO;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class GeneradorReporte {

	private BufferedImage img = null;
	ImageBuilder imgHeader = null;
	ImageBuilder imgFooter = null;
	File file = null;
	
	public void generarReporte() {     
       
      try {
    	   	ClassLoader classLoader = getClass().getClassLoader();
    	   	file = new File(classLoader.getResource("head.png").getFile());
    	   	img = ImageIO.read(file);    	
    	   	imgHeader = cmp.image(img).setImageScale(ImageScale.FILL_FRAME);
    	   	file = new File(classLoader.getResource("foot.png").getFile());
    	   	img = ImageIO.read(file);    	
    	   	imgFooter = cmp.image(img);
       } catch (IOException e) {
       	
       }
       try {

    	   //imgBackgroud.setPrintWhenExpression(exp.printInFirstPage());  
    	   FontBuilder defaultFont = stl.font().setFontName("Arial");  
    	   defaultFont.setFontSize(30);
    	  
    	   StyleBuilder boldStyle = stl.style().bold().setForegroundColor(Color.WHITE);
    			       			         

    	   	report()
    	   		.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
    	   		.setTitleBackgroundComponent(imgHeader)
    	   		.setDefaultFont(defaultFont)
    	   		.title(cmp.text("Antecedentes").setStyle(boldStyle))    	   		    	   		
    	   		.addPageFooter(imgFooter)
            //.toPdf(new FileOutputStream("/Users/Meltsan/Desktop/report.pdf"));
    	   		.show();
       } catch (DRException e) {
    	   		e.printStackTrace();
       }

	}
	
	 /*private JRDataSource createDataSource() {		
		       DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");		
		       for (int i = 0; i < 15; i++) {		 
		          dataSource.add("Book", new Date(), (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));		 
		       }
		 
		       for (int i = 0; i < 20; i++) {		 
		          dataSource.add("PDA", new Date(), (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));		 
		       }		 
		       return dataSource;
		    }*/

}
