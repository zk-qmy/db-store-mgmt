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
import register.createaccount.RegisterController;
import register.createaccount.RegisterView;
import register.Users.UsersDAO;
import register.login.LoginController;
import register.login.LoginView;

public class App {
    private static App instance;

    // Main components

    private final UsersDAO usersDAO;
    private final OrdersDAO ordersDAO;
    private final OrderDetailsDAO orderDetailsDAO;
    private final CartView cartView;
    private final CartDAO cartDAO;
    private final ProductsDAO productsDAO;
    private final BrowserView browserView;
    private final OrderHisView orderHisView;
    private final HomeScreen homeScreen;
    private final RegisterView registerView;
    private final LoginView loginView;

    // Controllers
    private final HomeScreenController homeScreenController;
    private final RegisterController registerController;
    private final LoginController loginController;
    private final OrderHisController orderHisController;
    private final CartController cartController;
    private final BrowserController browserController;



    // Constructor with Dependency Injection
    private App(UsersDAO usersDAO, OrdersDAO ordersDAO, OrderDetailsDAO orderDetailsDAO, CartDAO cartDAO, ProductsDAO productsDAO) {
        this.usersDAO = usersDAO;
        this.ordersDAO = ordersDAO;
        this.orderDetailsDAO = orderDetailsDAO;
        this.cartDAO = cartDAO;
        this.productsDAO = productsDAO;

        this.homeScreen = new HomeScreen();
        this.registerView = new RegisterView();
        this.loginView = new LoginView();
        this.orderHisView = new OrderHisView(ordersDAO, orderDetailsDAO);
        this.cartView = new CartView();
        this.browserView = new BrowserView(productsDAO, ordersDAO, orderDetailsDAO);

        // Initialize controllers
        this.homeScreenController = new HomeScreenController(registerView,loginView,homeScreen);
        this.registerController = new RegisterController(registerView, usersDAO, homeScreen);
        this.loginController = new LoginController(loginView, usersDAO, homeScreen);
        this.browserController = new BrowserController(browserView, productsDAO, ordersDAO, orderDetailsDAO);
        this.orderHisController = new OrderHisController(orderHisView, ordersDAO, orderDetailsDAO);
        this.cartController = new CartController(cartView, orderDetailsDAO, productsDAO);


    }

    public static App getInstance() {
        if (instance == null) {
            // Inject dependencies when creating the App instance
            instance = new App(new UsersDAO(), new OrdersDAO(), new OrderDetailsDAO(), new CartDAO(), new ProductsDAO());
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
    public RegisterView getRegisterView() {
        return registerView;
    }
    public HomeScreen getHomeScreen() {
        return homeScreen;
    }

    // Main class
    public static void main(String[] args) {
        //App.getInstance().getBrowserView().setVisible(true);
        //App.getInstance().getRegisterView().setVisible(true);
        App.getInstance().getHomeScreen().setVisible(true);
    }
}
