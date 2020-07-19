package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
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
		return null;
	}

	@Override
	public UsuarioRol buscarPorId(UsuarioRol usuarioRol) { 
		return buscarId(usuarioRol.getuSuariorolidpk());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioRol> buscar(UsuarioRol usuarioRol) {
		EntityManager manager = createEntityManager();
		List<UsuarioRol> lista = manager
				.createQuery("SELECT ur FROM UsuarioRol ur INNER JOIN ur.roles r INNER JOIN ur.usuario u WHERE u.uSuarioidpk=:idusuario")
				.setParameter("idusuario", usuarioRol.getUsuario().getuSuarioidpk()).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public UsuarioRol Registrar(UsuarioRol usuarioRol) {
		guardar(usuarioRol);
		return usuarioRol;
	}

	@Override
	public UsuarioRol Actualizar(UsuarioRol usuarioRol) {
		usuarioRol.setdFecreg(new Date());
		 actualizar(usuarioRol);
		 return usuarioRol;
	} 

	@Override
	public UsuarioRol Eliminar(UsuarioRol usuarioRol) { 
		usuarioRol.setdFecelimina(new Date());
		usuarioRol.setcFlgelimino(ConstantesUtil.C_INDC_ACTIVO);
		usuarioRol.setcFlgactivo(ConstantesUtil.C_INDC_INACTIVO);
		actualizar(usuarioRol);
		return usuarioRol;
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

	@SuppressWarnings("unchecked")
	@Override
	public UsuarioRol buscarPorRol(UsuarioRol usuarioRol) {
		UsuarioRol usuariorol = new UsuarioRol();
		EntityManager manager = createEntityManager();
		List<UsuarioRol> lista = manager
				.createQuery("SELECT ur FROM UsuarioRol ur INNER JOIN ur.roles r INNER JOIN ur.usuario u WHERE u.uSuarioidpk=:idusuario AND r.rOlidpk=:idrol")
				.setParameter("idusuario", usuarioRol.getUsuario().getuSuarioidpk())
				.setParameter("idrol", usuarioRol.getRoles().getrOlidpk()).getResultList();
		manager.close();
		if(lista.isEmpty()) {
			usuariorol=null;
		}else {
			usuariorol=lista.get(0); 
		}
		
		return usuariorol;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UsuarioRol buscarRolPorIdusuario(UsuarioRol usuarioRol) {
		UsuarioRol usuariorol = new UsuarioRol();
		EntityManager manager = createEntityManager();
		List<UsuarioRol> lista = manager
				.createQuery("SELECT ur FROM UsuarioRol ur INNER JOIN ur.roles r INNER JOIN ur.usuario u WHERE u.uSuarioidpk=:idusuario AND u.enabled=:habilitado AND ur.cFlgactivo=:activo AND u.cFlgeliminado=:eliminado")
				.setParameter("idusuario", usuarioRol.getUsuario().getuSuarioidpk()) 
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
