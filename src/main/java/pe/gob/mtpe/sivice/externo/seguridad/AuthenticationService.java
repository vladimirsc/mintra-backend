package pe.gob.mtpe.sivice.externo.seguridad;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;
import pe.gob.mtpe.sivice.externo.core.negocio.service.UsuarioRolService; 

@Service("UserDetailsService")
public class AuthenticationService implements UserDetailsService {
		
	@Autowired
	private UsuarioRolService usuarioRolService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
		UsuarioRol usuariorol  =usuarioRolService.buscarPorCorreo(username);
		
		
		if(usuariorol==null) {
			throw new UsernameNotFoundException("username "+username+"no existe");
		}
		 
		  
		GrantedAuthority authority = new SimpleGrantedAuthority(usuariorol.getRoles().getvDesnombre());
		UserDetails userDetails = (UserDetails)new User(usuariorol.getUsuario().getUsername(),usuariorol.getUsuario().getPassword(),Arrays.asList(authority));
		 
		return userDetails;
	}

}
