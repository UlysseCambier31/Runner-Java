/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing {
    public int indexsuperspeedstart;// Différentes variables en rapports avec des temps.
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
        if (attitude==0) { // Si sonic ne fait que courir.
            attitude = 1; // On change d'animation.
        }
    }

    public int getSuperspeedmultiplier() {
        return superspeedmultiplier;
    }

    public void superspeed() {
        if (attitude==0&&stamina==1000){ // Si sonic ne fait que courrir et a rempli sa barre de stamina.
            superspeedstate = 1; // On déclenche la superspeed.
            stamina = 1; // On reset la stamina.
            isinvincible=true; // Sonic est invincible en superspeed.
        }
    }

    public int getStamina() {
        return stamina;
    }

    static  boolean collision(Hero hero, Enemi enemi) {

        //Une fonction qui permet de trouver une collision entre deux objets.
        double alpha = 20;//Alpha permet de réduire la taille de la hitbox qui est trop grande...

        if(enemi.getEnemiType()==-2) alpha=0; // des petits ajustements liés au boss

        double rectaleft = hero.getX()+alpha;
        double rectaright = hero.getX()+hero.getImgView().getViewport().getWidth()-alpha;
        double rectatop = hero.getY()+hero.getImgView().getViewport().getHeight()-alpha;
        double rectabottom = hero.getY()+alpha;

        double rectbleft = enemi.getX()+alpha;
        double rectbright = enemi.getX()+enemi.getImgView().getViewport().getWidth()-alpha;
        double rectbtop= enemi.getY()+enemi.getImgView().getViewport().getHeight()-alpha;
        double rectbbottom = enemi.getY()+alpha;

        if(enemi.getEnemiType()==-2){ // des petits ajustements liés au boss
            rectaleft =200-150;
            rectaright=285-150;
        }

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

        //Update des animations en fonction de l'état attitude du héros.
        if (attitude==0) { // course normale.
            imgView.setViewport(new Rectangle2D(85 * (index % 6), 0, 85, 100));
            if (stamina<1000) {
                stamina++; // Le héros gagne de la stamina superspeed au cours du temps.
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
                imgView.setViewport(new Rectangle2D(85*2, 160+(100*(index%2)), 85, 90));
            } else {
                attitude=4;
            }
        } else if (attitude==4){ //touché saut bas
            if (y < 219) {
                y +=5;
                imgView.setViewport(new Rectangle2D(85*2, 160+(100*(index%2)), 85, 90));
            } else {
                attitude = 0;
                isinvincible= false;
            }
        } else if (attitude==5){ //superspeed début & fin
            imgView.setViewport(new Rectangle2D(85 * (index % 4), 160*2, 85, 100));
        } else if (attitude==6){ //superspeed
            imgView.setViewport(new Rectangle2D((85 * 4)+(85*(index%2)), 160*2, 85, 100));
        }else if (attitude==7){ //Mort saut
            if (y > 10) {
                y -=5 ;
                imgView.setViewport(new Rectangle2D(85*2, 160+(100*(index%2)), 85, 90));
            } else {
                attitude=8;
            }
        } else if (attitude==8){ //Mort saut bas
            if (y < 219) {
                y +=5;
                imgView.setViewport(new Rectangle2D(85*2, 160+(100*(index%2)), 85, 90));
            } else {
                attitude = 9;
            }
        }else if (attitude==9){ //Mort Animée
            imgView.setViewport(new Rectangle2D(85*2+(85*((index%3))), 160, 85, 100));
            if (index==3) attitude=10;
        } else if (attitude==10){ //Mort
            imgView.setViewport(new Rectangle2D(85 * 5, 160, 85, 100));
        }

        //Gestion du superspeed
        if(superspeedstate==1) { // début superspeed.
            attitude = 5;// On entre dans l'animation début superspeed ou sonic roule en boule mais est visible.
            superspeedmultiplier = 1; // On gagne de la vistesse.
            indexsuperspeedstart ++; // On fait avancer le temps dans cet état
            if (indexsuperspeedstart==indexsuperspeedstartmax) { //Lorsque sonic a bien roulé en boule, on passe en superspeed complète.
                superspeedstate=2; // On change d'état
                indexsuperspeedstart=0;
            }
        } else if (superspeedstate==2){ // superspeed
            attitude = 6; // On entre dans l'animation super speed ou sonic est en boule et n'est plus visible.
            superspeedmultiplier = 2; // On gagne en vitesse encore plus.
            indexsuperspeed++; // On fait avancer le temps dans cet état.
            if(indexsuperspeed==indexsuperspeedmax) { // Lorsque sonic a fini son animation,
                superspeedstate=3; // On va vers l'animation de fin superspeed.
                indexsuperspeed = 0;
            }
        } else if (superspeedstate ==3){// fin superspeed
            attitude=5; // on retourne à l'animation début superspeed qui ser aussi d'animation de fin.
            superspeedmultiplier = 1; // On baisse la vitesse.
            indexsuperspeedstart++; // on fait avancer le temps.
            if(indexsuperspeedstart==indexsuperspeedstartmax){
                superspeedstate=0; // on arrête la superspeed.
                indexsuperspeedstart=0;
                attitude = 0; // On retourne à la course à pied.
                superspeedmultiplier = 0; // On stop l'acceleration bonus.
                isinvincible=false; // Sonic n'est plus invincible.
            }
        }

        if (scene.getNumberOfLives()>0) { // Si sonic n'est pas mort, il avance...
            x++;
        }
        imgView.setY(y); //imgView.setY(y-cam.getY()); l'équilibre du ressort n'est pas bon... à faire.
        imgView.setX(x-cam.getX());

        for (Enemi enemi:scene.getEnemisArray()) { // Ici on check les collision est on prends les décision en fonction.
            if(enemi.getEnemiType()<4) { // Si l'enemi n'est pas un enemi inerte (décors : arbre, explosion, pont,...)
                if(collision(scene.getHeros(),enemi)&&!isinvincible) // Si il y a collision et que sonic n'est pas invincible.
                {
                    if (enemi.getEnemiType()==-1){ // dans le cas des anneaux.
                        if (stamina<800) stamina+=200; // sonic gagne un bonus de stamina !
                        if (stamina>=800 && stamina!=1000) stamina = 999;
                        if (scene.getNumberOfLives() < 3) {
                            scene.setNumberOfLives(scene.getNumberOfLives() +1); // Sonic regagne 1pv.
                        }
                        scene.setScore(scene.getScore()+1000); // Sonic gagne un bonus de score.
                        enemi.setEnemiType(6); // L'anneau disparait
                    }
                    else { // dans les autres cas.
                        enemi.setEnemiType(6); //L'enemi explose.
                        stamina = 1; // sonic perd toute sa stamina.
                        isinvincible = true; // sonic devient invicible
                        cam.setAcceleration(5); // l'acceleration accumulée est perdue.
                        if (scene.getNumberOfLives() > 0) {
                            scene.setNumberOfLives(scene.getNumberOfLives() - 1); // sonic perd une vie.
                        }
                        if (scene.getNumberOfLives() == 0) {
                            attitude = 7;// si sonic n'a plus de vie, il meurt.
                        } else {
                            attitude = 3;// si sonic a encore des vies, il fait son animation touché;
                        }
                    }
                } else if(collision(scene.getHeros(),enemi)&&isinvincible){
                    enemi.setEnemiType(6);// Si sonic est invincible, les enemis explosent sur son passage.
                }
            }
        }
    }
}
