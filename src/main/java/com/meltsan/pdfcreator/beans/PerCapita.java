package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

public class PerCapita {
	
	private String texto;
	private ArrayList<PerCapitaValues> grafica;
	
	public PerCapita(String texto, ArrayList<PerCapitaValues> grafica) {
		this.texto = texto;
		this.grafica = grafica;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<PerCapitaValues> getGrafica() {
		return grafica;
	}

	public void setGrafica(ArrayList<PerCapitaValues> grafica) {
		this.grafica = grafica;
	}

}
