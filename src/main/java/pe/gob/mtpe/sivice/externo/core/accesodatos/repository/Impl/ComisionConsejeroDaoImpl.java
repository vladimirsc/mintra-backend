package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.ComiConsej;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class ComisionConsejeroDaoImpl extends BaseDao<Long, ComiConsej> implements ComisionConsejeroDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ComiConsej> listar() {
		EntityManager manager = createEntityManager();
		List<ComiConsej> lista = manager
				.createQuery("FROM ComiConsej b WHERE b.cFlgeliminado=:eliminado ORDER BY b.cOmiconsidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	 
	@Override
	public ComiConsej buscarPorId(ComiConsej comiConsej) {
		ComiConsej comiconsejero = buscarId(comiConsej.getcOmiconsidpk());
		return comiconsejero;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComiConsej> buscar(Long comision) {
		EntityManager manager = createEntityManager();
		List<ComiConsej> lista = manager
				.createQuery("SELECT cj FROM ComiConsej cj INNER JOIN cj.comision c  WHERE c.cOmisionidpk=:codigocomision AND cj.cFlgeliminado=:eliminado ORDER BY cj.cOmiconsidpk DESC")
				.setParameter("codigocomision", comision)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public ComiConsej Registrar(ComiConsej comiConsej) {
		 guardar(comiConsej);
		return comiConsej;
	}

	@Override
	public ComiConsej Actualizar(ComiConsej comiConsej) {
		comiConsej.setdFecmodifica(new Date());
		actualizar(comiConsej);
		return comiConsej;
	}

	@Override
	public ComiConsej Eliminar(ComiConsej comiConsej) {
		comiConsej.setdFecelimina(new Date());
		comiConsej.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(comiConsej);
		return comiConsej;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ComiConsej> listaConsejerosPorComision(Long idcomision) {
		EntityManager manager = createEntityManager();
		List<ComiConsej> lista = manager
				.createQuery("FROM ComiConsej c   WHERE c.comisionfk=:comision AND c.cFlgeliminado=:eliminado")
				.setParameter("comision", idcomision)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

}
