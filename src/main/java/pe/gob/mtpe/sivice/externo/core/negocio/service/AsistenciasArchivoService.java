package pe.gob.mtpe.sivice.externo.core.negocio.service;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciasArchivos; 

public interface AsistenciasArchivoService {
	AsistenciasArchivos cargarArchivo(AsistenciasArchivos asistenciasArchivos);
	
	AsistenciasArchivos descargarArchivo(Long idsession);
}
