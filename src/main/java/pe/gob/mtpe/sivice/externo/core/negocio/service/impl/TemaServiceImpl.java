package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Temas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.TemaDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.TemaService;

@Service("TemaService")
@Transactional(readOnly = true)
public class TemaServiceImpl implements TemaService {
	
	@Autowired
	private TemaDao temaDao;
	
	@Autowired
	private SesionDao sesionDao;

	@Override
	public List<Temas> listar() {
		return null;
	}

	@Override
	public Temas buscarPorId(Temas temas) {
		return temaDao.buscarPorId(temas);
	}

	@Override
	public List<Temas> buscar(Temas temas) {
		return temaDao.buscar(temas);
	}

	@Override
	public Temas Registrar(Temas temas) {
		Sesiones session = new Sesiones();
		session.setsEsionidpk(temas.getsEsionfk());
		temas.setSesion(session);
		return temaDao.Registrar(temas);
	}

	@Override
	public Temas Actualizar(Temas temas) {
		return temaDao.Actualizar(temas);
	}

	@Override
	public Temas Eliminar(Temas temas) {
		return temaDao.Eliminar(temas);
	}

	@Override
	public Sesiones cabeceraSesion(Sesiones sesiones) { 
		return sesionDao.buscarPorId(sesiones);
	}

	@Override
	public List<Temas> temasPorSesion(Long idsesion) { 
		return temaDao.temasPorSesion(idsesion);
	}

 

}
