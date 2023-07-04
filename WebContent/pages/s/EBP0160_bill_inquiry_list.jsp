<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Consulta</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet"> 

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EBP0160List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

var ok = false;

function goSearch() {
	if (document.getElementById("SEARCHCDE").value == "") {
		alert("Seleccione un Código o Código Parcial para iniciar la lista!");
		return;	 
	}
	document.forms[0].submit();
}

function goInquiry() {
	isCheck();
	if (!ok) {
		alert("Seleccione una Factura!");
		return;	 
	}
	bilnum = document.getElementById("E01BPBNUM").value;
    var pg = "";
	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0160?SCREEN=3" + 
	        "&E01BPBNUM=" + bilnum;
	CenterWindow(pg,900,600,2);	
	
}

function isCheck() {
	var formLength= document.forms[0].elements.length;
   	ok = false;
	for(n=0;n<formLength;n++) {
     	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "CODE") {
			if (document.forms[0].elements[n].checked == true) {
				document.getElementById("E01BPBNUM").value = document.forms[0].elements[n].value;
				ok = true;
        		break;
			}
      	}
    }

}

</SCRIPT>

</head>

<body>



<h3 align="center"> Consulta Facturas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="bill_inqury_list.jsp, EBP0160"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0160">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <INPUT TYPE=HIDDEN NAME="E01BPBNUM" VALUE="">

<%	
	if ( EBP0160List.getNoResult() ) {
%>
	<TABLE class="tbenter"> 
		<TR>
			<TD align="CENTER" class="TDBKG" width="100%">
				<a href="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0160?SCREEN=1"><b>Regresar a <BR>Búsqueda</a>
	  		</TD>
		</TR>
	</TABLE> 
 	<TABLE class="tbenter" width="100%" height=50%">
 		<TR>
      		<TD align="center" width="100%"> 
     			<h4 style="text-align:center"> No hay facturas para su criterio de búsqueda.</h4>
    		</TD>
      	</TR>
   	</TABLE>
<%
	}
	else {
%>    
  <TABLE class="tbenter"> 
		<TR>
			<TD align="CENTER" class="TDBKG" width="33%">
				<a href="javascript:goInquiry()"><b>Consulta<br>Factura</b></a>
			</TD>
			<TD align="CENTER" class="TDBKG" width="33%">
				<a href="javascript:goModify()"><b>Consulta<br>Pago</b></a>
      		</TD>
	  		<TD align="CENTER" class="TDBKG" width="34%">
				<a href="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0160?SCREEN=1"><b>Regresar a<BR>Búsqueda</b></a>
	  		</TD>
		</TR>
	</TABLE>   
	<TABLE class="tableinfo" id="dataTable">
    	<TR id=trdark> 
			<td NOWRAP align="center" width="5%"><B>Sel</B></td>
			<td NOWRAP align="center" width="5%"><B>Número<BR>Interno</B></td>
			<td NOWRAP align="center" width="10%"><B>Referencia<BR>Factura</B></td>
			<td NOWRAP align="center" width="5%"><B>Fecha Venc.<BR>Pago</B></td>
			<td NOWRAP align="center" width="20%"><B>Nombre Proveedor</B></td>
			<td NOWRAP align="center" width="20%"><B>Descripción Factura</B></td>
			<td nowrap align="center" width="10%"><B>Monto</B></td>
			<td NOWRAP align="center" width="5%"><B>Forma de<BR>Pago</B></td>
			<td NOWRAP align="center" width="5%"><B>Tipo de<BR>Pago</B></td>
			<td NOWRAP align="center" width="5%"><B>Tipo de<BR>Factura</B></td>
			<td NOWRAP align="center" width="5%"><B>Estatus<BR>Factura</B></td>
		</TR>
    	<TR id=trdark>
			<td NOWRAP align="center" width="5%"></td>
			<td NOWRAP align="center" width="5%">
<%--
				<INPUT type="text" name="SEARCHCDE" size="10" maxlength="" value="<%= userPO.getProdCode() %>">
					<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch()" width="15" height="11"
				 	ALT="Orden y posición en...">
--%>
			</td>
			<td NOWRAP align="center" width="10%"></td>
			<td nowrap align="center" width="5%"></td>
			<td NOWRAP align="center" width="20%"></td>
			<td NOWRAP align="center" width="20%"></td>
			<td nowrap align="center" width="10%"></td>
			<td nowrap align="center" width="5%"></td>
			<td nowrap align="center" width="5%"></td>
			<td nowrap align="center" width="5%"></td>
			<td nowrap align="center" width="5%"></td>
		</TR>
         <%
    	  int i = 0;
          EBP0160List.initRow();    
          while (EBP0160List.getNextRow()) {
            EBP016001Message msgList = (EBP016001Message) EBP0160List.getRecord();
		%>              
    	<TR id=trclear>

			<td NOWRAP align="center" width="5%"> 
            	<INPUT type="radio" name="CODE" value="<%= msgList.getE01BPBNUM() %>">
			</td>
        	<td NOWRAP align="center" width="5%" ><%= msgList.getE01BPBNUM() %></td>
			<td NOWRAP align="left" width="10%" ><%= msgList.getE01BPBBIL() %></td>
			<td NOWRAP align="left" width="5%" ><%= datapro.eibs.master.Util.formatDate(msgList.getE01BPBPDM(),msgList.getE01BPBPDD(),msgList.getE01BPBPDY())%></td>
			<td NOWRAP align="left" width="20%" ><%= msgList.getE01BPBVCN() %></td>
			<td NOWRAP align="left" width="20%" ><%= msgList.getE01BPBDSC() %></td>
			<td NOWRAP align="right" width="10%" ><%= msgList.getE01BPBBAM() %></td>
			<td NOWRAP align="center" width="5%" >
		   		<% if(msgList.getE01BPBPVI().equals("A")) out.print("ACH");%>
		     	<% if(msgList.getE01BPBPVI().equals("R")) out.print("Cuenta Corriente");%>
		     	<% if(msgList.getE01BPBPVI().equals("G")) out.print("Cuenta Contable");%>
		     	<% if(msgList.getE01BPBPVI().equals("C")) out.print("Cheque Oficial");%>
		     	<% if(msgList.getE01BPBPVI().equals("S")) out.print("Swift");%>
		     	<% if(msgList.getE01BPBPVI().equals("P")) out.print("Caja Menor");%>
			</td>
			<td NOWRAP align="left" width="5%" >
		    	<% if(msgList.getE01BPBPTY().equals("F")) out.print("Monto Fijo");%>
		     	<% if(msgList.getE01BPBPTY().equals("V")) out.print("Monto Variable");%>
			</td>
			<td NOWRAP align="left" width="5%" ><%= msgList.getE01BPBTYPD() %></td>
			<td NOWRAP align="left" width="5%" >
				<% if (msgList.getE01BPBSTS().equals("W")) out.print("Pendiente");%>
	          	<% if (msgList.getE01BPBSTS().equals("A")) out.print("Activa");%>
	          	<% if (msgList.getE01BPBSTS().equals("P")) out.print("Pagada");%>
	          	<% if (msgList.getE01BPBSTS().equals("S")) out.print("Suspendida");%>
	          	<% if (msgList.getE01BPBSTS().equals("R")) out.print("Rechazada");%>	        
			</td>
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
