package sample;

import javafx.scene.Group;
import javafx.scene.Scene;

public class Hilbert_Curve {

    int width;
    int height;
    int order;
    int quadrants;
    int n;

    quadrant topLeft;
    quadrant topRight;
    quadrant bottomLeft;
    quadrant bottomRight;

    Vecc2f center;

    //leftCurve.getTransforms().add(new Rotate(90,this.position.x,this.position.y));//TODO Solution


    public Hilbert_Curve(int width, int height, int order, int quadrants, int n, Group group, Scene scene, int depth, int scale) {

        this.width = width;
        this.height = height;
        this.order = order;
        this.quadrants = quadrants;
        this.n = n;
        //
        depth++;
        //
        this.center = new Vecc2f(this.width / 2, this.height / 2);


        this.topLeft = new quadrant(width, height, 0, 0, order, quadrants, n, new Vecc2f(0, 0), depth, group, scale);
        this.bottomLeft = new quadrant(width, height, 0, height / scale, order, quadrants, n, new Vecc2f(0, 1), depth, group, scale);
        this.bottomRight = new quadrant(width, height, width / scale, height / scale, order, quadrants, n, new Vecc2f(1, 1), depth, group, scale);
        this.topRight = new quadrant(width, height, width / scale, 0, order, quadrants, n, new Vecc2f(1, 0), depth, group, scale);
        //

        this.bottomLeft.move();
        this.bottomRight.move();
        this.topLeft.move();
        this.topRight.move();
        //
        this.topLeft.rotate(270, (int) (this.topLeft.center.x), (int) (this.topLeft.center.y));
        this.topRight.rotate(90, (int) (this.topRight.center.x), (int) (this.topRight.center.y));


    }
}
