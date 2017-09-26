package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.PerCapitaValues;

public class IndicadoresSiniestros {
	
	private String texto;
	private ArrayList<PerCapitaValues> valores;
	
	public IndicadoresSiniestros(String texto, ArrayList<PerCapitaValues> valores) {
		this.texto = texto;
		this.valores = valores;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<PerCapitaValues> getValores() {
		return valores;
	}

	public void setValores(ArrayList<PerCapitaValues> valores) {
		this.valores = valores;
	}

}
