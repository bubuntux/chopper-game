package org.quesito.rancio.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import org.quesito.rancio.core.Rancio;

public class RancioJava {

	public static void main(String[] args) {
		JavaPlatform.Config config = new JavaPlatform.Config();
		// use config to customize the Java platform, if needed
		JavaPlatform.register(config);
		PlayN.run(new Rancio());
	}
}