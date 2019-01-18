package fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static fx.FXRunner.FX_GENERAL;

public class HomePageController {

    @FXML
    private Button registerClient;

    @FXML
    private Button checkFreeRooms;

    @FXML
    private Button showAllVisitors;

    @FXML
    private Button stats;

    @FXML
    private void initialize() {
        registerClient.setOnAction(
                actionEvent -> {
                    try {
                        FX_GENERAL.openNewStandartWindow("Регистрация нового клиента", "registration");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

        checkFreeRooms.setOnAction(
                actionEvent -> {
                    try {
                        FX_GENERAL.openNewStandartWindow("Проверка доступности комнат", "checkAvailable");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        showAllVisitors.setOnAction(
                actionEvent -> {
                    try {
                        FX_GENERAL.openNewStandartWindow("Все клиенты", "allVisitors");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        stats.setOnAction(
                actionEvent -> {
                    try {
                        FX_GENERAL.openNewStandartWindow("Статистика", "stats");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
