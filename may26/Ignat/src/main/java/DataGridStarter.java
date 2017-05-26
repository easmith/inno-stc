import Utils.DataSourceFactory;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by eku on 26.05.17.
 */
public class DataGridStarter {
    public static void main(String[] args) {
        Ignition.start();
        Ignite ignite = Ignition.ignite();
        final IgniteCache<Integer, String> igniteCache = ignite.createCache("Kotlin");
        Random random = new Random();

        final int maxStep = 1_000_000;
        final int maxRand = 1_000_000;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < maxStep; i++) {
            igniteCache.put(i, random.nextInt(maxRand) + "");
        }

        System.out.println("ignite insert time: " + (System.currentTimeMillis() - startTime));

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            startTime = System.currentTimeMillis();
            for (int i = 0; i < maxStep; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ignite_test (num, value) VALUES (?, ?)");
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, random.nextInt(maxRand) + "");
                preparedStatement.execute();
            }

            System.out.println("mysql insert time: " + (System.currentTimeMillis() - startTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        startTime = System.currentTimeMillis();
        BigInteger sumIgnite = new BigInteger("0");
        for (int i = 0; i < maxStep; i++) {
            sumIgnite.add(new BigInteger(igniteCache.get(i)));
        }
        System.out.println("ignite read time: "
                + (System.currentTimeMillis() - startTime)
                + " sum: " + sumIgnite);

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            startTime = System.currentTimeMillis();
            BigInteger sumDB = new BigInteger("0");
            for (int i = 0; i < maxStep; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT value FROM ignite_test WHERE num = ?");
                preparedStatement.setInt(1, i);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                sumDB.add(new BigInteger(resultSet.getString("value")));
            }

            System.out.println("mysql read time: "
                    + (System.currentTimeMillis() - startTime)
                    + " sum: " + sumDB);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Ignition.stop(true);
    }
}
