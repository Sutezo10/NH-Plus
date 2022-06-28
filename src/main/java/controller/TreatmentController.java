package controller;

import datastorage.CaretakerDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caretaker;
import model.Patient;
import model.Treatment;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The <code>TreatmentController</code> contains the entire logic of the treatment editor view. It determines which data is displayed and how to react to events.
 */
public class TreatmentController {
    @FXML
    public ComboBox<String> comboCaretaker;
    @FXML
    private Label lblPatientName;
    @FXML
    private Label lblCarelevel;
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
    @FXML
    private Button btnChange;
    @FXML
    private Button btnCancel;

    private AllTreatmentController controller;
    private Stage stage;
    private Patient patient;
    private Treatment treatment;

    private final ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();

    private CaretakerDAO caretakerDAO;

    /**
     * Initializes the controller
     * @param controller sets the val of the private var
     * @param stage sets the val of the private var
     * @param treatment sets the val of the private var
     */
    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        this.stage = stage;
        this.controller = controller;
        PatientDAO pDao = DAOFactory.getDAOFactory().createPatientDAO();
        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.treatment = treatment;
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the current data of the treatment
     */
    private void showData() {
        this.lblPatientName.setText(patient.getSurname() + ", " + patient.getFirstName());
        this.lblCarelevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datepicker.setValue(date);
        this.txtBegin.setText(this.treatment.getBegin());
        this.txtEnd.setText(this.treatment.getEnd());
        this.txtDescription.setText(this.treatment.getDescription());
        this.taRemarks.setText(this.treatment.getRemarks());
        createComboBoxData();
        comboCaretaker.setItems(myComboBoxData);
        comboCaretaker.getSelectionModel().select(0);
    }

    /**
     * Creates the data which can be selected in the combo box
     */
    private void createComboBoxData() {
        caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
        try {
            ArrayList<Caretaker> caretakerList = (ArrayList<Caretaker>) caretakerDAO.readAll();
            caretakerList.remove(0);
            for (Caretaker caretaker : caretakerList) {
                this.myComboBoxData.add(caretaker.getSurname());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action to change the treatment with the new selected data
     * is connected to the change-button
     */
    @FXML
    public void handleChange() {
        this.treatment.setDate(this.datepicker.getValue().toString());
        this.treatment.setBegin(txtBegin.getText());
        this.treatment.setEnd(txtEnd.getText());
        this.treatment.setDescription(txtDescription.getText());
        this.treatment.setRemarks(taRemarks.getText());
        caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
        Optional<Caretaker> caretaker;
        try {
            List<Caretaker> caretakerList = caretakerDAO.readAll();
            caretaker = caretakerList.stream().filter(c -> c.getSurname().equals(comboCaretaker.getSelectionModel().getSelectedItem())).findAny();
            caretaker.ifPresent(value -> this.treatment.setCid(value.getCid()));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * Updates the data with {@link TreatmentDAO}
     */
    private void doUpdate() {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.update(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action to cancel and close the treatment editor view
     * is connected to the cancel-button
     */
    @FXML
    public void handleCancel() {
        stage.close();
    }
}