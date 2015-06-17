package app.ypra.myapplication3;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by root on 12/04/15.
 */
public abstract class Player extends AnimatedSprite{

    private Body body;

    public Player(float pX, float pY, VertexBufferObjectManager pVertexBufferObjectManager, Camera camera, PhysicsWorld physicsWorld) {
        super(pX, pY, ResourcesManager.getInstance().player_region, pVertexBufferObjectManager);
        createPhysics(camera,physicsWorld);
        camera.setChaseEntity(this);
    }

    public abstract void onDie();

    private boolean canRun = false;

    private void createPhysics(final Camera camera, PhysicsWorld physicsWorld){
        body = PhysicsFactory.createBoxBody(physicsWorld,this, BodyDef.BodyType.DynamicBody,PhysicsFactory.createFixtureDef(0,0,0));
        body.setUserData("player");
        body.setFixedRotation(true);

        physicsWorld.registerPhysicsConnector(new PhysicsConnector(this,body,true,false){
            @Override
            public void onUpdate(float pSecondElapsed){
                super.onUpdate(pSecondElapsed);
                camera.onUpdate(0.1f);

                if(getY() < 0){
                    onDie();
                }

                if(canRun){
                    body.setLinearVelocity(new Vector2(5, body.getLinearVelocity().y));
                }
            }
        });
    }

    public void setRunning(){
        canRun = true;

        final long[] PLAYER_ANIMATE = new long[] {100,100,100};

        animate(PLAYER_ANIMATE,0,2,true);
    }

    public void jump(){
        body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 12));
    }


}
