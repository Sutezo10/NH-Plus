package controller;

import datastorage.CaretakerDAO;
import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caretaker;
import model.Treatment;
import utils.ControllerConstants;

import java.sql.SQLException;
import java.util.List;

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


    private ObservableList<Caretaker> tableviewContent = FXCollections.observableArrayList();
    private CaretakerDAO caretakerDAO;
    private TreatmentDAO treatmentDAO;

    public void initialize() {
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caretaker, String>("firstName"));
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Caretaker, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<Caretaker, String>("phoneNumber"));
        this.colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colID.setCellValueFactory(new PropertyValueFactory<Caretaker, Integer>("cid"));

        this.tableView.setItems(tableviewContent);
        readAllAndShowInTableView();
    }


    private void doUpdate(TableColumn.CellEditEvent<Caretaker, String> t) {
        try {
            caretakerDAO.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleEditSurname(TableColumn.CellEditEvent<Caretaker, String> event) {
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    public void handleEditFirstName(TableColumn.CellEditEvent<Caretaker, String> event) {
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    public void handleEditTelephone(TableColumn.CellEditEvent<Caretaker, String> event) {
        event.getRowValue().setPhoneNumber(event.getNewValue());
        doUpdate(event);
    }

    @FXML
    public void handleAddCaregiver() {
        String surname = this.txfSurname.getText();
        String firstname = this.txfFirstname.getText();
        String telephoneNumber = this.txfTelephone.getText();
        if (!surname.isEmpty() && !firstname.isEmpty() && !telephoneNumber.isEmpty()) {
            try {
                this.caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
                Caretaker c = new Caretaker(firstname, surname, telephoneNumber);
                caretakerDAO.create(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            readAllAndShowInTableView();
            clearTextfields();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(ControllerConstants.ALERT_INFORMATION_TITLE);
            alert.setHeaderText("Fehlende Daten!");
            alert.setContentText("Es müssen alle Felder befüllt werden, um einen neuen Pfleger zu erstellen!");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleDeleteCaregiver() {
        Caretaker selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            this.caretakerDAO = DAOFactory.getDAOFactory().createCaretakerDAO();
            this.treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
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
    }

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


    private void clearTextfields() {
        this.txfFirstname.clear();
        this.txfSurname.clear();
        this.txfTelephone.clear();
    }
}
