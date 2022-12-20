package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

public class SColor {

    @FXML
    private AnchorPane root;

    @FXML
    private RadioButton RadBtnRGB;

    @FXML
    private ToggleGroup MyGroup;

    @FXML
    private RadioButton RadBtnHSL;

    @FXML
    private Slider sliderF;

    @FXML
    private TextField RedNum;

    @FXML
    private Button BtnRedOk;

    @FXML
    private ImageView BtnDownload;

    @FXML
    private TextField R;

    @FXML
    private TextField G;

    @FXML
    private TextField B;

    @FXML
    private TextField H;

    @FXML
    private TextField S;

    @FXML
    private TextField L;

    @FXML
    private ImageView BtnUpload;

    @FXML
    private AnchorPane Image_Original;

    @FXML
    private ImageView Image_Original_view;

    @FXML
    private AnchorPane Image_After;

    @FXML
    private ImageView Image_After_view;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private ImageView InfoC;

    private File file;
    ArrayList<Color> list_of_rgb;
    ArrayList<Color> list_of_rgb_from_hsl;
    ArrayList<ArrayList<Double>> list_of_hsl;
    //ArrayList<Color> tempRGB;
    ArrayList<Double> tempHSL;
    Color[][] colors;
    Color[][] colorsfromHSL;
    BufferedImage bufferedImage;
    BufferedImage img;

    private int p;
    private Color color;


    @FXML
    void initialize() {

        //BtnRedOk.setDisable(false);

        RedNum.setText("0");

        Tooltip ttS8 = new Tooltip();
        ttS8.setText("Conclusion RGB");
        ttS8.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        R.setTooltip(ttS8);
        G.setTooltip(ttS8);
        B.setTooltip(ttS8);

        Tooltip tt8 = new Tooltip();
        tt8.setText("Conclusion HSL");
        tt8.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        H.setTooltip(tt8);
        S.setTooltip(tt8);
        L.setTooltip(tt8);

        Tooltip tt = new Tooltip();
        tt.setText("Change of illumination");
        tt.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        BtnRedOk.setTooltip(tt);

        Tooltip tt1 = new Tooltip();
        tt1.setText("The amount of light");
        tt1.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        RedNum.setTooltip(tt1);

        Tooltip tt2 = new Tooltip();
        tt2.setText("HSL");
        tt2.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        RadBtnHSL.setTooltip(tt2);

        Tooltip tt3 = new Tooltip();
        tt3.setText("RGB");
        tt3.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        RadBtnRGB.setTooltip(tt3);

        Tooltip tt4 = new Tooltip();
        tt4.setText("The amount of light");
        tt4.setStyle("-fx-font: normal bold 4 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + "-fx-font-size: 13px");
        sliderF.setTooltip(tt4);


        RedNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    RedNum.setText(oldValue);
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

        InfoC.setOnMouseClicked(mouseEvent -> {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("Information_C.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            root.getChildren().setAll(pane);

        });

        sliderF.setOnMouseClicked(mouseEvent -> {
            //sliderF = new Slider(0,10,1);
            double r = sliderF.getValue()*100;
            int s = (int)r;
            RedNum.setText(Integer.toString(s));
        });
        sliderF.setOnMousePressed(mouseEvent -> {
            double r = sliderF.getValue()*100;
            int s = (int)r;
            RedNum.setText(Integer.toString(s));
        });
        BtnUpload.setOnMouseClicked(mouseEvent -> {
            BtnRedOk.setVisible(true);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
            Stage stage = (Stage)Image_Original.getScene().getWindow();

            file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Image_Original_view.setPreserveRatio(false);
                Image_Original_view.setImage(new Image(file.toURI().toString()));
            }
        });

        BtnRedOk.setOnMouseClicked(mouseEvent -> {
            if(RedNum.getText().equals(""))
            {
                Error_Empty();
                return;
            }
            try {
                img = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (RadBtnRGB.isSelected()) {
                SetRGB();
                print(colors);
            } else if (RadBtnHSL.isSelected()) {
                HSL();
            }
           // if (RedNum.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Ooops, there was an error!");
          //  }
            //rgb_to_hsl(255,0,0);

        });

        BtnDownload.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("*.png", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(root.getScene().getWindow());
            Image i = Image_After.snapshot(null, null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(i, null), "png", file);
            } catch (Exception e){}
        });

        Image_Original_view.setOnMouseMoved(e -> {

                int p = img.getRGB((int) e.getX(), (int) e.getY());
                Color color = new Color(p, true);
                R.setText(String.valueOf(color.getRed()));
                G.setText(String.valueOf(color.getGreen()));
                B.setText(String.valueOf(color.getBlue()));
                tempHSL = new ArrayList<>();
                rgb_to_hsl(color.getRed(), color.getGreen(), color.getBlue());
                H.setText(String.valueOf((int)(tempHSL.get(0)*360)));
                S.setText(String.valueOf(BigDecimal.valueOf(tempHSL.get(1)*100)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue()));
                L.setText(String.valueOf(BigDecimal.valueOf(tempHSL.get(2)*100)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue()));
        });

        Image_After_view.setOnMouseMoved(e -> {
            int p = img.getRGB((int) e.getX(), (int) e.getY());
            Color color = new Color(p, true);
            R.setText(String.valueOf(color.getRed()));
            G.setText(String.valueOf(color.getGreen()));
            B.setText(String.valueOf(color.getBlue()));
            tempHSL = new ArrayList<>();
            rgb_to_hsl(color.getRed(), color.getGreen(), color.getBlue());
            double h = tempHSL.get(0)*360;
            double s = tempHSL.get(1)*100;
            double l = tempHSL.get(2)*100;
            if(h <= 6000 && h >= 0 /*|| h>=375 && h<=399*/)
            {
                l = sliderF.getValue()*100;
            }
            H.setText(String.valueOf((int)h));
            S.setText(String.valueOf(BigDecimal.valueOf(s)
                    .setScale(1, RoundingMode.HALF_UP)
                    .doubleValue()));
            L.setText(String.valueOf(BigDecimal.valueOf(l)
                    .setScale(1, RoundingMode.HALF_UP)
                    .doubleValue()));

            double[] rgb = Arrays.copyOf(HSLtoRGB(tempHSL.get(0),tempHSL.get(1),tempHSL.get(2)), 3);
            R.setText(String.valueOf((int)rgb[0]));
            G.setText(String.valueOf((int)rgb[1]));
            B.setText(String.valueOf((int)rgb[2]));
        });

    }


