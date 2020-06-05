package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Particalen;

public interface ParticipanteCalendarioService {
	
	List<Particalen> listar();

	Particalen buscarPorId(Particalen particalen);

	List<Particalen> buscar(Particalen particalen);

	public Particalen Registrar(Particalen particalen);

	public Particalen Actualizar(Particalen particalen);

	public Particalen Eliminar(Particalen particalen);
}
