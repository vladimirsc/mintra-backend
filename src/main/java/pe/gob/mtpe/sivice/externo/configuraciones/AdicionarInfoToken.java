package pe.gob.mtpe.sivice.externo.configuraciones;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component; 
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioService;


@Component
public class AdicionarInfoToken implements TokenEnhancer {
	
	@Autowired
	private UsuarioService usuarioService;
 
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accestoken, OAuth2Authentication autentificacion) {
	    Map<String,Object> informacion = new HashMap<>(); 
	    Usuarios usuario = new Usuarios();
	    usuario.setUsername(autentificacion.getName());
	    usuario = usuarioService.buscarPorCorreo(usuario);
	    
	    
	    informacion.put("infousuario", autentificacion.getName());
	    informacion.put("infonombre", usuario.getvNombre().concat(",").concat(usuario.getvAppaterno()).concat(" ").concat(usuario.getvApmaterno()));
	    informacion.put("inforegion",usuario.getRegiones().getvDesnombre());
	    informacion.put("id_usuario", usuario.getuSuarioidpk().toString());
	    informacion.put("inforegioncodigo",usuario.getRegiones().getrEgionidpk().toString());
	    ((DefaultOAuth2AccessToken) accestoken).setAdditionalInformation(informacion);
		return accestoken;
	}

}
