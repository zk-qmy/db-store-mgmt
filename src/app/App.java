package app;

import customer.browser.BrowserController;
import customer.browser.BrowserView;
import customer.cart.CartController;
import customer.cart.CartDAO;
import customer.cart.CartView;
import customer.orderhistory.OrderHisController;
import customer.orderhistory.OrderHisView;
import orders.OrdersDAO;
import orders.OrderDetailsDAO;
import products.ProductsDAO;

public class App {
    private static App instance;
    // Main components
    /*private Connection connection;
    public Connection getConnection() {
       return connection;
    }*/

    // Orders
    private OrdersDAO ordersDAO = new OrdersDAO();
    // OrderDetails
    private OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
    //Order History
    private OrderHisView orderHisView = new OrderHisView(ordersDAO, orderDetailsDAO);
    public OrderHisView getOrderHisView() {
        return orderHisView;
    }
    private OrderHisController orderHisController;

    // Cart
    private CartView cartView = new CartView();
    public CartView getCartView() {
        return cartView;
    }
    private CartController cartController;
    private CartDAO cartDAO = new CartDAO();

    //Browser
    private ProductsDAO productsDAO = new ProductsDAO();
    private BrowserView browserView = new BrowserView(productsDAO, ordersDAO, orderDetailsDAO);
    public BrowserView getBrowserView() {
        return browserView;
    }
    private BrowserController browserController;

    // Constructor
    private App() {
        browserController = new BrowserController(browserView, productsDAO, ordersDAO, orderDetailsDAO);
        orderHisController = new OrderHisController(orderHisView, ordersDAO, orderDetailsDAO);
        cartController = new CartController(cartView,cartDAO, productsDAO);
    }
    public static App getInstance() {
        if(instance == null) {
            instance = new App();
        }
        return instance;
    }
    // Main class
    public static void main(String[] args) {
        App.getInstance().getBrowserView().setVisible(true);
    }


}