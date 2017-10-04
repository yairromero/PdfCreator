package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

public class PadecimientoCronicos {
	
	private ArrayList<PadCronicosMontos> padecimientos;
	private TopPadecimientosCronicos topCronicos;	
	private ArrayList<PadCronicoClienteMercado> clienteMercado;

	public PadecimientoCronicos(ArrayList<PadCronicosMontos> padecimientos,
								TopPadecimientosCronicos topCronicos, 
								ArrayList<PadCronicoClienteMercado> clienteMercado) {
		
		this.padecimientos = padecimientos;
		this.topCronicos = topCronicos;
		this.clienteMercado = clienteMercado;
	}

	public TopPadecimientosCronicos getTopCronicos() {
		return topCronicos;
	}

	public void setTopCronicos(TopPadecimientosCronicos topCronicos) {
		this.topCronicos = topCronicos;
	}

	public ArrayList<PadCronicoClienteMercado> getClienteMercado() {
		return clienteMercado;
	}

	public void setClienteMercado(ArrayList<PadCronicoClienteMercado> clienteMercado) {
		this.clienteMercado = clienteMercado;
	}

	public ArrayList<PadCronicosMontos> getPadecimientos() {
		return padecimientos;
	}

	public void setPadecimientos(ArrayList<PadCronicosMontos> padecimientos) {
		this.padecimientos = padecimientos;
	}

}
