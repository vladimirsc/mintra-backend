package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Temas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.TemaDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil; 

@Component
public class TemaDaoImpl extends BaseDao<Long, Temas> implements TemaDao {
	
 	@SuppressWarnings("unchecked")
	@Override
	public List<Temas> listar() {
		EntityManager manager = createEntityManager();
		List<Temas> lista = manager
				.createQuery("FROM Temas c WHERE c.cFlgeliminado=:eliminado ORDER BY c.tEmaidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Temas buscarPorId(Temas temas) {
		Temas infoTemas = buscarId(temas.gettEmaidpk());
		return infoTemas;
	}

	@Override
	public List<Temas> buscar(Temas temas) {
		return null;
	}

	@Override
	public Temas Registrar(Temas temas) {
		temas.setdFecreg(new Date());
		guardar(temas);
		return temas;
	}

	@Override
	public Temas Actualizar(Temas temas) {
		temas.setdFecmodifica(new Date());
		actualizar(temas);
		return temas;
	}

	@Override
	public Temas Eliminar(Temas temas) {
		temas.setdFecelimina(new Date());
		temas.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(temas);
		return temas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Temas> temasPorSesion(Long idsesion) {
		EntityManager manager = createEntityManager();
		List<Temas> lista = manager
				.createQuery("SELECT t FROM Temas t INNER JOIN t.sesion s  WHERE s.sEsionidpk=:sesion  AND t.cFlgeliminado=:eliminado ORDER BY t.tEmaidpk DESC")
				.setParameter("sesion", idsesion)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

 
 
}
