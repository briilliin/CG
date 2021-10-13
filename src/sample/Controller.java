package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final float w1 = 200.0f;
    private final float w2 = 100.0f;
    private final float h = 200.0f;
    private final float r = 100.0f;
    @FXML
    private Slider sldW1;

    @FXML
    private Slider sldW2;

    @FXML
    private Slider sldH;

    @FXML
    private Slider sldR;

    @FXML
    private Slider sldSc;

    @FXML
    private Canvas mainCanvas;

    @FXML
    private Slider sldRt;

    @FXML
    private Button button;

    @FXML
    private Button buttonCompare;

    @FXML
    public void onClickMethod() {
        button.setText("Thanks!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        draw();

        button.setOnAction(actionEvent -> {
            sldW1.setValue(2);
            sldW2.setValue(1);
            sldH.setValue(2);
            sldR.setValue(1);
            sldSc.setValue(100);
            sldRt.setValue(1.0);
            draw();
        });

        buttonCompare.setOnAction(actionEvent -> {
            standard_draw();
        });





        sldW1.valueChangingProperty().addListener((observableValue, aBoolean, t1) ->
                draw());
        sldW2.valueChangingProperty().addListener((observableValue, aBoolean, t1) ->
                draw());
        sldH.valueChangingProperty().addListener((observableValue, aBoolean, t1) ->
                draw());
        sldR.valueChangingProperty().addListener((observableValue, aBoolean, t1) ->
                draw());
        sldRt.valueChangingProperty().addListener((observableValue, aBoolean, t1) ->
                draw());
        sldSc.valueChangingProperty().addListener((observableValue, aBoolean, t1) ->
                draw());


    }

    void draw() {

        GraphicsContext ctx = mainCanvas.getGraphicsContext2D();

        ctx.setFill(Color.WHITE);
        ctx.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());


        ctx.save();
        Affine transform = ctx.getTransform();
        transform.appendTranslation(mainCanvas.getWidth() / 2, mainCanvas.getHeight() / 2);
        transform.appendScale(-sldSc.getValue(), -sldSc.getValue());
        transform.append(Transform.rotate(sldRt.getValue(), -sldW1.getValue() / 2, -sldH.getValue() / 2));

        ctx.setTransform(transform);


        double w1 = sldW1.getValue();
        double w2 = sldW2.getValue();
        double h = sldH.getValue();
        double r = sldR.getValue();


        ctx.setLineWidth(1. / sldSc.getValue());


        ctx.strokePolygon(
                new double[]{w1 / 2, -w1 / 2, -w1 / 2, w1 / 2 - w2},
                new double[]{-h / 2, -h / 2, h / 2, h / 2},
                4);


        ctx.strokeArc(-w1 / 2 - r, -h / 2 - r, 2 * r, 2 * r, -90, 90, ArcType.ROUND);


        ctx.restore(); //создать кнопку сохраняющую исходную модель  возвращающую к ней
    }

    void standard_draw() {
        GraphicsContext ctx = mainCanvas.getGraphicsContext2D();

        ctx.setFill(Color.WHITE);
        ctx.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());


        ctx.save();
        Affine transform = ctx.getTransform();
        transform.appendTranslation(mainCanvas.getWidth() / 2, mainCanvas.getHeight() / 2);

        ctx.setTransform(transform);

        ctx.strokePolygon(
                new double[]{-w1 / 2, w1 / 2, w1 / 2, -w1 / 2 + w2},
                new double[]{h / 2, h / 2, -h / 2, -h / 2},
                4);


        ctx.strokeArc(w1 / 2 - r, h / 2 - r, 2 * r, 2 * r, 90, 90, ArcType.ROUND);
        ctx.restore();
    }


}