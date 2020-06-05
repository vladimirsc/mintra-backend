package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Temas;

public interface TemaDao {
	
	List<Temas> listar();

	Temas buscarPorId(Temas temas);

	List<Temas> buscar(Temas temas);

	public Temas Registrar(Temas temas);

	public Temas Actualizar(Temas temas);

	public Temas Eliminar(Temas temas);
}
