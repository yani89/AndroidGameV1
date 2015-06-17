package app.ypra.base;

import android.app.Activity;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import app.ypra.myapplication3.ResourcesManager;
import app.ypra.base.SceneManager.SceneType;

/**
 * Created by root on 11/04/15.
 */
public abstract class BaseScene extends Scene {
    /*Variable*/

    protected Engine engine;
    protected Activity activity;
    protected ResourcesManager resourcesManager;
    protected VertexBufferObjectManager vertexBufferObjectManager;
    protected Camera camera;

    /*Constructor*/

    public BaseScene(){
        this.resourcesManager = ResourcesManager.getInstance();
        this.engine = resourcesManager.engine;
        this.activity = resourcesManager.activity;
        this.camera = resourcesManager.camera;
        this.vertexBufferObjectManager = resourcesManager.vertexBufferObjectManager;
        createScene();
    }

    /*abstraction*/
    public abstract void createScene();

    public abstract void onBackKeyPressed();

    public abstract SceneType getSceneType();

    public abstract void disposeScene();

}
