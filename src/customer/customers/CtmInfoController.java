package customer.customers;

import java.awt.event.ActionEvent;

public class CtmInfoController {
    private CtmInfoView view;
    private CtmInfoDAO dao;

    public CtmInfoController(CtmInfoView view, dao) {
        this.view = view;
        this.dao = dao;

        CtmInfoView.getBtnLoad().addActionListener(this); // load user for customer to check
        CtmInfoView.getBtnSend().addActionListener(this); // send request
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
            userID = Integer.parseInt(view.getTxt)
        }
    }
}