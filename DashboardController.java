package com.example.hrvivek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {

    @FXML
    private Button adminbutton;
    @FXML
    private Button employeebutton;
    @FXML
    private Button logoutbutton;

    @FXML
    public void admin(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Admin.fxml")));
            Stage secondStage = new Stage();
            secondStage.setTitle("Admin scene");
            secondStage.setScene(new Scene(secondScene));

            Stage firstSceneStage = (Stage) adminbutton.getScene().getWindow();
            firstSceneStage.close();

            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void employee(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("employee.fxml")));
            Stage secondStage = new Stage();
            secondStage.setTitle("EMPLOYEE SCENE");
            secondStage.setScene(new Scene(secondScene));

            Stage firstSceneStage = (Stage) employeebutton.getScene().getWindow();
            firstSceneStage.close();

            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logout(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            Stage secondStage = new Stage();
            secondStage.setTitle("LOGOUT SCENE");
            secondStage.setScene(new Scene(secondScene));

            Stage firstSceneStage = (Stage) logoutbutton.getScene().getWindow();
            firstSceneStage.close();

            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
