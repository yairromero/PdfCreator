package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.IndicadoresSiniestroValues;

public class IndicadoresSiniestros {
	
	private String texto;
	private ArrayList<IndicadoresSiniestroValues> valores;
	
	public IndicadoresSiniestros(String texto, ArrayList<IndicadoresSiniestroValues> valores) {
		this.texto = texto;
		this.valores = valores;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<IndicadoresSiniestroValues> getValores() {
		return valores;
	}

	public void setValores(ArrayList<IndicadoresSiniestroValues> valores) {
		this.valores = valores;
	}

}
