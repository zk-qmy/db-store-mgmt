package admin.ordersmgmt;

import app.App;
import orders.Orders;
import orders.OrdersDAO;
import register.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdOrdersController implements ActionListener {
    private AdOrdersView view;
    private OrdersDAO ordersDAO;

    public AdOrdersController(AdOrdersView view, OrdersDAO ordersDAO) {
        this.view = view;
        this.ordersDAO = ordersDAO;
        displayOrders();

        view.getBtnAddOrder().addActionListener(this);
        view.getBtnDeleteOrder().addActionListener(this);
        view.getBtnUpdateOrder().addActionListener(this);
        view.getBtnBack().addActionListener(this);
        view.getBtnRefresh().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnAddOrder()) {
            addOrder();
        } else if (e.getSource() == view.getBtnUpdateOrder()) {
            //updateOrder();
        } else if(e.getSource() == view.getBtnDeleteOrder()) {
            //deleteOrder();
        } else if (e.getSource() == view.getBtnBack()) {
            view.dispose();
            App.getInstance().getDashBoardView().setVisible(true);
        } else if (e.getSource()==view.getBtnRefresh()) {
            displayOrders();
        }
    }
    public void addOrder(){
        App.getInstance().getCartView().setVisible(true);
    }

    public void displayOrders(){
        List<Orders> orderList = ordersDAO.loadAllOrders();
        view.getTableModel().setRowCount(0);

        for (Orders order : orderList) {
            view.getTableModel().addRow(new Object[] {
                    order.getOrderID(),
                    order.getCusID(),
                    order.getCtmName(),
                    order.getTotal(),
                    order.getStatus(),
                    order.getAddress(),
                    order.getPhone()
            });
        }

    }

}