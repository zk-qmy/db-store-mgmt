package register.createaccount;

import app.App;
import app.HomeScreen;
import register.users.UsersDAO;

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
            if(!isValidInput(name)) {
                JOptionPane.showMessageDialog(null, "Invalid name!");
                return;
            }
            String username = view.getUsernameField().getText().trim();
            if(!isValidInput(username)) {
                JOptionPane.showMessageDialog(null, "Invalid username!");
                return;
            }
            String password = new String(view.getPasswordField().getPassword());
            if(!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Invalid password!");
                return;
            }
            String confirmPassword = new String(view.getConfirmPasswordField().getPassword());
            String address = view.getAddressField().getText().trim();
            if(!isValidInput(address)) {
                JOptionPane.showMessageDialog(null, "Invalid adress!");
                return;
            }
            String phone = view.getPhoneField().getText().trim();
            if (!isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(null, "Invalid phone number!");
                return;
            }

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
    private boolean isValidInput(String string) {
        // Regex to check if the string contains only letters, digits, and spaces
        return string != null && !string.trim().isEmpty() && string.matches("^[a-zA-Z0-9\\s]+$");
    }
    private boolean isValidPassword(String string) {
        return string != null && !string.trim().isEmpty();
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regex to check if phone number starts with optional +, followed by digits, spaces, dashes
        return phoneNumber != null && phoneNumber.matches("^[+]?\\d{1,4}?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$");
    }

}