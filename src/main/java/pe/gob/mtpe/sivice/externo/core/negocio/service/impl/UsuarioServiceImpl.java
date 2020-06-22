package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Roles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FijasDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioRolService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioService;

@Service("UsuarioService")
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private FijasDao fijasDao;
	
	@Autowired
	private UsuarioRolService usuarioRolService;

	@Override
	public List<Usuarios> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuarios buscarPorId(Usuarios usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuarios> buscar(Usuarios usuario) { 
		return usuarioDao.buscar(usuario);
	}

	@Override
	public Usuarios Registrar(Usuarios usuario,Long idtipodocumento,Long rol) {
		if(idtipodocumento==null || rol==null) {
			return null;
		}
		TipoDocumentos tipoDocumentos = new TipoDocumentos();
		tipoDocumentos.settPdocumentoidpk(idtipodocumento);
		tipoDocumentos = fijasDao.buscarPorCodigoTipoDocumento(tipoDocumentos);

		Roles roles = new Roles();
		roles.setrOlidpk(rol); 
		roles=fijasDao.buscaRoles(roles);
		
		usuario.setTipodocumento(tipoDocumentos);
		usuario= usuarioDao.Registrar(usuario);
		
		if(usuario==null) {
			return null;
		}
		
		if(roles==null) {
			return null;
		}
		
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setRoles(roles);
		usuarioRol.setUsuario(usuario);
		usuarioRol = usuarioRolService.Registrar(usuarioRol);
		return usuario;
		
	}

	@Override
	public Usuarios Actualizar(Usuarios usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuarios Eliminar(Usuarios usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuarios buscarPorCorreo(String correo) {
		// TODO Auto-generated method stub
		return usuarioDao.buscarPorCorreo(correo);
	}

}
