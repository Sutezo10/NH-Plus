package controller;

import datastorage.DAOFactory;
import datastorage.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LoginController {

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField usernameField;

    private static String loggedInUser = "";


    public void handleLoginButtonAction() {
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            checkInputs();
        } else {
            alertNoInputs();
        }

    }


    private void checkInputs() {
        UserDAO userDAO = DAOFactory.getDAOFactory().createUserDAO();
        List<User> allUsers;
        try {
            allUsers = userDAO.readAll();
            Optional<User> foundUser = allUsers.stream().filter(user -> usernameField.getText().equals(user.getUserName())).findAny();
            if (foundUser.isPresent()) {
                if (foundUser.get().getPassword().equals(passwordField.getText())) {
                    loggedInUser = foundUser.get().getUserName();
                    switchScene();
                } else {
                    alertPasswordNotCorrect();
                }
            } else {
                alertUsernameNotFound();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void switchScene() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
        try {
            Main.primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MainWindowController controller = loader.getController();
        controller.setUserText();
    }

    private void alertUsernameNotFound() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("User wurde nicht gefunden!");
        alert.setContentText("Es wurde kein User mit diesem Username gefunden!");
        alert.showAndWait();
    }

    private void alertPasswordNotCorrect() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Falsches Passwort!");
        alert.setContentText("Das eingegebene Passwort ist falsch!");
        alert.showAndWait();
    }

    private void alertNoInputs() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Felder wurden nicht ausgefüllt!");
        alert.setContentText("Fülle beide Felder mit deinen Daten aus!");
        alert.showAndWait();
    }


    public static String getLoggedInUser() {
        return loggedInUser;
    }
}
