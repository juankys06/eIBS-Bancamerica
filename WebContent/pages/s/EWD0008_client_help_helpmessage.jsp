<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*" %>
<script language="javascript">
//<!-- Hide from old browsers
function enter(code, description, type) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldAux1){
  		top.opener.document.forms[0].elements[n].value = description;
  		break;
	}
 }
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldAux2){
  		top.opener.document.forms[0].elements[n].value = type;
  		break;
	}
 }
top.close();
 }
//-->
</script>
<TITLE></TITLE>
</head>
<jsp:useBean id= "ewd0008Help" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>

 <SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if ( ewd0008Help.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD>

      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font>
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>

  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="15%">Código Producto</TH>
      <TH ALIGN=CENTER  nowrap width="15%">Tipo de producto</TH>
      <TH ALIGN=CENTER  nowrap width="15%">Moneda</TH>
      <TH ALIGN=CENTER  nowrap width="55%">Descripción</TH>
    </TR>
    <%
                String AppCode = (String)request.getAttribute("AppCode");
                String ProductBank = (String)request.getAttribute("ProductBank");
                String srhProduct = (String)request.getAttribute("srhProduct");
                ewd0008Help.initRow();
                while (ewd0008Help.getNextRow()) {
                    if (ewd0008Help.getFlag().equals("")) {
                    		out.println(ewd0008Help.getRecord());
                    }
                }
              %>
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0008Help.getShowPrev() ) {
      			int pos =ewd0008Help.getFirstRec() - 21;
      			   out.print("<A HREF=\""+ request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0008?AppCode=" + AppCode + "&FromRecord=" + pos +  "&ProductBank=" + ProductBank + "&srhProduct=" + srhProduct + " \"> <img src=\"" + request.getContextPath() + "/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%
        if ( ewd0008Help.getShowNext() ) {
      			int pos = ewd0008Help.getLastRec();
      			out.print("<A HREF=\"" +request.getContextPath() +"/servlet/datapro.eibs.helps.JSEWD0008?AppCode=" + AppCode + "&FromRecord=" + pos +  "&ProductBank=" + ProductBank + "&srhProduct=" + srhProduct + " \" ><img src=\""+ request.getContextPath() + "/images/s/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
<%
  }
%>
</FORM>
</BODY>
</HTML>

