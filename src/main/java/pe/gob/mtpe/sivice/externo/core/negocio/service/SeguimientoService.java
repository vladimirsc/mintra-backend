package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Seguimientos;

public interface SeguimientoService {
	List<Seguimientos> listar();

	Seguimientos buscarPorId(Seguimientos seguimientos);

	List<Seguimientos> buscar(Seguimientos seguimientos);

	public Seguimientos Registrar(Seguimientos seguimientos);

	public Seguimientos Actualizar(Seguimientos seguimientos);

	public Seguimientos Eliminar(Seguimientos seguimientos);
}
