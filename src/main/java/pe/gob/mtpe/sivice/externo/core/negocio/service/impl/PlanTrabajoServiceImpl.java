package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.PlanTrabajo;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.PlanTrabajoDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.PlanTrabajoService;

@Service("PlanTrabajoService")
@Transactional(readOnly = true)
public class PlanTrabajoServiceImpl implements PlanTrabajoService {

	@Autowired
	private PlanTrabajoDao planTrabajoDao;
	
	@Override
	public List<PlanTrabajo> listar() {
		return planTrabajoDao.listar();
	}

	@Override
	public PlanTrabajo buscarPorId(PlanTrabajo planTrabajo) {
		return planTrabajoDao.buscarPorId(planTrabajo);
	}

	@Override
	public List<PlanTrabajo> buscar(PlanTrabajo planTrabajo) {
		return planTrabajoDao.buscar(planTrabajo);
	}

	@Override
	public PlanTrabajo Registrar(PlanTrabajo planTrabajo) {
		return planTrabajoDao.Registrar(planTrabajo);
	}

	@Override
	public PlanTrabajo Actualizar(PlanTrabajo planTrabajo) {
		return planTrabajoDao.Actualizar(planTrabajo);
	}

	@Override
	public PlanTrabajo Eliminar(PlanTrabajo planTrabajo) {
		return planTrabajoDao.Eliminar(planTrabajo);
	}

}
