package com.meltsan.pdfcreator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.meltsan.pdfcreator.beans.PadecimientosFrecuencia;
import com.meltsan.pdfcreator.beans.SiniestroRangoGrafica;
import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.beans.values.InflacionSSValues;
import com.meltsan.pdfcreator.beans.values.PadecimientosFrecuenciaValues;
import com.meltsan.pdfcreator.beans.values.PerCapitaValues;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class DataSources {
	
	private NumberFormat formatoEntero = NumberFormat.getInstance(Locale.US);
	private NumberFormat formatoCurrency = NumberFormat.getCurrencyInstance(Locale.US);
	
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
	 * Genera datos para crear reporte de Siniestros
	 * Padecimiento
	 * @param indicadores lista con objetos SiniestroPadecimientoValues 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearSiniestroPadecimientoDS(ArrayList<SiniestroPadecimientoValues> siniestros) {
		DRDataSource dataSource = new DRDataSource("siniestro", "padecimiento");
		
		for(SiniestroPadecimientoValues pc : siniestros) {
			dataSource.add(pc.getSiniestro(),pc.getPadecimiento());
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para crear grafica de Siniestros
	 * por rango de monto pagado
	 * @param indicadores lista con objetos SiniestroRangoValues 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearSiniestroRangoDS(ArrayList<SiniestroRangoGrafica> indicadores) {
		DRDataSource dataSource = new DRDataSource("periodo", "baja","alta","severa","catastrofe");
		
		for(SiniestroRangoGrafica pc : indicadores) {
			dataSource.add(pc.getPeriodo(),pc.getBaja(),pc.getAlta(),pc.getSevera(),pc.getCatastrofe());
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para crear tabla de Siniestros
	 * por rango de monto pagado
	 * @param indicadores lista con objetos SiniestroRangoTabla 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearSiniestroRangoDSTabla(ArrayList<SiniestroRangoPeriodo> periodoSinRango) {
				
        int masterRowNumber = periodoSinRango.size();	         
        String[] columns = new String[masterRowNumber + 2];                
        
        columns[0] = "vigencia";
        for (int i = 1; i <= masterRowNumber; i++) {	
           columns[i] = periodoSinRango.get(i-1).getPeriodo();
        }
        columns[masterRowNumber+1]="categoria";
        
        DRDataSource dataSource = new DRDataSource(columns);
	
        ArrayList<String>labels = new ArrayList<String>();
        labels.add("Monto pagado");
        labels.add("No. de Siniestro");
        labels.add("Costo per cápita");
        labels.add("Costo promedio");
        labels.add("% Representación");
                
        //Se llena datasource con datos de Catastrofico
        for (int i = 0; i < labels.size(); i++) {

           Object[] values = new Object[masterRowNumber+2];
           String label = labels.get(i); 
           values[0] = label;
           
           if(label.equals("Monto pagado")){
           		for (int j = 1; j <= masterRowNumber; j++) {	
           			values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getCatastroficos().getMontoPagado()).toString();	
           		}	
           }
           
           if(label.equals("No. de Siniestro")){
       			for (int j = 1; j <= masterRowNumber; j++) {	
       				values[j] = formatoEntero.format(periodoSinRango.get(j-1).getCatastroficos().getNoSiniestro()).toString();	
       			}	
           }	            	            
           
           if(label.equals("Costo per cápita")){
   				for (int j = 1; j <= masterRowNumber; j++) {	
   					values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getCatastroficos().getCostoPerCapita()).toString();	
   				}	
           }
           
           if(label.equals("Costo promedio")){
   				for (int j = 1; j <= masterRowNumber; j++) {	
   					values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getCatastroficos().getCostoPromedio()).toString();	
   				}	
           }
           
           if(label.equals("% Representación")){
					for (int j = 1; j <= masterRowNumber; j++) {	
						values[j] = formatoEntero.format(periodoSinRango.get(j-1).getCatastroficos().getRepresentacion()).toString() + "%";	
					}	
           }
           
           values[masterRowNumber+1]= "CATASTRÓFICO ($400,001 o más)";
           dataSource.add(values);	
        }
        
        //Se llena datasource con datos de Severidad
        for (int i = 0; i < labels.size(); i++) {

           Object[] values = new Object[masterRowNumber+2];
           String label = labels.get(i); 
           values[0] = label;
           
           if(label.equals("Monto pagado")){
           		for (int j = 1; j <= masterRowNumber; j++) {	
           			values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getSeveridades().getMontoPagado()).toString();	
           		}	
           }
           
           if(label.equals("No. de Siniestro")){
       			for (int j = 1; j <= masterRowNumber; j++) {	
       				values[j] = formatoEntero.format(periodoSinRango.get(j-1).getSeveridades().getNoSiniestro()).toString();	
       			}	
           }	            	            
           
           if(label.equals("Costo per cápita")){
   				for (int j = 1; j <= masterRowNumber; j++) {	
   					values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getSeveridades().getCostoPerCapita()).toString();	
   				}	
           }
           
           if(label.equals("Costo promedio")){
   				for (int j = 1; j <= masterRowNumber; j++) {	
   					values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getSeveridades().getCostoPromedio()).toString();	
   				}	
           }
           
           if(label.equals("% Representación")){
					for (int j = 1; j <= masterRowNumber; j++) {	
						values[j] = formatoEntero.format(periodoSinRango.get(j-1).getSeveridades().getRepresentacion()).toString() + "%";	
					}	
           }
           
           values[masterRowNumber+1]= "SEVERIDAD ($150,001-$400,000)";
           dataSource.add(values);	
        }
        
        //Se llena datasource con datos de Frecuencia Alta
        for (int i = 0; i < labels.size(); i++) {

            Object[] values = new Object[masterRowNumber+2];
            String label = labels.get(i); 
            values[0] = label;
            
            if(label.equals("Monto pagado")){
            		for (int j = 1; j <= masterRowNumber; j++) {	
            			values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getAltas().getMontoPagado()).toString();	
            		}	
            }
            
            if(label.equals("No. de Siniestro")){
        			for (int j = 1; j <= masterRowNumber; j++) {	
        				values[j] = formatoEntero.format(periodoSinRango.get(j-1).getAltas().getNoSiniestro()).toString();	
        			}	
            }	            	            
            
            if(label.equals("Costo per cápita")){
    				for (int j = 1; j <= masterRowNumber; j++) {	
    					values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getAltas().getCostoPerCapita()).toString();	
    				}	
            }
            
            if(label.equals("Costo promedio")){
    				for (int j = 1; j <= masterRowNumber; j++) {	
    					values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getAltas().getCostoPromedio()).toString();	
    				}	
            }
            
            if(label.equals("% Representación")){
 					for (int j = 1; j <= masterRowNumber; j++) {	
 						values[j] = formatoEntero.format(periodoSinRango.get(j-1).getAltas().getRepresentacion()).toString() + "%";	
 					}	
            }
            
            values[masterRowNumber+1]= "FRECUENCIA ALTA ($50,001-$150,000)";
            dataSource.add(values);	
         }
        
        //Se llena datasource con datos de Frecuencia Baja
        for (int i = 0; i < labels.size(); i++) {

           Object[] values = new Object[masterRowNumber+2];
           String label = labels.get(i); 
           values[0] = label;
           
           if(label.equals("Monto pagado")){
           		for (int j = 1; j <= masterRowNumber; j++) {	
           			values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getBajas().getMontoPagado()).toString();	
           		}	
           }
           
           if(label.equals("No. de Siniestro")){
       			for (int j = 1; j <= masterRowNumber; j++) {	
       				values[j] = formatoEntero.format(periodoSinRango.get(j-1).getBajas().getNoSiniestro()).toString();	
       			}	
           }	            	            
           
           if(label.equals("Costo per cápita")){
   				for (int j = 1; j <= masterRowNumber; j++) {	
   					values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getBajas().getCostoPerCapita()).toString();	
   				}	
           }
           
           if(label.equals("Costo promedio")){
   				for (int j = 1; j <= masterRowNumber; j++) {	
   					values[j] = formatoCurrency.format(periodoSinRango.get(j-1).getBajas().getCostoPromedio()).toString();	
   				}	
           }
           
           if(label.equals("% Representación")){
					for (int j = 1; j <= masterRowNumber; j++) {	
						values[j] = formatoEntero.format(periodoSinRango.get(j-1).getBajas().getRepresentacion()).toString()+"%";	
					}	
           }
           
           values[masterRowNumber+1]= "FRECUENCIA BAJA ($0-$50,000)";
           dataSource.add(values);	
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
		DRDataSource dataSource = new DRDataSource("periodo", "asegurados","variacionA",
				"variacionvsA","primaneta","variacionPN","variacionvsPN","primapercapita",
				"variacionPC","variacionvsPC");
		
		for(PobHistoricaValues pc : indicadores) {
			dataSource.add(pc.getPeriodo(),pc.getAsegurados(),pc.getVariacionAsegurados(),
					pc.getVariacionVs1Asegurados(),pc.getPrimaNeta(),pc.getVariacionPrimaNeta(),
					pc.getVariacionVs1PrimaNeta(),pc.getPrimaPerCapita(),pc.getVariacionPerCapita(),
					pc.getVariacionVs1PerCapita());
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
	 * Genera datos para llenar tabla del reporte
	 * Siniestros Mayores
	 * 	
	 * @param siniestros Lista de objetos tipo SiniestrosMayores
	 * @return JRDataSource para alimentar tabla
	 */
	public JRDataSource crearSiniestrosMayoresDS(ArrayList<SiniestrosMayores> siniestros) {
		
		int masterRowNumber = siniestros.size();	         
        String[] columns = new String[masterRowNumber + 2];                
        
        columns[0] = "siniestro";
        columns[1] = "padecimiento";
        for (int i = 2; i <= masterRowNumber; i++) {	
           columns[i] = siniestros.get(i-2).getPeriodo();
        }        
        
        DRDataSource dataSource = new DRDataSource(columns);

        
		
		return dataSource;
	}	
	
	/**
	 * Genera datos para llenar graficas reporte
	 * Top 5 Padecimientos por Frecuencia
	 * 	
	 * @param padecimientos Lista de objetos tipo PadecimientosFrecuencia
	 * @param tipoGrafica Entero donde 1 es la grafica de padecimientos totales
	 * y 2 es la tabla de padecimientos especifica
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearPadecimientoFrecuenciaDS(ArrayList<PadecimientosFrecuenciaValues> padecimientos, int tipoGrafica) {
		
		DRDataSource dataSource = new DRDataSource("padecimiento","frecuencia");
		
		switch(tipoGrafica){
		case 1:
			
			float porcentajePadecimientos = 0;
			for(PadecimientosFrecuenciaValues pad: padecimientos){
				porcentajePadecimientos += pad.getPorcentajeFrecuencia();
			}
			
			dataSource.add("Top "+padecimientos.size()+" Padecimientos",(porcentajePadecimientos));
			dataSource.add("Resto de Padecmientos",(100-porcentajePadecimientos));			
			
			break;
			
		case 2:
			
			for(PadecimientosFrecuenciaValues pad: padecimientos){
				dataSource.add(pad.getPadecimiento(),pad.getPorcentajeFrecuencia());
			}
			
			break;
		default:
			dataSource.add("Grafica no valida",100);
			break;
		}
		
		return dataSource;
	}
	
}
