package com.mahaboob.currencyconverter.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-currconverter";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable()
	        .authorizeRequests().antMatchers("/oauth/token").permitAll()
	        .and()
	        .authorizeRequests().antMatchers("/secured/**")
	        .access("#oauth2.hasScope('read') and #oauth2.hasScope('write') and hasAnyAuthority('admin','user')")
	        .and()
	        .authorizeRequests().antMatchers("/admin/**")
	        .access("#oauth2.hasScope('read') and #oauth2.hasScope('write') and hasAuthority('admin')")
        	.and()
            .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
    
}