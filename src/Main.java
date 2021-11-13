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
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("Demo");
        Group root = new Group();
        Camera MainCamera = new Camera(10,10);
        GameScene scene = new GameScene(root,MainCamera);
        primaryStage.setScene(scene);
        primaryStage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                scene.getHeros().update(time,MainCamera);
                MainCamera.update(time,scene.getHeros());
                scene.update(time,MainCamera);
                for (Enemi enemi : scene.getEnemisArray()) {
                    enemi.update(time, MainCamera);
                }
                scene.enemiSpwaner(time,root,MainCamera);
            }
        };
        timer.start();
        scene.setOnMouseClicked( (event)->{
            System.out.println("Jump");
            scene.getHeros().jump();
        });
    }
    public static void main(String[] args) {
        launch(args);

    }
}
