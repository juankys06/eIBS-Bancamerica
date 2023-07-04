<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>ACH Authorizations</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EACH300List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
var ok = false;
 
function goSearch(type) {
	if (type == 'A') {
		if (document.getElementById("SEARCHACC").value == "") {
			alert("Favor digitar un Código para posicionarse en la lista!!!");
			return;
		}
		document.getElementById("SEARCHCDE").value = document.getElementById("SEARCHACC").value;
	}
	if (type == 'C') {
		if (document.getElementById("SEARCHCUN").value == "") {
			alert("Favor digitar un Código para posicionarse en la lista!!!");
			return;
		}
		document.getElementById("SEARCHCDE").value = document.getElementById("SEARCHCUN").value;
	}

	if (type == 'O') {
		if (document.getElementById("SEARCHCOM").value == "") {
			alert("Favor digitar un Código para posicionarse en la lista!!!");
			return;
		}
		document.getElementById("SEARCHCDE").value = document.getElementById("SEARCHCOM").value;
	}
	if (type == 'N') {
		if (document.getElementById("SEARCHNOM").value == "") {
			alert("Favor digitar un nombre para posicionarse en la lista!!!");
			return;
		}
		document.getElementById("SEARCHCDE").value = document.getElementById("SEARCHNOM").value;
	}
	
	document.getElementById("SEARCHTYPE").value = type;
	<% if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){%>
			document.getElementById("SCREEN").value="1";
	<% } else { %>
			document.getElementById("SCREEN").value="6";
	<% }%>
	document.forms[0].submit();
}

function goNew() {
	document.getElementById("SCREEN").value="2";
	document.forms[0].submit();
}

function goProcess() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccionar un código!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="3";
	document.forms[0].submit();
}

function goDelete() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccione el código a borrar!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="4";
	if (!confirm("Desea borrar el registro seleccionado?")) {
		return;
	}
	document.forms[0].submit();
} 

function goInquiryInst() {
  	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH110?SCREEN=6";
	CenterWindow(pg,600,500,2);
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

function setKey(cun,acc,ocd) {
	document.getElementById("E01ACACUN").value = cun;
	document.getElementById("E01ACAACC").value = acc;
	document.getElementById("E01ACAOCD").value = ocd;
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

<h3 align="center">Autorizaciones ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="ach_operators_list.jsp, EACH300"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH300">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE="HIDDEN" name="E01ACACUN" value="0">
  <INPUT TYPE="HIDDEN" name="E01ACAACC" value="0">
  <INPUT TYPE="HIDDEN" name="E01ACAOCD" value="0">
  <INPUT TYPE="HIDDEN" name="SEARCHCDE" value="">
  <INPUT TYPE="HIDDEN" name="SEARCHTYPE" value="A">
<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>

<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goNew()"><b>Nueva<br>Autorización</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goProcess()"><b>Modificar<br>Autorización</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goDelete()"><b>Borrar<br>Autorización</b></a>
		</TD>
				<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goInquiryInst()"><b>Consultar<br>Instituciones</b></a>
		</TD>
		
	  	<TD align="CENTER" class="TDBKG" width="20%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
 	  	</TD>
	</TR>
</TABLE> 

<%      
  } else {
%> 

<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="50%">
			<a href="javascript:goProcess()"><b>Consultar<br>Autorización</b></a>
      	</TD>
	  	<TD align="CENTER" class="TDBKG" width="50%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
	  	</TD>
	</TR>
</TABLE> 

<%      
  }
%> 


<% 
	if ( EACH300List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 	<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay Autorizaciones.</h4></div>
      </TD>
	</TR>
   	</TABLE>
<%
	}
	else {
%>    
    
  <TABLE class="tableinfo" id="dataTable">
    <TR id=trdark> 
		<td NOWRAP align="center" width="5%"><B>Sel</B></td>
		<td NOWRAP align="center" width="10%"><B>Cliente</B></td>
		<td NOWRAP align="center" width="30%"><B>Nombre</B></td>
		<td NOWRAP align="center" width="10%"><B>Cuenta</B></td>
		<TD nowrap align="center" width="10%"><B>Compañía</B></TD>
		<TD nowrap align="center" width="35%"><B>Nombre</B></TD>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="10%">
			<INPUT type="text" name="SEARCHCUN" size="10" maxlength="9" value="<%= userPO.getCusNum() %>">
				<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch('C')" width="15" height="11" 
					ALT="Ordenar y posicionarse en...">
		</td>
		<td NOWRAP align="center" width="30%"></td>
		<td NOWRAP align="center" width="10%">
			<INPUT type="text" name="SEARCHACC" size="13" maxlength="12" value="<%= userPO.getAccNum() %>">
			<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch('A')" width="15" height="11"
			 ALT="Ordenar y posicionarse en...">
		</td>
		<TD nowrap align="center" width="10%">
			<INPUT type="text" name="SEARCHCOM" size="11" maxlength="10" value="<%= userPO.getProdCode() %>">
			<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch('O')" width="15" height="11"
			 ALT="Ordenar y posicionarse en...">
		</td>
		<TD nowrap align="center" width="35%">
			<INPUT type="text" name="SEARCHNOM" size="36" maxlength="35" value="<%= userPO.getHeader10() %>">
			<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch('N')" width="15" height="11"
			 ALT="Ordenar y posicionarse en...">
		</TD>
	</TR>
 
        <%
    	  int i = 0;
          EACH300List.initRow();    
          while (EACH300List.getNextRow()) {
            EACH30001Message msgList = (EACH30001Message) EACH300List.getRecord();	
         %>              
    <TR id=trclear>
		<td NOWRAP align="center" width="5%">
           	<INPUT TYPE="radio" name="ROW"  value="<%= EACH300List.getCurrentRow()%>"  
				onClick="setKey('<%=msgList.getE01ACACUN()%>','<%=msgList.getE01ACAACC()%>','<%=msgList.getE01ACAOCD()%>')">
		</td>
		<td NOWRAP align="left" width="10%" ><%= msgList.getE01ACACUN() %></td>
		<td NOWRAP align="left" width="30%" ><%= msgList.getE01CUNDSC() %></td>
		<td NOWRAP align="center" width="10%" ><%= msgList.getE01ACAACC() %></td>
		<td NOWRAP align="center" width="10%" ><%= msgList.getE01ACAOCD() %></td>
		<td NOWRAP align="left" width="35%" ><%= msgList.getE01OCDDSC() %></td>
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
		int screen = 6;
		if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
			screen = 1;
		} 
    	if ( EACH300List.getShowPrev() ) {
  			int pos = EACH300List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH300?SCREEN="+screen+"&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getHeader11() + "&SEARCHTYPE=" + userPO.getType() + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EACH300List.getShowNext()) {
  			int pos = EACH300List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH300?SCREEN="+screen+"&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getHeader11() + "&SEARCHTYPE=" + userPO.getType() + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EACH300List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EACH300List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
