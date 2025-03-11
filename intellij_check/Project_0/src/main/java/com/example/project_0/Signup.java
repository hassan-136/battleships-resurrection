package com.example.project_0;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Signup {
    @FXML
    private TextField firstText;
    @FXML
    private TextField lastText;
    @FXML
    private TextField userText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField passText;
    @FXML
    private TextField accountText;
    @FXML
    private TextField balanceText;
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Label error2Label;

    @FXML
    protected void signupButtonOnAction(ActionEvent e) {
        if (validateInputs()) {
            registerUser();
        }
    }

    private boolean validateInputs() {
        if (firstText.getText().isEmpty() || lastText.getText().isEmpty() ||
                userText.getText().isEmpty() || emailText.getText().isEmpty() ||
                passText.getText().isEmpty() || accountText.getText().isEmpty() ||
                balanceText.getText().isEmpty()) {

            error2Label.setText("All fields are required!");
            return false;
        }

        try {
            Double.parseDouble(balanceText.getText()); // Ensure balance is a number
        } catch (NumberFormatException e) {
            error2Label.setText("Balance must be a valid number!");
            return false;
        }

        return true;
    }

    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String checkUserQuery = "SELECT * FROM useraccounts WHERE userName = ?";
        String insertUserQuery = "INSERT INTO useraccounts (firstName, lastName, userName, email, password) VALUES (?, ?, ?, ?, ?)";
        String insertBalanceQuery = "INSERT INTO balance (accountNumber, userName, balance) VALUES (?, ?, ?)";

        try {
            PreparedStatement checkStatement = connectDB.prepareStatement(checkUserQuery);
            checkStatement.setString(1, userText.getText());
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                errorLabel.setText("Username already taken. Choose another one.");
                return;
            }

            // Insert into useraccounts table
            PreparedStatement userStatement = connectDB.prepareStatement(insertUserQuery);
            userStatement.setString(1, firstText.getText());
            userStatement.setString(2, lastText.getText());
            userStatement.setString(3, userText.getText());
            userStatement.setString(4, emailText.getText());
            userStatement.setString(5, passText.getText());

            int userRowsAffected = userStatement.executeUpdate();

            if (userRowsAffected > 0) {
                // Insert into balance table
                PreparedStatement balanceStatement = connectDB.prepareStatement(insertBalanceQuery);
                balanceStatement.setString(1, accountText.getText());
                balanceStatement.setString(2, userText.getText());
                balanceStatement.setDouble(3, Double.parseDouble(balanceText.getText()));

                int balanceRowsAffected = balanceStatement.executeUpdate();

                if (balanceRowsAffected > 0) {
                    openLoginPage();
                } else {
                    errorLabel.setText("Signup failed while inserting balance. Try again.");
                }
            } else {
                errorLabel.setText("Signup failed. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("An error occurred. Try again later.");
        }
    }

    @FXML
    protected void loginButtonOnAction(ActionEvent e) {
        openLoginPage();
    }

    public void openLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            Stage currentStage = (Stage) signupButton.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
