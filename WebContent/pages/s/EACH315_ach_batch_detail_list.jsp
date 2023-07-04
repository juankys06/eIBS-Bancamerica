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
<jsp:useBean id= "EACH315List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
var ok = false;
 
function goSearch() {
	if (document.getElementById("SEARCHCDE").value == "") {
		alert("Favor digitar un numero de transacción para posicionarse en la lista!!!");
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
		alert("Favor seleccionar una transacción!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="3";
	document.forms[0].submit();
}

function goDelete() {
	isCheck();
	if ( !ok ) {
		alert("Favor seleccione la transacción a borrar!!!");
		return;	 
	}
	document.getElementById("SCREEN").value="4";
	if (!confirm("Desea borrar esta transacción?")) {
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

function setKey(num) {
	document.getElementById("E01ACUNUM").value = num;
}

function showOfac(fieldValue){
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + fieldValue + "&shrCategory=ALL" + "&FromRecord=0&shrAction=INQ";
	CenterWindow(page,600,500,2);
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

<h3 align="center">Transacciones para el Lote ACH Nro. <%= userPO.getIdentifier().trim() %><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="ach_batches_list.jsp, EACH315"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH315">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE="HIDDEN" name="E01ACUBTH" value="<%= userPO.getIdentifier().trim() %>">
  <INPUT TYPE=HIDDEN NAME="E01ACUNUM" VALUE="0">

<TABLE class="tbenter"> 
<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>
	<TR>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goNew()"><b>Nueva<br>Transacción</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goProcess()"><b>Modificar<br>Transacción</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="25%">
			<a href="javascript:goDelete()"><b>Borrar<br>Transacción</b></a>
		</TD>
	  	<TD align="CENTER" class="TDBKG" width="25%">
			<a href="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH310?SCREEN=1"><b>Salir</b></a>
	  	</TD>
	</TR>
<%	} else { %>
	<TR>
		<TD align="CENTER" class="TDBKG" width="50%">
			<a href="javascript:goProcess()"><b>Ver <br>Transacción</b></a>
      	</TD>
	  	<TD align="CENTER" class="TDBKG" width="50%">
			<a href="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH310?SCREEN=10"><b>Salir</b></a>
      	</TD>
	</TR>
<% 	} %>
</TABLE> 
 
<%	if ( EACH315List.getNoResult() ) { %>
 	<TABLE class="tbenter" width=100% height=30%">
 	<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay Transacciones.</h4></div>
      </TD>
	</TR>
   	</TABLE>
<%
	}
	else {
		String warnImg= "";
		String warnFlag= "";
		String ofacImg = "";
		String ofacFlag = "";
%>    
    
  <TABLE class="tableinfo" id="dataTable">
    <TR id=trdark> 
		<td NOWRAP align="center" width="5%"><B>Sel</B></td>
		<td NOWRAP align="center" width="10%"><B>Número</B></td>
		<TD nowrap align="center" width="5%"><B>TR</B></TD>
		<td NOWRAP align="center" width="10%"><B>Cuenta</B></td>
		<td NOWRAP align="center" width="25%"><B>Cliente</B></td>
		<TD nowrap align="center" width="10%"><B>Ruta</B></TD>
		<TD nowrap align="center" width="20%"><B>Cuenta de Destino</B></TD>
		<TD nowrap align="center" width="15%"><B>Monto</B></TD>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="10%">
			<INPUT type="text" name="SEARCHCDE" size="13" maxlength="12" value="<%= userPO.getProdCode() %>">
				<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch()" width="15" height="11"
				 ALT="Ordenar y posicionarse en...">
		</td>
		<td NOWRAP align="center" width="5%"></td>
		<TD nowrap align="center" width="10%"></TD>
		<TD nowrap align="center" width="25%"></TD>
		<TD nowrap align="center" width="10%"></TD>
		<TD nowrap align="center" width="20%"></TD>
		<TD nowrap align="center" width="15%"></TD>
	</TR>
 
        <%
    	  int i = 0;
          EACH315List.initRow();    
          while (EACH315List.getNextRow()) {
            EACH31501Message msgList = (EACH31501Message) EACH315List.getRecord();	
			if (msgList.getH01FLGWK2().trim().equals("W")) {
				warnImg= "<IMG border=\"0\" src=\"../images/warning01.gif\" ALT=\"Advertencias\" onClick=\"showWarnings('" + msgList.getE01ACUNUM() + "','ROW')\">";
				warnFlag = msgList.getH01FLGWK2().trim();
			} else {
				warnImg= "";
			}

			if (msgList.getH01FLGWK3().trim().equals("3")) {
					ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01ACUNUM() + "')\">";
					ofacFlag = msgList.getH01FLGWK3().trim();
			} else {
					ofacImg= "";
			}
         %>              
    <TR id=trclear>
		<td NOWRAP align="center" width="5%">
           	<INPUT TYPE="radio" name="ROW"  value="<%= EACH315List.getCurrentRow()%>" onClick="setKey('<%=msgList.getE01ACUNUM()%>')">
			<%=warnImg+ofacImg%>
		</td>
		
		<td NOWRAP align="left" width="10%" ><%= msgList.getE01ACUNUM() %></td>
		<td NOWRAP align="center" width="5%" ><%= msgList.getE01ACUCDE() %></td>
		<td NOWRAP align="left" width="10%" ><%= msgList.getE01ACUACC() %></td>
		<td NOWRAP align="left" width="25%" ><%= msgList.getE01CUNDSC() %></td>
		<td NOWRAP align="center" width="10%" ><%= msgList.getE01ACUROU() %></td>
		<td NOWRAP align="left" width="20%" ><%= msgList.getE01ACUDAC() %></td>
		<td NOWRAP align="right" width="15%" ><%= msgList.getE01ACUAMT() %></td>
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
    	if ( EACH315List.getShowPrev() ) {
  			int pos = EACH315List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH315?SCREEN=1&BATCH=" + userPO.getProdCode() + "&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getProdCode() + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EACH315List.getShowNext()) {
  			int pos = EACH315List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH315?SCREEN=1&BATCH=" + userPO.getProdCode() + "&FromRecord=" + pos + "&SEARCHCDE=" + userPO.getProdCode() + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EACH315List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EACH315List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
