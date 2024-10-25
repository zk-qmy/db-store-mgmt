package register.login;

import app.App;
import app.HomeScreen;
import register.Session;
import register.users.Users;
import register.users.UsersDAO;

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

                Users currentUser = usersDAO.getUserByUsername(username);
                Session.getInstance().setCurrentUser(currentUser); // Store session
                view.dispose();
                homeScreen.dispose();
                if (user.getRoleID() == 1){ // admin
                    //App.getInstance().getAdProductsView().setVisible(true);
                    App.getInstance().getDashBoardView().setVisible(true);
                } else if (user.getRoleID() == 2) { // customer
                    App.getInstance().getBrowserView().setVisible(true);
                }

            }
        }
    }
}

