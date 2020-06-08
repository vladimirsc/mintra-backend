package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class SesionDaoImpl extends BaseDao<Long, Sesiones> implements SesionDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Sesiones> listar() {
		EntityManager manager = createEntityManager();
		List<Sesiones> lista = manager
				.createQuery("FROM Sesiones c WHERE c.cFlgeliminado=:eliminado ORDER BY c.sEsionidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Sesiones buscarPorId(Sesiones sesion) {
		Sesiones infosesion = buscarId(sesion.getsEsionidpk());
		return infosesion;
	}

	@Override
	public List<Sesiones> buscar(Sesiones sesion) {
		return null;
	}

	@Override
	public Sesiones Registrar(Sesiones sesion) {
		sesion.setvCodsesion(GenerarCorrelativo());
		guardar(sesion);
		return sesion;
	}

	@Override
	public Sesiones Actualizar(Sesiones sesion) {
		sesion.setdFecmodifica(new Date());
		actualizar(sesion);
		return sesion;
	}

	@Override
	public Sesiones Eliminar(Sesiones sesion) {
		sesion.setdFecelimina(new Date());
		sesion.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(sesion);
		return sesion;
	}
	
	public String GenerarCorrelativo() {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(c)+1 FROM Sesiones c").getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,ConstantesUtil.SESION_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

}
