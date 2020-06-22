package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioRolDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class UsuarioRolDaoImpl extends BaseDao<Long, UsuarioRol> implements UsuarioRolDao {

	@Override
	public List<UsuarioRol> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioRol buscarPorId(UsuarioRol usuarioRol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioRol> buscar(UsuarioRol usuarioRol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioRol Registrar(UsuarioRol usuarioRol) {
		guardar(usuarioRol);
		return usuarioRol;
	}

	@Override
	public UsuarioRol Actualizar(UsuarioRol usuarioRol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioRol Eliminar(UsuarioRol usuarioRol) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UsuarioRol buscarPorCorreo(String correo) {
		UsuarioRol usuariorol = new UsuarioRol();
		EntityManager manager = createEntityManager();
		List<UsuarioRol> lista = manager
				.createQuery("SELECT ur FROM UsuarioRol ur INNER JOIN ur.roles r INNER JOIN ur.usuario u WHERE u.username=:correo AND u.enabled=:habilitado AND ur.cFlgactivo=:activo AND u.cFlgeliminado=:eliminado")
				.setParameter("correo", correo) 
				.setParameter("habilitado", "1") 
				.setParameter("activo", "1") 
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if(lista.isEmpty()) {
			usuariorol=null;
		}else {
			usuariorol=lista.get(0); 
		}
		
		return usuariorol;
	}

}
