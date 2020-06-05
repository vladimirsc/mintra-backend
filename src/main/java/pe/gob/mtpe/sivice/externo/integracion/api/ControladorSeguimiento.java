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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Seguimientos;
import pe.gob.mtpe.sivice.externo.core.negocio.service.SeguimientoService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@RestController
@RequestMapping({ "api/seguimientos" })
public class ControladorSeguimiento {

	private static final Logger logger = LoggerFactory.getLogger(ControladorSeguimiento.class);

	@Autowired
	private SeguimientoService seguimientoService;

	@GetMapping("/")
	public List<Seguimientos> listar() {
		return seguimientoService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarSeguimientos(@PathVariable Long id) {
		Seguimientos generico = new Seguimientos();
		generico.setsEgimientoidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = seguimientoService.buscarPorId(generico);
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

		return new ResponseEntity<Seguimientos>(generico,HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public List<Seguimientos> buscar(@RequestBody Seguimientos buscar) {
		return seguimientoService.buscar(buscar);
	}

	

	@PostMapping("/seguimientos")
	public ResponseEntity<?> registrar(@RequestBody Seguimientos generico) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Map<String, Object> response = new HashMap<>();
		try {
			generico = seguimientoService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Seguimientos>(generico,HttpStatus.CREATED);
	}

	@PutMapping("/seguimientos")
	public ResponseEntity<?> actualizar(@RequestBody Seguimientos seguimientos) {
		Seguimientos generico = null;
		Map<String, Object> response = new HashMap<>();
		try {
			generico = seguimientoService.buscarPorId(seguimientos);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SEGUIMIENTO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SEGUIMIENTO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, seguimientos);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
			}
			seguimientos.setnUsureg(generico.getnUsureg());
			seguimientos.setdFecreg(generico.getdFecreg());
			generico = seguimientoService.Actualizar(seguimientos);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, seguimientos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		
		return new ResponseEntity<Seguimientos>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		logger.info("==========  insertarConsejeros  ===========");
		Seguimientos generico = new Seguimientos();
		generico.setsEgimientoidpk(id);
		
		Map<String, Object> response = new HashMap<>();
		try {
			generico = seguimientoService.buscarPorId(generico);
			
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SEGUIMIENTO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SEGUIMIENTO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			generico = seguimientoService.Eliminar(generico);
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Seguimientos>(generico,HttpStatus.OK);

	}

}
