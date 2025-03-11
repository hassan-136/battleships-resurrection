package com.example.project_0;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;


public class Options {
    @FXML
    private Button browseButton;
    @FXML
    private Label labelm;

    @FXML
    protected void browseButtonOnAction(ActionEvent e) {
        labelm.setText("Invalid username or password");
        clickBrowse();
    }
    public  void clickBrowse(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("options2.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Options2");
            stage.show();

            // Close current login window
            Stage currentStage = (Stage) browseButton.getScene().getWindow();
            currentStage.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}