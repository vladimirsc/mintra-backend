package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.EncargadoRegion;

public interface EncargadoRegionService {
  
	EncargadoRegion buscarPorId (EncargadoRegion encargadoRegion);
	EncargadoRegion registrar (EncargadoRegion encargadoRegion);
	EncargadoRegion actualizar (EncargadoRegion encargadoRegion);
	EncargadoRegion Eliminar (EncargadoRegion encargadoRegion);
	List<EncargadoRegion> listar ();
}
