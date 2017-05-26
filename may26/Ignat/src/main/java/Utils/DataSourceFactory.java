package Utils;

/**
 * Created by eku on 19.04.17.
 */

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DataSourceFactory {


    private static DataSource datasource = new DataSource();
    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost/students?useSSL=false");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("pass");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(300000);
        p.setTimeBetweenEvictionRunsMillis(300000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(100000);
        p.setRemoveAbandonedTimeout(600);
        p.setMinEvictableIdleTimeMillis(300000);
        p.setMinIdle(100);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        datasource.setPoolProperties(p);
    }

    /**
     * Get an object DataSource for connection to DB
     * @return dataSource
     */
    public static DataSource getDataSource() {
        return datasource;
    }

}