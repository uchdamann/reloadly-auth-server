package com.reloadly.devops.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.reloadly.devops.utilities.AuthProperties;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	@Qualifier("getPasswordEncoder")
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private AuthProperties props;
	private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEA1kjoM8AvYpxql6m7GVY2w9wlU/RtthFqQ0ftjYnLDUUR0Stk\r\n" + 
			"CemuprGIMRaiwc5/GwYSd4kswkl/Mofd0DeiIAChP7aqijYiThAUqqC5t8IugF78\r\n" + 
			"p073VDcXYQyXvUtPef2lG/BT4+NiRK4eiXFd8IIf5CFXhI8GYMLFLtyBhOWJyyfa\r\n" + 
			"RMJ38XDDsD3cG1Ef8JD6oHSuyQP5Cyio2XIvFqgImPZuJHQD3xNJnSIvQHCR68Mp\r\n" + 
			"XUSloDz3Q/3iEj5vhU0cntH/Xu6wYeKOUC46pBggy18ItReu3DnJQ+plvGIvYYEf\r\n" + 
			"oBApTIQvXgx+njs6EBYFit9Zy1TZ0iOMGHjWiwIDAQABAoIBADWTsQsPaEmdlkEp\r\n" + 
			"XPBuz+6UonilSrEVFZ6Q42JaK3y74kKMOM0JsnNXPBgZ8sPGX51gHUnp8Fj7i2QU\r\n" + 
			"+G7RGiYqQibuAyHfeyBNgP1Qhl7dnEbYeQ9T/R7/T0cioOXhaJO1yYx8wFzgQ3es\r\n" + 
			"mVIAGF7ECjk95OXt5MhYqniXBYM2c7nOwA4vTPjo91nuQakYMtYYihC8xNoo/Jms\r\n" + 
			"xn+sGTp3ZWWrziVm1NcrXMGxomIFzJk64MJWy3781Ycd+svn+HczsD1NNzIRChME\r\n" + 
			"OyY/oUAWk/3BadxiQz+r/brs8ZGyXpJMWBYIUKzMeO9al7mpNLPFShpfKcIgIWvq\r\n" + 
			"8uo8sqECgYEA95t4EvaAB+2VE6SAWSLhUphn0r4qu3OsDuq8JK5Txc95SwSawFbd\r\n" + 
			"LAw5VoUxpmlTumi9GLTZO+MSuCtStQQ/28EVuwkmoXvaJNS4LZ9lUYNTICm1sD9q\r\n" + 
			"iHWKvJgMWVlkbWURMf0QcCSNkVfnIGPXwzYNsN+TuwipUzNEEObbCmkCgYEA3YxK\r\n" + 
			"+wDnbjsSNUv9Ep6LLMQASIQpjfzLU3p8Mvvdf7Q41c9VBr37a7Y6CGMMn6fFl8Dm\r\n" + 
			"ObJE45o6BIqxlTMyWKlfmo/tudMUGwB1ja115boA5nElPh9PWs1O4hGILRdoXZTn\r\n" + 
			"gFzoTw7S0DVFyOYKYC7VtsGV2+uasPVbS/5Y8tMCgYEA1bikE/og2LcBjkrXXhj1\r\n" + 
			"VE2okAcE63gUc2u5qg+uz3cdwtremFZh4btLYprlGDahxqzRS5oOR9rgNYdvhD9+\r\n" + 
			"vzgSgbaMWaaFzHSv5PFIiutHvONDqDWUxuRuNAsp3ZWDUcmC5hsBLUrfxyyQmyyS\r\n" + 
			"+DSX7KuO4EnBnccKYWxidMECgYAvKVFA03VwmrSKke2CjCt2URb6WN/7sMebsqRD\r\n" + 
			"uifxqmy63xLrh3/A8x6xcFxclDtUzaR0vcU6ajdVhiy9ZROfmhaW8XKarxtFkDh1\r\n" + 
			"+2nwiELuy0ExUzrySmgzaaYCa0w1N6B8S8rMIYyw89YyKP4mcXHfIKCFG0dnyiMw\r\n" + 
			"LynCLwKBgQCubZS8X9CaojdLWyENmhjw8wWYXwwuiFYikwoXU9s/soZADdPjEINp\r\n" + 
			"yBcpp2DzTuIMll+UjXcRxkNjtgaBxtBXxazDwkmDYgMFex1AfRkAWQtojF69xm+Q\r\n" + 
			"AAM5B9HJPb+BfCCSvQPMxep8tWSBui8rgySzXPipQxdSHFUiUsr33A==\r\n" + 
			"-----END RSA PRIVATE KEY-----";

	private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1kjoM8AvYpxql6m7GVY2\r\n" + 
			"w9wlU/RtthFqQ0ftjYnLDUUR0StkCemuprGIMRaiwc5/GwYSd4kswkl/Mofd0Dei\r\n" + 
			"IAChP7aqijYiThAUqqC5t8IugF78p073VDcXYQyXvUtPef2lG/BT4+NiRK4eiXFd\r\n" + 
			"8IIf5CFXhI8GYMLFLtyBhOWJyyfaRMJ38XDDsD3cG1Ef8JD6oHSuyQP5Cyio2XIv\r\n" + 
			"FqgImPZuJHQD3xNJnSIvQHCR68MpXUSloDz3Q/3iEj5vhU0cntH/Xu6wYeKOUC46\r\n" + 
			"pBggy18ItReu3DnJQ+plvGIvYYEfoBApTIQvXgx+njs6EBYFit9Zy1TZ0iOMGHjW\r\n" + 
			"iwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter tokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);

		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenConverter());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(tokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(props.getClientId()).secret(bcrypt.encode(props.getClientSecret())).scopes("read", "write")
				.authorizedGrantTypes(props.getGrantTypePassword(), props.getGrantTypeClientCredentials(), "refresh_token").accessTokenValiditySeconds(props.getTokenValidity())
				.refreshTokenValiditySeconds(props.getTokenValidity());
	}
}