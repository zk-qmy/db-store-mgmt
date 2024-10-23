package customer.browser;
import orders.OrderDetailsDAO;
import orders.OrdersDAO;
import products.Products;
import products.ProductsDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class BrowserView extends JFrame {
    private JButton btnFind = new JButton("Find product");
    private JButton btnBuy = new JButton("Start Purchasing"); // navigate to cart view
    private JButton btnOrderHis = new JButton("See Order History");
    private JPanel productPan;

    public BrowserView(ProductsDAO productsDAO, OrdersDAO ordersDAO, OrderDetailsDAO orderDetailsDAO) {

        this.setTitle("Our Products");
        this.setLayout(new BorderLayout());
        this.setSize(1020, 800);

        // LabelTit
        JLabel labelTit = new JLabel("Explore Our Product!");
        labelTit.setBorder(new EmptyBorder(20, 10, 40, 20));
        labelTit.setFont(new Font("Arial", Font.BOLD, 50));

        // Parent panel - border
        JPanel parentPan = new JPanel();
        parentPan.setLayout(new BorderLayout());
        // Control panel - border
        JPanel controlPan = new JPanel();
        controlPan.setLayout(new BoxLayout(controlPan, BoxLayout.Y_AXIS));
        controlPan.setBorder(new EmptyBorder(40, 20, 40, 20));
        controlPan.add(btnBuy);
        controlPan.add(btnFind);
        controlPan.add(btnOrderHis);
        // Product panel - grid
        productPan = new JPanel();
        productPan.setLayout(new GridLayout(0, 4, 10, 30)); // dynamic rows, 4 cols
        productPan.setBorder(new EmptyBorder(0, 20, 20, 10));
        // Scrollable Pane
        JScrollPane scrollPan = new JScrollPane(productPan);
        scrollPan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPan.setPreferredSize(new Dimension(600, 400));
        // border for each product box
        new BrowserController(this, productsDAO, ordersDAO, orderDetailsDAO);
        //add(productPan, BorderLayout.CENTER);

        parentPan.add(controlPan, BorderLayout.EAST);
        parentPan.add(scrollPan, BorderLayout.CENTER);
        scrollPan.setViewportView(productPan);
        this.getContentPane().add(parentPan, BorderLayout.CENTER);
        this.getContentPane().add(labelTit, BorderLayout.NORTH);
    }

    public JButton getBtnFind() {
        return btnFind;
    }

    public JButton getBtnBuy() {
        return btnBuy;
    }
    public JButton getBtnOrderHis() {return btnOrderHis;}

    public void displayProducts(List<Products> productsList) {
        //List<Products> productList = BrowserController.loadProductList();
        // clear existing components
        productPan.removeAll();

        // Create product box
        Border boxBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        // add product to panel
        // TO DO: adjust this
        if (productsList.isEmpty()) {
            JLabel emptyList = new JLabel(" No products available!");
        } else {
            // Add each product to the product panel
            for (Products product : productsList) {
                JPanel box = new JPanel();
                box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
                box.setBorder(boxBorder);
                box.setPreferredSize(new Dimension(150, 200)); // Adjust the size for better appearance

                // Product info labels
                JLabel productID = new JLabel("ID: " + product.getProductID());
                JLabel productName = new JLabel("Name: " + product.getProductName());
                JLabel productPrice = new JLabel("Price: $" + product.getPrice());
                JLabel productQuantity = new JLabel("In Stock: " + product.getStockQuantity());

                // Font adjustments for better readability
                productID.setFont(new Font("Arial", Font.BOLD, 15));
                productName.setFont(new Font("Arial", Font.BOLD, 20));
                productPrice.setFont(new Font("Arial", Font.PLAIN, 18));
                productQuantity.setFont(new Font("Arial", Font.PLAIN, 18));

                // Center product name and price
                productName.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
                productQuantity.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Add product information to the box
                box.add(productID);
                box.add(productName);
                box.add(productPrice);
                box.add(productQuantity);

                // Add each box to the main product panel
                productPan.add(box);
            }
        }
        productPan.revalidate();
        productPan.repaint();
    }

    public void displaySelectedProduct(Products product) {
        // clear existing components
        productPan.removeAll();

        // Create product box
        Border boxBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        // add product to panel
        // TO DO: adjust this
        if (product == null) {
            JLabel nullProduct = new JLabel(" Product does not exist!");
        } else {
            JPanel box = new JPanel();
            box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
            box.setBorder(boxBorder);
            box.setPreferredSize(new Dimension(150, 200)); // Adjust the size for better appearance

            // Product info labels
            JLabel productID = new JLabel("ID: " + product.getProductID());
            JLabel productName = new JLabel("Name: " + product.getProductName());
            JLabel productPrice = new JLabel("Price: $" + product.getPrice());
            JLabel productQuantity = new JLabel("In Stock: " + product.getStockQuantity());

            // Font adjustments for better readability
            productID.setFont(new Font("Arial", Font.BOLD, 15));
            productName.setFont(new Font("Arial", Font.BOLD, 20));
            productPrice.setFont(new Font("Arial", Font.PLAIN, 18));
            productQuantity.setFont(new Font("Arial", Font.PLAIN, 18));

            // Center product name and price
            productName.setAlignmentX(Component.CENTER_ALIGNMENT);
            productPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
            productQuantity.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add product information to the box
            box.add(productID);
            box.add(productName);
            box.add(productPrice);
            box.add(productQuantity);

            // Add each box to the main product panel
            productPan.add(box);
        }
        productPan.revalidate();
        productPan.repaint();
    }
}