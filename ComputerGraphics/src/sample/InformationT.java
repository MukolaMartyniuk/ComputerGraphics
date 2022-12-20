package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InformationT {
    @FXML
    private AnchorPane root;

    @FXML
    private Button CloseTBtn;

    @FXML
    private TextArea TextT;

    @FXML
    void initialize() {

        CloseTBtn.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("S_Triangle.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);
        });

        TextT.setText("(x,y) - coordinate plane data.\n" +
                "step - step on the coordinate plane.\n" +
                "(x1,y1,...) - the vertices of the triangle.\n" +
                "Coefficient of increase - enlarges the triangle.");
        TextT.setWrapText(true);
    }

}
