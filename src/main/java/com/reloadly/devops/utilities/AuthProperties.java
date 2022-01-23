package com.reloadly.devops.utilities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("authserve.prop")
public class AuthProperties {
	private String grantTypePassword;
	private String grantTypeClientCredentials;
	private String clientId;
	private String clientSecret;
	private String threadPrefix;
	private int tokenValidity;
	private int maxPoolSize;
	private int corePoolSize;
	private String encryptKey;
	private String logDir;
}
