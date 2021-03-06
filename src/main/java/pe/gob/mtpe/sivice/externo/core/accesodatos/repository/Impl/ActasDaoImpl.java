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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ActasDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class ActasDaoImpl extends BaseDao<Long, Actas> implements ActasDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<Actas> listar() {
		EntityManager manager = createEntityManager();
		List<Actas> lista = manager
				.createQuery("FROM Actas b WHERE b.cFlagelimina=:eliminado ORDER BY b.aCtaidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Actas buscarPorId(Actas actas) {
		actas = buscarId(actas.getaCtaidpk());
		return actas;
	}

	@Override
	public List<Actas> buscar(Actas actas) {
		    return null;
	}

	@Override
	public Actas Registrar(Actas actas) {
		actas.setvCodacta(GenerarCorrelativo(actas));
		guardar(actas);
		return actas;
	}

	@Override
	public Actas Actualizar(Actas actas) {
		actas.setdFecmodifica(new Date());
		actualizar(actas);
		return actas;
	}

	@Override
	public Actas Eliminar(Actas actas) {
		actas.setdFecelimina(new Date());
		actas.setcFlagelimina(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(actas);
		return actas;
	}

	public String GenerarCorrelativo(Actas actas) {
		Long tipoacta = Long.parseLong("0");
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(b)+1 FROM Actas b INNER JOIN b.sesionfk s WHERE s.region.rEgionidpk=:idregion AND s.consejofk.cOnsejoidpk=:idconsejo AND s.tipoSesiones.tIposesionidpk=:idtiposesion")
				.setParameter("idregion", actas.getSesionfk().getRegion().getrEgionidpk())
				.setParameter("idconsejo", actas.getSesionfk().getConsejofk().getcOnsejoidpk())
				.setParameter("idtiposesion", actas.getSesionfk().getTipoSesiones().gettIposesionidpk())
				.getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,tipoacta,ConstantesUtil.ACTA_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Actas buscarActaPorIdSesion(Long idSesion) {
		Actas acta = new Actas();
		EntityManager manager = createEntityManager();
		List<Actas> lista = manager
				.createQuery("SELECT a FROM Actas a INNER JOIN a.sesionfk s WHERE s.sEsionidpk=:sesion AND a.cFlagelimina=:eliminado ")
				.setParameter("sesion", idSesion)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		 if(lista.isEmpty()) {
			 acta =null;
		 }else {
			 acta = lista.get(0);
		 }
		return acta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acuerdos> listaAcuerdosPorActa(Actas actas) {
		EntityManager manager = createEntityManager();
		List<Acuerdos> lista = manager
				.createQuery("SELECT a FROM Acuerdos a INNER  JOIN a.acta ac WHERE ac.aCtaidpk=:actafk AND a.cFlgeliminado=:eliminado ORDER BY a.aCuerdoidpk DESC")
				.setParameter("actafk", actas.getaCtaidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	 
	@Override
	public List<Actas> buscarActasPorSesion(Actas actas) {
		/* 
		EntityManager manager = createEntityManager();
		List<Actas> lista = manager
				.createQuery("SELECT a  FROM Actas  a INNER JOIN  a.sesionfk s "
						+ " WHERE s.vCodsesion=:codigosesion  OR s.dFecreacion BETWEEN :fechainicio  AND :fechafin OR s.tipoSesiones.tIposesionidpk=:codtiposesion  AND a.cFlagelimina=:eliminado")
				.setParameter("codigosesion", actas.getvCodigoSesion())
				.setParameter("codtiposesion", actas.getnTipoSesion())
				.setParameter("fechainicio",  (actas.getVfechaInicio()!=null)? FechasUtil.convertStringToDate(actas.getVfechaInicio()) : FechasUtil.convertStringToDate("01-01-1880"))
				.setParameter("fechafin",     (actas.getVfechafin()!=null)? FechasUtil.convertStringToDate(actas.getVfechafin()) : FechasUtil.convertStringToDate("01-01-1880"))
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista; */
		 
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Actas> criteriaQuery = builder.createQuery(Actas.class);
		Root<Actas> root = criteriaQuery.from(Actas.class);
		
		 Date fechaInicio = new Date();
		 Date fechaFin = new Date();
		if(actas.getVfechaInicio()!=null && actas.getVfechafin()!=null) {
			fechaInicio = FechasUtil.convertStringToDate(actas.getVfechaInicio());
			fechaFin = FechasUtil.convertStringToDate(actas.getVfechafin());
		}else {
			fechaInicio = FechasUtil.convertStringToDate("01-01-1880");
			fechaFin =  FechasUtil.convertStringToDate("01-01-1880");
		}
		
		Predicate valor1 = builder.equal(root.get("sesionfk").get("region"), actas.getNregion());
		Predicate valor2 = builder.equal(root.get("sesionfk").get("consejofk"),actas.getnTipoConsejo());
		Predicate valor3 = builder.equal(root.get("sesionfk").get("vCodsesion"), actas.getvCodigoSesion());
		Predicate valor4 = builder.equal(root.get("sesionfk").get("tipoSesiones"), actas.getnTipoSesion());
		Predicate valor5 = builder.between(root.get("sesionfk").get("dFecreacion"),fechaInicio,fechaFin);
		Predicate vsqland = builder.and(valor1,valor2);
		Predicate vsqlor =  builder.or(valor3,valor4,valor5);
		criteriaQuery.where(vsqlor,vsqland);
		Query<Actas> query = (Query<Actas>) manager.createQuery(criteriaQuery);
		List<Actas> resultado = query.getResultList();
		manager.close();
		return resultado;  
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actas> listarActasPorSesion(Actas actas) {
		EntityManager manager = createEntityManager();
		List<Actas> lista = manager
				.createQuery("SELECT a  FROM Actas  a INNER JOIN  a.sesionfk s INNER JOIN s.region r  WHERE r.rEgionidpk=:codigoregion AND s.consejofk.cOnsejoidpk=:idconsejo AND a.cFlagelimina=:eliminado ORDER BY s.sEsionidpk DESC")
				.setParameter("codigoregion", actas.getNregion())
				.setParameter("idconsejo", actas.getnTipoConsejo())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}
 
	
 
}
