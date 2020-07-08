package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;

public interface UsuarioRolDao {
	List<UsuarioRol> listar();

	UsuarioRol buscarPorId(UsuarioRol usuarioRol);
	
	UsuarioRol buscarPorCorreo(String correo);
	
	UsuarioRol buscarPorRol(UsuarioRol usuarioRol);

	List<UsuarioRol> buscar(UsuarioRol usuarioRol);

	public UsuarioRol Registrar(UsuarioRol usuarioRol);

	public UsuarioRol Actualizar(UsuarioRol usuarioRol);

	public UsuarioRol Eliminar(UsuarioRol usuarioRol);
	
	 
}
