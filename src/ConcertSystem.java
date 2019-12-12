public class ConcertSystem {
    public static FanWindow fanWindow = new FanWindow();
    public static AdminWindow adminWindow = new AdminWindow();
    public static Signin signin = new Signin();
    public static Login login = new Login();
    public static void main(String[] args) {
        login.setVisible(true);
        Admin admin = new Admin("admin", "admin", "admin".toCharArray());
        login.users.add(admin);
    }
}