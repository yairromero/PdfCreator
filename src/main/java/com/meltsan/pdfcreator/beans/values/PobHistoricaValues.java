package com.meltsan.pdfcreator.beans.values;

public class PobHistoricaValues {
	
	private String periodo;
	private Integer asegurados;
	private Double variacionAsegurados;
	private Double variacionVs1Asegurados;
	private Long primaNeta;
	private Double variacionPrimaNeta;
	private Double variacionVs1PrimaNeta;
	private Long primaPerCapita;
	private Double variacionPerCapita;
	private Double variacionVs1PerCapita;
	
	public PobHistoricaValues(String periodo, Integer asegurados, Double variacionAsegurados,
			Double variacionVs1Asegurados, Long primaNeta, Double variacionPrimaNeta,
			Double variacionVs1PrimaNeta, Long primaPerCapita, Double variacionPerCapita,
			Double variacionVs1PerCapita) {
		
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

	public Double getVariacionAsegurados() {
		return variacionAsegurados;
	}

	public void setVariacionAsegurados(Double variacionAsegurados) {
		this.variacionAsegurados = variacionAsegurados;
	}

	public Double getVariacionVs1Asegurados() {
		return variacionVs1Asegurados;
	}

	public void setVariacionVs1Asegurados(Double variacionVs1Asegurados) {
		this.variacionVs1Asegurados = variacionVs1Asegurados;
	}

	public Double getVariacionPrimaNeta() {
		return variacionPrimaNeta;
	}

	public void setVariacionPrimaNeta(Double variacionPrimaNeta) {
		this.variacionPrimaNeta = variacionPrimaNeta;
	}

	public Double getVariacionVs1PrimaNeta() {
		return variacionVs1PrimaNeta;
	}

	public void setVariacionVs1PrimaNeta(Double variacionVs1PrimaNeta) {
		this.variacionVs1PrimaNeta = variacionVs1PrimaNeta;
	}

	public Double getVariacionPerCapita() {
		return variacionPerCapita;
	}

	public void setVariacionPerCapita(Double variacionPerCapita) {
		this.variacionPerCapita = variacionPerCapita;
	}

	public Double getVariacionVs1PerCapita() {
		return variacionVs1PerCapita;
	}

	public void setVariacionVs1PerCapita(Double variacionVs1PerCapita) {
		this.variacionVs1PerCapita = variacionVs1PerCapita;
	}
	
	

}
