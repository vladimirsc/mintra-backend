package pe.gob.mtpe.sivice.externo.configuraciones;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Roles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioRolService;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioService;


@Component
public class AdicionarInfoToken implements TokenEnhancer {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRolService usuarioRolService;
 
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accestoken, OAuth2Authentication autentificacion) {
	    Map<String,Object> informacion = new HashMap<>(); 
	    Usuarios usuario = new Usuarios();
	    usuario.setUsername(autentificacion.getName());
	    usuario = usuarioService.buscarPorCorreo(usuario);
	    
	    UsuarioRol usuarioRol = new UsuarioRol();
	    Roles rol = new Roles();
	    usuarioRol.setUsuario(usuario);
	    usuarioRol.setRoles(rol);
	    usuarioRol = usuarioRolService.buscarRolPorIdusuario(usuarioRol);
	     
	    informacion.put("infousuario", autentificacion.getName());
	    informacion.put("infonombre", usuario.getvNombre().concat(",").concat(usuario.getvAppaterno()).concat(" ").concat(usuario.getvApmaterno()));
	    informacion.put("inforegion",usuario.getRegiones().getvDesnombre());
	    informacion.put("id_usuario", usuario.getuSuarioidpk().toString());
	    informacion.put("inforegioncodigo",usuario.getRegiones().getrEgionidpk().toString());
	    informacion.put("id_rol",usuarioRol.getRoles().getrOlidpk().toString());
	    informacion.put("nombre_rol",usuarioRol.getRoles().getvDesnombre());
	    ((DefaultOAuth2AccessToken) accestoken).setAdditionalInformation(informacion);
		return accestoken;
	}

}
