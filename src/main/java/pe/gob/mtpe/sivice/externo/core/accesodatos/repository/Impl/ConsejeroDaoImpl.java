package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class ConsejeroDaoImpl extends BaseDao<Long, Consejeros> implements ConsejeroDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Consejeros> listar(Consejeros consejero) {
		EntityManager manager = createEntityManager();
		List<Consejeros> lista = manager
				.createQuery("FROM Consejeros c WHERE c.rEgionfk=:region AND c.cOnsejofk=:consejo  AND c.cFlgeliminado=:eliminado ORDER BY c.cOnsejeroidpk DESC")
				.setParameter("region", consejero.getrEgionfk())
				.setParameter("consejo", consejero.getcOnsejofk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();

		if (lista.isEmpty()) {
			List<Consejeros> listavacia  = new ArrayList<Consejeros>();
			lista =listavacia;
		}

		return lista;
	}

	@Override
	public Consejeros buscarPorId(Consejeros consejero) {
		Consejeros consejeros = buscarId(consejero.getcOnsejeroidpk());
		return consejeros;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consejeros> buscar(Consejeros consejero) {
		EntityManager manager = createEntityManager();
		List<Consejeros> lista = manager
				.createQuery("FROM Consejeros c WHERE c.cFlgeliminado=:eliminado ORDER BY c.cOnsejeroidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Consejeros Registrar(Consejeros consejero) {
		guardar(consejero);
		return consejero;

	}

	@Override
	public Consejeros Actualizar(Consejeros consejero) {
		consejero.setdFecmodifica(new Date());
		actualizar(consejero);
		return consejero;
	}

	@Override
	public Consejeros Eliminar(Consejeros consejero) {
		consejero.setdFecelimina(new Date());
		consejero.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(consejero);
		return consejero;
	}

	@Override
	public Consejeros buscarPorTipoDocNumero(Consejeros consejero) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consejeros> listarConsejerosPorComision(Consejeros consejero) {
		EntityManager manager = createEntityManager();
		List<Consejeros> lista = manager
				.createQuery("FROM Consejeros c WHERE c.rEgionfk=:region AND c.cOnsejofk=:consejo  AND c.cFlgeliminado=:eliminado ORDER BY c.cOnsejeroidpk DESC")
				.setParameter("region", consejero.getrEgionfk())
				.setParameter("consejo", consejero.getcOnsejofk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();

		if (lista.isEmpty()) {
			lista = null;
		}

		return lista;
	}

}
