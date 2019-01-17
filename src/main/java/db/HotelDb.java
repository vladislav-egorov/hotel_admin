package db;

import db.mappers.Visitor;
import org.dalesbred.query.SqlQuery;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelDb {

    DBUtil database;

    public HotelDb(String url, String userName, String password) {
        this.database = new DBUtil(url, userName, password);
    }

    public String getPasswordForUser(String username) {
        SqlQuery query = SqlQuery.query("Select pass from managers where login = '" + username + "';");
        return database.executeQuery(query, String.class);
    }

    public List<Visitor> getAllVisitors() {
        SqlQuery query = SqlQuery.query("Select * from visitors;");
        return database.fetchAll(query, Visitor.class);
    }

    public void addNewVisitor(Visitor visitor) {
        Map<String,Object> values = new HashMap<>();
        values.put("firstName", visitor.getFirstName());
        values.put("lastName", visitor.getLastName());
        values.put("startVisit", visitor.getStartVisit().toString());
        values.put("endVisit", visitor.getEndVisit().toString());
        values.put("roomId", visitor.getRoomId());
        values.put("managerId", visitor.getManagerId());

        SqlQuery query = SqlQuery.namedQuery("INSERT INTO hotel.visitors (`first_name`, `last_name`, `visit_start_date`, `visit_end_date`, `room_id`, `manager_id`) \n" +
                "VALUES (:firstName, :lastName, :startVisit, :endVisit, :roomId, :managerId);", values);
        database.update(query);
    }
}
