package datastorage;

import model.Caretaker;
import utils.DateConverter;

import javax.swing.text.Caret;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CaretakerDAO<T> extends DAOimp<Caretaker> {
    public CaretakerDAO(Connection conn) {
        super(conn);
        }

    @Override
    protected String getCreateStatementString(Caretaker caretaker) {
        return String.format("INSERT INTO caretaker (cid, firstname, surname, phonenumber) VALUES ('%s', '%s', '%s', '%s')",
                caretaker.getCid(), caretaker.getFirstName(), caretaker.getSurname(), caretaker.getPhoneNumber());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caretaker WHERE cid = %d", key);
    }

    @Override
    protected Caretaker getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caretaker c = null;
        c = new Caretaker(result.getString(1), result.getString(2),
                result.getInt(3), result.getLong(4));
        return c;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM caretaker";
    }

    @Override
    protected ArrayList<Caretaker> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caretaker> list = new ArrayList<Caretaker>();
        Caretaker c = null;
        while (result.next()) {
            c = new Caretaker(result.getString(1), result.getString(2),
                    result.getInt(3), result.getLong(4));
            list.add(c);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Caretaker caretaker) {
        return String.format("UPDATE caretaker SET firstname = '%s', surname = '%s', phonenumber = '%s', " +
                "WHERE cid = %d", caretaker.getFirstName(), caretaker.getSurname(), caretaker.getCid(), caretaker.getPhoneNumber());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return null;
    }
}




