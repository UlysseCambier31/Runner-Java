/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import org.w3c.dom.css.Rect;

public class Enemi extends AnimatedThing{
    int EnemiType;
    public Enemi(double x, double y, ImageView imgView,int EnemiType) {
        super(x, y, imgView,0);
        imgView.setViewport(new Rectangle2D(20,0,65,100));
        imgView.setX(this.x);
        imgView.setY(this.y);
        this.frameduration=4;
        this.index=0;
        this.subindex=0;
        this.maxindex=5;
        this.EnemiType = EnemiType;
    }
    public void update(long time, Camera cam){
        subindex++;
        if (subindex>frameduration) {subindex=0;index++;}
        if (index>maxindex) index=0;
        //crab Enemi
        if (EnemiType==0) {
            imgView.setViewport(new Rectangle2D(0 + (85 * (index % 3)), 0, 85, 100));
        }
        //Spikes
        else if (EnemiType==1) {
            imgView.setViewport(new Rectangle2D(0+(85*3), 0, 85, 100));
        }
        //Fish Up
        else if (EnemiType==2) {
            if (y > 10) {
                y -=7 ;
                imgView.setViewport(new Rectangle2D(0, 160, 85, 100));
            } else {
                EnemiType=4;
            }
        }
        //thing on tree (goes with tree)
        if (EnemiType==3) {
            imgView.setViewport(new Rectangle2D(0 + (85 * (index % 3)), 160*2, 85, 100));
        }
        //Fish Down
        else if (EnemiType==4) {
            if (y < 500) {
                y +=7;
                imgView.setViewport(new Rectangle2D(85, 160, 85, 100));
            } else {
                EnemiType=0;
            }
        }
        //tree
        if (EnemiType==5) {
            imgView.setViewport(new Rectangle2D(479, 341, 125, 290));
        }
        //Explosion Effect
        if (EnemiType==6) {
            imgView.setViewport(new Rectangle2D(0 + (85 * (index % 5)), 160*3, 85, 100));
            if (index==5){
                EnemiType=7;
            }
        }
        //Explosion End
        if (EnemiType==7) {
            imgView.setViewport(new Rectangle2D(0 + (85 * 5), 160*3, 85, 100));
        }
        if (EnemiType==8){
            imgView.setViewport(new Rectangle2D(0,0,124,400));
        }
        x = (x - cam.getVx()*16*(Math.pow(10,-3)*4));
        imgView.setY(y);
        imgView.setX(x-cam.getX());
    }

}
