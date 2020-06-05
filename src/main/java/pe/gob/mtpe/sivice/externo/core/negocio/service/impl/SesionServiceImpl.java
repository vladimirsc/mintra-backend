package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesion;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.SesionService;

@Service("SesionService")
@Transactional(readOnly = true)
public class SesionServiceImpl implements SesionService {
	
	@Autowired
	private SesionDao sesionDao;

	@Override
	public List<Sesion> listar() {
		return sesionDao.listar();
	}

	@Override
	public Sesion buscarPorId(Sesion sesion) {
		return sesionDao.buscarPorId(sesion);
	}

	@Override
	public List<Sesion> buscar(Sesion sesion) {
		return sesionDao.buscar(sesion);
	}

	@Override
	public Sesion Registrar(Sesion sesion) {
		return sesionDao.Registrar(sesion);
	}

	@Override
	public Sesion Actualizar(Sesion sesion) {
		return sesionDao.Actualizar(sesion);
	}

	@Override
	public Sesion Eliminar(Sesion sesion) {
		return sesionDao.Eliminar(sesion);
	}

}
