package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acciones;

public interface AccionesDao {

	List<Acciones> listar();

	Acciones buscarPorId(Acciones acciones);

	List<Acciones> buscar(Acciones acciones);

	public Acciones Registrar(Acciones acciones);

	public Acciones Actualizar(Acciones acciones);

	public Acciones Eliminar(Acciones acciones);
	
	List<Acciones> listarAccionesPorAcuerdo(Long idacuerdo);
}
