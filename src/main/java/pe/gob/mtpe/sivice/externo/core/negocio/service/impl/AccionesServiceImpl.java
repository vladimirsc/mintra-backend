package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acciones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Entidades;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AccionesDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AcuerdoDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FijasDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AccionesService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@Service("AccionesService")
@Transactional(readOnly = true)
public class AccionesServiceImpl implements AccionesService {

	@Autowired
	private AccionesDao accionesDao;
	
	@Autowired
	private AcuerdoDao acuerdoDao;
	
	@Autowired
	private FijasDao fijasDao;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;

	@Override
	public List<Acciones> listar() {
		return accionesDao.listar();
	}

	@Override
	public Acciones buscarPorId(Acciones acciones) {
		return accionesDao.buscarPorId(acciones);
	}

	@Override
	public List<Acciones> buscar(Acciones acciones) {
		return accionesDao.buscar(acciones);
	}

	@Override
	public Acciones Registrar(Long idacuerdo,Long identidad,String responsable,String descripcionaccion,String fecha_ejecutara,String flgejecuto,String fecha_ejecuto,MultipartFile docaccion) {
		Acuerdos acuerdo = new Acuerdos();
		acuerdo.setaCuerdoidpk(idacuerdo);
		acuerdo = acuerdoDao.buscarPorId(acuerdo);
		
		Entidades entidades = new Entidades();
		entidades.seteNtidadidpk(identidad);
		entidades=fijasDao.buscarPorEntidad(entidades);
		
		Acciones accion = new Acciones();
		accion.setAcuerdo(acuerdo);
		accion.setEntidad(entidades);
		accion.setvResponsable(responsable);
		accion.setvDesaccion(descripcionaccion);
		accion.setdFecejecutara( (fecha_ejecutara!=null)? FechasUtil.convertStringToDate(fecha_ejecutara) : null );
		accion.setcFlgejecuto(flgejecuto);
		accion.setdFecejecuto( (fecha_ejecuto!=null)? FechasUtil.convertStringToDate(fecha_ejecuto) : null );
		Archivos archivo = new Archivos();
		
		archivo = archivoUtilitarioService.cargarArchivo(docaccion, ConstantesUtil.C_CONSEJERO_DOC_ASIGNACION);
		if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) {
			accion.setvNombrearchivo(archivo.getNombre());
			accion.setvUbiarch(archivo.getUbicacion());
			accion.setvExtarchivo(archivo.getExtension());
			
			accion = accionesDao.Registrar(accion);
		 }
		
		return accion;
	}

	
	@Override
	public Acciones Actualizar(Acciones acciones) {
		return accionesDao.Actualizar(acciones);
	}

	@Override
	public Acciones Eliminar(Acciones acciones) {
		return accionesDao.Eliminar(acciones);
	}

	@Override
	public List<Acciones> listarAccionesPorAcuerdo(Long idacuerdo) {
		Acuerdos acuerdo = new Acuerdos();
		acuerdo.setaCuerdoidpk(idacuerdo);
		return accionesDao.listarAccionesPorAcuerdo(idacuerdo);
	}

}
