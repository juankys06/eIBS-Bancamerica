package datapro.eibs.master;

import java.util.*;
import java.net.*;

/**
 * This class can be used to return the current application
 * root file-system path to the /classes folder and
 * the own class folder.
 * @author: Frank Hernandez
 */
public class LocateMe 
{
	private String sClassesRoot = "";
	private String sClassPath = "";

	public LocateMe ()
	{
		String myPackage = "";
		myPackage = getClass().getPackage().getName();
		myPackage = myPackage.replace('.', '/') + "/";
		
		URL url = getClass().getResource("");
		//url = Thread.currentThread().getContextClassLoader().getResource("");
		sClassPath = url.getPath();
		sClassesRoot = sClassPath.substring(0, sClassPath.length() - myPackage.length());
		if (sClassesRoot.lastIndexOf('/') != (sClassesRoot.length() - 1)) sClassesRoot = sClassesRoot + "/";
		System.out.println(sClassesRoot);
	}
	
	public String getClassesRoot(){
		return sClassesRoot; 
	}
	
	public String getClassPath(){
		return sClassPath; 
	}	
}
