package register.createaccount;

import app.App;
import app.HomeScreen;
import register.Users.UsersDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private RegisterView view;
    private UsersDAO usersDAO;
    private HomeScreen homeScreen;

    public RegisterController(RegisterView view, UsersDAO userDAO, HomeScreen homeScreen) {
        this.view = view;
        this.usersDAO = userDAO;
        this.homeScreen = homeScreen;
        view.getBtnRegister().addActionListener(this);
        //view.getBtnLogin().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnRegister()) {
            System.out.println("Register button triggered!!");
            String name = view.getNameField().getText().trim();
            String username = view.getUsernameField().getText().trim();
            String password = new String(view.getPasswordField().getPassword());
            String confirmPassword = new String(view.getConfirmPasswordField().getPassword());
            String address = view.getAddressField().getText().trim();
            String phone = view.getPhoneField().getText().trim();

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int roleID = 2; // default customer id is 2
                // Implement registration logic here
                if (usersDAO.addUser(name, username, password, address, phone, roleID)) {
                    JOptionPane.showMessageDialog(null, "Account created successfully!");
                    homeScreen.dispose();
                    view.dispose();
                    App.getInstance().getBrowserView().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create account!");
                }

            }
        }

    }
}