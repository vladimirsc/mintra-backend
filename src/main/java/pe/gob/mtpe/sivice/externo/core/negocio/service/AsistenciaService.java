package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Asistencias;

public interface AsistenciaService {
	
	List<Asistencias> listar();

	Asistencias buscarPorId(Asistencias asistencia);

	List<Asistencias> buscar(Asistencias asistencia);

	public Asistencias Registrar(Asistencias asistencia);

	public Asistencias Actualizar(Asistencias asistencia);

	public Asistencias Eliminar(Asistencias asistencia);
}
