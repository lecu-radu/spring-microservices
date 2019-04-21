package ro.rasel.service.passport.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import ro.rasel.security.client.resource.IResourceSecurityConfigurer;

@Configuration
public class SecurityConfigurer implements IResourceSecurityConfigurer {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .regexMatcher("/users/.*/passport")
                .authorizeRequests()
                .regexMatchers("/users/.*").access("#oauth2.hasScope('openid')")
                .antMatchers("/actuator/**").hasRole("ACTUATOR")
                .anyRequest().authenticated();
    }
}
