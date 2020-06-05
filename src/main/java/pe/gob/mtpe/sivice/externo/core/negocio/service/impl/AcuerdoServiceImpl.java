package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AcuerdoDao;

@Service("AcuerdoService")
@Transactional(readOnly = true)
public class AcuerdoServiceImpl implements pe.gob.mtpe.sivice.externo.core.negocio.service.AcuerdoService {
	
	@Autowired
	private AcuerdoDao acuerdoDao;

	@Override
	public List<Acuerdos> listar() {
		return acuerdoDao.listar();
	}

	@Override
	public Acuerdos buscarPorId(Acuerdos acuerdos) {
		return acuerdoDao.buscarPorId(acuerdos);
	}

	@Override
	public List<Acuerdos> buscar(Acuerdos acuerdos) {
		return acuerdoDao.buscar(acuerdos);
	}

	@Override
	public Acuerdos Registrar(Acuerdos acuerdos) {
		return acuerdoDao.Registrar(acuerdos);
	}

	@Override
	public Acuerdos Actualizar(Acuerdos acuerdos) {
		return acuerdoDao.Actualizar(acuerdos);
	}

	@Override
	public Acuerdos Eliminar(Acuerdos acuerdos) {
		return acuerdoDao.Eliminar(acuerdos);
	}

}
