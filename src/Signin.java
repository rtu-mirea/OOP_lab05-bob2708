import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signin extends JFrame {
    JLabel nameLabel = new JLabel("name"),
           logШnLabel = new JLabel("login"),
           passwordLabel = new JLabel("password");
    JButton acceptButton = new JButton("accept"),
            cancelButton = new JButton("cancel");
    JTextField logInText = new JTextField(15),
               nameText = new JTextField(15);
    JPasswordField passwordField = new JPasswordField(15);
    JPanel panel1 = new JPanel(),
            panel2 = new JPanel(),
            panel3 = new JPanel(),
            panel4 = new JPanel();

    public Signin () {
        setLayout(new GridLayout(4,1));

        panel1.add(nameLabel);
        panel1.add(nameText);
        panel2.add(logШnLabel);
        panel2.add(logInText);
        panel3.add(passwordLabel);
        panel3.add(passwordField);
        panel4.add(acceptButton);
        panel4.add(cancelButton);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConcertSystem.login.addUser(nameText.getText(),logInText.getText(),passwordField.getPassword());
                setVisible(false);
                ConcertSystem.login.setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ConcertSystem.login.setVisible(true);
            }
        });

        setSize(500, 500);
        pack();
        //setVisible(true);
    }
}
