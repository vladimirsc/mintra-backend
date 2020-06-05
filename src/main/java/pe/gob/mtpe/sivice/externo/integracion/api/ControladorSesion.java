package pe.gob.mtpe.sivice.externo.integracion.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesion;
import pe.gob.mtpe.sivice.externo.core.negocio.service.SesionService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@RestController
@RequestMapping({ "/api/sesion" })
public class ControladorSesion {

	private static final Logger logger = LoggerFactory.getLogger(ControladorSesion.class);

	@Autowired
	private SesionService sesionService;

	@GetMapping("/")
	public List<Sesion> listarSesion() {
		logger.info("============  BUSCAR listarSesion =================");
		return sesionService.listar();
	}

	@PostMapping("/buscar")
	public List<Sesion> buscarSesion(@RequestBody Sesion buscar) {
		return sesionService.buscar(buscar);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdPlanTrabajo(@PathVariable Long id) {
		Sesion generico  = new Sesion();
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

		return new ResponseEntity<Sesion>(generico,HttpStatus.OK);
	}

	@PostMapping("/sesion")
	public ResponseEntity<?> registrarSesion(@RequestBody Sesion generico) {
		logger.info("============  BUSCAR PROFESION =================");
		Map<String, Object> response = new HashMap<>();
		try {
			generico = sesionService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Sesion>(generico,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarSesion(@RequestBody Sesion sesion) {
		logger.info("============  BUSCAR PROFESION =================");
		Sesion generico = new Sesion();
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = sesionService.buscarPorId(sesion);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SESION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SESION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, sesion);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			 
			sesion.setnUsureg(generico.getnUsureg());
			sesion.setdFecreg(generico.getdFecreg());
			generico = sesionService.Actualizar(sesion);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, sesion);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
 
		return new ResponseEntity<Sesion>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarSesion(@PathVariable Long id) {
		logger.info("============  BUSCAR PROFESION =================");
		Sesion generico =  new Sesion();
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
 
		return new ResponseEntity<Sesion>(generico,HttpStatus.OK);

	}

}
