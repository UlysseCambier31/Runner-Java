import javafx.scene.image.ImageView;

public class staticThing {
    private double x;
    private double y;
    private ImageView imgView;

    public staticThing(double x, double y, ImageView imgView) {
        this.x = x;
        this.y = y;
        this.imgView = imgView;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }
}
