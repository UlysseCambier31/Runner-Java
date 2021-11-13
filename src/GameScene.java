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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScene extends Scene {
    private Camera MainCamera;
    private staticThing statThing1, statThing2;
    private int numberOfLives;
    private Hero heros;
    private List<Enemi> enemis = new ArrayList<Enemi>();
    private String syspath = System.getProperty("user.dir");
    private double x=500;
    private int subindex=0;
    private int minspawnduration=10;
    private int indexpois = 0;
    private int minspawnindexpois = 5; // Un poisson ne spawnera que tous les 5 enemis .
    public Camera getMainCamera() {
        return MainCamera;
    }

    public void setMainCamera(Camera mainCamera) {
        this.MainCamera = mainCamera;
    }

    public GameScene(Group g , Camera mainCamera) {

        super(g,600,400);
        this.MainCamera = mainCamera;

        Image spriteSheet = new Image(syspath+"\\img\\heros.png");
        ImageView sprite = new ImageView(spriteSheet);
        heros = new Hero(200,220,sprite,0);

        Image enemispritesheet = new Image(syspath+"\\img\\crab.png");
        ImageView enemisprite = new ImageView(enemispritesheet);
        enemis.add(new Enemi(200,500,enemisprite,0));

        Image lifespritesheet = new Image(syspath+"\\img\\life.png");
        ImageView lifesprite = new ImageView(lifespritesheet);
        lifesprite.setViewport(new Rectangle2D(0,0,60,60));
        lifesprite.setX(20);
        lifesprite.setY(20);

        this.statThing1 = new staticThing(0,0,new ImageView(syspath+"\\img\\desert.png"));
        this.statThing2 = new staticThing(0,800,new ImageView(syspath+"\\img\\desert.png"));
        g.getChildren().add(this.statThing1.getImgView());
        g.getChildren().add(this.statThing2.getImgView());
        g.getChildren().add(lifesprite);
        g.getChildren().add(heros.getImgView());
        this.numberOfLives = 3;


    }


    public GameScene(Parent parent, double v, double v1, Camera mainCamera) {
        super(parent, v, v1);
        this.MainCamera = mainCamera;
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
    public Hero getHeros() {
        return heros;
    }
    public  void update(long time,Camera cam){
        x = (x + cam.getVx()*16*(Math.pow(10,-3)*5)) % 800;
        statThing1.getImgView().setViewport(new Rectangle2D(x, 0, 800 - x, 400));
        statThing2.getImgView().setX(800 - x);
    }
    public void enemiSpwaner(long time, Group g,Camera cam) {
        subindex++;
        if (subindex==minspawnduration) {
            int random = new Random().nextInt(5);
            Image enemispritesheet = new Image(syspath + "\\img\\crab.png");
            ImageView enemisprite = new ImageView(enemispritesheet);
            if (random == 1) {
                indexpois++;
                double ytoset = 220;
                int enemiType = new Random().nextInt(4);
                if (enemiType == 2 && indexpois==5) {
                    ytoset = 400;// the fish as offset as it comes from the ground and it is definitely more complicated as fish does not live in the ground.....
                    Image bridgeImg = new Image(syspath + "\\img\\bridge.png");
                    ImageView bridge = new ImageView(bridgeImg);
                    enemis.add(new Enemi(heros.getX() + 600, 0, bridge, 8));
                    g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                } else if (enemiType == 3) {
                    ytoset = 20;// the thing in a tree is more complicated as it need a tree
                    Image treesheet = new Image(syspath + "\\img\\crab.png");
                    ImageView tree = new ImageView(treesheet);
                    enemis.add(new Enemi(heros.getX() + 600, 20, tree, 5));
                    g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                }
                if (enemiType==2 && indexpois!=5){
                    enemiType=0;
                }
                enemis.add(new Enemi(heros.getX() + 600, ytoset, enemisprite, enemiType));
                g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                if (indexpois==minspawnindexpois) indexpois=0;
            }
            subindex=0;
        }
    }
}
