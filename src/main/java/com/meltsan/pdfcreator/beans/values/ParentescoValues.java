package com.meltsan.pdfcreator.beans.values;

public class ParentescoValues {

	private String periodo;
	private Float montoDependiente;
	private Float montoTitular;
	
	public ParentescoValues(String periodo, Float montoDependiente, Float montoTitular) {
		this.periodo = periodo;
		this.montoDependiente = montoDependiente;
		this.montoTitular = montoTitular;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Float getMontoDependiente() {
		return montoDependiente;
	}

	public void setMontoDependiente(Float montoDependiente) {
		this.montoDependiente = montoDependiente;
	}

	public Float getMontoTitular() {
		return montoTitular;
	}

	public void setMontoTitular(Float montoTitular) {
		this.montoTitular = montoTitular;
	}

	
}
