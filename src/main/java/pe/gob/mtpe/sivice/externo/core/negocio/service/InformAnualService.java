package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.InfAnuales;

public interface InformAnualService {
	List<InfAnuales> listar(InfAnuales infAnuales );

	InfAnuales buscarPorId(InfAnuales infAnuales);

	List<InfAnuales> buscar(InfAnuales infAnuales);

	public InfAnuales Registrar(InfAnuales infAnuales);

	public InfAnuales Actualizar(InfAnuales infAnuales);

	public InfAnuales Eliminar(InfAnuales infAnuales);
}
