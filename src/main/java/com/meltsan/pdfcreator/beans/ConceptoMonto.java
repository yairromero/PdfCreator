package com.meltsan.pdfcreator.beans;

public class ConceptoMonto {
	
	private String concepto;
	private Integer monto;

	public ConceptoMonto(String concepto, Integer monto) {
		this.concepto = concepto;
		this.monto = monto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

}
