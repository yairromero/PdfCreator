package com.meltsan.pdfcreator.beans.values;

public class IndicadoresSiniestroValues {
	
	private Long montoPagado;
	private Integer noSiniestros;
	private Integer costoPerCapita;
	private Integer primaPerCapita;
	private Float porcentajeSiniestralidad;
	private Float porcienSiniestroSinCatastrofe;
	private String periodo;	
	
	public IndicadoresSiniestroValues(String periodo, Long montoPagado, Integer noSiniestro, Integer costo, Integer prima,
							Float porcentajeSiniestralidad, Float porcentajeSiniestroSinCatastrofe) {
		this.periodo = periodo;
		this.montoPagado = montoPagado;
		this.noSiniestros = noSiniestro;
		this.costoPerCapita = costo;
		this.primaPerCapita = prima;
		this.porcentajeSiniestralidad = porcentajeSiniestralidad;
		this.porcienSiniestroSinCatastrofe = porcentajeSiniestroSinCatastrofe;
	}
	
	public IndicadoresSiniestroValues(String periodo, Long montoPagado, Integer noSiniestro, Integer costo, Integer prima,
			Float porcentajeSiniestralidad) {
			
		this.periodo = periodo;
		this.montoPagado = montoPagado;
		this.noSiniestros = noSiniestro;
		this.costoPerCapita = costo;
		this.primaPerCapita = prima;
		this.porcentajeSiniestralidad = porcentajeSiniestralidad;

	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Long getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Long montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Integer getNoSiniestros() {
		return noSiniestros;
	}

	public void setNoSiniestros(Integer noSiniestros) {
		this.noSiniestros = noSiniestros;
	}

	public Integer getCostoPerCapita() {
		return costoPerCapita;
	}

	public void setCostoPerCapita(Integer costoPerCapita) {
		this.costoPerCapita = costoPerCapita;
	}

	public Integer getPrimaPerCapita() {
		return primaPerCapita;
	}

	public void setPrimaPerCapita(Integer primaPerCapita) {
		this.primaPerCapita = primaPerCapita;
	}

	public Float getPorcentajeSiniestralidad() {
		return porcentajeSiniestralidad;
	}

	public void setPorcentajeSiniestralidad(Float porcentajeSiniestralidad) {
		this.porcentajeSiniestralidad = porcentajeSiniestralidad;
	}

	public Float getPorcienSiniestroSinCatastrofe() {
		return porcienSiniestroSinCatastrofe;
	}

	public void setPorcienSiniestroSinCatastrofe(Float porcienSiniestroSinCatastrofe) {
		this.porcienSiniestroSinCatastrofe = porcienSiniestroSinCatastrofe;
	}

}
