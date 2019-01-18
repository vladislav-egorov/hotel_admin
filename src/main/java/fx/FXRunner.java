package fx;

import db.HotelDb;
import db.mappers.Manager;
import javafx.application.Application;
import javafx.stage.Stage;

public class FXRunner extends Application {
    public static final FXGeneral FX_GENERAL = new FXGeneral();

    private static final String URL = "jdbc:mysql://127.0.0.1:3307/hotel?useSSL=false";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static final HotelDb HOTEL_DB = new HotelDb(URL, USERNAME, PASSWORD);

    public static Manager currentUser = null;

    public static void main(String[] args) {
        launch(args);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       /* Visitor visitor = new Visitor(
                1,
                "Kylie",
                "Haig",
                LocalDate.parse("26/01/2019", formatter),
                LocalDate.parse("27/01/2019", formatter),
                30,
                2
        );
        Integer qty = db.availableRoomsQtyForDateAndType(LocalDate.parse("26/01/2019", formatter), 3);*/

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FX_GENERAL.openNewStandartWindow("Вход в систему", "main");
    }
}
