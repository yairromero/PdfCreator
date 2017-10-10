package com.meltsan.pdfcreator.beans.values;

public class TiempoRespuesta {
	
	private Integer tiempoRespuesta;
	private String mes;
	private Integer siniestros;
	private Integer porcentajeAcumulado;

	public TiempoRespuesta(Integer tiempoRespuesta, String mes, Integer siniestros, Integer porcentajeAcumulado) {
		this.tiempoRespuesta = tiempoRespuesta;
		this.mes = mes;
		this.siniestros = siniestros;
		this.porcentajeAcumulado = porcentajeAcumulado;
	}

	public Integer getTiempoRespuesta() {
		return tiempoRespuesta;
	}

	public void setTiempoRespuesta(Integer tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(Integer siniestros) {
		this.siniestros = siniestros;
	}

	public Integer getPorcentajeAcumulado() {
		return porcentajeAcumulado;
	}

	public void setPorcentajeAcumulado(Integer porcentajeAcumulado) {
		this.porcentajeAcumulado = porcentajeAcumulado;
	}

}
