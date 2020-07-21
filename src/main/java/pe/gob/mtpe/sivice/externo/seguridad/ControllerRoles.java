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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Roles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioRolService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping({ "/api/roles" })
public class ControllerRoles {

	@Autowired
	private UsuarioRolService usuarioRolService;

	@PostMapping(value = "/registrar")
	public ResponseEntity<?> registra(@RequestParam("idusuario") Long idusuario, @RequestParam("idrol") Long idrol) {

		UsuarioRol usuarioRol = new UsuarioRol();
		UsuarioRol usuarioRolbuscar = new UsuarioRol();

		Map<String, Object> response = new HashMap<>();
		try {

			Usuarios usuario = new Usuarios();
			usuario.setuSuarioidpk(idusuario);

			Roles rol = new Roles();
			rol.setrOlidpk(idrol);

			usuarioRol.setUsuario(usuario);
			usuarioRol.setRoles(rol);

			usuarioRolbuscar = usuarioRolService.buscarPorRol(usuarioRol);
			if (usuarioRolbuscar != null) {
				usuarioRol=usuarioRolService.deshabilitarrol(usuarioRolbuscar);
			} else {
				usuarioRol=usuarioRolService.Registrar(usuarioRol);
			}

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, usuarioRol);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		

		return new ResponseEntity<UsuarioRol>(usuarioRol, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/activar/{idusuariorol}")
	public ResponseEntity<?>  activarRol(@PathVariable Long idusuariorol){
		UsuarioRol usuarioRol = new UsuarioRol();
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioRol.setuSuariorolidpk(idusuariorol);
			usuarioRol = usuarioRolService.buscarPorId(usuarioRol);
			
			if(usuarioRol==null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.C_MSG_MENSAJE_USUARIOROL);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.C_MSG_ERROR_USUARIOROL);
				response.put(ConstantesUtil.X_ENTIDAD, usuarioRol);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			if(!usuarioRol.getcFlgactivo().equals("1")) {
 
			usuarioRol.setcFlgactivo("1");
			usuarioRol.setcFlgelimino("0");
			usuarioRol = usuarioRolService.Actualizar(usuarioRol);
			}
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, usuarioRol);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UsuarioRol>(usuarioRol, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/desactivar/{idusuariorol}")
	public ResponseEntity<?>  desactivarRol(@PathVariable Long idusuariorol){
		UsuarioRol usuarioRol = new UsuarioRol();
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioRol.setuSuariorolidpk(idusuariorol);
			usuarioRol = usuarioRolService.buscarPorId(usuarioRol);
			
			if(usuarioRol==null) {
				response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.C_MSG_MENSAJE_USUARIOROL);
				response.put(ConstantesUtil.X_ERROR, ConstantesUtil.C_MSG_ERROR_USUARIOROL);
				response.put(ConstantesUtil.X_ENTIDAD, usuarioRol);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			usuarioRol.setcFlgactivo("0");
			usuarioRol.setcFlgelimino("1");
			usuarioRol = usuarioRolService.Actualizar(usuarioRol);
			
		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, usuarioRol);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UsuarioRol>(usuarioRol, HttpStatus.OK);
	}
	
	@GetMapping(value="/{idusuario}")
	public List<UsuarioRol> listaRolesPorUsuario(@PathVariable Long idusuario){
		
		List<UsuarioRol> listar = new ArrayList<UsuarioRol>();
		
		Usuarios usuarios = new Usuarios();
		usuarios.setuSuarioidpk(idusuario);
		
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setUsuario(usuarios);
		listar = usuarioRolService.buscar(usuarioRol);
		
		return listar;
	}

}
