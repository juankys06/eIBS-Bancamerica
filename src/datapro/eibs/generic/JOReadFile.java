package datapro.eibs.generic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;



public class JOReadFile {
/**
 * JOReadFile 
 * 
 * 
*/	
public JOReadFile(){
	super();
}	


public static boolean readFile(String filename,OutputStream out) {
	boolean success = false;	
	try {
	    File sendFile = new File(filename);
		int size = 0;
		DataOutputStream bout = new DataOutputStream(out);
		int sizeOfFile = new Long(sendFile.length()).intValue();
		byte[] data = new byte[2048];
		DataInputStream bin = new DataInputStream(new FileInputStream(sendFile));
		while((size = bin.read(data, 0, data.length)) != -1) {
			bout.write(data, 0, size);
		}
		bout.close();
		success = true;
	} catch (Exception e) {
		System.out.println("Exception ocurred. Exception = " + e);
		success = false;
	}
	return success;
}

}
