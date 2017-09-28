package com.meltsan.pdfcreator.beans.values;

public class SexoValues {
	
	private String periodo;
	private Float montoMasculino;
	private Float montoFemenino;

	public SexoValues(String periodo, Float montoMasculino, Float montoFemenino) {
		
		this.periodo = periodo;
		this.montoMasculino = montoMasculino;
		this.montoFemenino = montoFemenino;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Float getMontoMasculino() {
		return montoMasculino;
	}

	public void setMontoMasculino(Float montoMasculino) {
		this.montoMasculino = montoMasculino;
	}

	public Float getMontoFemenino() {
		return montoFemenino;
	}

	public void setMontoFemenino(Float montoFemenino) {
		this.montoFemenino = montoFemenino;
	}

}
