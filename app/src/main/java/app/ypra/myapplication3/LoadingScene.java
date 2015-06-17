package app.ypra.myapplication3;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;


/**
 * Created by root on 11/04/15.
 */
public class LoadingScene extends BaseScene{

    @Override
    public void createScene() {
        setBackground(new Background(Color.WHITE));
        attachChild(new Text(400,240,resourcesManager.font,"Loading...",vertexBufferObjectManager));
    }

    @Override
    public void onBackKeyPressed() {
        return;
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_LOADING;
    }

    @Override
    public void disposeScene() {

    }
}
