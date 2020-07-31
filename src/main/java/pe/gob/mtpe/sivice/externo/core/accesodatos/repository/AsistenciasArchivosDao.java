package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciasArchivos;

public interface AsistenciasArchivosDao {
	
	AsistenciasArchivos cargarArchivo(AsistenciasArchivos asistenciasArchivos);
	
	AsistenciasArchivos descargarArchivo(Long idsession);
	
	AsistenciasArchivos VerificarArchivo(Long idsession);
}
