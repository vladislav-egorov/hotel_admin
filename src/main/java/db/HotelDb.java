package db;

import org.dalesbred.query.SqlQuery;

public class HotelDb {

    DBUtil database;

    public HotelDb(String url, String userName, String password) {
        this.database = new DBUtil(url, userName, password);
    }

    public String getPasswordForUser(String username) {
        SqlQuery query = SqlQuery.query("Select pass from managers where login = '" + username + "';");
        return database.executeQuery(query, String.class);
    }

    

}
