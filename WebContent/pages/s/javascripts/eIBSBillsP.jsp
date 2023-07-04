<%@ page contentType="application/x-javascript" %>

// Global variable for english
var fieldName;
var fieldDesc;

function GetVendorBP(code,name)
{
	page = webapp + "/servlet/datapro.eibs.bap.JSEWD0560";
	fieldName=code;
	fieldDesc=name;
	CenterWindow(page,500,500,2);
}
