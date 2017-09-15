package com.meltsan.pdfcreator;

public class Main {

	private static GeneradorReporte generador;
	
	public static void main(String[] args) {
		generador = new GeneradorReporte();
		generador.generarReporte();
	}

}
