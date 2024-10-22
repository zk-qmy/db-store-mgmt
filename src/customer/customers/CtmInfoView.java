package customer.customers;
import javax.swing.*;

public class CtmInfoView extends JFrame{
    private JTextField txtUserID  = new JTextField(10);
    private JTextField txtCtmName  = new JTextField(30);
    private JTextField txtCtmPhone  = new JTextField(10);
    private JTextField txtCtmAddress  = new JTextField(10);

    private JButton btnLoad = new JButton("Load Account"); // TO DO: maybe change to create account
    private JButton btnSend = new JButton("Send Order"); // maybe change to create account

    public CtmInfoView() {
        this.setTitle("Customer Shipping Information");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(800, 500);

        JPanel panelButton = new JPanel();
        panelButton.add(btnLoad);
        panelButton.add(btnSend);
        this.getContentPane().add(panelButton);

        JPanel panelCtmID = new JPanel();
        panelCtmID.add(new JLabel("User ID: "));
        panelCtmID.add(txtUserID);
        txtUserID.setHorizontalAlignment(JTextField.RIGHT);
        this.getContentPane().add(panelCtmID);

        JPanel panelCtmName = new JPanel();
        panelCtmName.add(new JLabel("Name: "));
        panelCtmName.add(txtCtmName);
        this.getContentPane().add(panelCtmName);

        JPanel panelCtmInfo = new JPanel();
        panelCtmInfo.add(new JLabel("Phone: "));
        panelCtmInfo.add(txtCtmPhone);
        txtCtmPhone.setHorizontalAlignment(JTextField.RIGHT);

        panelCtmInfo.add(new JLabel("Address: "));
        panelCtmInfo.add(txtCtmAddress);
        txtCtmAddress.setHorizontalAlignment(JTextField.RIGHT);

        this.getContentPane().add(panelCtmInfo);

    }

    public JButton getBtnLoad() {
        return btnLoad;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JTextField getTxtUserID() {
        return txtUserID;
    }

    public JTextField getTxtCtmName() {
        return txtCtmName;
    }

    public JTextField getTxtCtmPhone() {
        return txtCtmPhone;
    }

    public JTextField getTxtCtmAddress() {
        return txtCtmAddress;
    }
}
