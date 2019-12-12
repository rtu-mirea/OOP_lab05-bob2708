import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Song {
    private String name;
    private int voices;
    private List<User> voiced = new ArrayList<>();

    public Song() {
        this.name = "";
        this.voices = 0;
    }

    public Song(String name) {
        this.name = name;
        this.voices = 0;
    }

    public Song(String name, int voices) {
        this.name = name;
        this.voices = voices;
    }

    public int getVoices() {
        return voices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVoices(int voices) {
        this.voices = voices;
    }

    public boolean voice (User user) {
        for (User users :voiced) {
            if (user.getLogin().equals(users.getLogin())) {
                JOptionPane.showMessageDialog(null, "Вы уже проголосовали за эту песню", "Ошибка", JOptionPane.ERROR_MESSAGE);
                System.out.println("Вы уже проголосовали за эту песню");
                return false;
            }
        }
        this.voices++;
        voiced.add(user);
        JOptionPane.showMessageDialog(null, "Голос засчитан");
        return true;
    }
}
