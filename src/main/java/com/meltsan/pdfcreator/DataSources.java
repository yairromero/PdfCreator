package com.meltsan.pdfcreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.meltsan.pdfcreator.beans.InflacionSS;
import com.meltsan.pdfcreator.beans.InflacionSSValues;
import com.meltsan.pdfcreator.beans.PerCapitaValues;
import com.meltsan.pdfcreator.beans.PobHistoricaValues;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class DataSources {
	
	/**
	 * Genera datos para llenar tabla de antecedentes
	 * 	
	 * @param vigencias Mapa con perido y vigencias
	 * @return JRDataSource para alimentar tabla
	 */
	public JRDataSource crearAntecedentesDS(HashMap<String,String> vigencias) {		
	       DRDataSource dataSource = new DRDataSource("vigencia", "periodo");		
	       Iterator<Entry<String, String>> itAnte = vigencias.entrySet().iterator();
	       while (itAnte.hasNext()) {
	    	   		Map.Entry<String, String> entry = itAnte.next();
	    	   		dataSource.add(entry.getKey(),entry.getValue());
	       } 
	       return dataSource;
	    }
	
	/**
	 * Genera datos para crear grafica de Poblacion
	 * historica
	 * @param indicadores lista con objetos PobHistoricaValues 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearPobHistoricoDS(ArrayList<PobHistoricaValues> indicadores) {
		DRDataSource dataSource = new DRDataSource("periodo", "asegurados");
		
		for(PobHistoricaValues pc : indicadores) {
			dataSource.add(pc.getPeriodo(),pc.getAsegurados());
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para crear tabla de Poblacion
	 * historica
	 * @param indicadores lista con objetos PobHistoricaValues 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearPobHistoricoTablaDS(ArrayList<PobHistoricaValues> indicadores) {
		DRDataSource dataSource = new DRDataSource("periodo", "asegurados","primaneta","primapercapita");
		
		for(PobHistoricaValues pc : indicadores) {
			dataSource.add(pc.getPeriodo(),pc.getAsegurados(),pc.getPrimaNeta(),pc.getPrimaPerCapita());
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para crear grafica de Siniestralidad
	 * per capita
	 * @param indicadores lista con objetos PerCapita 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearPerCapitaDS(ArrayList<PerCapitaValues> indicadores) {
		DRDataSource dataSource = new DRDataSource("periodo", "costo","prima");
		
		for(PerCapitaValues pc : indicadores) {
			dataSource.add(pc.getPeriodo(),pc.getCostoPerCapita(),pc.getPrimaPerCapita());
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar datos de inflacion
	 * Sector Salud
	 * 	
	 * @param periodos Mapa con perido e indices de inflacion
	 * @return JRDataSource para alimentar tabla
	 */
	public JRDataSource crearInflacionSSDS(ArrayList<InflacionSSValues> indicadores) {
		DRDataSource dataSource = new DRDataSource("periodo", "indice");
		
		for(InflacionSSValues pc : indicadores) {
			dataSource.add(pc.getPerido(),pc.getInflacion());
		}
		
		return dataSource;
	}	
	
	/**
	 * Genera datos para llenar datos de inflacion
	 * Sector Salud
	 * 	
	 * @param periodos Mapa con perido e indices de inflacion
	 * @return JRDataSource para alimentar tabla
	 */
	
}