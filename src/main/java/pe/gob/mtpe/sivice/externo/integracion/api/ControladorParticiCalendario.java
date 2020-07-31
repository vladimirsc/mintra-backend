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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Particalen;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ParticipanteCalendarioService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;


@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping({ "api/particicalendario" })
public class ControladorParticiCalendario {

	private static final Logger logger = LoggerFactory.getLogger(ControladorParticiCalendario.class);

	@Autowired
	private ParticipanteCalendarioService participanteCalendarioService;


	@ApiOperation(value = "Registra un participante al calendario") 
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(
			@RequestBody Particalen generico,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Map<String, Object> response = new HashMap<>();
		try {
			
			
			//*****  DATOS DE USUARIO DE INICIO DE SESION **********
			Long usuariologin = Long.parseLong("21");
			//*******************************************************
 
			generico.setnUsuregistra(usuariologin);
			generico = participanteCalendarioService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Particalen>(generico,HttpStatus.CREATED);
	}
	
	
	
	@ApiOperation(value = "Lista los participantes por calendario")
	@GetMapping("/listaparticipantes/{calendariofk}")
	public List<Particalen> listarParticpantesPorCalendario(
			@PathVariable("calendariofk") Long  codigocalendario,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol){
		return participanteCalendarioService.listarParticipantesPorCalendario(codigocalendario);
	}
	
	
	@ApiOperation(value = "Elimina un participante")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("==========  insertarConsejeros  ===========");
		Particalen generico =new Particalen();
		generico.setpArtcalendidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = participanteCalendarioService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.PARTICICALENDA_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.PARTICICALENDA_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			//*****  DATOS DE USUARIO DE INICIO DE SESION **********
			Long usuariologin = Long.parseLong("21");
			//*******************************************************
			
			generico.setnUsuarioelimina(usuariologin);
			generico = participanteCalendarioService.Eliminar(generico);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Particalen>(generico,HttpStatus.OK);

	}
	

	/*
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizar(@RequestBody Particalen particalen) {
		Particalen generico = participanteCalendarioService.Actualizar(particalen);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = participanteCalendarioService.buscarPorId(particalen);
			if (generico == participanteCalendarioService) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.PARTICICALENDA_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.PARTICICALENDA_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, particalen);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
 
			generico = participanteCalendarioService.Actualizar(particalen);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, particalen);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} 

		return new ResponseEntity<Particalen>(generico,HttpStatus.OK);
	}
	 
	
 */

	/*
	@GetMapping("/")
	public List<Particalen> listar() {
		return participanteCalendarioService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Particalen generico = new Particalen();
		generico.setpArtcalendidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = participanteCalendarioService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.PARTICICALENDA_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.PARTICICALENDA_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Particalen>(generico,HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public List<Particalen> buscar(@RequestBody Particalen buscar) {
		return participanteCalendarioService.buscar(buscar);
	}
*/	
	

}
