package com.meltsan.pdfcreator.beans;

public class MisionObjetivo {

	private String misionTitulo;
	private String mision;
	private String objetivoTitulo;
	private String objetivo;
	
	public MisionObjetivo(String misionTitulo, String mision, String objetivoTitulo, String objetivo) {
		this.misionTitulo = misionTitulo;
		this.mision = mision;
		this.objetivoTitulo = objetivoTitulo;
		this.objetivo = objetivo;
	}

	public String getMisionTitulo() {
		return misionTitulo;
	}

	public void setMisionTitulo(String misionTitulo) {
		this.misionTitulo = misionTitulo;
	}

	public String getMision() {
		return mision;
	}

	public void setMision(String mision) {
		this.mision = mision;
	}

	public String getObjetivoTitulo() {
		return objetivoTitulo;
	}

	public void setObjetivoTitulo(String objetivoTitulo) {
		this.objetivoTitulo = objetivoTitulo;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

}
