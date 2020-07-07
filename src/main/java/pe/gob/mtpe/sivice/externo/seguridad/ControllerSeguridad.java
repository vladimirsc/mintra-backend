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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
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
	public ResponseEntity<?> registra(@RequestParam("tipodocumento") Long tipodocumento,
			@RequestParam("vNombre") String vNombre, @RequestParam("vAppaterno") String vAppaterno,
			@RequestParam("vApmaterno") String vApmaterno, @RequestParam("vNumdocumento") String vNumdocumento,
			@RequestParam("vCorreo") String vCorreo, @RequestParam("vClave") String vClave,
			@RequestParam("region") Long region) {
		Usuarios usuario = new Usuarios();
		Map<String, Object> response = new HashMap<>();
		Usuarios usuariobuscar = new Usuarios();

		try {

			TipoDocumentos tipoDocumentos = new TipoDocumentos();
			tipoDocumentos.settPdocumentoidpk(tipodocumento);

			usuario.setvNombre(vNombre);
			usuario.setvAppaterno(vAppaterno);
			usuario.setvApmaterno(vApmaterno);
			usuario.setvNumdocumento(vNumdocumento);
			usuario.setUsername(vCorreo);
			usuario.setPassword(vClave);
			usuario.setTipodocumento(tipoDocumentos);

			usuariobuscar = usuarioService.buscarPorCorreo(usuario);

			if (usuariobuscar == null) {
				usuariobuscar = usuarioService.Registrar(usuario);
				
			} else {
				if (!"1".equals(usuariobuscar.getEnabled())) {
					usuariobuscar.setEnabled("1");
					usuariobuscar = usuarioService.Actualizar(usuariobuscar);
				}

			}

		} catch (DataAccessException e) {
			response.put(ConstantesUtil.X_MENSAJE, ConstantesUtil.GENERAL_MSG_ERROR_BASE);
			response.put(ConstantesUtil.X_ERROR,
					e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put(ConstantesUtil.X_ENTIDAD, usuario);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Usuarios>(usuariobuscar, HttpStatus.OK);
	}

}
