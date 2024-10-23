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

        this.ordersDAO = ordersDAO;
        this.orderDetailsDAO = orderDetailsDAO;
        loadProductList();
        //int productID = 0;
        view.getBtnFind().addActionListener(this);
        view.getBtnBuy().addActionListener(this);
        view.getBtnOrderHis().addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnFind()) {
            findProduct();
        } else if (e.getSource() == view.getBtnBuy()) {
            //open new checkout screen
            App.getInstance().getCartView().setVisible(true);
        } else if (e.getSource() == view.getBtnOrderHis()) {
            App.getInstance().getOrderHisView().setVisible(true);
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
        Products product = productDAO.findProductbyID(productID);
        view.displaySelectedProduct(product);
    }


    public void loadProductList() {
        List<Products> productsList = productDAO.loadAllProducts();
        view.displayProducts(productsList);
    }

}
