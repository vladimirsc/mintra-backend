package pe.gob.mtpe.sivice.externo.integracion.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Comisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoComisiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ComisionService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping({ "api/comisiones" })
public class ControladorComisiones {

	private static final Logger logger = LoggerFactory.getLogger(ControladorComisiones.class);

	@Autowired
	private ComisionService comisionService;

	@Autowired
	private FijasService fijasService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;
	
	@Value("${rutaArchivo}")
	private String rutaRaiz;

	// @Secured({"ROLE_ADMCONSSAT","ROLE_ADMCORSSAT","ROLE_OPECONSSAT","ROLE_OPECORSSAT"})
	
	
	@ApiOperation(value = "Listar Comisiones")
	@GetMapping("/")
	public List<Comisiones> listarComisiones(
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		
		logger.info("========== listarComisiones =============== ");
 
		Regiones region = new Regiones();
		region.setrEgionidpk(idRegion);
 
		Comisiones comisiones = new Comisiones();
		comisiones.setRegion(region);
		
		return comisionService.listar(comisiones);
	}

	
	@ApiOperation(value = "Mostrar informacion de la comision por su codigo identificador")
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdComision(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		Comisiones generico = new Comisiones();
		generico.setcOmisionidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = comisionService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.COMISION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.COMISION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Comisiones>(generico, HttpStatus.OK);
	}

	
	@ApiOperation(value = "Buscar comisiones por criterio de busquedas")
	@PostMapping("/buscar")
	public List<Comisiones> buscarComisiones(
			@RequestBody Comisiones buscar,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		
		Regiones region = new Regiones();
		region.setrEgionidpk(idRegion);
		
		buscar.setRegion(region); 
		return comisionService.buscar(buscar);
	}

