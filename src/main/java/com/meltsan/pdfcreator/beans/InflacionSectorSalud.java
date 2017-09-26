package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.InflacionSSValues;

public class InflacionSectorSalud {
	
	private String texto;
	private ArrayList<InflacionSSValues> valores;
	
	public InflacionSectorSalud(String texto, ArrayList<InflacionSSValues> valores) {
		this.texto = texto;
		this.valores = valores;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public ArrayList<InflacionSSValues> getValores() {
		return valores;
	}
	public void setValores(ArrayList<InflacionSSValues> valores) {
		this.valores = valores;
	}
	
	

}
