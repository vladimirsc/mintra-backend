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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.BandejaActas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoSesiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AcuerdoService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/acuerdos" })
public class ControladorAcuerdos {

	private static final Logger logger = LoggerFactory.getLogger(ControladorAcuerdos.class);

	@Autowired
	private AcuerdoService acuerdoService;

	@GetMapping("/")
	public List<Acuerdos> listar() {
		return acuerdoService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdAcuerdo(@PathVariable Long id) {
		Acuerdos generico = new Acuerdos();
		generico.setaCuerdoidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {

			generico = acuerdoService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACUERDO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACUERDO_ERROR_BUSCAR);
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

		return new ResponseEntity<Acuerdos>(generico, HttpStatus.OK);

	}

	@PostMapping("/buscaracuerdosporsesion")
	public List<BandejaActas> buscar(@RequestParam("codigosesion") String codigosesion,
			@RequestParam("tiposesion") Long tiposesion, @RequestParam("fechainicio") String fechainicio,
			@RequestParam("fechafin") String fechafin) {

		Sesiones sesionbuscar = new Sesiones();
		List<BandejaActas> listarBandejaActas = new ArrayList<BandejaActas>();
		try {
			if (tiposesion != null) {
				TipoSesiones tiposesiones = new TipoSesiones();
				tiposesiones.settIposesionidpk(tiposesion);
			}

			sesionbuscar.setvCodsesion(codigosesion);
			sesionbuscar.setdFechaInicio(FechasUtil.convertStringToDate(fechainicio));
			sesionbuscar.setdFechaFin(FechasUtil.convertStringToDate(fechafin));

			listarBandejaActas = acuerdoService.buscarAcuerdosPorSesion(sesionbuscar);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listarBandejaActas;

	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Acuerdos acuerdos) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Map<String, Object> response = new HashMap<>();
		try {
			acuerdos = acuerdoService.Registrar(acuerdos);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, acuerdos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Acuerdos>(acuerdos, HttpStatus.CREATED);
	}

	@PutMapping("/acuerdos")
	public ResponseEntity<?> actualizar(@RequestBody Acuerdos acuerdos) {
		Acuerdos generico = null;
		Map<String, Object> response = new HashMap<>();
		try {
			generico = acuerdoService.buscarPorId(acuerdos);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACUERDO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACUERDO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, acuerdos);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			acuerdos.setnUsureg(generico.getnUsureg());
			acuerdos.setdFecreg(generico.getdFecreg());
			generico = acuerdoService.Actualizar(acuerdos);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, acuerdos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Acuerdos>(generico, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		logger.info("========== INGRESO A   eliminar=============== ");
		Acuerdos generico = new Acuerdos();
		generico.setaCuerdoidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {

			generico = acuerdoService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.ACUERDO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.ACUERDO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			generico.setnUsureg(generico.getnUsureg());
			generico.setdFecreg(generico.getdFecreg());
			generico = acuerdoService.Eliminar(generico);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Acuerdos>(generico, HttpStatus.OK);
	}

	@GetMapping("/acuerdosporacta/{idacta}")
	public List<Acuerdos> listarAcuerdosPorActas(@PathVariable Long idacta) {
		Actas acta = new Actas();
		acta.setaCtaidpk(idacta);
		return acuerdoService.listarAcuerdosPorActa(acta);
	}
	
	
	 

}
