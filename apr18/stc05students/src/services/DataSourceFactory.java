package services;

import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;

/**
 * Produce Connection Pool for connect to DB
 *
 * @author Artem Panasyuk
 */
public class DataSourceFactory {

    /**
     * Get an object DataSource for connection to DB
     * @return dataSource
     */
    public static DataSource getMyPGDataSource() {
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName("SDB");
        source.setServerName("localhost");
        source.setDatabaseName("students");
        source.setUser("postgres");
        source.setPassword("123456");
        source.setMaxConnections(10);
        return source;
    }
}
