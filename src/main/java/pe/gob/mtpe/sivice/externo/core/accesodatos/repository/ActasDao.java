package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos; 

public interface ActasDao {

	List<Actas> listar();

	Actas buscarPorId(Actas actas);

	List<Actas> buscar(Actas actas);

	public Actas Registrar(Actas actas);

	public Actas Actualizar(Actas actas);

	public Actas Eliminar(Actas actas);
	
	Actas buscarActaPorIdSesion(Long idSesion);
	
	List<Acuerdos> listaAcuerdosPorActa(Actas actas);
	
	List<Actas> buscarActasPorSesion(Actas actas);
	
	List<Actas> listarActasPorSesion(Actas actas);
	 
}
