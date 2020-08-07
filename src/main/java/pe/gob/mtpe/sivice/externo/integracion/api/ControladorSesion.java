package pe.gob.mtpe.sivice.externo.integracion.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Comisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoSesiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ComisionService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.SesionService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;


@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping({ "/api/sesion" })
public class ControladorSesion {

	private static final Logger logger = LoggerFactory.getLogger(ControladorSesion.class);

	@Autowired
	private SesionService sesionService;
	
	@Autowired
	private  FijasService fijasService;
	
	@Autowired
	private ComisionService comisionService;

	
	
	@ApiOperation(value = "Lista la sesiones")
	@GetMapping("/")
	public List<Sesiones> listarSesion(
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		
		logger.info("============  BUSCAR listarSesion =================");
	 
		Regiones region = new Regiones();
		region.setrEgionidpk(idRegion);
		
		Sesiones sesion = new Sesiones();
		sesion.setRegion(region);
		
		Consejos consejos = new Consejos();
		consejos.setcOnsejoidpk(fijasService.BuscarConsejoPorNombre(nombreRol));
		sesion.setConsejofk(consejos);
		return sesionService.listar(sesion);
	}

	
	@ApiOperation(value = "Busca sesiones por los criterios de busqueda")
	@PostMapping("/buscar")
	public List<Sesiones> buscarSesion(
			@RequestParam("codigosesion")  String codigosesion,
			@RequestParam("tiposesion")    Long tiposesion,
			@RequestParam("fechainicio")   String fechainicio,
			@RequestParam("fechafin")      String fechafin,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		
		List<Sesiones> generico = new  ArrayList<Sesiones>();
		Sesiones sesionbuscar = new Sesiones();
		
		Regiones region = new Regiones();
		region.setrEgionidpk(idRegion);
		
		Consejos consejos = new Consejos();
		consejos.setcOnsejoidpk(fijasService.BuscarConsejoPorNombre(nombreRol));
		
		sesionbuscar.setConsejofk(consejos);
		sesionbuscar.setRegion(region);
		
		 try {
			 TipoSesiones tiposesiones = new TipoSesiones();
			 if(tiposesion!=null) {
				 tiposesiones.settIposesionidpk(tiposesion); 
			  }

			 sesionbuscar.setvCodsesion(codigosesion); 
			 sesionbuscar.setdFechaInicio(FechasUtil.convertStringToDate(fechainicio));
			 sesionbuscar.setdFechaFin(FechasUtil.convertStringToDate(fechafin));
			 sesionbuscar.setTipoSesiones(tiposesiones);
			 generico = sesionService.buscar(sesionbuscar);
		} catch (Exception e) {
			generico = null;
		}
		return generico;
	}
	
	
	
