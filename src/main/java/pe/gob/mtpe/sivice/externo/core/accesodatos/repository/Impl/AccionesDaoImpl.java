package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acciones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AccionesDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class AccionesDaoImpl extends BaseDao<Long, Acciones> implements AccionesDao {

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Acciones> listar() {
		EntityManager manager = createEntityManager();
		List<Acciones> lista = manager
				.createQuery("FROM Acciones c WHERE c.cFlgeliminado=:eliminado ORDER BY c.aCcionesidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Acciones buscarPorId(Acciones acciones) {
		  acciones = buscarId(acciones.getaCcionesidpk());
		return acciones;
	}

	@Override
	public List<Acciones> buscar(Acciones acciones) {
	
		return null;
	}

	@Override
	public Acciones Registrar(Acciones acciones) {
		guardar(acciones);
		return acciones;
	}

	@Override
	public Acciones Actualizar(Acciones acciones) {
		acciones.setdFecmodifica(new Date());
		actualizar(acciones);
		return acciones;
	}

	@Override
	public Acciones Eliminar(Acciones acciones) {
		acciones.setdFecelimina(new Date());
		acciones.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(acciones);
		return acciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acciones> listarAccionesPorAcuerdo(Long idacuerdo) {
		EntityManager manager = createEntityManager();
		List<Acciones> lista = manager
				.createQuery("SELECT c FROM Acciones c INNER JOIN c.acuerdo ac  WHERE  ac.aCuerdoidpk=:idacuerdo AND c.cFlgeliminado=:eliminado ORDER BY c.aCcionesidpk DESC")
				.setParameter("idacuerdo", idacuerdo)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

}
