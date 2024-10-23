package customer.orderhistory;

import orders.OrderDetailsDAO;
import orders.Orders;
import orders.OrdersDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class OrderHisView extends JFrame{
    private JButton btnFind = new JButton("Find Order");
    private OrdersDAO ordersDAO;
    private OrderDetailsDAO orderDetailsDAO;


    public OrderHisView(OrdersDAO ordersDAO, OrderDetailsDAO orderDetailsDAO) {
        this.ordersDAO = ordersDAO;
        this.orderDetailsDAO = orderDetailsDAO;

        this.setTitle("Our Products");
        this.setLayout(new BorderLayout());
        this.setSize(1020, 800);

        // LabelTit
        JLabel labelTit = new JLabel("Order History");
        labelTit.setBorder(new EmptyBorder(20, 10, 40, 20));
        labelTit.setFont(new Font("Arial", Font.BOLD, 50));

        // Parent panel - border
        JPanel parentPan = new JPanel();
        parentPan.setLayout(new BorderLayout());
        // Control panel - border
        JPanel controlPan = new JPanel();
        controlPan.setLayout(new BoxLayout(controlPan, BoxLayout.X_AXIS));
        controlPan.setBorder(new EmptyBorder(40, 20, 40, 20));
        controlPan.add(btnFind);

        // Product panel - grid
        JPanel orderPan = new JPanel();
        orderPan.setLayout(new BoxLayout(orderPan, BoxLayout.Y_AXIS));
        orderPan.setBorder(new EmptyBorder(0, 20, 30, 20));
        // Scrollable Pane
        JScrollPane scrollPan = new JScrollPane(orderPan);
        scrollPan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPan.setPreferredSize(new Dimension(600, 400));
        // border for each product box
        new OrderHisController(this, ordersDAO, orderDetailsDAO);
        //add(orderPan, BorderLayout.CENTER);

        parentPan.add(controlPan, BorderLayout.SOUTH);
        parentPan.add(scrollPan, BorderLayout.CENTER);
        scrollPan.setViewportView(orderPan);
        this.getContentPane().add(parentPan, BorderLayout.CENTER);
        this.getContentPane().add(labelTit, BorderLayout.NORTH);
    }


    public void displaySelectedOrder(Orders order) {

    }
    public void displayOrders(List<Orders> ordersList) {

    }

    public JButton getBtnFind() {
        return btnFind;
    }
}
