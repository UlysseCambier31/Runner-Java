// add any usefull package line

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
        Camera MainCamera = new Camera(0,0);
        GameScene scene = new GameScene(root,600,400,MainCamera);
        primaryStage.setScene(scene);
        primaryStage.show();

        Image spriteSheet = new Image("C:\\Users\\cambi\\IdeaProjects\\Runner JAVA CAMBIER\\img\\heros.png");
        ImageView sprite = new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(20,0,65,100));
        sprite.setX(200);
        sprite.setY(300);

        root.getChildren().add(sprite);
    }
    public static void main(String[] args) {
        launch(args);

    }
}
