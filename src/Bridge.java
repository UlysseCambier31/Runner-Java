import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Bridge extends AnimatedThing{
    public Bridge(double x, double y, ImageView imgView) {
        super(x, y, imgView, 0);
        imgView.setViewport(new Rectangle2D(0, 0, 125, 400));
        imgView.setX(this.x);
        imgView.setY(this.y);
    }
    public void update(long time, Camera cam) {
        x = (x - cam.getVx() * 16 * (Math.pow(10, -3) * 5));
        imgView.setX(x-cam.getX());
    }
}
