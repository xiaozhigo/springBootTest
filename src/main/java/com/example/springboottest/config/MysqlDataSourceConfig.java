package com.example.springboottest.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
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
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author wulei
 * @date 2019-02-13 10:52
 */
@Configuration
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataSourceConfig {

    static final String PACKAGE = "com.example.springboottest.mysql";
    private static final String MAPPER_LOCATION = "classpath:mapper/mysql/*.xml";

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.uniqueResourceName}")
    private String uniqueResourceName;

    /*@Bean(name = "mysqlDataSource")
    @Primary
    public DataSource erpDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }*/
    @Bean(name = "mysqlDataSource")
    @Primary
    public DataSource erpDataSource() throws SQLException {
        MysqlXADataSource dataSource = new MysqlXADataSource();
        dataSource.setUrl(url);
        dataSource.setPinGlobalTxToPhysicalConnection(true);
        dataSource.setPassword(password);
        dataSource.setUser(username);

        //注册到全局事务
        AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        sourceBean.setXaDataSource(dataSource);
        sourceBean.setUniqueResourceName(uniqueResourceName);
        return sourceBean;
    }

   /* @Bean(name="mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name="mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MysqlDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean("xaTransaction")
    public JtaTransactionManager jtaTransactionManager(){
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        UserTransactionManager manager = new UserTransactionManager();
        return new JtaTransactionManager(userTransactionImp,manager);
    }

}
