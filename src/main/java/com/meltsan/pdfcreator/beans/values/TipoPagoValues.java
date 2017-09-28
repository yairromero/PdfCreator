package com.meltsan.pdfcreator.beans.values;

public class TipoPagoValues {

	private String periodo;
	private Float montoPagoDirecto;
	private Float montoReembolso;
	
	public TipoPagoValues(String periodo, Float montoPagoDirecto, Float montoReembolso) {
		this.periodo = periodo;
		this.montoPagoDirecto = montoPagoDirecto;
		this.montoReembolso = montoReembolso;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Float getMontoPagoDirecto() {
		return montoPagoDirecto;
	}

	public void setMontoPagoDirecto(Float montoPagoDirecto) {
		this.montoPagoDirecto = montoPagoDirecto;
	}

	public Float getMontoReembolso() {
		return montoReembolso;
	}

	public void setMontoReembolso(Float montoReembolso) {
		this.montoReembolso = montoReembolso;
	}
}
