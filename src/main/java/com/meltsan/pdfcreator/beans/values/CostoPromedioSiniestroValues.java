package com.meltsan.pdfcreator.beans.values;

public class CostoPromedioSiniestroValues {
	
	private String periodo;
	private Integer costoPromedio;
	private Integer costoPromSinCatastrofe;
	private Integer costoPromInflacionSS;

	public CostoPromedioSiniestroValues(String periodo, Integer costoPromedio, 
									Integer costoPromSinCatastrofe, Integer costoPromInflacionSS) {
		
		this.periodo = periodo;
		this.costoPromedio = costoPromedio;
		this.costoPromSinCatastrofe = costoPromSinCatastrofe;
		this.costoPromInflacionSS = costoPromInflacionSS;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Integer getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(Integer costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	public Integer getCostoPromSinCatastrofe() {
		return costoPromSinCatastrofe;
	}

	public void setCostoPromSinCatastrofe(Integer costoPromSinCatastrofe) {
		this.costoPromSinCatastrofe = costoPromSinCatastrofe;
	}

	public Integer getCostoPromInflacionSS() {
		return costoPromInflacionSS;
	}

	public void setCostoPromInflacionSS(Integer costoPromInflacionSS) {
		this.costoPromInflacionSS = costoPromInflacionSS;
	}
	
}
