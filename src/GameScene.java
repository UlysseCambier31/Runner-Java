/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScene extends Scene {
    private Camera MainCamera;
    private staticThing statThing1, statThing2,startscreen;
    private int numberOfLives;
    private Hero heros;
    private List<Enemi> enemis = new ArrayList<Enemi>();
    private String syspath = System.getProperty("user.dir");
    private double x=500;
    private int subindex=0;
    private int minspawnduration=20;
    private ImageView lifesprite;
    private  ImageView speedsprite;
    private int score;
    private Text scoretext;

    public Camera getMainCamera() {
        return MainCamera;
    }

    public void setMainCamera(Camera mainCamera) {
        this.MainCamera = mainCamera;
    }

    public GameScene(Group g , Camera mainCamera) {

        super(g,600,400);
        this.MainCamera = mainCamera;
        this.score=0;
        Image spriteSheet = new Image(syspath+"\\img\\heros.png");
        ImageView sprite = new ImageView(spriteSheet);
        this.heros = new Hero(200,220,sprite,5);

        Image enemispritesheet = new Image(syspath+"\\img\\crab.png");
        ImageView enemisprite = new ImageView(enemispritesheet);
        this.enemis.add(new Enemi(200,500,enemisprite,0));

        Image lifespritesheet = new Image(syspath+"\\img\\life.png");
        this.lifesprite = new ImageView(lifespritesheet);
        this.lifesprite.setViewport(new Rectangle2D(0,0,60,60));
        this.lifesprite.setX(20);
        this.lifesprite.setY(20);

        Image speedspritesheet = new Image(syspath+"\\img\\heros.png");
        this.speedsprite = new ImageView(speedspritesheet);
        this.speedsprite.setViewport(new Rectangle2D(10, 160*2+15, 68, 100));
        this.speedsprite.setX(80);
        this.speedsprite.setY(0);

        this.scoretext = new Text (200, 50, "");
        this.scoretext.setFont(Font.loadFont("file:Fonts/NiseSegaSonic.ttf", 20));
        this.scoretext.setFill(Color.YELLOW);

        this.statThing1 = new staticThing(0,0,new ImageView(syspath+"\\img\\desert.png"));
        this.statThing2 = new staticThing(0,800,new ImageView(syspath+"\\img\\desert.png"));
        this.startscreen = new staticThing(20,0,new ImageView(syspath+"\\img\\startscreen.png"));
        g.getChildren().add(this.statThing1.getImgView());
        g.getChildren().add(this.statThing2.getImgView());
        g.getChildren().add(lifesprite);
        g.getChildren().add(speedsprite);
        g.getChildren().add(heros.getImgView());
        g.getChildren().add(this.startscreen.getImgView());
        g.getChildren().add(this.scoretext);
        this.numberOfLives = 3;


    }


    public GameScene(Parent parent, double v, double v1, Camera mainCamera) {
        super(parent, v, v1);
        this.MainCamera = mainCamera;
    }


    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public List<Enemi> getEnemisArray() {
        return enemis;
    }

    public GameScene(Parent parent, Paint paint, Camera mainCamera) {
        super(parent, paint);
        this.MainCamera = mainCamera;
    }

    public GameScene(Parent parent, double v, double v1, Paint paint, Camera mainCamera) {
        super(parent, v, v1, paint);
        this.MainCamera = mainCamera;
    }

    public GameScene(Parent parent, double v, double v1, boolean b, Camera mainCamera) {
        super(parent, v, v1, b);
        this.MainCamera = mainCamera;
    }
    public GameScene(Parent parent, double v, double v1, boolean b, SceneAntialiasing sceneAntialiasing, Camera mainCamera) {
        super(parent, v, v1, b, sceneAntialiasing);
        this.MainCamera = mainCamera;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Hero getHeros() {
        return heros;
    }
    public void HideStartScreen() {
        startscreen.getImgView().setX(600);
    }
    public  void update(long time,Camera cam){
        x = (x + cam.getVx()*16*(Math.pow(10,-3)*(2+cam.getAcceleration()*(1+(heros.getSuperspeedmultiplier()*2))))) % 800;
        statThing1.getImgView().setViewport(new Rectangle2D(x, 0, 800 - x, 400));
        statThing2.getImgView().setX(800 - x);
        if (numberOfLives>0){
            cam.setAcceleration(cam.getAcceleration()+0.003);
            score = score +1+2*heros.getSuperspeedmultiplier();
            scoretext.setText("Score : "+this.score);
        }
        if(numberOfLives==3){
            lifesprite.setViewport(new Rectangle2D(0,0,60,60));
        } else if (numberOfLives==2) {
            lifesprite.setViewport(new Rectangle2D(60,0,60,60));
        } else if (numberOfLives==1) {
            lifesprite.setViewport(new Rectangle2D(120,0,60,60));
        } else if (numberOfLives==0){
            lifesprite.setX(600);
            cam.setAcceleration(0);
        }
        speedsprite.setViewport(new Rectangle2D(10, 160*2+15, 68*((double)heros.getStamina()/1000), 100));
        if (heros.getStamina()==1000){
            speedsprite.setViewport(new Rectangle2D(5+(85 * 4), (160*2)+15, 85, 100));
        }
    }
    public void spawnGameOver(Group g) {
        Text gameover = new Text (100, 150, "GAME \n OVER");
        gameover.setFont(Font.loadFont("file:Fonts/NiseSegaSonic.ttf", 100));
        gameover.setFill(Color.RED);
        g.getChildren().add(gameover);

    }
    public void enemiSpwaner(long time, Group g,Camera cam) {
        subindex++;
        if (subindex==minspawnduration) {
            // On crée un enemie avec sa propre spritesheet
            int random = new Random().nextInt(5);
            Image enemispritesheet = new Image(syspath + "\\img\\crab.png");
            ImageView enemisprite = new ImageView(enemispritesheet);
            // On randomise l'apparition;
            if (random == 1) {
                double ytoset = 220;
                //On randomise le type d'enemi
                int enemiType = new Random().nextInt(5);
                // certains enemis comme type 2 et type 3 .resp. poissons et trucs dans les arbres nécessitent l'utilisation d'un obet de décors
                // pont ou arbre.
                if (enemiType == 2) {
                    ytoset = 400;// the fish as offset as it comes from the ground and it is definitely more complicated as fish does not live in the ground.....
                    //Ajout du pont
                    Image bridgeImg = new Image(syspath + "\\img\\bridge.png");
                    ImageView bridge = new ImageView(bridgeImg);
                    enemis.add(new Enemi(heros.getX() + 600, 0, bridge, 8));
                    g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                } else if (enemiType == 3) {
                    ytoset = 20;// the thing in a tree is more complicated as it need a tree
                    //Ajout de l'arbre
                    Image treesheet = new Image(syspath + "\\img\\crab.png");
                    ImageView tree = new ImageView(treesheet);
                    enemis.add(new Enemi(heros.getX() + 600, 20, tree, 5));
                    g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                }   else if (enemiType == 4) {
                    ytoset = new Random().nextInt(200);// the rings
                    ytoset += 20;
                    //Changement de la feuille de sprite pour celle des anneaux !
                     enemispritesheet = new Image(syspath + "\\img\\anneaux.png");
                     enemisprite = new ImageView(enemispritesheet);
                    enemiType = -1;
                }
                enemis.add(new Enemi(heros.getX() + 600, ytoset, enemisprite, enemiType));
                g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
            }
            subindex=0;
        }
    }
}
