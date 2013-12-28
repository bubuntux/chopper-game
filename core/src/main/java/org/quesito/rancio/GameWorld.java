package org.quesito.rancio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class GameWorld implements Disposable {

    public final float _maxSpeed;
    private final Vector2 _gravity;
    private final World _world;

    private final OrthographicCamera _cam;
    private final Box2DDebugRenderer _debugRenderer;

    private Body _chopper;

    public GameWorld() {
        _maxSpeed = 3f;
        _gravity = new Vector2(0, -0.98f);
        _world = new World(_gravity, true);

        _cam = new OrthographicCamera(15, 15);
        _cam.position.set(7.5f, 7.5f, 0); //Half of viewports
        _debugRenderer = new Box2DDebugRenderer();

        addDummyBodies();
    }

    private void addDummyBodies() {
        ////////////////////////////////// Chopper
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(7.5f, 7.5f);


        // Create our body in the world using our body definition
        _chopper = _world.createBody(bodyDef);

        // Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.05f; // Make it bounce a little bit

        // Create our fixture and attach it to the body
        Fixture fixture = _chopper.createFixture(fixtureDef);

        circle.dispose();
        ////////////////////////////////// Chopper

        ////////////////////////////////// Ground
        // Create our body definition
        BodyDef groundBodyDef = new BodyDef();
        // Set its world position
        groundBodyDef.position.set(new Vector2(0.0f, 0.1f));

        // Create a body from the defintion and add it to the world
        Body groundBody = _world.createBody(groundBodyDef);

        // Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(_cam.viewportWidth, 0.1f);
        // Create a fixture from our polygon shape and add it to our ground body
        groundBody.createFixture(groundBox, 0.0f);
        // Clean up after ourselves
        groundBox.dispose();
        ////////////////////////////////// Ground
    }


    public void update(float delta) {
        Vector2 linearVelocity = _chopper.getLinearVelocity();
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) && linearVelocity.y < _maxSpeed) {
            _chopper.applyForceToCenter(0, 1.5f, true);
        }

        if (linearVelocity.y > _maxSpeed) {
            linearVelocity.y = _maxSpeed;
        }

        System.out.println(linearVelocity);

        _world.step(1 / 45f, 6, 2); //TODO ??
        _cam.position.x = _chopper.getPosition().x;
    }

    public void draw() {
        _cam.update();
        _debugRenderer.render(_world, _cam.combined);
    }

    @Override
    public void dispose() {
        _world.dispose();
    }
}