package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Personas;

public interface PersonaService {
	
	List<Personas> listar();

	Personas buscarPorId(Personas personas);

	List<Personas> buscar(Personas personas); 
	
	public Personas Registrar(Personas persona);

	public Personas ActualizarPersona(Personas personas);

	public Personas EliminarPersona(Personas personas);
	
	public Personas buscarTipoDocNumero(Personas personas);

 
}
