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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Comisiones; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class ComisionDaoImpl extends BaseDao<Long, Comisiones> implements ComisionDao {

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Comisiones> listar(Comisiones comisiones) {
		EntityManager manager = createEntityManager();
		List<Comisiones> lista = manager
				.createQuery("SELECT c FROM Comisiones c INNER JOIN  c.region r WHERE  r.rEgionidpk=:idregion AND c.cFlgeliminado=:eliminado ORDER BY c.cOmisionidpk DESC")
				.setParameter("idregion",comisiones.getRegion().getrEgionidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Comisiones buscarPorId(Comisiones comisiones) {
		return buscarId(comisiones.getcOmisionidpk());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comisiones> buscar(Comisiones comisiones) {
		EntityManager manager = createEntityManager();
		List<Comisiones> lista = manager
				.createQuery("SELECT cm FROM Comisiones cm INNER JOIN cm.consejero cs  WHERE  cm.vCodcomision=:codigocomision OR cs.vDesnombre=:encargado OR cm.vNumdocapr=:numdocaprobacion AND  cm.cFlgeliminado=:eliminado ORDER BY cm.cOmisionidpk DESC")
				.setParameter("codigocomision", comisiones.getvCodcomision())
		        .setParameter("encargado", comisiones.getNombrencargado())
		        .setParameter("numdocaprobacion", comisiones.getvNumdocapr()) 
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Comisiones Registrar(Comisiones comisiones) {
		comisiones.setvCodcomision(GenerarCorrelativo(comisiones));
		guardar(comisiones); 
		return comisiones;
	}

	@Override
	public Comisiones Actualizar(Comisiones comisiones) {
		comisiones.setdFecmodifica(new Date());
		actualizar(comisiones);
		return comisiones;
	}

	@Override
	public Comisiones Eliminar(Comisiones comisiones) {
		comisiones.setdFecelimina(new Date());
		comisiones.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(comisiones);
		return comisiones;
	}
	
	
	public String GenerarCorrelativo(Comisiones comisiones) {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(c)+1 FROM Comisiones c WHERE c.region.rEgionidpk=:idregion AND c.consejo.cOnsejoidpk=:idconsejo AND c.tipocomision.tIpocomsidpk=:idtpcomision")
				.setParameter("idregion", comisiones.getRegion().getrEgionidpk())
				.setParameter("idconsejo", comisiones.getConsejo().getcOnsejoidpk())
				.setParameter("idtpcomision", comisiones.getTipocomision().gettIpocomsidpk())
				.getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,ConstantesUtil.COMISION_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

	@Override
	public List<Comisiones> buscarComision(String nombre_comision) {
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Comisiones> criteriaQuery = builder.createQuery(Comisiones.class);
		Root<Comisiones> root = criteriaQuery.from(Comisiones.class);
		
		Predicate valor1 = builder.like(root.get("vCodcomision"), "%"+nombre_comision+"%");
		criteriaQuery.where(valor1);
		Query<Comisiones> query = (Query<Comisiones>) manager.createQuery(criteriaQuery);
		List<Comisiones> resultado = query.getResultList();
		manager.close();
		return resultado;
	}

	 
	@Override
	public Comisiones consultaPorId(Comisiones comisiones) {
		
		/*
		Comisiones consultacomision = new Comisiones();
		EntityManager manager = createEntityManager();
		List<Comisiones> lista = manager
				.createQuery("SELECT c FROM Comisiones c WHERE c.cOmisionidpk=:idcomision  AND c.cFlgeliminado=:eliminado")
				.setParameter("idcomision", comisiones.getcOmisionidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		
		if(!lista.isEmpty()) {
			consultacomision = lista.get(0);
		}
		
		return consultacomision;*/
		
		
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Comisiones> criteriaQuery = builder.createQuery(Comisiones.class);
		Root<Comisiones> root = criteriaQuery.from(Comisiones.class);
		
		Predicate valor1 = builder.equal(root.get("cOmisionidpk"),comisiones.getcOmisionidpk());
		criteriaQuery.where(valor1);
		Query<Comisiones> query = (Query<Comisiones>) manager.createQuery(criteriaQuery);
		List<Comisiones> resultado = query.getResultList();
		manager.close();
		
		if(!resultado.isEmpty()) {
			comisiones = resultado.get(0);
		}
		
		return comisiones;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Comisiones buscarComisionPorNombre(String nombre_comision) {
		Comisiones consultacomision = new Comisiones();
		EntityManager manager = createEntityManager();
		List<Comisiones> lista = manager
				.createQuery("SELECT c FROM Comisiones c WHERE c.vCodcomision=:codigocomision  AND c.cFlgeliminado=:eliminado")
				.setParameter("codigocomision", nombre_comision)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		
		if(!lista.isEmpty()) {
			consultacomision = lista.get(0);
		}
		
		return consultacomision;
	}
	
	


}
