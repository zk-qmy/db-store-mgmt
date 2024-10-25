package admin.usersmgmt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdUserView extends JFrame {
    private JTextField txtUsername = new JTextField(20);
    private JTextField txtPassword = new JPasswordField(20);
    private JTextField txtRole = new JTextField(10);
    private JButton btnAddUser = new JButton("Add User");
    private JButton btnUpdateUser = new JButton("Update User");
    private JButton btnDeleteUser = new JButton("Delete User");
    private JButton btnViewUsers = new JButton("View Users");
    private JTable userTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Username", "Role"}, 0));
    private DefaultTableModel tableModel = (DefaultTableModel) userTable.getModel();

    public AdUserView() {
        setTitle("User Management");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3, 2));
        panelForm.add(new JLabel("Username:"));
        panelForm.add(txtUsername);
        panelForm.add(new JLabel("Password:"));
        panelForm.add(txtPassword);
        panelForm.add(new JLabel("Role:"));
        panelForm.add(txtRole);

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnAddUser);
        panelButtons.add(btnUpdateUser);
        panelButtons.add(btnDeleteUser);
        panelButtons.add(btnViewUsers);

        add(panelForm, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);
        add(new JScrollPane(userTable), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return txtPassword.getText();
    }

    public String getRole() {
        return txtRole.getText();
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

    public JButton getBtnViewUsers() {
        return btnViewUsers;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}

