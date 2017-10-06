package com.meltsan.pdfcreator.beans;

public class HospitalPorcentaje {
	
	private String hospital;
	private Float porcentaje;

	public HospitalPorcentaje(String hospital, Float porcentaje) {
		this.hospital = hospital;
		this.porcentaje = porcentaje;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}

}
