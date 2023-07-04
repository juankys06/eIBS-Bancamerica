<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Aprobación de Pagos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet"> 

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EBP0145List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

var ok = false;

function goInquiry() {
	isCheck();
	bilnum = document.getElementById("E01BPPNUM").value;
    var pg = "";
 	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0160?SCREEN=3" + "&E01BPBNUM=" + bilnum;
 	CenterWindow(pg,900,600,2);
}

function goApproval() {
	isCheck();
	if ( !ok ) {
		alert("Seleccione un Pago!");
		return;	 
	}
	if (!confirm("Desea aprobar este pago?")) {
		return;
	}
	document.getElementById("SCREEN").value="2";
	document.forms[0].submit();
}

function goReject() {
	isCheck();
	if ( !ok ) {
		alert("Seleccione un Pago!");
		return;	 
	}
	if (!confirm("Desea rechazar este pago?")) {
		return;
	}
	document.getElementById("SCREEN").value="3";
	document.forms[0].submit();
} 

function isCheck() {
	var formLength= document.forms[0].elements.length;
   	ok = false;
	for(n=0;n<formLength;n++) {
     	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "CODE") {
			if (document.forms[0].elements[n].checked == true) {
				var I = document.forms[0].elements[n].value.indexOf("|");
				document.getElementById("E01BPPNUM").value = document.forms[0].elements[n].value.substr(0,I);
				document.getElementById("E01BPPSEQ").value = document.forms[0].elements[n].value.substr(I+1,3);		
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

<h3 align="center">
	<% 	if (userPO.getType().equals("C")) { 
			out.println("Customer "); 
	 	} else { 
	 		out.println("Vendor ");
		} 
	%>Payments Approval
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="payment_approval_list.jsp, EBP0145"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0145">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <INPUT TYPE=HIDDEN NAME="E01BPPNUM" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01BPPSEQ" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01BPBNUM" VALUE="">
  <INPUT TYPE=HIDDEN name="TOTROWS" value="0">
  <INPUT TYPE=HIDDEN name="NEXTROWS" value="0">
  <INPUT TYPE=HIDDEN name="CURRROWS" value="0">
    
<table  class="tbenter" width="100%">
	<tr bordercolor="#FFFFFF"> 
		<td nowrap> 
<%	 
	if ( EBP0145List.getNoResult() ) {
%>
	<TABLE class="tbenter" width="100%"> 
		<TR>
	  		<TD align="center" class="TDBKG" width="100%" nowrap><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></TD>
	  	
		</TR>
	</TABLE>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      		<TD>         
      			<div align="center"> <h4 style="text-align:center">No hay pagos para aprobar.</h4> 
      			</div>
      		</TD></TR>
   	</TABLE>
<%
	}
	else {
%>    
	<TABLE class="tbenter" width="100%"> 
		<TR>
			<TD align="CENTER" class="TDBKG" width="25%"><a href="javascript:goInquiry()"><b>Consultar<br>Facturas</b></a></TD>
			<TD align="CENTER" class="TDBKG" width="25%"><a href="javascript:goApproval()"><b>Aprobar</b></a></TD>
			<TD align="CENTER" class="TDBKG" width="25%"><a href="javascript:goReject()"><b>Rechazar</b></a></TD>
	  		<TD align="CENTER" class="TDBKG" width="25%"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></TD>
		</TR>
	</TABLE> 

  <TABLE class="tableinfo" id="dataTable" width="100%">
    <TR id=trdark> 
		<td nowrap align="center" width="2%"><B>Sel</B></td>
		<td nowrap align="center" width="5%"><B>Número<BR>Interno</B></td>
		<td nowrap align="center" width="5%"><B>Secuencia</B></td>
		<td nowrap align="center" width="5%"><B>Referencia<BR>Factura</B></td>
		<td nowrap align="center" width="8%"><B>Fecha<BR>de Pago</B></td>
		<td nowrap align="center" width="15%"><B>Nombre Proveedor</B></td>
		<td nowrap align="center" width="15%"><B>Descripcion Factura</B></td>
		<td nowrap align="center" width="8%"><B>Monto<BR>Pago</B></td>
		<td nowrap align="center" width="8%"><B>Monto<BR>Comisión</B></td>
		<td nowrap align="center" width="5%"><B>Forma de<BR>Pago</B></td>
		<td nowrap align="center" width="6%"><B>Tipo de<BR>Factura</B></td>
	</TR>
    <TR id=trdark>
		<td nowrap align="center" width="2%"></td>
		<td nowrap align="center" width="5%"></td>
		<td nowrap align="center" width="5%"></td>
		<td nowrap align="center" width="5%"></td>
		<td nowrap align="center" width="8%"></td>
		<td nowrap align="center" width="15%"></td>
		<td nowrap align="center" width="15%"></td>
		<td nowrap align="center" width="8%"></td>
		<td nowrap align="center" width="8%"></td>
		<td nowrap align="center" width="5%"></td>
		<td nowrap align="center" width="6%"></td>

	</TR>
         <%
    	  int i = 0;
          EBP0145List.initRow();    
          while (EBP0145List.getNextRow()) {
            EBP014501Message msgList = (EBP014501Message) EBP0145List.getRecord();
		%>              
    <TR id=trclear>

		<td NOWRAP align="center" width="2%"> 
            <INPUT type="radio" name="CODE" value="<%= msgList.getE01BPPNUM()%>|<%= msgList.getE01BPPSEQ()%>" >
		</td>
		<td NOWRAP align="center" width="5%" ><%= msgList.getE01BPPNUM() %></td>
        <td NOWRAP align="center" width="5%" ><%= msgList.getE01BPPSEQ() %></td>    
		<td NOWRAP align="left" width="5%" ><%= msgList.getE01BPBBIL() %></td>
		<td NOWRAP align="center" width="8%" ><FONT color="green"><%= datapro.eibs.master.Util.formatDate(msgList.getE01BPPPDM(),msgList.getE01BPPPDD(),msgList.getE01BPPPDY())%>	</FONT>
		</td>
		<td NOWRAP align="left" width="15%" ><%= msgList.getE01BPPVCN() %></td>
		<td NOWRAP align="left" width="15%" ><%= msgList.getE01BPBDSC() %></td>
		
		<td NOWRAP align="right" width="8%" ><FONT color="green"><%= msgList.getE01BPPAMT() %></FONT></td>
		<td NOWRAP align="right" width="8%" ><FONT color="green"><%= msgList.getE01BPPFEE() %></FONT></td>
		
		<td NOWRAP align="center" width="5%" >
			<FONT color="green">
		     <% if(msgList.getE01BPPPVI().equals("A")) out.print("ACH");%>
		     <% if(msgList.getE01BPPPVI().equals("R")) out.print("Cta. Corriente");%>
		     <% if(msgList.getE01BPPPVI().equals("G")) out.print("Cta. Contable");%>
		     <% if(msgList.getE01BPPPVI().equals("C")) out.print("Cheque");%>
		     <% if(msgList.getE01BPPPVI().equals("S")) out.print("Swift");%>
		     <% if(msgList.getE01BPPPVI().equals("P")) out.print("Caja Menor");%>
		    </FONT> 
		</td>
		<td NOWRAP align="left" width="6%" ><%= msgList.getE01BPBTYPD() %></td>

	</TR>
       <% 
       	 i++; 
        } 
       %> 
  </TABLE>
  
<BR>

	<SCRIPT Language="javascript">
		document.forms[0].TOTROWS.value = <%= i%>;
		document.forms[0].NEXTROWS.value = <%= EBP0145List.getLastRec()%>;
		document.forms[0].CURRROWS.value = <%= EBP0145List.getFirstRec()%>;
	</SCRIPT>
<%      
  }
%> 
		</TD>
	</TR>
</TABLE>
</form>
</body>
</html>
