package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Invitados;

public interface InvitadosDao {
	
	Invitados Registrar(Invitados invitados);
	
	Invitados buscarPorId(Invitados invitados);
	
	List<Invitados> listarInvitadosPorSesion(Long idsesion);
	
	Invitados Actualizar(Invitados invitados);

}
