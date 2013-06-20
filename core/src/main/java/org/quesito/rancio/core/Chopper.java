package org.quesito.rancio.core;

import playn.core.Image;
import playn.core.ImageLayer;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

public class Chopper {

	private static final float GRAVITY = 0.098f;
	private static final float MAX_GRAVITY_ACCELERATION = 10f;
	private final float _minY;
	private final float _maxY;
	private final ImageLayer _layer;
	private float _speedUp = 0.15f;
	private float _maxSpeedUp = -12f;
	private float _speedForward = 0.15f;
	private float _maxSpeedForward = -12f;
	private boolean _falling;
	private float _weight = 0.01f;
	private float _acceleration;

	public Chopper() {
		Image image = assets().getImageSync("images/aircraft.png");
		_layer = graphics().createImageLayer(image);
		_falling = true;
		_minY = 0;
		_maxY = graphics().height() - image.height();
	}

	public ImageLayer getLayer() {
		return _layer;
	}

	public void update(int delta) {
		if (_falling) {
			_acceleration += GRAVITY * delta;
			if (_acceleration > MAX_GRAVITY_ACCELERATION) {
				_acceleration = MAX_GRAVITY_ACCELERATION;
			}
		} else {
			_acceleration -= (_speedUp * delta) + _weight;
			if (_acceleration < _maxSpeedUp) {
				_acceleration = _maxSpeedUp;
			}
		}

		float y = _layer.ty() + _acceleration;
		if (y > _maxY) {
			//TODO crash or something!
			y = _maxY;
			_acceleration = 0;
		} else if (y < _minY) {
			y = _minY;
		}

		_layer.setTy(y);
	}

	public void setFalling(boolean falling) {
		_falling = falling;
	}
}
