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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Correlativos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class SesionDaoImpl extends BaseDao<Long, Sesiones> implements SesionDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Sesiones> listar(Sesiones sesion) {
		EntityManager manager = createEntityManager();
		List<Sesiones> lista = manager
				.createQuery("SELECT s FROM Sesiones s INNER JOIN s.region r WHERE r.rEgionidpk=:idregion AND s.consejofk.cOnsejoidpk=:idconsejo  AND s.cFlgeliminado=:eliminado ORDER BY s.sEsionidpk DESC")
				.setParameter("idregion",sesion.getRegion().getrEgionidpk())
				.setParameter("idconsejo", sesion.getConsejofk().getcOnsejoidpk())
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
				.createQuery("SELECT c FROM Sesiones c WHERE c.sEsionidpk=:idsesion AND c.cFlgeliminado=:eliminado ORDER BY c.sEsionidpk DESC")
				.setParameter("idsesion", sesion.getsEsionidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if(!lista.isEmpty()) {
			sesion = lista.get(0);
		}else {
			sesion =null;
		}
		return sesion;
	}

	@Override
	public List<Sesiones> buscar(Sesiones sesion) {
		
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Sesiones> criteriaQuery = builder.createQuery(Sesiones.class);
		Root<Sesiones> root = criteriaQuery.from(Sesiones.class);
 
		sesion.setvCodsesion( (sesion.getvCodsesion()!=null)?sesion.getvCodsesion() : "" );
		sesion.setdFechaInicio( (sesion.getdFechaInicio()!=null)? sesion.getdFechaInicio() : FechasUtil.convertStringToDate("01-01-1880")) ;
		sesion.setdFechaFin((sesion.getdFechaFin()!=null)? sesion.getdFechaFin() : FechasUtil.convertStringToDate("01-01-1880")) ; 
		
		Predicate valor1 = builder.equal(root.get("vCodsesion"), sesion.getvCodsesion()) ;
		Predicate valor2 = builder.between(root.get("dFecreacion"), sesion.getdFechaInicio(), sesion.getdFechaFin());
		Predicate valor3 = builder.equal(root.get("tipoSesiones") ,sesion.getTipoSesiones().gettIposesionidpk());
		Predicate valor4 = builder.equal(root.get("region") ,sesion.getRegion().getrEgionidpk());
		Predicate valor7 = builder.equal(root.get("consejofk") ,sesion.getConsejofk().getcOnsejoidpk());
		Predicate valor5 =builder.and(valor4,valor7);
		Predicate valor6 =builder.or(valor1,valor2,valor3);
		Predicate finalbusqueda =builder.and(valor6,valor5);
		
		criteriaQuery.where(finalbusqueda);
		Query<Sesiones> query = (Query<Sesiones>) manager.createQuery(criteriaQuery);
		List<Sesiones> resultado = query.getResultList();
		manager.close();
		return resultado;
	}

	@Override
	public Sesiones Registrar(Sesiones sesion) {
		sesion.setvCodsesion(GenerarCorrelativo(sesion));
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
	
	public String GenerarCorrelativo(Sesiones sesion) {
		
		Long correlativoInicial= ValorInicial(sesion);
		
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(s)+1 FROM Sesiones s WHERE s.region.rEgionidpk=:idregion AND s.consejofk.cOnsejoidpk=:idconsejo AND s.tipoSesiones.tIposesionidpk=:idtiposesion")
				.setParameter("idregion", sesion.getRegion().getrEgionidpk())
				.setParameter("idconsejo", sesion.getConsejofk().getcOnsejoidpk())
				.setParameter("idtiposesion", sesion.getTipoSesiones().gettIposesionidpk())
				.getSingleResult();
		manager.close();
		if(correlativoInicial>0) {
			correlativo = correlativo + correlativoInicial;
		}
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,sesion.getTipoSesiones().gettIposesionidpk(),ConstantesUtil.SESION_ALIAS_CORRELATIVO);
		return StrcorrelativoFinal;
	}
	
	
	@SuppressWarnings("unchecked")
	public Long ValorInicial(Sesiones sesion) {
		Long valorInicial = Long.parseLong("0");
		EntityManager manager = createEntityManager();
		List<Correlativos> lista = manager.createQuery("SELECT c FROM Correlativos c WHERE vRegion=:nomregion AND vModulo=:nommodulo AND vConsejo=:nomconsejo AND vTipo=:nomtiposesion")
				.setParameter("nomregion", sesion.getRegion().getvDesnombre())
				.setParameter("nommodulo", ConstantesUtil.C_SESION_MODULO)
				.setParameter("nomconsejo", sesion.getConsejofk().getvDesnombre())
				.setParameter("nomtiposesion", sesion.getTipoSesiones().getvDesnombre())
				.getResultList();
		manager.close();
		
		if(!lista.isEmpty()) {
			valorInicial = lista.get(0).getnValorInicial();
		}else {
			valorInicial =Long.parseLong("0");
		}
		
		return valorInicial; 
	}

	@Override
	public List<Sesiones> buscarSesion(Sesiones sesion) {
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Sesiones> criteriaQuery = builder.createQuery(Sesiones.class);
		Root<Sesiones> root = criteriaQuery.from(Sesiones.class);
		
		Predicate valor1 = builder.like(root.get("vCodsesion"), "%"+sesion.getvCodsesion()+"%");
		Predicate valor2 = builder.equal(root.get("consejofk"), sesion.getConsejofk().getcOnsejoidpk());
		Predicate valor3 = builder.equal(root.get("region"), sesion.getRegion().getrEgionidpk());
		Predicate valor4 = builder.and(valor2,valor3);
		criteriaQuery.where(valor1,valor4);
		Query<Sesiones> query = (Query<Sesiones>) manager.createQuery(criteriaQuery);
		List<Sesiones> resultado = query.getResultList();
		manager.close();
		return resultado;
	}

	 

}
