package com.meltsan.pdfcreator.beans.values;

public class SiniestroPadecimientoValues {
	
	private Long siniestro;
	private String padecimiento;
	
	public SiniestroPadecimientoValues(Long siniestro, String padecimiento) {
		this.siniestro = siniestro;
		this.padecimiento = padecimiento;
	}

	public Long getSiniestro() {
		return siniestro;
	}

	public void setSiniestro(Long siniestro) {
		this.siniestro = siniestro;
	}

	public String getPadecimiento() {
		return padecimiento;
	}

	public void setPadecimiento(String padecimiento) {
		this.padecimiento = padecimiento;
	}

}
