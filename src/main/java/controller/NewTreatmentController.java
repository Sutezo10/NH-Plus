package controller;

import datastorage.CaretakerDAO;
import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caretaker;
import model.Patient;
import model.Treatment;
import utils.Alerts;
import utils.ControllerConstants;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The <code>NewTreatmentController</code> contains the entire logic of the new treatment view. It determines which data is displayed and how to react to events.
 */
public class NewTreatmentController {

    @FXML
    private ComboBox<String> cmbxCaretaker;
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;

    private final ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();

    private ArrayList<Caretaker> caretakerList;
    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;

    /**
     * Initializes the view with given data
     *
     * @param controller sets val of the private var
     * @param stage      sets the val of the private var
     * @param patient    set the val of the private var
     */
    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller = controller;
        this.patient = patient;
        this.stage = stage;
        showPatientData();
        createComboBoxData();
        cmbxCaretaker.setItems(myComboBoxData);
        cmbxCaretaker.getSelectionModel().select(0);

    }

    /**
     * Adds the chooseable data out of the patient database to the combobox
     */
    private void createComboBoxData() {
        CaretakerDAO caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
        try {
            caretakerList = (ArrayList<Caretaker>) caretakerDAO.readAll();
            caretakerList.remove(0);
            for (Caretaker caretaker : caretakerList) {
                this.myComboBoxData.add(caretaker.getSurname());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the surname and firstname of the patient
     */
    private void showPatientData() {
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    /**
     * Handles the action to add a new treatment
     * is connected to the add-button
     */
    @FXML
    public void handleAdd() {
        LocalDate date = this.datepicker.getValue();
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        Caretaker caretaker = searchInList(cmbxCaretaker.getSelectionModel().getSelectedItem());
        try {
            LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
            LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
            assert caretaker != null;
            Treatment treatment = new Treatment(patient.getPid(), caretaker.getCid(), date,
                    begin, end, description, remarks, ControllerConstants.UNLOCKED, LocalDate.now());
            createTreatment(treatment);
            controller.readAllAndShowInTableView();
            stage.close();
        } catch (Exception e) {
            Alerts.wrongOrMissingDataAlert();
        }
    }

    /**
     * This method returns a matching caretaker from a given surname
     *
     * @param surname is used to find a matching caretaker
     * @return the matching caretaker from the surname
     */
    private Caretaker searchInList(String surname) {
        for (Caretaker caretaker : this.caretakerList) {
            if (caretaker.getSurname().equals(surname)) {
                return caretaker;
            }
        }
        return null;
    }

    /**
     * Creates the new treatment with {@link TreatmentDAO}
     *
     * @param treatment which will we created
     */
    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action to cancel and close the new treatment view
     * is connected to the cancel-button
     */
    @FXML
    public void handleCancel() {
        stage.close();
    }
}