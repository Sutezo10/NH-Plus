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
import javafx.scene.control.*;
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
    private CaretakerDAO caretakerDAO;

    private final ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();
    private ArrayList<Patient> patientList;


    public void initialize() {
        caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(ControllerConstants.ALERT_ERROR_TITLE);
                alert.setHeaderText("Fehler beim laden der Pfleger-ID!");
                alert.setContentText("Beim Laden der Informationen vom Pfleger ist ein Fehler aufgetreten");
                alert.showAndWait();
                return new SimpleStringProperty("-");
            }

        });
        this.tableView.setItems(this.tableviewContent);
    }

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
                if (dateYear - treatmentDateYear >= 10 && treatment.getLockState().equals(ControllerConstants.LOCKED)) {
                    if (dateMonth >= treatmentDateMonth && dateDay >= treatmentDateDay) {
                        treatmentDAO.deleteById(treatment.getTid());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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


    private Patient searchInList(String surname) {
        for (Patient patient : this.patientList) {
            if (patient.getSurname().equals(surname)) {
                return patient;
            }
        }
        return null;
    }

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


    public void handleLockTreatment() {
        lockFunction(ControllerConstants.LOCKED);


    }

    public void handleUnlockTreatment() {
        lockFunction(ControllerConstants.UNLOCKED);
    }

    private void lockFunction(String constant) {
        int index = this.tableView.getSelectionModel().getSelectedIndex();
        Treatment treatment = this.tableviewContent.get(index);
        treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
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


