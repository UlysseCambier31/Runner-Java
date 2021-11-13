/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing {
    public int indexsuperspeedstart;
    public int indexsuperspeedstartmax;
    public int indexsuperspeedmax;
    public int indexsuperspeed;
    public int superspeedstate;
    public int superspeedmultiplier;
    public int stamina;
    public boolean isinvincible;

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
        this.stamina = 1;
        this.indexsuperspeedstartmax = 20;
        this.indexsuperspeedmax=200;
        this.indexsuperspeed = 0;
        this.indexsuperspeedstart=0;
        this.superspeedstate = 0;
        this.isinvincible= false;
    }
    public void jump() {
        if (attitude==0) {
            attitude = 1;
        }
    }

    public int getSuperspeedmultiplier() {
        return superspeedmultiplier;
    }

    public void superspeed() {
        if (attitude==0&&stamina==1000){
            superspeedstate = 1;
            stamina = 1;
            isinvincible=true;
        }
    }
    public boolean isInvincible() {
        return  isinvincible;
    }

    public int getStamina() {
        return stamina;
    }

    static  boolean collision(Hero hero, Enemi enemi) {
        double alpha = 20;
        double rectaleft = hero.getX()+alpha;
        double rectaright = hero.getX()+hero.getImgView().getViewport().getWidth()-alpha;
        double rectatop = hero.getY()+hero.getImgView().getViewport().getHeight()-alpha;
        double rectabottom = hero.getY()+alpha;

        double rectbleft = enemi.getX()+alpha;
        double rectbright = enemi.getX()+enemi.getImgView().getViewport().getWidth()-alpha;
        double rectbtop= enemi.getY()+enemi.getImgView().getViewport().getHeight()-alpha;
        double rectbbottom = enemi.getY()+alpha;
        if (rectaleft < rectbright && rectaright > rectbleft &&
                rectatop > rectbbottom && rectabottom < rectbtop ) {
            return  true;
        }
        return  false;
    }
    public void update(long time,Camera cam,GameScene scene){
        subindex++;
        if (subindex>frameduration) {subindex=0;index++;} // réduction du temps frames
        if (index>maxindex) index=0;
        if (attitude==0) { // course normale.
            imgView.setViewport(new Rectangle2D(0 + (85 * (index % 6)), 0, 85, 100));
            if (stamina<1000) {
                stamina++;
            }
        } else if (attitude==1) { // Saut simplifié
            if (y > 10) {
                y -=7 ;
                imgView.setViewport(new Rectangle2D(0, 160, 85, 100));
            } else {
                attitude=2;
            }
        }
        else if (attitude==2) { // descente simplifiée
            if (y < 219) {
                y +=7;
                imgView.setViewport(new Rectangle2D(85, 160, 85, 100));
            } else {
                attitude = 0;
            }
        } else if (attitude==3){ //touché saut
            if (y > 10) {
                y -=5 ;
                imgView.setViewport(new Rectangle2D(85*2+(85*(index%2)), 160, 85, 100));
            } else {
                attitude=4;
            }
        } else if (attitude==4){ //touché saut bas
            if (y < 219) {
                y +=5;
                imgView.setViewport(new Rectangle2D(85*2+(85*(index%2)), 160, 85, 100));
            } else {
                attitude = 0;
                isinvincible= false;
            }
        } else if (attitude==5){ //superspeed début & fin
            imgView.setViewport(new Rectangle2D(0 + (85 * (index % 4)), 160*2, 85, 100));
        } else if (attitude==6){ //superspeed
            imgView.setViewport(new Rectangle2D(0 + (85 * 4)+(85*(index%2)), 160*2, 85, 100));
        }

        if(superspeedstate==1) {
            attitude = 5;
            superspeedmultiplier = 1;
            indexsuperspeedstart ++;
            if (indexsuperspeedstart==indexsuperspeedstartmax) {
                superspeedstate=2;
                indexsuperspeedstart=0;
            }
        } else if (superspeedstate==2){
            attitude = 6;
            superspeedmultiplier = 2;
            indexsuperspeed++;
            if(indexsuperspeed==indexsuperspeedmax) {
                superspeedstate=3;
                indexsuperspeed = 0;
            }
        } else if (superspeedstate ==3){
            attitude=5;
            superspeedmultiplier = 1;
            indexsuperspeedstart++;
            if(indexsuperspeedstart==indexsuperspeedstartmax){
                superspeedstate=0;
                indexsuperspeedstart=0;
                attitude = 0;
                superspeedmultiplier = 0;
                isinvincible=false;
            }
        }

        if (scene.getNumberOfLives()>0) {
            x++;
        }
        imgView.setY(y); //imgView.setY(y-cam.getY()); l'équilibre du ressort n'est pas bon... à faire.
        imgView.setX(x-cam.getX());
        for (Enemi enemi:scene.getEnemisArray()) {
            if(enemi.getEnemiType()<3) {
                if(collision(scene.getHeros(),enemi)&&!isinvincible)
                {
                    stamina = 1;
                    isinvincible = true;
                    if (scene.getNumberOfLives()>0) {
                        scene.setNumberOfLives(scene.getNumberOfLives()-1);
                    }
                    attitude= 3;
                } else if(collision(scene.getHeros(),enemi)&&isinvincible){
                    enemi.setEnemiType(6);
                }
            }
        }
    }
}
