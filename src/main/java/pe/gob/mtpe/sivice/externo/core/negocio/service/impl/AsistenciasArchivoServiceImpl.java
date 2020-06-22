package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciasArchivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AsistenciasArchivosDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AsistenciasArchivoService;

@Service
public class AsistenciasArchivoServiceImpl implements AsistenciasArchivoService {
	
	 @Autowired 
	private AsistenciasArchivosDao asistenciasArchivosDao;

	@Override
	public AsistenciasArchivos cargarArchivo(AsistenciasArchivos asistenciasArchivos) {
		return asistenciasArchivosDao.cargarArchivo(asistenciasArchivos);
	}

	@Override
	public AsistenciasArchivos descargarArchivo(Long idsession) { 
		return asistenciasArchivosDao.descargarArchivo(idsession);
	}

}
