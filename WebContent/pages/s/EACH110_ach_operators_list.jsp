<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>ACH Operators</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet"> 

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EACH110List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
var ok = false;

function goSearch() {
	if (document.getElementById("SEARCHCDE").value == "") {
		alert("Favor seleccionar un Código para posicionarse en la lista!!!");
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

function isCheck() {
	var formLength= document.forms[0].elements.length;
   	ok = false;
	for(n=0;n<formLength;n++) {
     	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "CODE") {
			if (document.forms[0].elements[n].checked == true) {
				document.getElementById("E01ACOCDE").value = document.forms[0].elements[n].value;
				ok = true;
        		break;
			}
      	}
    }

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

<h3 align="center">Operadores ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="ach_operators_list.jsp, EACH110"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH110">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="E01ACOCDE" VALUE="">
<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>

<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goNew()"><b>Nuevo</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goProcess()"><b>Modificar</b></a>
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
			<a href="javascript:goProcess()"><b>Consultar</b></a>
      	</TD>
	  	<TD align="CENTER" class="TDBKG" width="50%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
	  	</TD>
	</TR>
</TABLE> 


<%
  }	 
	if ( EACH110List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay Operadores.</h4> 
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
		<td NOWRAP align="center" width="10%"><B>Código</B></td>
		<td NOWRAP align="center" width="25%"><B>Nombre</B></td>
		<td NOWRAP align="center" width="10%"><B>Tipo</B>
		</td>
		<TD nowrap align="center" width="5%"><B>Oficina</B></TD>
		<TD nowrap align="center" width="20%"><B>Dirección</B></TD>
		<td NOWRAP align="center" width="5%"><B>Estado</B></td>
		<TD nowrap align="center" width="15%"><B>Ciudad</B></TD>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="10%">
			<INPUT type="text" name="SEARCHCDE" size="11" maxlength="10" value="<%= userPO.getProdCode() %>">
				<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch()" width="15" height="11"
				 ALT="Ordenar y posicionarse en...">
		</td>
		<td NOWRAP align="center" width="25%"></td>
		<td NOWRAP align="center" width="10%"></td>
		<TD nowrap align="center" width="5%"></TD>
		<TD nowrap align="center" width="20%"></TD>
		<td NOWRAP align="center" width="5%"></td>
		<TD nowrap align="center" width="15%"></TD>
	</TR>
 
        <%
    	  int i = 0;
          EACH110List.initRow();    
          while (EACH110List.getNextRow()) {
            EACH11001Message msgList = (EACH11001Message) EACH110List.getRecord();
		%>              
    <TR id=trclear>

		<td NOWRAP align="center" width="5%"> 
            <INPUT type="radio" name="CODE" value="<%= msgList.getE01ACOCDE() %>">
		</td>

		<td NOWRAP align="left" width="10%" ><%= msgList.getE01ACOCDE() %></td>
		<td NOWRAP align="left" width="25%" ><%= msgList.getE01ACONME() %></td>
		<td NOWRAP align="center" width="10%" >
			<% if (msgList.getE01ACOTYP().equals("D")) out.print("Institución Financiera");
			    else if (msgList.getE01ACOTYP().equals("O")) out.print("Operador");
			    else if (msgList.getE01ACOTYP().equals("C")) out.print("Compañía"); %>
		</td>
		<td NOWRAP align="center" width="5%" >
			<% if (msgList.getE01ACOOCO().equals("O")) out.print("Principal");
			    else if (msgList.getE01ACOOCO().equals("C")) out.print("Sucursal"); %>
		</td>
		<td NOWRAP align="left" width="20%" ><%= msgList.getE01ACOADD() %></td>
		<td NOWRAP align="center" width="5%" ><%= msgList.getE01ACOSTE() %></td>
		<td NOWRAP align="left" width="15%" ><%= msgList.getE01ACOCIT() %></td>
		
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
    	if ( EACH110List.getShowPrev() ) {
  			int pos = EACH110List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH110?SCREEN="+screen+"&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getProdCode() + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EACH110List.getShowNext()) {
  			int pos = EACH110List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH110?SCREEN="+screen+"&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getProdCode() + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EACH110List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EACH110List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
