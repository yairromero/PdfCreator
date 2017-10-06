package com.meltsan.pdfcreator.beans;

public class ParticipacionAsegurado {

	private Integer montoPagado;
	private Integer montoDeducible;
	private Integer montoCoaseguro;
	private Integer montoIVA;
	private Integer montoNoCubierto;
	private Integer montoReclamado;
	private Float porcentajePagado;
	private Float porcentajeDeducible;
	private Float porcentajeCoaseguro;
	private Float porcentajeIVA;
	private Float porcentajeNoCubierto;
	private String periodo;	
	
	
	public ParticipacionAsegurado(String periodo, Integer montoPagado, Integer montoDeducible,
								Integer montoCoaseguro, Integer montoIVA, Integer montoNoCubierto,
								Integer montoReclamado, Float porcentajePagado, Float porcentajeDeducible,
								Float porcentajeCoaseguro, Float porcentajeIVA, Float porcentajeNoCubierto) {
		
		this.periodo = periodo;
		this.montoPagado = montoPagado;
		this.montoDeducible = montoDeducible;
		this.montoCoaseguro = montoCoaseguro;
		this.montoIVA = montoIVA;
		this.montoNoCubierto = montoNoCubierto;
		this.montoReclamado = montoReclamado;
 		this.porcentajePagado = porcentajePagado;
		this.porcentajeCoaseguro = porcentajeCoaseguro;
		this.porcentajeDeducible = porcentajeDeducible;
		this.porcentajeIVA = porcentajeIVA;
		this.porcentajeNoCubierto = porcentajeNoCubierto;
		
	}


	public Integer getMontoPagado() {
		return montoPagado;
	}


	public void setMontoPagado(Integer montoPagado) {
		this.montoPagado = montoPagado;
	}


	public Integer getMontoDeducible() {
		return montoDeducible;
	}


	public void setMontoDeducible(Integer montoDeducible) {
		this.montoDeducible = montoDeducible;
	}


	public Integer getMontoCoaseguro() {
		return montoCoaseguro;
	}


	public void setMontoCoaseguro(Integer montoCoaseguro) {
		this.montoCoaseguro = montoCoaseguro;
	}


	public Integer getMontoIVA() {
		return montoIVA;
	}


	public void setMontoIVA(Integer montoIVA) {
		this.montoIVA = montoIVA;
	}


	public Integer getMontoNoCubierto() {
		return montoNoCubierto;
	}


	public void setMontoNoCubierto(Integer montoNoCubierto) {
		this.montoNoCubierto = montoNoCubierto;
	}


	public Integer getMontoReclamado() {
		return montoReclamado;
	}


	public void setMontoReclamado(Integer montoReclamado) {
		this.montoReclamado = montoReclamado;
	}


	public Float getPorcentajePagado() {
		return porcentajePagado;
	}


	public void setPorcentajePagado(Float porcentajePagado) {
		this.porcentajePagado = porcentajePagado;
	}


	public Float getPorcentajeDeducible() {
		return porcentajeDeducible;
	}


	public void setPorcentajeDeducible(Float porcentajeDeducible) {
		this.porcentajeDeducible = porcentajeDeducible;
	}


	public Float getPorcentajeCoaseguro() {
		return porcentajeCoaseguro;
	}


	public void setPorcentajeCoaseguro(Float porcentajeCoaseguro) {
		this.porcentajeCoaseguro = porcentajeCoaseguro;
	}


	public Float getPorcentajeIVA() {
		return porcentajeIVA;
	}


	public void setPorcentajeIVA(Float porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}


	public Float getPorcentajeNoCubierto() {
		return porcentajeNoCubierto;
	}


	public void setPorcentajeNoCubierto(Float porcentajeNoCubierto) {
		this.porcentajeNoCubierto = porcentajeNoCubierto;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
