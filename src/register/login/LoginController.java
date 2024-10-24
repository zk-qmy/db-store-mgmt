package register.login;

import register.Users.Users;
import register.Users.UsersDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginView view;
    private UsersDAO usersDAO;

    public LoginController(LoginView view, UsersDAO usersDAO) {
        this.view = view;
        this.usersDAO = usersDAO;

        view.getBtnLogin().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnLogin()) {
            String username = view.getUsernameField().getText().trim();
            String password = new String(view.getPasswordField().getPassword());

            System.out.println("Login with username = " + username + " and password = " + password);
            Users user = usersDAO.loadUser(username, password);

            if (user == null) {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            }
            else {
                System.out.println(" GO to browse view!!!!!!");
                //App.getInstance().setCurrentUser(user); // reconsider if it can hadlenultiple users
                //view.dispose();
                //App.getInstance().getHomeScreen().setVisible(true);
            }
        }
    }
}

