package datastorage;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends DAOimp<User> {
    public UserDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(User user) {
        return String.format("INSERT INTO user (username, password) VALUES ('%s', '%s')",
                user.getUserName(), user.getPassword());
    }

    @Override
    protected String getReadByIDStatementString(long username) {
        return String.format("SELECT * FROM user WHERE username = %d", username);
    }

    @Override
    protected User getInstanceFromResultSet(ResultSet result) throws SQLException {
        User p = null;
        p = new User(result.getString(1), result.getString(2));
        return p;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }

    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        User p = null;
        while (result.next()) {
            p = new User(result.getString(1), result.getString(2));
            list.add(p);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(User user) {
        return String.format("UPDATE user SET username = '%s', password = '%s'", user.getUserName(), user.getPassword());
    }

    @Override
    protected String getDeleteStatementString(long username) {
        return String.format("Delete FROM patient WHERE username=%d", username);
    }
}
