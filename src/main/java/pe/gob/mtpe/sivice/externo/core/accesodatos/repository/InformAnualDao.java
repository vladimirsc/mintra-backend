package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.InfAnuales;

public interface InformAnualDao {

	List<InfAnuales> listar(InfAnuales infAnuales );

	InfAnuales buscarPorId(InfAnuales infAnuales);

	List<InfAnuales> buscar(InfAnuales infAnuales);

	public InfAnuales Registrar(InfAnuales infAnuales);

	public InfAnuales Actualizar(InfAnuales infAnuales);

	public InfAnuales Eliminar(InfAnuales infAnuales);
}
