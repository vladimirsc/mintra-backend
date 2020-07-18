package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil; 

@Component
public class UsuarioDaoImpl extends BaseDao<Long, Usuarios> implements UsuarioDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> listar(Usuarios usuario) {
		EntityManager manager = createEntityManager();
		List<Usuarios> lista = manager
				.createQuery("SELECT u FROM Usuarios u order by uSuarioidpk desc").getResultList();
		manager.close();
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> listarPorRegion(Usuarios usuario) { 
		EntityManager manager = createEntityManager();
		List<Usuarios> lista = manager
				.createQuery("SELECT u FROM Usuarios u INNER JOIN u.regiones r WHERE r.rEgionidpk=:idregion")
				.setParameter("idregion", usuario.getRegiones().getrEgionidpk()).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Usuarios buscarPorId(Usuarios usuario) {
		return  buscarId(usuario.getuSuarioidpk());
	}

	
	@Override
	public List<Usuarios> buscar(Usuarios usuario) {
		
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Usuarios> criteriaQuery = builder.createQuery(Usuarios.class);
		Root<Usuarios> root = criteriaQuery.from(Usuarios.class);
		
		usuario.getTipodocumento().settPdocumentoidpk( (usuario.getTipodocumento().gettPdocumentoidpk()!=null)? usuario.getTipodocumento().gettPdocumentoidpk() : 0L );
		usuario.setvNumdocumento( (usuario.getvNumdocumento()!=null)?usuario.getvNumdocumento() : "" );
		usuario.setvNombre( (usuario.getvNombre()!=null)? usuario.getvNombre() : "" );
		usuario.setvAppaterno( (usuario.getvAppaterno()!=null)?usuario.getvAppaterno() : "" );
		usuario.setvApmaterno( (usuario.getvApmaterno()!=null)?usuario.getvApmaterno() :""  );
		usuario.getRegiones().setrEgionidpk( (usuario.getRegiones().getrEgionidpk()!=null)?usuario.getRegiones().getrEgionidpk() : 0L );
		
		Predicate valor1 = builder.equal(root.get("tipodocumento"), usuario.getTipodocumento().gettPdocumentoidpk());
		Predicate valor2 = builder.equal(root.get("vNumdocumento"), usuario.getvNumdocumento());
		Predicate valor3 =builder.and(valor1,valor2);
		Predicate valor4 =builder.equal(root.get("vNombre"),usuario.getvNombre());
		Predicate valor5 =builder.equal(root.get("vAppaterno"),usuario.getvAppaterno());
		Predicate valor6 =builder.equal(root.get("vApmaterno"),usuario.getvApmaterno());
		Predicate valor7 =builder.equal(root.get("regiones"),usuario.getRegiones().getrEgionidpk());
		
		Predicate finalbusqueda =builder.or(valor3,valor4,valor5,valor6,valor7);
		criteriaQuery.where(finalbusqueda);
		Query<Usuarios> query = (Query<Usuarios>) manager.createQuery(criteriaQuery);
		List<Usuarios> resultado = query.getResultList();
		manager.close();
		
		return resultado;
	}

	@Override
	public Usuarios Registrar(Usuarios usuario) { 
		guardar(usuario);
		return usuario;
	}

	@Override
	public Usuarios Actualizar(Usuarios usuario) { 
		actualizar(usuario);
		return usuario;
	}

	@Override
	public Usuarios Eliminar(Usuarios usuario) {
		usuario.setdFecelimina(new Date());
		usuario.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(usuario);
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Usuarios buscarPorCorreo(String correo) {
		Usuarios usuario = new Usuarios();
		EntityManager manager = createEntityManager();
		List<Usuarios> lista = manager
				.createQuery("SELECT u FROM Usuarios u WHERE u.username=:correo")
				.setParameter("correo", correo).getResultList();
		manager.close();
		if(lista.isEmpty()) {
			usuario=null;
		}else {
			usuario = lista.get(0);
		}		
		return usuario;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Usuarios informacionUsuario(Long idusuario) {
		Usuarios usuario = new Usuarios();
		EntityManager manager = createEntityManager();
		List<Usuarios> lista = manager
				.createQuery("SELECT u FROM Usuarios u WHERE u.uSuarioidpk=:idusuario")
				.setParameter("idusuario", idusuario).getResultList();
		manager.close();
		if(lista.isEmpty()) {
			usuario=null;
		}else {
			usuario = lista.get(0);
		}		
		return usuario;
	}

	

}
