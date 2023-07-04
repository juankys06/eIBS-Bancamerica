package datapro.splf2pdf;

import com.ibm.as400.access.*;

import java.io.*;
import java.util.*;

import datapro.splf2pdf.pbd.*;

/**
 * This type was created in VisualAge.
 */
class Splf2Pdf {
	
	private AS400 system = new AS400();
	private CommandCall cmd = new CommandCall( system );

/**
 * Splf2Pdf constructor comment.
 */
public Splf2Pdf(String splf, String pdf) {
	this(splf, pdf, null);
}
/**
 * Splf2Pdf constructor comment.
 */
public Splf2Pdf(String splf, String pdf, String fontSize) {
	String splfJobNumber;
	String splfUserID;
	String splfJobName;
	String splfName;
	String splfNumber;

	StringTokenizer st = new StringTokenizer(splf, "/");
	if (st.countTokens() != 5) {
		System.out.println(splf + " contains invalid spooled file syntax.");
		System.exit(0);
	}

	splfJobNumber = st.nextToken();
	splfUserID = st.nextToken();
	splfJobName = st.nextToken();
	splfName = st.nextToken();
	splfNumber = st.nextToken();

	String tempSpoolFileName = "/spool." + splfJobNumber + "."
										 + splfUserID + "."
										 + splfJobName + "."
										 + splfName;

	String cmdCRTPF = "CRTPF QTEMP/SPOOL RCDLEN(202)";
	String cmdCPYSPLF = "CPYSPLF FILE(" + splfName + ") TOFILE(QTEMP/SPOOL) JOB(" +
						splfJobNumber + "/" + splfUserID + "/" + splfJobName +
						") SPLNBR(" + splfNumber +") CTLCHAR(*PRTCTL)";
	String cmdCPYTOSTMF = "CPYTOSTMF FROMMBR('/QSYS.LIB/QTEMP.LIB/SPOOL.FILE/SPOOL.MBR') " +
						  "TOSTMF('" + tempSpoolFileName + "') STMFOPT(*REPLACE) " +
						  "STMFCODPAG(819)";
	String cmdERASE = "ERASE OBJLNK('" + tempSpoolFileName + "')";
	String cmdDLTF = "DLTF QTEMP/SPOOL";

	boolean go = true;
	if (go) go = runcmd(cmdCRTPF);
	if (go) go = runcmd(cmdCPYSPLF);
	if (go) go = runcmd(cmdCPYTOSTMF);
	if (go) doCopy(system, tempSpoolFileName, pdf, fontSize);
	if (go) go = runcmd(cmdERASE);
	if (go) go = runcmd(cmdDLTF);

}
/**
 * This method was created in VisualAge.
 */
private void doCopy(AS400 system, String spl, String pdf, String fontSize) {
	InputStream in;
	OutputStream out;

	try {
		in = new IFSTextFileInputStream(system, spl);
		if (System.getProperty("os.name").equals("OS/400"))
			out = new IFSTextFileOutputStream(system, pdf);
		else
			out = new FileOutputStream(pdf);
		if (fontSize == null)
			(new Spl2Pdf(in, out)).convert();
		else
			(new Spl2Pdf(in, out, fontSize)).convert();
	}
	catch (Exception e) {
		System.out.println("Bad mojo: " + e);
	}
}
/**
 * Starts the application.
 * @param args an array of command-line arguments
 */
public static void main(java.lang.String[] args) {

	if (args.length == 2)
		new Splf2Pdf(args[0], args[1]);
	else
		new Splf2Pdf(args[0], args[1], args[2]);
	System.exit(0);

}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param command java.lang.String
 */
private boolean runcmd(String command) {
	
	boolean success = false;
	
	try {
		success = cmd.run(command);
		AS400Message[] messagelist = cmd.getMessageList();
		if (!success)
			System.out.println("Command " + cmd.getCommand() + " did not run!" );
		for (int i=0; i < messagelist.length; i++) {
			System.out.println( messagelist[i].getText() );
		}
	} catch (Exception e) {
		System.out.println("Command " + cmd.getCommand() + " did not run!" );
		System.out.println("  Exception: " + e);
	}
	
	return success;
}
}