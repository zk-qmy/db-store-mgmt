package admin.usersmgmt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdUserView extends JFrame {
    private JButton btnAddUser = new JButton("Add User");
    private JButton btnUpdateUser = new JButton("Update User");
    private JButton btnDeleteUser = new JButton("Delete User");
    private JButton btnBack = new JButton("Back");
    private DefaultTableModel tableModel;
    private JTable table;

     public AdUserView() {
        this.setTitle("User Management");
        this.setSize(1020, 800);
        this.setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel labelTit = new JLabel("User Management");
        labelTit.setBorder(new EmptyBorder(20, 10, 40, 20));
        labelTit.setFont(new Font("Arial", Font.BOLD, 50));

        //table panel
        JPanel tablePan = new JPanel();
        tablePan.setLayout(new BoxLayout(tablePan, BoxLayout.Y_AXIS));
        // Add table to scroll
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "username", "password", "address", "phone", "role ID"},0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePan.add(scrollPane);

        JScrollPane scrollPan = new JScrollPane(table);
        scrollPan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       //scrollPan.setPreferredSize(new Dimension(600, 400));
         tablePan.add(scrollPan, BorderLayout.CENTER);
         scrollPan.setViewportView(table);

        this.add(tablePan, BorderLayout.CENTER);
        // control panel

        JPanel controlPan = new JPanel();
        controlPan.setLayout(new BoxLayout(controlPan, BoxLayout.Y_AXIS));
        controlPan.setBorder(new EmptyBorder(40, 20, 40, 20));

        // Center the buttons and add them to the control panel
        btnAddUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUpdateUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDeleteUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to control panel
        controlPan.add(Box.createVerticalStrut(10)); // Spacer
        controlPan.add(btnAddUser);
        controlPan.add(Box.createVerticalStrut(10)); // Spacer
        controlPan.add(btnUpdateUser);
        controlPan.add(Box.createVerticalStrut(10)); // Spacer
        controlPan.add(btnDeleteUser);
        controlPan.add(Box.createVerticalStrut(10)); // Spacer
        controlPan.add(btnBack);
        this.add(controlPan, BorderLayout.EAST);
        this.getContentPane().add(labelTit, BorderLayout.NORTH);
    }

    public JButton getBtnAddUser() {
        return btnAddUser;
    }

    public JButton getBtnUpdateUser() {
        return btnUpdateUser;
    }

    public JButton getBtnDeleteUser() {
        return btnDeleteUser;
    }

    public JButton getBtnBack(){
        return btnBack;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}

