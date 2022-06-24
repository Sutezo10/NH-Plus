package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainWindowController {

    @FXML
    public Text userInfo;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
        Label startingText = new Label("NH-Plus");
        startingText.setFont(new Font("Bahnschrift", 36));
        startingText.setStyle("-fx-font-weight: bold");
        mainBorderPane.setCenter(startingText);
    }


    @FXML
    private void handleShowAllPatient() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleShowAllTreatments() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setUserText() {
        userInfo.setText("Angemeldet als: \n" + LoginController.getLoggedInUser());
        userInfo.setFont(new Font("ARIAL", 18));
        userInfo.setStyle("-fx-font-weight: bold;");
        userInfo.setFill(Color.WHITE);
    }

    @FXML
    public void handleLogout() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
        try {
            Main.primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleShowAllCaregiver() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllCaretakerView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
