package org.quesito.rancio.core;

import playn.core.Layer;

public abstract class ScrollingLayout {

	protected final Layer _layer;
	private final float _speed;

	public ScrollingLayout(Layer layer, float speed) {
		_layer = layer;
		_speed = speed;
	}

	public void update(int delta) {
		_layer.setTx(_layer.tx() - (delta * _speed));
	}

	public Layer getLayer() {
		return _layer;
	}
}
