package sample;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton RadBtnRed;

    @FXML
    private RadioButton RadBtnBlue;

    @FXML
    private RadioButton RadBtnGreen;

    @FXML
    private RadioButton RadBtnFrac1;

    @FXML
    private RadioButton RadBtnFrac2;

    @FXML
    private TextField IterationNum;

    @FXML
    private Button BtnFractalOk;

    @FXML
    private ImageView BtnDownload;

    @FXML
    private AnchorPane Fractal;

    @FXML
    private Slider sliderF;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private ImageView InfoF;


    @FXML
    private AnchorPane root;
    private Canvas canvas ;
    private GraphicsContext gContext;
    private Graphics g;
    double x, y, a, b;
    int x1, x2, y1, y2, x3, y3 ;

    @FXML
    void initialize() {

        Tooltip tt = new Tooltip();
        tt.setText("Color change to blue");
        tt.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        RadBtnBlue.setTooltip(tt);

        Tooltip tt1 = new Tooltip();
        tt1.setText("Color change to green");
        tt1.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        RadBtnGreen.setTooltip(tt1);

        Tooltip tt2 = new Tooltip();
        tt2.setText("Color change to red");
        tt2.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        RadBtnRed.setTooltip(tt2);

        Tooltip tt3 = new Tooltip();
        tt3.setText("Koch line");
        tt3.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        RadBtnFrac1.setTooltip(tt3);

        Tooltip tt5 = new Tooltip();
        tt5.setText("Dragon");
        tt5.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        RadBtnFrac2.setTooltip(tt5);

        Tooltip ttS8 = new Tooltip();
        ttS8.setText("Enter the number of iterations");
        ttS8.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        IterationNum.setTooltip(ttS8);

        Tooltip tt8 = new Tooltip();
        tt8.setText("Construct a fractal");
        tt8.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        BtnFractalOk.setTooltip(tt8);

        Tooltip tt4 = new Tooltip();
        tt4.setText("Enter the number of iterations");
        tt4.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        sliderF.setTooltip(tt4);

        IterationNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    IterationNum.setText(oldValue);
                }
            }
        });

        MenuBtn.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("S_Menu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);

        });

        InfoF.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("Information_F.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);

        });

        IterationNum.setText("1");
        IterationNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("^([1-9]|10)$")) {
                    Platform.runLater(() -> {
                        IterationNum.clear();
                    });
                }
            }
        });
        sliderF.setOnMouseClicked(mouseEvent -> {
            //sliderF = new Slider(0,10,1);
            double r = sliderF.getValue();
            int s = (int)r;
            IterationNum.appendText(Integer.toString(s));
        });
        sliderF.setOnMousePressed(mouseEvent -> {
            double r = sliderF.getValue();
            int s = (int)r;
            IterationNum.appendText(Integer.toString(s));
        });
        BtnFractalOk.setOnAction(actionEvent1 -> {
            if(IterationNum.getText().equals(""))
            {
                Error_Empty();
                return;
            }
            Fractal.getChildren().remove(canvas);
            canvas = new Canvas(Fractal.getHeight(), Fractal.getWidth());
            gContext = canvas.getGraphicsContext2D();

            //gContext.setFill(Color.rgb(255, 230, 143));
            // gContext.setStroke(Color.rgb(255, 230, 143));
            if (RadBtnRed.isSelected()) {
                gContext.setFill(Color.WHITE);
                gContext.setStroke(Color.WHITE);
            } else if (RadBtnBlue.isSelected())
            {
                gContext.setFill(Color.BLUE);
                gContext.setStroke(Color.BLUE);
            }
            else if (RadBtnGreen.isSelected())
            {
                gContext.setFill(Color.GREEN);
                gContext.setStroke(Color.GREEN);
            } else
            {
                gContext.setFill(Color.rgb(255, 230, 143));
                gContext.setStroke(Color.rgb(255, 230, 143));

            }
            



            if(Integer.parseInt(IterationNum.getText()) > 7 && RadBtnFrac2.isSelected())
            {
                //label.setText("With increasing iteration,\n the changes will\n not be noticeable");
            }
            if (RadBtnFrac1.isSelected()) {

                gContext.strokeLine(0, 200, 269, 200);
                gContext.strokeLine(70, 0, 70, 269);
                //gContext.setStroke(Color.WHITE);
                KochF( 70-70, 200-200, 120-70, 200-200, 145-70, 157-200, 170-70, 200-200, 220-70, 200-200, Integer.parseInt(IterationNum.getText()), gContext);

            } else {
                //gContext.setFill(Color.RED);
                //gContext.setStroke(Color.RED);
                //drawDragon(180, 200, 400, 450, 24, gContext);
                /*x1 = 60;
                y1 = (int)canvas.getHeight()/3*2;
                x2 = (int)canvas.getWidth() - 60;
                y2 = y1-20;
                dragonRecur( x1, y1, x2, y2, Integer.parseInt(IterationNum.getText()), gContext);*/
                //dragonRecur( 300, 500, 1000, 1100, Integer.parseInt(IterationNum.getText()), gContext);
                //double square = Math.min(canvas.getHeight(), canvas.getWidth());
               // x1 = (int)(canvas.getWidth()/2 - square/2 + square/2 * 0.53);
                //y1 = (int)canvas.getHeight()/5*3;
                //x2 = (int)(canvas.getWidth()/2 + square/2 - square/2 * 0.25);
                //y2 = y1;
                //dragonRecur( x1, y1, x2, y2, Integer.parseInt(IterationNum.getText()), gContext);

                gContext.strokeLine(0, 200, 269, 200);
                gContext.strokeLine(70, 0, 70, 269);
                //gContext.setStroke(Color.WHITE);
                dragonRecur2( 70-70, 200-200, 70-70, 50-200, 220-70, 200-200, 220-70, 50-200, Integer.parseInt(IterationNum.getText()), gContext);
            }
            Fractal.getChildren().add(canvas);});

        BtnDownload.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("*.png", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(root.getScene().getWindow());
            Image i = Fractal.snapshot(null, null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(i, null), "png", file);
            } catch (Exception e){}
        });

    }



    public void drawKochCurve(int n, double x1, double  y1, double x5, double y5) {
        double deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        if(n > 0) {
            deltaX = x5 - x1;
            deltaY = y5 - y1;


            x2 = x1 + deltaX / 3;
            y2 = y1 + deltaY / 3;

            x3 = (int) (0.5 * (x1+x5) - (Math.sqrt(3)/6) * (y1-y5));
            y3 = (int) (0.5 * (y1+y5) - (Math.sqrt(3)/6) * (x5-x1));

            x4 = x1 + 2 * deltaX /3;
            y4 = y1 + 2 * deltaY /3;

            drawKochCurve(n-1, x1, y1, x2, y2);
            drawKochCurve(n-1, x2, y2, x3, y3);
            drawKochCurve(n-1, x3, y3, x4, y4);
            drawKochCurve(n-1, x4, y4, x5, y5);
        }
        else{
            gContext.strokeLine(x1, y1, x5, y5);
        }
    }
    //void drawLevel0() {gContext.strokeLine(x, y, a, b);}

    /*public void paintComponent(GraphicsContext g)
    {
        super.paintComponents(g);
        g.setFill(Color.RED);
        drawDragon(180, 200, 400, 450, 24, g);
        repaint();
    }*/



    public void  dragonRecur( int x1, int y1, int x2, int y2, int k, GraphicsContext gContext) {
        if (k > 0) {
            int xn = (x1 + x2) / 2 + (y2 - y1) / 2;
            int yn = (y1 + y2) / 2 - (x2 - x1) / 2;
            dragonRecur(x2, y2, xn, yn, k - 1, gContext);
            dragonRecur(x1, y1, xn, yn, k - 1, gContext);
        } else {
            gContext.strokeLine(x1, y1, x2, y2);
        }
    }

    public void  dragonRecur2( int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int k, GraphicsContext gContext) {
        int x, y, xn, yn;
        int d = 100;
            ArrayList<Integer> dr1 = new ArrayList();
            dr1.add(x1);
            dr1.add(y1);
            dr1.add(x2);
            dr1.add(y2);
            dr1.add(x1);
            dr1.add(y1);
            dr1.add(x3);
            dr1.add(y3);
            dr1.add(x2);
            dr1.add(y2);
            dr1.add(x4);
            dr1.add(y4);
            dr1.add(x3);
            dr1.add(y3);
            dr1.add(x4);
            dr1.add(y4);
            ArrayList<Integer> dr2x = new ArrayList();
            ArrayList<Integer> dr2y = new ArrayList();
            ArrayList<Integer> dr2 = new ArrayList();
            for (int i = 0; i < k; i++){
                for (int jx = 0; jx < dr1.size(); jx+=2){
                    x = dr1.get(jx);
                    y = dr1.get(jx+1);
                    x = (int)(x/Math.sqrt(2));
                    y = (int)(y/Math.sqrt(2));
                    xn = (int)(0.5*x + 0.5*y);
                    yn = (int)(-0.5*x + 0.5*y);
                    dr2.add((int)(xn*Math.sqrt(2)));
                    dr2.add((int)(yn*Math.sqrt(2)));
                }
                for (int jx = 0; jx < dr1.size(); jx+=2){
                    x = dr1.get(jx);
                    y = dr1.get(jx+1);
                    x = (int)(x/Math.sqrt(2));
                    y = (int)(y/Math.sqrt(2));
                    xn = (int)(-0.5*x + 0.5*y) + d;
                    yn = (int)(-0.5*x - 0.5*y);
                    dr2.add((int)(xn*Math.sqrt(2)));
                    dr2.add((int)(yn*Math.sqrt(2)));
                }
                dr1.clear();
                for (int el : dr2) {
                    dr1.add(el);
                }
                dr2.clear();
               // d = (int)(d/Math.sqrt(2));
            }

            for (int jx = 0; jx < dr1.size(); jx+=4){
                x = dr1.get(jx);
                y = dr1.get(jx+1);
                xn = dr1.get(jx+2);
                yn = dr1.get(jx+3);
                gContext.strokeLine(x+70,y+200,xn+70,yn+200);
            }


    }

    public void  KochF( int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, int k, GraphicsContext gContext) {
        int x, y, xn, yn;
        int d = 150;
        ArrayList<Integer> dr1 = new ArrayList();
        dr1.add(x1);
        dr1.add(y1);
        dr1.add(x2);
        dr1.add(y2);

        dr1.add(x2);
        dr1.add(y2);
        dr1.add(x3);
        dr1.add(y3);

        dr1.add(x3);
        dr1.add(y3);
        dr1.add(x4);
        dr1.add(y4);

        dr1.add(x4);
        dr1.add(y4);
        dr1.add(x5);
        dr1.add(y5);
        ArrayList<Integer> dr2x = new ArrayList();
        ArrayList<Integer> dr2y = new ArrayList();
        ArrayList<Integer> dr2 = new ArrayList();
        for (int i = 0; i < k; i++){
            for (int jx = 0; jx < dr1.size(); jx+=2){
                x = dr1.get(jx);
                y = dr1.get(jx+1);
                xn = (int)(x/3);
                yn = (int)(y/3);
                dr2.add((int)(xn));//*Math.sqrt(2)));
                dr2.add((int)(yn));//*Math.sqrt(2)));
            }
            for (int jx = 0; jx < dr1.size(); jx+=2){
                x = dr1.get(jx);
                y = dr1.get(jx+1);
                x = (int)(x/3);
                y = (int)(y/3);
                xn = (int)(0.5*x + 0.86*y) + d/3;
                yn = (int)(-0.86*x + 0.5*y);
                dr2.add((int)(xn));
                dr2.add((int)(yn));
            }
            for (int jx = 0; jx < dr1.size(); jx+=2){
                x = dr1.get(jx);
                y = dr1.get(jx+1);
                x = (int)(x/3);
                y = (int)(y/3);
                xn = (int)(0.5*x - 0.86*y) + d/2;
                yn = (int)(0.86*x + 0.5*y) - (int)((d*Math.sqrt(3))/6);
                dr2.add((int)(xn));
                dr2.add((int)(yn));
            }
            for (int jx = 0; jx < dr1.size(); jx+=2){
                x = dr1.get(jx);
                y = dr1.get(jx+1);
                xn = (int)(x/3)+ (int)(d*2/3);
                yn = (int)(y/3);
                dr2.add((int)(xn));//*Math.sqrt(2)));
                dr2.add((int)(yn));//*Math.sqrt(2)));
            }
            dr1.clear();
            for (int el : dr2) {
                dr1.add(el);
            }
            dr2.clear();
            // d = (int)(d/Math.sqrt(2));
        }

        for (int jx = 0; jx < dr1.size(); jx+=4){
            x = dr1.get(jx);
            y = dr1.get(jx+1);
            xn = dr1.get(jx+2);
            yn = dr1.get(jx+3);
            gContext.strokeLine(x+70,y+200,xn+70,yn+200);
        }


    }
    void Error_Empty()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please, enter the number!", ButtonType.OK);
        alert.showAndWait();
    }
}

