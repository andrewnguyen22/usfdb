package Controller;

import App.Queries;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_Controller {
    public PasswordField pass_field;
    public TextField username_field;
    public Button register_entry;
    public TextField email_field;
    public TextField total_classes_field;
    public TextField name_field;
    public TextField dob_field;
    public Button back_to_login;
    public ComboBox year_dropdown;

    public void initialize(){
        year_dropdown.getItems().add("Freshman");
        year_dropdown.getItems().add("Sophomore");
        year_dropdown.getItems().add("Junior");
        year_dropdown.getItems().add("Senior");
    }

    public void register() {
        try {
            //if true -> studentLogin else create alert
            try {
                boolean goodRegister = true;
                if(name_field.getText()==null||name_field.getText().isEmpty()) {
                    createAlert();
                    goodRegister = false;
                }
                if(email_field.getText()==null||name_field.getText().isEmpty()||!isEmail(email_field.getText())) {
                    createAlert();
                    goodRegister = false;

                }
                if(dob_field.getText()==null||name_field.getText().isEmpty()) {
                    createAlert();
                    goodRegister = false;

                }
                if(pass_field.getText()==null||name_field.getText().isEmpty()) {
                    createAlert();
                    goodRegister = false;

                }
                if(year_dropdown.getValue().toString()==null||name_field.getText().isEmpty()) {
                    createAlert();
                    goodRegister = false;

                }

                //Only registers if all fields are filled.
                if (goodRegister == true) {
                    Queries.register(username_field.getText(),
                            email_field.getText(),
                            name_field.getText(),
                            dob_field.getText(),
                            pass_field.getText(),
                            year_dropdown.getValue().toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            toLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toLogin() throws IOException {
        System.out.println("Switching to dashboard");
        FXMLLoader loader = new FXMLLoader(Init_Controller.class.getClassLoader().getResource("init.fxml"));
        Parent root = loader.load();
        Global.getStage().setScene(new Scene(root));
        Global.setLoader(loader);
    }

    private void toDashboard() throws IOException {
        System.out.println("Switching to dashboard");
        FXMLLoader loader = new FXMLLoader(Init_Controller.class.getClassLoader().getResource("main.fxml"));
        Parent root = loader.load();
        Global.getStage().setScene(new Scene(root));
        Global.setLoader(loader);
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
        alert.setHeaderText("Please Fill Out All Fields!");
        alert.show();
    }
    private boolean isEmail(String email){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        return mat.matches();
    }
}
