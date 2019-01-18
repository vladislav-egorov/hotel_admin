package fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXGeneral {
    Parent root;

    public void closeStage(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void openNewStandartWindow(String title, String mapperName) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/" + mapperName + ".fxml"));
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
