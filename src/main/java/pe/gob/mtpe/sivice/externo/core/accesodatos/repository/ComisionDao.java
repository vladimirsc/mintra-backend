package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Comisiones;

public interface ComisionDao {
	
	List<Comisiones> listar();

	Comisiones buscarPorId(Comisiones comisiones);

	List<Comisiones> buscar(Comisiones comisiones);

	public Comisiones Registrar(Comisiones comisiones);

	public Comisiones Actualizar(Comisiones comisiones);

	public Comisiones Eliminar(Comisiones comisiones);
	
	List<Comisiones> buscarComision(String nombre_comision);
}
