package app.ypra.myapplication3;

import android.view.KeyEvent;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;

import app.ypra.base.SceneManager;


public class GameActivity extends BaseGameActivity {

    /*CAMERA*/
    private Camera camera;

    /*Resource Manager*/
    private ResourcesManager resourcesManager;

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0,0, 800, 480);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(800,480),this.camera);
        engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
        resourcesManager = ResourcesManager.getInstance();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    /* running splash screen*/
    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                /*
                 * load menu resource, create menu scene
                 * set menu scene mananger
                 * disposesSplash scene
                 */
                SceneManager.getInstance().createMenuScene();
            }
        }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions){
        return new LimitedFPSEngine(pEngineOptions, 60);
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }

    //BACK
    public boolean onKeyDown(int KeyCode, KeyEvent event){
        char keyCode = 0;
        if(keyCode == KeyEvent.KEYCODE_BACK){
           SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
        }
        return false;
    }
}
