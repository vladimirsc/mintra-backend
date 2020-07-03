package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.EncargadoRegion; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.EncargadoRegionDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;


@Component
public class EncargadoRegionDaoImpl extends BaseDao<Long, EncargadoRegion>  implements EncargadoRegionDao {

	@Override
	public EncargadoRegion Registrar(EncargadoRegion encargadoRegion) {
		 guardar(encargadoRegion);
		return encargadoRegion;
	}

	@Override
	public EncargadoRegion Actualizar(EncargadoRegion encargadoRegion) {
		encargadoRegion.setdFechamodifica(new Date());
		actualizar(encargadoRegion);
		return encargadoRegion;
	}

	@Override
	public EncargadoRegion Eliminar(EncargadoRegion encargadoRegion) {
		encargadoRegion.setdFechaelimina(new Date());
		encargadoRegion.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(encargadoRegion);
		return encargadoRegion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EncargadoRegion> listar() {
		EntityManager manager = createEntityManager();
		List<EncargadoRegion> lista = manager
				.createQuery("FROM EncargadoRegion e WHERE e.cFlgeliminado=:eliminado ORDER BY e.eNcargadoregionidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public EncargadoRegion buscarPorId(EncargadoRegion encargadoRegion) { 
		return buscarId(encargadoRegion.geteNcargadoregionidpk());
	}

 
}
