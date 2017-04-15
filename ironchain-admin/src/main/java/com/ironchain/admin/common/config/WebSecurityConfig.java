package com.ironchain.admin.common.config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ironchain.common.dao.SystemPermissionDao;
import com.ironchain.common.domain.SystemPermission;

/**
 * WEB 安全配置
 * @author Administrator
 * @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) 启用方法权限注解@PreAuthorize和@Secured
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(com.ironchain.admin.common.security.UserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SystemPermissionDao systemPermissionDao;
	
	@Value("site.security.noauthor")
	private String noauthor;
	/**
	 * 不需要安全认证的url
	 */
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            //.antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/app/**/*.{js,html}")
            .antMatchers("/bower_components/**")
            .antMatchers("/i18n/**")
            .antMatchers("/static/**")
            .antMatchers("/swagger-ui/index.html")
            .antMatchers("/test/**")
            .antMatchers("/h2-console/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http
            .formLogin()
                .loginPage("/system/user/login/form")
                .loginProcessingUrl("/system/user/login")
                .defaultSuccessUrl("/", true)//false 登录成功后跳转到登录前访问的页面
                .failureUrl("/system/user/login/form?error=true")
                .permitAll()
                .and()
            .headers()
            	.frameOptions()
            	.sameOrigin()
            	.and()
            .logout()
            	.logoutUrl("/system/user/logout")
                .permitAll()
                .and()
            .authorizeRequests();//定义那些url需要保护
		if("true".equals(noauthor)){
			List<SystemPermission> permissions = systemPermissionDao.findAll();
			//.antMatchers("/", "/home", "/system/**").permitAll()//指定/ 和 /home 不需要保护
			for (SystemPermission systemPermission : permissions) {
				if(StringUtils.isNotBlank(systemPermission.getUrl()))
					urlRegistry.antMatchers(systemPermission.getUrl().trim()).hasAuthority(systemPermission.getCode());
			}
		}
		urlRegistry.anyRequest().authenticated();//其他url全部需要保护
    }
	
	/**
	 * 加密算法
	 * @return
	 */
	@Bean  
    public PasswordEncoder passwordEncoder(){  
        PasswordEncoder encoder = new BCryptPasswordEncoder(); //最安全， 耗时
//        PasswordEncoder encoder = new StandardPasswordEncoder();//sha 256 salt
        return encoder;
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		try {
            auth
                .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }
	
	/**
	 * 使用之后能在DAO中通过SPEL获取当前登录用户
	 * @Query("select m from Message m where m.to.id = ?#{ principal?.id }")
	 */
//	@Bean
//    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//        return new SecurityEvaluationContextExtension();
//    }
	
}
