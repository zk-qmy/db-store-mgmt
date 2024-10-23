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
    private final OrdersDAO ordersDAO;
    private final OrderDetailsDAO orderDetailsDAO;
    private final CartView cartView;
    private final CartDAO cartDAO;
    private final ProductsDAO productsDAO;
    private final BrowserView browserView;
    private final OrderHisView orderHisView;

    // Controllers
    private final OrderHisController orderHisController;
    private final CartController cartController;
    private final BrowserController browserController;

    // Constructor with Dependency Injection
    private App(OrdersDAO ordersDAO, OrderDetailsDAO orderDetailsDAO, CartDAO cartDAO, ProductsDAO productsDAO) {
        this.ordersDAO = ordersDAO;
        this.orderDetailsDAO = orderDetailsDAO;
        this.cartDAO = cartDAO;
        this.productsDAO = productsDAO;

        this.orderHisView = new OrderHisView(ordersDAO, orderDetailsDAO);
        this.cartView = new CartView();
        this.browserView = new BrowserView(productsDAO, ordersDAO, orderDetailsDAO);

        // Initialize controllers
        this.browserController = new BrowserController(browserView, productsDAO, ordersDAO, orderDetailsDAO);
        this.orderHisController = new OrderHisController(orderHisView, ordersDAO, orderDetailsDAO);
        this.cartController = new CartController(cartView, cartDAO, productsDAO);
    }

    public static App getInstance() {
        if (instance == null) {
            // Inject dependencies when creating the App instance
            instance = new App(new OrdersDAO(), new OrderDetailsDAO(), new CartDAO(), new ProductsDAO());
        }
        return instance;
    }

    public OrderHisView getOrderHisView() {
        return orderHisView;
    }

    public CartView getCartView() {
        return cartView;
    }

    public BrowserView getBrowserView() {
        return browserView;
    }

    // Main class
    public static void main(String[] args) {
        App.getInstance().getBrowserView().setVisible(true);
    }
}