	@ApiOperation(value = "Registrar una comision")
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarComisiones(
			@RequestParam(value="tipocomision")         Long          tipocomision,
			@RequestParam(value="numerodocaprobacion")  String        numerodocaprobacion,
			@RequestParam(value ="fechaaprobacion")     String        fechaaprobacion,
			@RequestParam(value="archivocomision", required = true)      MultipartFile archivocomision,
			@RequestParam(value ="fechainicio")         String        fechainicio,
			@RequestParam(value ="fechafin")            String        fechafin,
			@RequestParam(value ="consejeroasignado")   Long          consejeroasignado,
			@RequestParam(value ="descripcion")            String        descripcion,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		
		Comisiones generico = new Comisiones();
		Map<String, Object> response = new HashMap<>();
		try {

			// ***** DATOS DE USUARIO DE INICIO DE SESION **********
			Long codigoconsejo = fijasService.BuscarConsejoPorNombre(nombreRol); // CONSSAT

			Consejos consejo = new Consejos();
			consejo.setcOnsejoidpk(codigoconsejo);
			consejo = fijasService.buscarPorCodigoConsejo(consejo);

			Regiones region = new Regiones();
			region.setrEgionidpk(idRegion);
			region = fijasService.buscarPorCodigoRegion(region);
			// *******************************************************
			
			
			if(archivocomision!=null && archivocomision.getSize()>0) {
				Archivos archivo = new Archivos();
				archivo = archivoUtilitarioService.cargarArchivo(archivocomision, ConstantesUtil.C_COMISIONES);
				generico.setvUbidocap(archivo.getUbicacion());
				generico.setvNombreArchivo(archivo.getNombre());
				generico.setvArchivoextension(archivo.getExtension());
			}
			
			Consejeros consejero = new Consejeros();
			consejero.setcOnsejeroidpk(consejeroasignado);
			
			TipoComisiones vtipocomision = new TipoComisiones();
			vtipocomision.settIpocomsidpk(tipocomision);
			vtipocomision = fijasService.buscarPorCodigoTipoComision(vtipocomision);
			
			generico.setConsejero(consejero);
			generico.setRegion(region);
			generico.setConsejo(consejo);
			generico.setTipocomision(vtipocomision);
			generico.setvNumdocapr(numerodocaprobacion);
			generico.setdFecdocapr( (fechaaprobacion !=null)?FechasUtil.convertStringToDate(fechaaprobacion) : null );
			generico.setdFecinicio( (fechainicio!=null)?FechasUtil.convertStringToDate(fechainicio) : null );
			generico.setdFecfin((fechafin!=null)?FechasUtil.convertStringToDate(fechafin) : null );
			generico.setnUsureg(idUsuario);
			generico.setvDescripcion(descripcion);
			
			generico = comisionService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Comisiones>(generico, HttpStatus.CREATED);
	}

	
	@ApiOperation(value = "Actualizar una comision")
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarComisiones(
			@RequestParam(value="codigocomision")       Long          codigocomision,
			@RequestParam(value="tipocomision")         Long          tipocomision,
			@RequestParam(value="numerodocaprobacion")  String        numerodocaprobacion,
			@RequestParam(value ="fechaaprobacion")    String        fechaaprobacion,
			@RequestParam(value="archivocomision")      MultipartFile archivocomision,
			@RequestParam(value ="fechainicio")         String        fechainicio,
			@RequestParam(value ="fechafin")            String        fechafin ,
			@RequestParam(value ="consejeroasignado")   Long          consejeroasignado,
			@RequestParam(value ="descripcion")            String        descripcion,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		
		Comisiones generico = new Comisiones();
		Map<String, Object> response = new HashMap<>();
		try {

			// ***** DATOS DE USUARIO DE INICIO DE SESION **********
			Long codigoconsejo = fijasService.BuscarConsejoPorNombre(nombreRol); // CONSSAT

			Consejos consejo = new Consejos();
			consejo.setcOnsejoidpk(codigoconsejo);

			Regiones region = new Regiones();
			region.setrEgionidpk(idRegion);
			// *******************************************************
			
			
			if(archivocomision!=null && archivocomision.getSize()>0) {
				Archivos archivo = new Archivos();
				archivo = archivoUtilitarioService.cargarArchivo(archivocomision, ConstantesUtil.C_COMISIONES);
				generico.setvUbidocap(archivo.getUbicacion());
				generico.setvNombreArchivo(archivo.getNombre());
				generico.setvArchivoextension(archivo.getExtension());
			}
			
			Consejeros consejero = new Consejeros();
			consejero.setcOnsejeroidpk(consejeroasignado);
			
			TipoComisiones vtipocomision = new TipoComisiones();
			vtipocomision.settIpocomsidpk(tipocomision);
			
			generico.setcOmisionidpk(codigocomision);
			generico=comisionService.buscarPorId(generico);
 
			generico.setConsejero(consejero);
			generico.setRegion(region);
			generico.setConsejo(consejo);
			generico.setTipocomision(vtipocomision);
			generico.setvNumdocapr(numerodocaprobacion);
			generico.setdFecdocapr( (fechaaprobacion !=null)?FechasUtil.convertStringToDate(fechaaprobacion) : null );
			generico.setdFecinicio( (fechainicio!=null)?FechasUtil.convertStringToDate(fechainicio) : null );
			generico.setdFecfin((fechafin!=null)?FechasUtil.convertStringToDate(fechafin) : null );
			generico.setnUsumodifica(idUsuario);
			generico.setvDescripcion(descripcion);
			
			generico = comisionService.Actualizar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Comisiones>(generico, HttpStatus.OK);
	}

	
	@ApiOperation(value = "Eliminar una comision")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarComisiones(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("==========  insertarConsejeros  ===========");
		Comisiones generico = new Comisiones();
		generico.setcOmisionidpk(id);

		Map<String, Object> response = new HashMap<>();
		try {
			generico = comisionService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.COMISION_CONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.COMISION_CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			generico.setnUsuelimina(idUsuario);
			generico = comisionService.Eliminar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Comisiones>(generico, HttpStatus.OK);

	}

	
	@ApiOperation(value = "Buscar comision por su nombre")
	@PostMapping("/buscarpornombre")
	public List<Comisiones> buscarComision(
			@RequestParam(value = "nombrecomision") String nombrecomision,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		
		
		// ***** DATOS DE USUARIO DE INICIO DE SESION **********
		Long codigoconsejo = fijasService.BuscarConsejoPorNombre(nombreRol); // CONSSAT

		Consejos consejo = new Consejos();
		consejo.setcOnsejoidpk(codigoconsejo);

		Regiones region = new Regiones();
		region.setrEgionidpk(idRegion);
		// *******************************************************
		
		Comisiones generico = new Comisiones();
		generico.setvCodcomision(nombrecomision);
		generico.setConsejo(consejo);
		generico.setRegion(region);
		
		return comisionService.buscarComision(generico);
	}
	
	
	
	@ApiOperation(value = "descargar archivo de la comision")
	@GetMapping("/descargar/{id}")
	public void descargarArchivo(
			@PathVariable Long id, 
			HttpServletResponse res) {
		Comisiones generico = new Comisiones();
		generico.setcOmisionidpk(id);
		String ruta = "";
		try {
			generico = comisionService.buscarPorId(generico);
			ruta = rutaRaiz + generico.obtenerRutaAbsoluta();
			res.setHeader("Content-Disposition", "attachment; filename=" + generico.getvNombreArchivo()+"."+generico.getvArchivoextension());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	
	

}
