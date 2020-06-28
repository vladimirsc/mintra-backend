package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.BandejaActas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;

public interface AcuerdoService {

	List<Acuerdos> listar();

	Acuerdos buscarPorId(Acuerdos acuerdos);

	List<Acuerdos> buscar(Acuerdos acuerdos);

	public Acuerdos Registrar(Acuerdos acuerdos);

	public Acuerdos Actualizar(Acuerdos acuerdos);

	public Acuerdos Eliminar(Acuerdos acuerdos);
	
	List<BandejaActas> buscarAcuerdosPorSesion(Sesiones sesiones);
	
	List<Acuerdos> listarAcuerdosPorActa(Actas acta);
 
}
