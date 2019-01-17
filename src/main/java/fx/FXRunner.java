package fx;

import db.HotelDb;
import db.mappers.Visitor;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class FXRunner {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3307/hotel?useSSL=false";
        String username = "admin";
        String password = "admin";
        HotelDb db = new HotelDb(url, username, password);
        Visitor visitor = new Visitor(
                1,
                "Kylie",
                "Haig",
                LocalDate.now().minus(15, ChronoUnit.DAYS),
                LocalDate.now().minus(5, ChronoUnit.DAYS),
                9,
                2
        );
        db.addNewVisitor(visitor);
        System.out.println(db.getAllVisitors());
    }
}
