package apap.tugaskelompok.sibusiness.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/jss/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/api-docs").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/cabang/**").hasAuthority("Manager Business")
                .antMatchers("/user/add").hasAuthority("Manager Business")
                .antMatchers("/user/update/**").hasAuthority("Manager Business")
                .antMatchers("/coupon/delete/**").hasAuthority("Staff_Marketing")
                .antMatchers("/itemFactory/viewall").hasAuthority("Manager Business")
                .antMatchers("/accept/{id}").hasAuthority("Manager Business")
                .antMatchers("/reject/{id}").hasAuthority("Manager Business")
                .antMatchers("/mesin/list/**").hasAnyAuthority("Manager Business","Staff_Product")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public BCryptPasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("kijangSatu").password(encoder().encode("nasiGoreng"))
                .roles("Manager Business");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
