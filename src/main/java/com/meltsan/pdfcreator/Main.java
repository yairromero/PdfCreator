package com.meltsan.pdfcreator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.CostoPerCapitaTarifas;
import com.meltsan.pdfcreator.beans.CostoPromedioSiniestro;
import com.meltsan.pdfcreator.beans.InflacionSectorSalud;
import com.meltsan.pdfcreator.beans.MisionObjetivo;
import com.meltsan.pdfcreator.beans.MontosPagados;
import com.meltsan.pdfcreator.beans.PadecimientosFrecuencia;
import com.meltsan.pdfcreator.beans.IndicadoresSiniestros;
import com.meltsan.pdfcreator.beans.PoblacionHistorica;
import com.meltsan.pdfcreator.beans.SiniestralidadEsperada;
import com.meltsan.pdfcreator.beans.SiniestroMonto;
import com.meltsan.pdfcreator.beans.SiniestroPadecimiento;
import com.meltsan.pdfcreator.beans.SiniestroRangoGrafica;
import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.beans.values.CausaValues;
import com.meltsan.pdfcreator.beans.values.CostoPromedioSiniestroValues;
import com.meltsan.pdfcreator.beans.values.InflacionSSValues;
import com.meltsan.pdfcreator.beans.values.PadecimientosFrecuenciaValues;
import com.meltsan.pdfcreator.beans.values.ParentescoValues;
import com.meltsan.pdfcreator.beans.values.IndicadoresSiniestroValues;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.values.SexoValues;
import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;
import com.meltsan.pdfcreator.beans.values.SiniestroRangoTablaValues;
import com.meltsan.pdfcreator.beans.values.TipoPagoValues;

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
		
	
		IndicadoresSiniestroValues pcv1 = new IndicadoresSiniestroValues("2013-2014",12183742L,436,11147,14106,79f,51f);
		IndicadoresSiniestroValues pcv2 = new IndicadoresSiniestroValues("2014-2015",10825495L,396,9455,14256,66f,57f);
		IndicadoresSiniestroValues pcv3 = new IndicadoresSiniestroValues("2015-2016",16058004L,379,11976,15203,92f,50f);
		IndicadoresSiniestroValues pcv4 = new IndicadoresSiniestroValues("2016-2017",18400650L,404,18028,15154,99f);
		
		ArrayList<IndicadoresSiniestroValues> pcv = new ArrayList<IndicadoresSiniestroValues>();
		pcv.add(pcv1);
		pcv.add(pcv2);
		pcv.add(pcv3);
		pcv.add(pcv4);
		
		String pctxt = " Podemos ver que de acuerdo a los montos de siniestralidad y "
				+ "prima neta anual de las vigencias consideradas ha sido suficiente, "
				+ "sin embargo dado la tendencia creciente que ha mostrado la siniestralidad "
				+ "podría dejar de serlo para el cierre de la vigencia en curso.";
		
		IndicadoresSiniestros pc = new IndicadoresSiniestros(pctxt,pcv);
		
		
		
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
		
		
		
		ArrayList<SiniestroRangoGrafica> srv = new ArrayList<SiniestroRangoGrafica>();		
		srv.add(new SiniestroRangoGrafica("2013-2014",26.90,25.72,12.17,35.21));
		srv.add(new SiniestroRangoGrafica("2014-2015",31.66,30.34,23.52,14.48));
		srv.add(new SiniestroRangoGrafica("2015-2016",17.29,22.79,14.16,45.75));		
		
		
		
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
		
		
		
		ArrayList<TipoPagoValues> tp = new ArrayList<TipoPagoValues>();
		tp.add(new TipoPagoValues("2013-2014",9f,3f));
		tp.add(new TipoPagoValues("2014-2015",7f,2.7f));
		tp.add(new TipoPagoValues("2015-2016",13f,2.1f));
		
		ArrayList<CausaValues> cp = new ArrayList<CausaValues>();
		cp.add(new CausaValues("2013-2014",2f,10f,0.6f));
		cp.add(new CausaValues("2014-2015",1.5f,8f,1f));
		cp.add(new CausaValues("2015-2016",1f,13f,2f));
		
		ArrayList<SexoValues> sxp = new ArrayList<SexoValues>();
		sxp.add(new SexoValues("2013-2014",9f,3f));
		sxp.add(new SexoValues("2014-2015",7f,2.7f));
		sxp.add(new SexoValues("2015-2016",13f,2.1f));
		
		ArrayList<ParentescoValues> pp = new ArrayList<ParentescoValues>();
		pp.add(new ParentescoValues("2013-2014",9f,3f));
		pp.add(new ParentescoValues("2014-2015",7f,2.7f));
		pp.add(new ParentescoValues("2015-2016",13f,2.1f));
		
		MontosPagados mp = new MontosPagados(tp,cp,sxp,pp);
		
		
		ArrayList<CostoPerCapitaTarifas> cvt = new ArrayList<CostoPerCapitaTarifas>();
		cvt.add(new CostoPerCapitaTarifas("0-4",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("5-9",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("10-14",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("15-19",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("20-24",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("25-29",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("30-34",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("35-39",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("40-44",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("45-49",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("50-54",9f,5f,30f));
		cvt.add(new CostoPerCapitaTarifas("55-59",55f,25f,130f));
		cvt.add(new CostoPerCapitaTarifas("60-64",1f,20f,10f));
		cvt.add(new CostoPerCapitaTarifas("65-69",0f,30f,0f));
		cvt.add(new CostoPerCapitaTarifas("70+",0f,50f,0f));
		
		
		
		String mT = "Misión";
		String m = "Ser la empresa de valor y servicio líder a nivel mundial en corretaje de seguros, "
				+ "administración de riesgos y servicios actuariales.";
		String vT = "Objetivo";
		String v = "Ser el mejor lugar para trabajar y hacer negocios.";
		
		MisionObjetivo mv = new MisionObjetivo(mT,m,vT,v);
		
		
		String cpsTxt = "Con base a las medidas implementadas de contención se han logrado mantener los costos promedio de siniestro "
				+ "por debajo de los costos promedio actualizados por la inflación del sector salud a pesar de los "
				+ "siniestros catastróficos.";
				
		ArrayList<CostoPromedioSiniestroValues> cpsv = new ArrayList<CostoPromedioSiniestroValues>();
		cpsv.add(new CostoPromedioSiniestroValues("2013-2014",27944,18273,31500));
		cpsv.add(new CostoPromedioSiniestroValues("2014-2015",27337,23557,28500));
		cpsv.add(new CostoPromedioSiniestroValues("2015-2016",42369,23292,49500));
		
		CostoPromedioSiniestro cps = new CostoPromedioSiniestro(cpsTxt,cpsv); 
		
		
		generador.setReporteAntecedentes(ant);
		generador.setReporteSiniestralidadEsperada(se);
		generador.setReportePoblacionHistorica(ph);
		generador.setReporteIndicadoresSiniestralidad(pc);
		generador.setReporteInflacionSectorSalud(iss);		
		generador.setReporteCostoPromedio(cps);
		generador.setReporteSiniestroRangoGrafica(srv);
		generador.setReporteSiniestroRangoTabla(srp);
		generador.setReporteSiniestrosMayores(siniestrosMayores);
		generador.setReporteSiniestrosPadecimientos(sp);
		generador.setReportePadecimientosFrecuentes(pf);
		generador.setReporteMontosPagados(mp);
		generador.setReporteCostoVsTarifasFemenino(cvt);		
		generador.setReporteCostoVsTarifasMasculino(cvt);
		generador.setReporteMisionVision(mv);
		
		
		generador.generaReporte();
		
	}

}
