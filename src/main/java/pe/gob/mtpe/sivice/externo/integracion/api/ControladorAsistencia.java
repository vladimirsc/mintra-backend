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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciaConsejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Asistencias;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciasArchivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Entidades;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Invitados;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AsistenciaService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AsistenciasArchivoService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.InvitadoService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/asistencia" })
public class ControladorAsistencia {

	private static final Logger logger = LoggerFactory.getLogger(ControladorActas.class);

	@Autowired
	private AsistenciaService asistenciaService;

	@Autowired
	private FijasService fijasService;

	@Autowired
	private InvitadoService invitadoService;

	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;

	@Autowired
	private AsistenciasArchivoService asistenciasArchivoService;

	
	@Value("${rutaArchivo}")
	private String rutaRaiz;
	
	
	@GetMapping("/{idsesion}")
	public ResponseEntity<?> buscarPorIdAsistencias(@PathVariable Long idsesion) {
		// ONTENEMOS LOS DATOS DE LA SESSION
		Sesiones sesion = new Sesiones();
		Map<String, Object> response = new HashMap<>();
		try {
			sesion.setsEsionidpk(idsesion);
			sesion = asistenciaService.buscarSesion(sesion);
			if (sesion == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SESION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SESION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, sesion);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, sesion);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Sesiones>(sesion, HttpStatus.OK);
	}

