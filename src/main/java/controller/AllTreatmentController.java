package controller;

import datastorage.CaretakerDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Caretaker;
import model.Patient;
import model.Treatment;
import utils.Alerts;
import utils.ControllerConstants;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>AllTreatmentController</code> contains the entire logic of the treatment view. It determines which data is displayed and how to react to events.
 */
public class AllTreatmentController {

    @FXML
    private TableColumn<Treatment, String> colCid;
    @FXML
    private TableView<Treatment> tableView;
    @FXML
    private TableColumn<Treatment, Integer> colID;
    @FXML
    private TableColumn<Treatment, Integer> colPid;
    @FXML
    private TableColumn<Treatment, String> colDate;
    @FXML
    private TableColumn<Treatment, String> colBegin;
    @FXML
    private TableColumn<Treatment, String> colEnd;
    @FXML
    private TableColumn<Treatment, String> colDescription;
    @FXML
    private TableColumn<Treatment, String> colLockState;

    private final TableColumn<Treatment, String> colLockDate = new TableColumn<>();
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button btnNewTreatment;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnLockTreatment;
    @FXML
    private Button btnUnlockTreatment;

    private final ObservableList<Treatment> tableviewContent =
            FXCollections.observableArrayList();
    private TreatmentDAO treatmentDAO;

    private final ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();
    private ArrayList<Patient> patientList;

