/*
 * Created on Feb 1, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package datapro.eibs.tools;

import java.util.ArrayList;
import datapro.eibs.sockets.MessageRecord;
/**
 * @author smazhar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TableInfo
{
	private ArrayList table = new ArrayList();

	private static final int LABEL = 1;
	private static final int INPUT = 2;
	private static final int SELECT = 3;
	private static final int HTML = 4;
	private static final int POSITION = 5;
	private static final int NEW_ROW = 6;
	private static final int NEW_COL = 7;
	int rows;

	private MessageRecord msg;
	private String beginTableHtml = "\n" + "<TABLE class=\"tableinfo\">" + "\n\t" + "<TR><TD><TABLE cellspacing=\"0\" cellpadding=\"2\" width=\"100%\" border=\"0\">";
	private String endTableHtml = "\n\t<//TD><//TR><//TABLE>" + "\n<\\TABLE>";

	public TableInfo(MessageRecord message)
	{
		msg = message;
	}

	private class CellObject
	{
		String name, size, maxlength, label, align, id, readonly, value;
		String[] valuesAndLabels;
		int type;
	}

	public void addInput(String name, int size, int maxlength, boolean readonly)
	{
		CellObject cellObject = new CellObject();
		cellObject.name = "name=\"" + name + "\" ";
		cellObject.size = "size=\"" + size + "\" ";
		cellObject.maxlength = "maxlength=\"" + maxlength + "\" ";
		cellObject.readonly = (readonly ? "readonly = \"readonly\" " : "");
		cellObject.value = "value=\"" + msg.getField(cellObject.name).getString().trim() + "\" ";
		cellObject.type = INPUT;
		table.add(cellObject);
	}

	public void addInput(String name, int size, int maxlength)
	{
		addInput(name, size, maxlength, false);
	}

	public void addInput(String name, int sizeAndMaxlenth)
	{
		addInput(name, sizeAndMaxlenth, sizeAndMaxlenth, false);
	}

	public void addLabel(String label)
	{
		CellObject cellObject = new CellObject();
		cellObject.type = LABEL;
		cellObject.label = label;
		table.add(cellObject);
	}

	public void addHtml(String html)
	{
		CellObject cellObject = new CellObject();
		cellObject.type = LABEL;
		cellObject.label = html;
		table.add(cellObject);
	}

	public void addSelect(String name, String[] valuesAndLabels)
	{
		CellObject cellObject = new CellObject();
		cellObject.name = name;
		cellObject.valuesAndLabels = valuesAndLabels;
		cellObject.type = SELECT;
		table.add(cellObject);
	}

	public void newRow(String id)
	{
		CellObject cellObject = new CellObject();
		cellObject.type = NEW_ROW;
		cellObject.id = "id=\"" + id + "\" ";
		table.add(cellObject);
	}
	public void newRow()
	{
		newRow((rows++ % 2 == 0 ? "trdark" : "trclear"));
	}

	public void newCol(String align)
	{
		CellObject cellObject = new CellObject();
		cellObject.type = NEW_COL;
		cellObject.align = "align=\"" + align + "\" ";
		table.add(cellObject);
	}

	public void newCol()
	{
		newCol("left");
	}

	public String getHtml()
	{
		String html = beginTableHtml;

		for (int i = 0; i < table.size(); i++)
		{
			CellObject co = (CellObject) table.get(i);
			switch (co.type)
			{
				case NEW_ROW :
					if (i > 0)
						html += "\n\t\t\t<\\TD>" + "\n\t\t<\\TR>";
					html += "\n\t\t<TR " + co.id + ">";
					break;
				case NEW_COL :
					if (i > 0)
						html += "\n\t\t\t<\\TD>";
					html += "\n\t\t\t<TD " + co.align + ">";
				case LABEL :
					html += co.label;
					break;
				case INPUT :
					html += "<INPUT type=\"text\" " + co.name + co.size + co.maxlength + co.value + co.readonly + ">";
					break;
			}
		}
		return html + endTableHtml;
	}

	public String getBeginTableHtml()
	{
		return beginTableHtml;
	}

	public String getEndTableHtml()
	{
		return endTableHtml;
	}

	public void setBeginTableHtml(String string)
	{
		beginTableHtml = string;
	}

	public void setEndTableHtml(String string)
	{
		endTableHtml = string;
	}
}