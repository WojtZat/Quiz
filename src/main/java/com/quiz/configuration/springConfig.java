package com.quiz.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.quiz.DAO.DatabaseImpl;
import com.quiz.DAO.MemoryImpl;
import com.quiz.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan("com.quiz")
@PropertySource({"classpath:persistence.properties"})
public class springConfig {

    @Autowired
    private Environment env;

    @Bean
    public DatabaseImpl databaseQuiz() {
        return new DatabaseImpl();
    }

    @Bean
    public MemoryImpl memoryQuiz() {
        return new MemoryImpl(questionList());
    }

    @Bean
    public ObservableList<Question> questionList() {
        return FXCollections.observableArrayList();
    }

    @Bean
    public DataSource myDataSource() {
        ComboPooledDataSource myDataSource = new ComboPooledDataSource();
        try {
            myDataSource.setDriverClass("org.sqlite.JDBC");
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }
        myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));


        return myDataSource;
    }

    private int getIntProperty(String propName) {
        String propVal = env.getProperty(propName);
        int intPropVal = Integer.parseInt(propVal);
        return intPropVal;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("javax.persistence.schema-generation.database.action", "create");
        properties.setProperty("javax.persistence.schema-generation.create-source", "script");
        properties.setProperty("javax.persistence.schema-generation.create-script-source", "import.sql");
        properties.setProperty("hibernate.current_session_context_class", "thread");

        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(myDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }
}
