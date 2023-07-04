<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>ACH Batches</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EACH310List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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

function goProcess() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccionar un lote!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="3";
	document.forms[0].submit();
}

function goDetail() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccionar un lote!!!");
		return;	 
	}
	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH315?SCREEN=1&BATCH=" + document.getElementById("E01ACBBTH").value;
    window.location.href=pg;
}


function goDelete() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccione el lote a borrar!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="4";
	if (!confirm("Desea borrar el lote seleccionado y todas sus transacciones?")) {
		return;
	}
	document.forms[0].submit();
} 

function goApproval() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccione el lote a aprobar!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="15";
	if (!confirm("Desea aprobar el lote seleccionado y todas sus transacciones?")) {
		return;
	}
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
	document.getElementById("E01ACBBTH").value = bth;
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

<h3 align="center"><H3 align="center"><% if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")) out.print("Entrada ");
   else if (userPO.getPurpose().equals("APPROVAL")) out.print("Aprobación ");
	else out.print("Consulta ");%>
de Lotes de salida ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ach_batches_list.jsp, EACH310"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH310">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE="HIDDEN" name="E01ACBBTH" value="0">

<TABLE class="tbenter"> 
<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>
	<TR>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goNew()"><b>Nuevo<br>Lote</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goProcess()"><b>Modificar<br>Lote</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goDetail()"><b>Transacciones</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goDelete()"><b>Borrar<br>Lote</b></a>
		</TD>
	  	<TD align="CENTER" class="TDBKG" width="20%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
      	</TD>
	</TR>
<%      
  } else if (userPO.getPurpose().equals("APPROVAL")){
%>
	<TR>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goProcess()"><b>Ver Detalle<br>del Lote</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goDetail()"><b>Ver<br>Transacciones</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goApproval()"><b>Aprobar<br>Lote</b></a>
		</TD>
	  	<TD align="CENTER" class="TDBKG" width="25%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
      	</TD>
	</TR>
<%      
  	} else {

%> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="33%">
			<a href="javascript:goProcess()"><b>Ver Detalle<br>del Lote</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="33%">
			<a href="javascript:goDetail()"><b>Ver<br>Transacciones</b></a>
      	</TD>
	  	<TD align="CENTER" class="TDBKG" width="33%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
      	</TD>
	</TR>
<% } %>
</TABLE> 

<%	if ( EACH310List.getNoResult() ) { %>
 	<TABLE class="tbenter" width=100% height=30%">
 	<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay Lotes.</h4></div>
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
		<td NOWRAP align="center" width="5%"><B>Sel</B></td>
		<td NOWRAP align="center" width="5%"><B>Número</B></td>
		<td NOWRAP align="center" width="25%"><B>Descripción</B></td>
		<td NOWRAP align="center" width="5%"><B>Clase</B></td>
		<TD nowrap align="center" width="10%"><B>Fecha<br>Valor</B></TD>
		<TD nowrap align="center" width="15%"><B>Debitos</B></TD>
		<TD nowrap align="center" width="15%"><B>Créditos</B></TD>
		<TD nowrap align="center" width="10%"><B>Nro TR</B></TD>
		<TD nowrap align="center" width="10%"><B>Usuario</B></TD>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="5%">
			<INPUT type="text" name="SEARCHCDE" size="5" maxlength="4" value="<%= userPO.getProdCode() %>">
				<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch()" width="15" height="11"
				 ALT="Ordenar y posicionarse en...">
		</td>
		<td NOWRAP align="center" width="25%"></td>
		<td NOWRAP align="center" width="5%"></td>
		<TD nowrap align="center" width="10%"></TD>
		<TD nowrap align="center" width="15%"></TD>
		<TD nowrap align="center" width="15%"></TD>
		<TD nowrap align="center" width="10%"></TD>
		<TD nowrap align="center" width="10%"></TD>
	</TR>
 
        <%
    	  int i = 0;
          EACH310List.initRow();    
          while (EACH310List.getNextRow()) {
            EACH31001Message msgList = (EACH31001Message) EACH310List.getRecord();
			if (msgList.getH01FLGWK2().trim().equals("W")) {
				warnImg= "<IMG border=\"0\" src=\"../images/warning01.gif\" ALT=\"Ver Transacciones\" onClick=\"showWarnings('" + msgList.getE01ACBBTH() + "','ROW')\">";
			} else {
				warnImg= "";
			}	
         %>              
    <TR id=trclear>
		<td NOWRAP align="center" width="5%">
           	<INPUT TYPE="radio" name="ROW"  value="<%= EACH310List.getCurrentRow()%>" onClick="setKey('<%=msgList.getE01ACBBTH()%>')">
		</td>
		<td NOWRAP align="center" width="5%" ><%= msgList.getE01ACBBTH() %><%=warnImg%></td>
		<td NOWRAP align="left" width="25%" ><%= msgList.getE01ACBDSC() %></td>
		<td NOWRAP align="center" width="5%" ><%= msgList.getE01ACBECD() %></td>
		<td NOWRAP align="center" width="10%" ><%= Util.formatDate(msgList.getE01ACBVDD(),msgList.getE01ACBVDM(),msgList.getE01ACBVDY())%></td>
		<td NOWRAP align="right" width="15%" ><%= msgList.getE01ACBDEB() %></td>
		<td NOWRAP align="right" width="15%" ><%= msgList.getE01ACBCRE() %></td>
		<td NOWRAP align="right" width="10%" ><%= msgList.getE01ACBTRN() %></td>
		<td NOWRAP align="center" width="10%" ><%= msgList.getE01ACBUSR() %></td>
	</TR>
       <% 
       	 i++; 
        } 
       %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="88%" ALIGN=CENTER>
   	 <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25">
       <%
    	if ( EACH310List.getShowPrev() ) {
  			int pos = EACH310List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH310?SCREEN=1&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getProdCode() + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EACH310List.getShowNext()) {
  			int pos = EACH310List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH310?SCREEN=1&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getProdCode() + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EACH310List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EACH310List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
