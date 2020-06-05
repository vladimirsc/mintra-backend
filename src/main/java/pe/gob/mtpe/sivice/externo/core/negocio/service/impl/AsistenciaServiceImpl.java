package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Asistencias;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AsistenciaDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AsistenciaService;

@Service("AsistenciaService")
@Transactional(readOnly = true)
public class AsistenciaServiceImpl implements AsistenciaService {
	
	@Autowired
	private AsistenciaDao asistenciaDao;

	@Override
	public List<Asistencias> listar() {
		return asistenciaDao.listar();
	}

	@Override
	public Asistencias buscarPorId(Asistencias asistencia) {
		return asistenciaDao.buscarPorId(asistencia);
	}

	@Override
	public List<Asistencias> buscar(Asistencias asistencia) {
		return asistenciaDao.buscar(asistencia);
	}

	@Override
	public Asistencias Registrar(Asistencias asistencia) {
		return asistenciaDao.Registrar(asistencia);
	}

	@Override
	public Asistencias Actualizar(Asistencias asistencia) {
		return asistenciaDao.Actualizar(asistencia);
	}

	@Override
	public Asistencias Eliminar(Asistencias asistencia) {
		return asistenciaDao.Eliminar(asistencia);
	}

}
