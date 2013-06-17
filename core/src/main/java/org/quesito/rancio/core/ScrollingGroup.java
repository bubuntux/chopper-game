package org.quesito.rancio.core;

import playn.core.GroupLayer;
import playn.core.Layer;

import static playn.core.PlayN.graphics;

public class ScrollingGroup extends ScrollingLayout {

	public ScrollingGroup(float speed) {
		super(graphics().createGroupLayer(), speed);
	}

	public void addAt(Layer layer, float tx, float ty) {
		((GroupLayer) _layer).addAt(layer, tx, ty);
	}
}
