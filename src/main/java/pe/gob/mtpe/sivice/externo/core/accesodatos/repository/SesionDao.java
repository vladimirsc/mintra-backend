package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;
 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;

public interface SesionDao {
	
	List<Sesiones> listar();

	Sesiones buscarPorId(Sesiones sesion);

	List<Sesiones> buscar(Sesiones sesion);

	public Sesiones Registrar(Sesiones sesion);

	public Sesiones Actualizar(Sesiones sesion);

	public Sesiones Eliminar(Sesiones sesion);
	
	
}
