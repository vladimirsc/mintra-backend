package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Temas;

public interface TemaService {
	
	List<Temas> listar();

	Temas buscarPorId(Temas temas);

	List<Temas> buscar(Temas temas);

	public Temas Registrar(Temas temas);

	public Temas Actualizar(Temas temas);

	public Temas Eliminar(Temas temas);
	
	Sesiones cabeceraSesion(Sesiones sesiones);
	
	List<Temas> temasPorSesion (Long idsesion);
	 
 
}
