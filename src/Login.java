import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Login extends JFrame {
    public static java.util.List<Song> songs = new ArrayList<>();
    public static java.util.List<User> users = new ArrayList<>();
    static public int countOfSongs;
    static public boolean voteDone = false;
    static public User currentUser = null;
    static public String [] songsToStrArray(List<Song> list) {
        String [] temp = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            temp[i] = list.get(i).getName();
        }
        return temp;
    }
    static public void save(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            for (Song song : songs) {
                Integer voices = song.getVoices();
                String temp = song.getName() + " " + voices.toString() + "\n";
                fileWriter.write(temp);
            }
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Успешно сохранено","Cохранение",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Успешно сохранено");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка","Ошибка",JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }
    static public void load(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), "UTF-8"));
            songs.clear();
            String temp, name, voices;
            while ((temp = bufferedReader.readLine()) != null) {
                name = temp.substring(0, temp.lastIndexOf(" "));
                voices = temp.substring(temp.lastIndexOf(" ") + 1);
                int voice = Integer.parseInt(voices);
                songs.add(new Song(name, voice));
            }
            System.out.println("Успешно загружено");
            JOptionPane.showMessageDialog(null, "Успешно загружено","Загрузка",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка","Ошибка",JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }
    static public List getResults() {
        //JOptionPane.showMessageDialog(null, "Сначала выберите файл для сохранения/загрузки ","Ошибка",JOptionPane.ERROR_MESSAGE);
        songs.sort(new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                if (o1.getVoices() > o2.getVoices()) {
                    return -1;
                }
                if (o1.getVoices() < o2.getVoices()) {
                    return 1;
                }
                return 0;
            }
        });
        List<Song> winners = new ArrayList<>();
        if (countOfSongs > songs.size()) {
            countOfSongs = songs.size();
        }
        for (int i = 0; i < countOfSongs; i++) {
            winners.add(songs.get(i));
            System.out.println("- " + songs.get(i).getName());
        }
        System.out.println("----------------------------------------------");
        return winners;
    }
    static public void addUser(String name, String login, char[] password) {
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                JOptionPane.showMessageDialog(null,"Пользователь с таким логином уже существует");
                return;
            }
        }
        Fan fan = new Fan(name, login, password);
        users.add(fan);
        JOptionPane.showMessageDialog(null,"Пользователь " + name + " добавлен");
    }
    static private User findUser(String login, char[] password) {
        for (User user : users) {
            System.out.println(user.getPassword());
            if (user.enter(login, password)) {
                currentUser = user;
                JOptionPane.showMessageDialog(null, "Добро пожаловать, " + currentUser.getName());
                return currentUser;
            }
        }
        JOptionPane.showMessageDialog(null,"Неверный логин или пароль","Ошибка", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    JLabel logInLabel = new JLabel("login"),
            passwordLabel = new JLabel("password");
    JButton logInButton = new JButton("log in"),
            signUpButton = new JButton("sign in");
    JTextField logInText = new JTextField(15);
    JPasswordField passwordField = new JPasswordField(15);
    JPanel panel1 = new JPanel(),
            panel2 = new JPanel(),
            panel3 = new JPanel();

    public Login () {
        super("Concert System");

        logInLabel.setLabelFor(logInText);
        passwordLabel.setLabelFor(passwordField);

        setLayout(new GridLayout(3,1));

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findUser(logInText.getText(), passwordField.getPassword());
                    if (currentUser.getName().equals("admin")) {
                        ConcertSystem.adminWindow.setVisible(true);
                        ConcertSystem.login.setVisible(false);
                    } else if (currentUser != null) {
                        ConcertSystem.fanWindow.setVisible(true);
                        ConcertSystem.login.setVisible(false);
                    }
                } catch (NullPointerException ex) {
                    System.out.println("NullPointer");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ConcertSystem.signin.setVisible(true);
                //new Signin();
            }
        });

        panel1.add(logInLabel);
        panel1.add(logInText);
        panel2.add(passwordLabel);
        panel2.add(passwordField);
        panel3.add(logInButton);
        panel3.add(signUpButton);

        add(panel1);
        add(panel2);
        add(panel3);

        setSize(500, 500);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
