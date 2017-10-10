package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.SiniestralidadEsperadaValues;

public class SiniestralidadEsperada {
	
	private String textoHeader;
	private String textoNota;
	private Integer desviacionEstandar;
	private ArrayList<SiniestralidadEsperadaValues> siniestralidadHistorica;
	private ArrayList<SiniestralidadEsperadaValues> siniestralidadPronostico;
	
	public SiniestralidadEsperada(String textoHeader, String textoNota, Integer desviacionEstandar,
			 						ArrayList<SiniestralidadEsperadaValues> siniestralidadHistorica,
			 						ArrayList<SiniestralidadEsperadaValues> siniestralidadPronostico) {
		this.textoHeader = textoHeader;
		this.textoNota = textoNota;
		this.desviacionEstandar = desviacionEstandar;
		this.siniestralidadHistorica = siniestralidadHistorica;
		this.siniestralidadPronostico = siniestralidadPronostico;
		
	}

	public String getTextoHeader() {
		return textoHeader;
	}

	public void setTextoHeader(String textoHeader) {
		this.textoHeader = textoHeader;
	}

	public String getTextoNota() {
		return textoNota;
	}

	public void setTextoNota(String textoNota) {
		this.textoNota = textoNota;
	}

	public Integer getDesviacionEstandar() {
		return desviacionEstandar;
	}

	public void setDesviacionEstandar(Integer desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}

	public ArrayList<SiniestralidadEsperadaValues> getSiniestralidadHistorica() {
		return siniestralidadHistorica;
	}

	public void setSiniestralidadHistorica(ArrayList<SiniestralidadEsperadaValues> siniestralidadHistorica) {
		this.siniestralidadHistorica = siniestralidadHistorica;
	}

	public ArrayList<SiniestralidadEsperadaValues> getSiniestralidadPronostico() {
		return siniestralidadPronostico;
	}

	public void setSiniestralidadPronostico(ArrayList<SiniestralidadEsperadaValues> siniestralidadPronostico) {
		this.siniestralidadPronostico = siniestralidadPronostico;
	}

}
