package datapro.eibs.applets.menu;

import java.io.Serializable;
import java.util.EventObject;

public class AButtonTreeEvent extends EventObject
	implements Serializable {

	private String actionString;
	private int selectedLine;

	public AButtonTreeEvent(Object obj, String s, int i) {
		super(obj);
		selectedLine = 0;
		actionString = s;
		if(i >= 0)
			selectedLine = i;
	}
	public String getActionString() {
		return actionString;
	}
	public int getSelectedLine() {
		return selectedLine;
	}
}