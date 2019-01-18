package fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import static fx.FXRunner.currentUser;

public class StatsController {

    @FXML
    private Text fullNameText;

    @FXML
    private Text gradeLvlText;

    @FXML
    private Text clientsQtyText;

    @FXML
    private void initialize() {
        fullNameText.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        gradeLvlText.setText(String.valueOf(currentUser.getGrade()));
        clientsQtyText.setText(String.valueOf(currentUser.getInvolvedClients()));
    }
}
