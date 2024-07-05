import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import org.mindrot.jbcrypt.BCrypt;
//import  org.mindrot.jbcrypt;

public class AuthService {
    private static Connection connection = DatabaseConnection.getConnection();


    public static boolean register(String username, String password, String email) {

        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean authenticate(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                return checkPassword(password, storedHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean checkPassword(String password, String storedPassword) {
        return password.equals(storedPassword);

} }