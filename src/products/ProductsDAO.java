package products;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import app.DatabaseConn;


public class ProductsDAO {
    // Load
    //// Load all
    public List<Products> loadAllProducts(){
        List<Products> productsList = new ArrayList<>();
        String query = "SELECT * FROM Products";
        Connection conn = null;
        try {
            conn = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Products product = new Products();
                product.setProductID(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("quantity"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(conn);
        }
        return productsList;
    }
    //// Load by ID
    public Products findProductbyID(int id) {
        String query = "SELECT * FROM Products WHERE id = ?";
        Connection connection = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Products product = null;
            if (rs.next()) {
                product = new Products();
                product.setProductID(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setStockQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
            }
            DatabaseConn.getInstance().closeConn(connection);
            return product;
        } catch (SQLException e) {
            System.out.println("DB access error");
            e.printStackTrace();
        }
        return null;
    }


    // Add
    //Update
    // delete
}