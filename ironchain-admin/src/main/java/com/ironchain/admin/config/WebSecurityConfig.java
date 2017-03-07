package com.ironchain.admin.config;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * WEB 安全配置
 * @author Administrator
 *
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * 加密算法
	 * @return
	 */
	@Bean  
    public PasswordEncoder passwordEncoder(){  
//        PasswordEncoder encoder = new BCryptPasswordEncoder(); 最安全， 耗时
        PasswordEncoder encoder = new StandardPasswordEncoder();//sha 256 salt
        return encoder;
    }  
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()//定义那些url需要保护
                .antMatchers("/", "/home").permitAll()//指定/ 和 /home 不需要保护
                .anyRequest().authenticated()//其他url全部需要保护
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		try {
//            auth
//                .userDetailsService(userDetailsService)
//                    .passwordEncoder(passwordEncoder());
//        } catch (Exception e) {
//            throw new BeanInitializationException("Security configuration failed", e);
//        }
		 auth
         .inMemoryAuthentication()
             .withUser("user").password("password").roles("USER");
    }

}