	@ApiOperation(value = "Muestra la informacion de una sesion por su codigo")
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdPlanTrabajo(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		Sesiones generico  = new Sesiones();
		generico.setsEsionidpk(id); 
		Map<String, Object> response = new HashMap<>();
		try {
			generico = sesionService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SESION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SESION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Sesiones>(generico,HttpStatus.OK);
	}

	
	@ApiOperation(value = "Registra una sesion")
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarSesion(
			 @RequestParam(value="consejofk")   Long consejofk,
			 @RequestParam(value="cOmisionfk")  String cOmisionfk,
			 @RequestParam(value="tiposesion")  Long tiposesion,
			 @RequestParam(value="dFecreacion") String dFecreacion,
			 @RequestParam(value="dHorinicio")  String dHorinicio,
			 @RequestParam(value="dHorfin")     String dHorfin,
			 @RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			 @RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			 @RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		
		logger.info("============  REGISTRAR SESION =================");
		//*****  DATOS DE USUARIO DE INICIO DE SESION **********
		  Long idconsejo = 0L;  
		  idconsejo = fijasService.BuscarConsejoPorNombre(nombreRol);
		//*******************************************************
		  
		Sesiones generico = new Sesiones();
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Consejos consejo = new Consejos();
			consejo.setcOnsejoidpk(idconsejo);
			consejo = fijasService.buscarPorCodigoConsejo(consejo);
							
			Regiones region = new Regiones();
			region.setrEgionidpk(idRegion);
			region = fijasService.buscarPorCodigoRegion(region);
			
			TipoSesiones tipoSesiones = new TipoSesiones();
			tipoSesiones.settIposesionidpk(tiposesion);
			tipoSesiones = fijasService.buscarPorCodigoTipoSesion(tipoSesiones);
			
			// BUSCAR COMISION POR NOMBRE Y LUEGO ASIGNARLE CODIGO
			
			if(ConstantesUtil.C_ROLE_OPECONSSAT.equals(nombreRol) || ConstantesUtil.C_ROLE_OPECORSSAT.equals(nombreRol)) {
				Comisiones comision = new Comisiones();
				comision = comisionService.buscarComisionPorNombre(cOmisionfk,idRegion);
				generico.setComisionfk(comision);
			}
			
			
			generico.setRegion(region);
			generico.setConsejofk(consejo);
			
			generico.setnUsureg(idUsuario);
			generico.setTipoSesiones(tipoSesiones);
			generico.setdFecreacion(FechasUtil.convertStringToDate(dFecreacion));
			generico.setdHorinicio(dHorinicio);
			generico.setdHorfin(dHorfin);
			generico = sesionService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Sesiones>(generico,HttpStatus.CREATED);
	}

	
	@ApiOperation(value = "Actualiza una sesion")
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarSesion( 
			 @RequestParam(value="sEsionidpk")  Long sEsionidpk,
			 @RequestParam(value="cOmisionfk")  Long cOmisionfk,
			 @RequestParam(value="tiposesion")  Long tiposesion,
			 @RequestParam(value="dFecreacion") String dFecreacion,
			 @RequestParam(value="dHorinicio")  String dHorinicio,
			 @RequestParam(value="dHorfin")     String dHorfin,
			 @RequestParam(value="codusuario")  Long codusuario,
			 @RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			 @RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			 @RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		logger.info("============  BUSCAR PROFESION =================");
		Sesiones generico = new Sesiones();
		Map<String, Object> response = new HashMap<>();
		try {
			generico.setsEsionidpk(sEsionidpk);
			generico = sesionService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SESION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SESION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			generico.setdFecreacion(FechasUtil.convertStringToDate(dFecreacion));
			 generico.setdHorinicio(dHorinicio);
			 generico.setdHorfin(dHorfin); 
			 generico.setnUsumodifica(idUsuario);
			generico = sesionService.Actualizar(cOmisionfk,tiposesion,generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
 
		return new ResponseEntity<Sesiones>(generico,HttpStatus.OK);
	}

	
	@ApiOperation(value = "Elimina una sesion")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarSesion(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("============  BUSCAR PROFESION =================");
		Sesiones generico =  new Sesiones();
		generico.setsEsionidpk(id); 
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = sesionService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SESION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SESION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			generico.setnUsuelimina(idUsuario);
			generico = sesionService.Eliminar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
 
		return new ResponseEntity<Sesiones>(generico,HttpStatus.OK);

	}
	
	
	@ApiOperation(value = "Busca una sesion por nombre")
	@PostMapping("/buscarpornombre")
	public List<Sesiones> buscarSesion(
			@RequestParam(value="nombresesion")  String  nombresesion ,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
	      ){
		
		//*****  DATOS DE USUARIO DE INICIO DE SESION **********
		  Long idconsejo = 0L;  
		  idconsejo = fijasService.BuscarConsejoPorNombre(nombreRol);
		//*******************************************************
		  
		  List<Sesiones>  buscarsesion = new ArrayList<Sesiones>();
		  Sesiones sesionInfo = new Sesiones();
		  try {
			
			    Consejos consejo = new Consejos();
				consejo.setcOnsejoidpk(idconsejo);
				//consejo = fijasService.buscarPorCodigoConsejo(consejo);
								
				Regiones region = new Regiones();
				region.setrEgionidpk(idRegion);
				//region = fijasService.buscarPorCodigoRegion(region);
				
				sesionInfo.setvCodsesion(nombresesion);
				sesionInfo.setConsejofk(consejo);
				sesionInfo.setRegion(region);
				
				buscarsesion = sesionService.buscarSesion(sesionInfo);
				
		} catch (DataAccessException e) {
			return null;
		}
		
		return buscarsesion;
	}
	
 

}
