package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

public class PoblacionHistorica {
	
	private String texto;
	private ArrayList<PobHistoricaValues> grafica;
	
	public PoblacionHistorica(String texto, ArrayList<PobHistoricaValues> grafica) {
		
		this.texto = texto;
		this.grafica = grafica;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<PobHistoricaValues> getGrafica() {
		return grafica;
	}

	public void setGrafica(ArrayList<PobHistoricaValues> grafica) {
		this.grafica = grafica;
	}

}
