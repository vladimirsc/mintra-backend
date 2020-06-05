package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ActasDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ActaService;

@Service("ActaService")
@Transactional(readOnly = true)
public class ActaServiceImpl implements ActaService {
	
	@Autowired
	private ActasDao actasDao;

	@Override
	public List<Actas> listar() {
		return actasDao.listar();
	}

	@Override
	public Actas buscarPorId(Actas actas) {
		return actasDao.buscarPorId(actas);
	}

	@Override
	public List<Actas> buscar(Actas actas) {
		return actasDao.buscar(actas);
	}

	@Override
	public Actas Registrar(Actas actas) {
		return actasDao.Registrar(actas);
	}

	@Override
	public Actas Actualizar(Actas actas) {
		actas.getdFecmodifica();
		return actasDao.Actualizar(actas);
	}

	@Override
	public Actas Eliminar(Actas actas) {
		return actasDao.Eliminar(actas);
	}

	@Override
	public Actas descargarActa(Actas actas) {
		return actasDao.buscarPorId(actas);
	}

}
