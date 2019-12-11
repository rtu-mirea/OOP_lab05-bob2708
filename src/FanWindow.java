import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class FanWindow extends JFrame {
    JButton addButton = new JButton("добавить"),
            voteButton = new JButton("голосовать"),
            resultFileButton = new JButton("результат"),
            acceptButton = new JButton("принять"),
            cancelButton = new JButton("отменить");
    JTextField addFrameTextFieldBand = new JTextField(20),
               addFrameTextFieldSong = new JTextField(20);
    JLabel addFrameLabelBand = new JLabel("band:"),
            addFrameLabelSong = new JLabel("song:");
    JFrame addFrame = new JFrame(),
            resultFrame = new JFrame("Прогамма концерта");
    JList resultList = new JList(ConcertSystem.login.songsToStrArray(ConcertSystem.login.songs));
    JScrollPane resultScrollPane = new JScrollPane(resultList);

    public FanWindow() {
        JPanel jPanel = new JPanel(),
                addFrameJPanel1 = new JPanel(),
                addFrameJPanel2 = new JPanel(),
                addFrameJPanel3 = new JPanel();

        jPanel.add(addButton);
        jPanel.add(voteButton);
        jPanel.add(resultFileButton);

        addFrameJPanel1.add(addFrameLabelBand);
        addFrameJPanel1.add(addFrameTextFieldBand);
        addFrameJPanel2.add(addFrameLabelSong);
        addFrameJPanel2.add(addFrameTextFieldSong);
        addFrameJPanel3.add(acceptButton);
        addFrameJPanel3.add(cancelButton);

        resultScrollPane.setPreferredSize(new Dimension(200,200));
        resultFrame.add(resultScrollPane);

        addFrame.setLayout(new GridLayout(3,2));
        addFrame.add(addFrameJPanel1);
        addFrame.add(addFrameJPanel2);
        addFrame.add(addFrameJPanel3);
        addFrame.pack();

        add(jPanel);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addFrameTextFieldBand.getText().equals("") || addFrameTextFieldSong.getText().equals("")) {
                    JOptionPane.showMessageDialog(FanWindow.this, "Не оставляйте поле ввода пустым","Ошибка",JOptionPane.ERROR_MESSAGE);
                } else {
                    Song song = new Song(addFrameTextFieldBand.getText() + " - " + addFrameTextFieldSong.getText());
                    ConcertSystem.login.songs.add(song);
                    JOptionPane.showMessageDialog(FanWindow.this, "Успешно добавлено","Добавление",JOptionPane.INFORMATION_MESSAGE);
                    ConcertSystem.fanWindow.addFrame.setVisible(false);
                    ConcertSystem.fanWindow.setVisible(true);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConcertSystem.fanWindow.setVisible(true);
                ConcertSystem.fanWindow.addFrame.setVisible(false);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ConcertSystem.login.voteDone) {
                    JOptionPane.showMessageDialog(null, "Голосование окончено", "Голосование окончено", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                setVisible(false);
                addFrame.setVisible(true);
            }
        });

        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ConcertSystem.login.voteDone) {
                    JOptionPane.showMessageDialog(null, "Голосование окончено", "Голосование окончено", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                if (ConcertSystem.login.songs.isEmpty()) {
                    JOptionPane.showMessageDialog(FanWindow.this, "Список песен пуст - голосование невозможно","Ошибка",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String [] temp = ConcertSystem.login.songsToStrArray(ConcertSystem.login.songs);
                Object result = JOptionPane.showInputDialog(
                        FanWindow.this,
                        "Проголосуйте за песню :",
                        "ГОЛОСОВАНИЕ",
                        JOptionPane.QUESTION_MESSAGE,null, temp, temp[0]);
                for (Song song : ConcertSystem.login.songs) {
                    if (song.getName().equals(result)) {
                        song.voice(ConcertSystem.login.currentUser);
                    }
                }
                // Диалоговое окно вывода сообщения
                System.out.println(result);

            }
        });

        resultFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultList.setListData(ConcertSystem.login.songsToStrArray(ConcertSystem.login.getResults()));
                resultFrame.setVisible(true);
                resultFrame.pack();
                ConcertSystem.login.voteDone = true;
            }
        });
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
        pack();
    }
}