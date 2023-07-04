<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Proveedores</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet"> 

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EBP0110List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
var ok = false;

function goSearch() {
	if (document.getElementById("SEARCHCDE").value == "0") {
		alert("Please select a code or partial code to start the list!!!");
		return;	 
	}
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
		alert("Seleccione un Código");
		return;	 
	}
	document.getElementById("SCREEN").value="3";
	document.forms[0].submit();
}

function goDelete() {
	isCheck();
	if ( !ok ) {
		alert("Seleccione un Código para Borrar");
		return;	 
	}
	document.getElementById("SCREEN").value="4";
	if (!confirm("Esta seguro que desea borrar este registro?")) {
		return;
	}
	document.forms[0].submit();
} 

function isCheck() {
	var formLength= document.forms[0].elements.length;
   	ok = false;
	for(n=0;n<formLength;n++) {
     	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "CODE") {
			if (document.forms[0].elements[n].checked == true) {
				document.getElementById("E01BPVCOD").value = document.forms[0].elements[n].value;
				ok = true;
        		break;
			}
      	}
    }

}

function stopRKey(evt) {
  var evt = (evt) ? evt : ((event) ? event : null);
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
}

document.onkeypress = stopRKey; 

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

<h3 align="center">Proveedores<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="vendors_list.jsp, EBP0110"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0110">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="E01BPVCOD" VALUE="">
<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>

<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goNew()"><b>Nuevo</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goProcess()"><b>Actualizar</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goDelete()"><b>Borrar</b></a>
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
			<a href="javascript:goProcess()"><b>Consulta</b></a>
      	</TD>
	  	<TD align="CENTER" class="TDBKG" width="50%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
	  	</TD>
	</TR>
</TABLE> 


<%
  }	 
	if ( EBP0110List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center">No hay Proveedores.</h4> 
      </div>
      </TD></TR>
   	</TABLE>
<%
	}
	else {
%>    
    
  <TABLE class="tableinfo" id="dataTable">
    <TR id=trdark> 
		<td NOWRAP align="center" width="5%"><B>Sel</B></td>
		<td NOWRAP align="center" width="5%"><B>Código</B></td>
		<td NOWRAP align="center" width="25%"><B>Nombre</B></td>
		<td nowrap align="center" width="25%"><B>Dirección</B></td>
		<td NOWRAP align="center" width="10%"><B>Número Tel.r</B></td>
		<td NOWRAP align="center" width="5%"><B>País</B></td>
		<td NOWRAP align="center" width="5%"><B>Estado</B></td>
		<td nowrap align="center" width="10%"><B>Código Postal</B></td>
		<td NOWRAP align="center" width="10%"><B>Forma de Pago<BR>
		por Omisión</B></td>
		
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="5%">
			<INPUT type="text" name="SEARCHCDE" size="11" maxlength="10" value="<%= userPO.getProdCode() %>">
				<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch()" width="15" height="11"
				 ALT="Order and position in...">
		</td>
		<td NOWRAP align="center" width="25%"></td>
		<td nowrap align="center" width="25%"></td>
		<td nowrap align="center" width="10%"></td>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="5%"></td>
		<td nowrap align="center" width="10%"></td>
		<td nowrap align="center" width="10%"></td>
	</TR>
         <%
    	  int i = 0;
          EBP0110List.initRow();    
          while (EBP0110List.getNextRow()) {
            EBP011001Message msgList = (EBP011001Message) EBP0110List.getRecord();
		%>              
    <TR id=trclear>

		<td NOWRAP align="center" width="5%"> 
            <INPUT type="radio" name="CODE" value="<%= msgList.getE01BPVCOD() %>">
		</td>

		<td NOWRAP align="center" width="5%" ><%= msgList.getE01BPVCOD() %></td>
		<td NOWRAP align="left" width="20%" ><%= msgList.getE01BPVNM1() %></td>
		<td NOWRAP align="left" width="20%" ><%= msgList.getE01BPVNM3() %></td>
		<td NOWRAP align="left" width="10%" ><%= msgList.getE01BPVPHN() %></td>
		<td NOWRAP align="center" width="5%" ><%= msgList.getE01BPVCNT() %></td>
		<td NOWRAP align="left" width="5%" ><%= msgList.getE01BPVSTE() %></td>
		<td NOWRAP align="center" width="10%" ><%= msgList.getE01BPVZIP() %></td>
		<td NOWRAP align="center" width="10%" ><%= msgList.getE01BPVPVD() %></td>
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
    	if ( EBP0110List.getShowPrev() ) {
  			int pos = EBP0110List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.bap.JSEBP0110?SCREEN="+screen+"&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getProdCode() + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EBP0110List.getShowNext()) {
  			int pos = EBP0110List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.bap.JSEBP0110?SCREEN="+screen+"&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getProdCode() + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EBP0110List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EBP0110List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
