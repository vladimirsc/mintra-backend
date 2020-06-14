package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Firmantes;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FirmantesDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;


@Component
public class FirmantesDaoImpl extends BaseDao<Long, Firmantes> implements FirmantesDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Firmantes> listar() {
		EntityManager manager = createEntityManager();
		List<Firmantes> lista = manager
				.createQuery("FROM Firmantes b WHERE b.cFlgelimino=:eliminado ORDER BY b.fIrmanteidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Firmantes buscarPorId(Firmantes firmantes) {
		firmantes = buscarId(firmantes.getfIrmanteidpk());
		return firmantes;
	}

	@Override
	public List<Firmantes> buscar(Firmantes firmantes) { 
		return null;
	}

	@Override
	public Firmantes Registrar(Firmantes firmantes) {
		guardar(firmantes);
		return firmantes;
	}

	@Override
	public Firmantes Actualizar(Firmantes firmantes) {
		firmantes.setdFecmodifica(new Date());
		actualizar(firmantes);
		return firmantes;
	}

	@Override
	public Firmantes Eliminar(Firmantes firmantes) {
		firmantes.setdFecelimina(new Date());
		firmantes.setcFlgelimino(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(firmantes);
		return firmantes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Firmantes> listarFirmantesPorActa(Long idacta) {
		EntityManager manager = createEntityManager();
		List<Firmantes> lista = manager
				.createQuery("FROM Firmantes b WHERE b.aCtafk=:actapk AND b.cFlgelimino=:eliminado ORDER BY b.fIrmanteidpk DESC")
				.setParameter("actapk", idacta)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

}
