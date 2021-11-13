/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */
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
        this.attitude=0;
    }
    public void jump() {
        attitude=1;
    }
    public void update(long time,Camera cam){
        subindex++;
        if (subindex>frameduration) {subindex=0;index++;}
        if (index>maxindex) index=0;
        if (attitude==0) {
            imgView.setViewport(new Rectangle2D(0 + (85 * (index % 6)), 0, 85, 100));
        } else if (attitude==1) {
            if (y > 10) {
                y -=7 ;
                imgView.setViewport(new Rectangle2D(0, 160, 85, 100));
            } else {
                attitude=2;
            }
        }
        else if (attitude==2) {
            if (y < 219) {
                y +=7;
                imgView.setViewport(new Rectangle2D(85, 160, 85, 100));
            } else {
                attitude = 0;
            }
        }
        x++;
        imgView.setY(y); //imgView.setY(y-cam.getY()); l'équilibre du ressort n'est pas bon... à faire.
        imgView.setX(x-cam.getX());
    }
}
