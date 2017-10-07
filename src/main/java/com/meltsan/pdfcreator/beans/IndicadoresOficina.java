package com.meltsan.pdfcreator.beans;

public class IndicadoresOficina {
	
	private String periodo;
	private String oficina;
	private Integer morbilidad;
	private Integer costoPromedio;
	private Integer costoPerCapita;

	public IndicadoresOficina(String periodo, String oficina, Integer morbilidad, 
								Integer costoPromedio, Integer costoPerCapita) {
		this.periodo = periodo;
		this.oficina = oficina;
		this.morbilidad = morbilidad;
		this.costoPromedio = costoPromedio;
		this.costoPerCapita = costoPerCapita;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public Integer getMorbilidad() {
		return morbilidad;
	}

	public void setMorbilidad(Integer morbilidad) {
		this.morbilidad = morbilidad;
	}

	public Integer getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(Integer costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	public Integer getCostoPerCapita() {
		return costoPerCapita;
	}

	public void setCostoPerCapita(Integer costoPerCapita) {
		this.costoPerCapita = costoPerCapita;
	}

	
}
