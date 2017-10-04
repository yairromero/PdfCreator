package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

public class TopPadecimientosCronicos {
	
	private String encabezado;
	private Double montoNoCronicos;
	private ArrayList<ConceptoMonto> padecimientosCronicos;
	private String piePagina;

	public TopPadecimientosCronicos(String encabezado, String piePagina, Double montoNoCronicos, 
							ArrayList<ConceptoMonto> padecimientosCronicos) {
		
		this.encabezado = encabezado;
		this.padecimientosCronicos = padecimientosCronicos;
		this.montoNoCronicos = montoNoCronicos;		
		this.piePagina = piePagina;
	}

	public Double getMontoNoCronicos() {
		return montoNoCronicos;
	}

	public void setMontoNoCronicos(Double montoNoCronicos) {
		this.montoNoCronicos = montoNoCronicos;
	}

	public ArrayList<ConceptoMonto> getPadecimientosCronicos() {
		return padecimientosCronicos;
	}

	public void setPadecimientosCronicos(ArrayList<ConceptoMonto> padecimientosCronicos) {
		this.padecimientosCronicos = padecimientosCronicos;
	}

	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public String getPiePagina() {
		return piePagina;
	}

	public void setPiePagina(String piePagina) {
		this.piePagina = piePagina;
	}

}
