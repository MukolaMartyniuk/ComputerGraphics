package sample;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class STriangle {

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane Fractal;

    @FXML
    private LineChart<Number, Number> GrafLine;

    @FXML
    private Slider sliderF;

    @FXML
    private TextField IterationNum;

    @FXML
    private Button BtnOkTriangle;

    @FXML
    private ImageView BtnDownload;

    @FXML
    private ImageView InfoT;

    @FXML
    private TextField xA;

    @FXML
    private TextField xB;

    @FXML
    private TextField xC;

    @FXML
    private TextField yA;

    @FXML
    private TextField yB;

    @FXML
    private TextField yC;

    @FXML
    private TextField Max_X;

    @FXML
    private TextField Max_Y;

    @FXML
    private TextField Step;

    @FXML
    private Button BtnOkGraf;

    @FXML
    private NumberAxis X_axis;

    @FXML
    private NumberAxis Y_axis;

    @FXML
    private ImageView MenuBtn;





    double MAX_X;
    double MAX_Y;
    boolean IfCreate = false;


    @FXML
    void initialize() {

        Tooltip ttB4 = new Tooltip();
        ttB4.setText("Slider for iterations");
        ttB4.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");

        sliderF.setTooltip(ttB4);

        Tooltip ttB3 = new Tooltip();
        ttB3.setText("Button for constructing a triangle");
        ttB3.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");

        BtnOkTriangle.setTooltip(ttB3);


        Tooltip ttB2 = new Tooltip();
        ttB2.setText("Enter iterations");
        ttB2.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");

        IterationNum.setTooltip(ttB2);


        Tooltip ttB1 = new Tooltip();
        ttB1.setText("Cap for plotting");
        ttB1.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");

        BtnOkGraf.setTooltip(ttB1);

        Max_X.setText("10");
        Tooltip tt = new Tooltip();
        tt.setText("Enter the maximum x");
        tt.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");

        Max_X.setTooltip(tt);
        Max_Y.setText("10");
        Tooltip ttS1 = new Tooltip();
        ttS1.setText("Enter the maximum y");
        ttS1.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        Max_Y.setTooltip(ttS1);
        Step.setText("1");
        Tooltip ttA2 = new Tooltip();
        ttA2.setText("Enter the step");
        ttA2.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        Step.setTooltip(ttA2);
        xA.setText("3");
        Tooltip ttF3 = new Tooltip();
        ttF3.setText("Enter a point x1");
        ttF3.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        xA.setTooltip(ttF3);
        yA.setText("1");
        Tooltip ttF4 = new Tooltip();
        ttF4.setText("Enter a point y1");
        ttF4.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        yA.setTooltip(ttF4);
        xB.setText("4");
        Tooltip ttD5 = new Tooltip();
        ttD5.setText("Enter a point x2");
        ttD5.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        xB.setTooltip(ttD5);
        yB.setText("4");
        Tooltip ttD6 = new Tooltip();
        ttD6.setText("Enter a point y2");
        ttD6.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        yB.setTooltip(ttD6);
        xC.setText("7");
        Tooltip ttS7 = new Tooltip();
        ttS7.setText("Enter a point x3");
        ttS7.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
       xC.setTooltip(ttS7);
        yC.setText("3");
        Tooltip ttS8 = new Tooltip();
        ttS8.setText("Enter a point y3");
        ttS8.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        yC.setTooltip(ttS8);


        Step.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    Step.setText(oldValue);
                }
            }
        });

        Max_X.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    Max_X.setText(oldValue);
                }
            }
        });
        Max_Y.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    Max_X.setText(oldValue);
                }
            }
        });
        xA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(-?)\\d*(\\.\\d*)?")) {
                    xA.setText(oldValue);
                }
            }
        });
        yA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(-?)\\d*(\\.\\d*)?")) {
                    yA.setText(oldValue);
                }
            }
        });
        xB.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(-?)\\d*(\\.\\d*)?")) {
                    xB.setText(oldValue);
                }
            }
        });
        yB.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(-?)\\d*(\\.\\d*)?")) {
                    yB.setText(oldValue);
                }
            }
        });
        xC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(-?)\\d*(\\.\\d*)?")) {
                    xC.setText(oldValue);
                }
            }
        });
        yC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(-?)\\d*(\\.\\d*)?")) {
                    yC.setText(oldValue);
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

        InfoT.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("Information_T.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);
        });

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

       sliderF.setOnMouseClicked(mouseEvent -> {
            //sliderF = new Slider(0,10,1);
            double cof = sliderF.getValue();
            //sliderF.setMin(Double.parseDouble(IterationNum.getText()));
            //double cof = Double.parseDouble(IterationNum.getText());
            Double ax = Double.parseDouble(xA.getText());
            Double ay = Double.parseDouble(yA.getText());
            Double bx = Double.parseDouble(xB.getText());
            Double by = Double.parseDouble(yB.getText());
            Double cx = Double.parseDouble(xC.getText());
            Double cy = Double.parseDouble(yC.getText());
            try {
                MoveTriangle(cof ,ax, bx, cx, ay, by, cy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double r = sliderF.getValue();
           String result = String.format("%.2f",r);
            IterationNum.setText(result);
        });
        sliderF.setOnMousePressed(mouseEvent -> {
            double cof = sliderF.getValue();
           // sliderF.setMin(Double.parseDouble(IterationNum.getText()));
           // double cof = Double.parseDouble(IterationNum.getText());
            Double ax = Double.parseDouble(xA.getText());
            Double ay = Double.parseDouble(yA.getText());
            Double bx = Double.parseDouble(xB.getText());
            Double by = Double.parseDouble(yB.getText());
            Double cx = Double.parseDouble(xC.getText());
            Double cy = Double.parseDouble(yC.getText());
            try {
                MoveTriangle(cof ,ax, bx, cx, ay, by, cy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double r = sliderF.getValue();
            String result = String.format("%.2f",r);
            IterationNum.setText(result);
        });
       /* sliderF.setOnMouseMoved(mouseEvent -> {
            double cof = sliderF.getValue();
            sliderF.setMin(Double.parseDouble(IterationNum.getText()));
            //double cof = Double.parseDouble(IterationNum.getText());
            Double ax = Double.parseDouble(xA.getText());
            Double ay = Double.parseDouble(yA.getText());
            Double bx = Double.parseDouble(xB.getText());
            Double by = Double.parseDouble(yB.getText());
            Double cx = Double.parseDouble(xC.getText());
            Double cy = Double.parseDouble(yC.getText());
            try {
                MoveTriangle(cof ,ax, bx, cx, ay, by, cy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double r = sliderF.getValue();
            int s = (int)r;
            IterationNum.setText(Integer.toString(s));
        });*/

        BtnOkGraf.setOnAction(actionEvent -> {
            if(Max_X.getText().equals("") || Max_Y.getText().equals("") || Step.getText().equals("") )
            {
                Error_Empty();
                return;
            }

            X_axis.setAutoRanging(false);
            Y_axis.setAutoRanging(false);

            X_axis.setTickUnit(Double.parseDouble(Step.getText()));
            Y_axis.setTickUnit(Double.parseDouble(Step.getText()));
            X_axis.setUpperBound(Double.parseDouble(Max_X.getText()));
            Y_axis.setUpperBound(Double.parseDouble(Max_Y.getText()));
            X_axis.setLowerBound(-(Double.parseDouble(Max_X.getText())));
            Y_axis.setLowerBound(-(Double.parseDouble(Max_Y.getText())));
            if(!IfCreate) {
                MAX_X = Double.parseDouble(Max_X.getText());
                MAX_Y = Double.parseDouble(Max_Y.getText());
            }
            IfCreate = true;
            GrafLine.getData().clear();


        });

        BtnOkTriangle.setOnAction(actionEvent -> {
            if(xA.getText().equals("") || yA.getText().equals("") || xB.getText().equals("") || yB.getText().equals("")
                    || xC.getText().equals("") || yC.getText().equals(""))
            {
                Error_Empty();
                return;
            }
            GrafLine.getData().clear();
           // double cof = Double.parseDouble(IterationNum.getText());
            Double ax = Double.parseDouble(xA.getText());
            Double ay = Double.parseDouble(yA.getText());
            Double bx = Double.parseDouble(xB.getText());
            Double by = Double.parseDouble(yB.getText());
            Double cx = Double.parseDouble(xC.getText());
            Double cy = Double.parseDouble(yC.getText());

            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data<>(ax, ay));
            series.getData().add(new XYChart.Data<>(bx, by));

            XYChart.Series series2 = new XYChart.Series();
            series2.getData().add(new XYChart.Data<>(ax, ay));
            series2.getData().add(new XYChart.Data<>(cx, cy));

            XYChart.Series series3 = new XYChart.Series();
            series3.getData().add(new XYChart.Data<>(bx, by));
            series3.getData().add(new XYChart.Data<>(cx, cy));

            GrafLine.getData().add(series);
            GrafLine.getData().add(series2);
            GrafLine.getData().add(series3);


            ObservableList<XYChart.Data<Number, Number>> list = FXCollections.<XYChart.Data<Number, Number>>observableArrayList();
             list.add(createData(ax, ay, "A"));
             list.add(createData(bx, by, "B"));
             list.add(createData(cx, cy, "C"));
            GrafLine.getData().add(new XYChart.Series<Number, Number>(list));
        });


    }

    private Node createDataNode(String text) {
        var label = new Label(text);
        var pane = new Pane(label);
        pane.setShape(new Circle(6.0));
        pane.setScaleShape(false);

        label.translateYProperty().bind(label.heightProperty().divide(-1.5));

        return pane;
    }

    private XYChart.Data<Number, Number> createData(double x, double y, String s) {
        XYChart.Data<Number, Number> data = new XYChart.Data<Number, Number>(x, y);
        data.setNode(createDataNode(s));
        return data;
    }

    public void MoveTriangle(double mincof, double xA, double xB, double xC, double yA, double yB, double yC) throws InterruptedException {

        double xAn,xBn,xCn, yAn, yBn,yCn;
        double cof = mincof;

            xAn = xA * cof;
            yAn = yA * cof;
            xBn = xB * cof;
            yBn = yB * cof;
            xCn = xC * cof;
            yCn = yC * cof;

            GrafLine.getData().clear();
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data<>(xAn, yAn));
            series.getData().add(new XYChart.Data<>(xBn, yBn));

            XYChart.Series series2 = new XYChart.Series();
            series2.getData().add(new XYChart.Data<>(xAn, yAn));
            series2.getData().add(new XYChart.Data<>(xCn, yCn));

            XYChart.Series series3 = new XYChart.Series();
            series3.getData().add(new XYChart.Data<>(xBn, yBn));
            series3.getData().add(new XYChart.Data<>(xCn, yCn));

            GrafLine.getData().add(series);
            GrafLine.getData().add(series2);
            GrafLine.getData().add(series3);

            ObservableList<XYChart.Data<Number, Number>> list = FXCollections.<XYChart.Data<Number, Number>>observableArrayList();
            list.add(createData(xAn, yAn, "A"));
            list.add(createData(xBn, yBn, "B"));
            list.add(createData(xCn, yCn, "C"));
            GrafLine.getData().add(new XYChart.Series<Number, Number>(list));





    }
    void Error_Empty()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please, enter the number!", ButtonType.OK);
        alert.showAndWait();
    }

}
