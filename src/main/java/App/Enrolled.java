package App;

public class Enrolled {
    private String sid;
    private String cnum;
    private String grade;

    public Enrolled(String sid, String cnum, String grade) {
        this.sid = sid;
        this.cnum = cnum;
        this.grade = grade;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
