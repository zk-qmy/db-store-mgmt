package products;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import app.DatabaseConn;

import javax.swing.plaf.nimbus.State;
import javax.xml.crypto.Data;


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
    public boolean addProductToDB(String productName, int quantity, double price, int categID){
        Connection connection= null;
        try{
            connection = DatabaseConn.getInstance().getConnection();
            String query = "INSERT INTO Products(name, quantity, price, categoryid) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productName);
            statement.setInt(2, quantity);
            statement.setDouble(3, price);
            statement.setInt(4, categID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return true;

    }

    // Update
    public boolean updateProductToDB(int productID, String productName, int quantity, double price, int categID) {
        Connection connection= null;
        try{
            connection = DatabaseConn.getInstance().getConnection();
            String query = "UPDATE Products SET name = ?, quantity = ?, price = ?, categoryid = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productName);
            statement.setInt(2, quantity);
            statement.setDouble(3, price);
            statement.setInt(4, categID);
            statement.setInt(5, productID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return true;
    }

    // delete
    public boolean deleteProductFromDB(int productID) {
        Connection connection = null;
        try {
            connection = DatabaseConn.getInstance().getConnection();
            String query = "DELETE FROM Products WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productID);

            int rowsDeleted = statement.executeUpdate();

            // Check if any rows were deleted
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully: ID = " + productID);
                return true;
            } else {
                System.out.println("No product found with ID: " + productID);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
    }

    // Get product id key
    public List<Integer> getProductKeyID() {
        Connection connection = null;
        List<Integer> productKeys = new ArrayList<>();
        try {
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT id FROM Products");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                //Products product = new Products();
                //product.setProductID(rs.getInt("id"));
                productKeys.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return productKeys;
    }
    // Get all category tags
    public List<String> getAllCategs(){
        List<String> categories = new ArrayList<>();
        categories.add("Start filtering"); // set default(1st option) view of combobox
        Connection connection = null;
        try {
            connection= DatabaseConn.getInstance().getConnection();
            String query = "SELECT name FROM Categories";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                //int categID = rs.getInt("id");
                //String categName = rs.getString("name");
                categories.add(rs.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return categories;
    }

    // Get all Product with a certain categ name
    public List<Products> findProductsByCateg(String categName){
        Connection connection=null;
        List<Products> productsList = new ArrayList<>();
        try{
            connection = DatabaseConn.getInstance().getConnection();
            String query = "SELECT * FROM Products JOIN Categories ON Products.categoryID = Categories.id WHERE Categories.name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, categName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setProductID(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setStockQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                productsList.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return productsList;
    }

    // Update Stock of a product with id
    public void updateProductStock(int newStockQuantity, int productID){
        Connection connection = null;
        try {
            String query = "UPDATE Products SET quantity = ? WHERE id = ?";
            connection = DatabaseConn.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, newStockQuantity);
            statement.setInt(2, productID);
            // Execute the update query
            int rowsUpdated = statement.executeUpdate();

            // check if the update was successful
            if (rowsUpdated > 0) {
                System.out.println("Stock updated successfully for product ID: " + productID);
            } else {
                System.out.println("No product found with ID: " + productID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
    }
    // Get all product ID
    public List<Integer> getAllProductID(){
        Connection connection = null;
        List<Integer> productIDList = new ArrayList<>();
        try{
            connection = DatabaseConn.getInstance().getConnection();
            String query = "SELECT id FROM Products";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                productIDList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return productIDList;
    }

    // Get all categID
    public List<Integer> getAllCategID(){
        Connection connection = null;
        List<Integer> productIDList = new ArrayList<>();
        try{
            connection = DatabaseConn.getInstance().getConnection();
            String query = "SELECT id FROM Categories";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                productIDList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConn.getInstance().closeConn(connection);
        }
        return productIDList;
    }
}