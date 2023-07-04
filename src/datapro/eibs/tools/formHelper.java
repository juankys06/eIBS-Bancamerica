/*
 * Created on Mar 26, 2007
 * Created by Saif Mazhar
 */
package datapro.eibs.tools;

/**
 * @author smazhar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class formHelper
{

	public String getInput(String name, String value, int maxlength, int size, String html)
	{
		return "<INPUT type=\"text\" name=\""
			+ name
			+ "\" value=\""
			+ value
			+ "\" maxlength=\""
			+ maxlength
			+ "\" size=\""
			+ size
			+ "\" "
			+ html
			+ ">";
	}

	public String getInput(String name, String value, int size, String html)
	{
		return getInput(name, value, size, size, html);
	}

	public String getInput(String name, String value, int size)
	{
		return getInput(name, value, size, size, "");
	}

	public String getInput(String name, String value, int maxlength, int size)
	{
		return getInput(name, value, maxlength, size, "");
	}

	public String getInput(String name, int size)
	{
		return getInput(name, "", size, size, "");
	}

	public String getInput(int size)
	{
		return getInput("", "", size, size, "");
	}

}
