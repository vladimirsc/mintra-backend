package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Comisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ComisionService;

@Service("ComisionService")
@Transactional(readOnly = true)
public class ComisionServiceimpl implements ComisionService {

	@Autowired
	private ComisionDao ComisionDao;
	
	@Override
	public List<Comisiones> listar() {
		return ComisionDao.listar();
	}

	@Override
	public Comisiones buscarPorId(Comisiones comisiones) {
		
		return ComisionDao.buscarPorId(comisiones);
	}

	@Override
	public List<Comisiones> buscar(Comisiones comisiones) {
		return ComisionDao.buscar(comisiones);
	}

	@Override
	public Comisiones Registrar(Comisiones comisiones) {
		return ComisionDao.Registrar(comisiones);
	}

	@Override
	public Comisiones Actualizar(Comisiones comisiones) {
		return ComisionDao.Actualizar(comisiones);
	}

	@Override
	public Comisiones Eliminar(Comisiones comisiones) {
		return ComisionDao.Eliminar(comisiones);
	}

}
