package pe.gob.mtpe.sivice.externo.core.util;

import java.io.File;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

public class ConstantesArchivos {

 
	public static long getMilisegundos() {
		Calendar calendar = Calendar.getInstance();
		long hoy = calendar.getTimeInMillis();

		return hoy;
	}

	public static int getAnioActual() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getMesActual() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getDiaActual() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static String getAbsolute() {

		String strDia = String.valueOf(getDiaActual());
		String strMes = String.valueOf(getMesActual());
		String strAnio = String.valueOf(getAnioActual());
		String strMiliSegundos = String.valueOf(getMilisegundos());
		String strAbsoluto = null;

		switch (strDia.length()) {
		case 1:
			strAbsoluto = "0".concat(strDia);
			break;
		case 2:
			strAbsoluto = strDia;
			break;
		default:
			strAbsoluto = strDia;
			break;
		}

		switch (strMes.length()) {
		case 1:
			strAbsoluto = strAbsoluto.concat("0").concat(strMes);
			break;
		case 2:
			strAbsoluto = strAbsoluto.concat(strMes);
			break;
		default:
			strAbsoluto = strAbsoluto.concat(strMes);
			break;
		}

		switch (strAnio.length()) {
		case 1:
			strAbsoluto = strAbsoluto.concat("0").concat(strAnio);
			break;
		case 2:
			strAbsoluto = strAbsoluto.concat(strAnio);
			break;
		default:
			strAbsoluto = strAbsoluto.concat(strAnio);
			break;
		}

		return strAbsoluto.concat(strMiliSegundos);
	}

	public static String getNuevoNombreArchivo(String tipoDocumento) {
		String ruta = null;
		ruta = tipoDocumento+ "_" + getAbsolute();
		return ruta;
	}
	
	
	public static String getObtenerRutaCarpetas(){
		return  getAnioActual() 
     	+ System.getProperty("file.separator")+ getMesActual()  + System.getProperty("file.separator") + getDiaActual() +System.getProperty("file.separator");
	}
	
	public static boolean isEmptyFile(MultipartFile mpf) {

		if (mpf == null || (mpf != null && (mpf.getSize() == 0 || toCadena(mpf.getOriginalFilename()).equals("")))) {
			return true;
		}
		return false;
	}
	
	
	public static String  toCadena(Object valor) {
		if(valor==null){
			return "";
		}

		return valor.toString();

	}
	
	public static boolean existeFileEnRuta(String rutaArchivo) {

		File fichero = new File(rutaArchivo);

		if (!fichero.exists()) {
			return false;
		}
		return true;
	}
	

}
