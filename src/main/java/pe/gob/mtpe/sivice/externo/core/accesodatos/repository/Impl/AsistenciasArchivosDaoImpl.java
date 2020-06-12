package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import org.springframework.stereotype.Component;

import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciasArchivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AsistenciasArchivosDao;

@Component
public class AsistenciasArchivosDaoImpl extends BaseDao<Long, AsistenciasArchivos> implements AsistenciasArchivosDao {

	@Override
	public AsistenciasArchivos cargarArchivo(AsistenciasArchivos asistenciasArchivos) {
		guardar(asistenciasArchivos);
		return asistenciasArchivos;
	}

}
