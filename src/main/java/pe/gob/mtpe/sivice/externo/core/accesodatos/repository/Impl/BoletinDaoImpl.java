package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Boletines;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.BoletinDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class BoletinDaoImpl extends BaseDao<Long, Boletines> implements BoletinDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<Boletines> listar() {
		EntityManager manager = createEntityManager();
		List<Boletines> lista = manager
				.createQuery("FROM Boletines b WHERE b.cFlgeliminado=:eliminado ORDER BY b.bOletinidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Boletines buscarPorId(Boletines boletines) {
		Boletines boletin = buscarId(boletines.getbOletinidpk());
		return boletin;
	}

	@Override
	public Boletines Registrar(Boletines boletines) {
		boletines.setvNumbol(GenerarCorrelativo());
		guardar(boletines);
		return boletines;
	}

	@Override
	public Boletines Actualizar(Boletines boletines) {
		boletines.setdFecmodifica(new Date());
		actualizar(boletines);
		return boletines;
	}

	@Override
	public Boletines Eliminar(Boletines boletines) {
		boletines.setdFecelimina(new Date());
		boletines.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(boletines);
		return boletines;
	}

	public String GenerarCorrelativo() {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(b)+1 FROM Boletines b").getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo, ConstantesUtil.BOLETIN_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Boletines> buscarRangoFechas(Boletines boletines) {
		EntityManager manager = createEntityManager();
		List<Boletines> boletin = manager.createQuery(
				"FROM Boletines b WHERE b.dFecboletin  BETWEEN  :fechaInicio AND :fechaFin  AND b.cFlgeliminado=:eliminado")
				.setParameter("fechaInicio", boletines.getdFechaInicio())
				.setParameter("fechaFin", boletines.getdFechaFin())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return boletin;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Boletines> BuscarPorCodigo(Boletines boletines) {
		EntityManager manager = createEntityManager();
		List<Boletines> boletin = manager
				.createQuery("FROM Boletines b WHERE b.vNumbol=:numeroboletin AND b.cFlgeliminado=:eliminado")
				.setParameter("numeroboletin", boletines.getvNumbol())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return boletin;

	}

}
