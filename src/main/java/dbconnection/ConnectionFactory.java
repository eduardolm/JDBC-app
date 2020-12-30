package dbconnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    public String buildConnectionString() throws IOException {

        InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties");
        Properties prop = new Properties();
        prop.load(input);

        String driver = prop.getProperty("jdbc.driver");
        String dbAddress = prop.getProperty("db.address");
        String dbName = prop.getProperty("db.name");

        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dbAddress).append("/")
                .append(dbName);

        return sb.toString();
    }

    public Connection connectToDb(String connectionString) throws IOException {

        InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties");
        Properties prop = new Properties();
        prop.load(input);

        Connection connection = null;
        String dbUser = prop.getProperty("db.user");
        String dbPassword = prop.getProperty("db.password");

        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
            System.out.println("SUCESSO!");
        }
        catch (Exception ex) {
            System.out.println("FALHA: " + ex);
        }
        return connection;
    }
}
