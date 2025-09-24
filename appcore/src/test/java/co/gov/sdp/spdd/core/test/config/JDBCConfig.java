package co.gov.sdp.spdd.core.test.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"co.gov.sdp.spdd.data"})
@EnableTransactionManagement
public class JDBCConfig {

    public static final String DATABASE_URL = "jdbc:oracle:thin:@10.5.49.2:1521:ORCL";
    public static final String DATABASE_USER = "SPDD";
    public static final String DATABASE_PASSWORD = "wW1k7O8LrL9";
    public static final String DATABASE_DRIVER = "oracle.jdbc.OracleDriver";
    public static final String DATABASE_JNDI = "java:/JDBC/SPDDDS";

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USER);
        dataSource.setPassword(DATABASE_PASSWORD);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"co.gov.sdp.spdd"});
        em.setJpaVendorAdapter(jpaVendorAdapter);
        return em;
    }

}
