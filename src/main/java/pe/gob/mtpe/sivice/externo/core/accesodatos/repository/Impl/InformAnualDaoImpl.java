package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;

import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.InfAnuales;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.InformAnualDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Component
public class InformAnualDaoImpl extends BaseDao<Long, InfAnuales> implements InformAnualDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<InfAnuales> listar() {
		EntityManager manager = createEntityManager();
		List<InfAnuales> lista = manager
				.createQuery("FROM InfAnuales b WHERE b.cFlgeliminado=:eliminado ORDER BY b.iNformeidpk DESC")
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public InfAnuales buscarPorId(InfAnuales infAnuales) {
		infAnuales = buscarId(infAnuales.getiNformeidpk());
		return infAnuales;
	}

	@Override
	public List<InfAnuales> buscar(InfAnuales infAnuales) { 
		return null;
	}

	@Override
	public InfAnuales Registrar(InfAnuales infAnuales) {
		infAnuales.setvCodinforme(GenerarCorrelativo());
		guardar(infAnuales);
		return infAnuales;
	}

	@Override
	public InfAnuales Actualizar(InfAnuales infAnuales) {
		infAnuales.setdFecmodifica(new Date());
		actualizar(infAnuales);
		return infAnuales;
	}

	@Override
	public InfAnuales Eliminar(InfAnuales infAnuales) {
		infAnuales.setdFecelimina(new Date());
		infAnuales.setcFlgeliminado(ConstantesUtil.C_INDC_ACTIVO);
		actualizar(infAnuales);
		return infAnuales;
	}
	
	public String GenerarCorrelativo() {
		EntityManager manager = createEntityManager();
		Long correlativo = (Long) manager.createQuery("SELECT COUNT(c)+1 FROM InfAnuales c").getSingleResult();
		String StrcorrelativoFinal = FechasUtil.obtenerCorrelativo(correlativo,ConstantesUtil.INFORME_ALIAS_CORRELATIVO);
		manager.close();
		return StrcorrelativoFinal;
	}

}
