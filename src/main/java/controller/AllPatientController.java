package controller;

import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Patient;
import utils.Alerts;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static utils.GeneralCheckMethods.checkLetterInput;
import static utils.GeneralCheckMethods.checkNumberInput;


/**
 * The <code>AllPatientController</code> contains the entire logic of the patient view. It determines which data is displayed and how to react to events.
 */
public class AllPatientController {
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient, Integer> colID;
    @FXML
    private TableColumn<Patient, String> colFirstName;
    @FXML
    private TableColumn<Patient, String> colSurname;
    @FXML
    private TableColumn<Patient, String> colDateOfBirth;
    @FXML
    private TableColumn<Patient, String> colCareLevel;
    @FXML
    private TableColumn<Patient, String> colRoom;

    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtFirstname;
    @FXML
    DatePicker birthdayPicker;
    @FXML
    TextField txtCarelevel;
    @FXML
    TextField txtRoom;


    private final ObservableList<Patient> tableviewContent = FXCollections.observableArrayList();
    private PatientDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        birthdayPicker.setValue(LocalDate.of(1970, Month.JANUARY, 1));
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<>("pid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        this.colDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colCareLevel.setCellValueFactory(new PropertyValueFactory<>("careLevel"));
        this.colCareLevel.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colRoom.setCellValueFactory(new PropertyValueFactory<>("roomnumber"));
        this.colRoom.setCellFactory(TextFieldTableCell.forTableColumn());


        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * handles new firstname value
     *
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Patient, String> event) {
        if (checkLetterInput(event.getNewValue())) {
            event.getRowValue().setFirstName(event.getNewValue());
            doUpdate(event);
        } else {
            Alerts.wrongOrMissingDataAlert();
            readAllAndShowInTableView();
        }

    }

    /**
     * handles new surname value
     *
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Patient, String> event) {
        if (checkLetterInput(event.getNewValue())) {
            event.getRowValue().setSurname(event.getNewValue());
            doUpdate(event);
        } else {
            Alerts.wrongOrMissingDataAlert();
            readAllAndShowInTableView();
        }

    }

    /**
     * handles new birthdate value
     *
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditDateOfBirth(TableColumn.CellEditEvent<Patient, String> event) {
        LocalDate lclDate;
        try {
            lclDate = LocalDate.parse(event.getNewValue());
            event.getRowValue().setDateOfBirth(String.valueOf(lclDate));
            doUpdate(event);
        } catch (Exception e) {
            Alerts.wrongOrMissingDataAlert();
            readAllAndShowInTableView();
        }
    }

    /**
     * handles new carelevel value
     *
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditCareLevel(TableColumn.CellEditEvent<Patient, String> event) {
        if (checkNumberInput(event.getNewValue())) {
            event.getRowValue().setCareLevel(event.getNewValue());
            doUpdate(event);
        } else {
            Alerts.wrongOrMissingDataAlert();
            readAllAndShowInTableView();
        }
    }

    /**
     * handles new roomnumber value
     *
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditRoomnumber(TableColumn.CellEditEvent<Patient, String> event) {
        if (checkNumberInput(event.getNewValue())) {
            event.getRowValue().setRoomnumber(event.getNewValue());
            doUpdate(event);
        } else {
            Alerts.wrongOrMissingDataAlert();
            readAllAndShowInTableView();
        }
    }


    /**
     * updates a patient by calling the update-Method in the {@link PatientDAO}
     *
     * @param t row to be updated by the user (includes the patient)
     */
    private void doUpdate(TableColumn.CellEditEvent<Patient, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * calls readAll in {@link PatientDAO} and shows patients in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createPatientDAO();
        List<Patient> allPatients;
        try {
            allPatients = dao.readAll();
            this.tableviewContent.addAll(allPatients);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link PatientDAO} and {@link TreatmentDAO}
     */
    @FXML
    public void handleDeleteRow() {
        TreatmentDAO tDao = DAOFactory.getDAOFactory().createTreatmentDAO();
        Patient selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                tDao.deleteByPid(selectedItem.getPid());
                dao.deleteById(selectedItem.getPid());
                this.tableView.getItems().remove(selectedItem);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Alerts.noSelectionToDelete();
        }

    }

    /**
     * handles a add-click-event. Creates a patient and calls the create method in the {@link PatientDAO}
     */
    @FXML
    public void handleAdd() {
        String surname = this.txtSurname.getText();
        String firstname = this.txtFirstname.getText();
        LocalDate date = birthdayPicker.getValue();
        String carelevel = this.txtCarelevel.getText();
        String room = this.txtRoom.getText();
        if (checkLetterInput(surname) && checkLetterInput(firstname) && checkNumberInput(carelevel) && checkNumberInput(room)) {
            try {
                Patient p = new Patient(firstname, surname, date, carelevel, room);
                dao.create(p);
            } catch (Exception e) {
                Alerts.wrongOrMissingDataAlert();
            }
            readAllAndShowInTableView();
            clearAllFields();
        } else {
            Alerts.wrongDataAlert();
        }

    }


    /**
     * removes content from all fields
     */
    private void clearAllFields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.birthdayPicker.getEditor().clear();
        this.txtCarelevel.clear();
        this.txtRoom.clear();
    }


}