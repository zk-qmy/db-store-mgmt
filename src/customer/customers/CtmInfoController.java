/*
package customer.customers;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CtmInfoController {
    private CtmInfoView view;
    private CtmInfoDAO dao;

    public CtmInfoController(CtmInfoView view, dao) {
        this.view = view;
        this.dao = dao;

        view.getBtnLoad().addActionListener(this); // load user for customer to check
        view.getBtnSend().addActionListener(this); // send request
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnSend()) {
            placeOrder();
        }
        else if (e.getSource() == view.getBtnLoad()) {
            //loadAccount;
        }
    }
    private void placeOrder() {
        // save customer info, create and update customer's
        //order history;

        // validate by customerID and userID? if yes ,
        // save and process the order, no => error
        int userID;
        try {
            userID = Integer.parseInt(view.getTxtUserID().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid user ID!");
            return;
        }

        String ctmName = view.getTxtCtmName().getText().trim();
        if (ctmName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid name!");
            return;
        }

        String phone = view.getTxtCtmPhone().getText().trim();
        if(phone.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid phone number!");
            return;
        }

        String address = view.getTxtCtmAddress().getText().trim();
        if(address.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid address!");
            return;
        }

        /*
        // Done all validations! Make an object for this product!

        Product product = new Product();
        product.setProductID(productID);
        product.setSellerID(Application.getInstance().getCurrentUser().getUserID());
        product.setName(productName);
        product.setPrice(productPrice);
        product.setQuantity(productQuantity);

        // Store the product to the database

        dataAdapter.saveProduct(product);

    }
}
*/