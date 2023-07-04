package datapro.eibs.master;

import java.util.*;

/**
 * Insert the type's description here.
 * Creation date: (7/31/2001 4:36:13 PM)
 * @author: Enrique M. Almonte
 */
public class JSEIBSMSGProp {
/**
 * JSEIBSMSGProp constructor comment.
 */
 private static String fieldMSG0001;
 private static String fieldMSG0002;
 private static String fieldMSG0003;
 private static String fieldMSG0004;
 private static String fieldMSG0005; 
 private static String fieldMSG0006;
 private static String fieldMSG0007;
 private static String fieldMSG0008;
 private static String fieldMSG0009;
 private static String fieldMSG0010; 
 private static String fieldMSG0011;
 private static String fieldMSG0012;
 private static String fieldMSG0013;
 private static String fieldMSG0014;
 private static String fieldMSG0015; 
 private static String fieldMSG0016;
 private static String fieldMSG0017;
 private static String fieldMSG0018;
 private static String fieldMSG0019;
 private static String fieldMSG0020; 

// files : English : en_Resource , Spanish : sp_Resouce
 private static String propertyFileName;
 private static String Lang;

public JSEIBSMSGProp() {
	super();
}
private static void initProperties() throws MissingResourceException {
	PropertyResourceBundle eIBSProperties = null;
	try {
		eIBSProperties = (PropertyResourceBundle)PropertyResourceBundle.getBundle(propertyFileName);
		try {
			fieldMSG0001 = eIBSProperties.getString("eIBS.MSG0001");
		}
		catch (Exception e) {
			fieldMSG0001 = "";
		}
		try {
			fieldMSG0002 = eIBSProperties.getString("eIBS.MSG0002");
		}
		catch (Exception e) {
			fieldMSG0002 = "";
		}
		try {
			fieldMSG0003 = eIBSProperties.getString("eIBS.MSG0003");
		}
		catch (Exception e) {
			fieldMSG0003 = "";
		}
		try {
			fieldMSG0004 = eIBSProperties.getString("eIBS.MSG0004");
		}
		catch (Exception e) {
			fieldMSG0004 = "";
		}
		try {
			fieldMSG0005 = eIBSProperties.getString("eIBS.MSG0005");
		}
		catch (Exception e) {
			fieldMSG0005 = "";
		}
				try {
			fieldMSG0006 = eIBSProperties.getString("eIBS.MSG0006");
		}
		catch (Exception e) {
			fieldMSG0006 = "";
		}
		try {
			fieldMSG0007 = eIBSProperties.getString("eIBS.MSG0007");
		}
		catch (Exception e) {
			fieldMSG0007 = "";
		}
		try {
			fieldMSG0008 = eIBSProperties.getString("eIBS.MSG0008");
		}
		catch (Exception e) {
			fieldMSG0008 = "";
		}
		try {
			fieldMSG0009 = eIBSProperties.getString("eIBS.MSG0009");
		}
		catch (Exception e) {
			fieldMSG0009 = "";
		}
		try {
			fieldMSG0010 = eIBSProperties.getString("eIBS.MSG0010");
		}
		catch (Exception e) {
			fieldMSG0010 = "";
		}
		try {
			fieldMSG0011 = eIBSProperties.getString("eIBS.MSG0011");
		}
		catch (Exception e) {
			fieldMSG0011 = "";
		}
		try {
			fieldMSG0012 = eIBSProperties.getString("eIBS.MSG0012");
		}
		catch (Exception e) {
			fieldMSG0012 = "";
		}
		try {
			fieldMSG0013 = eIBSProperties.getString("eIBS.MSG0013");
		}
		catch (Exception e) {
			fieldMSG0013 = "";
		}
		try {
			fieldMSG0014 = eIBSProperties.getString("eIBS.MSG0014");
		}
		catch (Exception e) {
			fieldMSG0014 = "";
		}
		try {
			fieldMSG0015 = eIBSProperties.getString("eIBS.MSG0015");
		}
		catch (Exception e) {
			fieldMSG0015 = "";
		}
		try {
			fieldMSG0016 = eIBSProperties.getString("eIBS.MSG0016");
		}
		catch (Exception e) {
			fieldMSG0016 = "";
		}
		try {
			fieldMSG0017 = eIBSProperties.getString("eIBS.MSG0017");
		}
		catch (Exception e) {
			fieldMSG0017 = "";
		}
		try {
			fieldMSG0018 = eIBSProperties.getString("eIBS.MSG0018");
		}
		catch (Exception e) {
			fieldMSG0018 = "";
		}
		try {
			fieldMSG0019 = eIBSProperties.getString("eIBS.MSG0019");
		}
		catch (Exception e) {
			fieldMSG0019 = "";
		}
		try {
			fieldMSG0020 = eIBSProperties.getString("eIBS.MSG0020");
		}
		catch (Exception e) {
			fieldMSG0020 = "";
		}


	}
	catch (MissingResourceException e) {
		System.out.println("Failed to load Properties file.   Be sure " + propertyFileName +
			" is located correctly.");
		throw e;
	}
}

/**
 * Sets the PropertyFile Language property (java.lang.String) value.
 * @param pLang The new value for the property.
 * @see #getPropertyFileLang
 */
public static void setPropertyFileLang(String pLang) {
	//if null sets the default language : English
	if (pLang.toUpperCase().trim().equals("") || pLang.toUpperCase().trim().equals("E")) {
		propertyFileName = "en_Resource";
		Lang = pLang;
	}
	if (pLang.toUpperCase().trim().equals("S")){
		propertyFileName = "sp_Resource";
		Lang = pLang;
	}
	//add more languages here ...
	//
	initProperties();
}
/**
 * gets the PropertyFile Language property (java.lang.String) value.
 * @param Lang The new value for the property.
 * @see #getPropertyFileLang
 */
public static String getPropertyFileLang() {
	return Lang;
}
/**
 * gets the MSG0001 property (java.lang.String) value.
 * @see #setMSG0001
 */
public static String getMSG0001() {
	if (fieldMSG0001 == null) initProperties();
	return fieldMSG0001;
}
/**
 * Sets the fieldMSG0001 property (java.lang.String) value.
 * @param MSG0001 The new value for the property.
 * @see #getMSG0001
 */
public static void setMSG0001(String MSG0001) {
	fieldMSG0001 = MSG0001;
}
/**
 * gets the MSG0002 property (java.lang.String) value.
 * @see #setMSG0002
 */
public static String getMSG0002() {
	if (fieldMSG0002 == null) initProperties();
	return fieldMSG0002;
}
/**
 * Sets the fieldMSG0002 property (java.lang.String) value.
 * @param MSG0002 The new value for the property.
 * @see #getMSG0002
 */
public static void setMSG0002(String MSG0002) {
	fieldMSG0002 = MSG0002;
}
/**
 * gets the MSG0003 property (java.lang.String) value.
 * @see #setMSG0003
 */
public static String getMSG0003() {
	if (fieldMSG0003 == null) initProperties();
	return fieldMSG0003;
}
/**
 * Sets the fieldMSG0003 property (java.lang.String) value.
 * @param MSG0003 The new value for the property.
 * @see #getMSG0003
 */
public static void setMSG0003(String MSG0003) {
	fieldMSG0003 = MSG0003;
}
/**
 * gets the MSG0004 property (java.lang.String) value.
 * @see #setMSG0004
 */
public static String getMSG0004() {
	if (fieldMSG0004 == null) initProperties();
	return fieldMSG0004;
}
/**
 * Sets the fieldMSG0004 property (java.lang.String) value.
 * @param MSG0004 The new value for the property.
 * @see #getMSG0004
 */
public static void setMSG0004(String MSG0004) {
	fieldMSG0004 = MSG0004;
}
/**
 * gets the MSG0005 property (java.lang.String) value.
 * @see #setMSG0005
 */
public static String getMSG0005() {
	if (fieldMSG0005 == null) initProperties();
	return fieldMSG0005;
}
/**
 * Sets the fieldMSG0005 property (java.lang.String) value.
 * @param MSG0005 The new value for the property.
 * @see #getMSG0005
 */
public static void setMSG0005(String MSG0005) {
	fieldMSG0005 = MSG0005;
}
/**
 * gets the MSG0006 property (java.lang.String) value.
 * @see #setMSG0006
 */
public static String getMSG0006() {
	if (fieldMSG0006 == null) initProperties();
	return fieldMSG0006;
}
/**
 * Sets the fieldMSG0006 property (java.lang.String) value.
 * @param MSG0006 The new value for the property.
 * @see #getMSG0006
 */
public static void setMSG0006(String MSG0006) {
	fieldMSG0006 = MSG0006;
}
/**
 * gets the MSG0007 property (java.lang.String) value.
 * @see #setMSG0007
 */
public static String getMSG0007() {
	if (fieldMSG0007 == null) initProperties();
	return fieldMSG0007;
}
/**
 * Sets the fieldMSG0007 property (java.lang.String) value.
 * @param MSG0007 The new value for the property.
 * @see #getMSG0007
 */
public static void setMSG0007(String MSG0007) {
	fieldMSG0007 = MSG0007;
}
/**
 * gets the MSG0008 property (java.lang.String) value.
 * @see #setMSG0008
 */
public static String getMSG0008() {
	if (fieldMSG0008 == null) initProperties();
	return fieldMSG0008;
}
/**
 * Sets the fieldMSG0008 property (java.lang.String) value.
 * @param MSG0008 The new value for the property.
 * @see #getMSG0008
 */
public static void setMSG0008(String MSG0008) {
	fieldMSG0008 = MSG0008;
}
/**
 * gets the MSG0009 property (java.lang.String) value.
 * @see #setMSG0009
 */
public static String getMSG0009() {
	if (fieldMSG0009 == null) initProperties();
	return fieldMSG0009;
}
/**
 * Sets the fieldMSG0009 property (java.lang.String) value.
 * @param MSG0009 The new value for the property.
 * @see #getMSG0009
 */
public static void setMSG0009(String MSG0009) {
	fieldMSG0009 = MSG0009;
}
/**
 * gets the MSG0010 property (java.lang.String) value.
 * @see #setMSG0010
 */
public static String getMSG0010() {
	if (fieldMSG0010 == null) initProperties();
	return fieldMSG0010;
}
/**
 * Sets the fieldMSG0010 property (java.lang.String) value.
 * @param MSG0010 The new value for the property.
 * @see #getMSG0010
 */
public static void setMSG0010(String MSG0010) {
	fieldMSG0010 = MSG0010;
}
/**
 * gets the MSG0005 property (java.lang.String) value.
 * @see #setMSG0005
 */
public static String getMSG0011() {
	if (fieldMSG0011 == null) initProperties();
	return fieldMSG0011;
}
/**
 * Sets the fieldMSG0011 property (java.lang.String) value.
 * @param MSG0011 The new value for the property.
 * @see #getMSG0011
 */
public static void setMSG0011(String MSG0011) {
	fieldMSG0005 = MSG0011;
}
/**
 * gets the MSG0012 property (java.lang.String) value.
 * @see #setMSG0012
 */
public static String getMSG0012() {
	if (fieldMSG0012 == null) initProperties();
	return fieldMSG0012;
}
/**
 * Sets the fieldMSG0012 property (java.lang.String) value.
 * @param MSG0012 The new value for the property.
 * @see #getMSG0012
 */
public static void setMSG0012(String MSG0012) {
	fieldMSG0012 = MSG0012;
}
/**
 * gets the MSG0013 property (java.lang.String) value.
 * @see #setMSG0013
 */
public static String getMSG0013() {
	if (fieldMSG0013 == null) initProperties();
	return fieldMSG0013;
}
/**
 * Sets the fieldMSG0013 property (java.lang.String) value.
 * @param MSG0013 The new value for the property.
 * @see #getMSG0013
 */
public static void setMSG0013(String MSG0013) {
	fieldMSG0013 = MSG0013;
}
/**
 * gets the MSG0014 property (java.lang.String) value.
 * @see #setMSG0014
 */
public static String getMSG0014() {
	if (fieldMSG0014 == null) initProperties();
	return fieldMSG0014;
}
/**
 * Sets the fieldMSG0014 property (java.lang.String) value.
 * @param MSG0014 The new value for the property.
 * @see #getMSG0014
 */
public static void setMSG0014(String MSG0014) {
	fieldMSG0014 = MSG0014;
}
/**
 * gets the MSG0015 property (java.lang.String) value.
 * @see #setMSG0015
 */
public static String getMSG0015() {
	if (fieldMSG0015 == null) initProperties();
	return fieldMSG0015;
}
/**
 * Sets the fieldMSG0015 property (java.lang.String) value.
 * @param MSG0015 The new value for the property.
 * @see #getMSG0015
 */
public static void setMSG0015(String MSG0015) {
	fieldMSG0015 = MSG0015;
}
/**
 * gets the MSG0016 property (java.lang.String) value.
 * @see #setMSG0016
 */
public static String getMSG0016() {
	if (fieldMSG0016 == null) initProperties();
	return fieldMSG0016;
}
/**
 * Sets the fieldMSG0016 property (java.lang.String) value.
 * @param MSG0016 The new value for the property.
 * @see #getMSG0016
 */
public static void setMSG0016(String MSG0016) {
	fieldMSG0016 = MSG0016;
}
/**
 * gets the MSG0017 property (java.lang.String) value.
 * @see #setMSG0017
 */
public static String getMSG0017() {
	if (fieldMSG0017 == null) initProperties();
	return fieldMSG0017;
}
/**
 * Sets the fieldMSG0017 property (java.lang.String) value.
 * @param MSG0017 The new value for the property.
 * @see #getMSG0017
 */
public static void setMSG0017(String MSG0017) {
	fieldMSG0017 = MSG0017;
}
/**
 * gets the MSG0018 property (java.lang.String) value.
 * @see #setMSG0018
 */
public static String getMSG0018() {
	if (fieldMSG0018 == null) initProperties();
	return fieldMSG0018;
}
/**
 * Sets the fieldMSG0018 property (java.lang.String) value.
 * @param MSG0018 The new value for the property.
 * @see #getMSG0018
 */
public static void setMSG0018(String MSG0018) {
	fieldMSG0018 = MSG0018;
}
/**
 * gets the MSG0019 property (java.lang.String) value.
 * @see #setMSG0019
 */
public static String getMSG0019() {
	if (fieldMSG0019 == null) initProperties();
	return fieldMSG0019;
}
/**
 * Sets the fieldMSG0019 property (java.lang.String) value.
 * @param MSG0019 The new value for the property.
 * @see #getMSG0019
 */
public static void setMSG0019(String MSG0019) {
	fieldMSG0019 = MSG0019;
}
/**
 * gets the MSG0020 property (java.lang.String) value.
 * @see #setMSG0020
 */
public static String getMSG0020() {
	if (fieldMSG0020 == null) initProperties();
	return fieldMSG0020;
}
/**
 * Sets the fieldMSG0020 property (java.lang.String) value.
 * @param MSG0020 The new value for the property.
 * @see #getMSG0020
 */
public static void setMSG0020(String MSG0020) {
	fieldMSG0020 = MSG0020;
}

}