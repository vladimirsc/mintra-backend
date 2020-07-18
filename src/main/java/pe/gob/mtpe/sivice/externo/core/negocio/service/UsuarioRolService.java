package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol; 

public interface UsuarioRolService {

	List<UsuarioRol> listar();

	UsuarioRol buscarPorId(UsuarioRol usuarioperfil);
	
	UsuarioRol buscarPorCorreo(String correo);
	
	UsuarioRol buscarPorRol(UsuarioRol usuarioRol);

	List<UsuarioRol> buscar(UsuarioRol usuarioperfil);

	public UsuarioRol Registrar(UsuarioRol usuarioperfil);

	public UsuarioRol Actualizar(UsuarioRol usuarioperfil);

	public UsuarioRol Eliminar(UsuarioRol usuarioperfil);
	
	UsuarioRol deshabilitarrol(UsuarioRol usuarioRol);
	
	public UsuarioRol buscarRolPorIdusuario(UsuarioRol usuarioRol);

}
