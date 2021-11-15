/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.css.Rect;

public class Enemi extends AnimatedThing{
    int EnemiType;
    private String syspath = System.getProperty("user.dir");
    public Enemi(double x, double y, ImageView imgView,int EnemiType) {
        super(x, y, imgView,0);
        imgView.setViewport(new Rectangle2D(20,0,65,100));
        imgView.setX(this.x);
        imgView.setY(this.y);
        this.frameduration=8;
        this.index=0;
        this.subindex=0;
        this.maxindex=5;
        this.EnemiType = EnemiType;
    }

    public int getEnemiType() {
        return EnemiType;
    }

    public void setEnemiType(int enemiType) {
        EnemiType = enemiType;
    }

    public void update(long time, Camera cam, GameScene scene){
        subindex++;//ralentissement de frame
        if (subindex>frameduration) {subindex=0;index++;}
        if (index>maxindex) index=0;

        //Boss bullets
        if (EnemiType==-2) {
            imgView.setViewport(new Rectangle2D((85 * 6), 160*2, 85, 100));
        }

        //Anneaux - Pas un enemi mais bon...
        if (EnemiType==-1) {
            imgView.setViewport(new Rectangle2D(0 + (64 * (index % 10)), 0, 64, 64));
        }

        //crab Enemi
        else if (EnemiType==0) {
            imgView.setViewport(new Rectangle2D(0 + (85 * (index % 3)), 0, 85, 100));
        }
        //Spikes
        else if (EnemiType==1) {
            imgView.setViewport(new Rectangle2D(0+(85*3)+(85*(index%3)), 0, 85, 100));
        }
        //Fish Up (goes with Fish down and bridge )
        else if (EnemiType==2) { // poisson avec saut simplifié
            if (y > 10) {
                y -=5;
                imgView.setViewport(new Rectangle2D(0, 160, 85, 100));
            } else {
                EnemiType=4;
            }
        }
        //thing on tree (goes with tree)
        if (EnemiType==3) {
            imgView.setViewport(new Rectangle2D(0 + (85 * (index % 3)), 160*2, 85, 100));
        }

        // Plus extactement des enemis, mais des images ou animations importantes en rapport avec.
        //Fish Down
        else if (EnemiType==4) { // Poisson avec descente simplifié
            if (y < 500) {
                y +=5;
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
        //pont
        if (EnemiType==8){

            imgView.setViewport(new Rectangle2D(0+(124*(index%2)),0,124,400));
        }
        //boss
        if (EnemiType==9){//boss in
            if (x > 450) {
                x -=1;
                imgView.setViewport(new Rectangle2D(0,0+(61*(index%4)),97,61));
            } else {
                EnemiType=12;
            }
        }
        if (EnemiType==10) {//boss out
            if (x < 600) {
                x +=1;
                imgView.setViewport(new Rectangle2D(0,0+(61*(index%4)),97,61));
            } else {
                EnemiType=13;
            }
        }
        if(EnemiType==11){//boss up
            if (y > 10) {
                y -=1;
                imgView.setViewport(new Rectangle2D(0,0+(61*(index%4)),97,61));
            } else {
                EnemiType=12;
            }
            if(scene.isBoss()==3) {
                EnemiType=10;
            }
        }
        if(EnemiType==12){//boss down
            if (y < 250) {
                y +=1;
                imgView.setViewport(new Rectangle2D(0,0+(61*(index%4)),97,61));
            }  else  {
                EnemiType=11;
            }
            if(scene.isBoss()==3) {
                EnemiType=10;
            }
        }
        if(EnemiType==13){//get boss out of screen
            y=600;
        }
        // Update position
        if (EnemiType>8) {
            imgView.setY(y);
            imgView.setX(x);
        } else {
            x = (x - cam.getVx() * 16 * (Math.pow(10, -3) * (1 + cam.getAcceleration() * (1 + (scene.getHeros().getSuperspeedmultiplier() * 2)))));
            imgView.setY(y);
            imgView.setX(x - cam.getX());
        }
    }

}
