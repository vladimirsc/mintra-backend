package pe.gob.mtpe.sivice.externo.integracion.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Boletines;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.BoletinService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({"api/boletines"})
public class ControladorBoletines {

	private static final Logger logger = LoggerFactory.getLogger(ControladorBoletines.class);

	@Autowired
	private BoletinService boletinService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;
	
	@Autowired
	private  FijasService fijasService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;

	@GetMapping("/")
	public List<Boletines> listarBoletines(
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario) {
		logger.info("========== listarBoletines =============== "+idUsuario.toString()); 
		return boletinService.listar();
	}

  
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarBoletin(
			@RequestParam("archivoboletin") MultipartFile archivoboletin, 
			@RequestParam("fecha") String fecha_boletin) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Boletines generico = new Boletines();
		Archivos archivo = new Archivos();
		
		Map<String, Object> response = new HashMap<>();
		try {
			
			archivo = archivoUtilitarioService.cargarArchivo(archivoboletin, ConstantesUtil.BOLETIN_ALIAS_CORRELATIVO);

			if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) { 
				
				//*****  DATOS DE USUARIO DE INICIO DE SESION **********
				Long codigoconsejo=fijasService.BuscarConsejoPorNombre(ConstantesUtil.c_rolusuario); // CONSSAT
 
				Consejos consejo = new Consejos();
				consejo.setcOnsejoidpk(codigoconsejo);
				
				Regiones region = new Regiones();
				region.setrEgionidpk(ConstantesUtil.c_codigoregion);
				//*******************************************************
				
				generico.setdFecboletin(FechasUtil.convertStringToDate(fecha_boletin));
				generico.setvNombrearchivo(archivo.getNombre());
				generico.setvArchivoextension(archivo.getExtension());
				generico.setvUbiarch(archivo.getUbicacion());
				generico.setConsejo(consejo);
				generico.setRegion(region);
				generico.setnUsureg(ConstantesUtil.c_usuariologin);
				
				generico = boletinService.Registrar(generico);
			} else {
				response.put(ConstantesUtil.X_MENSAJE, "ARCHIVO NO ADJUNTO");
				response.put(ConstantesUtil.X_ERROR, "NO SE ENCONTRO EL ARCHIVO ADJUNTO");
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

			}
			
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Boletines>(generico,HttpStatus.CREATED);
	}
	
	

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarBoletin(
			@RequestParam(value="codigoboletin")                                 Long codigoboletin,
			@RequestParam(value="archivoboletin",required = false) MultipartFile archivoboletin, 
			@RequestParam(value="fecha")                                         String fecha_boletin
			) {
		logger.info("========== ACTUALIZAR A GRABAR BOLETINES=============== ");
		Archivos archivo = new Archivos();
		Boletines generico = new Boletines();
		Map<String, Object> response = new HashMap<>();
		try {
			generico.setbOletinidpk(codigoboletin);
			generico = boletinService.buscarPorId(generico);
			
			if(archivoboletin!=null && archivoboletin.getSize()>0) {
				archivo = archivoUtilitarioService.cargarArchivo(archivoboletin, ConstantesUtil.C_BOLETINES);
				generico.setvUbiarch(archivo.getUbicacion());
				generico.setvNombrearchivo((archivo.getNombre()));
				generico.setvArchivoextension(archivo.getExtension());
			}
			
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.BOLETIN_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.BOLETIN_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			if(fecha_boletin!=null) {
				generico.setdFecboletin(FechasUtil.convertStringToDate(fecha_boletin));
			}
 
			generico.setnUsumodifica(ConstantesUtil.c_usuariologin);
			
			generico = boletinService.Actualizar(generico);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		
		return new ResponseEntity<Boletines>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarBoletin(@PathVariable Long id) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Boletines generico = new Boletines();
		generico.setbOletinidpk(id);
		Map<String, Object> response = new HashMap<>();
		try { 
			
			generico = boletinService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.BOLETIN_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.BOLETIN_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			generico.setnUsuelimina(ConstantesUtil.c_usuariologin);
			generico = boletinService.Eliminar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.BOLETIN_MSG_EXITO_ELIMINAR);
		response.put(ConstantesUtil.X_ENTIDAD, generico);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Boletines generico = new Boletines();
		generico.setbOletinidpk(id);
		Map<String, Object> response = new HashMap<>();
		try { 
			
			generico = boletinService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.BOLETIN_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.BOLETIN_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
	 
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		response.put(ConstantesUtil.X_ENTIDAD, generico);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	@PostMapping("/buscar")
	public List<Boletines> buscar(@RequestBody Boletines buscar) {
		 
		return boletinService.buscarBoletines(buscar);
	}
	
	@GetMapping("/descargar/{id}")
	public void descargarArchivo(@PathVariable Long id, HttpServletResponse res) {
		Boletines generico = new Boletines();
		generico.setbOletinidpk(id);
		String ruta = "";
		try {
			generico = boletinService.buscarPorId(generico);
			ruta = rutaRaiz + generico.obtenerRutaAbsoluta();
			res.setHeader("Content-Disposition", "attachment; filename=" + generico.getvNombrearchivo()+"."+generico.getvArchivoextension());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	
 

}
