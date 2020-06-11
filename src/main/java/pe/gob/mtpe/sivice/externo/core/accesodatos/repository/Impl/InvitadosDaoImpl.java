package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Invitados;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.InvitadosDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class InvitadosDaoImpl extends BaseDao<Long, Invitados> implements InvitadosDao {

	@Override
	public Invitados Registrar(Invitados invitados) {
		 guardar(invitados);
		 return invitados;
	}

	@Override
	public Invitados buscarPorId(Invitados invitados) {  
		invitados = buscarId(invitados.getiNvitadosidpk());
		return invitados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invitados> listarInvitadosPorSesion(Long idsesion) {
		EntityManager manager = createEntityManager();
		List<Invitados> lista = manager
				.createQuery("FROM Invitados i WHERE i.sEsionfk=:sesion AND i.cFlgeliminado=:eliminado")
				.setParameter("sesion", idsesion)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		return lista;
	}

	@Override
	public Invitados Actualizar(Invitados invitados) {
		actualizar(invitados);
		return invitados;
		 
	}

}
