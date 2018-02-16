package App;

public class Student {
    private String sid;
    private String username;
    private String email;
    private String name;
    private String dob;
    private String password;
    private String year;
    public Student(String sid, String username, String email, String name, String dob, String password, String year) {
        this.sid = sid;
        this.username = username;
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.password = password;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
