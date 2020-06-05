package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Seguimientos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SeguimientoDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class SeguimientoDaoImpl extends BaseDao<Long, Seguimientos> implements SeguimientoDao {

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Seguimientos> listar() {
		EntityManager manager = createEntityManager();
		List<Seguimientos> lista = manager
				.createQuery("FROM Seguimientos c WHERE c.cFlgeliminado=:eliminado ORDER BY c.sEgimientoidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Seguimientos buscarPorId(Seguimientos seguimientos) {
		  seguimientos = buscarId(seguimientos.getsEgimientoidpk());
		return seguimientos;
	}

	@Override
	public List<Seguimientos> buscar(Seguimientos seguimientos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguimientos Registrar(Seguimientos seguimientos) {
		guardar(seguimientos);
		return seguimientos;
	}

	@Override
	public Seguimientos Actualizar(Seguimientos seguimientos) {
		seguimientos.setdFecmodifica(new Date());
		actualizar(seguimientos);
		return seguimientos;
	}

	@Override
	public Seguimientos Eliminar(Seguimientos seguimientos) {
		seguimientos.setdFecelimina(new Date());
		seguimientos.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(seguimientos);
		return seguimientos;
	}

}
