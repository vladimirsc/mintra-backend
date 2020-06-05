package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ActasDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class ActasDaoImpl extends BaseDao<Long, Actas> implements ActasDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<Actas> listar() {
		EntityManager manager = createEntityManager();
		List<Actas> lista = manager
				.createQuery("FROM Actas b WHERE b.cFlagelimina=:eliminado ORDER BY b.aCtaidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Actas buscarPorId(Actas actas) {
		actas = buscarId(actas.getaCtaidpk());
		return actas;
	}

	@Override
	public List<Actas> buscar(Actas actas) {
		return null;
	}

	@Override
	public Actas Registrar(Actas actas) {
		actas.setvCodacta(GenerarCorrelativo());
		guardar(actas);
		return actas;
	}

	@Override
	public Actas Actualizar(Actas actas) {
		actas.setdFecmodifica(new Date());
		actualizar(actas);
		return actas;
	}

	@Override
	public Actas Eliminar(Actas actas) {
		actas.setdFecelimina(new Date());
		actas.setcFlagelimina(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(actas);
		return actas;
	}

	public String GenerarCorrelativo() {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(b)+1 FROM Actas b").getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,ConstantesUtil.ACTA_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

}
