package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Calendarios;

public interface CalendarioDao {

	List<Calendarios> listar(Calendarios calendario);

	Calendarios buscarPorId(Calendarios calendarios);

	List<Calendarios> buscar(Calendarios calendarios);

	public Calendarios Registrar(Calendarios calendarios);

	public Calendarios Actualizar(Calendarios calendarios);

	public Calendarios Eliminar(Calendarios calendarios);

}
