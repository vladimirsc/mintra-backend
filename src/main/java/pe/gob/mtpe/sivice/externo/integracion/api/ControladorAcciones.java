package pe.gob.mtpe.sivice.externo.integracion.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acciones; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AccionesService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "api/acciones" })
public class ControladorAcciones {

	private static final Logger logger = LoggerFactory.getLogger(ControladorAcciones.class);

	@Autowired
	private AccionesService accionesService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;
	
	
	@Value("${rutaArchivo}")
	private String rutaRaiz;

	@GetMapping("/")
	public List<Acciones> listar() {
		return accionesService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarSeguimientos(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		Acciones generico = new Acciones();
		generico.setaCcionesidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = accionesService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SEGUIMIENTO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SEGUIMIENTO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Acciones>(generico,HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public List<Acciones> buscar(@RequestBody Acciones buscar,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		return accionesService.buscar(buscar);
	}

	

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(
	     @RequestParam(value = "idacuerdo")          Long idacuerdo,
	     @RequestParam(value = "identidad")          Long identidad,
	     @RequestParam(value = "responsable")        String responsable,
	     @RequestParam(value = "descripcionaccion")  String descripcionaccion,
	     @RequestParam(value = "fecha_ejecutara")    String fecha_ejecutara,
	     @RequestParam(value = "flgejecuto")         String flgejecuto,
	     @RequestParam(value = "fecha_ejecuto")      String fecha_ejecuto,
	     @RequestParam(value = "docaccion")          MultipartFile docaccion,@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
		 @RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
		 @RequestHeader(name = "info_rol", required = true) String nombreRol
	) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Acciones generico = new Acciones();
		Map<String, Object> response = new HashMap<>();
		try {
			generico = accionesService.Registrar(idacuerdo,identidad,responsable,descripcionaccion,fecha_ejecutara,flgejecuto,fecha_ejecuto,docaccion);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Acciones>(generico,HttpStatus.CREATED);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizar(
			@RequestParam(value = "idaccion")      Long idaccion, 
			@RequestParam(value = "flgejecuto")         String flgejecuto,
			@RequestParam(value = "fecha_ejecuto") String fecha_ejecuto,
			@RequestParam(value = "docaccion")      MultipartFile docaccion,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		Acciones generico = new Acciones();
		Map<String, Object> response = new HashMap<>();
		try {
			generico.setaCcionesidpk(idaccion);
			
			generico = accionesService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SEGUIMIENTO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SEGUIMIENTO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
			}
			 if(!docaccion.isEmpty()) {
				 Archivos archivo = new Archivos();
				 archivo = archivoUtilitarioService.cargarArchivo(docaccion, ConstantesUtil.C_CONSEJERO_DOC_ASIGNACION);
				 if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) {
					 generico.setvNombrearchivo(archivo.getNombre());
					 generico.setvUbiarch(archivo.getUbicacion());
					 generico.setvExtarchivo(archivo.getExtension());
				 }
			 }
			generico.setdFecejecuto(FechasUtil.convertStringToDate(fecha_ejecuto));
			generico = accionesService.Actualizar(generico);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		
		return new ResponseEntity<Acciones>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("==========  insertarConsejeros  ===========");
		Acciones generico = new Acciones();
		generico.setaCcionesidpk(id);
		
		Map<String, Object> response = new HashMap<>();
		try {
			generico = accionesService.buscarPorId(generico);
			
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SEGUIMIENTO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SEGUIMIENTO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			generico = accionesService.Eliminar(generico);
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Acciones>(generico,HttpStatus.OK);

	}
	
	@GetMapping("/accionesporacuerdo/{idacuerdo}")
	public List<Acciones> listarAccionesPorAcuerdo(
			@PathVariable Long idacuerdo,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol){
		List<Acciones> listaAcciones = new ArrayList<Acciones>();
		listaAcciones = accionesService.listarAccionesPorAcuerdo(idacuerdo);
		return listaAcciones;
	}
	
	
	@GetMapping("/descargar/{id}")
	public void descargarArchivo(
			@PathVariable Long id, HttpServletResponse res) {
		Acciones generico = new Acciones();
		generico.setaCcionesidpk(id);
		String ruta = "";
		try {
			generico = accionesService.buscarPorId(generico);
			ruta = rutaRaiz + generico.obtenerRutaAbsoluta();
			res.setHeader("Content-Disposition", "attachment; filename=" + generico.getvNombrearchivo()+"."+generico.getvExtarchivo());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	

}
