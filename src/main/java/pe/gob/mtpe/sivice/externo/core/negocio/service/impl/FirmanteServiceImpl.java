package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Firmantes;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FirmantesDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FirmanteService;

@Service("FirmanteService")
@Transactional(readOnly = true)
public class FirmanteServiceImpl implements FirmanteService {

	@Autowired
	private FirmantesDao firmantesDao;

	@Override
	public List<Firmantes> listar() {
		return firmantesDao.listar();
	}

	@Override
	public Firmantes buscarPorId(Firmantes firmantes) {
		return firmantesDao.buscarPorId(firmantes);
	}

	@Override
	public List<Firmantes> buscar(Firmantes firmantes) {
		return firmantesDao.buscar(firmantes);
	}

	@Override
	public Firmantes Registrar(Firmantes firmantes) {
		return firmantesDao.Registrar(firmantes);
	}

	@Override
	public Firmantes Actualizar(Firmantes firmantes) {
		return firmantesDao.Actualizar(firmantes);
	}

	@Override
	public Firmantes Eliminar(Firmantes firmantes) {
		return firmantesDao.Eliminar(firmantes);
	}

}
