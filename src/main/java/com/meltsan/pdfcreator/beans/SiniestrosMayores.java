package com.meltsan.pdfcreator.beans;

public class SiniestrosMayores {
	
	private String noSiniestro;
	private String padecimiento;
	private String periodo;
	private Integer monto;
	
	public SiniestrosMayores(String noSiniestro, String padecimiento, String periodo, Integer monto) {		
		this.noSiniestro = noSiniestro;
		this.padecimiento = padecimiento;
		this.periodo = periodo;
		this.monto = monto;
	}

	public String getNoSiniestro() {
		return noSiniestro;
	}

	public void setNoSiniestro(String noSiniestro) {
		this.noSiniestro = noSiniestro;
	}

	public String getPadecimiento() {
		return padecimiento;
	}

	public void setPadecimiento(String padecimiento) {
		this.padecimiento = padecimiento;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

}
