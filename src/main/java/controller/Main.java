package controller;

import datastorage.ConnectionBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Class which contains the content of the Java-Fx starting process
 */
public class Main extends Application {

    public static Stage primaryStage;


    /**
     * Overwritten starting method of Java-Fx
     * @param primaryStage used Stage to display the scenes
     */
    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        loginWindow();
    }

    /**
     * Opens the login window and initializes some information about the application
     */
    public void loginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
            GridPane pane = loader.load();

            Scene scene = new Scene(pane);
            Main.primaryStage.setTitle("NHPlus");
            Main.primaryStage.setScene(scene);
            Main.primaryStage.setResizable(false);
            Main.primaryStage.show();

            Main.primaryStage.setOnCloseRequest(e -> {
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}