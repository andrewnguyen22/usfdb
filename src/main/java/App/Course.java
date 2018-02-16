package App;

public class Course {
    private String cnum;
    private String descriptor;
    private String class_name;
    private String teacher;

    public Course(String cnum, String descriptor, String class_name, String teacher) {
        this.cnum = cnum;
        this.descriptor = descriptor;
        this.class_name = class_name;
        this.teacher = teacher;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
