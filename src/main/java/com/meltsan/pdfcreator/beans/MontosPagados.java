package com.meltsan.pdfcreator.beans;

import java.util.ArrayList;

import com.meltsan.pdfcreator.beans.values.CausaValues;
import com.meltsan.pdfcreator.beans.values.ParentescoValues;
import com.meltsan.pdfcreator.beans.values.SexoValues;
import com.meltsan.pdfcreator.beans.values.TipoPagoValues;

public class MontosPagados {

	private ArrayList<TipoPagoValues> montoTipoPago;
	private ArrayList<CausaValues> montoCausa;
	private ArrayList<SexoValues> montoSexo;
	private ArrayList<ParentescoValues> montoParentesco;
	
	public MontosPagados(ArrayList<TipoPagoValues> montoTipoPago, 
							ArrayList<CausaValues> montoCausa,
							ArrayList<SexoValues> montoSexo,
							ArrayList<ParentescoValues> montoParentesco) {
		
		this.montoTipoPago = montoTipoPago;
		this.montoCausa = montoCausa;
		this.montoSexo = montoSexo;
		this.montoParentesco = montoParentesco;
		
	}

	public ArrayList<TipoPagoValues> getMontoTipoPago() {
		return montoTipoPago;
	}

	public void setMontoTipoPago(ArrayList<TipoPagoValues> montoTipoPago) {
		this.montoTipoPago = montoTipoPago;
	}

	public ArrayList<CausaValues> getMontoCausa() {
		return montoCausa;
	}

	public void setMontoCausa(ArrayList<CausaValues> montoCausa) {
		this.montoCausa = montoCausa;
	}

	public ArrayList<SexoValues> getMontoSexo() {
		return montoSexo;
	}

	public void setMontoSexo(ArrayList<SexoValues> montoSexo) {
		this.montoSexo = montoSexo;
	}

	public ArrayList<ParentescoValues> getMontoParentesco() {
		return montoParentesco;
	}

	public void setMontoParentesco(ArrayList<ParentescoValues> montoParentesco) {
		this.montoParentesco = montoParentesco;
	}
	
}
