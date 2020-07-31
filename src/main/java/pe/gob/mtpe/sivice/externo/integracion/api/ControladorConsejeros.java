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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
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
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequestMapping({"/api/consejeros"})
public class ControladorConsejeros {

	private static final Logger logger = LoggerFactory.getLogger(ControladorFijas.class);

	@Autowired
	private ConsejeroService consejeroService;
	
	@Autowired
	private ArchivoUtilitarioService archivoUtilitarioService;
	
	@Autowired
	private FijasService fijasService;

	@Value("${rutaArchivo}")
	private String rutaRaiz;

	
	@ApiOperation(value = "Lista los consejeros")
	@GetMapping("/")
	public List<Consejeros> listarConsejeros(
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true) String nombreRol
			) {
		
		logger.info("==========  listarConsejeros  ===========");
		
 
		 
		   Regiones region = new Regiones();
		   region.setrEgionidpk(idRegion);
 
		  Consejeros consejero= new Consejeros();
		  consejero.setRegion(region); 
		 
		return consejeroService.listar(consejero);
	}

	
	@ApiOperation(value = "Busca consejeros segun los criterios de busqueda ingresados")
	@PostMapping("/buscar")
	public List<Consejeros> buscarConsejeros( 
			@RequestBody Consejeros buscar,
			@RequestHeader(name = "id_usuario", required = true)        Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true)          String nombreRol) {
		
		logger.info("==========  buscarConsejeros  ===========");
		
		// *****************  INFORMACION DEL USUARIO LOGEADO ***************
		   Long idconsejo = 0L;  
		   idconsejo = fijasService.BuscarConsejoPorNombre(nombreRol);
		// ******************************************************************
		
		  
		  Regiones region = new Regiones();
		  region.setrEgionidpk(idRegion);
		  
		  
		  Consejos consejo = new Consejos();
		  
		  
		  if(idconsejo>0) {
			  consejo.setcOnsejoidpk(idconsejo);
			  
		  }
		  
		  
		  buscar.setRegion(region);
		  buscar.setConsejo(consejo);
		
		return consejeroService.buscar(buscar);
	}
	
	
	
	@ApiOperation(value = "Muestra la informacion de un consejero por su codigo unico")
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId( 
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true)        Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true)          String nombreRol) {
		
		logger.info("==========  buscarConsejeros  ===========");
		
		// *****************  INFORMACION DEL USUARIO LOGEADO ***************
		 // InformacionUsuario informacionUsuario = new InformacionUsuario();
		 // informacionUsuario =fijasService.informacionUsuario(idUsuario);
		// ******************************************************************
		  
		Consejeros generico = new Consejeros();
		
		Map<String, Object> response = new HashMap<>();
		try {
			
			generico.setcOnsejeroidpk(id); 
			generico.setrEgionfk(idRegion);
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

	
	
	
	@ApiOperation(value = "Registrar un consejero")
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarConsejeros(
			@RequestParam(value="docaprob")        MultipartFile docaprob,      @RequestParam(value="vTipdocumento")  Long vTipdocumento,
			@RequestParam(value="vNumdocumento")   String        vNumdocumento, @RequestParam(value="vDesnombre")     String vDesnombre,
			@RequestParam(value="vDesappaterno")   String        vDesappaterno, @RequestParam(value="vDesapmaterno")  String vDesapmaterno,
			@RequestParam(value="vProfesion")      Long          vProfesion,    @RequestParam(value="vDesemail1" )    String  vDesemail1,
			@RequestParam(value="vDesemail2")      String        vDesemail2,    @RequestParam(value="vEntidad")       Long  vEntidad,
			@RequestParam(value="vTpconsejero")    Long          vTpconsejero,  @RequestParam(value="dFecinicio")     String  dFecinicio, 
			@RequestParam(value="dFecfin")         String        dFecfin,       @RequestParam(value="vNumdocasig")    String vNumdocasig,
			@RequestParam(value="rEgionfk")        String        rEgionfk,      @RequestParam(value="cOnsejofk")      Long   cOnsejofk,
			@RequestParam(value="cOmisionfk")      Long          cOmisionfk,    @RequestHeader(name = "id_usuario", required = true) Long idUsuario,
			@RequestHeader(name="info_regioncodigo",required=true) Long idRegion,@RequestHeader(name="info_rol",required =true) String nombreRol
			) {
		
		logger.info("==========  insertarConsejeros  ===========");
		
		// *****************  INFORMACION DEL USUARIO LOGEADO ***************
		  //InformacionUsuario informacionUsuario = new InformacionUsuario();
		  //informacionUsuario =fijasService.informacionUsuario(idUsuario);
		   Long idconsejo = 0L;  
		   idconsejo = fijasService.BuscarConsejoPorNombre(nombreRol);
		// ******************************************************************
		String flgeliminado = "0";  
		Archivos archivo = new Archivos();
		Consejeros generico = new Consejeros(); 
		Consejeros consejeroBuscar = new Consejeros(); 
		Map<String, Object> response = new HashMap<>();
		
		try {
			 consejeroBuscar.setvNumdocumento(vNumdocumento);
			 consejeroBuscar = consejeroService.buscarPorDni(consejeroBuscar);
				if(consejeroBuscar!=null) {
					if("1".equals(consejeroBuscar.getcFlgeliminado())) {
						flgeliminado = consejeroBuscar.getcFlgeliminado();
						generico.setcFlgeliminado("0");
						generico.setcOnsejeroidpk(consejeroBuscar.getcOnsejeroidpk());
						generico.setdFecreg(consejeroBuscar.getdFecreg());
						generico.setdFecelimina(consejeroBuscar.getdFecelimina());
						generico.setnUsureg(consejeroBuscar.getnUsureg());
						generico.setnUsueliminia(consejeroBuscar.getnUsueliminia());
					}else {
						response.put(ConstantesUtil.X_MENSAJE," EL DNI("+consejeroBuscar.getvNumdocumento()+") "+ConstantesUtil.C_DNI_DUPLICADO_MSG_CONSEJEROS);
						response.put(ConstantesUtil.X_ERROR, ConstantesUtil.C_DNI_DUPLICADO_ERROR_CONSEJEROS);
						response.put(ConstantesUtil.X_ENTIDAD, consejeroBuscar);
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
					}
					
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
					region.setrEgionidpk(idRegion);
					
					Consejos       consejo        = new Consejos();
					consejo.setcOnsejoidpk(idconsejo);
					
					Tipoconsejero  tipoconsejero = new Tipoconsejero(); 
					TipoDocumentos tipodocumento  = new TipoDocumentos();
					Profesiones    profesion      = new Profesiones();
					Entidades      entidad        = new Entidades();
					
					
					
					//if(rEgionfk!=null)      { region.setrEgionidpk(vregion); }
					//if(cOnsejofk!=null)     { consejo.setcOnsejoidpk(cOnsejofk);} 
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
					
					
					if("1".equals(flgeliminado)) {
						generico.setnUsumodifica(idUsuario);
						generico = consejeroService.Actualizar(generico);
						
					}else {
						generico.setnUsureg(idUsuario);
						generico = consejeroService.Registrar(generico);
					}
					
				 

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Consejeros>(generico,HttpStatus.CREATED);
	}
	
	

	@ApiOperation(value = "Actualizar informacion de un consejero")
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
			@RequestParam(value="cOmisionfk")      Long          cOmisionfk,    @RequestParam(value="cOnsejeroidpk")  Long  cOnsejeroidpk,
			@RequestHeader(name="id_usuario",required = true)  Long idUsuario,  @RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name="info_rol",required = true) String nombreRol
		) {
		
		logger.info("==========  ACTUALIZAR CONSEJERO  ===========");
 
		
		Archivos archivo = new Archivos();
		Consejeros consejeroBuscar = new Consejeros();
		consejeroBuscar.setcOnsejeroidpk(cOnsejeroidpk);
		
		
		Map<String, Object> response = new HashMap<>();
		try {
			
			
			consejeroBuscar = consejeroService.buscarPorId(consejeroBuscar);
			if (consejeroBuscar == null) {
				response.put(ConstantesUtil.X_MENSAJE,ConstantesUtil.C_DNI_DUPLICADO_MSG_CONSEJEROS);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CONSEJERO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, consejeroBuscar);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			//verificamos que no cambie de dni
			if(!consejeroBuscar.getvNumdocumento().equals(vNumdocumento)) {
				consejeroBuscar.setvNumdocumento(vNumdocumento);
				 consejeroBuscar = consejeroService.buscarPorDni(consejeroBuscar);
				if(consejeroBuscar!=null) {
					response.put(ConstantesUtil.X_MENSAJE," EL DNI("+consejeroBuscar.getvNumdocumento()+") "+ConstantesUtil.C_DNI_DUPLICADO_MSG_CONSEJEROS);
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
			
			Tipoconsejero  tipoconsejero = new Tipoconsejero(); 
			TipoDocumentos tipodocumento  = new TipoDocumentos();
			Profesiones    profesion      = new Profesiones();
			Entidades      entidad        = new Entidades();
			
			 
			if(vTpconsejero!=null)  { tipoconsejero.settPconsejeroidpk(vTpconsejero); }
			if(vTipdocumento!=null) { tipodocumento.settPdocumentoidpk(vTipdocumento); }
			if(vProfesion!=null)    { profesion.setpRofesionidpk(vProfesion); }
			if(vEntidad!=null)      { entidad.seteNtidadidpk(vEntidad); }
			

			consejeroBuscar.setTipodocumento(tipodocumento); 
			consejeroBuscar.setvNumdocumento(vNumdocumento);
			consejeroBuscar.setvDesnombre(vDesnombre);
			consejeroBuscar.setvDesappaterno(vDesappaterno);
			consejeroBuscar.setvDesapmaterno(vDesapmaterno);
			consejeroBuscar.setProfesion(profesion);  
			consejeroBuscar.setvDesemail1(vDesemail1);
			consejeroBuscar.setvDesemail2(vDesemail2);
			consejeroBuscar.setEntidad(entidad);  
			consejeroBuscar.setTipoconsejero(tipoconsejero);
			consejeroBuscar.setdFecinicio(FechasUtil.convertStringToDate(dFecinicio));
			consejeroBuscar.setdFecfin(FechasUtil.convertStringToDate(dFecfin));
			consejeroBuscar.setvNumdocasig(vNumdocasig); 
			consejeroBuscar.setnUsumodifica(idUsuario); 
			
			consejeroBuscar = consejeroService.Actualizar(consejeroBuscar);
			
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, consejeroBuscar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Consejeros>(consejeroBuscar,HttpStatus.OK);
		
	}


	@ApiOperation(value = "Eliminar un consejero")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(
			@PathVariable Long id ,
			@RequestHeader(name = "id_usuario", required = true)        Long idUsuario,
			@RequestHeader(name = "info_regioncodigo", required = true) Long idRegion,
			@RequestHeader(name = "info_rol", required = true)          String nombreRol) {
		
		logger.info("==========  ELIMINAR CONSEJERO ===========");
 
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

			generico.setnUsueliminia(idUsuario);
			generico = consejeroService.Eliminar(generico);
			 
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, generico);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Consejeros>(generico,HttpStatus.OK);
	}
	
	/*
	@GetMapping("/comision/{id}")
	public List<Consejeros> listarPorComision(
			@PathVariable Long id,
			@RequestHeader(name = "id_usuario", required = true) Long idUsuario){
		
		logger.info("==========  LISTAR CONSEJERO POR COMISION ===========");
		
		// *****************  INFORMACION DEL USUARIO LOGEADO ***************
		  InformacionUsuario informacionUsuario = new InformacionUsuario();
		  informacionUsuario =fijasService.informacionUsuario(idUsuario);
		// ******************************************************************
		
		Consejeros consejero = new Consejeros();
		 
	  return consejeroService.listarConsejerosPorComision(consejero);
	} */
	
	
	@ApiOperation(value = "descargar el archivo de un consejero")
	@GetMapping("/descargar/{id}")
	public void descargarArchivo(@PathVariable Long id, HttpServletResponse res) {
		
		logger.info("==========  DESCARGAR DOCUMENTO CONSEJERO ===========");
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
