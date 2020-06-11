package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciaConsejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Asistencias;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Invitados;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;

public interface AsistenciaService {
	
	List<Asistencias> listar();

	Asistencias buscarPorId(Asistencias asistencia);

	List<Asistencias> buscar(Asistencias asistencia);

	public Asistencias Registrar(Asistencias asistencia);

	public Asistencias Actualizar(Asistencias asistencia);

	public Asistencias Eliminar(Asistencias asistencia);
	
	Sesiones buscarSesion(Sesiones sesiones);
	
	Long cantidadAsistentesPorSesion(Long idsesion);
	
	List<AsistenciaConsejeros> listarConsejerosAsistencia(Long idsesion);
	
	 Invitados RegistrarInvitados(Invitados invitados);
	
}
