import javafx.scene.image.ImageView;

public class AnimatedThing {
    private double x;
    private double y;
    private ImageView imgView;
    private int attitude;
    private int index;
    private double frameduration;
    private int maxindex;
    private double winSizeX,winSizeY;

    public AnimatedThing(double x, double y, ImageView imgView) {
        this.x = x;
        this.y = y;
        this.imgView = imgView;

    }
}