	@GetMapping("/listarasistentes/{idsesion}")
	public List<AsistenciaConsejeros> buscarAsistencias(@PathVariable Long idsesion) {
		List<AsistenciaConsejeros> lista = new ArrayList<AsistenciaConsejeros>();

		try {
			lista = asistenciaService.listarConsejerosAsistencia(idsesion);
		} catch (Exception e) {

			lista = null;
		}
		return lista;
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarHoraAsistencia(
			@RequestParam(value = "idAsistencia") Long idAsistencia,
			@RequestParam(value = "asistio")      String cFlgasistio,
			@RequestParam(value = "horaEntrada")  String vHoentrada,
			@RequestParam(value = "horaSalida")   String vHosalida) {

		Map<String, Object> response = new HashMap<>();
		Asistencias generico = new Asistencias();
		try {
			generico.setaSistenciaidpk(idAsistencia);
			generico = asistenciaService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CALENDARIO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CALENDARIO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			generico.setcFlgasistio(cFlgasistio);
			generico.setvHoentrada(vHoentrada);
			generico.setvHosalida(vHosalida);
			generico = asistenciaService.Actualizar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Asistencias>(generico, HttpStatus.OK);
	}

	@GetMapping("/info/{idasistencia}")
	public ResponseEntity<?> infoAsistente(@PathVariable Long idasistencia) {

		Map<String, Object> response = new HashMap<>();
		AsistenciaConsejeros infoAsistencia = new AsistenciaConsejeros();
		Asistencias generico = new Asistencias();

		try {
			generico.setaSistenciaidpk(idasistencia);
			generico = asistenciaService.buscarPorId(generico);

			if (generico != null) {
				// AsistenciaConsejeros fila = new AsistenciaConsejeros();
				infoAsistencia.setIdAsistencia(generico.getaSistenciaidpk());
				infoAsistencia.setAsistio(generico.getcFlgasistio());

				infoAsistencia.setHoraEntrada(generico.getvHoentrada());
				infoAsistencia.setHoraSalida(generico.getvHosalida());

				if (generico.getConsejero() == null) {
					logger.info("=========== BUSCAMOS AL INVITADO =============");
					Invitados invitados = new Invitados();
					invitados.setiNvitadosidpk(generico.getiNvitadosfk());
					invitados = invitadoService.buscarPorId(invitados);
					infoAsistencia.setEntidad(invitados.getEntidad().getvDesnombre());
					infoAsistencia.setTipoDocumento(invitados.getTipodocumento().getvDesabreviado());
					infoAsistencia.setNumeroDocumento(invitados.getvNumerodocumento());
					infoAsistencia.setApellidosNombre(invitados.getvNombre() + "," + invitados.getvApellido_paterno()
							+ " " + invitados.getvApellido_materno());
				} else {
					infoAsistencia.setEntidad(generico.getConsejero().getvEntidad());
					infoAsistencia.setTipoDocumento(generico.getConsejero().getvTipdocumento());
					infoAsistencia.setNumeroDocumento(generico.getConsejero().getvNumdocumento());
					infoAsistencia.setApellidosNombre( generico.getConsejero().getvDesnombre() + "," + generico.getConsejero().getvDesappaterno()
									+ " " + generico.getConsejero().getvDesapmaterno());
				}

			} else {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CALENDARIO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CALENDARIO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, infoAsistencia);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AsistenciaConsejeros>(infoAsistencia, HttpStatus.OK);
	}

	@PostMapping("/registrarinvitado")
	public ResponseEntity<?> grabarInvitado(@RequestParam(value = "entidad") Long entidad,
			@RequestParam(value = "sEsionfk")          Long sEsionfk, 
			@RequestParam(value = "tipodocumento")     Long tipodocumento,
			@RequestParam(value = "vNumerodocumento")  String vNumerodocumento,
			@RequestParam(value = "vNombre")           String vNombre,
			@RequestParam(value = "vApellido_paterno") String vApellido_paterno,
			@RequestParam(value = "vApellido_materno") String vApellido_materno,
			@RequestParam(value = "vNumerocelular")    String vNumerocelular) {
		Invitados generico = new Invitados();
		Map<String, Object> response = new HashMap<>();
		try {

			Entidades entidades = new Entidades();
			entidades.seteNtidadidpk(entidad);
			entidades = fijasService.buscarPorEntidad(entidades);

			TipoDocumentos tipoDocumentos = new TipoDocumentos();
			tipoDocumentos.settPdocumentoidpk(tipodocumento);
			tipoDocumentos = fijasService.buscarPorCodigoTipoDocumento(tipoDocumentos);

			generico.setEntidad(entidades);
			generico.setsEsionfk(sEsionfk);
			generico.setTipodocumento(tipoDocumentos);
			generico.setvNumerodocumento(vNumerodocumento);
			generico.setvNombre(vNombre);
			generico.setvApellido_paterno(vApellido_paterno);
			generico.setvApellido_materno(vApellido_materno);
			generico.setvNumerocelular(vNumerocelular);
			generico = asistenciaService.RegistrarInvitados(generico);

			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CALENDARIO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CALENDARIO_ERROR_BUSCAR);
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

		return new ResponseEntity<Invitados>(generico, HttpStatus.OK);
	}

	@PostMapping("/cargardocasistencia")
	public ResponseEntity<?> cargarArchivoAsistencias(
			@RequestParam(value = "docasistencia") MultipartFile docasistencia,
			@RequestParam(value = "sesioncodigo") Long sesioncodigo) {

		AsistenciasArchivos asistenciasArchivos = new AsistenciasArchivos();
		Map<String, Object> response = new HashMap<>();
		try {

			if (docasistencia.isEmpty()) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CONSEJERO_MSG_DOCAPROBACION_VACIO);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CONSEJERO_ERROR_DOCAPROBACION_VACIO);
				response.put(ConstantesUtil.X_ENTIDAD, asistenciasArchivos);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			Archivos archivo = new Archivos();
			archivo = archivoUtilitarioService.cargarArchivo(docasistencia, ConstantesUtil.C_CONSEJERO_DOC_ASIGNACION);
			
			if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) {
				asistenciasArchivos.setnOmbrearchivo(archivo.getNombre());
				asistenciasArchivos.seteXtensionarchivo(archivo.getExtension());
				asistenciasArchivos.setuBicacionarchivo(archivo.getUbicacion());
				asistenciasArchivos.setsEsionfk(sesioncodigo);
				asistenciasArchivos.setvFlgeliminado("0");
			} else {
				response.put(ConstantesUtil.X_MENSAJE, "ARCHIVO NO ADJUNTO");
				response.put(ConstantesUtil.X_ERROR, "NO SE ENCONTRO EL ARCHIVO ADJUNTO");
				response.put(ConstantesUtil.X_ENTIDAD, archivo);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

			}

			asistenciasArchivos = asistenciasArchivoService.cargarArchivo(asistenciasArchivos);


		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, asistenciasArchivos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AsistenciasArchivos>(asistenciasArchivos, HttpStatus.OK);
 
	}
	
	
	
	
	@GetMapping("/descargar/{idsesion}")
	public void descargarArchivo(@PathVariable Long idsesion, HttpServletResponse res) {
		AsistenciasArchivos asistenciasArchivos = new AsistenciasArchivos(); 
		String ruta = "";
		try {
			asistenciasArchivos = asistenciasArchivoService.descargarArchivo(idsesion);
			ruta = rutaRaiz + asistenciasArchivos.obtenerRutaAbsoluta();
			res.setHeader("Content-Disposition", "attachment; filename=" + asistenciasArchivos.getnOmbrearchivo()+"."+asistenciasArchivos.geteXtensionarchivo());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	

}
