package org.quesito.rancio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

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
        bodyDef.fixedRotation = true;
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(1f, 7.5f);

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
        // Set its world positionA
        groundBodyDef.position.set(0.0f, 0.1f);
        groundBodyDef.type = BodyDef.BodyType.StaticBody;

        // Create a body from the defintion and add it to the world
        Body ground = _world.createBody(groundBodyDef);

        // Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(_cam.viewportWidth, 0.1f);
        // Create a fixture from our polygon shape and add it to our ground body
        ground.createFixture(groundBox, 0.0f);
        // Clean up after ourselves
        groundBox.dispose();
        ////////////////////////////////// Ground

        ////////////////////////////////// Random obstacles
        Random rnd = new Random();
        BodyDef def = new BodyDef();
        def.fixedRotation = true;
        def.gravityScale = 0;
        CircleShape shape = new CircleShape();
        shape.setRadius(0.25f);
        def.type = BodyDef.BodyType.DynamicBody;
        for (int i = 0; i < 5; i++) {
            def.position.set(rnd.nextFloat() * 20, rnd.nextFloat() * 10);
            Body obstacle = _world.createBody(def);
            obstacle.applyForceToCenter(-15.5f, 0, true);
            Fixture obstacleFixture = obstacle.createFixture(shape, 0.0f);
            obstacleFixture.setDensity(0.05f);
            obstacleFixture.setFriction(0.05f);
            obstacleFixture.setRestitution(0.5f);
        }
        shape.dispose();
        ////////////////////////////////// Random obstacles
    }

    public void update(float delta) {
        Vector2 linearVelocity = _chopper.getLinearVelocity();
        float xForce = 0.555f;
        float yForce = 0.0f;
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) && linearVelocity.y < _maxSpeed && !Gdx.input.isKeyPressed(Input.Keys.R)) {
            yForce = 1.5f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            _chopper.setTransform(1, 5, 0);
            _chopper.setLinearVelocity(0, 0);
        }
        _chopper.applyForceToCenter(xForce, yForce, true);

        if (linearVelocity.y > _maxSpeed) {
            linearVelocity.y = _maxSpeed;
        }
        if (linearVelocity.x > _maxSpeed) {
            linearVelocity.x = _maxSpeed;
        }

        _world.step(1 / 45f, 6, 2); //TODO ??
        _cam.position.x = _chopper.getPosition().x + 6.5f;   /// 7.5 - 1  (initial positions)
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