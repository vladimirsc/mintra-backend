package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;

public interface SesionService {

	List<Sesiones> listar();

	Sesiones buscarPorId(Sesiones sesion);

	List<Sesiones> buscar(Sesiones sesion);

	public Sesiones Registrar(Long consejo,Long comision,Long tipoSesion, Sesiones sesion);

	public Sesiones Actualizar(Long comision,Long tipoSesion,Sesiones sesion);

	public Sesiones Eliminar(Sesiones sesion);
}
