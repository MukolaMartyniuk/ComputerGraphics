package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SMenu {

    @FXML
    private AnchorPane root;

    @FXML
    private Button FractalBtn;

    @FXML
    private Button ColorBtn;

    @FXML
    private Button TriangleBtn;

    @FXML
    private ImageView Information;

    @FXML
    void initialize() {

        Tooltip ttS8 = new Tooltip();
        ttS8.setText("Button to go to the form with fractals");
        ttS8.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        FractalBtn.setTooltip(ttS8);

        Tooltip tS8 = new Tooltip();
        tS8.setText("Button to go to the form with color schemes");
        tS8.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        ColorBtn.setTooltip(tS8);

        Tooltip ttS = new Tooltip();
        ttS.setText("Button to go to the shape of a triangle");
        ttS.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        TriangleBtn.setTooltip(ttS);
        FractalBtn.setOnAction(actionEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);

        });

        Information.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("Information.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);
        });

        ColorBtn.setOnAction(actionEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("S_Color.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);

        });
        TriangleBtn.setOnAction(actionEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("S_Triangle.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);

        });
    }
}
