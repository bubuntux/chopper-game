package org.quesito.rancio.core;

import playn.core.Game;
import playn.core.GroupLayer;

import static playn.core.PlayN.graphics;

import static playn.core.PlayN.*;

public class Rancio extends Game.Default {

	private Chopper _chopper;
	private World _world;

	public Rancio() {
		super(60); // call update every 33ms (30 times per second)
	}

	@Override
	public void init() {
		_world = new World();
		_chopper = new Chopper();

		GroupLayer rootLayer = graphics().rootLayer();
		rootLayer.add(_world.getLayer());
		rootLayer.addAt(_chopper.getLayer(), 150, 100);

		if (keyboard().hasHardwareKeyboard()) {
			keyboard().setListener(new KeyboardListener(_chopper));
		}
		if (mouse().hasMouse()) {
			mouse().setListener(new MouseListener(_chopper));
		}
		if (touch().hasTouch()) {
			touch().setListener(new TouchListener(_chopper));
		}
	}

	@Override
	public void update(int delta) {
		_chopper.update(delta);
		_world.update(delta);
	}

	@Override
	public void paint(float alpha) {
		// the background automatically paints itself, so no need to do anything here!
	}
}
