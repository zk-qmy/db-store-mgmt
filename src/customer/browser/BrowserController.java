package customer.browser;
import app.App;
import customer.cart.CartView;
import orders.OrderDetailsDAO;
import orders.OrdersDAO;
import products.ProductsDAO;
import products.Products;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import customer.orderhistory.OrderHisView;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class BrowserController implements ActionListener {
    private ProductsDAO productDAO;
    private final BrowserView view;
    private OrdersDAO ordersDAO;
    private OrderDetailsDAO orderDetailsDAO;

    public BrowserController(BrowserView view, ProductsDAO productDAO, OrdersDAO ordersDAO, OrderDetailsDAO orderDetailsDAO) {
        this.productDAO = productDAO;
        this.view = view;
        System.out.println("browser controller created!");
        this.ordersDAO = ordersDAO;
        this.orderDetailsDAO = orderDetailsDAO;
        loadProductList();
        loadCategTags();
        //int productID = 0;
        view.getBtnFind().addActionListener(this);
        view.getBtnBrowse().addActionListener(this);
        view.getBtnBuy().addActionListener(this);
        view.getBtnOrderHis().addActionListener(this);
        view.getBtnLogOut().addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnFind()) {
            System.out.println("find product button triggered!");
            findProduct();
        }else if (e.getSource() == view.getBtnBrowse()){
            loadProductList();
        }
        else if (e.getSource() == view.getBtnBuy()) {
            //open new checkout screen
            App.getInstance().getCartView().setVisible(true);
        } else if (e.getSource() == view.getBtnOrderHis()) {
            App.getInstance().getOrderHisView().setVisible(true);
        } else if (e.getSource() == view.getBtnLogOut()) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                view.dispose();
                App.getInstance().getHomeScreen().setVisible(true);
            }

        }
    }
    private CartView cartView = new CartView();

    public CartView getCartView() {
        return cartView;
    }

    private OrderHisView orderHisView = new OrderHisView(ordersDAO, orderDetailsDAO);

    public OrderHisView getOrderHisView() {
        return orderHisView;
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
        List<Integer> productKeys = productDAO.getProductKeyID();
        if (productKeys.contains(productID)) {
            Products product = productDAO.findProductbyID(productID);
            view.displaySelectedProduct(product);
        } else {
            JOptionPane.showMessageDialog(null,"Product ID does not exist!");
        }
    }


    public void loadProductList() {
        List<Products> productsList = productDAO.loadAllProducts();
        view.displayProducts(productsList);
    }

    public void loadCategTags() {
        List<String> categories = productDAO.getAllCategs(); // Implement this method in your DAO
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
        List<Products> productsList = productDAO.findProductsByCateg(categoryName);
        view.displayProducts(productsList);
    }

}
