package io.ziyi.spider.showmax.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.showmax.common.BaseComponent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

@Configuration
@EntityScan("io.ziyi.spider.showmax.model")
@EnableTransactionManagement
public class DatabaseConfiguration extends BaseComponent {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    protected HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(name = "dataSource")
    public DataSource createDataSource(@Autowired HikariConfig config) {
        String jdbcUrl = ConfigTools3.getString("spider.showmax.database.url");
        String username = ConfigTools3.getString("spider.showmax.database.username");
        String password = ConfigTools3.getString("spider.showmax.database.password");

        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        HikariDataSource ds = new HikariDataSource(config);
        logger.info("Database", "Data source created.");
        return ds;
    }

    @Primary
    @Bean(name = "sessionFactory")
    public FactoryBean<SessionFactory> createSessionFactory(@Autowired DataSource dataSource) {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan("io.ziyi.spider.showmax.model");
        bean.setHibernateProperties(getHibernateProperties());
        logger.info("Database", "Session factory created.");
        return bean;
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager createTransactionManager(@Autowired SessionFactory sessionFactory) {
        int txTimeout = ConfigTools3.getInt("spider.showmax.database.tx-timeout-seconds", 10);
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        transactionManager.setDefaultTimeout(txTimeout);
        transactionManager.setTransactionSynchronization(1);
        logger.info("Database", "Transaction manager created.");
        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties props = new Properties();
        props.put("hibernate.dialect", ConfigTools3.getString("spider.showmax.database.hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect"));
        props.put("hibernate.show_sql", ConfigTools3.getBoolean("spider.showmax.database.show-sql", false));
        props.put("hibernate.format_sql", false);
        props.put("hibernate.generate_statistics", false);
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.auto_quote_keyword", true);
        //props.put("hibernate.globally_quoted_identifiers", true);
        props.put("hibernate.dialect.storage_engine", "innodb");
        props.put("hibernate.connection.isolation", Connection.TRANSACTION_READ_COMMITTED);
        props.put("hibernate.use_sql_comments", true);
        props.put("hibernate.cache.use_second_level_cache", false);
        props.put("hibernate.cache.use_query_cache", false);
        props.put("hibernate.connection.CharSet", "utf8");
        props.put("hibernate.connection.characterEncoding", "utf8");
        props.put("hibernate.connection.useUnicode", true);
        props.put("hibernate.autoReconnect", true);
        return props;
    }
}
