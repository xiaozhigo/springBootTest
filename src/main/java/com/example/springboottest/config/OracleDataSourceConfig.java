package com.example.springboottest.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.xa.client.OracleXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @author wulei
 * @date 2019-02-13 10:28
 */
@Configuration
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleDataSourceConfig {

    static final String PACKAGE = "com.example.springboottest.dao";
    private static final String MAPPER_LOCATION = "classpath:mapper/oracle/*.xml";

    @Value("${spring.datasource.oracle.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.oracle.url}")
    private String url;

    @Value("${spring.datasource.oracle.username}")
    private String username;

    @Value("${spring.datasource.oracle.password}")
    private String password;

    @Value("${spring.datasource.oracle.uniqueResourceName}")
    private String uniqueResourceName;


    /*@Bean(name = "oracleDataSource")
    public DataSource erpDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }*/

    @Bean(name = "oracleDataSource")
    public DataSource erpDataSource() throws SQLException {
        OracleXADataSource source = new OracleXADataSource();
        source.setURL(url);
        source.setPassword(password);
        source.setUser(username);

        //注册到全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(source);
        xaDataSource.setUniqueResourceName(uniqueResourceName);
        return xaDataSource;
    }



    @Bean(name="oracleTransactionManager")
    public DataSourceTransactionManager oracleTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(erpDataSource());
    }


    @Bean(name="oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(OracleDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}

