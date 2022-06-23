package datastorage;

import model.Caretaker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaretakerDAO extends DAOimp<Caretaker> {

    public static final long DELETE_ID = -10000;

    public CaretakerDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Caretaker caretaker) {
        return String.format("INSERT INTO caretaker (firstname, surname, phonenumber) VALUES ( '%s', '%s', '%s')",
                caretaker.getFirstName(), caretaker.getSurname(), caretaker.getPhoneNumber());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caretaker WHERE cid = %d", key);
    }

    @Override
    protected Caretaker getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caretaker c = null;
        c = new Caretaker(result.getString(2), result.getString(3),
                result.getLong(1), result.getString(4));
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
            c = new Caretaker(result.getString(2), result.getString(3),
                    result.getLong(1), result.getString(4));
            list.add(c);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Caretaker caretaker) {
        return String.format("UPDATE caretaker SET firstname = '%s', surname = '%s', phonenumber = '%s' " +
                "WHERE cid=%d", caretaker.getFirstName(), caretaker.getSurname(), caretaker.getPhoneNumber(), caretaker.getCid());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caretaker WHERE cid=%d", key);
    }
}




