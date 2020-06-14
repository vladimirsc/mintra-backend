package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Firmantes;

public interface FirmantesDao {
	
	List<Firmantes> listar();

	Firmantes buscarPorId(Firmantes firmantes);

	List<Firmantes> buscar(Firmantes firmantes);

	public Firmantes Registrar(Firmantes firmantes);

	public Firmantes Actualizar(Firmantes firmantes);

	public Firmantes Eliminar(Firmantes firmantes);
	
	List<Firmantes> listarFirmantesPorActa(Long idacta);
}
