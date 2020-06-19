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

	@SuppressWarnings("unchecked")
	@Override
	public Personas buscarTipoDocNumero(Personas personas) {
		Personas persona = new Personas();
		EntityManager manager = createEntityManager();
		List<Personas> lista = manager
				.createQuery("SELECT p FROM Personas p INNER JOIN p.tipodocumento tpd  WHERE tpd.tPdocumentoidpk=:tipodocumentofk AND p.vNumdocumento=:numerodoc AND p.cFlgeliminado=:eliminado")
				.setParameter("tipodocumentofk", personas.getTipodocumento().gettPdocumentoidpk())
				.setParameter("numerodoc", personas.getvNumdocumento())
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		 if(lista.isEmpty()) {
			 persona =null;
		 }else {
			 persona = lista.get(0);
		 }
		return persona;

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
