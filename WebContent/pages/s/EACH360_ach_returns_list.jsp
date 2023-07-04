<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>ACH Parameters</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EACH360List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function goInquiry(op,acc) {
	var pg = "";
  	switch (op) {
		case 1 :  // Account Inquiry
		  	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=400&ACCNUM=" + acc;
			break;
		case 2 :  // Customer Accounts
		  	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=1&opt=1";
			break;
	}
	CenterWindow(pg,600,500,2);
}

function goAccept() {
	var ok = confirm("Los retornos seleccionados serán marcados o desmarcados para aceptar ignorando las advertencias");
	if (!ok) return;
	document.getElementById("SCREEN").value="2"; 
	document.forms[0].submit();
}

function goDelete() {
	var ok = confirm("Los retornos seleccionados serán marcados o desmarcados para borrar");
	if (!ok) return;
	document.getElementById("SCREEN").value="4";
	document.forms[0].submit();
}

function goProcess() {
	var ok = confirm("Desea procesar los retornos marcados para aceptar y borrar?");
	if (!ok) return;
	document.getElementById("SCREEN").value="5"; 
	document.forms[0].submit();
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

<h3 align="center">Retornos ACH Pendientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="ach_returns_list.jsp, EACH360"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH360">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">

<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>
 
<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goInquiry(2,'0')"><b>Consultar<br>Cuentas</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goAccept()"><b>Aceptar/Enviar<br>Selección</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goDelete()"><b>Borrar/Enviar<br>Selección</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="20%">
			<a href="javascript:goProcess()"><b>Procesar<br>Actualizaciones</b></a>
		</TD>
	  	<TD align="CENTER" class="TDBKG" width="20%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
      		</a>
	  	</TD>
	</TR>
</TABLE> 

<%      
  }
%>     


<% 
	if ( EACH360List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay retornos.</h4> 
      </div>
      </TD></TR>
   	</TABLE>
<%
	}
	else {
%>    
    
  <TABLE class="tableinfo" id="dataTable">
    
    <TR id=trdark>
		<td NOWRAP align="center" width="2%"><b>Sel</b></td>
		<td NOWRAP align="center" width="5%"><b>Retorno<br>Nro.</b></td>
		<td NOWRAP align="center" width="5%"><b>Estado</b></td>
		<td NOWRAP align="center" width="8%"><b>Fecha<br>Valor</b></td>
		<td NOWRAP align="center" width="10%"><b>Cliente<br>Nro</b></td>
		<td NOWRAP align="center" width="15%"><b>Nombre</b></td>
		<td NOWRAP align="center" width="10%"><b>Cuenta</b></td>
		<td NOWRAP align="center" width="10%"><b>Monto</b></td>
		<td NOWRAP align="center" width="15%"><b>Transacción</b></td>
		<td NOWRAP align="center" width="20%"><b>Causal</b></td>
	</TR>
    
        <%
    	  int i = 0;
          EACH360List.initRow();    
          while (EACH360List.getNextRow()) {
               EACH36001Message msgList = (EACH36001Message) EACH360List.getRecord();		 	 
         %>              
    <TR id=trclear>

		<td NOWRAP align="center" width="2%" >
			<INPUT type="checkbox" name="E01ACT_<%= EACH360List.getCurrentRow() %>" 
					<% if (userPO.getPurpose().equals("INQUIRY") || userPO.getPurpose().equals("APPROVAL"))           	
              	  		out.print(" disabled"); %>>
		</td>

		<td NOWRAP align="center" width="5%" >
			<INPUT type="text" name="E01ACRNUM_<%= EACH360List.getCurrentRow() %>" size="3" 
				value='<%= msgList.getE01ACRNUM()%>' readonly >
		</td>
		<td NOWRAP align="left" width="5%" >
			<% if (msgList.getE01ACRSTS().equals("P")) out.print("ENVIAR");
			    else if (msgList.getE01ACRSTS().equals("D")) out.print("<FONT color=\"red\">BORRAR</FONT>");
			    else if (msgList.getE01ACRSTS().equals("F")) out.print("<FONT color=\"green\">ACEPTAR</FONT>"); 
				else if (msgList.getE01ACRSTS().equals("S")) out.print("ENVIADO"); 
				else if (msgList.getE01ACRSTS().equals("*")) out.print("REPROCESADO"); 
			%>
		</td>
		
		<td NOWRAP align="center" width="8%" >
			<%= Util.formatDate(msgList.getE01ACRVDD(),msgList.getE01ACRVDM(),msgList.getE01ACRVDY())%>
		</td>

		<td NOWRAP align="center" width="10%" ><%= msgList.getE01ACRCUN() %></td>
		<td NOWRAP align="left" width="15%" ><%= msgList.getE01CUNDSC() %></td>
		<TD nowrap align="left" width="10%" >
			<a href="javascript:goInquiry(1,'<%= msgList.getE01ACRDAC() %>')" ><%= msgList.getE01ACRDAC() %></a>
		</TD>
		<td NOWRAP align="right" width="10%" ><%= msgList.getE01ACRAMT() %></td>
		<td NOWRAP align="left" width="15%" ><%= msgList.getE01ACRCDE() %><br><%= msgList.getE01CDEDSC() %></td>
		<td NOWRAP align="left" width="20%" ><%= msgList.getE01ACRRTR() %><br><%= msgList.getE01RTRDSC() %></td>

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
    	if ( EACH360List.getShowPrev() ) {
  			int pos = EACH360List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH360?SCREEN=1&FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EACH360List.getShowNext()) {
  			int pos = EACH360List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH360?SCREEN=1&FromRecord=" + pos +  "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EACH360List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EACH360List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
