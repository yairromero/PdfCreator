package com.meltsan.pdfcreator;

import java.util.ArrayList;
import java.util.HashMap;

import com.meltsan.pdfcreator.beans.Antecedentes;
import com.meltsan.pdfcreator.beans.ComparativoHospital;
import com.meltsan.pdfcreator.beans.CostoPerCapitaTarifas;
import com.meltsan.pdfcreator.beans.CostoPromedioSiniestro;
import com.meltsan.pdfcreator.beans.DistribucionGastos;
import com.meltsan.pdfcreator.beans.HospitalPorcentaje;
import com.meltsan.pdfcreator.beans.InflacionSectorSalud;
import com.meltsan.pdfcreator.beans.MisionObjetivo;
import com.meltsan.pdfcreator.beans.MontosPagados;
import com.meltsan.pdfcreator.beans.PadCronicoClienteMercado;
import com.meltsan.pdfcreator.beans.PadCronicosMontos;
import com.meltsan.pdfcreator.beans.ConceptoMonto;
import com.meltsan.pdfcreator.beans.PadecimientoCronicos;
import com.meltsan.pdfcreator.beans.PadecimientosFrecuencia;
import com.meltsan.pdfcreator.beans.ParticipacionAsegurado;
import com.meltsan.pdfcreator.beans.IndicadoresSiniestros;
import com.meltsan.pdfcreator.beans.PoblacionHistorica;
import com.meltsan.pdfcreator.beans.SiniestralidadEsperada;
import com.meltsan.pdfcreator.beans.SiniestroPadecimiento;
import com.meltsan.pdfcreator.beans.SiniestroRangoGrafica;
import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.beans.TopHospitales;
import com.meltsan.pdfcreator.beans.TopHospitalesGrafica;
import com.meltsan.pdfcreator.beans.TopPadecimientosCronicos;
import com.meltsan.pdfcreator.beans.values.CausaValues;
import com.meltsan.pdfcreator.beans.values.ComparativoHospitalValues;
import com.meltsan.pdfcreator.beans.values.CostoPromedioSiniestroValues;
import com.meltsan.pdfcreator.beans.values.DistribucionGastosValues;
import com.meltsan.pdfcreator.beans.values.InflacionSSValues;
import com.meltsan.pdfcreator.beans.values.PadecimientoCronicoValues;
import com.meltsan.pdfcreator.beans.values.PadecimientosFrecuenciaValues;
import com.meltsan.pdfcreator.beans.values.ParentescoValues;
import com.meltsan.pdfcreator.beans.values.IndicadoresSiniestroValues;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.values.SexoValues;
import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;
import com.meltsan.pdfcreator.beans.values.SiniestroRangoTablaValues;
import com.meltsan.pdfcreator.beans.values.TipoPagoValues;
import com.meltsan.pdfcreator.beans.values.TopHospitalesValues;

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
		
	
		IndicadoresSiniestroValues pcv1 = new IndicadoresSiniestroValues("2013-2014",12183742,436,11147,14106,79f,51f);
		IndicadoresSiniestroValues pcv2 = new IndicadoresSiniestroValues("2014-2015",10825495,396,9455,14256,66f,57f);
		IndicadoresSiniestroValues pcv3 = new IndicadoresSiniestroValues("2015-2016",16058004,379,11976,15203,92f,50f);
		IndicadoresSiniestroValues pcv4 = new IndicadoresSiniestroValues("2016-2017",18400650,404,18028,15154,99f);
		
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
		
		
				
		ArrayList<SiniestrosMayores> siniestrosMayores = new ArrayList<SiniestrosMayores>();
		siniestrosMayores.add(new SiniestrosMayores("1150250772","Otros recién nacidos pre término","2015-2016",1997835));		
		siniestrosMayores.add(new SiniestrosMayores("1140209240","Otros recién nacidos pre término","2013-2014",1895998));
		siniestrosMayores.add(new SiniestrosMayores("1150251720","Esclerosis múltiple SAI","2015-2016",1894832));
		siniestrosMayores.add(new SiniestrosMayores("1140257979","Linfangitis","2014-2015",165684));
		siniestrosMayores.add(new SiniestrosMayores("1140257979","Linfangitis","2015-2016",1612961));
		siniestrosMayores.add(new SiniestrosMayores("1100244731","Gastroduodentis, no especificada","2013-2014",51293));
		siniestrosMayores.add(new SiniestrosMayores("1100244731","Gastroduodentis, no especificada","2014-2015",38210));
		siniestrosMayores.add(new SiniestrosMayores("1100244731","Gastroduodentis, no especificada","2015-2016",424248));
		siniestrosMayores.add(new SiniestrosMayores("3140202258","Lumbago no especificado","2014-2015",474453));				
		
		
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
		
		ArrayList<ConceptoMonto> pm = new ArrayList<ConceptoMonto>();
		pm.add(new ConceptoMonto("Diabetes",102626));
		pm.add(new ConceptoMonto("Trastornos de Columna",699781));
		pm.add(new ConceptoMonto("Tumor Maligno",608634));
		
		String hpc = "Considerando el acumulado de los 3 años del análisis, el comportamiento de "
				+ "los padecimientos crónico-degenerativos es el siguiente:";
		String ppc = " Las tres especialidades que generan mayor gasto son: Pediatría 15% , "
				+ "Ortopedia 13.4% y Gastroenterología 9% (porcentaje respecto al monto pagado "
				+ "acumulado del periodo de análisis).\n" + 
				"Por otro lado las tres especialidades con mayor número de siniestros son: "
				+ "Oftalmología 45.5%, Ortopedia 12.7% y Gastroenterología 6.9% (porcentaje "
				+ "respecto al total de siniestros acumulados en el periodo de análisis).";
		
		TopPadecimientosCronicos tpc = new TopPadecimientosCronicos(hpc,ppc,37656198.61,pm);
		
		ArrayList<PadCronicoClienteMercado> pcm = new ArrayList<PadCronicoClienteMercado>(); 
		pcm.add(new PadCronicoClienteMercado("Diabetes",0.4f,2.0f));
		pcm.add(new PadCronicoClienteMercado("Hipertensión",0.0f,2.1f));
		pcm.add(new PadCronicoClienteMercado("Insuficiencia",0.0f,0.7f));
		pcm.add(new PadCronicoClienteMercado("Transtornos de Columna",2.5f,1.8f));
		pcm.add(new PadCronicoClienteMercado("Trastornos Reumatológicos",0.0f,0.8f));
		pcm.add(new PadCronicoClienteMercado("Tumor Maligno",1.2f,2.9f));
		
		ArrayList<PadecimientoCronicoValues> pcl1 = new ArrayList<PadecimientoCronicoValues>();
		pcl1.add(new PadecimientoCronicoValues("Diabetes",27745,1));
		pcl1.add(new PadecimientoCronicoValues("Hipertensión",0,0));
		pcl1.add(new PadecimientoCronicoValues("Insuficiencia Renal",0,0));
		pcl1.add(new PadecimientoCronicoValues("Trastornos de Columna",276441,11));
		pcl1.add(new PadecimientoCronicoValues("Trastornos Reumatológicos",0,0));
		pcl1.add(new PadecimientoCronicoValues("Tumor Maligno",378493,4));	
		
		ArrayList<PadecimientoCronicoValues> pcl2 = new ArrayList<PadecimientoCronicoValues>();
		pcl1.add(new PadecimientoCronicoValues("Diabetes",40563,2));
		pcl1.add(new PadecimientoCronicoValues("Hipertensión",0,0));
		pcl1.add(new PadecimientoCronicoValues("Insuficiencia Renal",0,0));
		pcl1.add(new PadecimientoCronicoValues("Trastornos de Columna",332579,11));
		pcl1.add(new PadecimientoCronicoValues("Trastornos Reumatológicos",0,0));
		pcl1.add(new PadecimientoCronicoValues("Tumor Maligno",155552,4));
		
		ArrayList<PadecimientoCronicoValues> pcl3 = new ArrayList<PadecimientoCronicoValues>();
		pcl1.add(new PadecimientoCronicoValues("Diabetes",34319,2));
		pcl1.add(new PadecimientoCronicoValues("Hipertensión",0,0));
		pcl1.add(new PadecimientoCronicoValues("Insuficiencia Renal",0,0));
		pcl1.add(new PadecimientoCronicoValues("Trastornos de Columna",90762,8));
		pcl1.add(new PadecimientoCronicoValues("Trastornos Reumatológicos",0,0));
		pcl1.add(new PadecimientoCronicoValues("Tumor Maligno",74590,6));
		
		ArrayList<PadCronicosMontos> pcml = new ArrayList<PadCronicosMontos>();
		pcml.add(new PadCronicosMontos("2013-2014",pcl1));
		pcml.add(new PadCronicosMontos("2014-2015",pcl2));
		pcml.add(new PadCronicosMontos("2015-2016",pcl3));
		
		PadecimientoCronicos pcd = new PadecimientoCronicos(pcml,tpc,pcm);	
		
		String dgt = "NOTA: La cifra del gasto no cubierto fue deducida con base a diversos conceptos proporcionados por la aseguradora. "
				+ "Por otra parte las cifras mostradas en esta lámina se basan en la distribución proporcionada por el área "
				+ "de siniestros para los casos de reembolso.";
		
		ArrayList<ConceptoMonto> dgv1 = new ArrayList<ConceptoMonto>();
		dgv1.add(new ConceptoMonto("Medicamentos sin relación con el padecimiento",205333));
		dgv1.add(new ConceptoMonto("Honorarios que exceden tabulador",124666));
		dgv1.add(new ConceptoMonto("Estudios sin relación con padecimiento",73333));
		dgv1.add(new ConceptoMonto("Coberturas con suma asegurada topada",329999));
		
		ArrayList<ConceptoMonto> dgv2 = new ArrayList<ConceptoMonto>();
		dgv2.add(new ConceptoMonto("Medicamentos sin relación con el padecimiento",241471));
		dgv2.add(new ConceptoMonto("Honorarios que exceden tabulador",146607));
		dgv2.add(new ConceptoMonto("Estudios sin relación con padecimiento",86240));
		dgv2.add(new ConceptoMonto("Coberturas con suma asegurada topada",388078));
		
		ArrayList<ConceptoMonto> dgv3 = new ArrayList<ConceptoMonto>();
		dgv3.add(new ConceptoMonto("Medicamentos sin relación con el padecimiento",204250));
		dgv3.add(new ConceptoMonto("Honorarios que exceden tabulador",124009));
		dgv3.add(new ConceptoMonto("Estudios sin relación con padecimiento",72946));
		dgv3.add(new ConceptoMonto("Coberturas con suma asegurada topada",328259));
		

		ArrayList<DistribucionGastosValues> dgv = new ArrayList<DistribucionGastosValues>();
		dgv.add(new DistribucionGastosValues("2013-2014",dgv1));
		dgv.add(new DistribucionGastosValues("2014-2015",dgv2));
		dgv.add(new DistribucionGastosValues("2016-2016",dgv3));
		
		DistribucionGastos dg = new DistribucionGastos(dgt,dgv,dgv1);
				
		
		String tht = "Es importante destacar que para un 69.90% de los siniestros, no contamos con la información referente al hospital que atendió "
				+ "a los siniestros, estamos negociando con las aseguradoras el envío con los datos completos para el análisis.";
		
		
		ArrayList<HospitalPorcentaje> thcm = new ArrayList<HospitalPorcentaje>();
		thcm.add(new HospitalPorcentaje("Hospital Angeles Del Pedregal",2.62f));
		thcm.add(new HospitalPorcentaje("Hospital A.B.C",2.62f));
		thcm.add(new HospitalPorcentaje("Hospital San Javier",1.83f));
		thcm.add(new HospitalPorcentaje("Hospital Español",1.83f));
		thcm.add(new HospitalPorcentaje("Hospital Angeles De Cd Juarez",1.83f));
		TopHospitalesGrafica thg = new TopHospitalesGrafica(tht,69.90f,19.37f,thcm);
		
		ArrayList<TopHospitalesValues> thv = new ArrayList<TopHospitalesValues>(); 
		thv.add(new TopHospitalesValues("Hospital Ángeles Del Pedregal","Monto Pagado",675449.51f));
		thv.add(new TopHospitalesValues("Hospital Ángeles Del Pedregal","Número de Siniestros",10f));
		thv.add(new TopHospitalesValues("Hospital Ángeles Del Pedregal","Costo Promedio",67545f));
		thv.add(new TopHospitalesValues("Hospital Ángeles Del Pedregal","Morbilidad",0.87f));
		thv.add(new TopHospitalesValues("Hospital A.B.C","Monto Pagado",498027.01f));
		thv.add(new TopHospitalesValues("Hospital A.B.C","Número de Siniestros",10f));
		thv.add(new TopHospitalesValues("Hospital A.B.C","Costo Promedio",49803f));
		thv.add(new TopHospitalesValues("Hospital A.B.C","Morbilidad",0.87f));
		thv.add(new TopHospitalesValues("Hospital San Javier","Monto Pagado",214458.06f));
		thv.add(new TopHospitalesValues("Hospital San Javier","Número de Siniestros",7f));
		thv.add(new TopHospitalesValues("Hospital San Javier","Costo Promedio",30637f));
		thv.add(new TopHospitalesValues("Hospital San Javier","Morbilidad",0.61f));
		thv.add(new TopHospitalesValues("Hospital Español","Monto Pagado",300968.17f));
		thv.add(new TopHospitalesValues("Hospital Español","Número de Siniestros",7f));
		thv.add(new TopHospitalesValues("Hospital Español","Costo Promedio",41995f));
		thv.add(new TopHospitalesValues("Hospital Español","Morbilidad",0.61f));
		thv.add(new TopHospitalesValues("Hospital Á́ngeles De Cd Juárez","Monto Pagado",785601.64f));
		thv.add(new TopHospitalesValues("Hospital Á́ngeles De Cd Juárez","Número de Siniestros",7f));
		thv.add(new TopHospitalesValues("Hospital Á́ngeles De Cd Juárez","Costo Promedio",112229f));
		thv.add(new TopHospitalesValues("Hospital Á́ngeles De Cd Juárez","Morbilidad",0.61f));
		
		
		TopHospitales th = new TopHospitales(thv,thg);
		
		ArrayList<ParticipacionAsegurado> pa = new ArrayList<ParticipacionAsegurado>();
		pa.add(new ParticipacionAsegurado("2013-2014",12183742,203563,266076,568346,733331,13955058,87f,1f,2f,4f,5f));
		pa.add(new ParticipacionAsegurado("2014-2015",10825494,235152,260618,759024,862397,12942685,84f,2f,2f,6f,7f));
		pa.add(new ParticipacionAsegurado("2015-2016",16058004,268655,270204,802736,729464,18129063,89f,1f,1f,4f,4f));
			
		ArrayList<ComparativoHospitalValues> chv = new ArrayList<ComparativoHospitalValues>();
		chv.add(new ComparativoHospitalValues("Cesárea","Centro Medico Dalinde",1,36051,36051));
		chv.add(new ComparativoHospitalValues("Cesárea","Hospital De La Mujer",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Cesárea","Hospital San Javier",1,36051,36051));
		chv.add(new ComparativoHospitalValues("Cesárea","Star Medica Cd. Juárez",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Embarazo Abdominal","Hospital A.B.C",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Embarazo Abdominal","Hospital San Javier",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Miopia y Astigmatismo","Hospital San Javier",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Miopia y Astigmatismo","Active Visión Center",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Miopia y Astigmatismo","Klarhet Laser",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Recien Nacido Sano","Hospital A.B.C",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Recien Nacido Sano","Hospital San Javier",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Recien Nacido Sano","Hospital Sur Lomas",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Recien Nacido Sano","Hospital Ángeles del Pedregal",2,88647,44323));
		chv.add(new ComparativoHospitalValues("Recien Nacido Sano","Hospital Country 2000",2,88647,44323));
		
		String cht = "Para el padecimiento “Presencia de anteojos y lentes de contacto” no "
				+ "contamos con información del hospital en que se atendieron los siniestros, "
				+ "motivo por el cual, dicho padecimiento no aparece en esta lámina";	
		
		ComparativoHospital ch = new ComparativoHospital(cht,chv);
		
		generador.setReporteAntecedentes(ant);
		generador.setReporteSiniestralidadEsperada(se);
		generador.setReportePoblacionHistorica(ph);
		generador.setReporteIndicadoresSiniestralidad(pc);
		generador.setReporteInflacionSectorSalud(iss);		
		generador.setReporteCostoPromedio(cps);
		generador.setReporteSiniestroRangoGrafica(srv);
		generador.setReporteSiniestroRangoTabla(srp);
		generador.setReporteSiniestrosMayores(siniestrosMayores);
		generador.setReportePadecimientosCronicos(pcd);
		generador.setReporteSiniestrosPadecimientos(sp);
		generador.setReportePadecimientosFrecuentes(pf);
		generador.setReporteParticipacionAsegurado(pa);
		generador.setReporteGastosNoCubiertos(dg);
		generador.setResporteTopHospitales(th);
		generador.setReporteMontosPagados(mp);
		generador.setReporteComparativoHospitales(ch);
		generador.setReporteCostoVsTarifasFemenino(cvt);		
		generador.setReporteCostoVsTarifasMasculino(cvt);
		generador.setReporteMisionVision(mv);
		
		
		generador.generaReporte();
		
	}

}
