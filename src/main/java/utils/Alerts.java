package utils;

import javafx.scene.control.Alert;

public class Alerts {

    /**
     * General Alert
     */
    public static Alert wrongOrMissingDataAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ControllerConstants.ALERT_ERROR_TITLE);
        alert.setHeaderText("Fehlende/Falsche Daten in den Feldern!");
        alert.setContentText("Es wurde nicht alle Felder mit Daten befüllt oder es wurden Felder mit ungültigen Daten befüllt!");
        alert.showAndWait();
        return alert;
    }
    /**
     * General Alert
     */
    public static Alert wrongDataAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ControllerConstants.ALERT_ERROR_TITLE);
        alert.setHeaderText("Falsche Daten in den Feldern!");
        alert.setContentText("Es wurden Felder mit ungültigen Daten befüllt!");
        alert.showAndWait();
        return alert;
    }
    /**
     * General Alert
     */
    public static Alert missingDataAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ControllerConstants.ALERT_ERROR_TITLE);
        alert.setHeaderText("Fehlende Daten in den Feldern!");
        alert.setContentText("Es wurden nicht alle Felder mit Daten befüllt!");
        alert.showAndWait();
        return alert;
    }

    /**
     * AllPatient Alert
     */

    public static Alert noSelectionToDelete() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Es wurde nichts ausgewählt, was gelöscht werden kann!");
        alert.setContentText("Wähle eine Zeile in der Tabelle aus, um diese zu Löschen!");
        alert.showAndWait();
        return alert;
    }

    /**
     * AllTreatment Alert
     */
    public static Alert missingPatientAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Patient für die Behandlung fehlt!");
        alert.setContentText("Wählen Sie über die Combobox einen Patienten aus!");
        alert.showAndWait();
        return alert;
    }
    /**
     * AllTreatment Alert
     */
    public static Alert lockedTreatmentAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Diese Behandlung ist gesperrt!");
        alert.setContentText("Entsperre die Behandlung, um Änderungen vorzunehmen!");
        alert.showAndWait();
        return alert;
    }

    /**
     * AllTreatment Alert
     */
    public static Alert noSelectionToLockAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Es muss zuerst eine Behandlung ausgewählt werden!");
        alert.showAndWait();
        return alert;
    }

    /**
     * AllTreatment Alert
     */
    public static Alert sameLockStateAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Diese Behandlung befindet sich bereits in dem angeklickten Sperrstatus!");
        alert.showAndWait();
        return alert;
    }

    /**
     * AllTreatment Alert
     */
    public static Alert canNotLoadCaretakerIDAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ControllerConstants.ALERT_ERROR_TITLE);
        alert.setHeaderText("Fehler beim laden der Pfleger-ID!");
        alert.setContentText("Beim Laden der Informationen vom Pfleger ist ein Fehler aufgetreten");
        alert.showAndWait();
        return alert;
    }


    /**
     * Login Alert
     */

    public static Alert alertUsernameNotFound() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("User wurde nicht gefunden!");
        alert.setContentText("Es wurde kein User mit diesem Username gefunden!");
        alert.showAndWait();
        return alert;
    }
    /**
     * Login Alert
     */
    public static Alert alertPasswordNotCorrect() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Falsches Passwort!");
        alert.setContentText("Das eingegebene Passwort ist falsch!");
        alert.showAndWait();
        return alert;
    }
    /**
     * Login Alert
     */
    public static Alert alertNoInputs() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
        alert.setHeaderText("Felder wurden nicht ausgefüllt!");
        alert.setContentText("Fülle beide Felder mit deinen Daten aus!");
        alert.showAndWait();
        return alert;
    }



}
