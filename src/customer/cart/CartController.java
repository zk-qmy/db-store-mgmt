package customer.cart;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import products.Products;
import products.ProductsDAO;

public class CartController implements ActionListener {
    private CartView view;
    private CartDAO cartDao;
    private ProductsDAO productsDao;
    private Cart cart = null;

    public CartController(CartView view, CartDAO cartDao, ProductsDAO productsDao) {
        this.cartDao = cartDao;
        this.view = view;
        this.productsDao = productsDao;

        view.getBtnAdd().addActionListener(this);
        view.getBtnOrder().addActionListener(this);

        cart = new Cart();
        System.out.println("CartController created");

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnAdd()){
            System.out.println("button Add to cart pressed!");
            addProduct();
        }
        else if (e.getSource() == view.getBtnOrder()) {
            System.out.println("button place order pressed!");
            placeOrder();
        }
    }

    private void makeCart() {
        JOptionPane.showMessageDialog(null, "This function is being implemented!");

        /* Remember to update new orderQuantity of products!
        product.setQuantity(product.getQuantity() - orderQuantity); // update new orderQuantity!!
        cartDao.saveProduct(product); // and save this product back right away!!!
        */
    }
    public void placeOrder(){
        // Save the cart and its details into the database
        cartDao.addToCart(cart);
        // Optionally, clear the cart or show a success message
        JOptionPane.showMessageDialog(null, "Order placed successfully!");
        // Clear the cart
        cart = new Cart();
        view.clearCartTable();
        view.getLabTotal().setText("Total: $0");
    }


    private void addProduct(){
        // debug
        System.out.println("get into addProduct!!!");
        String id = JOptionPane.showInputDialog("Enter ProductID: ");

        Products product = productsDao.findProductbyID(Integer.parseInt(id));
        if (product == null) {
            JOptionPane.showMessageDialog(null, "This product does not exist!");
            return;
        }
        int orderQuantity = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter orderQuantity: "));

        if (orderQuantity < 0 ) {
            JOptionPane.showMessageDialog(null, "This orderQuantity is not valid!");
            return;
        } else if (orderQuantity > product.getStockQuantity()) {
            JOptionPane.showMessageDialog(null, "Only " + product.getStockQuantity() + "products left!");
            return;
        }

        // TO DO: update stock (stock quantity - orderquantity)

        //
        CartLines line = new CartLines();
        line.setCartID(this.cart.getCartID());
        line.setProductID(product.getProductID());
        line.setOrderQuantity(orderQuantity);
        line.setCost(orderQuantity * product.getPrice());
        cart.getLines().add(line);
        cart.setTotalPayment(cart.getTotalPayment() + line.getCost());


        Object[] row = new Object[5];
        row[0] = line.getProductID();
        row[1] = product.getProductName();
        row[2] = product.getPrice();
        row[3] = line.getOrderQuantity();
        row[4] = line.getCost();

        this.view.addRow(row);
        this.view.getLabTotal().setText("Total: $" + cart.getTotalPayment());
        this.view.invalidate();
    }

}