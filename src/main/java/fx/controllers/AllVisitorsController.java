package fx.controllers;

import db.mappers.Visitor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static fx.FXRunner.HOTEL_DB;

public class AllVisitorsController {


    @FXML
    private TableView<Visitor> visitorsTable;

    @FXML
    private TableColumn firstName;

    @FXML
    private TableColumn lastName;

    @FXML
    private TableColumn startVisit;

    @FXML
    private TableColumn endVisit;

    @FXML
    private TableColumn roomId;

    @FXML
    private void initialize() {
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        startVisit.setCellValueFactory(new PropertyValueFactory<>("startVisit"));
        endVisit.setCellValueFactory(new PropertyValueFactory<>("endVisit"));
        roomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        List<Visitor> allVisitors = HOTEL_DB.getAllVisitors();
        ObservableList<Visitor> list = FXCollections.observableArrayList(allVisitors);
        visitorsTable.getItems().addAll(list);
    }

}
