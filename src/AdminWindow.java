import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileFilter;

public class AdminWindow extends JFrame {
    JButton loadButton = new JButton("load"),
            saveButton = new JButton("save"),
            chooseFileButton = new JButton("choose file"),
            editWinnersButton = new JButton("edit number of songs");
    JTextField jTextField = new JTextField(40);
    JFileChooser jFileChooser = new JFileChooser();
    FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("txt files","txt");


    public AdminWindow() {
        JPanel jPanel = new JPanel();
        jPanel.add(jTextField);
        jPanel.add(loadButton);
        jPanel.add(saveButton);
        jPanel.add(editWinnersButton);
        jPanel.add(chooseFileButton);
        add(jPanel);

        jTextField.setEnabled(false);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setFileFilter(fileNameExtensionFilter);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                ConcertSystem.login.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ConcertSystem.login.voteDone) {
                    JOptionPane.showMessageDialog(null, "Голосование окончено", "Голосование окончено", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                if (jTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(AdminWindow.this, "Сначала выберите файл для сохранения/загрузки ","Ошибка",JOptionPane.ERROR_MESSAGE);
                } else {
                    ConcertSystem.login.load(new File(jTextField.getText()));
                    new JTableRow();
                }
            }
        });
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFileChooser.setDialogTitle("Выбор директории");
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = jFileChooser.showOpenDialog(AdminWindow.this);
                if (result == JFileChooser.APPROVE_OPTION ) {
                    jTextField.setText(jFileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(AdminWindow.this, "Сначала выберите файл для сохранения/загрузки ","Ошибка",JOptionPane.ERROR_MESSAGE);
                } else {
                    ConcertSystem.login.save(new File(jTextField.getText()));
                }
            }
        });
        editWinnersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ConcertSystem.login.voteDone) {
                    JOptionPane.showMessageDialog(null, "Голосование окончено", "Голосование окончено", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                try {
                    ConcertSystem.adminWindow.setEnabled(false);
                    int result = Integer.parseInt(JOptionPane.showInputDialog("num of songs"));
                    System.out.println(result);
                    if (result < 1) {
                        JOptionPane.showMessageDialog(null, "Должен быть хотя бы один победитель","Ошибка ввода",JOptionPane.ERROR_MESSAGE);
                    } else {
                        ConcertSystem.login.countOfSongs = result;
                        JOptionPane.showMessageDialog(null, "Успешно изменено");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка ввода: вводите числа","Ошибка ввода",JOptionPane.ERROR_MESSAGE);
                }
                finally {
                    ConcertSystem.adminWindow.setEnabled(true);
                    ConcertSystem.adminWindow.setVisible(true);
                }
            }
        });

        pack();
    }
}
