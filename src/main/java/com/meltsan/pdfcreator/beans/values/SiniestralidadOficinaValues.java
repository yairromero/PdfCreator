package com.meltsan.pdfcreator.beans.values;

public class SiniestralidadOficinaValues {

	private String estado;
	private Integer noSiniestros;
	private Integer montoPagado;
	
	public SiniestralidadOficinaValues(String estado, Integer noSiniestros, Integer montoPagado) {
		this.estado = estado;
		this.noSiniestros = noSiniestros;
		this.montoPagado = montoPagado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNoSiniestros() {
		return noSiniestros;
	}

	public void setNoSiniestros(Integer noSiniestros) {
		this.noSiniestros = noSiniestros;
	}

	public Integer getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Integer montoPagado) {
		this.montoPagado = montoPagado;
	}
	
	

}
