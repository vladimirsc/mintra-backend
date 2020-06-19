package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.List;

import org.springframework.stereotype.Component;

import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioPerfilRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioPerfilDao;

@Component
public class UsuarioPerfilDaoImpl extends BaseDao<Long, UsuarioPerfilRol> implements UsuarioPerfilDao {

	@Override
	public List<UsuarioPerfilRol> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioPerfilRol buscarPorId(UsuarioPerfilRol usuarioperfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioPerfilRol> buscar(UsuarioPerfilRol usuarioperfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioPerfilRol Registrar(UsuarioPerfilRol usuarioperfil) {
		guardar(usuarioperfil);
		return usuarioperfil;
	}

	@Override
	public UsuarioPerfilRol Actualizar(UsuarioPerfilRol usuarioperfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioPerfilRol Eliminar(UsuarioPerfilRol usuarioperfil) {
		// TODO Auto-generated method stub
		return null;
	}

}
