package com.meltsan.pdfcreator.beans;

public class InflacionSSValues {

	private Float inflacion;
	private String perido;
	
	public InflacionSSValues(String perido, Float inflacion) {
		this.perido = perido;
		this.inflacion = inflacion;
	}

	public Float getInflacion() {
		return inflacion;
	}

	public void setInflacion(Float inflacion) {
		this.inflacion = inflacion;
	}

	public String getPerido() {
		return perido;
	}

	public void setPerido(String perido) {
		this.perido = perido;
	}
}
