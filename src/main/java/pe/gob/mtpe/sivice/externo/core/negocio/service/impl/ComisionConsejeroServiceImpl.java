package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.ComiConsej;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ComisionConsejeroService;

@Service("ComisConsejeroService")
@Transactional(readOnly = true)
public class ComisionConsejeroServiceImpl implements ComisionConsejeroService {

	@Autowired
	private ComisionConsejeroDao comisiConsejeDao;

	@Override
	public List<ComiConsej> listar() {
		return comisiConsejeDao.listar();
	}

	@Override
	public ComiConsej buscarPorId(ComiConsej comiConsej) {
		return comisiConsejeDao.buscarPorId(comiConsej);
	}

	@Override
	public List<ComiConsej> buscar(Long comision) {
		return comisiConsejeDao.buscar(comision);
	}

	@Override
	public ComiConsej Registrar(ComiConsej comiConsej) {
		return comisiConsejeDao.Registrar(comiConsej);
	}

	@Override
	public ComiConsej Actualizar(ComiConsej comiConsej) {
		return comisiConsejeDao.Actualizar(comiConsej);
	}

	@Override
	public ComiConsej Eliminar(ComiConsej comiConsej) {
		return comisiConsejeDao.Eliminar(comiConsej);
	}

	@Override
	public List<ComiConsej> listaConsejerosPorComision(Long idcomision) {
		
		return null;
	}

}
