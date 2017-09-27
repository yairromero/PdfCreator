package com.meltsan.pdfcreator.beans.values;

public class PadecimientosFrecuenciaValues {
	
	private String padecimiento;
	private Float porcentajeFrecuencia;
	
	public PadecimientosFrecuenciaValues(String padecimiento, Float porcentajeFrecuencia) {
		this.padecimiento = padecimiento;
		this.porcentajeFrecuencia = porcentajeFrecuencia;
	}

	public String getPadecimiento() {
		return padecimiento;
	}

	public void setPadecimiento(String padecimiento) {
		this.padecimiento = padecimiento;
	}

	public Float getPorcentajeFrecuencia() {
		return porcentajeFrecuencia;
	}

	public void setPorcentajeFrecuencia(Float porcentajeFrecuencia) {
		this.porcentajeFrecuencia = porcentajeFrecuencia;
	}

}
