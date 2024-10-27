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
    // Load Orders
    //// Load all
    public List<Orders> loadAllOrders(){
        // DEBUG
        //System.out.println("got into orderDAO.loadAllOrders!!!");
        List<Orders> orderList = new ArrayList<>();
        String query = """
            WITH OrderTotals AS (
                SELECT
                    Orders.id AS orderID,
                    Orders.customerID,
                    Orders.status,
                    SUM(OrderDetails.orderQuantity * Products.price) AS orderTotal
                FROM Orders
                JOIN OrderDetails ON Orders.id = OrderDetails.orderID
                JOIN Products ON OrderDetails.productID = Products.id
                GROUP BY Orders.id, Orders.customerID
            )
            SELECT
                ot.orderID,
                ot.customerID,
                ot.orderTotal,
                ot.status,
                u.name AS name,
                u.address AS address,
                u.phone AS phone
            FROM OrderTotals ot
            JOIN Users u ON ot.customerID = u.id
            ORDER BY ot.orderID ASC;
        """;


        Connection conn = null;
        try {
            conn = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getInt("orderID"));
                order.setCusID(rs.getInt("customerID"));
                order.setCtmName(rs.getString("name"));
                order.setTotal(rs.getDouble("OrderTotal"));
                order.setStatus(rs.getString("status"));
                order.setAddress(rs.getString("address"));
                order.setPhone(rs.getString("phone"));


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
    // Save an Order (already in product DAO)
    // TO DO: check implement order history for a user


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
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return order;
    }

    //TO DO: Update order status(for admin - associate with updateTransaction

    // TO DO: Delete order(for admin - associated with updateTransaction
    public boolean deleteOrder(int orderID){
        Connection connection = null;
        try{
            String query1 = "DELETE FROM Orders WHERE id = ?";
            String query2 = "DELETE FROM OrderDetails WHERE orderID = ?";
            connection = DatabaseConn.getInstance().getConnection();

            // start transaction
            connection.setAutoCommit(false);

            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setInt(1,orderID);
            PreparedStatement statement2 = connection.prepareStatement((query2));
            statement2.setInt(1,orderID);
            statement2.executeUpdate();
            statement1.executeUpdate();

            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return true;
    }

    // TO DO: Remove Order(line) for customer - have not figure out how to do it

    // Get all orderID
    public List<Integer> getAllorderID(){
        Connection connection = null;
        List<Integer> orderIDList = new ArrayList<>();
        try{
            connection = DatabaseConn.getInstance().getConnection();
            String query = "SELECT id FROM Orders";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                orderIDList.add(resultSet.getInt("id"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return orderIDList;
    }

    // Load all order of a user
    public List<Orders> loadUserOrders (int userID){
        Connection connection = null;
        List<Orders> ordersList= new ArrayList<>();
        try{
            connection = DatabaseConn.getInstance().getConnection();
            String query = """
                WITH OrderTotals AS (
                    SELECT
                        Orders.id AS orderID,
                        Orders.customerID,
                        Orders.status,
                        SUM(OrderDetails.orderQuantity * Products.price) AS orderTotal
                    FROM Orders
                    JOIN OrderDetails ON Orders.id = OrderDetails.orderID
                    JOIN Products ON OrderDetails.productID = Products.id
                    GROUP BY Orders.id, Orders.customerID
                )
                SELECT
                    ot.orderID,
                    ot.orderTotal,
                    ot.status
                FROM OrderTotals ot
                WHERE ot.customerID = ?
                ORDER BY ot.orderID ASC;
            """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("orderID"));
                order.setTotal(resultSet.getDouble("orderTotal"));
                order.setStatus(resultSet.getString("status"));
                ordersList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return ordersList;
    }
}

