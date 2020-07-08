package pe.gob.mtpe.sivice.externo.seguridad;

import java.util.HashMap;
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
				usuarioRolService.deshabilitarrol(usuarioRolbuscar);
			} else {
				usuarioRolService.Registrar(usuarioRol);
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

}
