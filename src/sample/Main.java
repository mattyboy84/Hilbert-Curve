package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    static Rectangle2D screenBounds = Screen.getPrimary().getBounds();

    //int width = 1080;
    //int height = 1080;
    int width = (int) screenBounds.getHeight();
    int height =(int) screenBounds.getHeight();

    int order = 3;
    int quadrants = (int) Math.pow(2, order);
    int N = quadrants * quadrants;

    Group group = new Group();
    Scene scene = new Scene(group, width, height);
    ObservableList<Transform> test;

    Hilbert_Curve curve;

    @Override
    public void start(Stage stage) throws Exception {
        int scale = 2;
        int depth = 1;
        curve = new Hilbert_Curve(width, height, order, quadrants, N, group, scene, depth, scale);

        //System.out.println(curve.topLeft.topLeft.topLeft.top.getBoundsInParent());

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case F:
                    stage.setFullScreen(!stage.isFullScreen());
                    break;
                case A:
                    //this.curve.topLeft.topLeft.leftCurve.getTransforms().clear();
                    this.curve.topLeft.bottomRight.bottomCurve.getTransforms().add(0,new Rotate(90, 270, 270));

                    //this.curve.topLeft.topLeft.leftCurve.getTransforms().clear();
                    //this.curve.topLeft.topLeft.rightCurve.getTransforms().clear();
                    //this.curve.topLeft.topLeft.bottomCurve.getTransforms().clear();

                    //this.curve.topLeft.topLeft.rotateQuad(new Vecc2f(270,270),b);
                    //this.curve.topLeft.topLeft.


                    //new transform each time, 90,180,270,360
                    break;
                case B:
                    //test=this.curve.topLeft.bottomRight.bottomCurve.getTransforms();
                    //this.curve.topLeft.bottomRight.bottomCurve.getTransforms().clear();
                    //try {
                        this.curve.topLeft.bottomRight.bottomCurve.getTransforms().add(0, new Rotate(90, 540, 540));
                    //}catch (Exception e){
                    //    this.curve.topLeft.bottomRight.bottomCurve.getTransforms().add(new Rotate(90, 540, 540));
//
                    //}
                    //for (Transform transform : test) {
                    //    this.curve.topLeft.bottomRight.bottomCurve.getTransforms().add(transform);
                    //}
                    break;
            }
        });


        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
