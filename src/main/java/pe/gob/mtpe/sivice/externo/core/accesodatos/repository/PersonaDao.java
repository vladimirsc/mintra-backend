package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;

public interface PersonaDao {
	
	List<Usuarios> listar();

	Usuarios buscarPorId(Usuarios persona);

	List<Usuarios> buscar(Usuarios personas); 
	
	public Usuarios Registrar(Usuarios persona);

	public Usuarios ActualizarPersona(Usuarios personas);

	public Usuarios EliminarPersona(Usuarios personas);
	
	public Usuarios buscarTipoDocNumero(Usuarios personas);

}
