package pe.gob.mtpe.sivice.externo.integracion.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Personas; 
import pe.gob.mtpe.sivice.externo.core.negocio.service.PersonaService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/personas" })
public class ControladorPersonas {

	@Autowired
	private PersonaService personaService;

	@PostMapping("/grabar")
	public ResponseEntity<?> grabarPersona(@RequestBody Personas persona) {
		Personas personabuscar = new Personas();
		Map<String, Object> response = new HashMap<>();
		try {

			personabuscar = personaService.buscarTipoDocNumero(persona);
			if (personabuscar != null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.COMISION_CONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.COMISION_CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, persona);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			personabuscar = personaService.Registrar(persona);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, persona);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Personas>(personabuscar, HttpStatus.OK);
	}

}
