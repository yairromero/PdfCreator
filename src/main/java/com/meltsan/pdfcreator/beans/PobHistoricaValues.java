package com.meltsan.pdfcreator.beans;

public class PobHistoricaValues {
	
	private String periodo;
	private Integer asegurados;
	private Long primaNeta;
	private Long primaPerCapita;
	
	public PobHistoricaValues(String periodo, Integer asegurados,Long primaNeta,
								Long primaPerCapita) {
		this.periodo = periodo;
		this.asegurados = asegurados;
		this.primaNeta = primaNeta;
		this.primaPerCapita = primaPerCapita;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Integer getAsegurados() {
		return asegurados;
	}

	public void setAsegurados(Integer asegurados) {
		this.asegurados = asegurados;
	}

	public Long getPrimaNeta() {
		return primaNeta;
	}

	public void setPrimaNeta(Long primaNeta) {
		this.primaNeta = primaNeta;
	}

	public Long getPrimaPerCapita() {
		return primaPerCapita;
	}

	public void setPrimaPerCapita(Long primaPerCapita) {
		this.primaPerCapita = primaPerCapita;
	}

}
