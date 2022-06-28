package datastorage;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific user-SQL-queries.
 */
public class UserDAO extends DAOimp<User> {
    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     */
    public UserDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given user
     *
     * @param user for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(User user) {
        return String.format("INSERT INTO user (username, password) VALUES ('%s', '%s')",
                user.getUserName(), user.getPassword());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     *
     * @param key for which a specific SELECT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM user WHERE username = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>User</code>
     *
     * @param result ResultSet with a single row. Columns will be mapped to a user-object.
     * @return user with the data from the resultSet.
     */
    @Override
    protected User getInstanceFromResultSet(ResultSet result) throws SQLException {
        User p;
        p = new User(result.getString(1), result.getString(2));
        return p;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all users.
     *
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>User-List</code>
     *
     * @param result ResultSet with a multiple rows. Data will be mapped to user-object.
     * @return ArrayList with users from the resultSet.
     */
    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        User p;
        while (result.next()) {
            p = new User(result.getString(1), result.getString(2));
            list.add(p);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given User
     *
     * @param user for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(User user) {
        return String.format("UPDATE user SET username = '%s', password = '%s'", user.getUserName(), user.getPassword());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     *
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM patient WHERE username=%d", key);
    }
}
