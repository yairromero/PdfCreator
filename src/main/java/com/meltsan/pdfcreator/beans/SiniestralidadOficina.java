package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.SiniestralidadOficinaValues;

public class SiniestralidadOficina {
	
	private String oficina;
	private Integer noAsegurados;
	private Float morbilidad;
	private Integer costoPromedio;
	private Integer costoPerCapita;
	private ArrayList<SiniestralidadOficinaValues> estados;
	
	public SiniestralidadOficina(String oficina, Integer noAsegurados, Float morbilidad,
									Integer costoPromedio,Integer costoPerCapita,
									ArrayList<SiniestralidadOficinaValues> estados) {
		this.oficina = oficina;
		this.noAsegurados = noAsegurados;
		this.morbilidad = morbilidad;
		this.costoPromedio = costoPromedio;
		this.costoPerCapita = costoPerCapita;
		this.estados = estados;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public Integer getNoAsegurados() {
		return noAsegurados;
	}

	public void setNoAsegurados(Integer noAsegurados) {
		this.noAsegurados = noAsegurados;
	}

	public Float getMorbilidad() {
		return morbilidad;
	}

	public void setMorbilidad(Float morbilidad) {
		this.morbilidad = morbilidad;
	}

	public Integer getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(Integer costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	public Integer getCostoPerCapita() {
		return costoPerCapita;
	}

	public void setCostoPerCapita(Integer costoPerCapita) {
		this.costoPerCapita = costoPerCapita;
	}

	public ArrayList<SiniestralidadOficinaValues> getEstados() {
		return estados;
	}

	public void setEstados(ArrayList<SiniestralidadOficinaValues> estados) {
		this.estados = estados;
	}

}
