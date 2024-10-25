package admin.productsmgmt;

import orders.OrderDetailsDAO;
import orders.OrdersDAO;
import products.ProductsDAO;
import products.Products;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;

public class AdProductsController implements ActionListener{
    private ProductsDAO productsDAO;
    private final AdProductsView view;
    //private OrdersDAO ordersDAO;
    //private OrderDetailsDAO ordersDetails;

    public AdProductsController(AdProductsView view, ProductsDAO productsDAO) {
        System.out.println("adproductcontroller created!!!!!");
        this.productsDAO = productsDAO;
        this.view = view;
        //this.ordersDAO = ordersDAO;
        //this.ordersDetails = orderDetailsDAO;

        loadProductList();
        loadCategTags();
        view.getBtnAdd().addActionListener(this);
        view.getBtnDelete().addActionListener(this);
        view.getBtnBrowse().addActionListener(this);
        view.getBtnUpdate().addActionListener(this);
        view.getBtnFind().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnFind()) {
            System.out.println("find in admin triggered");
            findProduct();
        } else if (e.getSource() == view.getBtnAdd()) {
            System.out.println("add in admin triggered");
            boolean add = true;
            addOrupdateProduct(add);
        } else if (e.getSource() == view.getBtnDelete()) {
            System.out.println("delete in admin triggered");
            deleteProduct();
        } else if (e.getSource() == view.getBtnUpdate()) {
            System.out.println("update in admin triggered");
            boolean add= false;
            addOrupdateProduct(add);
        } else if (e.getSource() == view.getBtnBrowse()) {
            System.out.println("browse in admin triggered");
            loadProductList();
        }
    }
    public void findProduct() {
        int productID = 0;
        String id = JOptionPane.showInputDialog("Enter ProductID: ");
        try {
            productID = Integer.parseInt(id);
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID!");
            return;
        }
        List<Integer> productKeys = productsDAO.getProductKeyID();
        if (productKeys.contains(productID)) {
            Products product = productsDAO.findProductbyID(productID);
            view.displaySelectedProduct(product);
        } else {
            JOptionPane.showMessageDialog(null,"Product ID does not exist!");
        }
    }
    public void loadProductList() {
        List<Products> productsList = productsDAO.loadAllProducts();
        view.displayProducts(productsList);
    }

    public void loadCategTags() {
        List<String> categories = productsDAO.getAllCategs(); // Implement this method in your DAO
        // Clear existing items
        view.getCategoryComboBox().removeAllItems();

        for (String category : categories) {
            view.getCategoryComboBox().addItem(category);
        }

        // Action listener for category selection
        view.getCategoryComboBox().addActionListener(e -> {
            String selectedCategory = (String) view.getCategoryComboBox().getSelectedItem();
            if (selectedCategory != null) {
                loadProductsByCateg(selectedCategory);
            }
        });
    }

    public void loadProductsByCateg(String categoryName) {
        List<Products> productsList = productsDAO.findProductsByCateg(categoryName);
        view.displayProducts(productsList);
    }

    public void addOrupdateProduct(boolean add) {
        String productName = JOptionPane.showInputDialog("Enter Product Name: ");
        if (productName==null || productName.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Invalid Name");
            return;
        }

        int quantity = 0;
        String stockQuantity = JOptionPane.showInputDialog("Enter stock quantity: ");
        try {
            quantity = Integer.parseInt(stockQuantity);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid quantity!");
            return;
        }
        double price = 0;
        String priceString =JOptionPane.showInputDialog("Enter price: ");
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid price!");
            return;
        }
       int categoryID = 0;
        String categID = JOptionPane.showInputDialog("Enter category id: ");
        try {
            categoryID = Integer.parseInt(categID);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid category id!");
            return;
        }
        // logic to verify categoryid

        // Navigate task
        boolean success;
        if (add){
            success = productsDAO.addProductToDB(productName,quantity,price,categoryID);
            confirmProcess(success, "add");
        } else {
            int productID = 0;
            String id = JOptionPane.showInputDialog("Enter Product ID: ");
            try {
                productID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid product id!");
                return;
            }
            success = productsDAO.updateProductToDB(productID, productName,quantity,price,categoryID);
            confirmProcess(success, "update");
        }
        loadProductList();
    }

    public void confirmProcess(boolean success, String task){
        // Confirm process
        if (success) {
            JOptionPane.showMessageDialog(null, "Product " + task + "d");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to "+ task +" product!");
            return;
        }
    }

    public void deleteProduct() {
        int productID = 0;
        String id = JOptionPane.showInputDialog("Enter product id: ");
        try {
            productID = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product id!");
            return;
        }
        boolean success = productsDAO.deleteProductFromDB(productID);
        if (success) {
            JOptionPane.showMessageDialog(null, "Product deleted!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete product!");
        }
        loadProductList();
    }

}