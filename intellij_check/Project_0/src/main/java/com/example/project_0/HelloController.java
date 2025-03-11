package com.example.project_0;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button signupButton;
    @FXML
    protected void loginButtonOnAction(ActionEvent e) {

        validateLogin();

    }
    public  void validateLogin(){
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB= connectNow.getConnection();
        String verifyLogin="Select count(1) from useraccounts  where userName= '" + userNameText.getText()+"' AND password= '" + passwordText.getText()+"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("options.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Options");
                    stage.show();

                    // Close current login window
                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    currentStage.close();
                } else {
                    loginMessage.setText("Invalid username or password");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void signupButtonOnAction(ActionEvent e) {
        clickSign();
    }
    public  void clickSign() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Signup");
            stage.show();

            // Close current login window
            Stage currentStage = (Stage) signupButton.getScene().getWindow();
            currentStage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

