package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;

public interface ConsejeroDao {

	List<Consejeros> listar(Consejeros consejero);

	Consejeros buscarPorId(Consejeros consejero);
	
	Consejeros buscarPorDni(Consejeros consejero);

	List<Consejeros> buscar(Consejeros consejero);

	public Consejeros Registrar(Consejeros consejero);

	public Consejeros Actualizar(Consejeros consejero);

	public Consejeros Eliminar(Consejeros consejero);

	Consejeros buscarPorTipoDocNumero(Consejeros consejero);

	List<Consejeros> listarConsejerosPorComision(Consejeros consejero);
	
	List<Consejeros> listarConsejerosPorConsejo(Long idconsejo,Long region);
	
	List<Consejeros> listarConsejerosPorRegion(Long region);
}
