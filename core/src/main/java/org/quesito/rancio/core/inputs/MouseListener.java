package org.quesito.rancio.core.inputs;

import org.quesito.rancio.core.Chopper;
import playn.core.Mouse;

public class MouseListener extends Mouse.Adapter {

	private final Chopper _chopper;

	public MouseListener(Chopper chopper) {
		_chopper = chopper;
	}

	@Override
	public void onMouseDown(Mouse.ButtonEvent event) {
		_chopper.setFalling(false);
	}

	@Override
	public void onMouseUp(Mouse.ButtonEvent event) {
		_chopper.setFalling(true);
	}
}
