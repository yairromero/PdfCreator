package com.meltsan.pdfcreator.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.meltsan.pdfcreator.beans.SiniestrosMayores;

public class Utilidades {

	public static ArrayList<String> getPeriodosSinMayores(ArrayList<SiniestrosMayores> siniestros){
		ArrayList<String> result = new ArrayList<String>();
			
			for(SiniestrosMayores siniestro: siniestros) {
				result.add(siniestro.getPeriodo());
			}
			
			HashSet<String> h = new HashSet<String>(result);
			result.clear();
			result.addAll(h);
			Collections.reverse(result);
			
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

}
