package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Seguimientos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SeguimientoDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.SeguimientoService;

@Service("SeguimientoService")
@Transactional(readOnly = true)
public class SeguimientoServiceImpl implements SeguimientoService {

	@Autowired
	private SeguimientoDao seguimientoDao;

	@Override
	public List<Seguimientos> listar() {
		return seguimientoDao.listar();
	}

	@Override
	public Seguimientos buscarPorId(Seguimientos seguimientos) {
		return seguimientoDao.buscarPorId(seguimientos);
	}

	@Override
	public List<Seguimientos> buscar(Seguimientos seguimientos) {
		return seguimientoDao.buscar(seguimientos);
	}

	@Override
	public Seguimientos Registrar(Seguimientos seguimientos) {
		return seguimientoDao.Registrar(seguimientos);
	}

	@Override
	public Seguimientos Actualizar(Seguimientos seguimientos) {
		return seguimientoDao.Actualizar(seguimientos);
	}

	@Override
	public Seguimientos Eliminar(Seguimientos seguimientos) {
		return seguimientoDao.Eliminar(seguimientos);
	}

}
