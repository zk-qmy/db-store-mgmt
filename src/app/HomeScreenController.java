package app;

import register.createaccount.RegisterView;
import register.login.LoginView;

import javax.print.DocFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenController implements ActionListener {
    private RegisterView registerView;
    private LoginView loginView;
    private HomeScreen homeScreen;
    public HomeScreenController(RegisterView registerView, LoginView loginView, HomeScreen homeScreen){
        this.homeScreen = homeScreen;
        this.registerView = registerView;
        this.loginView = loginView;

        homeScreen.getBtnLogin().addActionListener(this);
        homeScreen.getBtnCreateAccount().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeScreen.getBtnCreateAccount()) {
            registerView.setVisible(true);
        } else if (e.getSource() == homeScreen.getBtnLogin()) {
            loginView.setVisible(true);
        }
    }
}