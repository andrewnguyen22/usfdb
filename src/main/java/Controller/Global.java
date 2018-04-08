package Controller;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.sql.Connection;

public class Global {
    private static String username;
    private static String pass;
    private static Connection connection;
    private static FXMLLoader loader;
    private static Stage stage;
    private static String sid;
    private static String tid;
    private static String name;

    public static String getSid() {
        return sid;
    }

    public static void setSid(String sid) {
        Global.sid = sid;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Global.stage = stage;
    }

    public static FXMLLoader getLoader() {
        return loader;
    }

    public static void setLoader(FXMLLoader loader) {
        Global.loader = loader;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        Global.connection = connection;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        Global.pass = pass;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Global.username = username;
    }

    public static String getTid() { return tid; }

    public static void setTid(String tid) { Global.tid = tid; }

    public static String getName() { return name; }

    public static void setName(String name) { Global.name = name; }
}
