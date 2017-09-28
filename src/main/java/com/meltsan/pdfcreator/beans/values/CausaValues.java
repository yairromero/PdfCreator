package com.meltsan.pdfcreator.beans.values;

public class CausaValues {

	private String periodo;
	private Float montoAccidente;
	private Float montoEnfermedad;	
	private Float montoParto;
	
	public CausaValues(String periodo, Float montoAccidente, Float montoEnfermedad, Float montoParto) {
		this.periodo = periodo;
		this.montoAccidente = montoAccidente;
		this.montoEnfermedad = montoEnfermedad;
		this.montoParto = montoParto;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Float getMontoAccidente() {
		return montoAccidente;
	}

	public void setMontoAccidente(Float montoAccidente) {
		this.montoAccidente = montoAccidente;
	}

	public Float getMontoEnfermedad() {
		return montoEnfermedad;
	}

	public void setMontoEnfermedad(Float montoEnfermedad) {
		this.montoEnfermedad = montoEnfermedad;
	}

	public Float getMontoParto() {
		return montoParto;
	}

	public void setMontoParto(Float montoParto) {
		this.montoParto = montoParto;
	}
}
