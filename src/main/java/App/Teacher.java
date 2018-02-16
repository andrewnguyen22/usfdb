package App;

public class Teacher {
    private String tid;
    private String name;
    private String username;
    private String salary;
    private String dob;
    private String password;
    private String email;

    public Teacher(String tid, String name, String username, String salary, String dob, String password, String email) {
        this.tid = tid;
        this.name = name;
        this.username = username;
        this.salary = salary;
        this.dob = dob;
        this.password = password;
        this.email = email;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
