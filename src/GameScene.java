import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

public class GameScene extends Scene {
    private Camera MainCamera;
    private staticThing statThing1, statThing2;
    private int numberOfLives;

    public Camera getMainCamera() {
        return MainCamera;
    }

    public void setMainCamera(Camera mainCamera) {
        this.MainCamera = mainCamera;
    }

    public GameScene(Group g , Camera mainCamera) {
        super(g,600,400);
        this.MainCamera = mainCamera;

        Image spriteSheet = new Image("C:\\Users\\cambi\\IdeaProjects\\Runner_Java_Cambier\\img\\heros.png");
        ImageView sprite = new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(20,0,65,100));
        sprite.setX(200);
        sprite.setY(300);


        this.statThing1 = new staticThing(0,0,new ImageView("C:\\Users\\cambi\\IdeaProjects\\Runner_Java_Cambier\\img\\desert.png"));
        this.statThing2 = new staticThing(0,800,new ImageView("C:\\Users\\cambi\\IdeaProjects\\Runner_Java_Cambier\\img\\desert.png"));
        g.getChildren().add(this.statThing1.getImgView());
        g.getChildren().add(this.statThing2.getImgView());
        g.getChildren().add(sprite);
        this.numberOfLives = 3;
    }

    public GameScene(Parent parent, double v, double v1, Camera mainCamera) {
        super(parent, v, v1);
        this.MainCamera = mainCamera;
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
}
