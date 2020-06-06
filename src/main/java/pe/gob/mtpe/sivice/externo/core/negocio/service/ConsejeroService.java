package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;

public interface ConsejeroService {

	List<Consejeros> listar(Consejeros consejero);

	Consejeros buscarPorId(Consejeros consejero);
	
	Consejeros buscarPorDni(Consejeros consejero);

	List<Consejeros> buscar(Consejeros consejero);
	
	public Consejeros Registrar(Consejeros consejero);

	public Consejeros Actualizar(Consejeros consejero);

	public Consejeros Eliminar(Consejeros consejero);
	
	Consejeros buscarPorTipoDocNumero(Consejeros consejero);
	
	List<Consejeros> listarConsejerosPorComision(Consejeros consejero);

}
