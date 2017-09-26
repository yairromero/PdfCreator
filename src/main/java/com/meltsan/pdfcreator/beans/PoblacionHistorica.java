package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;

public class PoblacionHistorica {
	
	private String texto;
	private ArrayList<PobHistoricaValues> valores;
	
	public PoblacionHistorica(String texto, ArrayList<PobHistoricaValues> valores) {
		
		this.texto = texto;
		this.valores = valores;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<PobHistoricaValues> getValores() {
		return valores;
	}

	public void setGrafica(ArrayList<PobHistoricaValues> valores) {
		this.valores = valores;
	}

}
