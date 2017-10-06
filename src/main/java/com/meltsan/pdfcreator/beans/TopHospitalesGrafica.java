package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

public class TopHospitalesGrafica {

	private String texto;
	private Float hospitalesSinEspecificar;
	private Float otrosHospitales;
	private ArrayList<HospitalPorcentaje> topHospitales;
	
	public TopHospitalesGrafica(String texto, Float hospitalesSinEspecificar,Float otrosHospitales,
							ArrayList<HospitalPorcentaje> topHospitales ) {
		
		this.texto = texto;
		this.hospitalesSinEspecificar = hospitalesSinEspecificar;
		this.otrosHospitales = otrosHospitales;
		this.topHospitales = topHospitales;
		
	}

	public Float getHospitalesSinEspecificar() {
		return hospitalesSinEspecificar;
	}

	public void setHospitalesSinEspecificar(Float hospitalesSinEspecificar) {
		this.hospitalesSinEspecificar = hospitalesSinEspecificar;
	}

	public Float getOtrosHospitales() {
		return otrosHospitales;
	}

	public void setOtrosHospitales(Float otrosHospitales) {
		this.otrosHospitales = otrosHospitales;
	}

	public ArrayList<HospitalPorcentaje> getTopHospitales() {
		return topHospitales;
	}

	public void setTopHospitales(ArrayList<HospitalPorcentaje> topHospitales) {
		this.topHospitales = topHospitales;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	

}