    void HSL()
    {
        SetRGB();
        list_of_rgb_from_hsl = new ArrayList<>();
        list_of_hsl = new ArrayList<>();
        tempHSL = new ArrayList<>();
        double r, g, b;
        double h, s, l;
        for (Color ALI : list_of_rgb) {
            r = ALI.getRed();
            g = ALI.getGreen();
            b = ALI.getBlue();
            tempHSL = new ArrayList<>();

            // Slider_color.getValue()
            rgb_to_hsl(r,g,b);

            list_of_hsl.add(tempHSL);
        }
        int conter = -1;
        for (ArrayList<Double> ALI : list_of_hsl) {
            ++conter;
            h = ALI.get(0) * 360;
            if (h <= 6000 && h >= 0 )
            {
                ALI.set(2, sliderF.getValue() );
                list_of_hsl.set(conter, ALI);
            }
            if (h <= 130000 && h >= 120000 )
            {
                ALI.set(2, sliderF.getValue() );
                list_of_hsl.set(conter, ALI);
            }
        }

        for (ArrayList<Double> ALI : list_of_hsl) {
            h = ALI.get(0);
            s = ALI.get(1);
            l = ALI.get(2);

            double[] rgb = Arrays.copyOf(HSLtoRGB(h,s,l), 3);
            list_of_rgb_from_hsl.add(new Color((int)rgb[0], (int)rgb[1], (int)rgb[2]));
        }

        int count = -1;
        colorsfromHSL = new Color[img.getWidth()][img.getHeight()];
        for (int x = 0; x < img.getWidth(); x++) {
            ++count;
            for (int y = 0; y < img.getHeight(); y++) {
                colorsfromHSL[x][y] = list_of_rgb_from_hsl.get(count);
                if(y+1 < img.getHeight())
                    ++count;
            }
        }

        print(colorsfromHSL);

    }


