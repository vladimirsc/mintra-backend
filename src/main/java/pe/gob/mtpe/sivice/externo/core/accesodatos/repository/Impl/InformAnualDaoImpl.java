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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.InfAnuales; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.InformAnualDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class InformAnualDaoImpl extends BaseDao<Long, InfAnuales> implements InformAnualDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<InfAnuales> listar() {
		EntityManager manager = createEntityManager();
		List<InfAnuales> lista = manager
				.createQuery("FROM InfAnuales b WHERE b.cFlgeliminado=:eliminado ORDER BY b.iNformeidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InfAnuales buscarPorId(InfAnuales infAnuales) { 
		InfAnuales informe = new InfAnuales();
		EntityManager manager = createEntityManager();
		List<InfAnuales> lista = manager
				.createQuery("FROM InfAnuales i WHERE i.iNformeidpk=:codigoinforme AND i.cFlgeliminado=:eliminado ")
				.setParameter("codigoinforme", infAnuales.getiNformeidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if(lista.isEmpty()) {
			informe = null;
		}else {
			informe = lista.get(0);
		}
		
		return informe;
	}

	@Override
	public List<InfAnuales> buscar(InfAnuales infAnuales) { 
		
		EntityManager manager = createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<InfAnuales> criteriaQuery = builder.createQuery(InfAnuales.class);
		Root<InfAnuales> root = criteriaQuery.from(InfAnuales.class);
		
		infAnuales.setdFecdesde( (infAnuales.getdFecdesde()!=null)? infAnuales.getdFecdesde() : FechasUtil.convertStringToDate("01-01-1880") );
		infAnuales.setdFhasta(   (infAnuales.getdFhasta()!=null)?   infAnuales.getdFhasta()   : FechasUtil.convertStringToDate("01-01-1880") );
		
		Predicate valor1 = builder.equal(root.get("vCodinforme"), infAnuales.getvCodinforme());
		Predicate valor2 = builder.equal(root.get("vSesion"), infAnuales.getvSesion());
		Predicate valor3 = builder.equal(root.get("vNumdocap"), infAnuales.getvNumdocap());
		Predicate valor4 = builder.equal(root.get("comision"), infAnuales.getComision());
		Predicate valor5 = builder.between(root.get("dFecdesde"), infAnuales.getdFecdesde(), infAnuales.getdFhasta());
		Predicate finalbusqueda=builder.or(valor1,valor2,valor3,valor4,valor5);
		
		criteriaQuery.where(finalbusqueda);
		Query<InfAnuales> query = (Query<InfAnuales>) manager.createQuery(criteriaQuery);
		List<InfAnuales> resultado = query.getResultList();
		manager.close(); 
		
		return resultado;
	}

	@Override
	public InfAnuales Registrar(InfAnuales infAnuales) {
		infAnuales.setvCodinforme(GenerarCorrelativo());
		guardar(infAnuales);
		return infAnuales;
	}

	@Override
	public InfAnuales Actualizar(InfAnuales infAnuales) {
		infAnuales.setdFecmodifica(new Date());
		actualizar(infAnuales);
		return infAnuales;
	}

	@Override
	public InfAnuales Eliminar(InfAnuales infAnuales) {
		infAnuales.setdFecelimina(new Date());
		infAnuales.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(infAnuales);
		return infAnuales;
	}
	
	public String GenerarCorrelativo() {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(c)+1 FROM InfAnuales c").getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,ConstantesUtil.INFORME_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

}
