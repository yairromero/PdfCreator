package com.meltsan.pdfcreator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.PerCapita;
import com.meltsan.pdfcreator.beans.PerCapitaValues;

public class Main {
	
	public static void main(String[] args) {
		
		String l1 = "1 de Julio del 2016 al 1 de Julio del 2017";
		String l2 = "1 de Julio del 2013 al 30 de Junio del 2016";
		HashMap<String, String> t1 = new HashMap<String, String>();
		t1.put("2013-2014", "1 de Julio del 2013 al 1 de Julio del 2014");
		t1.put("2014-2015", "1 de Julio del 2014 al 1 de Julio del 2015");
		t1.put("2015-2016", "1 de Julio del 2015 al 1 de Julio del 2016");
		
		Antecedentes ant = new Antecedentes(l1,l2,t1);
		
		PerCapitaValues p1 = new PerCapitaValues("2013-2014",new BigDecimal(11147),new BigDecimal(14106));
		PerCapitaValues p2 = new PerCapitaValues("2014-2015",new BigDecimal(9455),new BigDecimal(14256));
		PerCapitaValues p3 = new PerCapitaValues("2015-2016",new BigDecimal(11976),new BigDecimal(15203));
		PerCapitaValues p4 = new PerCapitaValues("2016-2017",new BigDecimal(18028),new BigDecimal(15154));
		
		ArrayList<PerCapitaValues> pcv = new ArrayList<PerCapitaValues>();
		pcv.add(p1);
		pcv.add(p2);
		pcv.add(p3);
		pcv.add(p4);
		
		String pctxt = " Podemos ver que de acuerdo a los montos de siniestralidad y "
				+ "prima neta anual de las vigencias consideradas ha sido suficiente, "
				+ "sin embargo dado la tendencia creciente que ha mostrado la siniestralidad "
				+ "podría dejar de serlo para el cierre de la vigencia en curso.";
		
		PerCapita pc = new PerCapita(pctxt,pcv);
		
		GeneradorReporte generador = new GeneradorReporte(ant,pc);
		
	}

}
