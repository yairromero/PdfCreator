package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.DistribucionGastosValues;

public class DistribucionGastos {

	private String nota;
	private ArrayList<DistribucionGastosValues> gastos;
	private ArrayList<ConceptoMonto> gastosTotales;
	
	public DistribucionGastos(String nota, ArrayList<DistribucionGastosValues> gastos,ArrayList<ConceptoMonto> gastosTotales) {
		this.nota = nota;
		this.gastos = gastos;
		this.gastosTotales = gastosTotales;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public ArrayList<DistribucionGastosValues> getGastos() {
		return gastos;
	}

	public void setGastos(ArrayList<DistribucionGastosValues> gastos) {
		this.gastos = gastos;
	}

	public ArrayList<ConceptoMonto> getGastosTotales() {
		return gastosTotales;
	}

	public void setGastosTotales(ArrayList<ConceptoMonto> gastosTotales) {
		this.gastosTotales = gastosTotales;
	}
	
	

}
