package com.meltsan.pdfcreator.beans;

public class CostoPerCapitaTarifas {

	private String grupoEdad;
	private Float costo;
	private Float tarifas;
	private Float morbilidad;
	
	public CostoPerCapitaTarifas(String grupoEdad, Float costo, Float tarifas, Float morbilidad) {
		this.grupoEdad = grupoEdad;
		this.costo = costo;
		this.tarifas = tarifas;
		this.morbilidad = morbilidad;
	}

	public String getGrupoEdad() {
		return grupoEdad;
	}

	public void setGrupoEdad(String grupoEdad) {
		this.grupoEdad = grupoEdad;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public Float getTarifas() {
		return tarifas;
	}

	public void setTarifas(Float tarifas) {
		this.tarifas = tarifas;
	}

	public Float getMorbilidad() {
		return morbilidad;
	}

	public void setMorbilidad(Float morbilidad) {
		this.morbilidad = morbilidad;
	}
}
