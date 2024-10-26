package register.users;

import app.DatabaseConn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    // Add user (ad + cus)
    public boolean addUser(String name, String username, String password, String address, String phone, int roleID) {
        Connection connection = null;
        try {
            String query = "INSERT INTO Users(username, password, name, address, phone, roleID) VALUES (?,?,?,?,?,?)";
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2,password);
            statement.setString(3, name);
            statement.setString(4, address);
            statement.setString(5,phone);
            statement.setInt(6, roleID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return true;
    }
    // Find user (ad)
    public Users loadUser(String username, String password) {
        Connection connection = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Users user = new Users();
                user.setUserID(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));
                user.setPhoneNum(resultSet.getString("phone"));
                user.setRoleID(resultSet.getInt("roleID"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }
    // Update user (ad)
    // delete user (ad)

    // Get all userID
    public List<Integer> getAllUserID() {
        List<Integer> userIDList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            String query = " SELECT id FROM Users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                userIDList.add(resultSet.getInt("id"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return userIDList;
    }

    // Get user by username
    public Users getUserByUsername(String username) {
        Connection connection = null;
        Users user = null;

        try {
            connection = DatabaseConn.getInstance().getConnection();
            String query1 = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.setString(1, username);

            // Debug: print the SQL query
            System.out.println("Executing query1: " + statement.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new Users();
                user.setUserID(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));
                user.setAddress(resultSet.getString("address"));
                user.setPhoneNum(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleID(resultSet.getInt("roleID"));
            }

            resultSet.close();
            statement.close();

            if (user != null) {
                String query2 = "SELECT roleName FROM Roles WHERE id = ?";
                PreparedStatement roleStatement = connection.prepareStatement(query2);
                roleStatement.setInt(1, user.getRoleID());  // Set the parameter here

                // Debug: print the SQL query
                System.out.println("Executing query2: " + roleStatement.toString());

                ResultSet roleResultSet = roleStatement.executeQuery();

                if (roleResultSet.next()) {
                    user.setRoleName(roleResultSet.getString("roleName"));
                }
                roleResultSet.close();
                roleStatement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }

        return user;
    }


}

