package customer.cart;

import app.DatabaseConn;
import customer.cart.CartLines;

import javax.xml.transform.Result;
import java.sql.*;

public class CartDAO {
    private Connection connection;

    public CartDAO() {

    }

    // loadCart
    public Cart loadCart(int cartID) {
        connection = null;
        Cart cart = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String query1 = "SELECT * FROM Cart";
            ResultSet rs = stmt.executeQuery(query1);
            if (rs.next()) {
                cart = new Cart();
                cart.setCartID(rs.getInt("CartID"));
                rs.close();
                stmt.close();
            }
            String query2 = "SELECT * FROM CartLines WHERE cartID = " + cartID;
            // Load cart lines for the cart
            rs = stmt.executeQuery(query2);
            while (rs.next()) {
                CartLines line = new CartLines();
                line.setCartID(rs.getInt(1));
                line.setProductID(rs.getInt(2));
                line.setOrderQuantity(rs.getInt(3));
                line.setCost(rs.getDouble(4));
            }
            return cart;
        } catch (SQLException e) {
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
    }
}
    /*
    // addToCart
    public boolean addToCart(Cart cart){
        System.out.println("Got into addToCart function!!!");
        connection = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Cart VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, cart.getCartID());
            //stmt.setInt(2, cart.getCusID());

            stmt.execute();
            stmt.close();
            stmt = connection.prepareStatement("INSERT INTO CartLines VALUES (?, ?, ?, ?)");
            for (CartLines line: cart.getLines()) {
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
        }finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
     */

    /*// TO DO: RemoveFromCart (clear a product/cartline by id in cart)
    // Clear cart from CartItem and Cart tables
    public void clearCart(int cartId) throws SQLException {
        String deleteItemsQuery = "DELETE FROM CartItem WHERE cart_id = ?";
        PreparedStatement stmt = connection.prepareStatement(deleteItemsQuery);
        stmt.setInt(1, cartId);
        stmt.executeUpdate();

        String deleteCartQuery = "DELETE FROM Cart WHERE cart_id = ?";
        stmt = connection.prepareStatement(deleteCartQuery);
        stmt.setInt(1, cartId);
        stmt.executeUpdate();
    }*/
    // TO DO: ClearCart(clear all products in cart)


    /*// TO DO: Placing order: Transfer cart items to order details
    public void placeOrder(int customerId) throws SQLException {
        int cartId = getCartIdForCustomer(customerId);

        // 1. Create a new order
        int orderId = createOrder(customerId);

        // 2. Move items from CartItem to OrderDetails
        String query = "INSERT INTO OrderDetails (order_id, product_id, quantity, price) " +
                "SELECT ?, product_id, quantity, price FROM CartItem WHERE cart_id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, orderId);
        stmt.setInt(2, cartId);
        stmt.executeUpdate();

        // 3. Clear the cart
        clearCart(cartId);
    }*/

