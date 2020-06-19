package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioPerfilRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioPerfilDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioPerfilService;

@Service("UsuarioPerfilService")
@Transactional(readOnly = true)
public class UsuarioPerfilServiceImpl implements UsuarioPerfilService {
	
	@Autowired
	private UsuarioPerfilDao usuarioPerfilDao;

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
		return usuarioPerfilDao.Registrar(usuarioperfil);
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
