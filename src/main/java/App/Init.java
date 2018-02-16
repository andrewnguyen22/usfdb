package App;

import Controller.Global;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Init extends Application {
    private static final Logger LOGGER = Logger.getLogger(Init.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Create Bootup Window
        System.out.println("Starting app.");
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("init.fxml"));
        Global.setLoader(loader);
        Global.setStage(stage);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(false);
        stage.show();
        System.out.println("Showing Ui.");
        Global.setConnection(DB_Tools.getConnection());
    }
}