package com.meltsan.pdfcreator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.meltsan.pdfcreator.beans.CostoPerCapitaTarifas;
import com.meltsan.pdfcreator.beans.SiniestroRangoGrafica;
import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.beans.values.CausaValues;
import com.meltsan.pdfcreator.beans.values.CostoPromedioSiniestroValues;
import com.meltsan.pdfcreator.beans.values.IndicadoresSiniestroValues;
import com.meltsan.pdfcreator.beans.values.InflacionSSValues;
import com.meltsan.pdfcreator.beans.values.PadecimientosFrecuenciaValues;
import com.meltsan.pdfcreator.beans.values.ParentescoValues;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.values.SexoValues;
import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;
import com.meltsan.pdfcreator.beans.values.TipoPagoValues;

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
	public JRDataSource crearPobHistoricoGraficaDS(ArrayList<PobHistoricaValues> indicadores) {
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
		
		int masterRowNumber = indicadores.size();	         
        String[] columns = new String[masterRowNumber + 1];                
        
        columns[0] = "vigencia";
        for (int i = 1; i <= masterRowNumber; i++) {	
           columns[i] = indicadores.get(i-1).getPeriodo();
        }        
        
        DRDataSource dataSource = new DRDataSource(columns);
		
        ArrayList<String>labels = new ArrayList<String>();
        labels.add("Asegurados");        
        labels.add("");
        labels.add("Prima Neta Anual");        
        labels.add("");
        labels.add("Prima Per Cápita");        
                               
        //Se llena datasource
        for (int i = 0; i < labels.size(); i++) {

           Object[] values = new Object[masterRowNumber+1];
           Object[] variaciones = new Object[masterRowNumber+1];
           Object[] variacionvs1 = new Object[masterRowNumber+1];           
           
           String label = labels.get(i);
           values[0] = label;
            
           if(label.equals("Asegurados")){        	   
        	   		
        	   		for (int j = 1; j <= masterRowNumber; j++) {	
        	   			values[j] = formatoEntero.format(indicadores.get(j-1).getAsegurados()).toString();        	   			
        	   		}
        	   		
        	   		variaciones[0]="% Variación";
        	   		variaciones[1]="";
        	   		for(int j = 1; j < masterRowNumber; j++) {
        	   			        	   			
        	   			Float res = (float) (indicadores.get(j).getAsegurados()-indicadores.get(j-1).getAsegurados())/indicadores.get(j-1).getAsegurados();		
        	   			Float res2 = res * 100.0f;        	   			
        	   			
        	   			variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
        	   		}
        	   		
        	   		variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
        	   		
        	   		Float res = (float) (indicadores.get(masterRowNumber-1).getAsegurados()-indicadores.get(0).getAsegurados())/indicadores.get(0).getAsegurados();		
    	   			Float res2 = res * 100.0f;
    	   			
        	   		variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
        	   		
        	   		dataSource.add(values);
        	   		dataSource.add(variaciones);
        	   		dataSource.add(variacionvs1);
           }
           
           if(label.equals("Prima Neta Anual")){
        	   
   	   			for (int j = 1; j <= masterRowNumber; j++) {	
   	   				values[j] = "$"+formatoEntero.format(indicadores.get(j-1).getPrimaNeta()).toString();
   	   			}
   	   			
   	   			variaciones[0]="% Variación";
   	   			variaciones[1]="";
   	   			for(int j = 1; j < masterRowNumber; j++) {
	   			        	   			
   	   				Float res = (float) (indicadores.get(j).getPrimaNeta()-indicadores.get(j-1).getPrimaNeta())/indicadores.get(j-1).getPrimaNeta();		
   	   				Float res2 = res * 100.0f;        	   			
	   			
   	   				variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
   	   			}
   	   			
   	   			variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
	   		
   	   			Float res = (float) (indicadores.get(masterRowNumber-1).getPrimaNeta()-indicadores.get(0).getPrimaNeta())/indicadores.get(0).getPrimaNeta();		
   	   			Float res2 = res * 100.0f;
   			
   	   			variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
   	   			
   	   			dataSource.add(values);
   	   			dataSource.add(variaciones);
   	   			dataSource.add(variacionvs1);
           }
           
           if(label.equals("Prima Per Cápita")){
  	   			for (int j = 1; j <= masterRowNumber; j++) {	
  	   				values[j] = "$"+formatoEntero.format(indicadores.get(j-1).getPrimaPerCapita()).toString();
  	   			}
  	   			
  	   			variaciones[0]="% Variación";
  	   			variaciones[1]="";
  	   			for(int j = 1; j < masterRowNumber; j++) {
	   			        	   			
  	   				Float res = (float) (indicadores.get(j).getPrimaPerCapita()-indicadores.get(j-1).getPrimaPerCapita())/indicadores.get(j-1).getPrimaPerCapita();		
  	   				Float res2 = res * 100.0f;        	   			
	   			
  	   				variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
  	   			}
  	   			
  	   			variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
	   		
	   			Float res = (float) (indicadores.get(masterRowNumber-1).getPrimaPerCapita()-indicadores.get(0).getPrimaPerCapita())/indicadores.get(0).getPrimaPerCapita();		
	   			Float res2 = res * 100.0f;
			
	   			variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
	   			
	   			dataSource.add(values);
	   			dataSource.add(variaciones);
	   			dataSource.add(variacionvs1); 	 
           }
           
          
           
          }
		
		return dataSource;
	}
	
	/**
	 * Genera datos para crear tabla de Poblacion
	 * historica
	 * @param indicadores lista con objetos PobHistoricaValues 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearIndicadoresSiniestroDS(ArrayList<IndicadoresSiniestroValues> indicadores) {		
		
		int masterRowNumber = indicadores.size();	         
        String[] columns = new String[masterRowNumber + 1];                
        
        columns[0] = "vigencia";
        for (int i = 1; i <= masterRowNumber; i++) {	
           columns[i] = indicadores.get(i-1).getPeriodo();
        }        
        
        DRDataSource dataSource = new DRDataSource(columns);
		
        ArrayList<String>labels = new ArrayList<String>();
        labels.add("Monto Pagado");        
        labels.add("");
        labels.add("No. de Siniestros");        
        labels.add("");
        labels.add("Costo Per cápita de siniestro");
        labels.add("");
        labels.add("% Siniestralidad");
        labels.add("");
        labels.add("%Siniestralidad(sin catastróficos)");
                              
        //Se llena datasource
        for (int i = 0; i < labels.size(); i++) {

           Object[] values = new Object[masterRowNumber+1];
           Object[] variaciones = new Object[masterRowNumber+1];
           Object[] variacionvs1 = new Object[masterRowNumber+1];           
           
           String label = labels.get(i);
           values[0] = label;
            
           if(label.equals("Monto Pagado")){        	   
        	   		
        	   		for (int j = 1; j <= masterRowNumber; j++) {	
        	   			values[j] = formatoEntero.format(indicadores.get(j-1).getMontoPagado()).toString();        	   			
        	   		}
        	   		
        	   		variaciones[0]="% Variación";
        	   		variaciones[1]="";
        	   		for(int j = 1; j < masterRowNumber; j++) {
        	   			        	   			
        	   			Float res = (float) (indicadores.get(j).getMontoPagado()-indicadores.get(j-1).getMontoPagado())/indicadores.get(j-1).getMontoPagado();		
        	   			Float res2 = res * 100.0f;        	   			
        	   			
        	   			variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
        	   		}
        	   		
        	   		variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
        	   		
        	   		Float res = (float) (indicadores.get(masterRowNumber-1).getMontoPagado()-indicadores.get(0).getMontoPagado())/indicadores.get(0).getMontoPagado();		
    	   			Float res2 = res * 100.0f;
    	   			
        	   		variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
        	   		
        	   		dataSource.add(values);
        	   		dataSource.add(variaciones);
        	   		dataSource.add(variacionvs1);
           }
           
           if(label.equals("No. de Siniestros")){
        	   
   	   			for (int j = 1; j <= masterRowNumber; j++) {	
   	   				values[j] = "$"+formatoEntero.format(indicadores.get(j-1).getNoSiniestros()).toString();
   	   			}
   	   			
   	   			variaciones[0]="% Variación";
   	   			variaciones[1]="";
   	   			for(int j = 1; j < masterRowNumber; j++) {
	   			        	   			
   	   				Float res = (float) (indicadores.get(j).getNoSiniestros()-indicadores.get(j-1).getNoSiniestros())/indicadores.get(j-1).getNoSiniestros();		
   	   				Float res2 = res * 100.0f;        	   			
	   			
   	   				variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
   	   			}
   	   			
   	   			variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
	   		
   	   			Float res = (float) (indicadores.get(masterRowNumber-1).getNoSiniestros()-indicadores.get(0).getNoSiniestros())/indicadores.get(0).getNoSiniestros();		
   	   			Float res2 = res * 100.0f;
   			
   	   			variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
   	   			
   	   			dataSource.add(values);
   	   			dataSource.add(variaciones);
   	   			dataSource.add(variacionvs1);
           }
           
           if(label.equals("Costo Per cápita de siniestro")){
  	   			for (int j = 1; j <= masterRowNumber; j++) {	
  	   				values[j] = "$"+formatoEntero.format(indicadores.get(j-1).getCostoPerCapita()).toString();
  	   			}
  	   			
  	   			variaciones[0]="% Variación";
  	   			variaciones[1]="";
  	   			for(int j = 1; j < masterRowNumber; j++) {
	   			        	   			
  	   				Float res = (float) (indicadores.get(j).getCostoPerCapita()-indicadores.get(j-1).getCostoPerCapita())/indicadores.get(j-1).getCostoPerCapita();		
  	   				Float res2 = res * 100.0f;        	   			
	   			
  	   				variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
  	   			}
  	   			
  	   			variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
	   		
	   			Float res = (float) (indicadores.get(masterRowNumber-1).getCostoPerCapita()-indicadores.get(0).getCostoPerCapita())/indicadores.get(0).getCostoPerCapita();		
	   			Float res2 = res * 100.0f;
			
	   			variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
	   			
	   			dataSource.add(values);
	   			dataSource.add(variaciones);
	   			dataSource.add(variacionvs1); 	 
           }
           
           if(label.equals("% Siniestralidad")){
 	   			for (int j = 1; j <= masterRowNumber; j++) {	
 	   				values[j] = formatoEntero.format(indicadores.get(j-1).getPorcentajeSiniestralidad()).toString() + "%";
 	   			}
 	   			
 	   			variaciones[0]="% Variación";
 	   			variaciones[1]="";
 	   			for(int j = 1; j < masterRowNumber; j++) {
	   			        	   			
 	   				Float res = (float) (indicadores.get(j).getPorcentajeSiniestralidad()-indicadores.get(j-1).getPorcentajeSiniestralidad())
 	   																								/indicadores.get(j-1).getPorcentajeSiniestralidad();		
 	   				Float res2 = res * 100.0f;        	   			
	   			
 	   				variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
 	   			}
 	   			
 	   			variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
	   		
	   			Float res = (float) (indicadores.get(masterRowNumber-1).getPorcentajeSiniestralidad()-indicadores.get(0).getPorcentajeSiniestralidad())
	   																											/indicadores.get(0).getPorcentajeSiniestralidad();		
	   			Float res2 = res * 100.0f;
			
	   			variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
	   			
	   			dataSource.add(values);
	   			dataSource.add(variaciones);
	   			dataSource.add(variacionvs1); 	 
          }
           
           if(label.equals("%Siniestralidad(sin catastróficos)")){
 	   			for (int j = 1; j <= masterRowNumber; j++) {	
 	   				
 	   				Float x = indicadores.get(j-1).getPorcienSiniestroSinCatastrofe();
 	   				if ( x != null)
 	   					values[j] = formatoEntero.format(x).toString() + "%";
 	   			}
 	   			
 	   			variaciones[0]="% Variación";
 	   			variaciones[1]="";
 	   			for(int j = 1; j < masterRowNumber; j++) {
	   			        	   			
 	   				Float x = indicadores.get(j).getPorcienSiniestroSinCatastrofe();
 	   				
 	   				if(x!=null){
 	   					Float res = (float) (indicadores.get(j).getPorcienSiniestroSinCatastrofe()-indicadores.get(j-1).getPorcienSiniestroSinCatastrofe())
 	   																								/indicadores.get(j-1).getPorcienSiniestroSinCatastrofe();		
 	   					Float res2 = res * 100.0f;        	   				   			
 	   					variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
 	   				}
 	   			}
 	   			
 	   			variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
 	   			
 	   			Float x = indicadores.get(masterRowNumber-1).getPorcienSiniestroSinCatastrofe();
 	   			
 	   			if(x!=null) {
 	   				Float res = (float) (indicadores.get(masterRowNumber-1).getPorcienSiniestroSinCatastrofe()-indicadores.get(0).getPorcienSiniestroSinCatastrofe())	   																										/indicadores.get(0).getPorcienSiniestroSinCatastrofe();		
	   				Float res2 = res * 100.0f;			
	   				variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
 	   			}
 	   			else {
 	   			variacionvs1[0]="%Variación año "+(masterRowNumber-1)+" Vs año 1";
 	   				Float res = (float) (indicadores.get(masterRowNumber-2).getPorcienSiniestroSinCatastrofe()-indicadores.get(0).getPorcienSiniestroSinCatastrofe())	   																										/indicadores.get(0).getPorcienSiniestroSinCatastrofe();		
 	   				Float res2 = res * 100.0f;			
 	   				variacionvs1[variacionvs1.length - 2]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
 	   			}
	   			dataSource.add(values);
	   			dataSource.add(variaciones);
	   			dataSource.add(variacionvs1); 	 
          }
           
           if(label.equals("")){
        	   		for (int j = 0; j <= masterRowNumber; j++) {
        	   			values[j] = "";
        	   		}
        	   		dataSource.add(values);
           }
                               
          }
		
		return dataSource;
	}
	
	/**
	 * Genera datos para crear grafica de Siniestralidad
	 * per capita
	 * @param indicadores lista con objetos PerCapita 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearIndicadoresSiniestroGraficaDS(ArrayList<IndicadoresSiniestroValues> indicadores) {
		DRDataSource dataSource = new DRDataSource("periodo", "costo","prima");
		
		for(IndicadoresSiniestroValues pc : indicadores) {
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
	 * Genera datos para llenar grafica de Costo
	 * Promedio de Siniestros
	 * 	
	 * @param siniestros Lista con objetos tipo CostoPromedioSiniestroValues
	 * @return JRDataSource para alimentar tabla
	 */
	public JRDataSource crearCostoPromedioGraficaDS (ArrayList<CostoPromedioSiniestroValues> siniestros) {
		
		DRDataSource dataSource = new DRDataSource("periodo", "costo", "costosinc","costoinfl");
		
		for(CostoPromedioSiniestroValues pc : siniestros) {
			dataSource.add(pc.getPeriodo(),pc.getCostoPromedio(),pc.getCostoPromSinCatastrofe(),pc.getCostoPromInflacionSS());
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar tabla de Costo
	 * Promedio de Siniestros
	 * 	
	 * @param siniestros Lista con objetos tipo CostoPromedioSiniestroValues
	 * @return JRDataSource para alimentar tabla
	 */
	public JRDataSource crearCostoPromedioTablaDS (ArrayList<CostoPromedioSiniestroValues> siniestros) {
		
		int masterRowNumber = siniestros.size();	         
        String[] columns = new String[masterRowNumber + 1];                
        
        columns[0] = "vigencia";
        for (int i = 1; i <= masterRowNumber; i++) {	
           columns[i] = siniestros.get(i-1).getPeriodo();
        }        
        
        DRDataSource dataSource = new DRDataSource(columns);
		
        ArrayList<String>labels = new ArrayList<String>();
        labels.add("Costo Promedio de siniestro");        
        labels.add("");        
        labels.add("Costo Promedio de siniestro (sin catastróficos)");        
       
                              
        //Se llena datasource
        for (int i = 0; i < labels.size(); i++) {

           Object[] values = new Object[masterRowNumber+1];
           Object[] variaciones = new Object[masterRowNumber+1];
           Object[] variacionvs1 = new Object[masterRowNumber+1];           
           
           String label = labels.get(i);
           values[0] = label;
            
           if(label.equals("Costo Promedio de siniestro")){        	   
        	   		
        	   		for (int j = 1; j <= masterRowNumber; j++) {	
        	   			values[j] = formatoEntero.format(siniestros.get(j-1).getCostoPromedio()).toString();        	   			
        	   		}
        	   		
        	   		variaciones[0]="% Variación";
        	   		variaciones[1]="";
        	   		for(int j = 1; j < masterRowNumber; j++) {
        	   			        	   			
        	   			Float res = (float) (siniestros.get(j).getCostoPromedio()-siniestros.get(j-1).getCostoPromedio())/siniestros.get(j-1).getCostoPromedio();		
        	   			Float res2 = res * 100.0f;        	   			
        	   			
        	   			variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
        	   		}
        	   		
        	   		variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
        	   		
        	   		Float res = (float) (siniestros.get(masterRowNumber-1).getCostoPromedio()-siniestros.get(0).getCostoPromedio())/siniestros.get(0).getCostoPromedio();		
    	   			Float res2 = res * 100.0f;
    	   			
        	   		variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
        	   		
        	   		dataSource.add(values);
        	   		dataSource.add(variaciones);
        	   		dataSource.add(variacionvs1);
           }
           
           if(label.equals("Costo Promedio de siniestro (sin catastróficos)")){        	   
   	   		
   	   		for (int j = 1; j <= masterRowNumber; j++) {	
   	   			values[j] = formatoEntero.format(siniestros.get(j-1).getCostoPromSinCatastrofe()).toString();        	   			
   	   		}
   	   		
   	   		variaciones[0]="% Variación";
   	   		variaciones[1]="";
   	   		for(int j = 1; j < masterRowNumber; j++) {
   	   			        	   			
   	   			Float res = (float) (siniestros.get(j).getCostoPromSinCatastrofe()-siniestros.get(j-1).getCostoPromSinCatastrofe())/siniestros.get(j-1).getCostoPromSinCatastrofe();		
   	   			Float res2 = res * 100.0f;        	   			
   	   			
   	   			variaciones[j+1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
   	   		}
   	   		
   	   		variacionvs1[0]="%Variación año "+masterRowNumber+" Vs año 1";
   	   		
   	   		Float res = (float) (siniestros.get(masterRowNumber-1).getCostoPromSinCatastrofe()-siniestros.get(0).getCostoPromSinCatastrofe())/siniestros.get(0).getCostoPromSinCatastrofe();		
	   			Float res2 = res * 100.0f;
	   			
   	   		variacionvs1[variacionvs1.length - 1]=formatoEntero.format(Math.round(res2 * 100.0) / 100.0) +"%";
   	   		
   	   		dataSource.add(values);
   	   		dataSource.add(variaciones);
   	   		dataSource.add(variacionvs1);
           }                       	   			
           
           if(label.equals("")){
        	   		for (int j = 0; j <= masterRowNumber; j++) {
        	   			values[j] = "";
        	   		}
        	   		dataSource.add(values);
           }
                               
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
	
	/**
	 * Genera datos para llenar grafica Tipo de Pago 	
	 * @param montos Lista de objetos tipo TipoPagoValues	 	 
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearMontosPagadosTPDS(ArrayList<TipoPagoValues> montos) {
		
		DRDataSource dataSource = new DRDataSource("periodo","pagodirecto","reembolso");			
		for(TipoPagoValues tpv: montos) {
			dataSource.add(tpv.getPeriodo(),tpv.getMontoPagoDirecto(),tpv.getMontoReembolso());
		}		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar grafica Tipo de Pago 	
	 * @param montos Lista de objetos tipo TipoPagoValues	 	 
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearMontosPagadosTipoPagoDS(ArrayList<TipoPagoValues> montos) {
		
		DRDataSource dataSource = new DRDataSource("periodo","pagodirecto","reembolso");			
		for(TipoPagoValues tpv: montos) {
			dataSource.add(tpv.getPeriodo(),tpv.getMontoPagoDirecto(),tpv.getMontoReembolso());
		}		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar grafica Tipo de Pago 	
	 * @param montos Lista de objetos tipo TipoPagoValues	 	 
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearMontosPagadosCausaDS(ArrayList<CausaValues> montos) {
		
		DRDataSource dataSource = new DRDataSource("periodo","accidente","enfermedad","parto");			
		for(CausaValues tpv: montos) {
			dataSource.add(tpv.getPeriodo(),tpv.getMontoAccidente(),tpv.getMontoEnfermedad(),tpv.getMontoParto());
		}		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar grafica Tipo de Pago 	
	 * @param montos Lista de objetos tipo TipoPagoValues	 	 
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearMontosPagadosSexoDS(ArrayList<SexoValues> montos) {
		
		DRDataSource dataSource = new DRDataSource("periodo","femenino","masculino");			
		for(SexoValues tpv: montos) {
			dataSource.add(tpv.getPeriodo(),tpv.getMontoFemenino(),tpv.getMontoMasculino());
		}		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar grafica Tipo de Pago 	
	 * @param montos Lista de objetos tipo TipoPagoValues	 	 
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearMontosPagadosParentescoDS(ArrayList<ParentescoValues> montos) {
		
		DRDataSource dataSource = new DRDataSource("periodo","titular","dependiente");			
		for(ParentescoValues tpv: montos) {
			dataSource.add(tpv.getPeriodo(),tpv.getMontoTitular(),tpv.getMontoDependiente());
		}		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar grafica de Costo per capita vs tarifas 	
	 * @param montos Lista de objetos tipo CostoPerCapitaTarifas	 	 
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearCostoVsTarifasDS(ArrayList<CostoPerCapitaTarifas> montos) {
		
		DRDataSource dataSource = new DRDataSource("periodo","costopc","tarifas");
		for(CostoPerCapitaTarifas cpc: montos){
			dataSource.add(cpc.getGrupoEdad(),cpc.getCosto(),cpc.getTarifas());
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar grafica de Morbilidad	
	 * @param montos Lista de objetos tipo CostoPerCapitaTarifas	 	 
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearMorbilidadDS(ArrayList<CostoPerCapitaTarifas> montos) {
		
		DRDataSource dataSource = new DRDataSource("periodo","morbilidad");
		for(CostoPerCapitaTarifas cpc: montos){
			dataSource.add(cpc.getGrupoEdad(),cpc.getMorbilidad());
		}
		
		return dataSource;
	}
	
}
