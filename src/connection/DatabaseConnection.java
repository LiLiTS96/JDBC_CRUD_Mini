package connection;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    //class field
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/data_base_java";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password", "");
        connection = DriverManager.getConnection(url, properties);
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if(connection != null && !connection.isClosed()){
            connection.close();
            connection = null;
        }
    }

    public static void executeDML(String sql) throws SQLException {
        //local variable
        Statement statement = null;
        try {
            if(connection == null) throw new SQLException();
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Wykonalo polecenie SQL");
        } catch (SQLException throwables) {
            System.out.println("Błąd podczas wykonania SQL polecenia");
            throwables.printStackTrace();
            throw throwables;
        }
        finally {
            if(statement!=null && !statement.isClosed()){
                statement.close();
            }
        }
    }

    public static ResultSet getData(String sql) throws SQLException{
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement= connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println("Pomyślnie pobrano dane...");
        } catch (SQLException throwables) {
            System.out.println("Problem z połączeniem z bazą danych...");
            throwables.printStackTrace();
        }

        return resultSet;
    }
}
