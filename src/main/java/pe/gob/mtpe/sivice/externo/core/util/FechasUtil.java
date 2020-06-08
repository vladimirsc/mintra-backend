package pe.gob.mtpe.sivice.externo.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FechasUtil {

	public static String obtenerCorrelativo(Long numCorrelativo, String alias) {
		int anio = 0;
		String strignAnio = null;
		String StrcorrelativoFinal = null;
		String StrCorrelativo = String.valueOf(numCorrelativo);
		try {
			Calendar cal = Calendar.getInstance();
			anio = cal.get(Calendar.YEAR);
			strignAnio = String.valueOf(anio);
			switch (StrCorrelativo.length()) {
			case 1:
				StrcorrelativoFinal = alias + "-" + strignAnio + '-' + "000" + StrCorrelativo;
				break;
			case 2:
				StrcorrelativoFinal = alias + "-" + strignAnio + '-' + "00" + StrCorrelativo;
				break;
			case 3:
				StrcorrelativoFinal = alias + "-" + strignAnio + '-' + "0" + StrCorrelativo;
				break;
			default:
				StrcorrelativoFinal = StrCorrelativo;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return StrcorrelativoFinal;
	}
	
	
	public static Date convertStringToDate(String fecha){
		DateFormat  formatoFecha = new SimpleDateFormat("dd-MM-yyyy");	 
		 Date fechaDate = null ;
		 try {			
			 fechaDate = formatoFecha.parse(fecha);		    
		} catch (Exception e) {			
		}	
		return fechaDate;
	}
	
	
	
	public static Date fechaActual() {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		format.setCalendar(cal);
		return cal.getTime();
	}
	
	
	
	 

}
