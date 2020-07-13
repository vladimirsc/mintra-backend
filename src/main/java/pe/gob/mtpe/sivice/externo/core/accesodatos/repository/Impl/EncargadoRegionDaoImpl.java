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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.EncargadoRegion;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.InfAnuales;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.EncargadoRegionDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;


@Component
public class EncargadoRegionDaoImpl extends BaseDao<Long, EncargadoRegion>  implements EncargadoRegionDao {

	@Override
	public EncargadoRegion Registrar(EncargadoRegion encargadoRegion) {
		 guardar(encargadoRegion);
		return encargadoRegion;
	}

	@Override
	public EncargadoRegion Actualizar(EncargadoRegion encargadoRegion) {
		encargadoRegion.setdFechamodifica(new Date());
		actualizar(encargadoRegion);
		return encargadoRegion;
	}

	@Override
	public EncargadoRegion Eliminar(EncargadoRegion encargadoRegion) {
		encargadoRegion.setdFechaelimina(new Date());
		encargadoRegion.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(encargadoRegion);
		return encargadoRegion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EncargadoRegion> listar() {
		EntityManager manager = createEntityManager();
		List<EncargadoRegion> lista = manager
				.createQuery("FROM EncargadoRegion e WHERE e.cFlgeliminado=:eliminado ORDER BY e.eNcargadoregionidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public EncargadoRegion buscarPorId(EncargadoRegion encargadoRegion) { 
		return buscarId(encargadoRegion.geteNcargadoregionidpk());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EncargadoRegion> buscar(EncargadoRegion encargadoRegion) {
		EntityManager manager = createEntityManager();
		encargadoRegion.getRegion().setrEgionidpk((encargadoRegion.getRegion().getrEgionidpk()!=null)?encargadoRegion.getRegion().getrEgionidpk() : 0L);
		encargadoRegion.setvNombre( (encargadoRegion.getvNombre().trim().length()>0)?encargadoRegion.getvNombre() : "-" );
		List<EncargadoRegion> lista = manager
				.createQuery("SELECT e FROM EncargadoRegion e INNER JOIN e.region r  WHERE r.rEgionidpk=:idregion OR e.vNombre LIKE :encargado OR e.vNumdocaprobacion=:numerodoc AND  e.cFlgeliminado=:eliminado ")
				.setParameter("idregion", encargadoRegion.getRegion().getrEgionidpk())
				.setParameter("encargado",encargadoRegion.getvNombre()+"%") 
				.setParameter("numerodoc",encargadoRegion.getvNumdocaprobacion()) 
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

 
}
