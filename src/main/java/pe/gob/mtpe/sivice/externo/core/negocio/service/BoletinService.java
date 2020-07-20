package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Boletines;

public interface BoletinService {
	
	public List<Boletines> listar(Boletines boletines);

	public Boletines buscarPorId(Boletines boletines);

	public List<Boletines> buscarRangoFechas(Boletines boletines);

	public List<Boletines> BuscarPorCodigo(Boletines boletines);

	public Boletines Registrar(Boletines boletines);

	public Boletines Actualizar(Boletines boletines);

	public Boletines Eliminar(Boletines boletines);
	
	List<Boletines> buscarBoletines(Boletines boletines);
}
