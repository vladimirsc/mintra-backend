package pe.gob.mtpe.sivice.externo.configuraciones;

 
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AutorizacionServidorConfiguracion extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder claveCodificada;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private AdicionarInfoToken adicionarInfoToken;
	
	@Value("${authorization.user.client}")
    private String client;

    @Value("${authorization.user.secret}")
    private String secret;
    
    @Value("${authorization.user.timeout}")
    private int expiration;
 
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception { 
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}
 
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception { 
		//super.configure(clients);
		clients.inMemory()
		.withClient(client)
		.secret(claveCodificada.encode(secret))
		.scopes("read","write")
		.authorizedGrantTypes("password","refresh_token")
		.accessTokenValiditySeconds(expiration)
		.refreshTokenValiditySeconds(expiration);
	}
	
 
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception { 
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(adicionarInfoToken,accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
	}

 
	@Bean
	public JwtAccessTokenConverter  accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfiguracion.RSA_PRIVADA);
		jwtAccessTokenConverter.setVerifierKey(JwtConfiguracion.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		JwtTokenStore jwtTokenStore = new JwtTokenStore(accessTokenConverter());
		return jwtTokenStore;
	}
	
	
	
}
