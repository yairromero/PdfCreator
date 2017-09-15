package com.meltsan.pdfcreator;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.imageio.ImageIO;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class GeneradorReporte {
	
	private StyleBuilder boldCenteredStyle;

	
	public GeneradorReporte() {
		
	}
	
	public void generarReporte() {
		StyleBuilder boldStyle         = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment
                (HorizontalTextAlignment.CENTER);        
       BufferedImage img = null;
       
       try {
    	   	ClassLoader classLoader = getClass().getClassLoader();
    	   	File file = new File(classLoader.getResource("back.png").getFile());
    	   	img = ImageIO.read(file);
       } catch (IOException e) {
       	
       }
       try {
    	   	report()//create new report design
                     // .setColumnTitleStyle(boldStyle)
                     // .setColumnStyle(boldStyle)
                      .highlightDetailEvenRows()
          .columns(//add columns
            col.column(null,"Col_1", type.stringType()),
            col.column(null,"Col_2", type.stringType())
           )
            .summary(
            		cmp.verticalList()
                 .add(cmp.text("\n\nHYSTERISIS PLOT").setStyle(boldStyle))
                 .add(cmp.image(img))
             )
              .title(cmp.text("XYZ Hospital").setStyle(boldCenteredStyle))//shows report title
              .pageFooter(cmp.pageXofY())//shows number of page at page footer
              .setDataSource(createDataSource())//set datasource
              .show();//create and show report
       } catch (DRException e) {
    	   		e.printStackTrace();
       }
	}
	
	 private JRDataSource createDataSource() {		
		       DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");		
		       for (int i = 0; i < 15; i++) {		 
		          dataSource.add("Book", new Date(), (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));		 
		       }
		 
		       for (int i = 0; i < 20; i++) {		 
		          dataSource.add("PDA", new Date(), (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));		 
		       }		 
		       return dataSource;
		    }

}
