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

public class EmployeeController implements Initializable {

    public TableView<Employee> userTable;
    public TableColumn<Employee, Integer> id;
    public TableColumn<Employee, String> name;
    public TableColumn<Employee, String> email;
    public TableColumn<Employee, String> salary;
    public TextField uid;
    public TextField uname;
    public TextField uemail;
    public TextField usalary;

    public Button Backbutton;
    @FXML
    private Label welcomeText;

    ObservableList<Employee> list = FXCollections.observableArrayList();




    @FXML
    protected void onHelloButtonClick() {
        fetchdata();
    }

    private void fetchdata() {
        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/xamp";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String salary = resultSet.getString("salary");
                list.add(new Employee(id, name, email, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        userTable.setItems(list);
    }

    public void InsertData(ActionEvent actionEvent) {
        String name = uname.getText();
        String email = uemail.getText();
        String salary = usalary.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/xamp";
        String dbUser = "root";
        String dbPassword = "";
        String query = "INSERT INTO Employee (name, email, salary) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, salary);
            preparedStatement.executeUpdate();
            fetchdata();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateData(ActionEvent actionEvent) {
        int id = Integer.parseInt(uid.getText());
        String name = uname.getText();
        String email = uemail.getText();
        String salary = usalary.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/xamp";
        String dbUser = "root";
        String dbPassword = "";
        String query = "UPDATE Employee SET name = ?, email = ?, salary = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, salary);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            fetchdata();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteData(ActionEvent actionEvent) {
        int id = Integer.parseInt(uid.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/xamp";
        String dbUser = "root";
        String dbPassword = "";
        String query = "DELETE FROM Employee WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            fetchdata();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Back(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Stage secondStage = new Stage();
            secondStage.setTitle("Dash Scene");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) Backbutton.getScene().getWindow();
            firstSceneStage.close();
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}