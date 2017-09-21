package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.SiniestroRangoValues;

public class SiniestroRango {
	
	private ArrayList<SiniestroRangoValues> siniestros;
	
	public SiniestroRango(ArrayList<SiniestroRangoValues> siniestros) {
		this.siniestros = siniestros;
	}

	public ArrayList<SiniestroRangoValues> getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(ArrayList<SiniestroRangoValues> siniestros) {
		this.siniestros = siniestros;
	}

}
