package peaksoft.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.entities.Company;
import peaksoft.entities.Address;
import peaksoft.entities.Programmer;
import peaksoft.entities.Project;

import java.util.Properties;

/**
 * @author Mukhammed Asantegin
 */
public class DatabaseConnectionConfig {

    public static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(Environment.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
        properties.setProperty(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5434/postgres");
        properties.setProperty(Environment.JAKARTA_JDBC_USER, "postgres");
        properties.setProperty(Environment.JAKARTA_JDBC_PASSWORD, "yiman");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

        return properties;
    }

    public static EntityManagerFactory entityManagerFactory() {
        Configuration configuration = new Configuration();
        configuration.addProperties(properties());
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(Programmer.class);
        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }

    public static SessionFactory sessionFactory(){
        return entityManagerFactory().unwrap(SessionFactory.class);
    }


}
