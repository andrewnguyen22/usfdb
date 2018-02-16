package App;

import javafx.beans.property.SimpleStringProperty;

public class TableList {
    String name;
    String dob;
    String email;
    double GPA;
    String Year;


    public TableList(String name, String dob, String email, double GPA, String year) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.GPA = GPA;
        Year = year;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }
}
