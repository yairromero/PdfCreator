package com.meltsan.pdfcreator.beans;

import com.meltsan.pdfcreator.beans.values.SiniestroRangoTablaValues;

public class SiniestroRangoPeriodo {
	
	private String periodo;
	private SiniestroRangoTablaValues bajas;
	private SiniestroRangoTablaValues altas;
	private SiniestroRangoTablaValues severidades;
	private SiniestroRangoTablaValues catastroficos;
	
	public SiniestroRangoPeriodo(String periodo, SiniestroRangoTablaValues bajas,
								SiniestroRangoTablaValues altas,
								SiniestroRangoTablaValues severidades,
								SiniestroRangoTablaValues catastroficos) {		
		this.periodo = periodo;
		this.bajas = bajas;
		this.altas = altas;
		this.severidades = severidades;
		this.catastroficos = catastroficos;
	}

	
	
	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}



	public SiniestroRangoTablaValues getBajas() {
		return bajas;
	}



	public void setBajas(SiniestroRangoTablaValues bajas) {
		this.bajas = bajas;
	}



	public SiniestroRangoTablaValues getAltas() {
		return altas;
	}



	public void setAltas(SiniestroRangoTablaValues altas) {
		this.altas = altas;
	}



	public SiniestroRangoTablaValues getSeveridades() {
		return severidades;
	}



	public void setSeveridades(SiniestroRangoTablaValues severidades) {
		this.severidades = severidades;
	}



	public SiniestroRangoTablaValues getCatastroficos() {
		return catastroficos;
	}



	public void setCatastroficos(SiniestroRangoTablaValues catastroficos) {
		this.catastroficos = catastroficos;
	}

	
}
