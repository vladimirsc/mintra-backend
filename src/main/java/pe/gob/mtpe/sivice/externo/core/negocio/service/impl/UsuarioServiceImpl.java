package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FijasDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioService;

@Service("UsuarioService")
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private FijasDao fijasDao;

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
	public Usuarios Registrar(Usuarios usuario) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuarios buscarPorCorreo(Usuarios usuario) {
		return usuarioDao.buscarPorCorreo(usuario.getUsername());

	}

}
