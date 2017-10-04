package com.meltsan.pdfcreator.beans;

public class ConceptoMonto {
	
	private String padecimiento;
	private Integer montoTotalPadecimiento;

	public ConceptoMonto(String padecimiento, Integer montoTotalPadecimiento) {
		this.padecimiento = padecimiento;
		this.montoTotalPadecimiento = montoTotalPadecimiento;
	}

	public String getPadecimiento() {
		return padecimiento;
	}

	public void setPadecimiento(String padecimiento) {
		this.padecimiento = padecimiento;
	}

	public Integer getMontoTotalPadecimiento() {
		return montoTotalPadecimiento;
	}

	public void setMontoTotalPadecimiento(Integer montoTotalPadecimiento) {
		this.montoTotalPadecimiento = montoTotalPadecimiento;
	}

}
