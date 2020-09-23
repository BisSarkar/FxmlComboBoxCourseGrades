/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import stockofproducts.Stock;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CourseController implements Initializable {

    @FXML
    private Label lblCc;
    @FXML
    private ComboBox<String> lblCombo;
    @FXML
    private Label lblCourseCode;
    @FXML
    private Label lblCourseGrade;
    @FXML
    private Label lblMaxGrade;

    @FXML
    private TextField txtCourseCode;
    @FXML
    private TextField txtCourseGrade;
    @FXML
    private TextField txtMaxGrade;

    @FXML
    private Label lblError;

    private ObservableList<String> observableList = FXCollections.observableArrayList();

    String comboIndex;
    Map<String, Course> courseMap = new HashMap<String, Course>();
    Course course = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lblCc.setText("Select a course from list");
        lblCourseCode.setText("Course Code");
        lblCourseGrade.setText("Course Grade");
        lblMaxGrade.setText("Max Grade");
        int c = 0;
        File file = new File("E:\\CourseList.txt");
        if (file.exists()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (scanner.hasNextLine()) {

               
                String s = scanner.nextLine();
                String[] fields = s.split(",");
                if (fields.length > 0) {
                    Course course = new Course(fields[0],Double.parseDouble(fields[1]),Double.parseDouble(fields[2]));
                   // course.setCode(fields[0]);
                   // course.setGrade(Double.parseDouble(fields[1]));
                   // course.setMaxGrade(Double.parseDouble(fields[2]));
                    observableList.add(c, fields[0]);
                    c++;
                     
                    courseMap.put(fields[0], course);

                }
            }

            lblCombo.setItems(observableList);

            lblCombo.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue ov, String t, String t1) {
                    comboIndex = t1;
                    course = courseMap.get(t1);
                    txtCourseCode.setText(course.getCode());
                    txtCourseGrade.setText(String.valueOf(course.getGrade()));
                    txtMaxGrade.setText(String.valueOf(course.getMaxGrade()));

                }
            });

            scanner.close();

        }

    }

    @FXML
    private void editCourse(ActionEvent event) {
        String courseCode = txtCourseCode.getText();
        String courseGrade = txtCourseGrade.getText();
        String maxGrade = txtMaxGrade.getText();
        if (isNumberValidation(courseGrade)) {
            Course oldCourse = courseMap.get(courseCode);
            oldCourse.setGrade(Double.parseDouble(courseGrade));
            courseMap.put(courseCode, oldCourse);

        } else {
            lblError.setText("Invalid Data!!!!!");
        }

        try {

            FileWriter fstream = new FileWriter("E:\\CourseList.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            for (Course newCourse : courseMap.values()) {
                String line = newCourse.getCode() + "," + newCourse.getGrade() + "," + newCourse.getMaxGrade();
                out.write(line);
                out.write("\n");
            }

            //Close the output stream
            out.close();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

    @FXML
    private void exitCourse(ActionEvent event) {
        System.exit(0);
    }

    public boolean isNumberValidation(String fieldName) {
        boolean isValid = false;

        int count = 0;
        if (fieldName.contains(".")) {
            return true;
        }
        if (fieldName != null && fieldName.length() > 0) {
            for (int i = 0; i < fieldName.length(); i++) {
                if (Character.isDigit(fieldName.charAt(i))) {
                    count++;
                }
            }
            if (count == fieldName.length()) {
                isValid = true;
            }
        }

        return isValid;
    }

}
