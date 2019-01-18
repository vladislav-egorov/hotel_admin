package fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.util.Map;

import static fx.FXRunner.HOTEL_DB;

public class CheckController {

    @FXML
    private DatePicker dateField;

    @FXML
    private Button checkButton;

    @FXML
    private ChoiceBox<?> typeField;

    @FXML
    private void initialize() {
        checkButton.setOnAction(
                actionEvent -> {
                    String currentValue = typeField.getValue().toString();
                    System.out.println(currentValue);
                    int roomType = 99;
                    switch (currentValue) {
                        case "Эконом":
                            roomType = 2;
                            break;
                        case "Стандарт":
                            roomType = 1;
                            break;
                        case "Премиум":
                            roomType = 3;
                            break;
                        default:
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setTitle("Ошибка");
                            error.setContentText("Нужно выбрать тип комнаты");
                            error.show();
                            break;
                    }
                    Map<String, Object> results = HOTEL_DB.availableRoomsQtyForDateAndType(dateField.getValue(), roomType);
                    System.out.println(results);

                    Alert infoMsg = new Alert(Alert.AlertType.INFORMATION);
                    infoMsg.setTitle("Информация по доступности комнат");
                    infoMsg.setContentText("На " + dateField.getValue().toString() + " доступно " + results.get("freeRoomsQty") +
                            " из " + results.get("availableRooms") + ". Занято: " + results.get("bookedRooms"));
                    infoMsg.show();
                }
        );
    }
}
