package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Particalen;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ParticipaCalendarioDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class ParticipaCalendarioDaoImpl extends BaseDao<Long, Particalen> implements ParticipaCalendarioDao {

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Particalen> listar() {
		EntityManager manager = createEntityManager();
		List<Particalen> lista = manager
				.createQuery("FROM Particalen p WHERE p.cFlgeliminado=:eliminado ORDER BY p.pArtcalendidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Particalen buscarPorId(Particalen particalen) {
		return buscarId(particalen.getpArtcalendidpk());
	}

	@Override
	public List<Particalen> buscar(Particalen particalen) { 
		return null;
	}

	@Override
	public Particalen Registrar(Particalen particalen) {
		guardar(particalen);
		return particalen;
	}

	@Override
	public Particalen Actualizar(Particalen particalen) {
		particalen.setdFecmodifica(new Date());
		actualizar(particalen);
		return particalen;
	}

	@Override
	public Particalen Eliminar(Particalen particalen) {
		particalen.setdFecElimina(new Date());
		particalen.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(particalen);
		return particalen;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Particalen> listarParticipantesPorCalendario(Long codigocalendario) {
		EntityManager manager = createEntityManager();
		List<Particalen> lista = manager
				.createQuery("SELECT p FROM Particalen p INNER JOIN p.calendario c  WHERE c.cAlendarioidpk=:calendariofk AND  p.cFlgeliminado=:eliminado ORDER BY p.pArtcalendidpk DESC")
				.setParameter("calendariofk", codigocalendario)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

}
