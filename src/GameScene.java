import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

public class GameScene extends Scene {
    private Camera MainCamera;
    private staticThing statThing1, statThing2;

    public Camera getMainCamera() {
        return MainCamera;
    }

    public void setMainCamera(Camera mainCamera) {
        this.MainCamera = mainCamera;
    }

    public GameScene(Parent parent, Camera mainCamera) {
        super(parent);
        this.MainCamera = mainCamera;
        this.statThing1 = new staticThing(0,0,new ImageView("C:\\Users\\cambi\\IdeaProjects\\Runner JAVA CAMBIER\\img\\desert.png"));
        this.statThing2 = new staticThing(0,600,new ImageView("C:\\Users\\cambi\\IdeaProjects\\Runner JAVA CAMBIER\\img\\desert.png"));
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
