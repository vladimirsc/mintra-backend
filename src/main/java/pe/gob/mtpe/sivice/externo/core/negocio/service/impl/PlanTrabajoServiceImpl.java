package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.PlanTrabajo;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.PlanTrabajoDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.PlanTrabajoService;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil; 

@Service("PlanTrabajoService")
@Transactional(readOnly = true)
public class PlanTrabajoServiceImpl implements PlanTrabajoService {
	
	//private static final Logger logger = LoggerFactory.getLogger(PlanTrabajoServiceImpl.class);

	@Autowired
	private PlanTrabajoDao planTrabajoDao;
	
	@Override
	public List<PlanTrabajo> listar(PlanTrabajo planTrabajo) {
		return planTrabajoDao.listar(planTrabajo);
	}

	@Override
	public PlanTrabajo buscarPorId(PlanTrabajo planTrabajo) {
		return planTrabajoDao.buscarPorId(planTrabajo);
	}

	@Override
	public List<PlanTrabajo> buscar(PlanTrabajo planTrabajo) {
		if(planTrabajo.getdFecaprobacion()!=null && planTrabajo.getdFecaprobacionfin()!=null) {
			planTrabajo.setdFecaprobacion(FechasUtil.convertStringToDate(FechasUtil.convertDateToString(planTrabajo.getdFecaprobacion()).substring(0,10)));
			planTrabajo.setdFecaprobacionfin(FechasUtil.convertStringToDate(FechasUtil.convertDateToString(planTrabajo.getdFecaprobacionfin()).substring(0,10)));
		}
		
		if( planTrabajo.getdFecinicio()!=null && planTrabajo.getdFecfin()!=null ) {
			planTrabajo.setdFecinicio(FechasUtil.convertStringToDate(FechasUtil.convertDateToString(planTrabajo.getdFecinicio()).substring(0,10)));
			planTrabajo.setdFecfin(FechasUtil.convertStringToDate(FechasUtil.convertDateToString(planTrabajo.getdFecfin()).substring(0,10)));
		}
		
		
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
