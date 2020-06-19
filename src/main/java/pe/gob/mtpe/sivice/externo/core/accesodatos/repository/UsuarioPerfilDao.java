package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioPerfilRol;

public interface UsuarioPerfilDao {
	List<UsuarioPerfilRol> listar();

	UsuarioPerfilRol buscarPorId(UsuarioPerfilRol usuarioperfil);

	List<UsuarioPerfilRol> buscar(UsuarioPerfilRol usuarioperfil);

	public UsuarioPerfilRol Registrar(UsuarioPerfilRol usuarioperfil);

	public UsuarioPerfilRol Actualizar(UsuarioPerfilRol usuarioperfil);

	public UsuarioPerfilRol Eliminar(UsuarioPerfilRol usuarioperfil);
}
