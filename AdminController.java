package com.example.hrvivek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public TableView<Admin> userTable;
    public TableColumn<Admin, Integer> id;
    public TableColumn<Admin, String> name;
    public TableColumn<Admin, String> email;
    public TableColumn<Admin, String> password;
    public TextField uid;
    public TextField uname;
    public TextField uemail;
    public TextField upassword;
    public Button viewbutton;
    @FXML
    private Label welcomeText;

    ObservableList<Admin> list = FXCollections.observableArrayList();

    @FXML
    protected void onHelloButtonClick() {
        fethdata();
    }

    private void fethdata() {
        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/xamp";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM Admin";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                userTable.getItems().add(new Admin(id, name, email, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Admin, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<Admin, String>("password"));
        userTable.setItems(list);


    }


    public void InsertData(ActionEvent actionEvent) {

        String name = uname.getText();
        String email = uemail.getText();
        String password = upassword.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/xamp";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO Admin( name, email, password) VALUES ('" + name + "','" + email + "','" + password + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateData(ActionEvent actionEvent) {

        String id = uid.getText();
        String name = uname.getText();
        String email = uemail.getText();
        String password = upassword.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/xamp";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "UPDATE admin SET name='" + name + "',email='" + email + "',password='" + password + "' WHERE id='" + id + "' ";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteData(ActionEvent actionEvent) {

        String id = uid.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/Xamp";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM admin WHERE id='" + id + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Back(ActionEvent actionEvent) {

        try {

            Parent secondScene = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("dash Scene");
            secondStage.setScene(new Scene(secondScene));

            Stage firstSceneStage = (Stage) viewbutton.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}