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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Asistencias;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AsistenciaService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@RestController
@RequestMapping({ "/api/asistencia" })
public class ControladorAsistencia {

	private static final Logger logger = LoggerFactory.getLogger(ControladorActas.class);

	@Autowired
	private AsistenciaService asistenciaService;

	@GetMapping("/")
	public List<Asistencias> listar() {
		return asistenciaService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdAsistencias(@PathVariable Long id) {
		Asistencias generico = new Asistencias();
		generico.setaSistenciaidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = asistenciaService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ASISTENCIA_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ASISTENCIA_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Asistencias>(generico,HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public List<Asistencias> buscar(@RequestBody Asistencias buscar) {
		return asistenciaService.buscar(buscar);
	}

	@PostMapping("/asistencia")
	public ResponseEntity<?> registrar(@RequestBody Asistencias generico) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Map<String, Object> response = new HashMap<>();
		try {
			generico = asistenciaService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Asistencias>(generico,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Asistencias asistencias) {
		Asistencias generico = new Asistencias();
		Map<String, Object> response = new HashMap<>();
		try {
			generico = asistenciaService.buscarPorId(asistencias);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACUERDO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACUERDO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, asistencias);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			asistencias.setnUsureg(generico.getnUsureg());
			asistencias.setdFecreg(generico.getdFecreg());
			generico = asistenciaService.Actualizar(asistencias);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, asistencias);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		
		return new ResponseEntity<Asistencias>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		logger.info("==========  insertarConsejeros  ===========");
		Asistencias generico = new  Asistencias();
		generico.setaSistenciaidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = asistenciaService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACUERDO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACUERDO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			generico = asistenciaService.Eliminar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Asistencias>(generico,HttpStatus.OK);
	}

}
