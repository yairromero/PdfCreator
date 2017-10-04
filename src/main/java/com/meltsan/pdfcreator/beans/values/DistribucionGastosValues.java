package com.meltsan.pdfcreator.beans.values;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.ConceptoMonto;

public class DistribucionGastosValues {

	private String periodo;
	private ArrayList<ConceptoMonto> montos;
	
	public DistribucionGastosValues(String periodo, ArrayList<ConceptoMonto> montos) {
		this.periodo = periodo;
		this.montos = montos;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public ArrayList<ConceptoMonto> getMontos() {
		return montos;
	}

	public void setMontos(ArrayList<ConceptoMonto> montos) {
		this.montos = montos;
	}

}
