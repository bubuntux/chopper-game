package org.quesito.rancio;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class GameWorld implements Disposable {

    private final Vector2 _gravity;
    private final World _world;

    private final OrthographicCamera _cam;
    private final Box2DDebugRenderer _debugRenderer;

    public GameWorld() {
        _gravity = new Vector2(0, -0.98f);
        _world = new World(_gravity, true);

        _cam = new OrthographicCamera(10, 15);
        _debugRenderer = new Box2DDebugRenderer();

        addDummyBodies();
    }

    private void addDummyBodies() {
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(0, 0); //TODO Center ???


        // Create our body in the world using our body definition
        Body body = _world.createBody(bodyDef);

        // Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();
    }


    public void update(float delta) {
        _world.step(1 / 45f, 6, 2); //TODO use delta??
    }

    public void draw() {
        _debugRenderer.render(_world, _cam.combined);
    }

    @Override
    public void dispose() {
        _world.dispose();
    }
}