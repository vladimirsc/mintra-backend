package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.ArrayList;
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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class ConsejeroDaoImpl extends BaseDao<Long, Consejeros> implements ConsejeroDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Consejeros> listar(Consejeros consejero) {
		EntityManager manager = createEntityManager();
		List<Consejeros> lista = manager
				.createQuery("FROM Consejeros c WHERE c.rEgionfk=:region AND c.cOnsejofk=:consejo  AND c.cFlgeliminado=:eliminado ORDER BY c.cOnsejeroidpk DESC")
				.setParameter("region", consejero.getrEgionfk())
				.setParameter("consejo", consejero.getcOnsejofk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();

		if (lista.isEmpty()) {
			List<Consejeros> listavacia  = new ArrayList<Consejeros>();
			lista =listavacia;
		}

		return lista;
	}

	@Override
	public Consejeros buscarPorId(Consejeros consejero) {
		Consejeros consejeros = buscarId(consejero.getcOnsejeroidpk());
		return consejeros;
	}

	 
	@Override
	public List<Consejeros> buscar(Consejeros consejero) {
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Consejeros> criteriaQuery = builder.createQuery(Consejeros.class);
		Root<Consejeros> root = criteriaQuery.from(Consejeros.class);

		consejero.setvNumdocasig((consejero.getvNumdocasig()!=null)?     consejero.getvNumdocasig()   :"");
		consejero.setdFecinicio((consejero.getdFecinicio()!= null) ?     consejero.getdFecinicio()    :FechasUtil.convertStringToDate("01-01-1880"));
		consejero.setdFecfin( (consejero.getdFecfin()!=null)?            consejero.getdFecfin()       :FechasUtil.convertStringToDate("01-01-1880") );
		consejero.setvNumdocumento((consejero.getvNumdocumento()!=null)? consejero.getvNumdocumento() : "");
		consejero.setvDesnombre( (consejero.getvDesnombre()!=null)?      consejero.getvDesnombre()    : "");
		consejero.setvDesappaterno( (consejero.getvDesappaterno()!=null)?consejero.getvDesappaterno() : "");
		consejero.setvDesapmaterno((consejero.getvDesapmaterno()!=null)? consejero.getvDesapmaterno() : "");
		consejero.setvEntidad((consejero.getvEntidad()!=null)?           consejero.getvEntidad()      : "");
		 
		Predicate valor1 = builder.equal(root.get("vNumdocasig"), consejero.getvNumdocasig()) ;
		Predicate valor2 = builder.between(root.get("dFecinicio"), consejero.getdFecinicio(), consejero.getdFecfin());
		Predicate valor3 = builder.equal(root.get("vNumdocumento"),consejero.getvNumdocumento()) ;
		Predicate valor4 = builder.equal(root.get("vDesnombre"), consejero.getvDesnombre()) ;
		Predicate valor5 = builder.equal(root.get("vDesappaterno"), consejero.getvDesappaterno()) ;
		Predicate valor6 = builder.equal(root.get("vDesapmaterno"), consejero.getvDesapmaterno()) ;
		Predicate valor7 = builder.equal(root.get("vEntidad"),consejero.getvEntidad()) ;
		Predicate valor8 = builder.equal(root.get("rEgionfk"),consejero.getrEgionfk()) ;
		Predicate valor9 = builder.equal(root.get("cOnsejofk"),consejero.getcOnsejofk()) ;
		Predicate valor10 = builder.or(valor1,valor2,valor3,valor4,valor5,valor6,valor7); 
		Predicate valor11 = builder.and(valor8,valor9);
		Predicate finalbusqueda =builder.and(valor10,valor11);
		
		criteriaQuery.where(finalbusqueda);
		Query<Consejeros> query = (Query<Consejeros>) manager.createQuery(criteriaQuery);
		List<Consejeros> resultado = query.getResultList();
		manager.close();
		return resultado;
	}

	@Override
	public Consejeros Registrar(Consejeros consejero) {
		guardar(consejero);
		return consejero;

	}

	@Override
	public Consejeros Actualizar(Consejeros consejero) {
		consejero.setdFecmodifica(new Date());
		actualizar(consejero);
		return consejero;
	}

	@Override
	public Consejeros Eliminar(Consejeros consejero) {
		consejero.setdFecelimina(new Date());
		consejero.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(consejero);
		return consejero;
	}

	@Override
	public Consejeros buscarPorTipoDocNumero(Consejeros consejero) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consejeros> listarConsejerosPorComision(Consejeros consejero) {
		EntityManager manager = createEntityManager();
		List<Consejeros> lista = manager
				.createQuery("FROM Consejeros c WHERE c.rEgionfk=:region AND c.cOmisionfk=:consejo  AND c.cFlgeliminado=:eliminado ORDER BY c.cOnsejeroidpk DESC")
				.setParameter("region", consejero.getrEgionfk())
				.setParameter("comision", consejero.getcOmisionfk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();

		if (lista.isEmpty()) {
			lista = null;
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Consejeros buscarPorDni(Consejeros consejero) {
		EntityManager manager = createEntityManager();
		 Consejeros  consejeroresultado = new Consejeros();
		List<Consejeros> lista = manager
				.createQuery("FROM Consejeros c WHERE c.vNumdocumento=:numerodoc AND c.cFlgeliminado=:eliminado")
				.setParameter("numerodoc", consejero.getvNumdocumento()) 
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();

		if (lista.isEmpty()) {
			consejeroresultado = null;
		}else {
			consejeroresultado = lista.get(0);
		}

		return consejeroresultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consejeros> listarConsejerosPorConsejo(Long idconsejo) {
		EntityManager manager = createEntityManager();
		List<Consejeros> lista = manager
				.createQuery("FROM Consejeros c WHERE  c.cOnsejofk=:consejo  AND c.cFlgeliminado=:eliminado ORDER BY c.cOnsejeroidpk DESC")
				.setParameter("consejo", idconsejo)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();

		if (lista.isEmpty()) {
			List<Consejeros> listavacia  = new ArrayList<Consejeros>();
			lista =listavacia;
		}

		return lista;
	}

}
