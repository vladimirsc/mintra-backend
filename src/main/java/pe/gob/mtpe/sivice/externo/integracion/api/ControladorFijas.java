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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Entidades;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Profesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Roles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoComisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoSesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoTemas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Tipoconsejero;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping({"/api/fijas"})
public class ControladorFijas {
	
	@Autowired
	private FijasService fijasService;
	
	private static final Logger logger = LoggerFactory.getLogger(ControladorFijas.class);
	
	
	// ==================    PROFESIONES          ===========================	
	@ApiOperation(value = "Lista las profesiones")
	@GetMapping({"/listarprofesiones"})
	public List<Profesiones> listarProfesiones() {
		logger.info("============  LISTAR PROFESIONES =================");
		return fijasService.listarProfesiones();
	} 
	
	
	@ApiOperation(value = "Buscar profesion por codigo")
	@GetMapping("/buscarprofesion/{id}")
    public ResponseEntity<?> buscarPorIdProfesion(@PathVariable Long id) {
		logger.info("============  BUSCAR PROFESION =================");
		Profesiones generico = new Profesiones();
		generico.setpRofesionidpk(id);
		
    	Map<String,Object> response = new HashMap<>();
    	try {
    		generico =fijasService.buscarPorCodigoProfesion(generico);
    		  
    		  if(generico==null) {
    	    		response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.FPROFESION_MSG_ERROR_BUSCAR);
    				response.put(ConstantesUtil.X_ERROR,ConstantesUtil.FPROFESION_ERROR_BUSCAR);
    				response.put(ConstantesUtil.X_ENTIDAD, generico);
    	    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
    	    	}
    		  
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    	return new ResponseEntity<Profesiones>(generico,HttpStatus.OK);
    	 
    }
	
	
	// ==================    TIPOS DE DOCUMENTOS  ===========================
	@ApiOperation(value = "Lista los tipo de documentos")
	@GetMapping({"/listartipodocumentos"})
	public List<TipoDocumentos> listarTipoDocumentos() {
		logger.info("============  LISTAR listarTipoDocumentos =================");
		return fijasService.listarTipoDocumentos();
	} 
	
	
	
