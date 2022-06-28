package datastorage;

import model.Caretaker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific caretaker-SQL-queries.
 */
public class CaretakerDAO extends DAOimp<Caretaker> {

    public static final long DELETE_ID = -10000;

    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     */
    public CaretakerDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given caretaker
     *
     * @param caretaker for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Caretaker caretaker) {
        return String.format("INSERT INTO caretaker (firstname, surname, phonenumber) VALUES ( '%s', '%s', '%s')",
                caretaker.getFirstName(), caretaker.getSurname(), caretaker.getPhoneNumber());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     *
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caretaker WHERE cid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caretaker</code>
     *
     * @param result ResultSet with a single row. Columns will be mapped to a caretaker-object.
     * @return caretaker with the data from the resultSet.
     */
    @Override
    protected Caretaker getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caretaker c;
        c = new Caretaker(result.getString(2), result.getString(3),
                result.getLong(1), result.getString(4));
        return c;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all caretakers.
     *
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM caretaker";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caretaker-List</code>
     *
     * @param result ResultSet with a multiple rows. Data will be mapped to caretaker-object.
     * @return ArrayList with caretakers from the resultSet.
     */
    @Override
    protected ArrayList<Caretaker> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caretaker> list = new ArrayList<>();
        Caretaker c;
        while (result.next()) {
            c = new Caretaker(result.getString(2), result.getString(3),
                    result.getLong(1), result.getString(4));
            list.add(c);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given caretaker
     *
     * @param caretaker for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Caretaker caretaker) {
        return String.format("UPDATE caretaker SET firstname = '%s', surname = '%s', phonenumber = '%s' " +
                "WHERE cid=%d", caretaker.getFirstName(), caretaker.getSurname(), caretaker.getPhoneNumber(), caretaker.getCid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     *
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caretaker WHERE cid=%d", key);
    }
}




