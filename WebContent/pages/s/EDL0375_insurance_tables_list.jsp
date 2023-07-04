<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Insurance Calculations Table</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "tableList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
var ok = false;
 
function goSearch() {
	if (document.getElementById("SEARCHCDE").value == "") {
		alert("Favor digitar un lote para posicionarse en la lista!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="1";
	document.forms[0].submit();
}

function goNew() {
	document.getElementById("SCREEN").value="2";
	document.forms[0].submit();
}

function goMaintenance() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccionar una tabla");
		return;	 
	}
	document.getElementById("SCREEN").value="3";
	document.forms[0].submit();
}

function goInquiry() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccionar una tabla");
		return;	 
	}
	document.getElementById("SCREEN").value="4";
	document.forms[0].submit();
}

function isCheck() {
	var formLength= document.forms[0].elements.length;
   	ok = false;
	for(n=0;n<formLength;n++) {
     	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ROW") {
			if (document.forms[0].elements[n].checked == true) {
				ok = true;
				break;
			}
      	}
    }
}

function setKey(bth) {
	document.getElementById("KEY").value = bth;
}

</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) { 
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Mantenimiento de Tablas de Calculos de Seguros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ach_batches_list.jsp, EACH310"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL0375">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE="HIDDEN" name="KEY" value="0">

<TABLE class="tbenter"> 

	<TR>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goNew()"><b>Nueva Tabla</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goMaintenance()"><b>Editar</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goInquiry()"><b>Consultar</b></a>
      	</TD>
	  	<TD align="CENTER" class="TDBKG" width="20%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
      	</TD>
	</TR>

</TABLE> 

<%	if ( tableList.getNoResult() ) { %>
 	<TABLE class="tbenter" width=100% height=30%">
 	<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay tablas para mostrar.</h4></div>
      </TD>
	</TR>
   	</TABLE>
<%
	}
	else {
		String warnImg= "";
%>    
    
  <TABLE class="tableinfo" id="dataTable">
    <TR id=trdark> 
		<td NOWRAP align="center"><B></B></td>
		<td NOWRAP align="center"><B>Código</B></td>
		<td NOWRAP align="center"><B>Descripción</B></td>
		<td NOWRAP align="center"><B>Monto por Base de Capital</B></td>
	</TR>
        <%
    	  int i = 0;
          tableList.initRow();    
          while (tableList.getNextRow()) {
            datapro.eibs.beans.EDL037501Message msgList = (datapro.eibs.beans.EDL037501Message) tableList.getRecord();

         %>              
    <TR id=trclear>
		<td NOWRAP align="center" width="5%">
           	<INPUT TYPE="radio" name="ROW"  value="<%= tableList.getCurrentRow()%>" onClick="setKey('<%=msgList.getE01INSCOD()%>')">
		</td>
		<td NOWRAP align="center" width="5%" ><%= msgList.getE01INSCOD() %></td>
		<td NOWRAP align="center" width="25%" ><%= msgList.getE01INSDEC() %></td>
		<td NOWRAP align="center" width="25%" ><%= msgList.getE01INSAMT() %></td>
	</TR>
       <% 
       	 i++; 
        } 
       %> 
  </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= tableList.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= tableList.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
