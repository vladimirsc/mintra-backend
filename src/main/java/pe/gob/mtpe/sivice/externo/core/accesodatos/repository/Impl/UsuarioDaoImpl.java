package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.List;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioDao;


@Component
public class UsuarioDaoImpl extends BaseDao<Long, Usuarios> implements UsuarioDao {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuarios Registrar(Usuarios usuario) { 
		guardar(usuario);
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

}
