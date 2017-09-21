package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.SiniestroRangoValuesGrafica;

public class SiniestroRangoGrafica {
	
	private ArrayList<SiniestroRangoValuesGrafica> siniestros;
	
	public SiniestroRangoGrafica(ArrayList<SiniestroRangoValuesGrafica> siniestros) {
		this.siniestros = siniestros;
	}

	public ArrayList<SiniestroRangoValuesGrafica> getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(ArrayList<SiniestroRangoValuesGrafica> siniestros) {
		this.siniestros = siniestros;
	}

}
