package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Personas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.PersonaDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.PersonaService;

@Service("PersonaService")
@Transactional(readOnly=true)
public class PersonaServiceImpl  implements PersonaService{
	
	@Autowired
	private PersonaDao personaDao;

	@Override
	public List<Personas> listar() {
		return personaDao.listar();
	}

	@Override
	public Personas buscarPorId(Personas personas) {
		return personaDao.buscarPorId(personas);
	}

	@Override
	public List<Personas> buscar(Personas personas) {
		return personaDao.buscar(personas);
	}


	@Override
	public Personas ActualizarPersona(Personas personas) {
		return personaDao.ActualizarPersona(personas);
	}

	@Override
	public Personas EliminarPersona(Personas personas) {
		return personaDao.EliminarPersona(personas);
	}

	@Override
	public Personas buscarTipoDocNumero(Personas personas) { 
		return personaDao.buscarTipoDocNumero(personas);
	}

	@Override 
	public Personas Registrar(Personas persona) { 
		return personaDao.Registrar(persona);
	}

}
