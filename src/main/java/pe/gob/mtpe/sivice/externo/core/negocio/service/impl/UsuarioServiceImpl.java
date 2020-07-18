package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Service("UsuarioService")
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

 
 
	@Override
	public List<Usuarios> listar(Usuarios usuario) {
		 
		List<Usuarios> lista = new ArrayList<Usuarios>();
		if(ConstantesUtil.C_ROLE_ADMCONSSAT.equals(usuario.getVrol())) {
			lista =usuarioDao.listar(usuario);
			
		}else if(ConstantesUtil.C_ROLE_ADMCORSSAT.equals(usuario.getVrol())) {
			
			
			//region = fijasDao.buscarPorNombreRegion(usuario.getVregion());
			
			 
				Regiones region = new Regiones();
				region.setrEgionidpk(usuario.getRegiones().getrEgionidpk());
				
				usuario.setRegiones(region);
				lista =usuarioDao.listarPorRegion(usuario);
			 
 	
		}
		
		return lista;
	}

	@Override
	public Usuarios buscarPorId(Usuarios usuario) { 
		return usuarioDao.buscarPorId(usuario);
	}

	@Override
	public List<Usuarios> buscar(Usuarios usuario) {
		return usuarioDao.buscar(usuario);
	}

	@Override
	public Usuarios Registrar(Usuarios usuario) {
		 usuario.setPassword(usuario.getPassword());
		 usuarioDao.Registrar(usuario);
		return usuario;

	}

	@Override
	public Usuarios Actualizar(Usuarios usuario) {
		usuario=usuarioDao.Actualizar(usuario);
		return usuario;
	}

	@Override
	public Usuarios Eliminar(Usuarios usuario) { 
		usuario=usuarioDao.Eliminar(usuario);
		return usuario;
	}

	@Override
	public Usuarios buscarPorCorreo(Usuarios usuario) {
		return usuarioDao.buscarPorCorreo(usuario.getUsername());

	}

}
