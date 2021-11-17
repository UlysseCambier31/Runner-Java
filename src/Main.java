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
                scene.setWaittospawn(scene.getWaittospawn()+1);
                scene.getHeros().update(time,MainCamera,scene);// Update du héros.
                MainCamera.update(time,scene.getHeros()); // Update de la caméra
                scene.update(time,MainCamera);//update de la scene
                step = scene.Lateupdate(time,MainCamera,step,root); // Update de la physique de la scene
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
