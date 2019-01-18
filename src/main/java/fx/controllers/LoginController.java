package fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static fx.FXRunner.*;

public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize() {
        loginButton.setOnAction(
                actionEvent -> {
                    if (HOTEL_DB.isUserAndPasswordValid(loginField.getText(), passwordField.getText())) {
                        currentUser = HOTEL_DB.getManagerByLogin(loginField.getText());
                        FX_GENERAL.closeStage(loginButton);
                        try {
                            FX_GENERAL.openNewStandartWindow("Домашняя", "home");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Ошибка входа");
                        error.setContentText("Неверные данные для входа!");
                        error.show();
                    }

                }
        );
    }


}
