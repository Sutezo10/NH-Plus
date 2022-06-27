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

/**
 * The <code>MainWindowController</code> contains the entire logic of the mainwindow view. It determines which data is displayed and how to react to events.
 */
public class MainWindowController {

    @FXML
    public Text userInfo;
    @FXML
    private BorderPane mainBorderPane;

    /**
     * Initializes a starting title to greet the user
     */
    public void initialize() {
        Label startingText = new Label("NH-Plus");
        startingText.setFont(new Font("Bahnschrift", 36));
        startingText.setStyle("-fx-font-weight: bold");
        mainBorderPane.setCenter(startingText);
    }

    /**
     * Handles the action to show the patient view
     * is connected to the patient-button
     * */
    @FXML
    private void handleShowAllPatient() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the action to show the treatment view
     * is connected to the treatment-button
     */
    @FXML
    private void handleShowAllTreatments() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sets an information text for the user to see with which account he is currently logged in
     */
    public void setUserText() {
        userInfo.setText("Angemeldet als: \n" + LoginController.getLoggedInUser());
        userInfo.setFont(new Font("ARIAL", 18));
        userInfo.setStyle("-fx-font-weight: bold;");
        userInfo.setFill(Color.WHITE);
    }

    /**
     * Handles the action to log out of the application and switch to log in page
     * is connected to the logout-button
     */
    @FXML
    public void handleLogout() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
        try {
            Main.primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the action to show the caregiver view
     * is connected to the caregiver-button
     */
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
