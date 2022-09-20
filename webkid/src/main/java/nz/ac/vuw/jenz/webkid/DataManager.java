package nz.ac.vuw.jenz.webkid;

import java.sql.*;
import java.util.List;

public class DataManager {


    // there is no real database here but since this is static analysis this does not matter
    public static final String DB_URL = "jdbc:foo"; // not a real db
    public static Client getClient(String id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String sql = "SELECT id,firstName,name from CLIENTS where id=" + id;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                return new Client(
                    result.getString("id"),
                    result.getString("firstname"),
                    result.getString("name")
                );
            }
        } catch (SQLException x) {
            return null;
        }
        return null;
    }

    public static void importClients(List<Client> clients) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String sql = "INSERT INTO CLIENTS(id,firstname,name) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            for (Client client:clients) {
                statement.setString(1,client.getId());
                statement.setString(2,client.getFirstName());
                statement.setString(3,client.getName());
                statement.executeUpdate();
            }
        } catch (SQLException x) {} // ignore
    }
}
