package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Calendarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.CalendarioDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class CalendarioDaoImpl extends BaseDao<Long, Calendarios> implements CalendarioDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Calendarios> listar() {
		EntityManager manager = createEntityManager();
		List<Calendarios> lista = manager
				.createQuery("FROM Calendarios b WHERE b.cFlgeliminado=:eliminado ORDER BY b.cAlendarioidpk DESC")
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
		return null;
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
		eliminar(calendarios);
		return calendarios;
	}

}
