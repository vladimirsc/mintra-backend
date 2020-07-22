package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Roles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
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
		return usuarioRolDao.buscarPorId(usuarioperfil);
	}

	@Override
	public List<UsuarioRol> buscar(UsuarioRol usuarioperfil) { 
		return usuarioRolDao.buscar(usuarioperfil);
	}

	@Override
	public UsuarioRol Registrar(UsuarioRol usuarioperfil) { 
		List<UsuarioRol> listaRoles = new ArrayList<UsuarioRol>();
		 listaRoles = usuarioRolDao.buscar(usuarioperfil);
		 
		 if(!listaRoles.isEmpty()) {
			 for(UsuarioRol usurol : listaRoles) {
				 UsuarioRol usuarioRolActualizar = new UsuarioRol();
				 Usuarios usuario = new Usuarios();
				 Roles rol = new Roles();
				 
				 usuario.setuSuarioidpk(usurol.getUsuario().getuSuarioidpk());
				 rol.setrOlidpk(usurol.getRoles().getrOlidpk());
				 
				 
				 usuarioRolActualizar.setuSuariorolidpk(usurol.getuSuariorolidpk());
				 usuarioRolActualizar.setUsuario(usuario);
				 usuarioRolActualizar.setRoles(rol);
				 usuarioRolActualizar.setcFlgactivo("0");
				 usuarioRolActualizar.setcFlgelimino("1");
				 usuarioRolActualizar= usuarioRolDao.Actualizar(usuarioRolActualizar);	 
			 }
 
		 }
		 
		 Usuarios usuario = new Usuarios();
		 Roles rol = new Roles();
		 
		 usuario.setuSuarioidpk(usuarioperfil.getUsuario().getuSuarioidpk());
		 rol.setrOlidpk(usuarioperfil.getRoles().getrOlidpk());
		 
		 usuarioperfil.setUsuario(usuario);
		 usuarioperfil.setRoles(rol);
		 usuarioperfil.setcFlgactivo("1");
		 usuarioperfil.setcFlgelimino("0");
		 usuarioperfil= usuarioRolDao.Registrar(usuarioperfil);
		 
		return usuarioperfil;
	}

	@Override
	public UsuarioRol Actualizar(UsuarioRol usuarioperfil) { 
		
		 List<UsuarioRol> listaRoles = new ArrayList<UsuarioRol>();
		 listaRoles = usuarioRolDao.buscar(usuarioperfil);
		 
		 if(!listaRoles.isEmpty()) {
			 for(UsuarioRol usurol : listaRoles) {
				 UsuarioRol usuarioRolActualizar = new UsuarioRol();
				 Usuarios usuario = new Usuarios();
				 Roles rol = new Roles();
				 
				 usuario.setuSuarioidpk(usurol.getUsuario().getuSuarioidpk());
				 rol.setrOlidpk(usurol.getRoles().getrOlidpk());
				 
				 
				 usuarioRolActualizar.setuSuariorolidpk(usurol.getuSuariorolidpk());
				 usuarioRolActualizar.setUsuario(usuario);
				 usuarioRolActualizar.setRoles(rol);
				 usuarioRolActualizar.setcFlgactivo("0");
				 usuarioRolActualizar.setcFlgelimino("1");
				 usuarioRolActualizar= usuarioRolDao.Actualizar(usuarioRolActualizar);	 
			 }
 
		 }
		
		usuarioperfil.setcFlgactivo("1");
		usuarioperfil.setcFlgelimino("0");
		return usuarioRolDao.Actualizar(usuarioperfil);
	}

	@Override
	public UsuarioRol Eliminar(UsuarioRol usuarioperfil) { 
		return usuarioRolDao.Eliminar(usuarioperfil);
	}

	@Override
	public UsuarioRol buscarPorCorreo(String correo) { 
		return usuarioRolDao.buscarPorCorreo(correo);
	}

	@Override
	public UsuarioRol buscarPorRol(UsuarioRol usuarioRol) { 
		return usuarioRolDao.buscarPorRol(usuarioRol);
	}

	@Override
	public UsuarioRol deshabilitarrol(UsuarioRol usuarioRol) {
		
		 List<UsuarioRol> listaRoles = new ArrayList<UsuarioRol>();
		 listaRoles = usuarioRolDao.buscar(usuarioRol);
		 
		 if(!listaRoles.isEmpty()) {
			 for(UsuarioRol usurol : listaRoles) {
				 UsuarioRol usuarioRolActualizar = new UsuarioRol();
				 Usuarios usuario = new Usuarios();
				 Roles rol = new Roles();
				 
				 usuario.setuSuarioidpk(usurol.getUsuario().getuSuarioidpk());
				 rol.setrOlidpk(usurol.getRoles().getrOlidpk());
				 
				 usuarioRolActualizar.setuSuariorolidpk(usurol.getuSuariorolidpk());
				 usuarioRolActualizar.setUsuario(usuario);
				 usuarioRolActualizar.setRoles(rol);
				 usuarioRolActualizar.setcFlgactivo("0");
				 usuarioRolActualizar.setcFlgelimino("1");
				 usuarioRolActualizar= usuarioRolDao.Actualizar(usuarioRolActualizar);	 
			 }
			 
			 Usuarios usuario = new Usuarios();
			 Roles rol = new Roles();
			 
			 usuario.setuSuarioidpk(usuarioRol.getUsuario().getuSuarioidpk());
			 rol.setrOlidpk(usuarioRol.getRoles().getrOlidpk());
			 
			 usuarioRol.setuSuariorolidpk(usuarioRol.getuSuariorolidpk());
			 usuarioRol.setUsuario(usuario);
			 usuarioRol.setRoles(rol);
			 usuarioRol.setcFlgactivo("1");
			 usuarioRol.setcFlgelimino("0");
			 usuarioRol= usuarioRolDao.Actualizar(usuarioRol);
		 }
		 
		return usuarioRol;
	}

	@Override
	public UsuarioRol buscarRolPorIdusuario(UsuarioRol usuarioRol) { 
		return usuarioRolDao.buscarRolPorIdusuario(usuarioRol);
	}

	 

}
