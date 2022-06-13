package com.hisu.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hisu")
@EnableTransactionManagement
@PropertySource({"classpath:database.properties"})
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;

	@Bean
	public DataSource myDataSource() {
		
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();
		
		try {
			myDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		myDataSource.setUser(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));
		
		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return myDataSource;
	}
	
	private int getIntProperty(String propName) {
		return Integer.parseInt(env.getProperty(propName));
	}
	
	private Properties getHibernateProperties() {
		Properties props = new Properties();
		
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		
		sessionFactoryBean.setDataSource(myDataSource());;
		
		sessionFactoryBean.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		
		return sessionFactoryBean;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		
		txManager.setSessionFactory(sessionFactory);
		
		return txManager;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}