import java.util.List;

public class SiteUser implements Comparable<SiteUser> {
    private String nickname;
    private String login;
    private String password;
    private String gender;
    private String meet_date;
    private int messages;



    public SiteUser(String nickname, String login, String password, String gender, String meet_date, int messages) {
        this.nickname = nickname;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.meet_date = meet_date;
        this.messages = messages;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getMeet_date() {
        return meet_date;
    }

    public int getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return  String.format("%s %s %s %s %s %s",
                nickname, login, password, gender, meet_date, messages);
    }

    @Override
    public int compareTo(SiteUser siteUser) {
        return nickname.compareTo(siteUser.getNickname());
    }
}
