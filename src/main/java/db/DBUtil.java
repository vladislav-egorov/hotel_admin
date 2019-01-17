package db;

import org.dalesbred.Database;
import org.dalesbred.annotation.SQL;
import org.dalesbred.query.SqlQuery;
import org.dalesbred.result.ResultTable;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class DBUtil {
    private Database database;

    public DBUtil(String url, String username, String password
    ) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database = Database.forUrlAndCredentials(url, username, password);
    }

    public <T> List<T> fetchAll(@SQL String sqlQuery, Class<T> returnClass) {
        return database.findAll(returnClass, sqlQuery);
    }

    public ResultTable findTable(SqlQuery sqlQuery) {
        return database.findTable(sqlQuery);
    }

    public <T> T fetchOne(@SQL String sqlQuery, Class<T> returnClass) {
        return database.findUnique(returnClass, sqlQuery);
    }

    public <T> T executeQuery(SqlQuery sqlQuery, Class<T> tClass) {
        return database.findOptional(tClass, sqlQuery).orElse(null);
    }

    public void update(SqlQuery sqlQuery) {
        database.update(sqlQuery);
    }

    public void update(@SQL String sqlSting) {
        update(SqlQuery.query(sqlSting));
    }

}

