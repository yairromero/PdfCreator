package com.meltsan.pdfcreator.beans;

import java.math.BigDecimal;

public class PerCapita {
	
	private String periodo;
	private BigDecimal costoPerCapita;
	private BigDecimal primaPerCapita;
	
	public PerCapita(String periodo, BigDecimal costo, BigDecimal prima) {
		this.periodo = periodo;
		this.costoPerCapita = costo;
		this.primaPerCapita = prima;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getCostoPerCapita() {
		return costoPerCapita;
	}

	public void setCostoPerCapita(BigDecimal costoPerCapita) {
		this.costoPerCapita = costoPerCapita;
	}

	public BigDecimal getPrimaPerCapita() {
		return primaPerCapita;
	}

	public void setPrimaPerCapita(BigDecimal primaPerCapita) {
		this.primaPerCapita = primaPerCapita;
	}

}
