package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioRolDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioRolService;

@Service("UsuarioRolService")
@Transactional(readOnly = true)
public class UsuarioRolServicempl implements UsuarioRolService {
	
	@Autowired
	private UsuarioRolDao usuarioRolDao;

	@Override
	public List<UsuarioRol> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioRol buscarPorId(UsuarioRol usuarioperfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioRol> buscar(UsuarioRol usuarioperfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioRol Registrar(UsuarioRol usuarioperfil) { 
		return usuarioRolDao.Registrar(usuarioperfil);
	}

	@Override
	public UsuarioRol Actualizar(UsuarioRol usuarioperfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioRol Eliminar(UsuarioRol usuarioperfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioRol buscarPorCorreo(String correo) { 
		return usuarioRolDao.buscarPorCorreo(correo);
	}

}
