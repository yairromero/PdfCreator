package com.meltsan.pdfcreator.beans.values;

public class SiniestralidadEsperadaValues {
	
	private String periodo;
	private Float siniestralidad;
	private Float tendencia;

	public SiniestralidadEsperadaValues(String periodo, Float siniestralidad, Float tendencia) {
		this.periodo = periodo;
		this.siniestralidad = siniestralidad;
		this.tendencia = tendencia;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Float getSiniestralidad() {
		return siniestralidad;
	}

	public void setSiniestralidad(Float siniestralidad) {
		this.siniestralidad = siniestralidad;
	}

	public Float getTendencia() {
		return tendencia;
	}

	public void setTendencia(Float tendencia) {
		this.tendencia = tendencia;
	}
	
}
