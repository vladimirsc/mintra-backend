package pe.gob.mtpe.sivice.externo.seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping({ "/api/seguridad" })
public class ControllerSeguridad {

	@Autowired
	private UsuarioService usuarioService;
	
	
	 
	

	@PostMapping(value = "/registrar")
	public ResponseEntity<?> registra(
			@RequestParam("tipodocumento") Long tipodocumento,
			@RequestParam("vNombre") String vNombre,
			@RequestParam("vAppaterno") String vAppaterno,
			@RequestParam("vApmaterno") String vApmaterno,
			@RequestParam("vNumdocumento") String vNumdocumento,
			@RequestParam("vCorreo") String vCorreo,
			@RequestParam("vClave") String vClave,
			@RequestParam("rol") Long rol) {
		Usuarios usuario = new Usuarios();
		Map<String, Object> response = new HashMap<>();
		try {

			usuario.setvNombre(vNombre);
			usuario.setvAppaterno(vAppaterno);
			usuario.setvApmaterno(vApmaterno);
			usuario.setvNumdocumento(vNumdocumento);
			usuario.setUsername(vCorreo);
			usuario.setPassword(vClave);
			
			 List<Usuarios> listaUsuario = new ArrayList<Usuarios>();
			 
			 listaUsuario = usuarioService.buscar(usuario);
             if(!listaUsuario.isEmpty()) {
            	 response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CALENDARIO_MSG_ERROR_BUSCAR);
 				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CALENDARIO_ERROR_BUSCAR);
 				response.put(ConstantesUtil.X_ENTIDAD, usuario);
 				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
             }
			
			
			usuario = usuarioService.Registrar(usuario, tipodocumento,rol);
			
			if (usuario == null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.CALENDARIO_MSG_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.CALENDARIO_ERROR_BUSCAR);
				response.put(ConstantesUtil.X_ENTIDAD, usuario);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, usuario);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Usuarios>(usuario, HttpStatus.OK);
	}

}
