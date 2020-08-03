package com.springboot.mvc;

import java.util.Properties;
import com.springboot.mvc.models.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.PropertySource;

// no need getter and setter to make it work:

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:db.properties")
public class HibernateUtil {

  private SessionFactory sessionFactory;

  @Value("${db.DB_DRIVER_CLASS}")
  private String DB_DRIVER_CLASS;

  @Value("${db.DB_URL}")
  private String DB_URL;

  @Value("${db.DB_USERNAME}")
  private String DB_USERNAME;

  @Value("${db.DB_PASSWORD}")
  private String DB_PASSWORD;

  @Value("${db.DB_DIALECT}")
  private String DB_DIALECT;

  @Value("${db.DB_SHOW_SQL}")
  private String DB_SHOW_SQL;

  @Value("${db.DB_CURRENT_SESSION_CONTEXT_CLASS}")
  private String DB_CURRENT_SESSION_CONTEXT_CLASS;

  @Value("${db.DB_HBM2DDL_AUTO}")
  private String DB_HBM2DDL_AUTO;

  @Bean
  public SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();
        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Properties settings = new Properties();

        settings.put(Environment.DRIVER, DB_DRIVER_CLASS);
        settings.put(Environment.URL, DB_URL);
        settings.put(Environment.USER, DB_USERNAME);
        settings.put(Environment.PASS, DB_PASSWORD);
        settings.put(Environment.DIALECT, DB_DIALECT);
        settings.put(Environment.SHOW_SQL, DB_SHOW_SQL);
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, DB_CURRENT_SESSION_CONTEXT_CLASS);
        settings.put(Environment.HBM2DDL_AUTO, DB_HBM2DDL_AUTO);

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Customer.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return sessionFactory;

  }

}