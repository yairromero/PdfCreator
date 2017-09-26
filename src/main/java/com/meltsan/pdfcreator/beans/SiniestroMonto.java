package com.meltsan.pdfcreator.beans;

import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;

public class SiniestroMonto {
	
	private SiniestroPadecimientoValues siniestro;
	private Long monto;
	
	public SiniestroMonto(SiniestroPadecimientoValues siniestro, Long monto) {		
		this.siniestro = siniestro;
		this.monto = monto;
	}

	public SiniestroPadecimientoValues getSiniestro() {
		return siniestro;
	}

	public void setSiniestro(SiniestroPadecimientoValues siniestro) {
		this.siniestro = siniestro;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

}
