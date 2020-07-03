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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Archivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Entidades;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Profesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Tipoconsejero;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ArchivoUtilitarioService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ConsejeroService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping({"/api/consejeros"})
public class ControladorConsejeros {

	private static final Logger logger = LoggerFactory.getLogger(ControladorFijas.class);

	@Autowired
	private ConsejeroService consejeroService;
	
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;

	@GetMapping("/")
	public List<Consejeros> listarConsejeros() {
		logger.info("==========  listarConsejeros  ===========");
		//OBTENEMOS LOS DATOS DE LA SESION
		 Consejeros consejero= new Consejeros();
		 consejero.setrEgionfk(Long.parseLong("14"));
		 consejero.setcOnsejofk(Long.parseLong("1"));
		return consejeroService.listar(consejero);
	}

	@PostMapping("/buscar")
	public List<Consejeros> buscarConsejeros(@RequestBody Consejeros buscar) {
		logger.info("==========  buscarConsejeros  ===========");
		return consejeroService.buscar(buscar);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		logger.info("==========  buscarConsejeros  ===========");
		Consejeros generico = new Consejeros();
		generico.setcOnsejeroidpk(id);
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico = consejeroService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Consejeros>(generico,HttpStatus.OK);
		
	}

	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarConsejeros(
			@RequestParam(value="docaprob")        MultipartFile docaprob,      @RequestParam(value="vTipdocumento")  Long vTipdocumento,
			@RequestParam(value="vNumdocumento")   String        vNumdocumento, @RequestParam(value="vDesnombre")     String vDesnombre,
			@RequestParam(value="vDesappaterno")   String        vDesappaterno, @RequestParam(value="vDesapmaterno")  String vDesapmaterno,
			@RequestParam(value="vProfesion")      Long          vProfesion,    @RequestParam(value="vDesemail1" )    String  vDesemail1,
			@RequestParam(value="vDesemail2")      String        vDesemail2,    @RequestParam(value="vEntidad")       Long  vEntidad,
			@RequestParam(value="vTpconsejero")    Long          vTpconsejero,  @RequestParam(value="dFecinicio")     String  dFecinicio, 
			@RequestParam(value="dFecfin")         String        dFecfin,       @RequestParam(value="vNumdocasig")    String vNumdocasig,
			@RequestParam(value="rEgionfk")        Long          rEgionfk,      @RequestParam(value="cOnsejofk")      Long   cOnsejofk,
			@RequestParam(value="cOmisionfk")      Long          cOmisionfk
			) {
		
		logger.info("==========  insertarConsejeros  ===========");
		Archivos archivo = new Archivos();
		Consejeros generico = new Consejeros(); 
		Consejeros consejeroBuscar = new Consejeros();  
		Map<String, Object> response = new HashMap<>();
		
		try {
			 consejeroBuscar.setvNumdocumento(vNumdocumento);
			 consejeroBuscar = consejeroService.buscarPorDni(consejeroBuscar);
				if(consejeroBuscar!=null) {
					response.put(ConstantesUtil.X_MENSAJE,ConstantesUtil.C_DNI_DUPLICADO_MSG_CONSEJEROS);
					response.put(ConstantesUtil.X_ERROR, ConstantesUtil.C_DNI_DUPLICADO_ERROR_CONSEJEROS);
					response.put(ConstantesUtil.X_ENTIDAD, consejeroBuscar);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}
			
		        if(docaprob!=null && docaprob.getSize()>0) {
		        	archivo = archivoUtilitarioService.cargarArchivo(docaprob, ConstantesUtil.C_CONSEJERO_DOC_ASIGNACION);
		        	
		        	if (archivo.isVerificarCarga() == true && archivo.isVerificarCarga() == true) {
		        		generico.setvNombredocasig(archivo.getNombre());
						generico.setvUbidocasig(archivo.getUbicacion());
						generico.setvExtdocasig(archivo.getExtension());
		        	}
		        }
 
					// REGISTRAMOS AL CONSEJERO
					Regiones       region         = new Regiones();
					Consejos       consejo        = new Consejos();
					Tipoconsejero  tipoconsejero = new Tipoconsejero(); 
					TipoDocumentos tipodocumento  = new TipoDocumentos();
					Profesiones    profesion      = new Profesiones();
					Entidades      entidad        = new Entidades();
					
					if(rEgionfk!=null)      { region.setrEgionidpk(rEgionfk); }
					if(cOnsejofk!=null)     { consejo.setcOnsejoidpk(cOnsejofk);} 
					if(vTipdocumento!=null) { tipodocumento.settPdocumentoidpk(vTipdocumento); }
					if(vProfesion!=null)    { profesion.setpRofesionidpk(vProfesion); }
					if(vEntidad!=null)      { entidad.seteNtidadidpk(vEntidad); }
					if(vTpconsejero!=null)  { tipoconsejero.settPconsejeroidpk(vTpconsejero); }
					
					generico.setRegion(region);
					generico.setConsejo(consejo);
					generico.setTipoconsejero(tipoconsejero); 
					generico.setTipodocumento(tipodocumento);
					generico.setProfesion(profesion);
					generico.setEntidad(entidad);
					generico.setvNumdocumento(vNumdocumento);
					generico.setvDesnombre(vDesnombre);
					generico.setvDesappaterno(vDesappaterno);
					generico.setvDesapmaterno(vDesapmaterno); 
					generico.setvDesemail1(vDesemail1);
					generico.setvDesemail2(vDesemail2);
					generico.setdFecinicio(FechasUtil.convertStringToDate(dFecinicio));
					generico.setdFecfin(FechasUtil.convertStringToDate(dFecfin));
					generico.setvNumdocasig(vNumdocasig);
					
					
					generico = consejeroService.Registrar(generico);
				 

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Consejeros>(generico,HttpStatus.CREATED);
	}
	
	

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarConsejeros(
			@RequestParam(value="docaprob",required = false)        MultipartFile docaprob,      @RequestParam(value="vTipdocumento")  Long vTipdocumento,
			@RequestParam(value="vNumdocumento")   String        vNumdocumento, @RequestParam(value="vDesnombre")     String vDesnombre,
			@RequestParam(value="vDesappaterno")   String        vDesappaterno, @RequestParam(value="vDesapmaterno")  String vDesapmaterno,
			@RequestParam(value="vProfesion")      Long          vProfesion,    @RequestParam(value="vDesemail1" )    String  vDesemail1,
			@RequestParam(value="vDesemail2")      String        vDesemail2,    @RequestParam(value="vEntidad")       Long  vEntidad,
			@RequestParam(value="vTpconsejero")    Long          vTpconsejero,  @RequestParam(value="dFecinicio")     String  dFecinicio, 
			@RequestParam(value="dFecfin")         String        dFecfin,       @RequestParam(value="vNumdocasig")    String vNumdocasig,
			@RequestParam(value="rEgionfk")        Long          rEgionfk,      @RequestParam(value="cOnsejofk")      Long   cOnsejofk,
			@RequestParam(value="cOmisionfk")      Long          cOmisionfk,    @RequestParam(value="cOnsejeroidpk")  Long  cOnsejeroidpk) throws IOException{
		
		Archivos archivo = new Archivos();
		Consejeros consejeroBuscar = new Consejeros();
		consejeroBuscar.setcOnsejeroidpk(cOnsejeroidpk);
		
		
		Map<String, Object> response = new HashMap<>();
		try {
			
			
			consejeroBuscar = consejeroService.buscarPorId(consejeroBuscar);
			if (consejeroBuscar == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, consejeroBuscar);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			//verificamos que no cambie de dni
			if(!consejeroBuscar.getvNumdocumento().equals(vNumdocumento)) {
				consejeroBuscar.setvNumdocumento(vNumdocumento);
				 consejeroBuscar = consejeroService.buscarPorDni(consejeroBuscar);
				if(consejeroBuscar!=null) {
					response.put(ConstantesUtil.X_MENSAJE,ConstantesUtil.C_DNI_DUPLICADO_MSG_CONSEJEROS);
					response.put(ConstantesUtil.X_ERROR, ConstantesUtil.C_DNI_DUPLICADO_ERROR_CONSEJEROS);
					response.put(ConstantesUtil.X_ENTIDAD, consejeroBuscar);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}
			} 
 
			
			if(docaprob!=null && docaprob.getSize()>0) {
				archivo = archivoUtilitarioService.cargarArchivo(docaprob, ConstantesUtil.C_CONSEJERO_DOC_ASIGNACION); 
				consejeroBuscar.setvNombredocasig(archivo.getNombre());
				consejeroBuscar.setvUbidocasig(archivo.getUbicacion());
				consejeroBuscar.setvExtdocasig(archivo.getExtension());
			}
			
			
			
			//consejeroBuscar.setvTipdocumento(vTipdocumento); ??????
			consejeroBuscar.setvNumdocumento(vNumdocumento);
			consejeroBuscar.setvDesnombre(vDesnombre);
			consejeroBuscar.setvDesappaterno(vDesappaterno);
			consejeroBuscar.setvDesapmaterno(vDesapmaterno);
			//consejeroBuscar.setvProfesion(vProfesion); ??????
			consejeroBuscar.setvDesemail1(vDesemail1);
			consejeroBuscar.setvDesemail2(vDesemail2);
			//consejeroBuscar.setvEntidad(vEntidad); ??????
			//consejeroBuscar.setvTpconsejero(vTpconsejero); ??????
			consejeroBuscar.setdFecinicio(FechasUtil.convertStringToDate(dFecinicio));
			consejeroBuscar.setdFecfin(FechasUtil.convertStringToDate(dFecfin));
			consejeroBuscar.setvNumdocasig(vNumdocasig);
			//consejeroBuscar.setcOmisionfk(cOmisionfk); ??????
			
			consejeroBuscar = consejeroService.Actualizar(consejeroBuscar);
			
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, consejeroBuscar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Consejeros>(consejeroBuscar,HttpStatus.OK);
		
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarBoletin(@PathVariable Long id) {
		logger.info("==========  eliminarBoletin  ===========");
		Consejeros generico = new Consejeros();
		generico.setcOnsejeroidpk(id);
 
		Map<String, Object> response = new HashMap<>();
		try {
			generico = consejeroService.buscarPorId(generico);
			if (generico == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CONSEJERO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, generico);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			generico = consejeroService.Eliminar(generico);
			 
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Consejeros>(generico,HttpStatus.OK);
	}
	
	@GetMapping("/comision/{id}")
	public List<Consejeros> listarPorComision(@PathVariable Long id){
		Consejeros consejero = new Consejeros();
		//consejero.setcOmisionfk(id); ??????
	  return consejeroService.listarConsejerosPorComision(consejero);
	}
	
	@GetMapping("/descargar/{id}")
	public void descargarArchivo(@PathVariable Long id, HttpServletResponse res) {
		Consejeros generico = new Consejeros();
		generico.setcOnsejeroidpk(id);
		String ruta = "";
		try {
			generico = consejeroService.buscarPorId(generico);
			ruta = rutaRaiz + generico.obtenerRutaAbsoluta();
			res.setHeader("Content-Disposition", "attachment; filename=" + generico.getvNombredocasig()+"."+generico.getvExtdocasig());
			res.getOutputStream().write(Files.readAllBytes(Paths.get(ruta)));
		} catch (Exception e) {
			
		}

	}
	
}
