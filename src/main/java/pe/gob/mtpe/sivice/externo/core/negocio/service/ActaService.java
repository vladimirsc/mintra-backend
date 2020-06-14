package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Firmantes;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;

public interface ActaService {

	List<Actas> listar();

	Actas buscarPorId(Actas actas);

	List<Actas> buscar(Actas actas);

	public Actas Registrar(Actas actas);

	public Actas Actualizar(Actas actas);

	public Actas Eliminar(Actas actas);
	
	public Actas descargarActa(Actas actas);
	
	Sesiones cabeceraActa(Sesiones sesiones);
	
	Actas buscarActaPorIdSesion(Long idSesion);
	
	List<Acuerdos> listaAcuerdosPorActa(Actas actas);
	
	List<Firmantes> listarFirmentes(Long idSesion);
	
	Acuerdos registrarAcueros(Acuerdos acuerdos);
	
}
