package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaretakerDAO<T> extends DAOimp<T> {
    public CaretakerDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(T t) {
        return null;
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return null;
    }

    @Override
    protected T getInstanceFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected String getReadAllStatementString() {
        return null;
    }

    @Override
    protected ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected String getUpdateStatementString(T t) {
        return null;
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return null;
    }
}
