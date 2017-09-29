package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.CostoPromedioSiniestroValues;

public class CostoPromedioSiniestro {

	private String texto;
	private ArrayList<CostoPromedioSiniestroValues> valores;
	
	public CostoPromedioSiniestro(String texto, ArrayList<CostoPromedioSiniestroValues> valores) {
		
		this.texto = texto;
		this.valores = valores;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<CostoPromedioSiniestroValues> getValores() {
		return valores;
	}

	public void setValores(ArrayList<CostoPromedioSiniestroValues> valores) {
		this.valores = valores;
	}
	
	

}
