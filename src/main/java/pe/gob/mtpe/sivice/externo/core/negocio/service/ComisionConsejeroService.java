package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.ComiConsej;

public interface ComisionConsejeroService {

	List<ComiConsej> listar();

	ComiConsej buscarPorId(ComiConsej comiConsej);

	List<ComiConsej> buscar(Long comision);

	public ComiConsej Registrar(ComiConsej comiConsej);

	public ComiConsej Actualizar(ComiConsej comiConsej);

	public ComiConsej Eliminar(ComiConsej comiConsej);
	
	List<ComiConsej> listaConsejerosPorComision(Long idcomision);

}
