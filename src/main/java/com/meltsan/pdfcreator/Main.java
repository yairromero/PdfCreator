package com.meltsan.pdfcreator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.InflacionSS;
import com.meltsan.pdfcreator.beans.PerCapita;
import com.meltsan.pdfcreator.beans.PoblacionHistorica;
import com.meltsan.pdfcreator.beans.SiniestralidadEsperada;
import com.meltsan.pdfcreator.beans.SiniestroPadecimiento;
import com.meltsan.pdfcreator.beans.SiniestroRangoGrafica;
import com.meltsan.pdfcreator.beans.SiniestroRangoTabla;
import com.meltsan.pdfcreator.beans.values.InflacionSSValues;
import com.meltsan.pdfcreator.beans.values.PerCapitaValues;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;
import com.meltsan.pdfcreator.beans.values.SiniestroRangoValuesGrafica;
import com.meltsan.pdfcreator.beans.values.SiniestroRangoValuesTabla;

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
		phv.add(new PobHistoricaValues("2013-2014",1093,new Double(0.0),0.0,15417673L,0.0,0.0,14106L,0.0,0.0));
		phv.add(new PobHistoricaValues("2014-2015",1145,4.8,0.0,16322655L,5.9,0.0,14256L,1.9,5.03));
		phv.add(new PobHistoricaValues("2015-2016",1149,0.4,0.0,17467912L,7.0,0.0,15203L,6.6,0.0));
		phv.add(new PobHistoricaValues("2016-2017",1148,-0.9,5.03,18544881L,6.1,20.2,16154L,6.2,14.5));
				
		PoblacionHistorica ph = new PoblacionHistorica(pht,phv);
		
		ArrayList<SiniestroRangoValuesGrafica> srv = new ArrayList<SiniestroRangoValuesGrafica>();		
		srv.add(new SiniestroRangoValuesGrafica("2013-2014",26.90,25.72,12.17,35.21));
		srv.add(new SiniestroRangoValuesGrafica("2014-2015",31.66,30.34,23.52,14.48));
		srv.add(new SiniestroRangoValuesGrafica("2015-2016",17.29,22.79,14.16,45.75));		
		SiniestroRangoGrafica sr = new SiniestroRangoGrafica(srv);
		
		ArrayList<SiniestroPadecimientoValues> spv = new ArrayList<SiniestroPadecimientoValues>();
		spv.add(new SiniestroPadecimientoValues(1140257979L,"Linfangitis"));
		spv.add(new SiniestroPadecimientoValues(1140242996L,"Hidronefrosis con obstrucción por cálculos del riñón y del uréter"));
		spv.add(new SiniestroPadecimientoValues(3120202584L,"Linfangitis"));
		spv.add(new SiniestroPadecimientoValues(1100244731L,"Gastroduodentis, no especificada"));
		spv.add(new SiniestroPadecimientoValues(1130260716L,"Hemorragia subaracnoidea, no especificada"));
		
		String sptxt = "4. Debido a que los siniestros que a continuación se enlistan, "
				+ "han presentado reclamaciones de manera consecutiva en las últimas vigencias, "
				+ "buscaremos que la aseguradora les de seguimiento, ya que estos 5 siniestros "
				+ "representan el 11.9% del monto pagado acumulado del periodo de análisis";
		
		SiniestroPadecimiento sp = new SiniestroPadecimiento(sptxt,spv);
		
		ArrayList<SiniestroRangoValuesTabla> bajas = new ArrayList<SiniestroRangoValuesTabla>();
		ArrayList<SiniestroRangoValuesTabla> altas = new ArrayList<SiniestroRangoValuesTabla>();
		ArrayList<SiniestroRangoValuesTabla> serveridades = new ArrayList<SiniestroRangoValuesTabla>();
		ArrayList<SiniestroRangoValuesTabla> catastroficos = new ArrayList<SiniestroRangoValuesTabla>();
		
		SiniestroRangoTabla srt = new SiniestroRangoTabla(bajas,altas,serveridades,catastroficos);
		
		
		GeneradorReporte generador = new GeneradorReporte(ant,pc,iss,se,ph,sr,sp,srt);
		
	}

}
