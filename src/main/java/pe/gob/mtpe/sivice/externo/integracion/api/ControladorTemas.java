package pe.gob.mtpe.sivice.externo.integracion.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Temas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoTemas;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.TemaService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/temas" })
public class ControladorTemas {

	private static final Logger logger = LoggerFactory.getLogger(ControladorTemas.class);

	@Autowired
	private TemaService temaService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;

	@GetMapping("/")
	public List<Temas> listarTemas() {
		return temaService.listar();
	}

	@PostMapping("/buscar")
	public List<Temas> buscarTemas(@RequestBody Temas buscar) {
		return temaService.buscar(buscar);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdPlanTrabajo(@PathVariable Long id) {
		Temas generico = new Temas();
		generico.settEmaidpk(id);
 
		Map<String, Object> response = new HashMap<>();
		try {
			generico = temaService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TEMA_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TEMA_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Temas>(generico,HttpStatus.OK);
	}

	/*
	@PostMapping("/temas")
	public ResponseEntity<?> registrarTemas(@RequestBody Temas generico) {
		logger.info("============  BUSCAR registrarTemas =================");
		Map<String, Object> response = new HashMap<>();
		try {
			generico = temaService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Temas>(generico,HttpStatus.CREATED);
	}
	*/
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarTemas(
			@RequestParam("docAdjunto1") MultipartFile docAdjunto1,
			@RequestParam("docAdjunto2") MultipartFile docAdjunto2,
			@RequestParam("docAdjunto3") MultipartFile docAdjunto3,
			@RequestParam("sEsionfk") Long sEsionfk,
			@RequestParam("tIpotemafk") Long tIpotemafk,
			@RequestParam("vDescripcion") String vDescripcion
			
			) {
		
		 Archivos archivo1 = new Archivos();
		 Archivos archivo2 = new Archivos();
		 Archivos archivo3 = new Archivos();
		 Temas generico= new Temas();
		
		Map<String, Object> response = new HashMap<>();
		 try {
			 
			if((docAdjunto1.isEmpty() || docAdjunto2.isEmpty() || docAdjunto3.isEmpty()) || 
					(docAdjunto1.isEmpty() && docAdjunto2.isEmpty() && docAdjunto3.isEmpty()) ) {
				logger.info("=============== ESTAN VACIOS ============ ");
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SESION_MSG_ERROR_ARCHIVO);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SESION_ERROR_ARCHIVO);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				
			}else {
				logger.info("=============== NO ESTAN VACIOS ============ ");
				archivo1 = archivoUtilitarioService.cargarArchivo(docAdjunto1, ConstantesUtil.SESION_ARCHIVO_1_SIGLA);
				archivo2 = archivoUtilitarioService.cargarArchivo(docAdjunto2, ConstantesUtil.SESION_ARCHIVO_2_SIGLA);
				archivo3 = archivoUtilitarioService.cargarArchivo(docAdjunto3, ConstantesUtil.SESION_ARCHIVO_3_SIGLA);
				
				generico.setvNombrearchivo1(archivo1.getNombre());
				generico.setvUbiarchivo1(archivo1.getUbicacion());
				generico.setvExtarchivo1(archivo1.getExtension());
				
				generico.setvNombrearchivo2(archivo2.getNombre());
				generico.setvUbiarchivo2(archivo2.getUbicacion());
				generico.setvExtarchivo2(archivo2.getExtension());
				
				generico.setvNombrearchivo3(archivo3.getNombre());
				generico.setvUbiarchivo3(archivo3.getUbicacion());
				generico.setvExtarchivo3(archivo3.getExtension());
				
				TipoTemas pipoTema =  new TipoTemas();
				pipoTema.settIpotemaidpk(tIpotemafk);
				
				generico.setsEsionfk(sEsionfk);
				//generico.settIpotemafk(tIpotemafk);
				generico.settIpotemafk(pipoTema);
				generico.setvDescripcion(vDescripcion);
				
				generico = temaService.Registrar(generico);
				
				
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 return new ResponseEntity<Temas>(generico,HttpStatus.CREATED);
	}
	
	
	

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarTemas(@RequestBody Temas temas) {
		logger.info("============  BUSCAR actualizarTemas =================");
		Temas generico = new Temas();
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = temaService.buscarPorId(temas);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TEMA_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TEMA_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, temas);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		 
			temas.setnUsureg(generico.getnUsureg());
			temas.setdFecreg(generico.getdFecreg());
			generico = temaService.Actualizar(temas);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, temas);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Temas>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarTemas(@PathVariable Long id) {
		logger.info("==========  eliminarTemas  ===========");
		Temas generico = new Temas();
		generico.settEmaidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = temaService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.TEMA_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.TEMA_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		 
			
			generico = temaService.Eliminar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		 
		return new ResponseEntity<Temas>(generico,HttpStatus.OK);

	}

}
