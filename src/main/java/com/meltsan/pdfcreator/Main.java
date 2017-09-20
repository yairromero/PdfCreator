package com.meltsan.pdfcreator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.InflacionSS;
import com.meltsan.pdfcreator.beans.InflacionSSValues;
import com.meltsan.pdfcreator.beans.PerCapita;
import com.meltsan.pdfcreator.beans.PerCapitaValues;
import com.meltsan.pdfcreator.beans.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.PoblacionHistorica;
import com.meltsan.pdfcreator.beans.SiniestralidadEsperada;

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
		
		String itxt = "Un elemento importante que se debe considerar, es la tasa de inflación del "
				+ "sector salud publicada por el Banco de México, la cual esta afectada por un "
				+ "factor del sector asegurador mexicano que es acordado en la Asociación Mexicana "
				+ "de Instituciones de Seguros, debido a que los honorarios médicos, gastos "
				+ "hospitalarios y medicamentos no están regulados y por tanto los porcentajes que "
				+ "manejan dichos proveedores médicos están por arriba de la inflación que se "
				+ "pública.";
		
		ArrayList<InflacionSSValues> it = new ArrayList<InflacionSSValues>();
			it.add(new InflacionSSValues("2011", 11.61f));
			it.add(new InflacionSSValues("2012", 11.57f));
			it.add(new InflacionSSValues("2013", 11.80f));
			it.add(new InflacionSSValues("2014", 11.90f));
			it.add(new InflacionSSValues("2015", 11.51f));
			it.add(new InflacionSSValues("2016", 12.65f));
		
		InflacionSS iss = new InflacionSS(itxt,it);
		
		String setxt = "En base a la siniestralidad histórica mensual de las vigencias "
				+ "anteriores y considerando la siniestralidad que se lleva hasta el momento se "
				+ "espera el siguiente comportamiento (mostrado gráficamente), con un monto al "
				+ "final de la vigencia de $18,400,650.";	
		
		SiniestralidadEsperada se = new SiniestralidadEsperada(setxt);
		
		String pht = "Durante las vigencias en consideración, la población incrementó un 5.03%, por su parte la prima per cápita ha "
				+ "incrementado un  ́14.5%";
		
		ArrayList<PobHistoricaValues> phv = new ArrayList<PobHistoricaValues>();
		phv.add(new PobHistoricaValues("2013-2014",1093,15417673L,14106L));
		phv.add(new PobHistoricaValues("2014-2015",1145,16322655L,14256L));
		phv.add(new PobHistoricaValues("2015-2016",1149,17467912L,15203L));
		phv.add(new PobHistoricaValues("2016-2017",1148,18544881L,16154L));
				
		PoblacionHistorica ph = new PoblacionHistorica(pht,phv);
		
		GeneradorReporte generador = new GeneradorReporte(ant,pc,iss,se,ph);
		
	}

}
