package com.meltsan.pdfcreator.beans;

import java.util.HashMap;

public class Antecedentes {
	
	private String vigenciaCompleta;
	private String periodoAnalisis;
	private HashMap<String,String> tablaVigencias;
	
	public Antecedentes(String vigencia,String periodo, HashMap<String,String> tabla){
		this.vigenciaCompleta = vigencia;
		this.periodoAnalisis = periodo;
		this.tablaVigencias = tabla;
	}
	
	public String getVigenciaCompleta() {
		return vigenciaCompleta;
	}
	
	public void setVigenciaCompleta(String vigenciaCompleta) {
		this.vigenciaCompleta = vigenciaCompleta;
	}
	
	public String getPeriodoAnalisis() {
		return periodoAnalisis;
	}
	
	public void setPeriodoAnalisis(String periodoAnalisis) {
		this.periodoAnalisis = periodoAnalisis;
	}
	
	public HashMap<String, String> getTablaVigencias() {
		return tablaVigencias;
	}
	
	public void setTablaVigencias(HashMap<String, String> tablaVigencias) {
		this.tablaVigencias = tablaVigencias;
	} 
	
	
}
