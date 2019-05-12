package ro.rasel.service.eureka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import ro.rasel.security.client.resource.config.IResourceSecurityConfigurer;
import ro.rasel.security.client.sso.config.IWebSecurityConfigurer;

// implements both configurers so that the service works no matter which security service is loaded:
// - ResourceSecurityClientComponent.class to be called as proxy
// - WebSecurityClientComponent.class for direct authentication and authorization
@Configuration
public class SecurityConfigurer implements IResourceSecurityConfigurer, IWebSecurityConfigurer {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/eureka/**").anonymous()
                .antMatchers("/actuator/**").hasRole("ACTUATOR")
                .anyRequest().authenticated();
        // @formatter:on
    }
}
