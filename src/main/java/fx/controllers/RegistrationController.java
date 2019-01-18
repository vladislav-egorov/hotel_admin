package fx.controllers;

import db.mappers.Visitor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

import static fx.FXRunner.HOTEL_DB;
import static fx.FXRunner.currentUser;

public class RegistrationController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private DatePicker startVisitDateField;

    @FXML
    private DatePicker endVisitDateField;

    @FXML
    private TextField roomNumberField;

    @FXML
    private Button registrationButton;

    @FXML
    private void initialize() {
        registrationButton.setOnAction(
                actionEvent -> {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    LocalDate startVisitDate = startVisitDateField.getValue();
                    LocalDate endVisitDate = endVisitDateField.getValue();
                    int roomId = HOTEL_DB.getRoomIdByNumber(Integer.parseInt(roomNumberField.getText()));
                    int managerId = currentUser.getManagerId();
                    Visitor visitor = new Visitor(firstName, lastName, startVisitDate, endVisitDate, roomId, managerId);
                    HOTEL_DB.addNewVisitor(visitor);
                    if (HOTEL_DB.visitorRegistrated(visitor)) {
                        Alert error = new Alert(Alert.AlertType.INFORMATION);
                        error.setTitle("Успех");
                        error.setContentText("Постоялец зарегистрирован");
                        error.show();
                    } else {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Ошибка");
                        error.setContentText("Ошибка при регистрации постояльца, проверьте заполнение полей");
                        error.show();
                    }
                }
        );
    }

}
