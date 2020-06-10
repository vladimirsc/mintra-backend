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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciaConsejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AsistenciaService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;



@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping({ "/api/asistencia" })
public class ControladorAsistencia {

	private static final Logger logger = LoggerFactory.getLogger(ControladorActas.class);

	@Autowired
	private AsistenciaService asistenciaService;

 
	@GetMapping("/{idsesion}")
	public ResponseEntity<?> buscarPorIdAsistencias(@PathVariable Long idsesion) {
		//ONTENEMOS LOS DATOS DE LA SESSION
		Sesiones sesion = new Sesiones();
		Map<String, Object> response = new HashMap<>();
		try {
			sesion.setsEsionidpk(idsesion);
			sesion= asistenciaService.buscarSesion(sesion);
			if(sesion==null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.SESION_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.SESION_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, sesion);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, sesion);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Sesiones>(sesion,HttpStatus.OK);
	}
	
	@GetMapping("/listarasistentes/{idsesion}")
	public List<AsistenciaConsejeros> buscarAsistencias(@PathVariable Long idsesion) {
		List<AsistenciaConsejeros> lista = new ArrayList<AsistenciaConsejeros>();
		
		try {
			lista = asistenciaService.listarConsejerosAsistencia(idsesion);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}
	
	
	
	

}
