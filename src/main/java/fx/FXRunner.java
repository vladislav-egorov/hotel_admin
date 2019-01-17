package fx;

import db.HotelDb;

public class FXRunner {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3307/hotel?useSSL=false";
        String username = "admin";
        String password = "admin";
        HotelDb db = new HotelDb(url, username, password);
        System.out.println(db.getPasswordForUser("jsmith"));
    }
}
