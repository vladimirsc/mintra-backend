package pe.gob.mtpe.sivice.externo.configuraciones;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.gob.mtpe.sivice.externo.seguridad.AuthenticationService;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Bean
	public BCryptPasswordEncoder claveCodificada() {
		return new BCryptPasswordEncoder();
	}
 

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(claveCodificada());
	}

	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().frameOptions().sameOrigin().contentSecurityPolicy("frame-ancestors 'self'");
	}
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/v2/api-docs",
				"/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
				"/webjars/**",
				"/",
				"/error",
				"/images/**",
				"/js/**",
				"/css/**");
    }
	
	
	
}

