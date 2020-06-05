package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesion;

public interface SesionService {

	List<Sesion> listar();

	Sesion buscarPorId(Sesion sesion);

	List<Sesion> buscar(Sesion sesion);

	public Sesion Registrar(Sesion sesion);

	public Sesion Actualizar(Sesion sesion);

	public Sesion Eliminar(Sesion sesion);
}
