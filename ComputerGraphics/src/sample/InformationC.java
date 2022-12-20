package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InformationC {
    @FXML
    private AnchorPane root;

    @FXML
    private TextArea TextC;

    @FXML
    private Button CloseCBtn;



    @FXML
    void initialize() {


        CloseCBtn.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("S_Color.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);
        });

        TextC.setText("RGB - In this model, the color is encoded by the gradations of the component channels (Red, Green, Blue). " +
                "\nTherefore, as the magnitude of the gradation of a channel increases, its intensity increases during synthesis.\n" +
                "HSL -  stands for hue, saturation, and lightness.\n" +
                "Illumination of red - introduces illumination of red.\n" +
                "-> - output coordinates in the color scheme.");
        TextC.setWrapText(true);
    }

}