	@ApiOperation(value = "Busca el tipo de documento por su codigo")
	@GetMapping("/buscartipodocumento/{id}")
    public ResponseEntity<?> buscarPorIdTipoDocumento(@PathVariable Long id) {
		logger.info("============  BUSCAR buscarPorIdTipoDocumento =================");
		TipoDocumentos generico = new TipoDocumentos();
		generico.settPdocumentoidpk(id);
    	Map<String,Object> response = new HashMap<>();
    	try {
    		generico =fijasService.buscarPorCodigoTipoDocumento(generico);
 
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TIPODOCUMENTO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TIPODOCUMENTO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    	return new ResponseEntity<TipoDocumentos>(generico,HttpStatus.OK);
    }
	
	
	// ==================    TIPOS DE CONSEJEROS  ===========================
	@ApiOperation(value = "Lista los tipos de consejeros")
	@GetMapping({"/listartipoconsejeros"})
	public List<Tipoconsejero> listarConsejeros() {
		logger.info("============  LISTAR listarConsejeros =================");
		return fijasService.listarTipoConsejeros();
	} 
	
	
	@ApiOperation(value = "Buscar consejero por su codigo")
	@GetMapping("/buscartipoconsejero/{id}")
    public ResponseEntity<?> buscarPorIdConsejero(@PathVariable Long id) {
		logger.info("============  BUSCAR buscarPorIdConsejero =================");
		Tipoconsejero generico = new Tipoconsejero();
		generico.settPconsejeroidpk(id);
    	Map<String,Object> response = new HashMap<>();
    	try {
    		
    		generico =fijasService.buscarPorCodigoTipoConsejero(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TIPOCONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TIPOCONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    	return new ResponseEntity<Tipoconsejero>(generico,HttpStatus.OK);
    }

	// ==================    TIPOS DE REGIONES    ===========================
	@ApiOperation(value = "Lista las regiones")
	@GetMapping({"/listaregiones"})
	public List<Regiones> listarRegiones(
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("============  LISTAR listarRegiones =================");
		
		 List<Regiones>  lstregiones=  new ArrayList<Regiones>();
		
		if(ConstantesUtil.C_ROLE_ADMCONSSAT.equals(nombreRol)) {
			lstregiones = fijasService.listarTipoRegiones();
		}else {
			Regiones regiones= new Regiones();
			regiones.setrEgionidpk(idRegion);
			lstregiones = fijasService.listarTipoRegiones(regiones);
		}
		return lstregiones;
	} 
	
	
	
	@ApiOperation(value = "Busca region por su codigo")
	@GetMapping("/buscaregion/{id}")
    public ResponseEntity<?> buscarPorIdRegion(@PathVariable Long id) {
		logger.info("============  BUSCAR buscarPorIdRegion =================");
		Regiones generico = new Regiones();
		generico.setrEgionidpk(id);
    	Map<String,Object> response = new HashMap<>();
    	try {
    		
    		generico =fijasService.buscarPorCodigoRegion(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.REGION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.REGION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    	return new ResponseEntity<Regiones>(generico,HttpStatus.OK);
    }
	
	// ==================   listar tipo CONSEJO     ===========================
	@ApiOperation(value = "Lista los tipos de consejo")
	@GetMapping({"/listartipoconsejo"})
	public List<Consejos> listarConsejos() {
		logger.info("============  LISTAR listarConsejos =================");
		return fijasService.listarConsejos();
	} 
	
	
	
	@ApiOperation(value = "Busca consejo por su codigo")
	@GetMapping("/buscarconsejo/{id}")
    public ResponseEntity<?> buscarPorIdConsejo(@PathVariable Long id) {
		logger.info("============  BUSCAR buscarPorIdConsejo =================");
		Consejos generico = new Consejos();
		generico.setcOnsejoidpk(id);
    	Map<String,Object> response = new HashMap<>();
    	try {
    		
    		generico =fijasService.buscarPorCodigoConsejo(generico);
    		 
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TIPO_CONSEJO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TIPO_CONSEJO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	 
    	return new ResponseEntity<Consejos>(generico,HttpStatus.OK);
    }
	
	
	
	// ==================    TIPOS DE COMISIONES  ===========================
	@ApiOperation(value = "Lista los tipo de comisiones")
	@GetMapping({"/listartipocomisiones"})
	public List<TipoComisiones> listarTipoComisiones() {
		logger.info("============  LISTAR listarTipoComisiones =================");
		return fijasService.listarTipoComisiones();
	} 
	
	
	
	@ApiOperation(value = "Busca comision por su codigo")
	@GetMapping("/buscartipocomision/{id}")
    public ResponseEntity<?> buscarPorIdTipoComisiones(@PathVariable Long id) {
		logger.info("============  BUSCAR buscarPorIdTipoComisiones =================");
		TipoComisiones generico = new TipoComisiones();
		generico.settIpocomsidpk(id);
    	Map<String,Object> response = new HashMap<>();
    	try {
    		
    		generico =fijasService.buscarPorCodigoTipoComision(generico);

			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TIPO_COMISION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TIPO_COMISION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
    		
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	 
    	return new ResponseEntity<TipoComisiones>(generico,HttpStatus.OK);
    }
	
	// ==================    TIPOS DE SESION      ===========================
	@ApiOperation(value = "Lista los tipo de sesion")
	@GetMapping({"/listartiposesion"})
	public List<TipoSesiones> listarTipoSesion() {
		logger.info("============  LISTAR listarTipoSesion =================");
		return fijasService.listarTipoSesion();
	} 
	
	
	
	@ApiOperation(value = "Lista informacion de una sesion por su codigo")
	@GetMapping("/buscarsesion/{id}")
    public ResponseEntity<?> buscarPorCodigoTipoSesion(@PathVariable Long id) {
		logger.info("============  BUSCAR buscarPorIdTipoSesion =================");
		TipoSesiones generico = new TipoSesiones();
		generico.settIposesionidpk(id);
    	Map<String,Object> response = new HashMap<>();
    	try {
    		
    		generico =fijasService.buscarPorCodigoTipoSesion(generico);
    		 
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TIPO_SESION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TIPO_SESION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    		
    	return new ResponseEntity<TipoSesiones>(generico,HttpStatus.OK);
    	 
    }
	
	
	// ==================    TIPOS DE TEMAS       ===========================
	@ApiOperation(value = "Lista los tipo de temas")
	@GetMapping({"/listartemas"})
	public List<TipoTemas> listarTipoTemas() {
		logger.info("============  LISTAR listarTipoTemas =================");
		return fijasService.listarTipoTemas();
	} 
	
	
	
	@ApiOperation(value = "Busca un tema por su codigo")
	@GetMapping("/buscartema/{id}")
    public ResponseEntity<?> buscarPorIdTipoTema(@PathVariable Long id) {
		logger.info("============  BUSCAR buscarPorIdTipoTema =================");
		TipoTemas generico = new TipoTemas();
		generico.settIpotemaidpk(id);
    	Map<String,Object> response = new HashMap<>();
    	try {
    		
    		generico =fijasService.buscarPorCodigoTipoTema(generico); 
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TIPO_TEMA_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TIPO_TEMA_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    	 
    	return new ResponseEntity<TipoTemas>(generico,HttpStatus.OK);
    	 
    }
	
	
	
	// ==================    ENTIDADES       ===========================
	@ApiOperation(value = "Lista las entidades")
	@GetMapping({"/listarentidades"})
	public List<Entidades> listarEntidades() {
		logger.info("============  LISTAR listarTipoTemas =================");
		return fijasService.listarEntidades();
	} 
	
	
	
	
	@ApiOperation(value = "Busca entidad por su codigo")
	@GetMapping("/buscarentidad/{id}")
    public ResponseEntity<?> buscarPorIdEntidad(@PathVariable Long id) {
		Entidades entidad = new Entidades();
		entidad.seteNtidadidpk(id);
		Map<String,Object> response = new HashMap<>();
		try {
			entidad = fijasService.buscarPorEntidad(entidad);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, entidad);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Entidades>(entidad,HttpStatus.OK);
	}
	  
	// ==================    ENTIDADES       ===========================
	@ApiOperation(value = "Lista los roles")
	@GetMapping({"/listaroles"})
	public List<Roles> listarRoles(
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		logger.info("============  LISTAR listarTipoTemas =================");
		List<Roles> listaRoles = new ArrayList<Roles>();
		
		if(ConstantesUtil.C_ROLE_ADMCONSSAT.equals(nombreRol)) {
			listaRoles = fijasService.listaRoles();
		}else {
			listaRoles =  fijasService.listaRolesCorssat();
		}
		return listaRoles;
	} 
	
}
