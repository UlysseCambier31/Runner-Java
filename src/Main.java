/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application{
    int step = 0; // définit l'état du jeu : lancé, en cours, arrété.
    int waittospawn = 0; // waittospawn& waittospawnmaw permettent de s'assurer que le joueur ne reçois pas d'énemi dès le lancement du jeu.
    int waittospawnmax = 200;
    int debugindex = 0;
    public void start(Stage primaryStage){
        primaryStage.setTitle("Java CAMBIER Ulysse - SONIC THE HEDGEHOG");
        Group root = new Group();
        Camera MainCamera = new Camera(10,10);
        GameScene scene = new GameScene(root,MainCamera);
        primaryStage.setScene(scene);
        primaryStage.show();
        // On créé le timer ici.
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                waittospawn++;
                scene.getHeros().update(time,MainCamera,scene);// Update du héros.
                MainCamera.update(time,scene.getHeros()); // Update de la caméra
                scene.update(time,MainCamera); // Update de la scene
                for (Enemi enemi : scene.getEnemisArray()) { // Update
                    enemi.update(time, MainCamera,scene);    // des
                }                                            // Enemis
                if (waittospawn>waittospawnmax&&step==3) {                // Appel
                    scene.enemiSpwaner(time, root, MainCamera);  // du Enemi
                }                                                // Spawner
                if(scene.getNumberOfLives()==0&&step==3){ //Check
                    scene.spawnGameOver(root);            //for
                    step = 4;                             //GAME OVER
                }
                if(scene.getEnemisArray().size()>10){
                    int i =0;
                    while(scene.getEnemisArray().size()>11) {
                        if(!(scene.getEnemisArray().get(i).EnemiType>8)){
                            scene.getEnemisArray().remove(i);
                        }
                        i++;
                    }// No more than 10 enemis handeled outside boss  for perf.
                }
            }
        };
        scene.setOnMouseClicked( (event)-> {
            if (step == 3) { // Si le jeu dans l'état lancé, alors le click correspond au jump
                scene.getHeros().jump();
            } else if (step == 0) { //Si le jeu est sur le menu d'acceuil.
                step = 1; // On passe step dans l'état jeu lancé.
                scene.HideStartScreen(); //On cache l'écran start.
            } else if (step == 1) { //Si le jeu est sur le menu d'acceuil.
                step = 2; // On passe step dans l'état jeu lancé.
                scene.HideInfo1();//On cache l'écran info1.
            } else if (step == 2) { //Si le jeu est sur le menu d'acceuil.
                step = 3; // On passe step dans l'état jeu lancé
                scene.HideInfo2();//On cache l'écran info2.
                timer.start(); //On démarre le jeu en démarrant le timer.
            }
        });
        scene.setOnKeyPressed((KeyEvent e) -> { // Si le joueur appuie sur Espace, on appel la fonction superspeed.
            if (e.getCode().equals(KeyCode.SPACE)){
                scene.getHeros().superspeed();
            }
        });
    }
    public static void main(String[] args) {
        launch(args);

    }
}
