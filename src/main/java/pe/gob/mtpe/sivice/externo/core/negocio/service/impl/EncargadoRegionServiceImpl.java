package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.EncargadoRegion;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.EncargadoRegionDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.EncargadoRegionService;

@Service("EncargadoRegionService")
@Transactional(readOnly = true)
public class EncargadoRegionServiceImpl implements EncargadoRegionService {
	
	@Autowired
	private EncargadoRegionDao  encargadoRegionDao;

	@Override
	public EncargadoRegion registrar(EncargadoRegion encargadoRegion) { 
		return encargadoRegionDao.Registrar(encargadoRegion);
	}

	@Override
	public EncargadoRegion actualizar(EncargadoRegion encargadoRegion) {
		return encargadoRegionDao.Actualizar(encargadoRegion);
	}

	@Override
	public EncargadoRegion Eliminar(EncargadoRegion encargadoRegion) { 
		return encargadoRegionDao.Eliminar(encargadoRegion);
	}

	@Override
	public List<EncargadoRegion> listar() { 
		return encargadoRegionDao.listar();
	}

	@Override
	public EncargadoRegion buscarPorId(EncargadoRegion encargadoRegion) { 
		return encargadoRegionDao.buscarPorId(encargadoRegion);
	}

	@Override
	public List<EncargadoRegion> buscar(EncargadoRegion encargadoRegion) { 
		return encargadoRegionDao.buscar(encargadoRegion);
	}

	@Override
	public List<EncargadoRegion> listarFiltrado(Regiones region) { 
		return encargadoRegionDao.listarFiltrado(region);
	}

}
