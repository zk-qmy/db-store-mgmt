package orders;

import app.DatabaseConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
    /*private Connection connection;

    public OrdersDAO() {
        System.out.println("OrderDAO created!!!");
    }*/
    // Load Orders
    //// Load all
    public List<Orders> loadAllOrders(){
        // DEBUG
        System.out.println("got into orderDAO.loadAllOrders!!!");
        List<Orders> orderList = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        Connection conn = null;
        try {
            conn = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getInt("id"));
                order.setCusID(rs.getInt("customerID"));
                order.setStatus(rs.getString("status"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(conn);
        }
        return orderList;
    }
    // Save an Order
    // TO DO: double check


    // Find an order by ID
    public Orders findOrderByID(int orderID) {
        Connection connection = null;
        Orders order = null;
        try {
            String query1 = "SELECT * FROM Orders WHERE id = "+orderID;
            connection = DatabaseConn.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            if (rs.next()) {
                order = new Orders();
                order.setOrderId(rs.getInt("id"));
                //order.setCusID(rs.getString("name"));
                order.setStatus(rs.getString("status"));
                rs.close();
                stmt.close();
            }
            String query2= "SELECT * FROM OrderDetails WHERE orderID = "+orderID;
            rs = stmt.executeQuery(query2);

            while (rs.next()) {
                OrderDetails orderLine = new OrderDetails();
                orderLine.setOrderID(rs.getInt(1));
                orderLine.setProductID(rs.getInt(2));
                orderLine.setOrderQuantity(rs.getInt(3));
                orderLine.setCost(rs.getDouble(4));
                order.addLine(orderLine);
            }
        } catch (SQLException e) {
            System.out.println("Error here!!1");
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return order;
    }

    //TO DO: Update order (for admin - associate with updateTransaction

    // TO DO: Delete order(for admin - associated with updateTransaction

    // TO DO: Remove Order(line) for customer - have not figure out how to do it
}

