package pe.gob.mtpe.sivice.externo.integracion.api;

import java.nio.file.Files;
import java.nio.file.Paths;
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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ActaService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/actas" })
public class ControladorActas {

	private static final Logger logger = LoggerFactory.getLogger(ControladorActas.class);

	@Autowired
	private ActaService actaService;

	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;

	@GetMapping("/")
	public List<Actas> listarActas() {
		logger.info("==========LISTAR ACTAS=============== ");
		return actaService.listar();
	}

	@GetMapping("/{id}")
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
	public ResponseEntity<?> registrar(@RequestParam("file") MultipartFile file,
			@RequestParam("sesionfk") Long sesionfk, @RequestParam("fecha_acta") String fecha_acta) {
		Actas generico = new Actas();
		Archivos archivo = new Archivos();

		Map<String, Object> response = new HashMap<>();
		try {
			archivo = archivoUtilitarioService.cargarArchivo(file, ConstantesUtil.ACTA_ALIAS_CORRELATIVO);

			if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) {
				generico.setsEsionfk(sesionfk);
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

}
