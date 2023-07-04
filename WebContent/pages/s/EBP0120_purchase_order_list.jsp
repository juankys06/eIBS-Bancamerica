<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>BAP Ordenes de Compra </title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EBP0120List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

var ok = false;
 
function goSearch(type) {
	if (type == 'O') {
		if (document.getElementById("SEARCHORD").value == "") {
			alert("Entre un Número de Orden de Compra para comenzar la lista!");
			return;
		}
		document.getElementById("SEARCHCDE").value = document.getElementById("SEARCHORD").value;
	}
	if (type == 'V') {
		if (document.getElementById("SEARCHVEN").value == "") {
			alert("Entre un Código de Proveedor para comenzar la lista!");
			return;
		}
		document.getElementById("SEARCHCDE").value = document.getElementById("SEARCHVEN").value;
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
		alert("Please Select an Order!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="3";
	document.forms[0].submit();
}

function goDelete() {
	isCheck();
	if ( !ok ) {
		alert("Please Select an Order to delete!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="4";
	if (!confirm("Do you want to delete this Order?")) {
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

function setKey(order) {
	document.getElementById("E01BPONUM").value = order;
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

<h3 align="center">Purchase Orders List<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="purchase_order_list.jsp, EBP0120">
</h3>

<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0120">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE="HIDDEN" name="E01BPONUM" value="0">
  <INPUT TYPE="HIDDEN" name="SEARCHCDE" value="">
  <INPUT TYPE="HIDDEN" name="SEARCHTYPE" value="O">
<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>

<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goNew()"><b>Nueva<br>Orden</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goProcess()"><b>Actualizar<br>Orden</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goDelete()"><b>Borrar<br>Orden</b></a>
		</TD>
	  	<TD align="CENTER" class="TDBKG" width="25%">
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
			<a href="javascript:goProcess()"><b>Consulta<br>Orden de Compra</b></a>
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
	if ( EBP0120List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 	<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center">No hay órdenes.</h4></div>
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
		<td NOWRAP align="center" width="10%"><B>No. Orden</B></td>
		<td NOWRAP align="center" width="10%"><B>Proveedor</B></td>
		<td NOWRAP align="center" width="30%"><B>Nombre</B></td>
		<TD nowrap align="center" width="10%"><B>Moneda</B></TD>
		<TD nowrap align="center" width="35%"><B>Monto</B></TD>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="10%">
			<INPUT type="text" name="SEARCHORD" size="10" maxlength="9" value="<%= userPO.getAccNum() %>" >
				<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch('O')" width="15" height="11" 
					ALT="Posicionar en Orden...">
		</td>
		<td NOWRAP align="center" width="30%">
			<INPUT type="text" name="SEARCHVEN" size="10" maxlength="9" value="<%= userPO.getCusNum() %>">
				<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch('V')" width="15" height="11"
					alt="Posicionar en Proveedor...">
		</td>
		<td NOWRAP align="center" width="10%"></td>
		<td NOWRAP align="center" width="10%"></td>
		<td NOWRAP align="center" width="10%"></td>
 	</TR>
 
        <%
    	  int i = 0;
          EBP0120List.initRow();    
          while (EBP0120List.getNextRow()) {
            EBP012001Message msgList = (EBP012001Message) EBP0120List.getRecord();	
         %>              
    <TR id=trclear>
		<td NOWRAP align="center" width="5%">
           	<INPUT TYPE="radio" name="ROW"  value="<%= EBP0120List.getCurrentRow()%>"  
				onClick="setKey('<%=msgList.getE01BPONUM()%>')">
		</td>
		<td NOWRAP align="left" width="10%" ><%= msgList.getE01BPONUM() %></td>
		<td NOWRAP align="center" width="10%" ><%= msgList.getE01BPOVCO() %></td>
		<td NOWRAP align="left" width="35%" ><%= msgList.getE01BPVNM1() %></td>
        <td NOWRAP align="left" width="35%" ><%= msgList.getE01BPOCCY() %></td>
		<td NOWRAP align="center" width="10%" ><%= msgList.getE01BPODCA() %></td>
		<td NOWRAP align="left" width="30%" ><%= msgList.getE01BPOVAC() %></td>
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
    	if ( EBP0120List.getShowPrev() ) {
  			int pos = EBP0120List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.bap.JSEBP0120?SCREEN="+screen+"&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getHeader11() + "&SEARCHTYPE=" + userPO.getType() + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EBP0120List.getShowNext()) {
  			int pos = EBP0120List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.bap.JSEBP0120?SCREEN="+screen+"&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getHeader11() + "&SEARCHTYPE=" + userPO.getType() + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EBP0120List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EBP0120List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
