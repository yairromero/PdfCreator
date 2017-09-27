package com.meltsan.pdfcreator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.InflacionSectorSalud;
import com.meltsan.pdfcreator.beans.PadecimientosFrecuencia;
import com.meltsan.pdfcreator.beans.IndicadoresSiniestros;
import com.meltsan.pdfcreator.beans.PoblacionHistorica;
import com.meltsan.pdfcreator.beans.SiniestralidadEsperada;
import com.meltsan.pdfcreator.beans.SiniestroMonto;
import com.meltsan.pdfcreator.beans.SiniestroPadecimiento;
import com.meltsan.pdfcreator.beans.SiniestroRangoGrafica;
import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.beans.values.InflacionSSValues;
import com.meltsan.pdfcreator.beans.values.PadecimientosFrecuenciaValues;
import com.meltsan.pdfcreator.beans.values.PerCapitaValues;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;
import com.meltsan.pdfcreator.beans.values.SiniestroRangoTablaValues;

public class Main {
	
	public static void main(String[] args) {
		
		GeneradorReporte generador = new GeneradorReporte("/Users/Meltsan/Desktop/report.pdf");
		
		String l1 = "1 de Julio del 2016 al 1 de Julio del 2017";
		String l2 = "1 de Julio del 2013 al 30 de Junio del 2016";
		HashMap<String, String> t1 = new HashMap<String, String>();
		t1.put("2013-2014", "1 de Julio del 2013 al 1 de Julio del 2014");
		t1.put("2014-2015", "1 de Julio del 2014 al 1 de Julio del 2015");
		t1.put("2015-2016", "1 de Julio del 2015 al 1 de Julio del 2016");
		
		Antecedentes ant = new Antecedentes(l1,l2,t1);
		
		generador.setReporteAntecedentes(ant);
		
		PerCapitaValues pcv1 = new PerCapitaValues("2013-2014",new BigDecimal(11147),new BigDecimal(14106));
		PerCapitaValues pcv2 = new PerCapitaValues("2014-2015",new BigDecimal(9455),new BigDecimal(14256));
		PerCapitaValues pcv3 = new PerCapitaValues("2015-2016",new BigDecimal(11976),new BigDecimal(15203));
		PerCapitaValues pcv4 = new PerCapitaValues("2016-2017",new BigDecimal(18028),new BigDecimal(15154));
		
		ArrayList<PerCapitaValues> pcv = new ArrayList<PerCapitaValues>();
		pcv.add(pcv1);
		pcv.add(pcv2);
		pcv.add(pcv3);
		pcv.add(pcv4);
		
		String pctxt = " Podemos ver que de acuerdo a los montos de siniestralidad y "
				+ "prima neta anual de las vigencias consideradas ha sido suficiente, "
				+ "sin embargo dado la tendencia creciente que ha mostrado la siniestralidad "
				+ "podría dejar de serlo para el cierre de la vigencia en curso.";
		
		IndicadoresSiniestros pc = new IndicadoresSiniestros(pctxt,pcv);
		
		generador.setReporteIndicadoresSiniestralidad(pc);
		
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
		
		InflacionSectorSalud iss = new InflacionSectorSalud(itxt,it);
		
		generador.setReporteInflacionSectorSalud(iss);
		
		String setxt = "En base a la siniestralidad histórica mensual de las vigencias "
				+ "anteriores y considerando la siniestralidad que se lleva hasta el momento se "
				+ "espera el siguiente comportamiento (mostrado gráficamente), con un monto al "
				+ "final de la vigencia de $18,400,650.";	
		
		SiniestralidadEsperada se = new SiniestralidadEsperada(setxt);
		
		generador.setReporteSiniestralidadEsperada(se);
		
		String pht = "Durante las vigencias en consideración, la población incrementó un 5.03%, por su parte la prima per cápita ha "
				+ "incrementado un  ́14.5%";
		
		ArrayList<PobHistoricaValues> phv = new ArrayList<PobHistoricaValues>();
		phv.add(new PobHistoricaValues("2013-2014",1093,new Double(0.0),0.0,15417673L,0.0,0.0,14106L,0.0,0.0));
		phv.add(new PobHistoricaValues("2014-2015",1145,4.8,0.0,16322655L,5.9,0.0,14256L,1.9,5.03));
		phv.add(new PobHistoricaValues("2015-2016",1149,0.4,0.0,17467912L,7.0,0.0,15203L,6.6,0.0));
		phv.add(new PobHistoricaValues("2016-2017",1148,-0.9,5.03,18544881L,6.1,20.2,16154L,6.2,14.5));
				
		PoblacionHistorica ph = new PoblacionHistorica(pht,phv);
		
		generador.setReportePoblacionHistorica(ph);
		
		ArrayList<SiniestroRangoGrafica> srv = new ArrayList<SiniestroRangoGrafica>();		
		srv.add(new SiniestroRangoGrafica("2013-2014",26.90,25.72,12.17,35.21));
		srv.add(new SiniestroRangoGrafica("2014-2015",31.66,30.34,23.52,14.48));
		srv.add(new SiniestroRangoGrafica("2015-2016",17.29,22.79,14.16,45.75));		
		
		generador.setReporteSiniestroRangoGrafica(srv);
		
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
		
		generador.setReporteSiniestrosPadecimientos(sp);
				
		SiniestroRangoTablaValues  baja1 = new SiniestroRangoTablaValues(3277431L,2999L,8491L,386,26.9);
		SiniestroRangoTablaValues  alta1 = new SiniestroRangoTablaValues(3277431L,2999L,8491L,386,26.9);
		SiniestroRangoTablaValues  frec1 = new SiniestroRangoTablaValues(3277431L,2999L,8491L,386,26.9);
		SiniestroRangoTablaValues  catas1 = new SiniestroRangoTablaValues(3277431L,2999L,8491L,386,26.9);
		
		SiniestroRangoTablaValues  baja2 = new SiniestroRangoTablaValues(3427031L,2993L,10021L,342,31.7);
		SiniestroRangoTablaValues  alta2 = new SiniestroRangoTablaValues(3277431L,2999L,8491L,386,26.9);
		SiniestroRangoTablaValues  frec2 = new SiniestroRangoTablaValues(3277431L,2999L,8491L,386,26.9);
		SiniestroRangoTablaValues  catas2 = new SiniestroRangoTablaValues(3277431L,2999L,8491L,386,26.9);
		
		ArrayList<SiniestroRangoPeriodo> srp = new ArrayList<SiniestroRangoPeriodo>();
		srp.add(new SiniestroRangoPeriodo("2013-2014",baja1,alta1,frec1,catas1));
		srp.add(new SiniestroRangoPeriodo("2014-2015",baja2,alta2,frec2,catas2));	
		
		generador.setReporteSiniestroRangoTabla(srp);
		
		SiniestroPadecimientoValues p1 = new SiniestroPadecimientoValues(1150250772L,"Otros recién nacidos pre término");
		SiniestroPadecimientoValues p2 = new SiniestroPadecimientoValues(1140209240L,"Otros recién nacidos pre término");
		SiniestroPadecimientoValues p3 = new SiniestroPadecimientoValues(1150251720L,"Esclerosis múltiple SAI");
		SiniestroPadecimientoValues p4 = new SiniestroPadecimientoValues(1140257979L,"Linfangitis");
		SiniestroPadecimientoValues p5 = new SiniestroPadecimientoValues(1140242996L,"Hidronefrosis con obstrucción por cálculos del riñón y del uréter");
		SiniestroPadecimientoValues p6 = new SiniestroPadecimientoValues(1100244731L,"Gastroduodentis, no especificada");
		
		SiniestroMonto s1 = new SiniestroMonto(p1,1997835L);
		SiniestroMonto s2 = new SiniestroMonto(p2,1895998L);
		SiniestroMonto s4 = new SiniestroMonto(p3,1894832L);
		SiniestroMonto s5 = new SiniestroMonto(p4,132869L);
		SiniestroMonto s6 = new SiniestroMonto(p4,339741L);
		SiniestroMonto s7 = new SiniestroMonto(p5,78218L);
		SiniestroMonto s8 = new SiniestroMonto(p5,1216460L);
		SiniestroMonto s9 = new SiniestroMonto(p6,51293L);
		SiniestroMonto s10 = new SiniestroMonto(p6,38210L);
		SiniestroMonto s11 = new SiniestroMonto(p6,424248L);	
		
		ArrayList<SiniestroMonto> p2014 = new ArrayList<SiniestroMonto>();
		p2014.add(s2);
		p2014.add(s9);
		
		
		ArrayList<SiniestroMonto> p2015 = new ArrayList<SiniestroMonto>();		
		p2015.add(s5);
		p2015.add(s7);
		p2015.add(s10);
		
		ArrayList<SiniestroMonto> p2016 = new ArrayList<SiniestroMonto>();
		p2016.add(s1);
		p2016.add(s4);
		p2016.add(s6);
		p2016.add(s8);
		p2016.add(s11);
				
		ArrayList<SiniestrosMayores> siniestrosMayores = new ArrayList<SiniestrosMayores>();
		siniestrosMayores.add(new SiniestrosMayores("2013-2014",p2014));
		siniestrosMayores.add(new SiniestrosMayores("2014-2015",p2015));
		siniestrosMayores.add(new SiniestrosMayores("2015-2016",p2016));
		
		generador.setReporteSiniestrosMayores(siniestrosMayores);
		
		ArrayList<PadecimientosFrecuenciaValues> padecimientosFrecuencia = new ArrayList<PadecimientosFrecuenciaValues>();
		padecimientosFrecuencia.add(new PadecimientosFrecuenciaValues("Presencia de anteojos y lentes de contacto",18.25f));
		padecimientosFrecuencia.add(new PadecimientosFrecuenciaValues("Miopía y astigmatismo", 17.59f));
		padecimientosFrecuencia.add(new PadecimientosFrecuenciaValues("Recién nacido sano",3.80f));
		padecimientosFrecuencia.add(new PadecimientosFrecuenciaValues("Cesárea",3.39f));
		padecimientosFrecuencia.add(new PadecimientosFrecuenciaValues("Embarazo Abdominal",2.23f));
		
		String textoTop ="Es importante destacar que el 45.25% de los siniestros no se deben a "
				+ "una enfermedad como tal, mas bien son coberturas de apoyo que la empresa ha "
				+ "contratado para sus empleados. Siendo la mas representativa la presencia de "
				+ "anteojos y lentes de contacto.";
		
		PadecimientosFrecuencia pf = new PadecimientosFrecuencia(textoTop,padecimientosFrecuencia);
		
		generador.setReportePadecimientosFrecuentes(pf);
		
		generador.generaReporte();
		
	}

}
