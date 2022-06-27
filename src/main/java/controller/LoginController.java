package controller;

import datastorage.DAOFactory;
import datastorage.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.User;
import utils.Alerts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The <code>AllLoginController</code> contains the entire logic of the login view. It reacts to the update process of the user.
 */

public class LoginController {

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField usernameField;

    private static String loggedInUser = "";


    /**
     * Checks the login process of the user with his entered data
     */
    public void handleLoginButtonAction() {
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            checkInputs();
        } else {
            Alerts.alertNoInputs();
        }

    }

    /**
     * Compares the inputs of the user data with the database data and switches the scene to the main view
     * when the data is matching
     */
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
                    Alerts.alertPasswordNotCorrect();
                }
            } else {
                Alerts.alertUsernameNotFound();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switches the view to the main view
     */

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

    /**
     * Getter for the logged in user
     * @return logged in user
     */
    public static String getLoggedInUser() {
        return loggedInUser;
    }


    /**
     * Gives the user the possibility to log in with the enter-button
     * @param keyEvent used to get the keycode to see if it is the enter-button
     */
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            handleLoginButtonAction();
        }
    }
}
