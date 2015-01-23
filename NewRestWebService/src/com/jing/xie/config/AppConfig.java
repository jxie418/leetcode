package com.jing.xie.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.jing.xie.hb","com.jing.xie" })
@EnableTransactionManagement

public class AppConfig {

  @Bean
  public SessionFactory sessionFactory() {
    LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
    builder.scanPackages("com.jing.xie.jpa","com.jing.xie.hb").addProperties(getHibernateProperties());
    return builder.buildSessionFactory();
  }

  private Properties getHibernateProperties() {
    Properties prop = new Properties();
    prop.put("hibernate.format_sql", "true");
    prop.put("hibernate.show_sql", "true");
    prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    return prop;
  }

  @Bean(name = "dataSource")
  public BasicDataSource dataSource() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
    ds.setUrl("jdbc:derby://localhost:1527/db1;create=true");
    ds.setUsername("user1");
    ds.setPassword("user1");
    return ds;
  }

  // Create a transaction manager
  @Bean
  public HibernateTransactionManager txManager() {
    return new HibernateTransactionManager(sessionFactory());
  }

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/pages/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

}
