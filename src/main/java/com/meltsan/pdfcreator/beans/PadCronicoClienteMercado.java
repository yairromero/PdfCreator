package com.meltsan.pdfcreator.beans;

public class PadCronicoClienteMercado {
	
	private String padecimiento;
	private Float porcentajeMercado;
	private Float porcentajeCliente;

	public PadCronicoClienteMercado(String padecimiento, Float porcentajeCliente, 
										Float porcentajeMercado) {
		this.padecimiento = padecimiento;
		this.porcentajeCliente = porcentajeCliente;
		this.porcentajeMercado = porcentajeMercado;
		
	}

	public String getPadecimiento() {
		return padecimiento;
	}

	public void setPadecimiento(String padecimiento) {
		this.padecimiento = padecimiento;
	}

	public Float getPorcentajeMercado() {
		return porcentajeMercado;
	}

	public void setPorcentajeMercado(Float porcentajeMercado) {
		this.porcentajeMercado = porcentajeMercado;
	}

	public Float getPorcentajeCliente() {
		return porcentajeCliente;
	}

	public void setPorcentajeCliente(Float porcentajeCliente) {
		this.porcentajeCliente = porcentajeCliente;
	}

}
