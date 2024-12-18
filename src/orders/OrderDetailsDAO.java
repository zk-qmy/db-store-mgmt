package orders;

import app.DatabaseConn;
import customer.cart.Cart;
import customer.cart.CartLines;

import java.sql.*;

public class OrderDetailsDAO {
    private Connection connection;
    public OrderDetailsDAO() {

    }
    // Load all (OrderDetails)
    // Find an OrderDetail
    // Save an OrderDetail
    // addToCart
    public boolean addToOrderDB(Cart cart, int defaultStatusID, int customerID) {
        System.out.println("Got into addToOrderDB function!!!");
        connection = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Orders(customerID, statusID) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, customerID);
            stmt.setInt(2, defaultStatusID);

            stmt.executeUpdate();
            // Retrieve generated OrderID
            ResultSet generatedKey = stmt.getGeneratedKeys();
            int orderID = 0;
            if (generatedKey.next()) {
                orderID = generatedKey.getInt(1);
            }
            stmt.close();

            stmt = connection.prepareStatement("INSERT INTO OrderDetails(orderID, productID, orderQuantity) VALUES (?, ?, ?)");
            for (CartLines line : cart.getLines()) {
                stmt.setInt(1, orderID);
                stmt.setInt(2, line.getProductID());
                stmt.setInt(3, line.getOrderQuantity());
                //stmt.setDouble(5, line.getCost());

                stmt.executeUpdate();
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
