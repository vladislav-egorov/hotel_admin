package db;

import db.mappers.Manager;
import db.mappers.Visitor;
import org.dalesbred.query.SqlQuery;
import org.dalesbred.result.ResultTable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelDb {

    private DBUtil database;

    public HotelDb(String url, String userName, String password) {
        this.database = new DBUtil(url, userName, password);
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String URL = "jdbc:mysql://127.0.0.1:3307/hotel?useSSL=false";
        String USERNAME = "admin";
        String PASSWORD = "admin";

        HotelDb HOTEL_DB = new HotelDb(URL, USERNAME, PASSWORD);
        HOTEL_DB.availableRoomsQtyForDateAndType(LocalDate.parse("26/01/2019", formatter), 2);
    }

    public boolean isUserAndPasswordValid(String username, String password) {
        Map<String, String> values = new HashMap<>();
        values.put("username", username);
        values.put("password", password);
        SqlQuery query = SqlQuery.namedQuery("SELECT count(*) FROM hotel.managers\n" +
                "where login = :username and pass = :password;", values);
        return database.executeQuery(query, Integer.class) > 0;
    }

    public List<Visitor> getAllVisitors() {
        SqlQuery query = SqlQuery.query("Select * from visitors;");
        return database.fetchAll(query, Visitor.class);
    }

    public Manager getManagerByLogin(String login) {
        Map<String, String> values = new HashMap<>();
        values.put("login", login);

        SqlQuery query = SqlQuery.namedQuery("SELECT manager_id, first_name, last_name, involved_clients, grade FROM managers\n" +
                "where login = :login;", values);
        return database.executeQuery(query, Manager.class);
    }

    public Integer getRoomIdByNumber(Integer roomNumber) {
        Map<String, Object> values = new HashMap<>();
        values.put("roomNumber", roomNumber);
        SqlQuery query = SqlQuery.namedQuery("SELECT room_id FROM hotel.rooms\n" +
                "where rooms.number = :roomNumber;", values);
        return database.executeQuery(query, Integer.class);
    }

    public void addNewVisitor(Visitor visitor) {
        Map<String, Object> values = new HashMap<>();
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

    public boolean visitorRegistrated(Visitor visitor) {
        Map<String, Object> values = new HashMap<>();
        values.put("firstName", visitor.getFirstName());
        values.put("lastName", visitor.getLastName());
        values.put("startVisit", visitor.getStartVisit().toString());
        values.put("endVisit", visitor.getEndVisit().toString());
        values.put("roomId", visitor.getRoomId());

        SqlQuery query = SqlQuery.namedQuery("select count(*) from visitors where\n" +
                "visitors.first_name = :firstName\n" +
                "and visitors.last_name = :lastName\n" +
                "and visitors.visit_start_date = :startVisit\n" +
                "and visitors.visit_end_date = :endVisit\n" +
                "and visitors.room_id = :roomId;", values);
        int visitorsCount = database.executeQuery(query, Integer.class);
        return visitorsCount > 0;
    }

    public Map<String, Object> availableRoomsQtyForDateAndType(LocalDate date, Integer typeId) {
        Map<String, Object> values = new HashMap<>();
        values.put("date", date.toString());
        values.put("roomTypeId", typeId);

        SqlQuery query = SqlQuery.namedQuery("select\n" +
                "case when\n" +
                "room_types.rooms_qt is null then (select room_types.rooms_qt from room_types where room_types.type_id = :roomTypeId)\n" +
                "else room_types.rooms_qt\n" +
                "end as available_rooms,\n" +
                "count(*) as booked_rooms, \n" +
                "case when\n" +
                "room_types.rooms_qt is null then (select room_types.rooms_qt from room_types where room_types.type_id = :roomTypeId) - count(*)\n" +
                "else room_types.rooms_qt - count(*)\n" +
                "end as free_rooms_qty\n" +
                "from rooms\n" +
                "left join visitors on rooms.room_id = visitors.room_id\n" +
                "left join room_types on rooms.room_type_id = room_types.type_id\n" +
                "where rooms.room_type_id = :roomTypeId\n" +
                "and visit_start_date <= :date and visit_end_date >= :date;", values);
        ResultTable result = database.findTable(query);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("availableRooms", result.get(0, "available_rooms"));
        resultMap.put("bookedRooms", result.get(0, "booked_rooms"));
        resultMap.put("freeRoomsQty", result.get(0, "free_rooms_qty"));

        return resultMap;
    }
}
