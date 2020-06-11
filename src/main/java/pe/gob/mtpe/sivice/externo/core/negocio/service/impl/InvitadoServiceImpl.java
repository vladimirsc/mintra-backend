package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Invitados;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.InvitadosDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.InvitadoService;


@Service
public class InvitadoServiceImpl implements InvitadoService {
	
	@Autowired
	private InvitadosDao  invitadosDao;
	

	@Override
	public Invitados buscarPorId(Invitados invitados) {
		// TODO Auto-generated method stub
		return invitadosDao.buscarPorId(invitados);
	}

}
