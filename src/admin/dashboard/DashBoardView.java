package admin.dashboard;

import admin.productsmgmt.AdProductsView;
import admin.usersmgmt.AdUserView;
import app.App;

import javax.swing.*;
import java.awt.*;

public class DashBoardView extends JFrame {
    private AdProductsView adProductsView;
    private AdUserView adUserView;

    private JPanel mainPanel, sidebarPanel, contentPanel;
    private JButton btnManageUsers, btnManageOrders, viewReportsButton, btnLogout, btnManageProducts;
    private JLabel dashboardTitle;

    public DashBoardView(AdProductsView adProductsView, AdUserView adUserView ) {
        this.adProductsView = adProductsView;
        this.adUserView = adUserView;

        // Frame settings
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Sidebar Panel
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.LIGHT_GRAY);

        btnManageUsers = new JButton("Manage Users");
        btnManageOrders = new JButton("Manage Orders");
        btnManageProducts = new JButton("Manage Products");
        viewReportsButton = new JButton("View Reports");
        btnLogout = new JButton("Logout");

        sidebarPanel.add(btnManageProducts);
        sidebarPanel.add(btnManageUsers);
        sidebarPanel.add(btnManageOrders);
        sidebarPanel.add(viewReportsButton);
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(btnLogout);

        // Content Panel
        contentPanel = new JPanel(new BorderLayout());
        dashboardTitle = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(dashboardTitle, BorderLayout.NORTH);

        // Add Sidebar and Content to Main Panel
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Button Actions
        btnManageProducts.addActionListener(e -> {
            dispose();
            App.getInstance().getAdProductsView().setVisible(true);
        });
        btnManageUsers.addActionListener(e -> {
            dispose();
            App.getInstance().getAdUserView().setVisible(true);
        });
        btnManageOrders.addActionListener(e -> {
            dispose();
            App.getInstance().getAdOrdersView().setVisible(true);
        });
        viewReportsButton.addActionListener(e -> {
        });
        btnLogout.addActionListener(e -> logout());

    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();  // Close dashboard
            App.getInstance().getHomeScreen().setVisible(true);  // Replace with login screen
        }
    }
}