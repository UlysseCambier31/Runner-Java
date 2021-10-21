import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public class GameScene extends Scene {
    private Camera MainCamera;

    public Camera getMainCamera() {
        return MainCamera;
    }

    public void setMainCamera(Camera mainCamera) {
        MainCamera = mainCamera;
    }

    public GameScene(Parent parent, Camera mainCamera) {
        super(parent);
        MainCamera = mainCamera;
    }

    public GameScene(Parent parent, double v, double v1, Camera mainCamera) {
        super(parent, v, v1);
        MainCamera = mainCamera;
    }

    public GameScene(Parent parent, Paint paint, Camera mainCamera) {
        super(parent, paint);
        MainCamera = mainCamera;
    }

    public GameScene(Parent parent, double v, double v1, Paint paint, Camera mainCamera) {
        super(parent, v, v1, paint);
        MainCamera = mainCamera;
    }

    public GameScene(Parent parent, double v, double v1, boolean b, Camera mainCamera) {
        super(parent, v, v1, b);
        MainCamera = mainCamera;
    }

    public GameScene(Parent parent, double v, double v1, boolean b, SceneAntialiasing sceneAntialiasing, Camera mainCamera) {
        super(parent, v, v1, b, sceneAntialiasing);
        MainCamera = mainCamera;
    }
}
