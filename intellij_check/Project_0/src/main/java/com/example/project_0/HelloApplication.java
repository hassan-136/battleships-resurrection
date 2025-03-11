package com.example.project_0;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class HelloApplication extends Application {
    @FXML
    private Label slogan; // This will be injected from FXML

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the logo screen
        FXMLLoader logoLoader = new FXMLLoader(getClass().getResource("logo.fxml"));
        Parent logoRoot = logoLoader.load();

        // Get controller to access UI elements
        Logo logoController = logoLoader.getController();

        Scene logoScene = new Scene(logoRoot);
        Stage logoStage = new Stage();
        logoStage.initStyle(StageStyle.UNDECORATED);
        logoStage.setScene(logoScene);
        logoStage.show();

        // Delay for showing the slogan label after 1.5s
        PauseTransition sloganDelay = new PauseTransition(Duration.seconds(1));
        sloganDelay.setOnFinished(event -> {
            logoController.showSloganWithAnimation();
        });
        sloganDelay.play();

        // Delay before switching to the login screen
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> {
            logoStage.close();
            try {
                // Load and show the login screen
                FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent loginRoot = loginLoader.load();
                Scene loginScene = new Scene(loginRoot);

                primaryStage.setTitle("Login Window");
                primaryStage.setScene(loginScene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        delay.play();
    }
}
