package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.ComparativoHospitalValues;

public class ComparativoHospital {
	
	private String texto;
	private ArrayList<ComparativoHospitalValues> hospitales;
	
	public ComparativoHospital(String texto, ArrayList<ComparativoHospitalValues> hospitales) {
		this.texto = texto;
		this.hospitales = hospitales;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<ComparativoHospitalValues> getHospitales() {
		return hospitales;
	}

	public void setHospitales(ArrayList<ComparativoHospitalValues> hospitales) {
		this.hospitales = hospitales;
	}

}
