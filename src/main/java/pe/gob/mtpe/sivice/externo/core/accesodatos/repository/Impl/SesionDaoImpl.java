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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class SesionDaoImpl extends BaseDao<Long, Sesiones> implements SesionDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Sesiones> listar() {
		EntityManager manager = createEntityManager();
		List<Sesiones> lista = manager
				.createQuery("FROM Sesiones c WHERE c.cFlgeliminado=:eliminado ORDER BY c.sEsionidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Sesiones buscarPorId(Sesiones sesion) {
		Sesiones infosesion = buscarId(sesion.getsEsionidpk());
		return infosesion;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Sesiones buscarPorIdAsistencia(Sesiones sesion) {
		EntityManager manager = createEntityManager();
		List<Sesiones> lista = manager
				.createQuery("FROM Sesiones c WHERE c.sEsionidpk=:idsesion AND c.cFlgeliminado=:eliminado ORDER BY c.sEsionidpk DESC")
				.setParameter("idsesion", sesion.getsEsionidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if(!lista.isEmpty()) {
			sesion = lista.get(0);
		} 
		return sesion;
	}

	@Override
	public List<Sesiones> buscar(Sesiones sesion) {
		
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Sesiones> criteriaQuery = builder.createQuery(Sesiones.class);
		Root<Sesiones> root = criteriaQuery.from(Sesiones.class);
		//ListJoin<Sesiones, TipoSesiones> join = root.join(Sesiones_);
		//ListJoin<Sesiones,TipoSesiones> tiposesion = root.joinList("tIposesionidpk");
 
		
		sesion.setvCodsesion( (sesion.getvCodsesion()!=null)?sesion.getvCodsesion() : "" );
		sesion.setdFechaInicio( (sesion.getdFechaInicio()!=null)? sesion.getdFechaInicio() : FechasUtil.convertStringToDate("01-01-1880")) ;
		sesion.setdFechaFin((sesion.getdFechaFin()!=null)? sesion.getdFechaFin() : FechasUtil.convertStringToDate("01-01-1880")) ;
		//sesion.getTipoSesiones().settIposesionidpk( (sesion.getTipoSesiones().gettIposesionidpk()!=null)?sesion.getTipoSesiones().gettIposesionidpk() : 0 );
		
		Predicate valor1 = builder.equal(root.get("vCodsesion"), sesion.getvCodsesion()) ;
		Predicate valor2 = builder.between(root.get("dFecreacion"), sesion.getdFechaInicio(), sesion.getdFechaFin());
		Predicate valor3 = builder.equal(root.get("tipoSesiones") ,sesion.getTipoSesiones().gettIposesionidpk());
		Predicate finalbusqueda =builder.or(valor1,valor2,valor3);
		
		criteriaQuery.where(finalbusqueda);
		Query<Sesiones> query = (Query<Sesiones>) manager.createQuery(criteriaQuery);
		List<Sesiones> resultado = query.getResultList();
		manager.close();
		return resultado;
	}

	@Override
	public Sesiones Registrar(Sesiones sesion) {
		sesion.setvCodsesion(GenerarCorrelativo());
		guardar(sesion);
		return sesion;
	}

	@Override
	public Sesiones Actualizar(Sesiones sesion) {
		sesion.setdFecmodifica(new Date());
		actualizar(sesion);
		return sesion;
	}

	@Override
	public Sesiones Eliminar(Sesiones sesion) {
		sesion.setdFecelimina(new Date());
		sesion.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(sesion);
		return sesion;
	}
	
	public String GenerarCorrelativo() {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(c)+1 FROM Sesiones c").getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,ConstantesUtil.SESION_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

	@Override
	public List<Sesiones> buscarSesion(String nombresesion) {
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Sesiones> criteriaQuery = builder.createQuery(Sesiones.class);
		Root<Sesiones> root = criteriaQuery.from(Sesiones.class);
		
		Predicate valor1 = builder.like(root.get("vCodsesion"), "%"+nombresesion+"%");
		criteriaQuery.where(valor1);
		Query<Sesiones> query = (Query<Sesiones>) manager.createQuery(criteriaQuery);
		List<Sesiones> resultado = query.getResultList();
		manager.close();
		return resultado;
	}

	 

}
