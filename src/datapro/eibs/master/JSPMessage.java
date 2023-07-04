/*
 * Created on Mar 13, 2007
 * Created by Saif Mazhar
 */
package datapro.eibs.master;
import java.util.ArrayList;

import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

/**
 * @author smazhar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSPMessage
{
	private ArrayList names, lengths, values;

	public JSPMessage(MessageRecord message)
	{
		names = new ArrayList();
		lengths = new ArrayList();
		values = new ArrayList();
		java.util.Enumeration enu = message.fieldEnumeration();
		String value = null;
		MessageField field = null;
		while (enu.hasMoreElements())
		{
			field = (MessageField) enu.nextElement();
			names.add(field.getTag());
			lengths.add(Integer.toString(field.getLength()));
			values.add(field.getString());
		}
	}

	public String getLength(String s)
	{
		return (String) lengths.get(names.indexOf(s));
	}

	public String getValue(String s)
	{
		return (String) values.get(names.indexOf(s));
	}

	public boolean valueEquals(String tag, String value)
	{
		return (getValue(tag).equals(value));
	}

	public String getInput(String tag, String html)
	{
		return "<INPUT type=\"text\" name=\""
			+ tag
			+ "\" "
			+ "value=\""
			+ getValue(tag)
			+ "\" size=\""
			+ getLength(tag)
			+ "\" "
			+ html
			+ ">";
	}

	public String getInput(String tag)
	{
		return getInput(tag, "");
	}

	public String getSelect(String tag, String[] values, String[] options)
	{
		String select = "<SELECT name=\"" + tag + "\">\n";
		for (int i = 0; i < options.length; i++)
		{
			select += "    <OPTION value=\""
				+ values[i] + "\""
				+ (valueEquals(tag, values[i]) ? " selected >" : " >")
				+ options[i]
				+ "</OPTION>\n";
		}
		return select + "</SELECT>";
	}

}
