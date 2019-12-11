import java.util.Arrays;

public class User {
    private String name;
    private String login;
    private char[] password;

    public User() {}

    public User(String name, String login, char [] password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
    public boolean enter(String login, char [] password) {
        if (this.login.equals(login) && Arrays.equals(this.password,password)) {
            return true;
        } else return false;
    }

    public String getName() {
        return name;
    }

    public char[] getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
