package org.quesito.rancio.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import org.quesito.rancio.core.Rancio;

public class RancioActivity extends GameActivity {

  @Override
  public void main(){
    PlayN.run(new Rancio());
  }
}
