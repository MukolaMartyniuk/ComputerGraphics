package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
//import javafx.scene.media.NGMediaView;



import java.io.IOException;

public class Information {

    @FXML
    private AnchorPane root;

    @FXML
    private Button CloseBtn;

    @FXML
    private Button Video;

    @FXML
    private TextArea TextA;

    @FXML
    void initialize() {
        CloseBtn.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("S_Menu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);
        });

        Video.setOnAction(actionEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("MediaP.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);
        });
      /*  CloseBtn.styleProperty().bind(
                new SimpleStringProperty("-fx-base: ")
                        //.concat(colorStringProperty)
                        .concat(";")
                        .concat("-fx-font-size: 20px;")
        );*/

        TextA.setText("Fractal - irregular, self-similar structure. " +
                "\nIn a broad sense, a fractal means a figure, some parts of which in arbitrary magnification are similar to itself." +
                "\nRGB - additive color model, which describes a method of color synthesis in which red, green and blue light are \nsuperimposed together, mixing into different colors." +
                "\nHSL - color model in which any color is determined by three characteristics: color tone, saturation, luminosity.\nTriangle - a geometric figure consisting of three points that do not lie on one line and three segments connecting them." +
                "\nFractal - irregular, self-similar structure. " +
                "\nIn a broad sense, a fractal means a figure, some parts of which in arbitrary magnification are similar to itself." +
                "\nRGB - additive color model, which describes a method of color synthesis in which red, green and blue light are superimposed together, mixing into different colors." +
                "\nHSL - color model in which any color is determined by three characteristics: color tone, saturation, luminosity." +
                "\nTriangle - a geometric figure consisting of three points that do not lie on one line and three segments connecting them.");

        TextA.setWrapText(true);

    }

}
