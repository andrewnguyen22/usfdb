package Controller;

import App.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Teacher_Controller {
    public Label class_name;
    public Label professor_name;
    public Label description;
    public Label average_gpa;
    public TextField search_field;
    public TableView student_table;
    public Button class_action;
    private String cnum;
    private List<Student> enrolled_students = new ArrayList<>();
    int status = 0;//0 for not in class


    public void search() {
        try {
            //clear values
            status = 0;
            enrolled_students.clear();
            student_table.getColumns().clear();
            student_table.getItems().clear();
            class_name.setText("None");
            average_gpa.setText("0");
            description.setText("Search for a course to find Students");
            //Searching for course
            Course c = Queries.search(search_field.getText()).get(0);
            class_name.setText(c.getClass_name());
            //Search for professor of that course
            Teacher t = Queries.getTeacher(c.getTeacher());
            assert t != null;

                System.out.println(t.getTid());
                professor_name.setText(t.getName());
                description.setText(c.getDescriptor());
                //Get SID of enrolled
                List<Enrolled> enrolled_list = Queries.getEnrolled(c.getCnum());
                assert enrolled_list != null;
                double totalgpa = 0; //To calc avg gpa
                int student_count = 0; //To calc avg gpa
                //create columns for table
                TableColumn<TableList, String> name_column = new TableColumn<>("Name");
                name_column.setCellValueFactory(new PropertyValueFactory<>("Name"));
                TableColumn<TableList, String> dob_column = new TableColumn<>("DOB");
                dob_column.setCellValueFactory(new PropertyValueFactory<>("dob"));
                TableColumn<TableList, String> email_column = new TableColumn<>("Email");
                email_column.setCellValueFactory(new PropertyValueFactory<>("Email"));
                TableColumn<TableList, Double> GPA_column = new TableColumn<>("GPA");
                GPA_column.setCellValueFactory(new PropertyValueFactory<>("GPA"));
                TableColumn<TableList, String> year_column = new TableColumn<>("Year");
                year_column.setCellValueFactory(new PropertyValueFactory<>("Year"));
                for (Enrolled e : enrolled_list) {
                    //For Each Student that is enrolled
                    Student s = Queries.getStudents(e.getSid());
                    //Enroll Globally
                    enrolled_students.add(s);
                    //calc total gpa
                    totalgpa += Double.parseDouble(e.getGrade());
                    ++student_count;
                    //add to table
                    TableList tableList = new TableList(s.getName(), s.getDob(), s.getEmail(),
                            Double.parseDouble(e.getGrade()), s.getYear());
                    student_table.getItems().add(tableList);
                }
                student_table.getColumns().addAll(name_column, dob_column, email_column, GPA_column, year_column);
                student_table.styleProperty().setValue("-fx-font-size:10px;");
                average_gpa.setText(Double.toString(totalgpa / (double) student_count));
                //Check if logged in user is within class
                for (Student s : enrolled_students) {
                    //assuming usernames are prim keys as well
                    //TODO add in functionality
                    if (Global.getUsername().equals(s.getUsername()))
                        status = 1;
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void class_action() {
//        if (status == 0) {
//            //register
//            System.out.println("Registering for class");
//            try {
//                Queries.register_for_class(Global.getSid(), cnum);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else {
//            //drop
//            System.out.println("Dropping Class");
//            try {
//                Queries.drop_class(Global.getSid(), cnum);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        search();
//    }

    public void toLogin() throws IOException {
        System.out.println("Switching to dashboard");
        FXMLLoader loader = new FXMLLoader(Init_Controller.class.getClassLoader().getResource("init.fxml"));
        Parent root = loader.load();
        Global.getStage().setScene(new Scene(root));
        Global.setLoader(loader);
        centerStage(Global.getStage(), Global.getStage().getWidth(), Global.getStage().getHeight());
    }

    private void centerStage(Stage stage, double width, double height) {
        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }
}
