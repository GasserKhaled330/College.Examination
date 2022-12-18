package com.college_examination_system;

import com.college_examination_system.utils.FxmlLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FxmlLoader.load("login-view.fxml",650,400,"User Log in");
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());
//        primaryStage.initStyle(StageStyle.DECORATED);
//        primaryStage.setTitle("User Log in");
//        primaryStage.setScene(scene);
//        primaryStage.centerOnScreen();
//        primaryStage.setResizable(false);

        //primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
