package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.PlanTrabajo;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.PlanTrabajoDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class PlanTrabajoDaoImpl extends BaseDao<Long, PlanTrabajo> implements PlanTrabajoDao {

	

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanTrabajo> listar() {
		EntityManager manager = createEntityManager();
		List<PlanTrabajo> lista = manager
				.createQuery("FROM PlanTrabajo c WHERE c.cFlgeliminado=:eliminado ORDER BY c.pLantrabidpk DESC")
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanTrabajo Registrar(PlanTrabajo planTrabajo) {
		planTrabajo.setvCodigoplantrab(GenerarCorrelativo());
		guardar(planTrabajo);
		return planTrabajo;
	}

	@Override
	public PlanTrabajo Actualizar(PlanTrabajo planTrabajo) {
		planTrabajo.setdFecmodifica(new Date());
		actualizar(planTrabajo);
		return planTrabajo;
	}

	@Override
	public PlanTrabajo Eliminar(PlanTrabajo planTrabajo) {
		planTrabajo.setdFecelimina(new Date());
		planTrabajo.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(planTrabajo);
		return planTrabajo;
	}
	
	
	public String GenerarCorrelativo() {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(c)+1 FROM PlanTrabajo c").getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,ConstantesUtil.PLANTRABAJO_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

}
