package app.ypra.Menu;


import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import app.ypra.base.BaseScene;
import app.ypra.base.SceneManager;
import app.ypra.base.SceneManager.SceneType;

/**
 * Created by root on 11/04/15.
 */
public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener {

    private MenuScene menuChildScene;
    private final int MENU_PLAY = 0;
    private final int MENU_OPTIONS = 1;

    @Override
    public void createScene() {
        createBackground();
        createMenuChildScene();
    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene() {

    }

    private void createBackground(){
        attachChild(new Sprite(400,240, resourcesManager.menu_background_region,vertexBufferObjectManager){
            protected void preDraw(GLState pGLState, Camera pCamera){
                super.preDraw(pGLState,pCamera);
                pGLState.enableDither();
            }
        });
    }

    /* menu class*/
    private void createMenuChildScene(){
        menuChildScene = new MenuScene(camera);
        menuChildScene.setPosition(400,240);

        final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.play_region,vertexBufferObjectManager),1.2f,1);
        final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourcesManager.options_region,vertexBufferObjectManager),1.2f,1);

        menuChildScene.addMenuItem(playMenuItem);
        menuChildScene.addMenuItem(optionsMenuItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        playMenuItem.setPosition(playMenuItem.getX() - 380,playMenuItem.getY() - 250);
        optionsMenuItem.setPosition(optionsMenuItem.getX() - 380, optionsMenuItem.getY()-270);

        menuChildScene.setOnMenuItemClickListener(this);
        setChildScene(menuChildScene);
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()){
            case MENU_PLAY:
                //load game scene
                SceneManager.getInstance().loadGameScene(engine);
                return true;
            case MENU_OPTIONS:
                return true;
            default:
                return false;
        }
    }
}