    void SetRGB()
    {
        bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        list_of_rgb = new ArrayList<>();
        colors = new Color[img.getWidth()][img.getHeight()];
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int pixel = img.getRGB(x, y);
                Color c = new Color(pixel, true);
                list_of_rgb.add(c);
                colors[x][y] = c;
            }

        }


    }

    void print(Color [][] colors)
    {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                bufferedImage.setRGB(x, y, colors[x][y].getRGB());

            }
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        Image_After_view.setPreserveRatio(false);
        Image_After_view.setImage(image);
    }

    void rgb_to_hsl(double r, double g, double b){
        r = r / 255.0;
        g = g / 255.0;
        b = b / 255.0;

        double cmax = Math.max(r, Math.max(g, b));
        double cmin = Math.min(r, Math.min(g, b));
        double h = 0, s = 0, l = 0;

        double d = cmax - cmin;

        if (d == 0)
            h = 0;
        else if (cmax == r)
            h = ((g - b)/d) % 6;
        else if (cmax == g)
            h = (b - r)/d + 2;
        else
            h = (r - g)/d + 4;

        h = Math.round(h * 60);

        if (h < 0)
            h += 360;

        l = (cmax + cmin)/2;

        s = d == 0 ? 0 : d / (1 - Math.abs(2 * l - 1));

        //s = +(s * 100);
        //l = +(l * 100);


        //System.out.println("h = " +(int)h+" s = " +(int)s+ " l = " +(int)l);


        tempHSL.add(h);
        tempHSL.add(s);
        tempHSL.add(l);
    }

    void rgb_to_hsl(double r, double g, double b, double saturation){
        r = r / 255.0;
        g = g / 255.0;
        b = b / 255.0;

        double cmax = Math.max(r, Math.max(g, b));
        double cmin = Math.min(r, Math.min(g, b));
        double h = 0, s = 0, l = saturation;

        double d = cmax - cmin;

        if (d == 0)
            h = 0;
        else if (cmax == r)
            h = ((g - b)/d) % 6;
        else if (cmax == g)
            h = (b - r)/d + 2;
        else
            h = (r - g)/d + 4;

        h = Math.round(h * 60);

        if (h < 0)
            h += 360;

        //l = (cmax + cmin)/2;

        s = d == 0 ? 0 : d / (1 - Math.abs(2 * l - 1));

       // l = saturation;

        s = (s * 100);
        l = (l * 100);



        tempHSL.add(h);
        tempHSL.add(s);
        tempHSL.add(l);
    }

    public static double[] HSLtoRGB(double h, double s, double l) {
        //h*=  360;

      //  s /= 100;
       // l /= 100;


        double c = (1 - Math.abs(2 * l - 1)) * s;
        double x = c * (1 - Math.abs((h / 60) % 2 - 1));
        double m = l - c / 2;
        double r = 0;
        double g = 0;
        double b = 0;

        double[] rgb = new double[3];

        if (0 <= h && h < 60) {
            r = c;
            g = x;
            b = 0;
        } else if (60 <= h && h < 120) {
            r = x;
            g = c;
            b = 0;
        } else if (120 <= h && h < 180) {
            r = 0;
            g = c;
            b = x;
        } else if (180 <= h && h < 240) {
            r = 0;
            g = x;
            b = c;
        } else if (240 <= h && h < 300) {
            r = x;
            g = 0;
            b = c;
        } else if (300 <= h && h < 360) {
            r = c;
            g = 0;
            b = x;
        }
        r = Math.round((r + m) * 255);
        g = Math.round((g + m) * 255);
        b = Math.round((b + m) * 255);
       // System.out.println(r+" "+g+" "+b+" ");
        rgb[0] = r ;
        rgb[1] = g ;
        rgb[2] = b ;
        return rgb;
    }
    void Error_Empty()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please, enter the number!", ButtonType.OK);
        alert.showAndWait();
    }



}

