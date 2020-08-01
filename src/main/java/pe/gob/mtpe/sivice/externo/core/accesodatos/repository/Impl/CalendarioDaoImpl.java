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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Calendarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.CalendarioDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class CalendarioDaoImpl extends BaseDao<Long, Calendarios> implements CalendarioDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Calendarios> listar(Calendarios calendario) {
		EntityManager manager = createEntityManager();
		List<Calendarios> lista = manager
				.createQuery("SELECT b FROM Calendarios b INNER JOIN b.region r WHERE r.rEgionidpk=:idregion AND b.consejo.cOnsejoidpk=:idconsejo AND b.cFlgeliminado=:eliminado ORDER BY b.cAlendarioidpk DESC")
				.setParameter("idregion",calendario.getRegion().getrEgionidpk())
				.setParameter("idconsejo", calendario.getConsejo().getcOnsejoidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Calendarios buscarPorId(Calendarios calendarios) {
		calendarios = buscarId(calendarios.getcAlendarioidpk());
		return calendarios;
	}

	@Override
	public List<Calendarios> buscar(Calendarios calendarios) {
		
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Calendarios> criteriaQuery = builder.createQuery(Calendarios.class);
		Root<Calendarios> root = criteriaQuery.from(Calendarios.class);
		
		calendarios.setvCoidigoComision( (calendarios.getvCoidigoComision()!=null)? calendarios.getvCoidigoComision() : "");
		calendarios.setvDesactividad( (calendarios.getvDesactividad()!=null)? "%"+calendarios.getvDesactividad()+"%" : "");
		calendarios.setdFechaInicioActividad( (calendarios.getvFechaInicioActividad()!=null)? FechasUtil.convertStringToDate(calendarios.getvFechaInicioActividad()): FechasUtil.convertStringToDate("01-01-1880"));
		calendarios.setdFechaFinActividad( (calendarios.getvFechaFinActividad()!=null)? FechasUtil.convertStringToDate(calendarios.getvFechaFinActividad()) : FechasUtil.convertStringToDate("01-01-1880") );
		calendarios.setcFlgejecuto( (calendarios.getcFlgejecuto()!=null)?calendarios.getcFlgejecuto() : "" );
		calendarios.setdFecejecuto( (calendarios.getvFechaActividad()!=null)? FechasUtil.convertStringToDate(calendarios.getvFechaActividad()) : FechasUtil.convertStringToDate("01-01-1880") );
		
		Predicate valor1 = builder.equal(root.get("cOmisionfk"),calendarios.getvCoidigoComision());
		Predicate valor2 = builder.like(root.get("vDesactividad"),calendarios.getvDesactividad());
		Predicate valor3 = builder.between(root.get("dFecactividad"),calendarios.getdFechaInicioActividad(),calendarios.getdFechaFinActividad());
		Predicate valor4=null;
		Predicate valor5 = builder.equal(root.get("region"),calendarios.getRegion().getrEgionidpk());
		Predicate valor6 = builder.and(valor5);
		
		if(!"".equals(calendarios.getcFlgejecuto()) && "1".equals(calendarios.getcFlgejecuto()) || ("1".equals(calendarios.getcFlgejecuto()))){
			valor4 = builder.equal(root.get("dFecejecuto"), calendarios.getdFecejecuto()) ;  
		}else {
			calendarios.setdFecejecuto(FechasUtil.convertStringToDate("01-01-1880"));
			valor4 =  builder.equal(root.get("dFecejecuto"), calendarios.getdFecejecuto()) ; 
		}
			
		Predicate valor7 = builder.or(valor1,valor2,valor3,valor4);
		Predicate finalbusqueda = builder.and(valor7,valor6);
		criteriaQuery.where(finalbusqueda);
		Query<Calendarios> query = (Query<Calendarios>) manager.createQuery(criteriaQuery);
		List<Calendarios> resultado = query.getResultList();
		manager.close();
		
		return resultado;
	}

	@Override
	public Calendarios Registrar(Calendarios calendarios) {
		guardar(calendarios);
		return calendarios;
	}

	@Override
	public Calendarios Actualizar(Calendarios calendarios) {
		calendarios.setdFecmodifica(new Date());
		actualizar(calendarios);
		return calendarios;
	}

	@Override
	public Calendarios Eliminar(Calendarios calendarios) {
		calendarios.setdFecelimina(new Date());
		calendarios.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(calendarios);
		return calendarios;
	}

}
