package datapro.eibs.generic;

import java.io.*;
import java.util.*;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.JSEIBSMSGProp;

/**
 * Insert the type's description here.
 * Creation date: (8/23/2001 11:51:51 AM)
 * @author: Enrique M. Almonte
 */
public class JODBMetaData {
	private DatabaseMetaData DBMetaData = null;
	private Statement stMetaData = null;
	private ResultSet rsMetaDataCatalog = null;
	private ResultSet rsMetaDataSchema = null;
	private ResultSet rsMetaDataType = null;
	private ResultSet rsMetaDataTable = null;

	private String filterTableCatalog = "";
	private String filterTableSchema = "";
	private String filterTablePattern = "";
	private String [] filterTableType;

	private String currentTableNameDesc = "";
/**
 * JODBMetaData constructor comment.
 */
 
public JODBMetaData() {
	super();
}
/* method : setDBConnection
 * params : Connection DBConnection
 * sets the dastabse connection to extract the metadata
 */
public void setDBConnection(Connection DBConnection){
	try {
		DBMetaData = DBConnection.getMetaData();
		rsMetaDataCatalog = DBMetaData.getCatalogs();
		rsMetaDataSchema = DBMetaData.getSchemas();
		rsMetaDataType = DBMetaData.getTableTypes();
	} catch (SQLException e){
		System.out.println("SQL Exception : " + e);
	}
}
/* method : getCatalog
 * params : 
 * returns the name of the catalogs , "<EOR>" if end of resultset
 */
public String getCatalog(){
	String valReturn = "";
	try {
		if (rsMetaDataCatalog.next()) {
			valReturn = rsMetaDataCatalog.getString(1);
		} else {
			valReturn = "<EOR>";
		}
		
	} catch (SQLException e){
		System.out.println("SQL Exception : " + e);
	}
	return valReturn;
}
/* method : getSchema
 * params : 
 * returns the name of the schemas , "<EOR>" if end of resultset
 */
public String getSchema(){
	String valReturn = "";
	try {
		if (rsMetaDataSchema.next()) {
			valReturn = rsMetaDataSchema.getString(1);
		} else {
			valReturn = "<EOR>";
		}
		
	} catch (SQLException e){
		System.out.println("SQL Exception : " + e);
	}
	return valReturn;
}
/* method : getTableType
 * params : 
 * returns the name of the table types  , "<EOR>" if end of resultset
 */
public String getTableType(){
	String valReturn = "";
	try {
		if (rsMetaDataType.next()) {
			valReturn = rsMetaDataType.getString(1);
		} else {
			valReturn = "<EOR>";
		}
		
	} catch (SQLException e){
		System.out.println("SQL Exception : " + e);
	}
	return valReturn;
}
/* method : setFilterTable
 * params : String catalog, String schema, String pattern, String type
 * sets the filter for the metadata resultset of tables
 *
 */
public void setFilterTable(String catalog, String schema, String pattern, String [] type){
	filterTableCatalog = catalog;
	filterTableSchema = schema;
	filterTablePattern = pattern;
	filterTableType = type;
	try {
		rsMetaDataTable = DBMetaData.getTables(filterTableCatalog,filterTableSchema,filterTablePattern,filterTableType);
	} catch (SQLException e) {
		System.out.println("Exception : " + e);
	}
}
 
/* method : getTable
 * params : 
 * returns the name of the tables , "<EOR>" if end of resultset
 *
*TABLE_CAT String => table catalog (may be null)
*TABLE_SCHEM String => table schema (may be null)
*TABLE_NAME String => table name
*TABLE_TYPE String => table type.  Typical types are "TABLE",
*	"VIEW",	"SYSTEM TABLE", "GLOBAL TEMPORARY", 
*	"LOCAL TEMPORARY", "ALIAS", "SYNONYM".
*REMARKS String => explanatory comment on the table
*/
	  
public String getTable(){
	
	String valReturn = "";
	if (rsMetaDataTable == null) {
		try {
			rsMetaDataTable = DBMetaData.getTables(filterTableCatalog,filterTableSchema,filterTablePattern,filterTableType);
		} catch (SQLException e) {
			System.out.println("Exception : " + e);
		}
	}
	
	try {
		if (rsMetaDataTable.next()) {
			valReturn = rsMetaDataTable.getString(3);
			currentTableNameDesc = rsMetaDataTable.getString(5);
		} else {
			valReturn = "<EOR>";
			currentTableNameDesc = "";
		}
	} catch (SQLException e){
		System.out.println("SQL Exception : " + e);
	}
	return valReturn;
}

/* method : getTableDesc
 * params : 
 * returns the description of the current table
 */
 
public String getTableDesc(){
	return currentTableNameDesc;
}



}