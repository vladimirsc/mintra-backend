package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Personas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.PersonaDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class PersonaDaoImpl extends BaseDao<Long, Personas> implements PersonaDao {

	@Override
	public Personas buscarPorId(Personas persona) {
		return buscarId(persona.getpErsonaidpk());
	}

	@Override
	public Personas Registrar(Personas persona) {
		 guardar(persona);
		 return persona;
	}

	 
	@Override
	public Personas buscarTipoDocNumero(Personas personas) {
		EntityManager manager = createEntityManager();
		try {
			 Personas personaDao = (Personas) manager
						.createQuery("FROM Personas c WHERE c.tPdocumentofk=:tipodocuemnto AND  c.vNumdocumento=:numerodocumento AND  c.cFlgeliminado=:eliminado ORDER BY c.pErsonaidpk DESC")
						.setParameter("tipodocuemnto",personas.gettPdocumentofk())
						.setParameter("numerodocumento",personas.getvNumdocumento())
						.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getSingleResult();
				manager.close();
				return personaDao;
		} catch (Exception e) {
			return null;
		}
				
	}

	@Override
	public List<Personas> listar() {
	
		return null;
	}

	@Override
	public List<Personas> buscar(Personas personas) {
		
		return null;
	}

	@Override
	public Personas ActualizarPersona(Personas personas) {
		
		return null;
	}

	@Override
	public Personas EliminarPersona(Personas personas) {
		
		return null;
	}

}
