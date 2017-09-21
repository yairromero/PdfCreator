package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;

public class SiniestroPadecimiento {
	
	private String texto;
	private ArrayList<SiniestroPadecimientoValues> siniestros;
	
	public SiniestroPadecimiento(String texto, ArrayList<SiniestroPadecimientoValues> siniestros ) {
		this.texto = texto;
		this.siniestros = siniestros;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<SiniestroPadecimientoValues> getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(ArrayList<SiniestroPadecimientoValues> siniestros) {
		this.siniestros = siniestros;
	}

}
