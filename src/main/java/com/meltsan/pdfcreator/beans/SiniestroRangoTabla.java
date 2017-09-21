package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.SiniestroRangoValuesTabla;

public class SiniestroRangoTabla {

	private ArrayList<SiniestroRangoValuesTabla> bajas;
	private ArrayList<SiniestroRangoValuesTabla> altas;
	private ArrayList<SiniestroRangoValuesTabla> severidades;
	private ArrayList<SiniestroRangoValuesTabla> catastroficos;
	
	public SiniestroRangoTabla(ArrayList<SiniestroRangoValuesTabla> bajas,
								ArrayList<SiniestroRangoValuesTabla> altas,
								ArrayList<SiniestroRangoValuesTabla> severidades,
								ArrayList<SiniestroRangoValuesTabla> catastroficos) {
		this.bajas = bajas;
		this.altas = altas;
		this.severidades = severidades;
		this.catastroficos = catastroficos;
	}

	public ArrayList<SiniestroRangoValuesTabla> getBajas() {
		return bajas;
	}

	public void setBajas(ArrayList<SiniestroRangoValuesTabla> bajas) {
		this.bajas = bajas;
	}

	public ArrayList<SiniestroRangoValuesTabla> getAltas() {
		return altas;
	}

	public void setAltas(ArrayList<SiniestroRangoValuesTabla> altas) {
		this.altas = altas;
	}

	public ArrayList<SiniestroRangoValuesTabla> getSeveridades() {
		return severidades;
	}

	public void setSeveridades(ArrayList<SiniestroRangoValuesTabla> severidades) {
		this.severidades = severidades;
	}

	public ArrayList<SiniestroRangoValuesTabla> getCatastroficos() {
		return catastroficos;
	}

	public void setCatastroficos(ArrayList<SiniestroRangoValuesTabla> catastroficos) {
		this.catastroficos = catastroficos;
	}

}
