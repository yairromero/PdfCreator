package com.meltsan.pdfcreator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.PerCapita;

public class Main {
	
	public static void main(String[] args) {
		
		String l1 = "1 de Julio del 2016 al 1 de Julio del 2017";
		String l2 = "1 de Julio del 2013 al 30 de Junio del 2016";
		HashMap<String, String> t1 = new HashMap<String, String>();
		t1.put("2013-2014", "1 de Julio del 2013 al 1 de Julio del 2014");
		t1.put("2014-2015", "1 de Julio del 2014 al 1 de Julio del 2015");
		t1.put("2015-2016", "1 de Julio del 2015 al 1 de Julio del 2016");
		
		Antecedentes ant = new Antecedentes(l1,l2,t1);
		
		PerCapita p1 = new PerCapita("2013-2014",new BigDecimal(11147),new BigDecimal(14106));
		PerCapita p2 = new PerCapita("2014-2015",new BigDecimal(9455),new BigDecimal(14256));
		PerCapita p3 = new PerCapita("2015-2016",new BigDecimal(13976),new BigDecimal(15203));
		PerCapita p4 = new PerCapita("2016-2017",new BigDecimal(16028),new BigDecimal(16154));
		
		ArrayList<PerCapita> pc = new ArrayList<PerCapita>();
		pc.add(p1);
		pc.add(p2);
		pc.add(p3);
		pc.add(p4);
		
		GeneradorReporte generador = new GeneradorReporte(ant,pc);
		
	}

}
