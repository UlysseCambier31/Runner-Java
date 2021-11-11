import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing {
    public Hero(double x, double y, ImageView imgView, int attitude) {
        super(x, y, imgView, attitude);
        imgView.setViewport(new Rectangle2D(20,0,65,100));
        imgView.setX(this.x);
        imgView.setY(this.y);
        this.frameduration=4;
        this.index=0;
        this.subindex=0;
        this.maxindex=5;
    }
    public void update(long time){
        subindex++;
        if (subindex>frameduration) {subindex=0;index++;}
        if (index>maxindex) index=0;
        this.getImgView().setViewport(new Rectangle2D(0+(85*(index%6)),0,85,100));
    }
}
