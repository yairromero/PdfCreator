package com.meltsan.pdfcreator.beans.values;

public class PobHistoricaValues {
	
	private String periodo;
	private Integer asegurados;
	//private Double variacionAsegurados;
	//private Double variacionVs1Asegurados;
	private Long primaNeta;
	//private Double variacionPrimaNeta;
	//private Double variacionVs1PrimaNeta;
	private Long primaPerCapita;
	//private Double variacionPerCapita;
	//private Double variacionVs1PerCapita;
	
	/*public PobHistoricaValues(String periodo, Integer asegurados, Double variacionAsegurados,
			Double variacionVs1Asegurados, Long primaNeta, Double variacionPrimaNeta,
			Double variacionVs1PrimaNeta, Long primaPerCapita, Double variacionPerCapita,
			Double variacionVs1PerCapita) {*/
	
	public PobHistoricaValues(String periodo,Integer asegurados, Long primaNeta, Long primaPerCapita) {
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
