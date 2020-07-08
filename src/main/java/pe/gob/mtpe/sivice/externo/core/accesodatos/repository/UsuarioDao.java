package pe.gob.mtpe.sivice.externo.core.accesodatos.repository;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;

public interface UsuarioDao {
	
	List<Usuarios> listar(Usuarios usuario);
	
	List<Usuarios> listarPorRegion(Usuarios usuario);

	Usuarios buscarPorId(Usuarios usuario);
	
	Usuarios buscarPorCorreo(String correo);

	List<Usuarios> buscar(Usuarios usuario);

	public Usuarios Registrar(Usuarios usuario);

	public Usuarios Actualizar(Usuarios usuario);

	public Usuarios Eliminar(Usuarios usuario);
}
