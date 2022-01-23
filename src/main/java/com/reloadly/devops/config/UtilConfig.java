package com.reloadly.devops.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reloadly.devops.utilities.AuthProperties;
import com.reloadly.devops.utilities.DatasourceProperties;
import com.reloadly.devops.utilities.EncryptUtil;


/*This class is for extra configurations*/

@Configuration
public class UtilConfig {
	@Autowired
	private AuthProperties prop;
	@Autowired
	private EncryptUtil encryptUtil;
	@Autowired
	DatasourceProperties datasourceProperties;
	
//	Configure encryption
	@Bean
	public EncryptUtil encryptUtil() {
		return new EncryptUtil(prop.getEncryptKey());
	}
	
//	Configure datasource
	@Bean
	@SuppressWarnings("rawtypes")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(datasourceProperties.getDriver());
        dataSourceBuilder.url(datasourceProperties.getUrl());
        dataSourceBuilder.username(encryptUtil.decryptStringEncoded(datasourceProperties.getUsername()));
        dataSourceBuilder.password(encryptUtil.decryptStringEncoded(datasourceProperties.getPassword()));
        
        return dataSourceBuilder.build();
    }
}
