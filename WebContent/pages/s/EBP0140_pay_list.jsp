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
<jsp:useBean id= "EBP0140List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

var ok = false;

function goInquiry() {
	isCheck();
	bilnum = document.getElementById("E01BPBNUM").value;
    var pg = "";
 	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0160?SCREEN=3" + "&E01BPBNUM=" + bilnum;
 	CenterWindow(pg,900,600,2);
}

function goModify() {
	isCheck();
	if ( !ok ) {
		alert("Seleccione una Factura!");
		return;	 
	}
	document.getElementById("SCREEN").value="3";
	document.forms[0].submit();
}

function goPay() {
	isCheck();
	if ( !ok ) {
		alert("Seleccione una Factura!");
		return;	 
	}
	document.getElementById("SCREEN").value="4";
	if (!confirm("Desea confirmar/negar el pago de esta factura?")) {
		return;
	}
	document.forms[0].submit();
} 

function goPayAll() {
	document.getElementById("SCREEN").value="5";
	if (!confirm("Desea confirmar el pago para todas las facturas de la lista?")) {
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
				var I = document.forms[0].elements[n].value.indexOf("|");
				document.getElementById("E01BPBNUM").value = document.forms[0].elements[n].value.substr(0,I);
				document.getElementById("E01BPBSEQ").value = document.forms[0].elements[n].value.substr(I+1,3);		
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

<h3 align="center">Seleccion de Pagos
	<% 	if (userPO.getType().equals("C")) { 
			out.println("Clientes "); 
	 	} else { 
	 		out.println("Proveedores ");
		} 
	%>
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pay_list.jsp, EBP0140"></h3>

<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0140">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <INPUT TYPE=HIDDEN NAME="E01BPBNUM" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01BPBSEQ" VALUE="">

<%	 
	if ( EBP0140List.getNoResult() ) {
%>
	<TABLE class="tbenter"> 
		<TR>
	  		<TD align="CENTER" class="TDBKG" width="100%">
				<a href="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0140?SCREEN=1"><b>Regresar a<BR>Búsqueda</b></a>
	  		</TD>
		</TR>
	</TABLE>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      		<TD>         
      			<div align="center"> <h4 style="text-align:center">No hay facturas para su criterio de búsqueda.</h4> 
      			</div>
      		</TD></TR>
   	</TABLE>
<%
	}
	else {
%>    
	<TABLE class="tbenter"> 
		<TR>
			<TD align="CENTER" class="TDBKG" width="20%">
				<a href="javascript:goInquiry()"><b>Bill<br>Consulta</b></a>
			</TD>
			<TD align="CENTER" class="TDBKG" width="20%">
				<a href="javascript:goModify()"><b>Modificar<br>Pago</b></a>
      		</TD>
			<TD align="CENTER" class="TDBKG" width="20%">
				<a href="javascript:goPay()"><b>Confirmar/Rechazar</b></a>
			</TD>
			<TD align="CENTER" class="TDBKG" width="20%">
				<a href="javascript:goPayAll()"><b>Confirmar<br>Todo</b></a>
			</TD>
	  		<TD align="CENTER" class="TDBKG" width="20%">
				<a href="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0140?SCREEN=1"><b>Regresar a<BR>Búsqueda</b></a>
	  		</TD>
		</TR>
	</TABLE> 

  <TABLE class="tableinfo" id="dataTable">
    <TR id=trdark> 
		<td NOWRAP align="center" width="2%"><B>Sel</B></td>
		<td NOWRAP align="center" width="5%"><B>Número<BR>Interno</B></td>
		<td NOWRAP align="center" width="2%"><B>Sec</B></td>
		<td NOWRAP align="center" width="5%"><B>Estatus</B></td>
		<td NOWRAP align="center" width="5%"><B>Ref.<BR>Fact.</B></td>
		<td NOWRAP align="center" width="8%"><B>Fecha<BR>Venc.</B></td>
		<td NOWRAP align="center" width="8%"><B>Fecha<BR>Pago</B></td>
		<td NOWRAP align="center" width="15%"><B>Nombre Vendedor</B></td>
		<td NOWRAP align="center" width="15%"><B>Descripción Factura</B></td>
		<td nowrap align="center" width="8%"><B>Monto<BR>Factura</B></td>
		<td nowrap align="center" width="8%"><B>Monto<BR>de Pago</B></td>
		<td NOWRAP align="center" width="5%"><B>Factura<BR>Via</B></td>
		<td NOWRAP align="center" width="5%"><B>Forma de<BR>Pago</B></td>
		<td NOWRAP align="center" width="5%"><B>Tipo de<BR>Pago</B></td>
		<td NOWRAP align="center" width="6%"><B>Tipo de<BR>Factura</B></td>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="2%"></td>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="2%"></td>
		<td NOWRAP align="center" width="5%"></td>
		<td nowrap align="center" width="5%"></td>
		<td NOWRAP align="center" width="8%"></td>
		<td NOWRAP align="center" width="8%"></td>
		<td nowrap align="center" width="15%"></td>
		<td nowrap align="center" width="13%"></td>
		<td nowrap align="center" width="8%"></td>
		<td nowrap align="center" width="8%"></td>
		<td nowrap align="center" width="5%"></td>
		<td nowrap align="center" width="5%"></td>
		<td nowrap align="center" width="5%"></td>
		<td nowrap align="center" width="6%"></td>

	</TR>
         <%
    	  int i = 0;
          EBP0140List.initRow();    
          while (EBP0140List.getNextRow()) {
            EBP014001Message msgList = (EBP014001Message) EBP0140List.getRecord();
		%>              
    <TR id=trclear>

		<td NOWRAP align="center" width="2%"> 
            <INPUT type="radio" name="CODE" value="<%= msgList.getE01BPBNUM()%>|<%= msgList.getE01BPBSEQ()%>" >
		</td>
		<td NOWRAP align="center" width="5%" ><%= msgList.getE01BPBNUM() %></td>
		<td NOWRAP align="center" width="2%" ><%= msgList.getE01BPBSEQ() %></td>		
        <td NOWRAP align="left" width="5%" >
        	<FONT color="green"><b>
        	<% if(msgList.getE01BPPSTS().equals("W")) out.print("Pending");%>
        	<% if(msgList.getE01BPPSTS().equals("C")) out.print("Confirm");%>
        	<% if(msgList.getE01BPPSTS().equals("A")) out.print("Approved");%>
        	<% if(msgList.getE01BPPSTS().equals("R")) out.print("Rejected");%>
        	</b></FONT>
		</td>    
		<td NOWRAP align="left" width="5%" ><%= msgList.getE01BPBBIL() %></td>
		<td NOWRAP align="center" width="8%" >
			<%= datapro.eibs.master.Util.formatDate(msgList.getE01BPBPDM(),msgList.getE01BPBPDD(),msgList.getE01BPBPDY())%>
		</td>
		<td NOWRAP align="center" width="8%" >
			<FONT color="green">
			<%= datapro.eibs.master.Util.formatDate(msgList.getE01BPPPDM(),msgList.getE01BPPPDD(),msgList.getE01BPPPDY())%>
			</FONT>
		</td>
		<td NOWRAP align="left" width="15%" ><%= msgList.getE01BPBVCN() %></td>
		<td NOWRAP align="left" width="13%" ><%= msgList.getE01BPBDSC() %></td>
		<td NOWRAP align="right" width="8%" ><%= msgList.getE01BPBBAM() %></td>
		<td NOWRAP align="right" width="8%" >
			<FONT color="green"><%= msgList.getE01BPPAMT() %></FONT>
		</td>
		<td NOWRAP align="center" width="5%" >
		     <% if(msgList.getE01BPBPVI().equals("A")) out.print("ACH");%>
		     <% if(msgList.getE01BPBPVI().equals("R")) out.print("Cta. Corriente");%>
		     <% if(msgList.getE01BPBPVI().equals("G")) out.print("Gta. Contable");%>
		     <% if(msgList.getE01BPBPVI().equals("C")) out.print("Cheque");%>
		     <% if(msgList.getE01BPBPVI().equals("S")) out.print("Swift");%>
		     <% if(msgList.getE01BPBPVI().equals("P")) out.print("Caja Menor");%>
		</td>
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
		<td NOWRAP align="left" width="5%" >
		     <% if(msgList.getE01BPBPTY().equals("F")) out.print("Fijo");%>
		     <% if(msgList.getE01BPBPTY().equals("V")) out.print("Variable");%>
		</td>
		<td NOWRAP align="left" width="6%" ><%= msgList.getE01BPBTYPD() %></td>

	</TR>
       <% 
       	 i++; 
        } 
       %> 
  </TABLE>
  
<BR>

<%      
  }
%> 
</form>
</body>
</html>
