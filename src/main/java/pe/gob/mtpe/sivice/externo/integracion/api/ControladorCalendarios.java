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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Calendarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.CalendarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/calendario" })
public class ControladorCalendarios {

	private static final Logger logger = LoggerFactory.getLogger(ControladorCalendarios.class);

	@Autowired
	private CalendarioService calendarioService;
	
	@Autowired
	private  FijasService fijasService;

	@GetMapping("/")
	public List<Calendarios> listar(
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		
		Regiones region = new Regiones();
		region.setrEgionidpk(idRegion);
		
		Calendarios calendario = new Calendarios();
		calendario.setRegion(region);
		return calendarioService.listar(calendario);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		Calendarios generico = new Calendarios();
		generico.setcAlendarioidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = calendarioService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CALENDARIO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CALENDARIO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			generico = calendarioService.buscarPorId(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Calendarios>(generico,HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public List<Calendarios> buscar(
			@RequestBody Calendarios buscar,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		
		Regiones region = new Regiones();
		region.setrEgionidpk(idRegion);
		buscar.setRegion(region);
		return calendarioService.buscar(buscar);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(
			@RequestBody Calendarios calendarios,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Map<String, Object> response = new HashMap<>();
		try {
			
			//*****  DATOS DE USUARIO DE INICIO DE SESION **********
			Long codigoconsejo=fijasService.BuscarConsejoPorNombre(nombreRol); 

			Consejos consejo = new Consejos();
			consejo.setcOnsejoidpk(codigoconsejo);
			
			Regiones region = new Regiones();
			region.setrEgionidpk(idRegion);
			//*******************************************************
			
			calendarios.setRegion(region);
			calendarios.setConsejo(consejo);
			calendarios.setnUsureg(idUsuario);
			
			calendarios = calendarioService.Registrar(calendarios);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, calendarios);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Calendarios>(calendarios,HttpStatus.CREATED);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizar(
			@RequestBody Calendarios calendarios,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		Calendarios generico = null;
		Map<String, Object> response = new HashMap<>();
		try {
			generico = calendarioService.buscarPorId(calendarios);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CALENDARIO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CALENDARIO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, calendarios);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
 
			calendarios.setnUsureg(generico.getnUsureg());
			calendarios.setdFecreg(generico.getdFecreg());
			calendarios.setRegion(generico.getRegion());
			calendarios.setConsejo(generico.getConsejo());
			calendarios.setnUsumodifica(idUsuario);
			generico = calendarioService.Actualizar(calendarios);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Calendarios>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("==========  insertarConsejeros  ===========");
		Calendarios generico = new Calendarios();
		generico.setcAlendarioidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = calendarioService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACTAS_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACTAS_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
 
			generico.setnUsuelimina(idUsuario);
			generico = calendarioService.Eliminar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Calendarios>(generico,HttpStatus.OK);
	}
	
	
	

}
