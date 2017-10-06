package com.meltsan.pdfcreator.beans.values;

public class TopHospitalesValues {

	private String hopital;
	private String concepto;
	private Float indicadores;
	
	public TopHospitalesValues(String hospital, String concepto, Float indicadores) {
		this.hopital = hospital;
		this.concepto = concepto;
		this.indicadores = indicadores;
	}

	public String getHopital() {
		return hopital;
	}

	public void setHopital(String hopital) {
		this.hopital = hopital;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Float getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Float indicadores) {
		this.indicadores = indicadores;
	}
	
	

}
