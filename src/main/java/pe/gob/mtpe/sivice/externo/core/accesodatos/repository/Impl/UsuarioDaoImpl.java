package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

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

	@SuppressWarnings("unchecked")
	@Override
	public Usuarios buscarPorCorreo(String correo) {
		EntityManager manager = createEntityManager();
		List<Usuarios> lista = manager
				.createQuery("FROM Usuarios u WHERE u.username=:correo AND u.enabled=:activo  AND u.cFlgeliminado=:eliminado")
				.setParameter("correo", correo) 
				.setParameter("activo", "1") 
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if(lista.isEmpty()) {
			lista=null;
		}
		
		return lista.get(0);
	}

}
