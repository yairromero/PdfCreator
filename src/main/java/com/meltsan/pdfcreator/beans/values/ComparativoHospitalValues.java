package com.meltsan.pdfcreator.beans.values;

public class ComparativoHospitalValues {
	
	private String padecimiento;
	private String hospital;
	private Integer noSiniestros;
	private Integer montoPagdo;
	private Integer costoPromedio;

	public ComparativoHospitalValues(String padecimiento, String hospital, Integer noSiniestros,
								Integer montoPagado, Integer costoPromedio) {
		this.padecimiento = padecimiento;
		this.hospital = hospital;
		this.noSiniestros = noSiniestros;
		this.montoPagdo = montoPagado;
		this.costoPromedio = costoPromedio;
	}

	public String getPadecimiento() {
		return padecimiento;
	}

	public void setPadecimiento(String padecimiento) {
		this.padecimiento = padecimiento;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Integer getNoSiniestros() {
		return noSiniestros;
	}

	public void setNoSiniestros(Integer noSiniestros) {
		this.noSiniestros = noSiniestros;
	}

	public Integer getMontoPagdo() {
		return montoPagdo;
	}

	public void setMontoPagdo(Integer montoPagdo) {
		this.montoPagdo = montoPagdo;
	}

	public Integer getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(Integer costoPromedio) {
		this.costoPromedio = costoPromedio;
	}
}
