package pe.gob.mtpe.sivice.externo.integracion.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.EncargadoRegion;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.EncargadoRegionService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping({"/api/encargadoregion"})
public class ControladorEncargadoRegion {
	
	@Autowired
	private EncargadoRegionService encargadoRegionService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;
	
	
	
	
	@GetMapping("/")
	public List<EncargadoRegion> informacion(){
		List<EncargadoRegion> generico= new ArrayList<EncargadoRegion>();
		generico = encargadoRegionService.listar();
		return generico;
	}
	
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(
			          @RequestParam(value = "regionpk")            Long          regionpk,
			          @RequestParam(value = "consejeropk")         Long          consejeropk, 
			          @RequestParam(value = "numerodocaprobacion") String        numerodocaprobacion, 
			          @RequestParam(value = "fechadocaprobacion")  String        fechadocaprobacion, 
			          @RequestParam(value="docaprob")              MultipartFile docaprob
	 ) {
		
		EncargadoRegion  generico = new EncargadoRegion();
		Map<String, Object> response = new HashMap<>();
		try {
			
			if(docaprob!=null && docaprob.getSize()>0) {
				Archivos archivo = new Archivos();
				archivo = archivoUtilitarioService.cargarArchivo(docaprob, ConstantesUtil.C_ENCARGADO_REGION);
				if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) {
					generico.setvNombreArchivo(archivo.getNombre());
					generico.setvUbicacionarchivo(archivo.getUbicacion());
					generico.setvExtension(archivo.getExtension());
				}
				
			}
			
			Regiones region = new Regiones();
			region.setrEgionidpk(regionpk);
			
			Consejeros consejero = new Consejeros();
			consejero.setcOnsejeroidpk(consejeropk);
			
			generico.setRegion(region);
			generico.setConsejero(consejero);
			generico.setvNumdocaprobacion(numerodocaprobacion);
			generico.setdFechaprobacion((fechadocaprobacion!=null)? FechasUtil.convertStringToDate(fechadocaprobacion) : null  );
			generico = encargadoRegionService.registrar(generico);
			
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EncargadoRegion>(generico,HttpStatus.OK);
	}
	
	
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizar(
			          @RequestParam(value = "eNcargadoregionidpk") Long          eNcargadoregionidpk,
			          @RequestParam(value = "consejeropk")         Long          consejeropk, 
			          @RequestParam(value = "numerodocaprobacion") String        numerodocaprobacion, 
			          @RequestParam(value = "fechadocaprobacion")  String        fechadocaprobacion, 
			          @RequestParam(value="docaprob")              MultipartFile docaprob
	 ) {
		
		EncargadoRegion  generico = new EncargadoRegion();
		Map<String, Object> response = new HashMap<>();
		try {
			
			
			generico.seteNcargadoregionidpk(eNcargadoregionidpk);
			generico = encargadoRegionService.buscarPorId(generico);
			
			if(docaprob!=null && docaprob.getSize()>0) {
				Archivos archivo = new Archivos();
				archivo = archivoUtilitarioService.cargarArchivo(docaprob, ConstantesUtil.C_ENCARGADO_REGION);
				if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) {
					generico.setvNombreArchivo(archivo.getNombre());
					generico.setvUbicacionarchivo(archivo.getUbicacion());
					generico.setvExtension(archivo.getExtension());
				}
				
			}
			
			
			Consejeros consejero = new Consejeros();
			consejero.setcOnsejeroidpk(consejeropk);
 
			generico.setConsejero(consejero);
			generico.setvNumdocaprobacion(numerodocaprobacion);
			generico.setdFechaprobacion((fechadocaprobacion!=null)? FechasUtil.convertStringToDate(fechadocaprobacion) : null  );
			generico = encargadoRegionService.actualizar(generico);
			
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EncargadoRegion>(generico,HttpStatus.OK);
	}
	
	@GetMapping("/{idencargadoregion}")
	public ResponseEntity<?> informacion(@PathVariable Long idencargadoregion){
		
		EncargadoRegion  generico = new EncargadoRegion();
		generico.seteNcargadoregionidpk(idencargadoregion);
		Map<String, Object> response = new HashMap<>();
		try {
			generico = encargadoRegionService.buscarPorId(generico);
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EncargadoRegion>(generico,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idencargadoregion}")
	public ResponseEntity<?> eliminar(@PathVariable Long idencargadoregion){
		
		EncargadoRegion  generico = new EncargadoRegion();
		generico.seteNcargadoregionidpk(idencargadoregion);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = encargadoRegionService.buscarPorId(generico);
			generico = encargadoRegionService.Eliminar(generico);
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EncargadoRegion>(generico,HttpStatus.OK);
	}
	
	

}
