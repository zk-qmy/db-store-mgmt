package orders;

import app.DatabaseConn;
import customer.cart.Cart;
import customer.cart.CartLines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAO {
    private Connection connection;
    public OrderDetailsDAO() {

    }
    // Load all (OrderDetails)
    // Find an OrderDetail
    // Save an OrderDetail
    // addToCart
    public boolean addToOrderDB(Cart cart, String defaultStatus, int customerID) {
        System.out.println("Got into addToOrderDB function!!!");
        connection = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Orders VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, cart.getCartID());
            //stmt.setInt(2, cart.getCusID());

            stmt.execute();
            stmt.close();
            stmt = connection.prepareStatement("INSERT INTO OrderDetails VALUES (?, ?, ?, ?)");
            for (CartLines line : cart.getLines()) {
                stmt.setInt(2, line.getCartID());
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
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        // Remove product(an order) from Cart
    }
}
