package pe.gob.mtpe.sivice.externo.integracion.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.InfAnuales;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.InformAnualService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/informes" })
public class ControladorInformeAnuales {

	private static final Logger logger = LoggerFactory.getLogger(ControladorInformeAnuales.class);

	@Autowired
	private InformAnualService informAnualService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;

	@GetMapping("/")
	public List<InfAnuales> listar() {
		return informAnualService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		InfAnuales generico = new InfAnuales();
		generico.setiNformeidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = informAnualService.buscarPorId(generico); 
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.INFORMES_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.INFORMES_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
 
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<InfAnuales>(generico,HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public List<InfAnuales> buscar(
		@RequestParam(value="comision")     String  comision,
		@RequestParam(value="vCodinforme")  String  vCodinforme,
		@RequestParam(value="vSesion")      String  vSesion,
		@RequestParam(value="vNumdocap")    String  vNumdocap,
		@RequestParam(value="dFecdesde")    String  dFecdesde,
		@RequestParam(value="dFhasta")      String  dFhasta

			) {
		
		InfAnuales informes = new InfAnuales();
		
		if(dFecdesde!=null && dFhasta!=null) {
			informes.setdFecdesde(FechasUtil.convertStringToDate(dFecdesde));
			informes.setdFhasta(FechasUtil.convertStringToDate(dFhasta));
		}
		
		informes.setComision(comision);
		informes.setvCodinforme(vCodinforme);
		informes.setvSesion(vSesion);
		informes.setvNumdocap(vNumdocap);
		
		return informAnualService.buscar(informes);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar( 
			@RequestParam(value="vCodinforme")                   String       vCodinforme,
			@RequestParam(value="vSesion")                       String        vSesion, 
			@RequestParam(value="vNumdocap")                     String        vNumdocap, 
			@RequestParam(value="comision",required = false)     String         comision, 
			@RequestParam(value="dFecdesde")                     String         dFecdesde,  
			@RequestParam(value="docboletin",required = false)   MultipartFile  docboletin ) {
		
		logger.info("========== INGRESO A GRABAR BOLETINES=============== ");
		
		InfAnuales generico = new InfAnuales();
		Archivos archivo = new Archivos();
		Map<String, Object> response = new HashMap<>();
		try {
			
			if(docboletin!=null && docboletin.getSize()>0) {
				archivo = archivoUtilitarioService.cargarArchivo(docboletin, ConstantesUtil.C_BOLETINES);
				generico.setvUbiarch(archivo.getUbicacion());
				generico.setvNombreArchivo(archivo.getNombre());
				generico.setvExtension(archivo.getExtension());
			}
			
			generico.setvCodinforme(vCodinforme);
			generico.setvSesion(vSesion);
			generico.setComision(comision);
			generico.setvNumdocap(vNumdocap);
			generico.setdFecdesde(FechasUtil.convertStringToDate(dFecdesde)); 
			generico = informAnualService.Registrar(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<InfAnuales>(generico,HttpStatus.CREATED);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizar(
			@RequestParam(value="iNformeidpk")                   Long          iNformeidpk,
			@RequestParam(value="vCodinforme")                   String        vCodinforme,
			@RequestParam(value="vSesion")                       String        vSesion, 
			@RequestParam(value="vNumdocap")                     String      vNumdocap, 
			@RequestParam(value="comision",required = false)     String          comision, 
			@RequestParam(value="dFecdesde")                     String        dFecdesde,  
			@RequestParam(value="docboletin",required = false)   MultipartFile docboletin 
			
			) throws IOException {
		
		
		InfAnuales generico = new InfAnuales();
		Map<String, Object> response = new HashMap<>();
		try {
			
			if(docboletin!=null && docboletin.getSize()>0) {
				Archivos archivo = new Archivos();
				archivo = archivoUtilitarioService.cargarArchivo(docboletin, ConstantesUtil.C_BOLETINES);
				generico.setvUbiarch(archivo.getUbicacion());
				generico.setvNombreArchivo(archivo.getNombre());
				generico.setvExtension(archivo.getExtension());
			}
			 
			
			generico.setiNformeidpk(iNformeidpk);
			generico.setvCodinforme(vCodinforme);
			generico.setvSesion(vSesion);
			generico.setComision(comision);
			generico.setvNumdocap(vNumdocap);
			generico.setdFecdesde(FechasUtil.convertStringToDate(dFecdesde)); 
			generico = informAnualService.Actualizar(generico);  
			
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<InfAnuales>(generico,HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		logger.info("==========  insertarConsejeros  ==========="+id);
		InfAnuales generico = new InfAnuales();
		generico.setiNformeidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = informAnualService.buscarPorId(generico);
			
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.INFORMES_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.INFORMES_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			 
			generico = informAnualService.Eliminar(generico);

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<InfAnuales>(generico,HttpStatus.OK);
	}

}
