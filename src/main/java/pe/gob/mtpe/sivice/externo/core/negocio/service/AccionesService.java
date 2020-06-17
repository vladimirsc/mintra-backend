package pe.gob.mtpe.sivice.externo.core.negocio.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acciones; 

public interface AccionesService {
	List<Acciones> listar();

	Acciones buscarPorId(Acciones acciones);

	List<Acciones> buscar(Acciones acciones);

	public Acciones Registrar(Long idacuerdo,Long identidad,String responsable,String descripcionaccion,String fecha_ejecutara,String fecha_ejecuto,MultipartFile docaccion);

	public Acciones Actualizar(Acciones acciones);

	public Acciones Eliminar(Acciones acciones);
	
	List<Acciones> listarAccionesPorAcuerdo(Long idacuerdo);
}
