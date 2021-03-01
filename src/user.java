import java.sql.Date;

public class user {
    private String user;
    private String  password;
    private int balance;

    public user() {
    }

    public user(String user, String passsword, int balance) {
        this.user = user;
        this.password = passsword;
        this.balance = balance;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasssword() {
        return password;
    }

    public void setPasssword(String passsword) {
        this.password = passsword;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "order{" +
                "user='" + user + '\'' +
                ", passsword='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
