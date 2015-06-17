package app.ypra.myapplication3;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import app.ypra.myapplication3.SceneManager.SceneType;

/**
 * Created by root on 11/04/15.
 */
public class SplashScene extends BaseScene {

    private Sprite splash;

    @Override
    public void createScene() {
        splash = new Sprite(0,0,resourcesManager.splash_region,vertexBufferObjectManager){
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera){
                super.preDraw(pGLState,pCamera);
                pGLState.enableDither();
            }
        };
        splash.setScale(1.5f);
        splash.setPosition(400,240);
        attachChild(splash);
    }

    @Override
    public void onBackKeyPressed() {
       // System.exit(0);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_SPLASH;
    }

    @Override
    public void disposeScene() {
        splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
    }
}
