package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Calendarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Entidades;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Particalen;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ParticipaCalendarioDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ParticipanteCalendarioService;

@Service("ParticipaCalendarioServiceImpl")
@Transactional(readOnly=true)
public class ParticipaCalendarioServiceImpl implements ParticipanteCalendarioService {

	@Autowired
	private ParticipaCalendarioDao participaCalendarioDao;
	
	@Override
	public List<Particalen> listar() {
		return participaCalendarioDao.listar();
	}

	@Override
	public Particalen buscarPorId(Particalen particalen) {
		return participaCalendarioDao.buscarPorId(particalen);
	}

	@Override
	public List<Particalen> buscar(Particalen particalen) {
		return participaCalendarioDao.buscar(particalen);
	}

	@Override
	public Particalen Registrar(Particalen particalen) {
		
		 Calendarios calendario = new Calendarios();
		 calendario.setcAlendarioidpk(particalen.getnCalendiariofk());
		 
		 TipoDocumentos tipodocumentos= new TipoDocumentos();
		 tipodocumentos.settPdocumentoidpk(particalen.getnTipodocumento());
		 
		 if(particalen.getnEntidad()!= 0L) {
			 Entidades entidades = new Entidades(); 
			 entidades.seteNtidadidpk(particalen.getnEntidad()); 
			 particalen.setEntidad(entidades);
		 }
		 
		 particalen.setCalendario(calendario);
		 particalen.setTipodocumento(tipodocumentos);
		
		 
		return participaCalendarioDao.Registrar(particalen);
	}

	@Override
	public Particalen Actualizar(Particalen particalen) {
		return participaCalendarioDao.Actualizar(particalen);
	}

	@Override
	public Particalen Eliminar(Particalen particalen) {
		return participaCalendarioDao.Eliminar(particalen);
	}

	@Override
	public List<Particalen> listarParticipantesPorCalendario(Long codigocalendario) { 
		return participaCalendarioDao.listarParticipantesPorCalendario(codigocalendario);
	}

}
