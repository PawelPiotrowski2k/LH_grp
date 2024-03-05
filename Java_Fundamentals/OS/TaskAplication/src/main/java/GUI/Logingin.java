package GUI;

import javax.swing.*;
import java.awt.*;

public class Logingin extends JFrame{
    private JButton loginBtn;
    private JPasswordField pswdField;
    private JTextField emailField;
    private JPanel mainPanel;
    private void createUIComponents() {
         loginBtn = new JButton();
         mainPanel = new JPanel();
         pswdField = new JPasswordField();
         emailField = new JTextField();
    }
    public Logingin(){
        mainPanel.setPreferredSize(new Dimension(400, 400));
        setContentPane(mainPanel);
        createUIComponents();
        setTitle("TaskManager.Task Manager");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        Logingin loginFrame = new Logingin();
    }
}
