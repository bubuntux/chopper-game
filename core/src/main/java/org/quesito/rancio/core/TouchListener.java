package org.quesito.rancio.core;

import playn.core.Touch;

public class TouchListener extends Touch.Adapter {

	private final Chopper _chopper;

	public TouchListener(Chopper chopper) {
		_chopper = chopper;
	}

	@Override
	public void onTouchStart(Touch.Event[] touches) {
		_chopper.setFalling(false);
	}

	@Override
	public void onTouchEnd(Touch.Event[] touches) {
		_chopper.setFalling(true);
	}
}
