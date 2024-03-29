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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScene extends Scene {
    private Camera MainCamera;
    private List<staticThing> statThing1 = new ArrayList<staticThing>();
    private List<staticThing> statThing2 = new ArrayList<staticThing>();
    private staticThing startscreen,info1,info2;
    private int numberOfLives;
    private Hero heros;
    private List<Enemi> enemis = new ArrayList<Enemi>();
    private String syspath = System.getProperty("user.dir"); // ceci permet de ne pas avoir de bug liés aux paths.
    private double x=500,x2=500,x3=500,x4=500,x5=500,x6=500;
    private int subindex=0;
    private int minspawnduration=20;
    private ImageView lifesprite;
    private  ImageView speedsprite;
    private int score;
    private Text scoretext;
    private int boss;
    private  ImageView bosssprite;
    private int bosswave;
    private int waittospawn = 0; // waittospawn& waittospawnmaw permettent de s'assurer que le joueur ne reçois pas d'énemi dès le lancement du jeu.
    private int waittospawnmax = 200;
    private MediaPlayer sonicplayer;
    private MediaPlayer doomplayer;

    public GameScene(Group g , Camera mainCamera) {

        super(g,600,400);

        this.MainCamera = mainCamera;

        this.score=0;
        this.boss=0;
        this.bosswave=0;

        //musics
        Media sonicsound = new Media(new File(syspath+"\\sound\\sonic-the-hedgehog-ost-green-hill-zone.mp3").toURI().toString());
        sonicplayer = new MediaPlayer(sonicsound);
        sonicplayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                sonicplayer.seek(Duration.ZERO);
                sonicplayer.play();
            }
        });
        sonicplayer.play();

        Media bosssound = new Media(new File(syspath+"\\sound\\sonic-1-music-bosses.mp3").toURI().toString());
        doomplayer = new MediaPlayer(bosssound);
        doomplayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                doomplayer.seek(Duration.ZERO);
                doomplayer.play();
            }
        });

        //Hero init
        Image spriteSheet = new Image(syspath+"\\img\\heros.png");
        ImageView sprite = new ImageView(spriteSheet);
        this.heros = new Hero(200,220,sprite,5);

        //Enemi init
        Image enemispritesheet = new Image(syspath+"\\img\\crab.png");
        ImageView enemisprite = new ImageView(enemispritesheet);
        this.enemis.add(new Enemi(200,500,enemisprite,0));

        // Life indicator init
        Image lifespritesheet = new Image(syspath+"\\img\\life.png");
        this.lifesprite = new ImageView(lifespritesheet);
        this.lifesprite.setViewport(new Rectangle2D(0,0,60,60));
        this.lifesprite.setX(20);
        this.lifesprite.setY(20);

        //SuperSpeed indicator init
        Image speedspritesheet = new Image(syspath+"\\img\\heros.png");
        this.speedsprite = new ImageView(speedspritesheet);
        this.speedsprite.setViewport(new Rectangle2D(10, 160*2+15, 68, 100));
        this.speedsprite.setX(80);
        this.speedsprite.setY(0);

        //Score Text init
        this.scoretext = new Text (200, 50, "");
        this.scoretext.setFont(Font.loadFont("file:Fonts/NiseSegaSonic.ttf", 20));
        this.scoretext.setFill(Color.YELLOW);

        //StaticThings init
        this.statThing1.add(new staticThing(0,0,new ImageView(syspath+"\\img\\desert.png")));
        this.statThing2.add(new staticThing(0,800,new ImageView(syspath+"\\img\\desert.png")));
        this.statThing1.add(new staticThing(0,0,new ImageView(syspath+"\\img\\desert2.png")));
        this.statThing2.add(new staticThing(0,800,new ImageView(syspath+"\\img\\desert2.png")));
        this.statThing1.add(new staticThing(0,0,new ImageView(syspath+"\\img\\desert3.png")));
        this.statThing2.add(new staticThing(0,800,new ImageView(syspath+"\\img\\desert3.png")));
        this.statThing1.add(new staticThing(0,0,new ImageView(syspath+"\\img\\desert4.png")));
        this.statThing2.add(new staticThing(0,800,new ImageView(syspath+"\\img\\desert4.png")));
        this.statThing1.add(new staticThing(0,0,new ImageView(syspath+"\\img\\desert5.png")));
        this.statThing2.add(new staticThing(0,800,new ImageView(syspath+"\\img\\desert5.png")));
        this.statThing1.add(new staticThing(0,0,new ImageView(syspath+"\\img\\desert6.png")));
        this.statThing2.add(new staticThing(0,800,new ImageView(syspath+"\\img\\desert6.png")));
        this.startscreen = new staticThing(20,0,new ImageView(syspath+"\\img\\startscreen.png"));
        this.info1 = new staticThing(20,0,new ImageView(syspath+"\\img\\infos1.png"));
        this.info2 = new staticThing(20,0,new ImageView(syspath+"\\img\\infos2.png"));


        //Write on screen
        g.getChildren().add(this.statThing1.get(2).getImgView());
        g.getChildren().add(this.statThing2.get(2).getImgView());
        g.getChildren().add(this.statThing1.get(3).getImgView());
        g.getChildren().add(this.statThing2.get(3).getImgView());
        g.getChildren().add(this.statThing1.get(1).getImgView());
        g.getChildren().add(this.statThing2.get(1).getImgView());
        g.getChildren().add(this.statThing1.get(5).getImgView());
        g.getChildren().add(this.statThing2.get(5).getImgView());
        g.getChildren().add(this.statThing1.get(4).getImgView());
        g.getChildren().add(this.statThing2.get(4).getImgView());
        g.getChildren().add(this.statThing1.get(0).getImgView());
        g.getChildren().add(this.statThing2.get(0).getImgView());
        g.getChildren().add(lifesprite);
        g.getChildren().add(speedsprite);
        g.getChildren().add(heros.getImgView());
        g.getChildren().add(this.info2.getImgView());
        g.getChildren().add(this.info1.getImgView());
        g.getChildren().add(this.startscreen.getImgView());
        g.getChildren().add(this.scoretext);
        //Set initial numberOfLives
        this.numberOfLives = 3;


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

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}

    public Hero getHeros() {
        return heros;
    }

    public int isBoss() {
        return boss;
    }

    public void setWaittospawn(int waittospawn) {
        this.waittospawn = waittospawn;
    }

    public int getWaittospawn() {
        return waittospawn;
    }

    public void HideStartScreen() {
        startscreen.getImgView().setX(600);
    }
    public void HideInfo1() {
        info1.getImgView().setX(600);
    }
    public void HideInfo2() {
        info2.getImgView().setX(600);
    }

    public  void update(long time,Camera cam){
        //On update la position du fond en prenant compte de l'acc accumulé et du l'éventuelle superspeed.
        // Effet parallax.
        x = (x + cam.getVx()*16*(Math.pow(10,-3)*(2+cam.getAcceleration()*(1+(heros.getSuperspeedmultiplier()*2))))) % 800;
        x2 = (x2 + cam.getVx()*16*(Math.pow(10,-3)*((2+cam.getAcceleration()*(1+(heros.getSuperspeedmultiplier()*2)))/2))) % 800;
        x3 = (x3 + cam.getVx()*16*(Math.pow(10,-3)*((2+cam.getAcceleration()*(1+(heros.getSuperspeedmultiplier()*2)))/3))) % 800;
        x4 = (x4 + cam.getVx()*16*(Math.pow(10,-3)*((2+cam.getAcceleration()*(1+(heros.getSuperspeedmultiplier()*2)))/100))) % 800;
        x5 = (x5 + cam.getVx()*16*(Math.pow(10,-3)*((2+cam.getAcceleration()*(1+(heros.getSuperspeedmultiplier()*2)))/50))) % 800;
        x6 = (x6 + cam.getVx()*16*(Math.pow(10,-3)*((2+cam.getAcceleration()*(1+(heros.getSuperspeedmultiplier()*2)))/200))) % 800;
        statThing1.get(0).getImgView().setViewport(new Rectangle2D(x, 0, 800 - x, 400));
        statThing2.get(0).getImgView().setX(800 - x);
        statThing1.get(4).getImgView().setViewport(new Rectangle2D(x2, 0, 800 - x2, 400));
        statThing2.get(4).getImgView().setX(800 - x2);
        statThing1.get(5).getImgView().setViewport(new Rectangle2D(x3, 0, 800 - x3, 400));
        statThing2.get(5).getImgView().setX(800 - x3);
        statThing1.get(1).getImgView().setViewport(new Rectangle2D(x4, 0, 800 - x4, 400));
        statThing2.get(1).getImgView().setX(800 - x4);
        statThing1.get(3).getImgView().setViewport(new Rectangle2D(x5, 0, 800 - x5, 400));
        statThing2.get(3).getImgView().setX(800 - x5);
        statThing1.get(2).getImgView().setViewport(new Rectangle2D(x6, 0, 800 - x6, 400));
        statThing2.get(2).getImgView().setX(800 - x6);

        // Si le jeu est toujours en cours
        if (numberOfLives>0){
            if(boss!=2) {
                cam.setAcceleration(cam.getAcceleration() + 0.003); // On accumule de l'acceleration sauf en boss.
            }
            score = score +(int)(cam.getAcceleration()/2)+2*heros.getSuperspeedmultiplier(); // On accumule du score, et on gagne un bonus si on est en superspeed.
            scoretext.setText("Score : "+this.score); // On update le score affiché.
            if (score>(Math.pow(10,4+bosswave))&&boss==0){  // Apparition du boss toute les puissance de 10 à partir de 10000.
                sonicplayer.stop();//changement de musique
                doomplayer.play();
                boss = 1;
            } if (score>(Math.pow(10,4+bosswave)+10000)&&boss==2){ // Disparition du boss 10000 de score plus loin.
                bosswave++;
                boss = 0;
                doomplayer.stop();//changement de musique
                sonicplayer.play();
            }
        }

        // On update l'affichage du nombre de vie.
        if(numberOfLives==3){
            lifesprite.setViewport(new Rectangle2D(0,0,60,60));
        } else if (numberOfLives==2) {
            lifesprite.setViewport(new Rectangle2D(60,0,60,60));
        } else if (numberOfLives==1) {
            lifesprite.setViewport(new Rectangle2D(120,0,60,60));
        } else if (numberOfLives==0){
            lifesprite.setX(600); // on fait disparaitre le sprite de l'écran.
            cam.setAcceleration(0);// On stop net toute vitesse si le personnage est mort.
        }

        //On update l'affichage de la stamina pour la superspeed
        speedsprite.setViewport(new Rectangle2D(10, 160*2+15, 68*((double)heros.getStamina()/1000), 100));//Barre de chargement
        if (heros.getStamina()==1000){
            speedsprite.setViewport(new Rectangle2D(5+(85 * 4), (160*2)+15, 85, 100));//Stamina full -> changement d'image.
        }

    }

    public  int Lateupdate(long time,Camera cam,int step,Group g){

        if(enemis.size()>10){
            int i =0;
            while(enemis.size()>11) {
                if(!(enemis.get(i).EnemiType>8)){
                    enemis.remove(i);
                }
                i++;
            }// No more than 10 enemis handeled outside boss  for perf.
        }

        for (Enemi enemi : enemis) {                      // Update
            enemi.update(time, MainCamera,this);    // des
        }                                                // Enemis
        if (waittospawn>waittospawnmax&&step==3) { // Appel
            enemiSpwaner(time, g, MainCamera);     // du Enemi
        }                                          // Spawner
        if(getNumberOfLives()==0&&step==3){ //Check
            spawnGameOver(g);               //for
            return 4;                       //GAME OVER
        }
        return step;
    }

    public void spawnGameOver(Group g) {
        // On crée le texte GAMEOVER
        Text gameover = new Text (100, 150, "GAME \n OVER");
        gameover.setFont(Font.loadFont("file:Fonts/NiseSegaSonic.ttf", 100));
        gameover.setFill(Color.RED);
        //On affiche le texte
        g.getChildren().add(gameover);

    }
    public void enemiSpwaner(long time, Group g,Camera cam) {
        subindex++; // On ralenti le spawner par comptage...
        if (subindex==minspawnduration) {
            if (boss==0) {
                // On crée un enemie avec sa propre spritesheet
                int random = new Random().nextInt(5);
                Image enemispritesheet = new Image(syspath + "\\img\\crab.png");
                ImageView enemisprite = new ImageView(enemispritesheet);
                // On randomise l'apparition;
                if (random == 1) {
                    double ytoset = 220;
                    //On randomise le type d'enemi
                    int enemiType = new Random().nextInt(5); // Seul 5 "enemis" sont interactifs enemiType={0:crab,1:spikes,2:fish,3:ThingInATree,-1:Rings}
                    // certains enemis comme type 2 et type 3 .resp. poissons et trucs dans les arbres nécessitent l'utilisation d'un obet de décors
                    // pont ou arbre.
                    if (enemiType == 2) {
                        ytoset = 400;// the fish as offset as it comes from the ground and it is definitely more complicated as fish does not live in the ground.....
                        //Ajout du pont comme un enemi inerte
                        Image bridgeImg = new Image(syspath + "\\img\\bridge.png");
                        ImageView bridge = new ImageView(bridgeImg);
                        enemis.add(new Enemi(heros.getX() + 600, 0, bridge, 8));
                        g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                    } else if (enemiType == 3) {
                        ytoset = 20;// the thing in a tree is more complicated as it need a tree, it as an offset to be high up in the tree.
                        //Ajout de l'arbre comme un enemi inerte
                        Image treesheet = new Image(syspath + "\\img\\crab.png");
                        ImageView tree = new ImageView(treesheet);
                        enemis.add(new Enemi(heros.getX() + 600, 20, tree, 5));
                        g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                    } else if (enemiType == 4) { // Les anneaux ne sont pas enemyType 4, mais c'était plus simple de faire comme ça. On rétabli cette valeur ensuite.
                        ytoset = new Random().nextInt(200);// the rings, not an enemi but... more simple to use the Enemi class. It appear randomly in y on the screen.
                        ytoset += 20;
                        //Changement de la feuille de sprite pour celle des anneaux !En effet,les anneaux ne sont pas sur la feuille crab.png!
                        enemispritesheet = new Image(syspath + "\\img\\anneaux.png");
                        enemisprite = new ImageView(enemispritesheet);
                        enemiType = -1;// On rétabli l'enemiType des anneaux.
                    }
                    //Ajout de l'enemi avec les nouvelles valeurs correspondant à son enemiType.
                    enemis.add(new Enemi(heros.getX() + 600, ytoset, enemisprite, enemiType));
                    g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                }
            } else if (boss==1) { // Si on est entrain d'entrée en phase de boss
                boss=2;
                Image bossspritesheet = new Image(syspath+"\\img\\boss.png"); // on charge le boss
                bosssprite = new ImageView(bossspritesheet);
                enemis.add(new Enemi(600, 20, bosssprite, 9));
                g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
            } else if (boss==2) { // Phase de tire dans la phase de boss.
                int rd = new Random().nextInt(10);
                if (rd <5  && Math.abs(bosssprite.getY()-(heros.getImgView().getY()+10))<10) {
                    Image bulletspritesheet = new Image(syspath + "\\img\\fireball.png");
                    ImageView bulletsprite = new ImageView(bulletspritesheet);
                    enemis.add(new Enemi(bosssprite.getX(), bosssprite.getY()+25, bulletsprite, -2));
                    g.getChildren().add(enemis.get(enemis.size() - 1).getImgView());
                }
            }
            subindex=0; // On reset le temps d'attente après le spawn d'un enemi.
        }
    }
}
