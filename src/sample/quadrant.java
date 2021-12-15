package sample;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class quadrant {


    Vecc2f position;
    Vecc2f center;
    quadrant topLeft;
    quadrant topRight;
    quadrant bottomLeft;
    quadrant bottomRight;
    boolean leaf;
    Line top;
    Line bottom;
    Line left;
    Line right;
    //
    Rectangle bottomCurve, leftCurve, rightCurve;
    Rectangle leftConnect,middleConnect,rightConnect;
    //
    int order;
    int depth;
    int curveORDER;
    int xGap,yGap;

    int WIDTH_HEIGHT = 2;


    public quadrant(int width, int height, int positionX, int positionY, int order, int quadrants, int n, Vecc2f smallPos, int depth, Group group, int scale) {

        this.curveORDER=order;
        this.position = new Vecc2f(positionX, positionY);

        if (depth < order) {
            this.leaf = false;
            depth++;
            scale = scale * 2;
            //
            this.topLeft = new quadrant(width, height, positionX, positionY, order, quadrants, n, new Vecc2f(0, 0), depth, group, scale);
            //left();
            this.bottomLeft = new quadrant(width, height, positionX, positionY + (height / scale), order, quadrants, n, new Vecc2f(0, 1), depth, group, scale);
            this.bottomRight = new quadrant(width, height, positionX + (width / scale), positionY + (height / scale), order, quadrants, n, new Vecc2f(1, 1), depth, group, scale);
            this.topRight = new quadrant(width, height, positionX + (width / scale), positionY, order, quadrants, n, new Vecc2f(1, 0), depth, group, scale);
            //
            this.center = new Vecc2f(this.position.x + (width / (scale)), this.position.y + (height / (scale)));

        } else {//final child 'LEAF'
            this.leaf = true;
            //grid
            this.left = new Line(positionX, positionY, positionX, (positionY + (height / scale)));
            this.top = new Line(positionX, positionY, positionX + (height / scale), (positionY));
            this.right = new Line(positionX + (height / scale), positionY, positionX + (height / scale), positionY + (height / scale));
            this.bottom = new Line(positionX, positionY + (height / scale), positionX + (height / scale), positionY + (height / scale));
            //System.out.println(this.center);
            //
            xGap = (int) ((width / scale) * 0.15);
            yGap = (int) ((height / scale) * 0.15);
            //System.out.println(xGap);
            this.center = new Vecc2f(this.position.x + (width / (scale * 2)), this.position.y + (height / (scale * 2)));

            leftCurve = new Rectangle(positionX + xGap, positionY + yGap, WIDTH_HEIGHT, (height / scale) - (2 * yGap));
            rightCurve = new Rectangle(positionX + (width / scale) - xGap, positionY + yGap, WIDTH_HEIGHT, (height / scale) - (2 * yGap));
            bottomCurve = new Rectangle(positionX + xGap, positionY + (height / scale) - (yGap), (width / scale) - (2 * xGap), WIDTH_HEIGHT);

            //System.out.println((height / scale) - (2 * yGap));
            //topCurve = new Line(positionX + xGap, positionY + yGap, (positionX + (width / scale) - xGap), (positionY + yGap));
            //bottomCurve = new Line(positionX + xGap, positionY + (height / scale) - yGap, (positionX + (width / scale) - xGap), positionY + (height / scale) - yGap);
            //leftCurve = new Line(positionX + xGap, positionY + yGap, (positionX + xGap), (positionY + (height / scale) - yGap));
            //rightCurve = new Line((positionX + (width / scale) - xGap), (positionY + yGap), (positionX + (width / scale) - xGap), positionY + (height / scale) - yGap);
            group.getChildren().addAll(this.left, this.right, this.bottom, this.top);
            group.getChildren().addAll(this.leftCurve, this.rightCurve, this.bottomCurve);
            //this.leftCurve.setRotate(90);
            //group.getChildren().addAll(this.topCurve, this.bottomCurve, this.leftCurve, this.rightCurve);
            //this.topCurve.setVisible(false);
            //this.invisLine = topCurve;
            //this.invisLineS = "up";
            //System.out.println(this.position);
            //left();
        }
        //
        this.order = order;
        this.depth = depth;
    }

    public void move(Group group) {
        //System.out.println(depth);
        //if (!this.topLeft.leaf) {
        //    this.topLeft.move();
        //} else {
        //    this.topLeft.rotate(270, (int) (this.topLeft.center.x), (int) (this.topLeft.center.y));
        //}
        //if (!this.topRight.leaf) {
        //    this.topRight.move();
        //} else {
        //    this.topRight.rotate(90, (int) (this.topRight.center.x), (int) (this.topRight.center.y));
        //}
        //rotate(90,270,270*3);


        if (this.depth==order){
            this.topLeft.rotate(270, (int) (this.topLeft.center.x), (int) (this.topLeft.center.y));
            this.topRight.rotate(90, (int) (this.topRight.center.x), (int) (this.topRight.center.y));
        }else {
            this.topLeft.move(group);
            this.topRight.move(group);
            this.bottomLeft.move(group);
            this.bottomRight.move(group);
        }
        //System.out.println(depth  +" " + order);
        if (depth+1==order){
            this.topLeft.rotate(270, (int) (this.topLeft.center.x), (int) (this.topLeft.center.y));
            this.topRight.rotate(90, (int) (this.topRight.center.x), (int) (this.topRight.center.y));
        }


            //this.topLeft.rotate(270, (int) (this.topLeft.center.x), (int) (this.topLeft.center.y));
            //this.topRight.rotate(90, (int) (this.topRight.center.x), (int) (this.topRight.center.y));


    }

    public void rotate(int i, int x, int y) {
        if (this.leaf) {
            this.leftCurve.getTransforms().add(0, new Rotate(i, x, y));
            this.rightCurve.getTransforms().add(0, new Rotate(i, x, y));
            this.bottomCurve.getTransforms().add(0, new Rotate(i, x, y));
        } else {
            topLeft.rotate(i, x, y);
            topRight.rotate(i, x, y);
            bottomLeft.rotate(i, x, y);
            bottomRight.rotate(i, x, y);
        }
    }

}