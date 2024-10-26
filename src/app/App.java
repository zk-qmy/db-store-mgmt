package app;

import admin.dashboard.DashBoardView;
import admin.ordersmgmt.AdOrdersView;
import admin.productsmgmt.AdProductsController;
import admin.productsmgmt.AdProductsView;
import admin.usersmgmt.AdUserView;
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
import register.Session;
import register.createaccount.RegisterController;
import register.createaccount.RegisterView;
import register.users.UsersDAO;
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

    private final DashBoardView dashBoardView;
    private final AdProductsView adProductsView;
    private final AdUserView adUserView;
    private final AdOrdersView adOrdersView;

    // Controllers
    private final HomeScreenController homeScreenController;
    private final RegisterController registerController;
    private final LoginController loginController;
    private final OrderHisController orderHisController;
    private final CartController cartController;
    private final BrowserController browserController;
    private final AdProductsController adProductsController;



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
        this.adProductsView = new AdProductsView(productsDAO, ordersDAO, orderDetailsDAO);
        this.orderHisView = new OrderHisView(ordersDAO, orderDetailsDAO);
        this.cartView = new CartView();
        this.browserView = new BrowserView();
        this.adUserView = new AdUserView();
        this.adOrdersView = new AdOrdersView();
        this.dashBoardView = new DashBoardView();


        // Initialize controllers
        this.homeScreenController = new HomeScreenController(homeScreen);
        this.registerController = new RegisterController(registerView, usersDAO, homeScreen);
        this.loginController = new LoginController(loginView, usersDAO, homeScreen);
        this.browserController = new BrowserController(browserView, productsDAO, ordersDAO, orderDetailsDAO);
        //System.out.println("ordersDAO: " + ordersDAO);
        this.orderHisController = new OrderHisController(orderHisView, ordersDAO, orderDetailsDAO);
        this.cartController = new CartController(cartView, orderDetailsDAO, productsDAO);
        this.adProductsController = new AdProductsController(adProductsView, productsDAO);

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

    public AdProductsView getAdProductsView() {
        return adProductsView;
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
    public LoginView getLoginView() {return loginView;}
    public HomeScreen getHomeScreen() {
        return homeScreen;
    }
    public DashBoardView getDashBoardView(){return dashBoardView;}
    public AdUserView getAdUserView() {return adUserView;}
    public AdOrdersView getAdOrdersView(){return adOrdersView;}

    // Main class
    public static void main(String[] args) {
        //App.getInstance().getHomeScreen().setVisible(true);
        // developing view
        App.getInstance().getAdUserView().setVisible(true);
    }
}
