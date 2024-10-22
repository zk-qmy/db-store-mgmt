package orders;

import app.DatabaseConn;

import java.sql.Connection;

import orders.OrderDetails;
import orders.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDAO {
    private Connection connection;

    public OrdersDAO() throws SQLException {

    }
    // Load Orders
    // Save an Order
    // TO DO: double check
    public boolean addToCart(Orders order) throws SQLException {
        connection = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Orders VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, order.getId());
            stmt.setInt(2, order.getCusID());
            //stmt.setString(3, order.getStatus());

            stmt.execute();
            stmt.close();
            stmt = connection.prepareStatement("INSERT INTO OrderDetails VALUES (?, ?, ?, ?)");
            for (OrderDetails line: order.getLines()) {
                stmt.setInt(2, line.getOrderID());
                stmt.setInt(3, line.getProductID());
                stmt.setInt(4, line.getOrderQuantity());
                stmt.setDouble(5, line.getCost());

                stmt.execute();
            }
            stmt.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
    }
    // Find an order

}