    /**
     * Initializes the view with given data
     */
    public void initialize() {
        CaretakerDAO caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
        createComboBoxData();
        checkLockedData();
        readAllAndShowInTableView();
        comboBox.setItems(myComboBoxData);
        comboBox.getSelectionModel().select(0);

        this.colID.setCellValueFactory(new PropertyValueFactory<>("tid"));
        this.colPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        this.colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.colBegin.setCellValueFactory(new PropertyValueFactory<>("begin"));
        this.colEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        this.colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.colLockState.setCellValueFactory(new PropertyValueFactory<>("lockState"));
        this.colLockDate.setCellValueFactory(new PropertyValueFactory<>("lockDate"));
        this.colCid.setCellValueFactory(cellDataFeatures -> {
            try {
                Caretaker c = caretakerDAO.read(cellDataFeatures.getValue().getCid());
                return (c.getCid() != CaretakerDAO.DELETE_ID) ?
                        new SimpleStringProperty(String.format("%-15s %s", "\uD83D\uDC66: " + c.getSurname(), " 📞: " + c.getPhoneNumber())) :
                        new SimpleStringProperty(c.getSurname());

            } catch (SQLException e) {
                e.printStackTrace();
                Alerts.canNotLoadCaretakerIDAlert();
                return new SimpleStringProperty("-");
            }

        });
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * calls readAll in {@link TreatmentDAO} and shows the treatments in the table
     */
    public void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
        List<Treatment> allTreatments;
        try {
            allTreatments = treatmentDAO.readAll();
            this.tableviewContent.addAll(allTreatments);
            comboBox.getSelectionModel().select(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the chooseable data out of the patient database to the combobox
     */

    private void createComboBoxData() {
        PatientDAO patientDAO = DAOFactory.getDAOFactory().createPatientDAO();
        try {
            patientList = (ArrayList<Patient>) patientDAO.readAll();
            this.myComboBoxData.add("alle");
            for (Patient patient : patientList) {
                this.myComboBoxData.add(patient.getSurname());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the locked date from the database with the actual date, to decide if treatments will be deleted
     */
    private void checkLockedData() {
        treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            int dateYear = LocalDate.now().getYear();
            int dateMonth = LocalDate.now().getMonthValue();
            int dateDay = LocalDate.now().getDayOfMonth();
            List<Treatment> allTreatments = this.treatmentDAO.readAll();
            for (Treatment treatment : allTreatments) {
                int treatmentDateYear = treatment.getLockDate().getYear();
                int treatmentDateMonth = treatment.getLockDate().getMonthValue();
                int treatmentDateDay = treatment.getLockDate().getDayOfMonth();
                if ((dateYear - treatmentDateYear >= 10 && treatment.getLockState().equals(ControllerConstants.LOCKED)) && (dateMonth >= treatmentDateMonth && dateDay >= treatmentDateDay)) {
                    treatmentDAO.deleteById(treatment.getTid());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Shows the treatments of the selected patient
     */
    @FXML
    public void handleComboBox() {
        String p = this.comboBox.getSelectionModel().getSelectedItem();
        this.tableviewContent.clear();
        this.treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
        List<Treatment> allTreatments;
        if (p.equals("alle")) {
            try {
                allTreatments = this.treatmentDAO.readAll();
                this.tableviewContent.addAll(allTreatments);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Patient patient = searchInList(p);
        if (patient != null) {
            try {
                allTreatments = treatmentDAO.readTreatmentsByPid(patient.getPid());
                this.tableviewContent.addAll(allTreatments);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method returns a matching patient from a given surname
     *
     * @param surname is used to filter through the patient data
     * @return the matching patient
     */
    private Patient searchInList(String surname) {
        for (Patient patient : this.patientList) {
            if (patient.getSurname().equals(surname)) {
                return patient;
            }
        }
        return null;
    }


    /**
     * Handles the action to delete a treatment from the database and updates the shown treatments
     */
    @FXML
    public void handleDelete() {
        int index = this.tableView.getSelectionModel().getSelectedIndex();
        Treatment t = this.tableviewContent.remove(index);
        treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            treatmentDAO.deleteById(t.getTid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a new window, where a new treatment can be created
     */
    @FXML
    public void handleNewTreatment() {
        try {
            String p = this.comboBox.getSelectionModel().getSelectedItem();
            Patient patient = searchInList(p);
            newTreatmentWindow(patient);
        } catch (NullPointerException e) {
            Alerts.missingPatientAlert();
        }
    }


    /**
     * Handles the mouse click on a treatment, opens an editor window if the click condition is reached
     */
    @FXML
    public void handleMouseClick() {
        tableView.setOnMouseClicked(event -> {
            TableView.TableViewSelectionModel<Treatment> selectionModel = tableView.getSelectionModel();
            if (event.getClickCount() == 2 && (selectionModel.getSelectedItem() != null)) {
                if (selectionModel.getSelectedItem().getLockState().equals(ControllerConstants.UNLOCKED)) {
                    int index = this.tableView.getSelectionModel().getSelectedIndex();
                    Treatment treatment = this.tableviewContent.get(index);

                    treatmentWindow(treatment);
                } else {
                    selectionModel.select(null);
                    Alerts.lockedTreatmentAlert();
                }

            }
        });
    }

    /**
     * Opens the view for the new Treatment window
     *
     * @param patient data which is needed to initialize the  controller of the new view
     */
    public void newTreatmentWindow(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/NewTreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            //da die primaryStage noch im Hintergrund bleiben soll
            Stage stage = new Stage();

            NewTreatmentController controller = loader.getController();
            controller.initialize(this, stage, patient);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the view of the editor window
     *
     * @param treatment data which is needed to initialize the  controller of the new view
     */
    public void treatmentWindow(Treatment treatment) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/TreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            //da die primaryStage noch im Hintergrund bleiben soll
            Stage stage = new Stage();
            TreatmentController controller = loader.getController();

            controller.initializeController(this, stage, treatment);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action to lock a Treatment
     * is connected to the lock-button
     */
    @FXML
    public void handleLockTreatment() {
        lockFunction(ControllerConstants.LOCKED);


    }

    /**
     * Handles the action to unlock a Treatment
     * is connected to the unlock-button
     */
    @FXML
    public void handleUnlockTreatment() {
        lockFunction(ControllerConstants.UNLOCKED);
    }

    /**
     * First checks the validity of the Selection, then goes into the lock process
     *
     * @param constant is used to call the matching process of this constant
     */
    private void lockFunction(String constant) {
        int index = this.tableView.getSelectionModel().getSelectedIndex();
        if (index > 0) {
            Treatment treatment = this.tableviewContent.get(index);
            treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
            lockingTreatment(constant, treatment);
        } else {
            Alerts.noSelectionToLockAlert();
        }

    }

    /**
     * Trys to lock the treatment with the {@link TreatmentDAO}
     *
     * @param constant  decides the lock-state of the treatment
     * @param treatment which gets locked or unlocked
     */
    private void lockingTreatment(String constant, Treatment treatment) {
        if (!constant.equals(treatment.getLockState())) {
            try {
                treatment.setLockState(constant);
                treatment.setLockDate(LocalDate.now().toString());
                treatmentDAO.update(treatment);

            } catch (SQLException e) {
                Alerts.noSelectionToLockAlert();
            }
            readAllAndShowInTableView();
        } else {
            Alerts.sameLockStateAlert();
        }
    }
}


