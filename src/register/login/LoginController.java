package register.login;

import app.App;
import app.HomeScreen;
import register.Users.Users;
import register.Users.UsersDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginView view;
    private UsersDAO usersDAO;
    private HomeScreen homeScreen;

    public LoginController(LoginView view, UsersDAO usersDAO, HomeScreen homeScreen) {
        this.view = view;
        this.usersDAO = usersDAO;
        this.homeScreen = homeScreen;

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
                view.dispose();
                homeScreen.dispose();
                if (user.getRoleID() == 1){ // admin

                } else if (user.getRoleID() == 2) { // customer
                    App.getInstance().getBrowserView().setVisible(true);
                }

            }
        }
    }
}
