package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Calendarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.CalendarioDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.CalendarioService;

@Service("CalendarioService")
@Transactional(readOnly = true)
public class CalendarioServiceImpl implements CalendarioService {

	@Autowired
	private CalendarioDao calendarioDao;

	@Override
	public List<Calendarios> listar() {
		return calendarioDao.listar();
	}

	@Override
	public Calendarios buscarPorId(Calendarios calendarios) {
		return calendarioDao.buscarPorId(calendarios);
	}

	@Override
	public List<Calendarios> buscar(Calendarios calendarios) {
		return calendarioDao.buscar(calendarios);
	}

	@Override
	public Calendarios Registrar(Calendarios calendarios) {
		return calendarioDao.Registrar(calendarios);
	}

	@Override
	public Calendarios Actualizar(Calendarios calendarios) {
		return calendarioDao.Actualizar(calendarios);
	}

	@Override
	public Calendarios Eliminar(Calendarios calendarios) {
		return calendarioDao.Eliminar(calendarios);
	}

}
