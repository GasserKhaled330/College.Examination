package com.college_examination_system.utils;

import com.college_examination_system.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FxmlLoader {
    public static void load(String fxmlFileName,long minWidth,long minHeight,String title) {
        try  {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("fxml/"+fxmlFileName));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setMinWidth(minWidth);
            stage.setMinHeight(minHeight);
            stage.centerOnScreen();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Can't find fxml file " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public static void load(String fxmlFileName,String title,long minWidth,long minHeight,StageStyle stageStyle) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("fxml/"+fxmlFileName));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage(stageStyle);
            stage.setMinWidth(minWidth);
            stage.setMinHeight(minHeight);
            stage.centerOnScreen();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Can't find fxml file " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
