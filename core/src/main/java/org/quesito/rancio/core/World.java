package org.quesito.rancio.core;

import java.util.Random;
import org.quesito.rancio.core.inputs.MouseListener;
import playn.core.GroupLayer;
import playn.core.Image;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.mouse;

public class World {

	private final GroupLayer _layer;
	private final ScrollingImage _bg;
	private final ScrollingGroup _border;
	private final Chopper _chopper;

	public World() {
		int Rx = 0, Ry;
		Random r = new Random();

		_bg = new ScrollingImage("panorama.jpg", 0.015f);
		Image blockImage = assets().getImage("images/cloud.png");

		_border = new ScrollingGroup(0.055f);

		for (int i = 0; i < 25; i++) {
			Rx += r.nextInt(420);
			Ry = r.nextInt(620);

			_border.addAt(graphics().createImageLayer(blockImage), i * 360 + Rx, Ry);
		}
		_layer = graphics().createGroupLayer();

		_layer.add(_bg.getLayer());
		_layer.add(_border.getLayer());

		_chopper = new Chopper();
		_layer.add(_chopper.getLayer());

		mouse().setListener(new MouseListener(_chopper));
	}

	public GroupLayer getLayer() {
		return _layer;
	}

	public void update(int delta) {
		_bg.update(delta);
		_border.update(delta);
		_chopper.update(delta);
	}
}
