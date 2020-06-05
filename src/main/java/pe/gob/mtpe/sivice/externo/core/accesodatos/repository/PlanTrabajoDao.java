package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.PlanTrabajo;

public interface PlanTrabajoDao {
	
	List<PlanTrabajo> listar();

	PlanTrabajo buscarPorId(PlanTrabajo planTrabajo);

	List<PlanTrabajo> buscar(PlanTrabajo planTrabajo);

	public PlanTrabajo Registrar(PlanTrabajo planTrabajo);

	public PlanTrabajo Actualizar(PlanTrabajo planTrabajo);

	public PlanTrabajo Eliminar(PlanTrabajo planTrabajo);
}
