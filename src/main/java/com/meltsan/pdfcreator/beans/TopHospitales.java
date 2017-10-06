package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.TopHospitalesValues;

public class TopHospitales {
		
	private ArrayList<TopHospitalesValues> topHospitales;
	private TopHospitalesGrafica topHospitalesGrafica;

	public TopHospitales(ArrayList<TopHospitalesValues> topHospitales,
						TopHospitalesGrafica topHospitalesGrafica) {
				
		this.topHospitales = topHospitales;
		this.topHospitalesGrafica = topHospitalesGrafica;
	}

	public ArrayList<TopHospitalesValues> getTopHospitales() {
		return topHospitales;
	}

	public void setTopHospitales(ArrayList<TopHospitalesValues> topHospitales) {
		this.topHospitales = topHospitales;
	}

	public TopHospitalesGrafica getTopHospitalesGrafica() {
		return topHospitalesGrafica;
	}

	public void setTopHospitalesGrafica(TopHospitalesGrafica topHospitalesGrafica) {
		this.topHospitalesGrafica = topHospitalesGrafica;
	}
	
}
