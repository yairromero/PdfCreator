package com.meltsan.pdfcreator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.meltsan.pdfcreator.beans.ConceptoMonto;
import com.meltsan.pdfcreator.beans.CostoPerCapitaTarifas;
import com.meltsan.pdfcreator.beans.HospitalPorcentaje;
import com.meltsan.pdfcreator.beans.IndicadoresOficina;
import com.meltsan.pdfcreator.beans.PadCronicoClienteMercado;
import com.meltsan.pdfcreator.beans.PadCronicosMontos;
import com.meltsan.pdfcreator.beans.ParticipacionAsegurado;
import com.meltsan.pdfcreator.beans.SiniestralidadEsperada;
import com.meltsan.pdfcreator.beans.SiniestralidadOficina;
import com.meltsan.pdfcreator.beans.SiniestroRangoGrafica;
import com.meltsan.pdfcreator.beans.SiniestroRangoPeriodo;
import com.meltsan.pdfcreator.beans.SiniestrosMayores;
import com.meltsan.pdfcreator.beans.TopHospitalesGrafica;
import com.meltsan.pdfcreator.beans.TopPadecimientosCronicos;
import com.meltsan.pdfcreator.beans.values.CausaValues;
import com.meltsan.pdfcreator.beans.values.ComparativoHospitalValues;
import com.meltsan.pdfcreator.beans.values.CostoPromedioSiniestroValues;
import com.meltsan.pdfcreator.beans.values.DistribucionGastosValues;
import com.meltsan.pdfcreator.beans.values.IndicadoresSiniestroValues;
import com.meltsan.pdfcreator.beans.values.InflacionSSValues;
import com.meltsan.pdfcreator.beans.values.PadecimientoCronicoValues;
import com.meltsan.pdfcreator.beans.values.PadecimientosFrecuenciaValues;
import com.meltsan.pdfcreator.beans.values.ParentescoValues;
import com.meltsan.pdfcreator.beans.values.PobHistoricaValues;
import com.meltsan.pdfcreator.beans.values.SexoValues;
import com.meltsan.pdfcreator.beans.values.SiniestralidadEsperadaValues;
import com.meltsan.pdfcreator.beans.values.SiniestralidadOficinaValues;
import com.meltsan.pdfcreator.beans.values.SiniestroPadecimientoValues;
import com.meltsan.pdfcreator.beans.values.TiempoRespuesta;
import com.meltsan.pdfcreator.beans.values.TipoPagoValues;
import com.meltsan.pdfcreator.beans.values.TopHospitalesValues;
import com.meltsan.pdfcreator.util.Utilidades;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
		
		ArrayList<String> periodos = Utilidades.getPeriodosSinMayores(siniestros);
		ArrayList<String> labels = Utilidades.getEtiquetasSinMayores(siniestros);
		ArrayList<Object[]> temp = new ArrayList<Object[]>();
		
		int noCols = periodos.size()+3;	         
        String[] columns = new String[noCols];                
        
        columns[0] = "siniestro";
        columns[1] = "padecimiento";
        
        for (int i = 0; i < periodos.size(); i++) {	
           columns[i+2] = periodos.get(i);
        }
        
        columns[noCols-1]="acumulado";
        
        DRDataSource dataSource = new DRDataSource(columns);
        columns = null;

        for(String label:labels) {        		
        		for(SiniestrosMayores siniestro: siniestros) {        			
        			Object[] data = new Object[noCols];
        			data[0] = label;
        			data[1] = siniestro.getPadecimiento();
        			
        			if(siniestro.getNoSiniestro().equals(label)) {        				
        				Object[] values = new Object[periodos.size()];
        				int i = 0;
        				
        				for(String periodo:periodos) {        					
        					if(periodo.equals(siniestro.getPeriodo())) {
        						values[i] = siniestro.getMonto();
        					}
        					i++;
        				}
        				        				
        				System.arraycopy(values, 0, data, 2, values.length);
        				temp.add(data);
        			}
        		}        	
        }
        
        int acumulado = 0;
        Object[] result = new Object[noCols];
        for(String label:labels) {
        		Object[] data = new Object[noCols];
        		Object[] tmp = new Object[noCols];
        		for(Object[] objeto:temp) {        		
        			if(objeto[0].equals(label)) {
        				for(int i=2;i<noCols;i++) {
        					if(objeto[i] != null) {
        						tmp[i] = "$"+formatoEntero.format(objeto[i]);
        						acumulado = acumulado +(Integer)objeto[i];
        						result[0] = objeto[0];
        						result[1] = objeto[1];
        					}
        				}        				
        			}
        			data = tmp;	
        		}  
        		System.arraycopy(data, 2, result, 2, data.length-2);
        		result[noCols-1] = "$"+formatoEntero.format(acumulado);        		
        		dataSource.add(result);
        		acumulado = 0;
		}
        
        
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
	
	/**
	 * Genera datos para llenar grafica Padecimientos Cronicos 
	 * de pie 	
	 * @param padecimientos Objeto tipo TopPadecimientosCronico
	 * @param tipoGrafica Entero donde 1 es la grafica de padecimientos totales
	 * y 2 es la tabla de padecimientos detalle del top
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearPadecimientoCronicoPieDS(TopPadecimientosCronicos padecimientos, int tipoGrafica) {
		
		DRDataSource dataSource = new DRDataSource("padecimiento","porcentaje");
		
		switch(tipoGrafica){
		case 1:
			
			Double porcentajePadecimientos = 0.0;
			for(ConceptoMonto pad: padecimientos.getPadecimientosCronicos()){
				porcentajePadecimientos += pad.getMonto();
			}
			
			dataSource.add("No crónicos",padecimientos.getMontoNoCronicos());
			dataSource.add("Crónicos",porcentajePadecimientos);			
			
			break;
			
		case 2:
			
			for(ConceptoMonto pad: padecimientos.getPadecimientosCronicos()){
				dataSource.add(pad.getConcepto(),pad.getMonto());
			}
			
			break;
		default:
			dataSource.add("Grafica no valida",100);
			break;
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar grafica  Padecimientos Cronicos
	 * de barras
	 * @param padecimientos Lista de objetos tipo PadCronicoClienteMercado
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearPadecimientoCronicoBarraDS(ArrayList<PadCronicoClienteMercado> padecimientos) {
		
		DRDataSource dataSource = new DRDataSource("padecimiento","cliente","mercado");
		
		for(PadCronicoClienteMercado pcm: padecimientos) {
			dataSource.add(pcm.getPadecimiento(),pcm.getPorcentajeCliente(),pcm.getPorcentajeMercado());
		}
		
		padecimientos = null;
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar tabla Padecimientos Cronicos 
	 * 	
	 * @param padecimientos Lista de objetos tipo PadCronicosMontos
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearPadecimientoCronicoTablaDS(ArrayList<PadCronicosMontos> padecimientos) {
		
		  	int masterRowNumber = padecimientos.size();
		  	int noCols = masterRowNumber * 3;
		  	noCols = noCols +1;
	        String[] columns = new String[noCols];                
	        ArrayList<String> namesCols = new ArrayList<String>();
	        ArrayList<String> labels = new ArrayList<String>();
	        
	        for (int i = 0; i < masterRowNumber; i++) {
	        		namesCols.add("monto"+i);
	        		namesCols.add("siniestro"+i);
	        		namesCols.add("costo"+i);
	        }
	        
	        columns[0] = "vigencia";
	        for (int i = 1; i <= namesCols.size(); i++) {	
	           columns[i] = namesCols.get(i-1);
	        }	        
	        
	        DRDataSource dataSource = new DRDataSource(columns);
    		
	        for(PadCronicosMontos pad:padecimientos) {       		
	        		for(PadecimientoCronicoValues padv: pad.getPadecimientos()) {	        			
	        			labels.add(padv.getPadecimiento());
	        		}
	        }
	        
	        HashSet<String> h = new HashSet<String>(labels);
	        labels.clear();
	        labels.addAll(h);
	        	        
	        		Object[] values = new Object[noCols];
	        		Object[] valuesInts = new Object[noCols];
	        		Object[] totales = new Object[noCols];
	        		totales[0]="TOTAL";
	        		
	        		for(int i=1;i<values.length;i++) {	        				
        				totales[i]= (Integer)0;
        			}
	        		
	        		for(String l:labels) {	        			
	        			values = getPadecimientosPeriodoStrings(noCols,l,padecimientos);
	        			valuesInts = getPadecimientosPeriodoInts(noCols,l,padecimientos);
	        			dataSource.add(values);
	        			for(int i=1;i<valuesInts.length;i++) {
	        				totales[i] = (Integer)valuesInts[i] + (Integer)totales[i];
	        			}
	        		}
	        		
	        		dataSource.add(getTotalesPadecimientos(noCols,totales));

		return dataSource;
	}
	
	/**
	 * Genera datos para llenar tabla Distribucion de Gastos 
	 * No Cubiertos
	 * @param gastos Lista de objetos tipo DistribucionGastoValues
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearGastosNoCubiertosTablaDS(ArrayList<DistribucionGastosValues> gastos) {
		
		int masterRowNumber = gastos.size();
		int noCols = masterRowNumber+1;
        String[] columns = new String[noCols];                
        
        columns[0] = "vigencia";
        for (int i = 1; i <= masterRowNumber; i++) {	
           columns[i] = gastos.get(i-1).getPeriodo();
        }        
        
        DRDataSource dataSource = new DRDataSource(columns);
		
        ArrayList<String> labels = new ArrayList<String>();
        for(DistribucionGastosValues periodo:gastos) {       		
    			for(ConceptoMonto concepto: periodo.getMontos()) {	        			
    				labels.add(concepto.getConcepto());
    			}
        }
    
        HashSet<String> h = new HashSet<String>(labels);
        labels.clear();
        labels.addAll(h);
    
        Object[] values = new Object[noCols];
        Object[] totales = new Object[noCols];
        
        totales[0]="No Cubierto";
        for(int i=1;i<values.length;i++) {	        				
			totales[i]= (Integer)0;
		}
        
        for(String l:labels) {	        			
    			values = getMontoPeriodoGastosNoCubiertos(noCols,l,gastos);
    			dataSource.add(values);
    			for(int i=1;i<values.length;i++) {
    				totales[i] = (Integer)values[i] + (Integer)totales[i];
    			}
    		}        
            
        dataSource.add(totales);
		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar tabla Distribucion de Gastos 
	 * No Cubiertos
	 * @param gastos Lista de objetos tipo ConceptoMonto
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearGastosNoCubiertosGraficaDS(ArrayList<ConceptoMonto> gastos) {
		
		DRDataSource dataSource = new DRDataSource("concepto","monto");
		for(ConceptoMonto pad: gastos){
			dataSource.add(pad.getConcepto(),pad.getMonto());
		}
		return dataSource;		
	}
	
	/**
	 * Genera datos para llenar graficas reporte
	 * Top 5 Hospitales
	 * 	
	 * @param hospitales Objeto tipo TopHospitalesGrafica
	 * @param tipoGrafica Entero donde 1 es la grafica de hospitales totales
	 * y 2 es la tabla de hospitales especifica
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearTopHospitalesGraficaDS(TopHospitalesGrafica hospitales, int tipoGrafica) {
		
		DRDataSource dataSource = new DRDataSource("hospital","siniestros");
		
		switch(tipoGrafica){
		case 1:
					
			float totalTop = 0;
			for(HospitalPorcentaje pad: hospitales.getTopHospitales()){
				totalTop += pad.getPorcentaje();
			}
			
			dataSource.add("Sin Especificar",hospitales.getHospitalesSinEspecificar());
			dataSource.add("Otros Hospitales",hospitales.getOtrosHospitales());
			dataSource.add("Top "+hospitales.getTopHospitales().size() +" Hospitales",totalTop);
			
			break;
			
		case 2:
			
			for(HospitalPorcentaje pad: hospitales.getTopHospitales()){
				dataSource.add(pad.getHospital(),pad.getPorcentaje());
			}
			
			break;
		default:
			dataSource.add("Grafica no valida",100);
			break;
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar tabla reporte
	 * Top 5 Hospitales
	 * 	
	 * @param hospitales Lista de objetos tipo TopHospitalValues
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearTopHospitalesTablaDS(ArrayList<TopHospitalesValues> hospitales) {
		
		DRDataSource dataSource = new DRDataSource("hospital","concepto","indicadores");
		
		for(TopHospitalesValues thv: hospitales) {
			if(thv.getConcepto().equals("Monto Pagado") || thv.getConcepto().equals("Costo Promedio"))
				dataSource.add(thv.getHopital(),thv.getConcepto(),"$"+formatoEntero.format(thv.getIndicadores()));
			
			if(thv.getConcepto().equals("Número de Siniestros"))
				dataSource.add(thv.getHopital(),thv.getConcepto(),formatoEntero.format(thv.getIndicadores()));
			
			if(thv.getConcepto().equals("Morbilidad"))
				dataSource.add(thv.getHopital(),thv.getConcepto(),formatoEntero.format(thv.getIndicadores())+"%");
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para crear tabla de Participacion
	 * Asegurado
	 * @param indicadores lista con objetos ParticipacionAsegurado 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearParticipacionAseguradoTablaDS(ArrayList<ParticipacionAsegurado> montos) {		
		
		int masterRowNumber = montos.size();	  
		int noCols = masterRowNumber * 2;
		noCols = noCols + 3;
        String[] columns = new String[noCols];                
        ArrayList<String> namesCols = new ArrayList<String>();        
        
        for (int i = 0; i < masterRowNumber; i++) {
        		namesCols.add("monto"+i);
        		namesCols.add("porcien"+i);        		
        }
        
        columns[0] = "vigencia";
        for (int i = 1; i <= namesCols.size(); i++) {	
	           columns[i] = namesCols.get(i-1);
	        }      
        
        columns[noCols-2]="montototal";
        columns[noCols-1]="porcientotal";
        
        DRDataSource dataSource = new DRDataSource(columns);
		
        ArrayList<String>labels = new ArrayList<String>();
        labels.add("Reclamado");
        labels.add("Pagado");                
        labels.add("Deducible");                
        labels.add("Coaseguro");        
        labels.add("IVA");        
        labels.add("No Cubierto");        
                              
        float acumuladoTotal = 0;        
        Object[] temp = new Object[noCols];
        
        //Se llena datasource
        for (int i = 0; i < labels.size(); i++) {

           Object[] values = new Object[noCols];                      
           
           String label = labels.get(i);
           values[0] = label;
            
           if(label.equals("Reclamado")){        	   
   	   			int j = 1;   	   		
   	   			for(ParticipacionAsegurado monto:montos) {
   	   				values[j] = "$"+formatoEntero.format(monto.getMontoReclamado()).toString();
   	   				acumuladoTotal = acumuladoTotal + monto.getMontoReclamado();
   	   				j++;
   	   				values[j] = "100%";
   	   				j++;
   	   			}
   	   			values[noCols-2] = "$"+formatoEntero.format(acumuladoTotal).toString();
   	   			values[noCols-1] = "100%";
   	   			System.arraycopy(values,0, temp, 0, values.length);   	   			
           }
           
           if(label.equals("Pagado")){        	   
        	   		int j = 1;
        	   		float acumulado = 0;
        	   		for(ParticipacionAsegurado monto:montos) {
        	   			values[j] = "$"+formatoEntero.format(monto.getMontoPagado()).toString();
        	   			acumulado = acumulado + monto.getMontoPagado();
        	   			j++;
        	   			values[j] = formatoEntero.format(monto.getPorcentajePagado()).toString()+"%";
        	   			j++;
        	   		}
        	   		values[noCols-2] = "$"+formatoEntero.format(acumulado).toString();
        	   		float x = acumulado/acumuladoTotal;
        	   		x = Math.round(x * 100.0f) / 100.0f;
        	   		int res = (int) (x * 100);
        	   		values[noCols-1] = formatoEntero.format(res).toString()+"%";
        	   		dataSource.add(values);        	   		
           }
           
           if(label.equals("Deducible")){
        	   
        	   int j = 1;
   	   		float acumulado = 0;
   	   		for(ParticipacionAsegurado monto:montos) {
   	   			values[j] = "$"+formatoEntero.format(monto.getMontoDeducible()).toString();
   	   			acumulado = acumulado + monto.getMontoDeducible();
   	   			j++;
   	   			values[j] = formatoEntero.format(monto.getPorcentajeDeducible()).toString()+"%";
   	   			j++;
   	   		}
   	   		values[noCols-2] = "$"+formatoEntero.format(acumulado).toString();
   	   		float x = acumulado/acumuladoTotal;
   	   		x = Math.round(x * 100.0f) / 100.0f;
   	   		int res = (int) (x * 100);
   	   		values[noCols-1] = formatoEntero.format(res).toString()+"%";
   	   		dataSource.add(values); 
   	   		
           }
           
           if(label.equals("Coaseguro")){
        	   int j = 1;
      	   		float acumulado = 0;
      	   		for(ParticipacionAsegurado monto:montos) {
      	   			values[j] = "$"+formatoEntero.format(monto.getMontoCoaseguro()).toString();
      	   			acumulado = acumulado + monto.getMontoDeducible();
      	   			j++;
      	   			values[j] = formatoEntero.format(monto.getPorcentajeCoaseguro()).toString()+"%";
      	   			j++;
      	   		}
      	   		values[noCols-2] = "$"+formatoEntero.format(acumulado).toString();
      	   		float x = acumulado/acumuladoTotal;
      	   		x = Math.round(x * 100.0f) / 100.0f;
      	   		int res = (int) (x * 100);
      	   		values[noCols-1] = formatoEntero.format(res).toString()+"%";
      	   		dataSource.add(values); 
	   			 	 
           }
           
           if(label.equals("IVA")){
        	   int j = 1;
      	   		float acumulado = 0;
      	   		for(ParticipacionAsegurado monto:montos) {
      	   			values[j] = "$"+formatoEntero.format(monto.getMontoIVA()).toString();
      	   			acumulado = acumulado + monto.getMontoIVA();
      	   			j++;
      	   			values[j] = formatoEntero.format(monto.getPorcentajeIVA()).toString()+"%";
      	   			j++;
      	   		}
      	   		values[noCols-2] = "$"+formatoEntero.format(acumulado).toString();
      	   		float x = acumulado/acumuladoTotal;
      	   		x = Math.round(x * 100.0f) / 100.0f;
      	   		int res = (int) (x * 100);
      	   		values[noCols-1] = formatoEntero.format(res).toString()+"%";
      	   		dataSource.add(values); 
	   			 	 
          }
           
           if(label.equals("No Cubierto")){
 	   			 	   				
        	   int j = 1;
      	   		float acumulado = 0;
      	   		for(ParticipacionAsegurado monto:montos) {
      	   			values[j] = "$"+formatoEntero.format(monto.getMontoNoCubierto()).toString();
      	   			acumulado = acumulado + monto.getMontoNoCubierto();
      	   			j++;
      	   			values[j] = formatoEntero.format(monto.getPorcentajeNoCubierto()).toString()+"%";
      	   			j++;
      	   		}
      	   		values[noCols-2] = "$"+formatoEntero.format(acumulado).toString();
      	   		float x = acumulado/acumuladoTotal;
      	   		x = Math.round(x * 100.0f) / 100.0f;
      	   		int res = (int) (x * 100);
      	   		values[noCols-1] = formatoEntero.format(res).toString()+"%";
      	   		dataSource.add(values); 
	 
          }
                               
        }
		dataSource.add(temp);
		return dataSource;
	}
	
	/**
	 * Genera datos para crear grafica de Participacion
	 * Asegurado
	 * @param indicadores lista con objetos ParticipacionAsegurado 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearParticipacionAseguradoGraficaDS(ArrayList<ParticipacionAsegurado> montos) {	
		
		DRDataSource dataSource = new DRDataSource("periodo", "pagado","deducible","coaseguro","iva","nocubierto");
		
		for(ParticipacionAsegurado pc : montos) {
			dataSource.add(pc.getPeriodo(),pc.getPorcentajePagado(),pc.getPorcentajeDeducible(),
							pc.getPorcentajeCoaseguro(),pc.getPorcentajeIVA(),pc.getPorcentajeNoCubierto());
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar tabla reporte
	 * Comparativo Hospitales
	 * 	
	 * @param hospitales Lista de objetos tipo ComparativoHospital
	 * @return JRDataSource para alimentar graficas
	 */
	public JRDataSource crearComparativoHospitalDS(ArrayList<ComparativoHospitalValues> hospitales) {
		
		DRDataSource dataSource = new DRDataSource("padecimiento","hospital","siniestro","monto","costo");
		
		for(ComparativoHospitalValues ch: hospitales) {
			dataSource.add(ch.getPadecimiento(),ch.getHospital(),ch.getNoSiniestros(),"$"+formatoEntero.format(ch.getMontoPagdo()),
					"$"+formatoEntero.format(ch.getCostoPromedio()));
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para crear tabla de Indicadores
	 * Oficina
	 * @param indicadores lista con objetos IndicadoresOficina 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearIndicadoresOficinaTablaDS(ArrayList<IndicadoresOficina> montos) {		
		
		ArrayList<String>labels = Utilidades.getEtiquetasIndicadoresOficina(montos);
        ArrayList<String>periodos = Utilidades.getPeriodosIndicadoresOficina(montos);
        
		int masterRowNumber = periodos.size();	  
		int noCols = masterRowNumber * 3;
		noCols = noCols + 1;
        String[] columns = new String[noCols];                
        ArrayList<String> namesCols = new ArrayList<String>();        
        
        for (int i = 0; i < masterRowNumber; i++) {
        		namesCols.add("morbo"+i);
        		namesCols.add("costo"+i);
        		namesCols.add("percapita"+i);
        }
        
        columns[0] = "oficina";
        for (int i = 1; i <= namesCols.size(); i++) {	
	           columns[i] = namesCols.get(i-1);
	        }      
        
        DRDataSource dataSource = new DRDataSource(columns);                        
        Object[] valuesGeneral = new Object[noCols];
        
        for(String label :labels) {        		
        			Object[] values = new Object[noCols];
        			values[0]=label;
        			int i = 1;
        			for(IndicadoresOficina io: montos) {
        				Object[] tmp = new Object[3];        			
        				if(io.getOficina().equals(label)) {        				
        					for(String periodo:periodos) {        					
        						if(io.getPeriodo().equals(periodo)) {
        							tmp[0] = formatoEntero.format(io.getMorbilidad())+"%";        						
        							tmp[1] = "$"+formatoEntero.format(io.getCostoPromedio());        						
        							tmp[2] = "$"+formatoEntero.format(io.getCostoPerCapita());        						
        						}        					
        					}
        					if(label.equals("General")) {
        						valuesGeneral[0] = "General";
        						System.arraycopy(tmp, 0, valuesGeneral, i, tmp.length);
        					}else {
        						System.arraycopy(tmp, 0, values, i, tmp.length);
        					}
        					
        					i = i+3;    					
        				}
        			}
        			if(!label.equals("General"))
        				dataSource.add(values);        		
        }
        
        if(valuesGeneral[1] != null) {
        		dataSource.add(valuesGeneral);  
        }
        
        
        return dataSource;
	}
	
	/**
	 * Genera datos para crear Grafica de Indicadores
	 * Oficina
	 * @param indicadores lista con objetos IndicadoresOficina 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearIndicadoresOficinaGraficaDS(ArrayList<IndicadoresOficina> montos) {
		
		ArrayList<String>labels = Utilidades.getEtiquetasIndicadoresOficina(montos);
		ArrayList<String>periodos = Utilidades.getPeriodosIndicadoresOficina(montos);
		
		int noCols = 0;
		
		labels.remove("General");
		 
		noCols = labels.size()+1;
		
		String[] columns = new String[noCols];
		columns[0] = "periodo";
		
		for (int i = 1; i < noCols; i++) {	
	           columns[i] = labels.get(i-1);
	        }  
		
		DRDataSource dataSource = new DRDataSource(columns);
		
		 for(String periodo :periodos) {        		
 			Object[] values = new Object[noCols];
 			values[0]=periodo;
 			int i = 1;
 			for(IndicadoresOficina io: montos) {
 				//Object[] tmp = new Object[3];        			
 				if(io.getPeriodo().equals(periodo)) {        				
 					for(String label:labels) {        					
 						if(io.getOficina().equals(label)) {
 							values[i] = io.getMorbilidad(); 
 							i++;
 						} 						
 					}   				 					
 				} 				
 			}
 			dataSource.add(values);
 		}
		
		
		return dataSource;
	}
	

	/**
	 * Genera datos para crear tabla de Siniestralidad
	 * por Oficina y Estado
	 * @param oficinas lista con objetos SiniestralidadOficina 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearSiniestralidadOficinaDS(ArrayList<SiniestralidadOficina> oficinas) {
		
		ArrayList<So> dataSource = new ArrayList<So>();
	

		for (SiniestralidadOficina oficina:oficinas) {
			
			So data = new So();
			ArrayList<String> estados = new ArrayList<String>();
			ArrayList<String> siniestros = new ArrayList<String>();
			ArrayList<String> monto = new ArrayList<String>();
			
			for(SiniestralidadOficinaValues estado: oficina.getEstados()) {
				estados.add(estado.getEstado());
				siniestros.add(estado.getNoSiniestros().toString());
				monto.add("$"+formatoEntero.format(estado.getMontoPagado()));				
			}
			
			data.setOfi(oficina.getOficina()+" "+oficina.getNoAsegurados()+" Asegurados");
			data.setEstado(estados);
			data.setSiniestros(siniestros);
			data.setMonto(monto);
			data.setMorbo(formatoEntero.format((oficina.getMorbilidad()))+"%");
			data.setCosto("$"+formatoEntero.format(oficina.getCostoPromedio()));
			data.setPercapita("$"+formatoEntero.format(oficina.getCostoPerCapita()));
			dataSource.add(data);
		}
				
		
		return new JRBeanCollectionDataSource(dataSource);
	}
	
	/**
	 * Genera datos para crear grafica de Siniestralidad
	 * esperada
	 * @param indicadores Objetos tipo SiniestralidadEsperada 
	 * @return JRDataSource para alimentar grafica 
	 */
	public JRDataSource crearIndicadoresSiniestroGraficaDS(SiniestralidadEsperada indicadores) {
		DRDataSource dataSource = new DRDataSource("periodo", "siniestralidad","pronostico","tendencia");
		
		for(SiniestralidadEsperadaValues pc : indicadores.getSiniestralidadHistorica()) {
			dataSource.add(pc.getPeriodo(),(pc.getSiniestralidad()/1000000),null,(pc.getTendencia()/1000000));
		}
		
		dataSource.add(indicadores.getSiniestralidadPronostico().get(0).getPeriodo(),null,
				(indicadores.getSiniestralidadPronostico().get(0).getSiniestralidad()/1000000),
				null);
		
		for(int i = 1;i< indicadores.getSiniestralidadPronostico().size();i++) {
			
			dataSource.add(indicadores.getSiniestralidadPronostico().get(i).getPeriodo(),null,
					(indicadores.getSiniestralidadPronostico().get(i).getSiniestralidad()/1000000),
					(indicadores.getSiniestralidadPronostico().get(i).getTendencia()/1000000));
		}
		
		return dataSource;
	}
	
	/**
	 * Genera datos para llenar tabla de Tiempos de Respuesta
	 * 	
	 * @param info Lista de objetos tipo TiempoRespuesta
	 * @return JRDataSource para alimentar tabla
	 */
	public JRDataSource crearTiempoRespuestaDS(ArrayList<TiempoRespuesta> info) {		
		
		ArrayList<String>labels = Utilidades.getEtiquetasTiempoRespuesta(info);
		ArrayList<String>periodos = Utilidades.getPeriodoTiempoRespuesta(info);
		
		int noCols = 0;
						 
		noCols = periodos.size()+1;
		
		String[] columns = new String[noCols];
		Object[] totales = new Object[noCols];
		columns[0] = "periodo";
		totales[0]="Total";
		
		for (int i = 1; i < noCols; i++) {	
	           columns[i] = periodos.get(i-1);
	           totales[i] = 0;
	        }  
		
		DRDataSource dataSource = new DRDataSource(columns);

		 for(String label :labels) {        		
 			Object[] values = new Object[noCols];
 			Object[] porcientos = new Object[noCols];
 			
 			values[0]=label;
 			porcientos[0]="% Acumulado";
 			int i = 1;
 			for(TiempoRespuesta io: info) { 				   		
 				if(io.getMes().equals(label)) {        				
 					for(String periodo:periodos) {        					
 						if(io.getTiempoRespuesta().toString().equals(periodo)) {
 							values[i] = io.getSiniestros().toString();
 							totales[i] = (Integer)totales[i] + io.getSiniestros();
 							porcientos[i] = io.getPorcentajeAcumulado()+"%";
 							i++;
 						} 						
 					}   				 					
 				} 				
 			}
 			dataSource.add(values);
 			dataSource.add(porcientos);
 		}
		 
		 for(int i = 1;i<totales.length;i++) {
			 totales[i] = totales[i].toString();
		 }
		 
		  dataSource.add(totales);
	       return dataSource;
	    }
	
	
	public class So{
		
		private String ofi;
		private ArrayList<String> estado;
		private ArrayList<String> siniestros;
		private ArrayList<String> monto;
		private String morbo;
		private String costo;
		private String percapita;
		
		public String getOfi() {
			return ofi;
		}
		public void setOfi(String ofi) {
			this.ofi = ofi;
		}
		public ArrayList<String> getEstado() {
			return estado;
		}
		public void setEstado(ArrayList<String> estado) {
			this.estado = estado;
		}
		public ArrayList<String> getSiniestros() {
			return siniestros;
		}
		public void setSiniestros(ArrayList<String> siniestros) {
			this.siniestros = siniestros;
		}
		public ArrayList<String> getMonto() {
			return monto;
		}
		public void setMonto(ArrayList<String> monto) {
			this.monto = monto;
		}
		public String getMorbo() {
			return morbo;
		}
		public void setMorbo(String morbo) {
			this.morbo = morbo;
		}
		public String getCosto() {
			return costo;
		}
		public void setCosto(String costo) {
			this.costo = costo;
		}
		public String getPercapita() {
			return percapita;
		}
		public void setPercapita(String percapita) {
			this.percapita = percapita;
		}
		
	}
		
	/**
	 * Función para obtener monto y numero de siniestros de un padecimiento
	 * @param columnas Integer con el numero de columnas que se van a crear
	 * @param padecimiento String con el padecimiento a buscar
	 * @param padecimientos lista de padecimientos tipo PadCronicosMontos
	 * @return
	 */
	private Object[] getPadecimientosPeriodoStrings(Integer columnas, String padecimiento, ArrayList<PadCronicosMontos> padecimientos) {
		      					
			Object[] temp = new Object[3];
			Object[] result = new Object[columnas];
			int i = 1;
			result[0] = padecimiento;
 			for(PadCronicosMontos padv: padecimientos) { 					
 					for(PadecimientoCronicoValues pcv: padv.getPadecimientos()) {
 						if(pcv.getPadecimiento().equals(padecimiento)) { 							
 							temp[0]="$"+pcv.getMontoPagado();
 							Integer noSins = pcv.getNoSiniestros();
 							temp[1]=noSins.toString(); 							
 							if(noSins!=0) {
 								Integer promedio = pcv.getMontoPagado()/noSins; 
 								temp[2]="$"+promedio;
 							}
 							else
 								temp[2]="$"+0;
 							System.arraycopy(temp, 0, result, i, temp.length);
 							i = i + 3;
 						}
 					} 				
 			}
     return result;
		
	}
	
	private Object[] getMontoPeriodoGastosNoCubiertos(Integer columnas, String concepto, ArrayList<DistribucionGastosValues> gastos) {
			
		Object[] temp = new Object[1];
		Object[] result = new Object[columnas];
		int i = 1;
		result[0] = concepto;
			for(DistribucionGastosValues padv: gastos) { 					
					for(ConceptoMonto pcv: padv.getMontos()) {
						if(pcv.getConcepto().equals(concepto)) { 							
							temp[0] = pcv.getMonto();	
							System.arraycopy(temp, 0, result, i, temp.length);
							i++;
						}
					} 				
			}
			return result;
	
	}
	
	
	private Object[] getPadecimientosPeriodoInts(Integer columnas, String padecimiento, ArrayList<PadCronicosMontos> padecimientos) {
			
		Object[] temp = new Object[3];
		Object[] result = new Object[columnas];
		int i = 1;
		result[0] = padecimiento;
			for(PadCronicosMontos padv: padecimientos) { 					
					for(PadecimientoCronicoValues pcv: padv.getPadecimientos()) {
						if(pcv.getPadecimiento().equals(padecimiento)) { 							
							temp[0]=pcv.getMontoPagado();
							int noSins = pcv.getNoSiniestros();
							temp[1]=noSins; 							
							if(noSins!=0) {
								Integer promedio = pcv.getMontoPagado()/noSins; 
								temp[2]=promedio;
							}
							else
								temp[2]=0;
							System.arraycopy(temp, 0, result, i, temp.length);
							i = i + 3;
						}
					} 				
			}
 return result;
	
}
	
	
	private Object[] getTotalesPadecimientos(Integer columnas,Object[] values) {		
		Object[] result = new Object[columnas];
		result[0]="TOTAL";		
		
		for(int i=1;i<values.length;i++) {
			if(i % 3 == 0) {
				Integer res = (Integer)values[i-2] / (Integer)values[i-1];
				result[i] = "$ "+res.toString();
			} else if(i % 2 == 0){
				result[i] = values[i].toString();
			} else {
				result[i] = "$ "+values[i];
			}
		}		
		
		return result;
	}
	
}
