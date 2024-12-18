package customer.orderhistory;

import orders.OrderDetailsDAO;
import orders.Orders;
import orders.OrdersDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class OrderHisView extends JFrame{
    private JButton btnCancel = new JButton("Cancel Order");
    private JButton btnRefresh = new JButton("Refresh Page");
    private JPanel orderPan;

    public OrderHisView() {
        this.setTitle("Our Products");
        this.setLayout(new BorderLayout());
        this.setSize(1020, 800);
        this.setLocationRelativeTo(null);

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
        controlPan.add(btnCancel);
        controlPan.add(btnRefresh);

        // Order panel - grid
        orderPan = new JPanel();
        orderPan.setLayout(new BoxLayout(orderPan, BoxLayout.Y_AXIS));
        orderPan.setBorder(new EmptyBorder(0, 20, 30, 20));
        // Scrollable Pane
        JScrollPane scrollPan = new JScrollPane(orderPan);
        scrollPan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPan.setPreferredSize(new Dimension(600, 400));

        parentPan.add(controlPan, BorderLayout.SOUTH);
        parentPan.add(scrollPan, BorderLayout.CENTER);
        scrollPan.setViewportView(orderPan);
        this.getContentPane().add(parentPan, BorderLayout.CENTER);
        this.getContentPane().add(labelTit, BorderLayout.NORTH);
    }

    public void displayOrders(List<Orders> ordersList) {
        orderPan.removeAll();

        Border boxBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        if (ordersList.isEmpty()) {
            JLabel emptyList = new JLabel("No order available");
        } else {
            for (Orders order : ordersList) {
                JPanel box = new JPanel();
                box.setLayout(new GridLayout(1,3));
                box.setBorder(boxBorder);

                JLabel orderID = new JLabel("Order ID: "+ order.getOrderID());
                JLabel status = new JLabel("Status: "+ order.getStatus());
                JLabel total = new JLabel("Total: "+order.getTotal());

                orderID.setFont(new Font("Arial", Font.BOLD, 18));
                total.setFont(new Font("Arial", Font.PLAIN, 18));
                status.setFont(new Font("Arial", Font.BOLD, 18));
                setStatusColor(status);

                box.add(orderID);
                box.add(total);
                box.add(status);

                orderPan.add(box);
            }
        }
        orderPan.revalidate();;
        orderPan.repaint();
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }
    public JButton getBtnRefresh(){
        return btnRefresh;
    }

    private void setStatusColor(JLabel status){
        String extractedStatus = status.getText().replace("Status: ","").trim().toLowerCase();
        switch(extractedStatus) {
            case "pending":
                status.setForeground(new Color(0xB8860B));
                break;
            case "approved":
                status.setForeground(new Color(0x228B22));
                break;
            case "shipped":
                status.setForeground(new Color(0x104E8B));
                break;
            case "canceled":
                status.setForeground(new Color(0x8B0000));
                break;
            default:
                break;
        }
    }
}
