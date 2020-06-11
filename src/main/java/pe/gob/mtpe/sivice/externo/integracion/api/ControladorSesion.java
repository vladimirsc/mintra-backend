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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoSesiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.SesionService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/sesion" })
public class ControladorSesion {

	private static final Logger logger = LoggerFactory.getLogger(ControladorSesion.class);

	@Autowired
	private SesionService sesionService;

	@GetMapping("/")
	public List<Sesiones> listarSesion() {
		logger.info("============  BUSCAR listarSesion =================");
		//falta adicionar el consejo como parametro eso de la session se obtiene
		return sesionService.listar();
	}

	@PostMapping("/buscar")
	public List<Sesiones> buscarSesion(
			@RequestParam("codigosesion")  String codigosesion,
			@RequestParam("tiposesion")    Long tiposesion,
			@RequestParam("fechainicio")   String fechainicio,
			@RequestParam("fechafin")      String fechafin
			) {
		List<Sesiones> generico = new  ArrayList<Sesiones>();
		Sesiones sesionbuscar = new Sesiones();
		
		 try {
			 if(tiposesion!=null) {
				 TipoSesiones tiposesiones = new TipoSesiones();
				 tiposesiones.settIposesionidpk(tiposesion); 
			  }

			 sesionbuscar.setvCodsesion(codigosesion); 
			 sesionbuscar.setdFechaInicio(FechasUtil.convertStringToDate(fechainicio));
			 sesionbuscar.setdFechaFin(FechasUtil.convertStringToDate(fechafin));
			 
			 generico = sesionService.buscar(sesionbuscar);
		} catch (Exception e) {
			generico = null;
		}
		return generico;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdPlanTrabajo(@PathVariable Long id) {
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

	@PostMapping("/registrar")
	public ResponseEntity<?> registrarSesion(
			 @RequestParam(value="consejofk")   Long consejofk,
			 @RequestParam(value="cOmisionfk")  Long cOmisionfk,
			 @RequestParam(value="tiposesion")  Long tiposesion,
			 @RequestParam(value="dFecreacion") String dFecreacion,
			 @RequestParam(value="dHorinicio")  String dHorinicio,
			 @RequestParam(value="dHorfin")     String dHorfin
			) {
		logger.info("============  BUSCAR PROFESION =================");
		// OBTENER 
		Sesiones generico = new Sesiones();
		Map<String, Object> response = new HashMap<>();
		try {
			
			 
			 generico.setdFecreacion(FechasUtil.convertStringToDate(dFecreacion));
			 generico.setdHorinicio(dHorinicio);
			 generico.setdHorfin(dHorfin);
			 generico = sesionService.Registrar(consejofk,cOmisionfk,tiposesion,generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Sesiones>(generico,HttpStatus.CREATED);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarSesion( 
			 @RequestParam(value="sEsionidpk")  Long sEsionidpk,
			 @RequestParam(value="cOmisionfk")  Long cOmisionfk,
			 @RequestParam(value="tiposesion")  Long tiposesion,
			 @RequestParam(value="dFecreacion") String dFecreacion,
			 @RequestParam(value="dHorinicio")  String dHorinicio,
			 @RequestParam(value="dHorfin")     String dHorfin,
			 @RequestParam(value="codusuario")  Long codusuario
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
			 generico.setnUsumodifica(codusuario);
			generico = sesionService.Actualizar(cOmisionfk,tiposesion,generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
 
		return new ResponseEntity<Sesiones>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarSesion(@PathVariable Long id) {
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
			generico = sesionService.Eliminar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
 
		return new ResponseEntity<Sesiones>(generico,HttpStatus.OK);

	}

}
