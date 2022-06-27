package controller;

import datastorage.CaretakerDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caretaker;
import model.Treatment;
import utils.Alerts;

import java.sql.SQLException;
import java.util.List;

import static utils.GeneralCheckMethods.checkLetterInput;
import static utils.GeneralCheckMethods.checkNumberInput;


/**
 * The <code>AllCaretakerController</code> contains the entire logic of the caretaker view. It determines which data is displayed and how to react to events.
 */
public class AllCaretakerController {

    @FXML
    public TableView<Caretaker> tableView;
    @FXML
    public TableColumn<Caretaker, Integer> colID;
    @FXML
    public TableColumn<Caretaker, String> colSurname;
    @FXML
    public TableColumn<Caretaker, String> colFirstName;
    @FXML
    public TableColumn<Caretaker, String> colTelephone;
    @FXML
    public TextField txfSurname;
    @FXML
    public TextField txfFirstname;
    @FXML
    public TextField txfTelephone;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnDelete;


    private final ObservableList<Caretaker> tableviewContent = FXCollections.observableArrayList();
    private CaretakerDAO caretakerDAO;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        this.colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colID.setCellValueFactory(new PropertyValueFactory<>("cid"));

        this.tableView.setItems(tableviewContent);
        readAllAndShowInTableView();
    }

    /**
     * Updates the passed data
     *
     * @param t data of the column, which need to be updated
     */
    private void doUpdate(TableColumn.CellEditEvent<Caretaker, String> t) {
        try {
            caretakerDAO.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the  edited var of the column surname
     *
     * @param event data of column, which was edited
     */
    public void handleEditSurname(TableColumn.CellEditEvent<Caretaker, String> event) {
        if (checkLetterInput(event.getNewValue())) {
            event.getRowValue().setSurname(event.getNewValue());
            doUpdate(event);
        } else {
            Alerts.wrongOrMissingDataAlert();
            readAllAndShowInTableView();
        }

    }

    /**
     * Handles the  edited var of the column firstname
     *
     * @param event data of column, which was edited
     */
    public void handleEditFirstName(TableColumn.CellEditEvent<Caretaker, String> event) {
        if (checkLetterInput(event.getNewValue())) {
            event.getRowValue().setFirstName(event.getNewValue());
            doUpdate(event);
        } else {
            Alerts.wrongOrMissingDataAlert();
            readAllAndShowInTableView();
        }

    }

    /**
     * Handles the  edited var of the column phonenumber
     *
     * @param event data of column, which was edited
     */
    public void handleEditTelephone(TableColumn.CellEditEvent<Caretaker, String> event) {
        if (checkNumberInput(event.getNewValue())) {
            event.getRowValue().setPhoneNumber(event.getNewValue());
            doUpdate(event);
        } else {
            Alerts.wrongOrMissingDataAlert();
            readAllAndShowInTableView();
        }

    }

    /**
     * Handles the action to add a caregiver to the database and reloads the table
     * This Method is connected to the add-button
     */
    @FXML
    public void handleAddCaregiver() {
        String surname = this.txfSurname.getText();
        String firstname = this.txfFirstname.getText();
        String telephoneNumber = this.txfTelephone.getText();
        if ((!surname.isEmpty() && !firstname.isEmpty() && !telephoneNumber.isEmpty()) &&
                (checkLetterInput(surname) && checkLetterInput(firstname) && checkNumberInput(telephoneNumber))) {
            try {
                this.caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
                Caretaker c = new Caretaker(firstname, surname, telephoneNumber);
                caretakerDAO.create(c);
            } catch (Exception e) {
                Alerts.wrongDataAlert();
            }
            readAllAndShowInTableView();
            clearTextfields();
        } else {
            Alerts.wrongOrMissingDataAlert();
        }
    }

    /**
     * Handles the action to delete a caregiver from the database and reloads the table
     * This Method is connected to the delete-button
     */
    @FXML
    public void handleDeleteCaregiver() {
        Caretaker selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                this.caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
                TreatmentDAO treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
                List<Treatment> allTreatments = treatmentDAO.readAll();
                for (Treatment t : allTreatments) {
                    if (t.getCid() == selectedItem.getCid()) {
                        t.setCid(CaretakerDAO.DELETE_ID);
                    }
                    treatmentDAO.update(t);
                }
                caretakerDAO.deleteById(selectedItem.getCid());
                readAllAndShowInTableView();
                this.tableView.getItems().remove(selectedItem);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Alerts.noSelectionToDelete();
        }

    }

    /**
     * Calls readAll in {@link CaretakerDAO} and shows the caretakers in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
        List<Caretaker> allCaretakers;
        try {
            allCaretakers = caretakerDAO.readAll();
            allCaretakers.remove(0);
            this.tableviewContent.addAll(allCaretakers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the textfields in the UI
     */
    private void clearTextfields() {
        this.txfFirstname.clear();
        this.txfSurname.clear();
        this.txfTelephone.clear();
    }
}
