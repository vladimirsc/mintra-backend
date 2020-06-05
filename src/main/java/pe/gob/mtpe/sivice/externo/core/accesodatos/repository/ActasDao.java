package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;

public interface ActasDao {

	List<Actas> listar();

	Actas buscarPorId(Actas actas);

	List<Actas> buscar(Actas actas);

	public Actas Registrar(Actas actas);

	public Actas Actualizar(Actas actas);

	public Actas Eliminar(Actas actas);
}
