package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InformationF {
    @FXML
    private AnchorPane root;


    @FXML
    private Button CloseFBtn;

    @FXML
    private TextArea TextF;

    @FXML
    void initialize() {


        CloseFBtn.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);
        });

        TextF.setText("Koch's fractal - The Koch curve is a fractal curve \ndescribed in 1904 by the Swedish mathematician Helge von Koch.\n" +
                "Harter`s fractal - example of a system of iterative functions, " +
                "\na common name for some fractal curves that can be approximated by recursive methods such as the L-system.\n" +
                "Number of iterations - selection of the number of iterations.");
        TextF.setWrapText(true);
    }

}
