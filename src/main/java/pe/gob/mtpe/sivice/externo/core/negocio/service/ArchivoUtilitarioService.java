package pe.gob.mtpe.sivice.externo.core.negocio.service;

import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;

public interface ArchivoUtilitarioService {
	Archivos cargarArchivo(MultipartFile file,String tipodocumento);
	String copiar(MultipartFile archivo, String nomArchivo, String rutaRaiz); 
}
