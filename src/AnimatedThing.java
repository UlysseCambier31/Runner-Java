/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

abstract class AnimatedThing {
    protected double x;
    protected double y;
    protected ImageView imgView;
    protected int attitude;
    protected int index;
    protected double frameduration;
    protected int maxindex;
    protected int subindex;
    protected int maxsubindex;
    protected double winSizeX,winSizeY;

    public AnimatedThing(double x, double y, ImageView imgView, int attitude) {
        this.x = x;
        this.y = y;
        this.imgView = imgView;
        this.attitude =attitude;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImageView getImgView() {
        return imgView;
    }

}
