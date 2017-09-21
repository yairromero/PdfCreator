package com.meltsan.pdfcreator.beans.values;

public class SiniestroRangoValuesGrafica {
	
	private String periodo;
	private Double baja;
	private Double alta;
	private Double severa;
	private Double catastrofe;

	public SiniestroRangoValuesGrafica(String periodo, Double baja, Double alta, Double severa, 
								Double catastrofe) {
		
		this.periodo = periodo;
		this.baja = baja;
		this.alta = alta;
		this.severa = severa;
		this.catastrofe = catastrofe;
		
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Double getBaja() {
		return baja;
	}

	public void setBaja(Double baja) {
		this.baja = baja;
	}

	public Double getAlta() {
		return alta;
	}

	public void setAlta(Double alta) {
		this.alta = alta;
	}

	public Double getSevera() {
		return severa;
	}

	public void setSevera(Double severa) {
		this.severa = severa;
	}

	public Double getCatastrofe() {
		return catastrofe;
	}

	public void setCatastrofe(Double catastrofe) {
		this.catastrofe = catastrofe;
	}

}
