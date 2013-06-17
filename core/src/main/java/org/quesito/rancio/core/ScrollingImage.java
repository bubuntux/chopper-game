package org.quesito.rancio.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

public class ScrollingImage extends ScrollingLayout {

	public ScrollingImage(String image, float speed) {
		super(graphics().createImageLayer(assets().getImageSync("images/" + image)), speed);
	}
}
