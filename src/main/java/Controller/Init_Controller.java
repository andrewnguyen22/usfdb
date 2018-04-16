package Controller;

import App.Queries;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Init_Controller {
    @FXML
    public TextField username_field;
    public PasswordField pass_field;

    public void login() {
        try {
            //Login Query Here
            //if true -> studentLogin else create alert
            try {
                boolean success = Queries.studentLogin(username_field.getText(),pass_field.getText());
                if(success) {
                    Global.setUsername(username_field.getText());
                    Global.setPass(pass_field.getText());
                    Queries.getSid();
                    toStudentDashboard();
                }
                else{
                    success = Queries.teacherLogin(username_field.getText(),pass_field.getText());
                    if(success) {
                        Global.setUsername(username_field.getText());
                        Global.setPass(pass_field.getText());
                        Queries.getInfo();
                        toTeacherDashboard();
                    }
                    else {
                        createAlert();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void register() throws IOException {
        System.out.println("Switching to register");
        FXMLLoader loader = new FXMLLoader(Init_Controller.class.getClassLoader().getResource("register.fxml"));
        Parent root = loader.load();
        Global.getStage().setScene(new Scene(root));
        Global.setLoader(loader);
        centerStage(Global.getStage(), Global.getStage().getWidth(), Global.getStage().getHeight());
        System.out.println("Showing Register");
    }
    private void toStudentDashboard() throws IOException {
        System.out.println("Switching to dashboard");
        FXMLLoader loader = new FXMLLoader(Init_Controller.class.getClassLoader().getResource("main.fxml"));
        Parent root = loader.load();
        Global.getStage().setScene(new Scene(root));
        Global.setLoader(loader);
        centerStage(Global.getStage(), Global.getStage().getWidth(), Global.getStage().getHeight());
        System.out.println("Showing Dashboard");
    }
    private void toTeacherDashboard() throws IOException {
        System.out.println("Switching to dashboard");
        FXMLLoader loader = new FXMLLoader(Init_Controller.class.getClassLoader().getResource("teacher.fxml"));
        Parent root = loader.load();
        Global.getStage().setScene(new Scene(root));
        Global.setLoader(loader);
        Teacher_Controller t = loader.getController();
        t.init();
        centerStage(Global.getStage(), Global.getStage().getWidth(), Global.getStage().getHeight());
        System.out.println("Showing Dashboard");
    }


    private void centerStage(Stage stage, double width, double height) {
        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }
    private void createAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Login Failed!");
        alert.show();
    }
}
