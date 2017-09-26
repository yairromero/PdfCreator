package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

public class SiniestrosMayores {
	
	private String periodo;
	private ArrayList<SiniestroMonto> siniestros;
	
	public SiniestrosMayores(String periodo, ArrayList<SiniestroMonto> siniestros) {
		this.periodo = periodo;
		this.siniestros = siniestros;		
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public ArrayList<SiniestroMonto> getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(ArrayList<SiniestroMonto> siniestros) {
		this.siniestros = siniestros;
	}

}
