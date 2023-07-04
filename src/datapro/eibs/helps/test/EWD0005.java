package datapro.eibs.helps.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EWD0005DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class EWD0005 extends Thread {

	public void run() {
		Socket s = null;
		MessageContext mc = null;
		MessageRecord newmessage = null;
		EWD0005DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;
		JBList beanList = null;

		try {
			s = new Socket("134.177.251.91", 47000 + 1);
			s.setSoTimeout(5000);
			mc =
				new MessageContext(
					new DataInputStream(new BufferedInputStream(s.getInputStream())),
					new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
					"datapro.eibs.beans");
		} catch (Exception e) {
			System.out.print("Error: " + e);
			return;
		}

		try {
			msgHelp = (EWD0005DSMessage) mc.getMessageRecord("EWD0005DS");
			msgHelp.setEWDSHR("A");
			msgHelp.send();
			msgHelp.destroy();

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0005DS")) {

				beanList = new JBList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String myRow = "";
				String chk = "";

				while (true) {

					msgHelp = (EWD0005DSMessage) newmessage;

					marker = msgHelp.getEWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgHelp.getEWDREC()));
							chk = "checked";
						} else {
							chk = "";
						}
						myRow = "<TR>";
						myRow += "<td NOWRAP ><a href=\"javascript:enter('"
							+ msgHelp.getEWDACC()
							+ "')\">"
							+ Util.formatCell(msgHelp.getEWDNME())
							+ "</a></td>";
						myRow += "<td NOWRAP ><a href=\"javascript:enter('"
							+ msgHelp.getEWDACC()
							+ "')\">"
							+ Util.formatCell(msgHelp.getEWDACC())
							+ "</a></td>";
						myRow += "<td NOWRAP ><a href=\"javascript:enter('"
							+ msgHelp.getEWDACC()
							+ "')\">"
							+ Util.formatCell(msgHelp.getEWDBRN())
							+ "</a></td>";
						myRow += "<td NOWRAP ><a href=\"javascript:enter('"
							+ msgHelp.getEWDACC()
							+ "')\">"
							+ Util.formatCell(msgHelp.getEWDCCY())
							+ "</a></td>";
						myRow += "<td NOWRAP ><a href=\"javascript:enter('"
							+ msgHelp.getEWDACC()
							+ "')\">"
							+ Util.formatCell(msgHelp.getEWDATY())
							+ "</a></td>";
						myRow += "<td NOWRAP ><a href=\"javascript:enter('"
							+ msgHelp.getEWDACC()
							+ "')\">"
							+ Util.formatCell(msgHelp.getEWDPRD())
							+ "</a></td>";
						myRow += "<td NOWRAP ALIGN=RIGHT><a href=\"javascript:enter('"
							+ msgHelp.getEWDACC()
							+ "')\">"
							+ Util.fcolorCCY(msgHelp.getEWDAMT())
							+ "</a></td>";
						myRow += "</TR>";
						beanList.addRow(myFlag, myRow);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				System.out.print("Ends with no error.");

			}
		} catch (Exception e) {
			System.out.print("Error: " + e);
		}

		try {
			s.close();
		} catch (Exception e) {
			System.out.print("Error: " + e);
		}
	}
}