package pe.gob.mtpe.sivice.externo.integracion.api;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.PlanTrabajo;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.PlanTrabajoService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/plantrabajo" })
public class ControladorPlanTrabajo {

	private static final Logger logger = LoggerFactory.getLogger(ControladorPlanTrabajo.class);

	@Autowired
	private PlanTrabajoService planTrabajoService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;
	
	@Autowired
	private  FijasService fijasService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;

	@GetMapping("/")
	public List<PlanTrabajo> listarPlanTrabajo() {
		logger.info("============  BUSCAR listarPlanTrabajo =================");
		return planTrabajoService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdPlanTrabajo(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		PlanTrabajo generico  = new PlanTrabajo();
		generico.setpLantrabidpk(id); 
		Map<String, Object> response = new HashMap<>();
		try {
			generico = planTrabajoService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.PLANTRABAJO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.PLANTRABAJO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PlanTrabajo>(generico,HttpStatus.OK);
	}
	

	@PostMapping("/buscar")
	public List<PlanTrabajo> buscarPlanTrabajo(
			@RequestBody PlanTrabajo buscar,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		return planTrabajoService.buscar(buscar);
	}
	
	
 	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarPlanTrabajo(
			@RequestParam(value="docaprobacion",required = false) MultipartFile docaprobacion , @RequestParam(value="docplantrabajo" ,required = false) MultipartFile docplantrabajo,
			@RequestParam(value="comisionfk"   ) Long          comisionfk,    @RequestParam(value="dFecaprobacion") String  dFecaprobacion,
			@RequestParam(value="dFecinicio"   ) String        dFecinicio,    @RequestParam(value="dFecfin")        String  dFecfin,
			@RequestParam(value="vNumdocapr"   ) String        vNumdocapr,@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) throws IOException{
		
		PlanTrabajo generico = new PlanTrabajo();
		Archivos archivoDocAprobacion = new Archivos();
		Archivos archivoPlanTrabajo = new Archivos();
		
		Map<String, Object> response = new HashMap<>();
		try {
			
			 if(docplantrabajo.isEmpty() || (docaprobacion.isEmpty())  ||  (docaprobacion.isEmpty() && docplantrabajo.isEmpty())) {
				 response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.PLANTRABAJO_MSG_DOC_ADJUNTOS);
					response.put(ConstantesUtil.X_ERROR, ConstantesUtil.PLANTRABAJO_ERROR_DOC_ADJUNTOS);
					response.put(ConstantesUtil.X_ENTIDAD, generico);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			 }else {
				 logger.info("============ pland de trabajo =============");
				 
				 archivoDocAprobacion = archivoUtilitarioService.cargarArchivo(docaprobacion, ConstantesUtil.C_DOCAPROBACION_PLAN_TRABAJO_SIGLAS);
				 archivoPlanTrabajo   = archivoUtilitarioService.cargarArchivo(docplantrabajo, ConstantesUtil.C_DOC_PLAN_TRABAJO_SIGLAS);
				 
				 
				//*****  DATOS DE USUARIO DE INICIO DE SESION **********
				 Long codigoconsejo=fijasService.BuscarConsejoPorNombre(ConstantesUtil.c_rolusuario); // CONSSAT
				  
				 Consejos consejo = new Consejos();
				 consejo.setcOnsejoidpk(codigoconsejo);
				 				
				 Regiones region = new Regiones();
				 region.setrEgionidpk(ConstantesUtil.c_codigoregion);
				 //*******************************************************
				 
				 if ((archivoDocAprobacion.isVerificarCarga() == true && archivoDocAprobacion.isVerificarCarga() == true)
						  && (archivoPlanTrabajo.isVerificarCarga() == true && archivoPlanTrabajo.isVerificarCarga() == true) ) {
					 logger.info("=================== cargaron plandes de trabajo =================");
					 generico.setcOmisionfk(comisionfk);
					 generico.setdFecaprobacion(FechasUtil.convertStringToDate(dFecaprobacion));
					 generico.setdFecinicio(FechasUtil.convertStringToDate(dFecinicio));
					 generico.setdFecfin(FechasUtil.convertStringToDate(dFecfin));
					 generico.setvNumdocapr(vNumdocapr);
					 
					 generico.setvNomarchdocaprob(archivoDocAprobacion.getNombre());
					 generico.setvUbidocaprobacion(archivoDocAprobacion.getUbicacion());
					 generico.setvExtarchdocaprob(archivoDocAprobacion.getExtension());
					 
					 generico.setvNomarchplan(archivoPlanTrabajo.getNombre());
					 generico.setvUbiarchplan(archivoPlanTrabajo.getUbicacion());
					 generico.setvExtarchplan(archivoPlanTrabajo.getExtension());
					 
					 generico.setRegion(region);
					 generico.setConsejo(consejo);
					 generico.setnUsureg(ConstantesUtil.c_usuariologin);

					 generico =planTrabajoService.Registrar(generico);
				 }
			 }
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PlanTrabajo>(generico,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPlanTrabajo(
			@RequestParam(value="docaprobacion",required = false) MultipartFile docaprobacion, @RequestParam(value="docplantrabajo",required = false) MultipartFile docplantrabajo,
			@RequestParam(value="comisionfk"   ) Long          comisionfk,    @RequestParam(value="dFecaprobacion") String  dFecaprobacion,
			@RequestParam(value="dFecinicio"   ) String        dFecinicio,    @RequestParam(value="dFecfin")        String  dFecfin,
			@RequestParam(value="vNumdocapr"   ) String        vNumdocapr,    @RequestParam(value="pLantrabidpk"   ) Long  pLantrabidpk,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) throws IOException {
		logger.info("============  BUSCAR PROFESION =================");
		PlanTrabajo generico = new PlanTrabajo();
		Archivos archivoAprobacion = new Archivos();
		Archivos archivoPlanTrabajo = new Archivos();
    	Map<String,Object> response = new HashMap<>();
    	try {
    		generico.setpLantrabidpk(pLantrabidpk);
    		generico = planTrabajoService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.PLANTRABAJO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.PLANTRABAJO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			
			if(docaprobacion!=null && docaprobacion.getSize()>0) {
				archivoAprobacion = archivoUtilitarioService.cargarArchivo(docaprobacion, ConstantesUtil.C_CONSEJERO_DOC_ASIGNACION); 
				generico.setvNomarchdocaprob(archivoAprobacion.getNombre());
				generico.setvUbidocaprobacion(archivoAprobacion.getUbicacion());
				generico.setvExtarchdocaprob(archivoAprobacion.getExtension());
			} 
			
			if(docplantrabajo!=null && docplantrabajo.getSize()>0) {
				archivoPlanTrabajo = archivoUtilitarioService.cargarArchivo(docplantrabajo, ConstantesUtil.C_CONSEJERO_DOC_ASIGNACION); 
				generico.setvNomarchplan(archivoPlanTrabajo.getNombre());
				generico.setvUbiarchplan(archivoPlanTrabajo.getUbicacion());
				generico.setvExtarchplan(archivoPlanTrabajo.getExtension());
			} 
			
			generico.setdFecaprobacion(FechasUtil.convertStringToDate(dFecaprobacion));
			generico.setdFecinicio(FechasUtil.convertStringToDate(dFecinicio));
			generico.setdFecfin(FechasUtil.convertStringToDate(dFecfin));
			generico.setvNumdocapr(vNumdocapr);
			generico.setnUsumodifica(ConstantesUtil.c_usuariologin);
			generico =planTrabajoService.Actualizar(generico);
			 
    		
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	 
    	return new ResponseEntity<PlanTrabajo>(generico,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPlanTrabajo(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol) {
		logger.info("============  BUSCAR PROFESION =================");
		PlanTrabajo generico = new PlanTrabajo();
		generico.setpLantrabidpk(id);
    	Map<String,Object> response = new HashMap<>();
    	try {
    		

			generico = planTrabajoService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.PLANTRABAJO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.PLANTRABAJO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
            generico.setnUsuelimina(ConstantesUtil.c_usuariologin);
			generico =planTrabajoService.Eliminar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
     
    	return new ResponseEntity<PlanTrabajo>(generico,HttpStatus.OK);

	}
	
	
	
	@GetMapping("/descargaraprobacion/{id}")
	public void descargarArchivoAprobacion(
			@PathVariable Long id, 
			HttpServletResponse res) {
		PlanTrabajo generico = new PlanTrabajo();
		generico.setpLantrabidpk(id);
		String ruta = "";
		try {
			generico = planTrabajoService.buscarPorId(generico);
			ruta = rutaRaiz + generico.obtenerRutaAbsolutaAprobacion();
			res.setHeader("Content-Disposition", "attachment; filename=" + generico.getvNomarchdocaprob()+"."+generico.getvExtarchdocaprob());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	
	@GetMapping("/descargarplan/{id}")
	public void descargarArchivoPlan(
			@PathVariable Long id,
			HttpServletResponse res) {
		PlanTrabajo generico = new PlanTrabajo();
		generico.setpLantrabidpk(id);
		String ruta = "";
		try {
			generico = planTrabajoService.buscarPorId(generico);
			ruta = rutaRaiz + generico.obtenerRutaAbsolutaPlanTrabajo();
			res.setHeader("Content-Disposition", "attachment; filename=" + generico.getvNomarchplan()+"."+generico.getvExtarchplan());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	
	
	
	

}
