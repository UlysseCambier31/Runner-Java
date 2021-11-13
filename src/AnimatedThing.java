/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */
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

    public void setX(double x) {
        imgView.setX(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        imgView.setX(y);
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getFrameduration() {
        return frameduration;
    }

    public void setFrameduration(double frameduration) {
        this.frameduration = frameduration;
    }

    public int getMaxindex() {
        return maxindex;
    }

    public void setMaxindex(int maxindex) {
        this.maxindex = maxindex;
    }

    public double getWinSizeX() {
        return winSizeX;
    }

    public void setWinSizeX(double winSizeX) {
        this.winSizeX = winSizeX;
    }

    public double getWinSizeY() {
        return winSizeY;
    }

    public void setWinSizeY(double winSizeY) {
        this.winSizeY = winSizeY;
    }
}
