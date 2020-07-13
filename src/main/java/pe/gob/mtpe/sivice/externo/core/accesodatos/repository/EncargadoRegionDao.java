package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.EncargadoRegion;

public interface EncargadoRegionDao {
	
	EncargadoRegion buscarPorId (EncargadoRegion encargadoRegion);
	List<EncargadoRegion> buscar (EncargadoRegion encargadoRegion);
	EncargadoRegion Registrar(EncargadoRegion encargadoRegion);
	EncargadoRegion Actualizar(EncargadoRegion encargadoRegion);
	EncargadoRegion Eliminar(EncargadoRegion encargadoRegion);
	List<EncargadoRegion> listar();
}
