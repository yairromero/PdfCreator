package com.meltsan.pdfcreator.beans.values;

public class PadecimientoCronicoValues {
		
	private String padecimiento;
	private Integer montoPagado;
	private Integer noSiniestros;
	
	public PadecimientoCronicoValues(String padecimiento, Integer montoPagado, Integer noSiniestros) {
		this.padecimiento = padecimiento;
		this.montoPagado = montoPagado;
		this.noSiniestros = noSiniestros;	
	}

	public String getPadecimiento() {
		return padecimiento;
	}

	public void setPadecimiento(String padecimiento) {
		this.padecimiento = padecimiento;
	}

	public Integer getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Integer montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Integer getNoSiniestros() {
		return noSiniestros;
	}

	public void setNoSiniestros(Integer noSiniestros) {
		this.noSiniestros = noSiniestros;
	}
	
	

}
