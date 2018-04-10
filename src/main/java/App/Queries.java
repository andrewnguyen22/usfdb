package App;

import Controller.Global;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Queries {
    final static String register_query =
            "INSERT into students (username, email, name, dob, pass, year)"
                    + " values (?,?,?,?,?,?)";
    final static String student_login_query =
            "SELECT sid FROM students WHERE username = ? AND pass=?";
    final static String teacher_login_query =
            "SELECT tid FROM teachers WHERE username = ? AND pass=?";
    final static String search_query =
            "SELECT * FROM course WHERE class_name LIKE ?";
    final static String teacher_query =
            "SELECT * FROM teachers WHERE tid=?";
    final static String enrolled_query =
            "SELECT * FROM enrolled WHERE cnum=?";
    final static String student_query =
            "SELECT * FROM students WHERE sid=?";
    final static String register_for_class =
            "INSERT into enrolled (sid, cnum, s_grade)"
                    + " values (?,?,?)";
    final static String drop_class =
            "DELETE FROM enrolled WHERE sid=? AND cnum=?";
    final static String getSid =
            "SELECT sid FROM students WHERE username=?";
    final static String getInfo =
            "SELECT tid, name, salary FROM teachers WHERE username=?";
    //TODO add teachers page

    public static String getSid() throws SQLException {
        Connection con = Global.getConnection();
        PreparedStatement pstmt = con.prepareStatement(getSid);
        pstmt.setString(1, Global.getUsername());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Global.setSid(rs.getString("sid"));
        }
        return null;
    }

    public static String getInfo() throws SQLException {
        Connection con = Global.getConnection();
        PreparedStatement pstmt = con.prepareStatement(getInfo);
        pstmt.setString(1, Global.getUsername());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Global.setTid(rs.getString("tid"));
            Global.setName(rs.getString("name"));
            Global.setSalary(rs.getString("salary"));
        }
        return null;
    }

    public static List<Course> search(String search_string) throws SQLException {
        System.out.println(search_string);
        Connection con = Global.getConnection();
        PreparedStatement pstmt = con.prepareStatement(search_query);
        pstmt.setString(1, "%" + search_string + "%");
        ResultSet rs = pstmt.executeQuery();
        List<Course> l = new ArrayList<>();
        String cnum;
        String descriptor;
        String class_name;
        String teacher;
        Course c;
        while (rs.next()) {
            cnum = rs.getString(1);
            descriptor = rs.getString(2);
            class_name = rs.getString(3);
            teacher = rs.getString(4);
            c = new Course(cnum, descriptor, class_name, teacher);
            l.add(c);
            cnum = "";
            descriptor = "";
            class_name = "";
            teacher = "";
        }
        return l;
    }

    public static Teacher getTeacher(String tid_search) {
        Connection conn = Global.getConnection();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(teacher_query);
            preparedStmt.setString(1, tid_search);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                String tid = rs.getString("tid");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String salary = rs.getString("salary");
                String dob = rs.getString("dob");
                String pass = rs.getString("pass");
                String email = rs.getString("email");
                Teacher t = new Teacher(tid, name, username, salary, dob, pass, email);
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Enrolled> getEnrolled(String cnum_search) {
        Connection conn = Global.getConnection();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(enrolled_query);
            preparedStmt.setString(1, cnum_search);

            List<Enrolled> enrolled_students = new ArrayList<>();
            ResultSet rs = preparedStmt.executeQuery();
            Enrolled e;
            while (rs.next()) {
                String sid = rs.getString("sid");
                String cnum = rs.getString("cnum");
                String s_grade = rs.getString("s_grade");

                e = new Enrolled(sid, cnum, s_grade);
                enrolled_students.add(e);
            }
            return enrolled_students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Student getStudents(String sid_search) {
        Connection conn = Global.getConnection();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(student_query);
            preparedStmt.setString(1, sid_search);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                String sid = rs.getString("sid");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String pass = rs.getString("pass");
                String year = rs.getString("year");
                return new Student(sid, username, email, name, dob, pass, year);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean studentLogin(String username, String password) throws SQLException {
        Connection conn = Global.getConnection();
        // create the mysql insert prepared statement
        PreparedStatement preparedStmt = conn.prepareStatement(student_login_query);
        preparedStmt.setString(1, username);
        preparedStmt.setString(2, password);
        ResultSet rs = preparedStmt.executeQuery();
        return rs.next();
    }

    public static boolean teacherLogin(String username, String password) throws SQLException {
        Connection conn = Global.getConnection();
        // create the mysql insert prepared statement
        PreparedStatement preparedStmt = conn.prepareStatement(teacher_login_query);
        preparedStmt.setString(1, username);
        preparedStmt.setString(2, password);
        ResultSet rs = preparedStmt.executeQuery();
        return rs.next();
    }

    public static void register(String username, String email,
                                String name, String dob,
                                String pass, String year) throws SQLException {
        Connection conn = Global.getConnection();
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(register_query);
        preparedStmt.setString(1, username);
        preparedStmt.setString(2, email);
        preparedStmt.setString(3, name);
        preparedStmt.setString(4, dob);
        preparedStmt.setString(5, pass);
        preparedStmt.setString(6, year);

        // execute the prepared statement
        preparedStmt.execute();
    }

    public static void register_for_class(String sid, String cnum) throws SQLException {
        //random gpa gen
        Random r = new Random();
        double gpa = 3+(4-3) * r.nextDouble();
        DecimalFormat df = new DecimalFormat("#.00");
        Connection con = Global.getConnection();
        PreparedStatement preparedStmt = con.prepareStatement(register_for_class);
        preparedStmt.setString(1, sid);
        preparedStmt.setString(2, cnum);
        preparedStmt.setString(3, df.format(gpa));
        // execute the prepared statement
        preparedStmt.execute();
    }

    public static void drop_class(String sid, String cnum) throws SQLException {
        Connection con = Global.getConnection();
        PreparedStatement preparedStmt = con.prepareStatement(drop_class);
        preparedStmt.setString(1, sid);
        preparedStmt.setString(2, cnum);
        preparedStmt.execute();
    }
}
