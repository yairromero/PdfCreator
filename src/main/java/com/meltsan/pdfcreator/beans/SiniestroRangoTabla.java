package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

public class SiniestroRangoTabla {
	
	private ArrayList<SiniestroRangoPeriodo> periodos;
	
	public SiniestroRangoTabla(ArrayList<SiniestroRangoPeriodo> periodos) {
		this.periodos = periodos;
	}

	public ArrayList<SiniestroRangoPeriodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(ArrayList<SiniestroRangoPeriodo> periodos) {
		this.periodos = periodos;
	}
	
}
