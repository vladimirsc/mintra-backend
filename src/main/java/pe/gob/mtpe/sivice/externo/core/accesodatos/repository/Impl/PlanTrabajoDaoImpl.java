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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.PlanTrabajo;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.PlanTrabajoDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class PlanTrabajoDaoImpl extends BaseDao<Long, PlanTrabajo> implements PlanTrabajoDao {

	//private static final Logger logger = LoggerFactory.getLogger(PlanTrabajoDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanTrabajo> listar(PlanTrabajo planTrabajo) {
		EntityManager manager = createEntityManager();
		List<PlanTrabajo> lista = manager
				.createQuery("SELECT c FROM PlanTrabajo c INNER JOIN c.region r WHERE r.rEgionidpk=:idregion AND c.consejo.cOnsejoidpk=:idconsejo  AND c.cFlgeliminado=:eliminado ORDER BY c.pLantrabidpk DESC")
				.setParameter("idregion", planTrabajo.getRegion().getrEgionidpk())
				.setParameter("idconsejo", planTrabajo.getConsejo().getcOnsejoidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public PlanTrabajo buscarPorId(PlanTrabajo planTrabajo) {
		PlanTrabajo plan = buscarId(planTrabajo.getpLantrabidpk());
		return plan;
	}

	@Override
	public List<PlanTrabajo> buscar(PlanTrabajo planTrabajo) {
		 
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<PlanTrabajo> criteriaQuery = builder.createQuery(PlanTrabajo.class);
		Root<PlanTrabajo> root = criteriaQuery.from(PlanTrabajo.class);
		
		planTrabajo.setdFecaprobacion( (planTrabajo.getdFecaprobacion()!=null)?planTrabajo.getdFecaprobacion() : FechasUtil.convertStringToDate("01-01-1880")  );
		planTrabajo.setdFecaprobacionfin( (planTrabajo.getdFecaprobacionfin() !=null)?planTrabajo.getdFecaprobacionfin() : FechasUtil.convertStringToDate("01-01-1880"));
		
		planTrabajo.setdFecinicio( (planTrabajo.getdFecinicio()!=null)? planTrabajo.getdFecinicio() : FechasUtil.convertStringToDate("01-01-1880"));
		planTrabajo.setdFecfin((planTrabajo.getdFecfin()!=null)? planTrabajo.getdFecfin() : FechasUtil.convertStringToDate("01-01-1880"));
		
		Predicate valor1 = builder.equal(root.get("vCodigoplantrab"), planTrabajo.getvCodigoplantrab());
		Predicate valor2 = builder.between(root.get("dFecaprobacion"), planTrabajo.getdFecaprobacion(), planTrabajo.getdFecaprobacionfin());
		Predicate valor3 = builder.between(root.get("dFecinicio"), planTrabajo.getdFecinicio(),planTrabajo.getdFecfin());
		Predicate valor4 = builder.equal(root.get("region"),planTrabajo.getRegion().getrEgionidpk());
		Predicate valor5 = builder.or(valor1,valor2,valor3); 
		Predicate finalbusqueda = builder.and(valor5,valor4);
		criteriaQuery.where(finalbusqueda);
		Query<PlanTrabajo> query = (Query<PlanTrabajo>) manager.createQuery(criteriaQuery);
		List<PlanTrabajo> resultado = query.getResultList();
		manager.close(); 
		return resultado;
	}

	@Override
	public PlanTrabajo Registrar(PlanTrabajo planTrabajo) {
		planTrabajo.setvCodigoplantrab(GenerarCorrelativo(planTrabajo));
		planTrabajo.setdFecreg(new Date());
		guardar(planTrabajo);
		return planTrabajo;
	}

	@Override
	public PlanTrabajo Actualizar(PlanTrabajo planTrabajo) {
		planTrabajo.setdFecmodifica(FechasUtil.fechaActual());
		planTrabajo.setdFecmodifica(new Date());
		actualizar(planTrabajo);
		return planTrabajo;
	}

	@Override
	public PlanTrabajo Eliminar(PlanTrabajo planTrabajo) {
		planTrabajo.setdFecelimina(FechasUtil.fechaActual());
		planTrabajo.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		planTrabajo.setdFecelimina(new Date());
		actualizar(planTrabajo);
		return planTrabajo;
	}
	
	
	public String GenerarCorrelativo(PlanTrabajo planTrabajo) {
		Long tipoplan = Long.parseLong("0"); 
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(p)+1 FROM PlanTrabajo p WHERE p.region.rEgionidpk=:idregion AND p.consejo.cOnsejoidpk=:idconsejo")
				.setParameter("idregion", planTrabajo.getRegion().getrEgionidpk())
				.setParameter("idconsejo", planTrabajo.getConsejo().getcOnsejoidpk())
				.getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,tipoplan,ConstantesUtil.PLANTRABAJO_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

}
