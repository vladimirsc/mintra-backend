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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Entidades;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Firmantes;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FijasDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ActaService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({"/api/actas"})
public class ControladorActas {

	private static final Logger logger = LoggerFactory.getLogger(ControladorActas.class);

	@Autowired
	private ActaService actaService;

	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;
	
	@Autowired
	private FijasDao fijasDao;


	@Value("${rutaArchivo}")
	private String rutaRaiz;
	
 
 /*
	@GetMapping("/{idsesion}")  //CABECERA DEL ACTA (INFORMACION DE LA SESION)
	public ResponseEntity<?> cabeceraActa(@PathVariable Long idsesion) {
		logger.info("==========LISTAR ACTAS=============== ");
		Sesiones generico = new Sesiones();
		Map<String, Object> response = new HashMap<>();
		try {
			generico.setsEsionidpk(idsesion);
			generico = actaService.cabeceraActa(generico);
			if(generico==null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACTAS_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACTAS_ERROR_BUSCAR);
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

		return new ResponseEntity<Sesiones>(generico, HttpStatus.OK);
		 
	}
	*/
 
	@GetMapping("/actaporsesion/{idsesion}")   //CUERPO DEL ACTA POR SESION
	ResponseEntity<?> actasPorSesion(@PathVariable Long idsesion){
		Actas acta = new Actas();
		Map<String, Object> response = new HashMap<>();
		try {
			//buscamos el acta de la sesion
			acta = actaService.buscarActaPorIdSesion(idsesion);
			 if(acta==null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACTAS_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACTAS_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, acta);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
			 }

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD,acta);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Actas>(acta, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/listaracuerdosporacta/{idsesion}")  //CUERPO LISTA DE ACUERDOS POR ACTA
	List<Acuerdos> listaAcuerdosPorActa(
			@PathVariable Long idsesion
	 ){
		
		Sesiones sesion = new Sesiones();
		sesion.setsEsionidpk(idsesion);
		Actas acta = new Actas();
		acta.setSesionfk(sesion);
		
		List<Acuerdos> listarAcuerdos =  actaService.listaAcuerdosPorActa(acta);
		
		return listarAcuerdos;
	}
	
	@GetMapping("/{idacta}")  //CABECERA DEL ACTA (INFORMACION DE LA SESION)
	public ResponseEntity<?> cabeceraActa(@PathVariable Long idacta) {
		Actas actas = new Actas();
		Map<String, Object> response = new HashMap<>();
		try {
			actas.setaCtaidpk(idacta);
			actas=actaService.buscarPorId(actas);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, actas);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Actas>(actas, HttpStatus.OK); 
	}
	
	
	@PostMapping("/registraracuerdos")               //REGISTRAR LOS ACUERDOS
	public ResponseEntity<?> registrarAcuerdo(
			@RequestParam("actafk")        Long actafk,
			@RequestParam("vResponsable")  String vResponsable,
			@RequestParam("vDesacuerdo")   String vDesacuerdo,
			@RequestParam("entidadfk")       Long entidadfk,
			@RequestParam("dFecatencion")  String dFecatencion
			){
		
		
		Map<String, Object> response = new HashMap<>();
		Acuerdos acuerdo = new Acuerdos();
		try {
			Actas acta = new Actas();
			acta.setaCtaidpk(actafk);
			acta=actaService.buscarPorId(acta);
			if(acta==null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACTAS_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACTAS_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, acta);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			Entidades entidad = new Entidades();
			entidad.seteNtidadidpk(entidadfk);
			entidad = fijasDao.buscarPorEntidad(entidad);
			
			acuerdo.setActa(acta);
			acuerdo.setvResponsable(vResponsable);
			acuerdo.setEntidad(entidad);
			acuerdo.setvDesacuerdo(vDesacuerdo);
			acuerdo.setdFecatencion(FechasUtil.convertStringToDate(dFecatencion));
			acuerdo = actaService.registrarAcueros(acuerdo);
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, acuerdo);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Acuerdos>(acuerdo, HttpStatus.OK);
	}
	
	
	@GetMapping("/listarfirmantesporacta/{idsesion}")           //LISTAMOS LOS FIRMANTES O REGISTRAMOS
	List<Firmantes> listarFirmantesPorActa(@PathVariable Long idsesion){
		List<Firmantes>listarFirmantes = new ArrayList<Firmantes>();
		listarFirmantes = actaService.listarFirmentes(idsesion);
		return listarFirmantes;
	}
	
	
	@PutMapping("/actualizarfirmante")
	public ResponseEntity<?> actualizar(
			@RequestParam("fIrmanteidpk")     Long   fIrmanteidpk,
			@RequestParam("actas")            Long   actas,
			@RequestParam("vEntidad")         String vEntidad,
			@RequestParam("vTipodocumento")   String vTipodocumento,
			@RequestParam("vNumerodocumento") String vNumerodocumento,
			@RequestParam("vNombre")          String vNombre,
			@RequestParam("vTipo")            String vTipo,
			@RequestParam("cFlgasistio")      String cFlgasistio
	 ) {
		Firmantes generico = new Firmantes();
		Map<String, Object> response = new HashMap<>();
		try {
			 
			generico.setfIrmanteidpk(fIrmanteidpk);
			generico.setcFlgasistio(cFlgasistio);
			generico = actaService.actualizarFirmante(generico);
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Firmantes>(generico, HttpStatus.OK);
	}
	
	
	
	
	
	

	@GetMapping("/infoacta/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		logger.info("========== BUSCAR ACTA ID=============== ");
		Actas actas = new Actas();
		actas.setaCtaidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {

			actas = actaService.buscarPorId(actas);
			if (actas == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACTAS_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACTAS_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, actas);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, actas);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Actas>(actas, HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public List<Actas> buscarActas(@RequestBody Actas buscar) {
		logger.info("========== BUSCAR ACTAS=============== ");
		return actaService.buscar(buscar);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarActas(@RequestBody Actas acta) {
		logger.info("========== ACTUALIZAR BOLETIN=============== ");
		Map<String, Object> response = new HashMap<>();
		Actas generico = new Actas();
		try {
			generico = actaService.buscarPorId(acta);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACTAS_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACTAS_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, acta);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			acta.setvCodacta(generico.getvCodacta());
			acta.setnUsureg(generico.getnUsureg());
			acta.setdFecreg(generico.getdFecreg());
			generico = actaService.Actualizar(acta);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, acta);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {

		}
		return new ResponseEntity<Actas>(generico, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarActas(@PathVariable Long id) {
		logger.info("========== ELIMINAR BOLETIN=============== ");
		Actas generico = new Actas();
		generico.setaCtaidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {

			generico = actaService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACTAS_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACTAS_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			generico = actaService.Eliminar(generico);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Actas>(generico, HttpStatus.OK);

	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(
			@RequestParam("docacta") MultipartFile docacta,
			@RequestParam("sesionfk") Long sesionfk,
			@RequestParam("fecha_acta") String fecha_acta
	) {
		Actas generico = new Actas();
		Archivos archivo = new Archivos();

		Map<String, Object> response = new HashMap<>();
		try {
			archivo = archivoUtilitarioService.cargarArchivo(docacta, ConstantesUtil.ACTA_ALIAS_CORRELATIVO);

			if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) {
				Sesiones sesion = new Sesiones();
				sesion.setsEsionidpk(sesionfk);
				generico.setSesionfk(sesion);
				generico.setdFecacta(FechasUtil.convertStringToDate(fecha_acta));
				generico.setvNombrearchivo(archivo.getNombre());
				generico.setvArchivoextension(archivo.getExtension());
				generico.setvUbiarch(archivo.getUbicacion());
				generico = actaService.Registrar(generico);
			} else {
				response.put(ConstantesUtil.X_MENSAJE, "ARCHIVO NO ADJUNTO");
				response.put(ConstantesUtil.X_ERROR, "NO SE ENCONTRO EL ARCHIVO ADJUNTO");
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

			}

		} catch (DataAccessException e) {
			logger.info("===========  error =========================");
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Actas>(generico, HttpStatus.OK);
	}

	@GetMapping("/descargar/{id}")
	public void descargarArchivo(@PathVariable Long id, HttpServletResponse res) {
		Actas generico = new Actas();
		generico.setaCtaidpk(id);
		String ruta = "";
		try {
			generico = actaService.buscarPorId(generico);
			ruta = rutaRaiz + generico.obtenerRutaAbsoluta();
			res.setHeader("Content-Disposition", "attachment; filename=" + generico.getvNombrearchivo()+"."+generico.getvArchivoextension());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	
	@PostMapping("/buscartemasporsesion")
	public List<Actas> buscarTemasPorSesion(@RequestBody Actas actas){
		return actaService.buscarActasPorSesion(actas);
	}
	
	@PostMapping("/listarActasPorSesion")
	public List<Actas> listarActasPorSesion(@RequestBody Actas actas){
		return actaService.listarActasPorSesion(actas);
	}
	
	

}
