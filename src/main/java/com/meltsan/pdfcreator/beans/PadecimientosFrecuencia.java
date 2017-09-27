package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.PadecimientosFrecuenciaValues;

public class PadecimientosFrecuencia {
	
	private String texto;
	private ArrayList<PadecimientosFrecuenciaValues> padecimientos;
	
	public PadecimientosFrecuencia(String texto, ArrayList<PadecimientosFrecuenciaValues> padecimientos) {
		this.texto = texto;
		this.padecimientos = padecimientos;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<PadecimientosFrecuenciaValues> getPadecimientos() {
		return padecimientos;
	}

	public void setPadecimientos(ArrayList<PadecimientosFrecuenciaValues> padecimientos) {
		this.padecimientos = padecimientos;
	}
}
