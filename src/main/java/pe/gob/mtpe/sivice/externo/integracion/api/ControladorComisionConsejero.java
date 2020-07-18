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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.ComiConsej;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Tipoconsejero;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ComisionConsejeroService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/comisiconsej" })
public class ControladorComisionConsejero {

	private static final Logger logger = LoggerFactory.getLogger(ControladorComisionConsejero.class);
	
	@Autowired
	private ComisionConsejeroService ComisionConsejeroService;
	
	@Autowired
	private FijasService fijasService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;

	/*
	@GetMapping("/")
	public List<ComiConsej> listar() {
		return ComisionConsejeroService.listar();
	}
	
	@PostMapping("/comisiconsej")
	public ResponseEntity<?> registrar(@RequestBody ComiConsej generico) {
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		Map<String, Object> response = new HashMap<>();
		try {
			generico = ComisionConsejeroService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ComiConsej>(generico,HttpStatus.CREATED);
	}
	
	@PostMapping("/buscar")
	public List<ComiConsej> buscar(@RequestBody ComiConsej buscar) {
		return null;
	}

	
	*/

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		ComiConsej generico = new ComiConsej();
		generico.setcOmiconsidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = ComisionConsejeroService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.COMISION_CONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.COMISION_CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ComiConsej>(generico,HttpStatus.OK);
	}

	
	
	@GetMapping("/consejeroscomision/{idcomision}")
	public List<ComiConsej> listarConsejerosComision(@PathVariable Long idcomision ){
		
 
		List<ComiConsej> listaconsejero = new ArrayList<ComiConsej>();
		listaconsejero = ComisionConsejeroService.buscar(idcomision);
		return listaconsejero;
	}
	

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizar(
			@RequestParam(value="codcomiconsejero" ) Long codcomiconsejero,
			@RequestParam(value="vtipoconsejero",required = true )   Long vtipoconsejero,
			@RequestParam(value="vfechainicio" )     String vfechainicio,
			@RequestParam(value="vfechafin" )        String vfechafin,
			@RequestParam(value="vnumerodocumento" ) String vnumerodocumento,
			@RequestParam(value="vdocumento" )       MultipartFile  vdocumento
			) {
		ComiConsej generico = new ComiConsej(); 
		Archivos archivoDocumento = new Archivos();
		Map<String, Object> response = new HashMap<>();
		try {
 
			  generico.setcOmiconsidpk(codcomiconsejero);
 
			  generico = ComisionConsejeroService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.COMISION_CONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.COMISION_CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			
			//*****  DATOS DE USUARIO DE INICIO DE SESION **********
			Long codigoconsejo=fijasService.BuscarConsejoPorNombre(ConstantesUtil.c_rolusuario); // CONSSAT
			//*******************************************************
			
			if(vdocumento!=null && vdocumento.getSize()>0) {
				archivoDocumento = archivoUtilitarioService.cargarArchivo(vdocumento, ConstantesUtil.C_DOCCOMISIONCONSEJERO);
				generico.setvNombrearchivo(archivoDocumento.getNombre());
			    generico.setvUbicacion(archivoDocumento.getUbicacion());
			    generico.setvExtension(archivoDocumento.getExtension());
			}
			
			
			Tipoconsejero tipoconsejero= new Tipoconsejero();
            tipoconsejero.settPconsejeroidpk(vtipoconsejero);
            
            generico.setTipoconsejero(tipoconsejero);
            generico.setTipoconsejero(tipoconsejero);
            generico.setdFecinicio(  (vfechainicio!=null)? FechasUtil.convertStringToDate(vfechainicio) : null  );
            generico.setdFecfin(     (vfechafin!=null)? FechasUtil.convertStringToDate(vfechafin) : null );
            generico.setvNumdocumento(vnumerodocumento);
            generico.setnUsureg(codigoconsejo); 
            generico = ComisionConsejeroService.Actualizar(generico);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		
		return new ResponseEntity<ComiConsej>(generico,HttpStatus.OK);
	}

	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar( @PathVariable Long id) {
		logger.info("==========  ELIMINAR CONSEJERO  ===========");
		ComiConsej generico = new ComiConsej();
		generico.setcOmiconsidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = ComisionConsejeroService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.COMISION_CONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.COMISION_CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} 
			generico = ComisionConsejeroService.Eliminar(generico);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ComiConsej>(generico,HttpStatus.OK);

	}
	
	
	@GetMapping("/descargar/{id}")
	public void descargarArchivo(@PathVariable Long id, HttpServletResponse res) {
		ComiConsej generico = new ComiConsej();
		generico.setcOmiconsidpk(id);
		String ruta = "";
		try {
			generico = ComisionConsejeroService.buscarPorId(generico);
			ruta = rutaRaiz + generico.obtenerRutaAbsoluta();
			res.setHeader("Content-Disposition", "attachment; filename=" + generico.getvNombrearchivo()+"."+generico.getvExtension());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	
	

}
