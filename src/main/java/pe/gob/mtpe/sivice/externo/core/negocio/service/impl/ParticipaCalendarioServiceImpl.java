package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Particalen;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ParticipaCalendarioDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ParticipanteCalendarioService;

@Service("ParticipaCalendarioServiceImpl")
@Transactional(readOnly=true)
public class ParticipaCalendarioServiceImpl implements ParticipanteCalendarioService {

	@Autowired
	private ParticipaCalendarioDao participaCalendarioDao;
	
	@Override
	public List<Particalen> listar() {
		return participaCalendarioDao.listar();
	}

	@Override
	public Particalen buscarPorId(Particalen particalen) {
		return participaCalendarioDao.buscarPorId(particalen);
	}

	@Override
	public List<Particalen> buscar(Particalen particalen) {
		return participaCalendarioDao.buscar(particalen);
	}

	@Override
	public Particalen Registrar(Particalen particalen) {
		return participaCalendarioDao.Registrar(particalen);
	}

	@Override
	public Particalen Actualizar(Particalen particalen) {
		return participaCalendarioDao.Actualizar(particalen);
	}

	@Override
	public Particalen Eliminar(Particalen particalen) {
		return participaCalendarioDao.Eliminar(particalen);
	}

}
