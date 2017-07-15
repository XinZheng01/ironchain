package com.ironchain.intfc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.alibaba.druid.pool.DruidDataSource;
import com.ironchain.common.base.BaseDaoImpl;
import com.ironchain.common.kits.SpringKit;
import com.ironchain.common.persistence.dialect.Dialect;
import com.ironchain.common.persistence.dialect.MySQLDialect;

/**
 * 持久化配置
 * @author Administrator
 * @EnableJpaAuditing 支持domain通过@CreatedDate @CreatedBy 注入创建人、创建时间等信息
 * @EntityScan 实体扫描，不在同一个工程中需要添加
 */
@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseDaoImpl.class, basePackages="com.ironchain.common.dao")
@EntityScan({"com.ironchain.common.domain"})
@EnableJpaAuditing
@Import({SpringKit.class})
public class DatabaseConfig {
	
	/**====== Druid Config Start =====*/
//    @Bean
//    public ServletRegistrationBean druidServlet() {
//        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//    }
 
    @Bean
    public DataSource druidDataSource(@Value("${spring.datasource.driver-class-name}") String driverClassName,
    		@Value("${spring.datasource.url}") String url,
    		@Value("${spring.datasource.username}") String username,
    		@Value("${spring.datasource.password}") String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
//        try {
//            druidDataSource.setFilters("stat, wall");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return druidDataSource;
    }
 
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setAsyncSupported(false);
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return filterRegistrationBean;
//    }
    /**====== Druid Config End =====*/
    
    /**
     * 分页方言
     */
    @Bean(name="pageDialect")
    public Dialect pageDialect(){
    	return new MySQLDialect();
    }
    
}