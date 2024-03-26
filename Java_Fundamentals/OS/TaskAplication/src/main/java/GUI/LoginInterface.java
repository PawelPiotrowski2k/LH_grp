package GUI;

import Authentication.Authentication;
import Authentication.AuthenticationException;

import javax.swing.*;
import java.awt.*;
public class LoginInterface {
    private JFrame frame;
    private JButton loginBtn;
    private JPasswordField passwordField;
    private JTextField textField;
    private JLabel loginText;
    private JLabel passwordText;
    private ImageIcon icon = new ImageIcon("src\\main\\resources\\icon.png");
    public static void main(String[] args) {
        LoginInterface loginInterface = new LoginInterface();
    }

    public LoginInterface() {
        createComponents();
        setFrame(frame,loginBtn,passwordField,textField,loginText,passwordText);
        buttonListener();
    }
    private void buttonListener(){
        loginBtn.addActionListener(e -> {
            String login = textField.getText();
            Authentication authentication = new Authentication();
            String password = new String(passwordField.getPassword());
            try {
                if(authentication.logInUser(login,password) != null){
                    JOptionPane.showMessageDialog(frame, "git gut");
                }else{
                    JOptionPane jOptionPane = new JOptionPane();
                    jOptionPane.setFocusable(false);
                    jOptionPane.showMessageDialog(frame, "błędne hasło lub mail");
                }
            } catch (AuthenticationException ex) {
                JOptionPane jOptionPane = new JOptionPane();
                jOptionPane.setFocusable(false);
                jOptionPane.showMessageDialog(frame, "błędne hasło lub mail");
            }

        });
    }
    private void createComponents(){
        frame = new JFrame();
        loginBtn = new JButton();
        passwordField = new JPasswordField();
        textField = new JTextField();
        loginText= new JLabel();
        passwordText = new JLabel();
        setLoginBtn(loginBtn);
        setPasswordField(passwordField);
        setLoginText(loginText);
        setPasswordText(passwordText);
        setTextField(textField);
    }
    private void setFrame(JFrame frame,JButton loginBtn,JPasswordField passwordField,JTextField textField,JLabel loginText,JLabel passwordText){
        frame.setSize(400,400);
        frame.setIconImage(icon.getImage());
        frame.setTitle("Task Manager");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginText, gbc);

        gbc.gridy++;
        panel.add(textField, gbc);

        gbc.gridy++;
        panel.add(passwordText, gbc);

        gbc.gridy++;
        panel.add(passwordField, gbc);

        gbc.gridy++;
        panel.add(loginBtn, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    private void setPasswordText(JLabel passwordText){
        passwordText.setText("wprowadź hasło");
        passwordText.setSize(100,20);
        passwordText.setHorizontalAlignment(JLabel.CENTER);
        passwordText.setVerticalAlignment(JLabel.CENTER);
    }
    private void setLoginBtn(JButton btn){
        btn.setSize(100,20);
        btn.setText("Login");
        btn.setFocusable(false);
    }
    private void setLoginText(JLabel loginText){
        loginText.setText("Wprowadź maila");
        loginText.setSize(100,20);
    }
    private void setTextField(JTextField textField){
        textField.setText("mail");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setPreferredSize(new Dimension(200,20));
    }

    private void setPasswordField(JPasswordField passwordField){
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);
        passwordField.setPreferredSize(new Dimension(200,20));
        passwordField.setText("haslo");
    }
}
