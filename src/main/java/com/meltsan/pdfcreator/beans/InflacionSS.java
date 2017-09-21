package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.InflacionSSValues;

public class InflacionSS {
	
	private String texto;
	private ArrayList<InflacionSSValues> grafica;
	
	public InflacionSS(String texto, ArrayList<InflacionSSValues> grafica) {
		this.texto = texto;
		this.grafica = grafica;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public ArrayList<InflacionSSValues> getGrafica() {
		return grafica;
	}
	public void setGrafica(ArrayList<InflacionSSValues> grafica) {
		this.grafica = grafica;
	}
	
	

}
