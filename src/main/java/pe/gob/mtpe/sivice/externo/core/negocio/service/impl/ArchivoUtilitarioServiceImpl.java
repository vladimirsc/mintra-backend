package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesArchivos;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil; 

@Service("ArchivoUtilitarioServiceImpl")
public class ArchivoUtilitarioServiceImpl implements ArchivoUtilitarioService {
	
	@Value("${rutaArchivo}")
	private String rutaRaiz;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ArchivoUtilitarioServiceImpl.class);
	
	

	@Override
	public String copiar(MultipartFile archivo, String nomArchivo, String rutaRaiz) {
		String ruta_guardar =rutaRaiz+ ConstantesArchivos.getObtenerRutaCarpetas() + System.getProperty("file.separator") + nomArchivo;

		File rutaFile = new File(rutaRaiz);
		try {

			if (rutaFile.exists()) {
				File directorio = new File(
						rutaRaiz + System.getProperty("file.separator") + ConstantesArchivos.getObtenerRutaCarpetas());
				directorio.mkdirs(); // CREAR DIRECTORIO
			}

			if (!ConstantesArchivos.isEmptyFile(archivo)) {// Verificar que el archivo no sea vacio
				File localFile = new File(ruta_guardar);
				FileOutputStream os = null;
				try {
					os = new FileOutputStream(localFile);
					os.write(archivo.getBytes());
				} catch (IOException e) {
					logger.error("Error al guardar archivo en disco>>" + e);
					return "ERROR";
				} finally {
					if (os != null)
						os.close();
				}

			} else {
				return "ERROR";
			}
		} catch (IOException e1) {
			logger.error("BUS copiar>>>>" + this.getClass().getName(), e1);
			return "ERROR";
		}

		return "OK";
	}

	@Override
	public Archivos cargarArchivo(MultipartFile file, String tipodocumento) {
		Archivos archivo = new Archivos();
		String estadoCopiado = "";
		try {

			if (!file.isEmpty()) {
				archivo.setNombre(ConstantesArchivos.getNuevoNombreArchivo(tipodocumento));
				archivo.setUbicacion(System.getProperty("file.separator")+ConstantesArchivos.getObtenerRutaCarpetas());
				archivo.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
				archivo.setRutaCompleta(rutaRaiz + System.getProperty("file.separator") + archivo.getUbicacion());
				archivo.setNombreExtension(archivo.getNombre() + "."+ archivo.getExtension());
				archivo.setRutaNombreArchivo(archivo.getRutaCompleta() + archivo.getNombreExtension());
				// 1. COPIAR EL ARCHIVO AL REPOSITORIO
				estadoCopiado = copiar(file, archivo.getNombreExtension(), rutaRaiz);

				if (estadoCopiado.equals("OK")) { // 2. VERIFICAR SI EXISTE ERROR AL COPIAR EL ARCHIVO
					archivo.setVerificarCarga(true);
					archivo.setMensaje(ConstantesUtil.C_ARCHIVO_CARGA_CORRECTA);
				} else {
					archivo.setVerificarCarga(false);
					archivo.setMensaje(ConstantesUtil.C_ARCHIVO_CARGA_INCORRECTA);
				}

				// 3. VERIFICACION DE LA EXISTENCIA DEL ARCHIVO EN  LA RUTA
				if (ConstantesArchivos.existeFileEnRuta(archivo.getRutaNombreArchivo())) { 
					archivo.setVerificarUbicacion(true);
					archivo.setMensaje(ConstantesUtil.C_ARCHIVO_VALIDACION_UBICACION_CORRECTA);
				} else {
					archivo.setVerificarUbicacion(false);
					archivo.setMensaje(ConstantesUtil.C_ARCHIVO_VALIDACION_UBICACION_INCORRECTA);
				}
			}

		} catch (Exception e) {
			archivo.setVerificarCarga(false);
			archivo.setVerificarUbicacion(false);
			archivo.setMensaje(e.getMessage().concat(":").concat(e.getCause().getMessage()));
		}

		return archivo;
	}


}
