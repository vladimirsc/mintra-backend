package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;

public interface UsuarioService {
	
	List<Usuarios> listar();

	Usuarios buscarPorId(Usuarios usuario);
	
	Usuarios buscarPorCorreo(String correo);

	List<Usuarios> buscar(Usuarios usuario);

	public Usuarios Registrar(Usuarios usuario,Long idtipodocumento,Long rol);

	public Usuarios Actualizar(Usuarios usuario);

	public Usuarios Eliminar(Usuarios usuario);

}
