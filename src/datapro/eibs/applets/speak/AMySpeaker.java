package datapro.eibs.applets.speak;

// Copy registered to Orestes Garcia- United States
// Source File Name:   AMiniSpeakO.java

import java.applet.Applet;
import java.applet.AudioClip;

public class AMySpeaker extends Applet {

	public AMySpeaker() {
	}
	public void init() {
	}
	public void SayThis(String s) {
		AudioClip audioclip = getAudioClip(getCodeBase(), s);
		audioclip.play();
	}
}