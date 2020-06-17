package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AcuerdoDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class AcuerdoDaoImpl extends BaseDao<Long, Acuerdos> implements AcuerdoDao {

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Acuerdos> listar() {
		EntityManager manager = createEntityManager();
		List<Acuerdos> lista = manager
				.createQuery("FROM Acuerdos a WHERE a.cFlgeliminado=:eliminado ORDER BY a.aCuerdoidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Acuerdos buscarPorId(Acuerdos acuerdos) {
		acuerdos = buscarId(acuerdos.getaCuerdoidpk());
		return acuerdos;
	}

	@Override
	public List<Acuerdos> buscar(Acuerdos acuerdos) {
		return null;
	}

	@Override
	public Acuerdos Registrar(Acuerdos acuerdos) {
		guardar(acuerdos);
		return acuerdos;
	}

	@Override
	public Acuerdos Actualizar(Acuerdos acuerdos) {
		acuerdos.setdFecmodifica(new Date());
		actualizar(acuerdos);
		return acuerdos;
	}

	@Override
	public Acuerdos Eliminar(Acuerdos acuerdos) {
		acuerdos.setdFecelimina(new Date());
		acuerdos.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(acuerdos);
		return acuerdos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acuerdos> listarAcuerdosPorActa(Actas acta) { 
		EntityManager manager = createEntityManager();
		List<Acuerdos> lista = manager
				.createQuery("SELECT a FROM Acuerdos a INNER JOIN  a.acta ac  WHERE ac.aCtaidpk=:idacta  AND a.cFlgeliminado=:eliminado ORDER BY a.aCuerdoidpk DESC")
				.setParameter("idacta", acta.getaCtaidpk())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

}
