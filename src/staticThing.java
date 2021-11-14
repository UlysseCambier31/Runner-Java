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

    public ImageView getImgView() {
        return imgView;
    }
}
