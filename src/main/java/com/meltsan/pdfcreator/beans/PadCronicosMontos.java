package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.PadecimientoCronicoValues;

public class PadCronicosMontos {

	private String periodo;
	private ArrayList<PadecimientoCronicoValues> padecimientos;
	
	public PadCronicosMontos(String periodo, ArrayList<PadecimientoCronicoValues> padecimientos) {
		this.periodo = periodo;
		this.padecimientos = padecimientos;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public ArrayList<PadecimientoCronicoValues> getPadecimientos() {
		return padecimientos;
	}

	public void setPadecimientos(ArrayList<PadecimientoCronicoValues> padecimientos) {
		this.padecimientos = padecimientos;
	}
}
