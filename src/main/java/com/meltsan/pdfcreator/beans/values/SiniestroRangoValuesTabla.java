package com.meltsan.pdfcreator.beans.values;

public class SiniestroRangoValuesTabla {

	private Long montoPagado;
	private Long costoPerCapita;
	private Long costoPromedio;
	private Integer noSiniestro;
	private Double representacion;
	
	public SiniestroRangoValuesTabla(Long montoPagado, Long costoPerCapita, 
									Long costoPromedio,Integer noSiniestro, Double representacion) {
		
		this.montoPagado = montoPagado;
		this.costoPerCapita = costoPerCapita;
		this.costoPromedio = costoPromedio;
		this.noSiniestro = noSiniestro;
		this.representacion = representacion;
	}

	public Long getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Long montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Long getCostoPerCapita() {
		return costoPerCapita;
	}

	public void setCostoPerCapita(Long costoPerCapita) {
		this.costoPerCapita = costoPerCapita;
	}

	public Long getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(Long costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	public Integer getNoSiniestro() {
		return noSiniestro;
	}

	public void setNoSiniestro(Integer noSiniestro) {
		this.noSiniestro = noSiniestro;
	}

	public Double getRepresentacion() {
		return representacion;
	}

	public void setRepresentacion(Double representacion) {
		this.representacion = representacion;
	}

	
}
