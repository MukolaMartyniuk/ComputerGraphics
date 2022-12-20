package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;

import static javafx.scene.media.MediaPlayer.Status.PLAYING;

public class MediaP {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView ImageG;

    @FXML
    private Button CloseBtn;

    @FXML
    private Button PlayBtn;

    @FXML
    private Button StopBtn;

    @FXML
    private MediaView MP;

    MediaPlayer mediaPlayer;

    @FXML
    void initialize() {
        CloseBtn.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("Information.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);
        });

        //Image i = new Image(new File("file:/Users/mikola/Desktop/Rep/VideoInfo.gif").toURI().toString());
        //ImageG.setImage(i);

        String VUrl = "file:/Users/mikola/Desktop/Rep/VideoInfo.mp4";
        Media media = new Media(VUrl);
        mediaPlayer = new MediaPlayer(media);
        MP.setFitHeight(300);
        MP.setFitWidth(400);
        MP.setMediaPlayer(mediaPlayer);

        PlayBtn.setOnAction(actionEvent -> {
            if(mediaPlayer.getStatus() == PLAYING){
                mediaPlayer.stop();
                mediaPlayer.play();
            }else {
                mediaPlayer.play();
            }
        });

        StopBtn.setOnAction(actionEvent -> {

        mediaPlayer.stop();
        });
    }

}

