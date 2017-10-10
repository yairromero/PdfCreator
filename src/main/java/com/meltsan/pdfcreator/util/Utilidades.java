package com.meltsan.pdfcreator.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.meltsan.pdfcreator.beans.IndicadoresOficina;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.beans.values.TiempoRespuesta;

public class Utilidades {

	public static ArrayList<String> getPeriodosSinMayores(ArrayList<SiniestrosMayores> siniestros){
		ArrayList<String> result = new ArrayList<String>();
			
			for(SiniestrosMayores siniestro: siniestros) {
				result.add(siniestro.getPeriodo());
			}
			
			HashSet<String> h = new HashSet<String>(result);
			result.clear();
			result.addAll(h);
			Collections.sort(result);
			
		return result;
	}
	
	public static ArrayList<String> getEtiquetasSinMayores(ArrayList<SiniestrosMayores> siniestros){
		ArrayList<String> result = new ArrayList<String>();
			
			for(SiniestrosMayores siniestro: siniestros) {
				result.add(siniestro.getNoSiniestro());
			}
			
			HashSet<String> h = new HashSet<String>(result);
			result.clear();
			result.addAll(h);
			Collections.reverse(result);
			
		return result;
	}
	
	public static ArrayList<String> getPeriodosIndicadoresOficina(ArrayList<IndicadoresOficina> oficinas){
		ArrayList<String> result = new ArrayList<String>();
			
			for(IndicadoresOficina oficina: oficinas) {
				result.add(oficina.getPeriodo());
			}
			
			HashSet<String> h = new HashSet<String>(result);
			
			result.clear();
			result.addAll(h);
			Collections.sort(result);
			
		return result;
	}
	
	public static ArrayList<String> getEtiquetasIndicadoresOficina(ArrayList<IndicadoresOficina> oficinas){
		ArrayList<String> result = new ArrayList<String>();
			
			for(IndicadoresOficina oficina: oficinas) {
				result.add(oficina.getOficina());
			}
			
			HashSet<String> h = new HashSet<String>(result);
			result.clear();
			result.addAll(h);
			Collections.reverse(result);
			
		return result;
	}
	
	
	public static ArrayList<String> getEtiquetasTiempoRespuesta(ArrayList<TiempoRespuesta> tiempos){
		ArrayList<String> result = new ArrayList<String>();
			
			for(TiempoRespuesta tiempo: tiempos) {
				result.add(tiempo.getMes());
			}
			
			HashSet<String> h = new HashSet<String>(result);
			result.clear();			
			
			for(int i=0;i<h.size();i++) {
				result.add(tiempos.get(i).getMes());
			}
			
		return result;
	}
	
	public static ArrayList<String> getPeriodoTiempoRespuesta(ArrayList<TiempoRespuesta> tiempos){
		ArrayList<String> result = new ArrayList<String>();
			
			for(TiempoRespuesta tiempo: tiempos) {
				result.add(tiempo.getTiempoRespuesta().toString());
			}
			
			HashSet<String> h = new HashSet<String>(result);
			result.clear();			
			result.addAll(h);
			Collections.sort(result);
			
		return result;
	}

}
