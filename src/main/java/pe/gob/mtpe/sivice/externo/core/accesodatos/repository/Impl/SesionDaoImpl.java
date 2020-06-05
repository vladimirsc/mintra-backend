package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesion;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class SesionDaoImpl extends BaseDao<Long, Sesion> implements SesionDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Sesion> listar() {
		EntityManager manager = createEntityManager();
		List<Sesion> lista = manager
				.createQuery("FROM Sesion c WHERE c.cFlgeliminado=:eliminado ORDER BY c.sEsionidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Sesion buscarPorId(Sesion sesion) {
		Sesion infosesion = buscarId(sesion.getsEsionidpk());
		return infosesion;
	}

	@Override
	public List<Sesion> buscar(Sesion sesion) {
		return null;
	}

	@Override
	public Sesion Registrar(Sesion sesion) {
		sesion.setvCodsesion(GenerarCorrelativo());
		guardar(sesion);
		return sesion;
	}

	@Override
	public Sesion Actualizar(Sesion sesion) {
		sesion.setdFecmodifica(new Date());
		actualizar(sesion);
		return sesion;
	}

	@Override
	public Sesion Eliminar(Sesion sesion) {
		sesion.setdFecelimina(new Date());
		sesion.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(sesion);
		return sesion;
	}
	
	public String GenerarCorrelativo() {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(c)+1 FROM Sesion c").getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,ConstantesUtil.SESION_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

}
