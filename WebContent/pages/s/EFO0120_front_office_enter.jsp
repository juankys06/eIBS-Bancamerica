<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head>
<title>Front Office - Seleccion de Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 
<jsp:useBean id="msgCust" class="datapro.eibs.beans.EFO012001Message" scope="session" /> 
<jsp:useBean id="fieldList" class="datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<SCRIPT Language="Javascript">

  function goAction(op) {
	
	var cun = "0";
	var opt = "01";
	var pg = "";
    cun = document.getElementById("E01CUSCUN").value;
	var formLength= document.forms[0].elements.length;
    var ok = false;
 	
	for(n=0;n<formLength;n++) {
     	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "CINPTOPT") {
			if (document.forms[0].elements[n].checked == true) {
				ok = true;
        		break;
			}
      	}
    }
    if ( ok ) {	
		opt = document.getElementById("INPTOPT").value;
	}
	switch (op){
	case 1:
	case 2:
  	case 3:
  	case 4:
	case 5:  {
		if ( ok ) {
    			pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120?SCREEN=2&CUSTOMER=" + cun + "&OPTION=" + opt;
    			window.location.href=pg;
        		break;
    		} else {
				alert("Por favor seleccione el tipo de Transacción.");
				break;	 
			}
		}
   	default:  {
    	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0010?SCREEN=1&TYPE=FRONT_OFFICE";
		window.location.href=pg;
        break;
        }
    }  
  }


// Consulta de Cuentas
 function showAccount(acc) {
	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0088?SCREEN=600&PORTFOLIO=" + acc;
	CenterWindow(pg,600,500,2);
 }

</SCRIPT>


</head>
<body>
<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>
<div align="center">
<h3>Front Office - Selección de Transacción<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="front_office_enter.jsp,EFO0120"></h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120">
<INPUT type="hidden" name="SCREEN" value="2">
<INPUT type=HIDDEN name="INPTOPT" value="01">

<TABLE class="tbenter" width="100%">
 	<tr id="trintrot"> 
    	<td colspan="7"><%= currUser.getH01USR()%></td>
        <td width="20%"> 
          <div align="right"><%= datapro.eibs.master.Util.formatDate(currUser.getE01RDM(),currUser.getE01RDD(),currUser.getE01RDY())%></div>
        </td>
    </tr>    
	<TR> 
	</TR>
</TABLE>

<table class="tableinfo">
	<tr>
		<td nowrap width="100%">
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			<TBODY>
				<TR id="trintro">
					<TD nowrap align="right" width="25%"><B>Número de Cliente :</B></TD>
					<TD nowrap width="25%">
						<INPUT type="text" readonly name="E01CUSCUN" size="10" value="<%= msgCust.getE01CUSCUN().trim()%>">
					</TD>
					<TD nowrap align="right" width="25%"><B>Tipo - Status :</B></TD>
					<TD nowrap width="25%">
						<%if (msgCust.getE01CUSLGT().equals("1")) out.print("JURIDICO - ");
									else if (msgCust.getE01CUSLGT().equals("2")) out.print("PERSONAL - ");
									else out.print("OTRO - ");%>
						<%if (msgCust.getE01CUSSTS().equals("3")) out.print("Lista Negra");
									else if (msgCust.getE01CUSSTS().equals("1")) out.print("Activo");
									else if (msgCust.getE01CUSSTS().equals("2")) out.print("Inactivo");
									else out.print("");%>
					</TD>
				</TR>
				<TR id="trintro">
					<TD nowrap align="right" width="25%"><B>Nombre del Cliente </B>:</TD>
					<TD nowrap width="25%">
						<INPUT type="text" readonly name="E01CUSNA1" size="35" value="<%= msgCust.getE01CUSNA1().trim()%>"></TD>
					<TD nowrap align="right" width="25%"><B>Oficial :</B> </TD>
					<TD nowrap width="25%"><INPUT type="text" readonly
						name="E01CUSOFC" size="4" value="<%= msgCust.getE01CUSOFC().trim()%>"> <INPUT
						type="text" readonly name="D01CUSOFC" size="30"
						value="<%= msgCust.getD01CUSOFC().trim()%>"></TD>
				</TR>
				<TR id="trintro">
					<TD nowrap align="right" width="25%"></TD>
					<TD nowrap width="25%"></TD>
					<TD nowrap width="25%"></TD>
					<TD nowrap width="25%"></TD>
				</TR>
				<TR id="trintro">
					<TD nowrap align="right" width="25%">
					<H4 align="center">Tipo de Transacción:</H4>
					</TD>
					<TD nowrap align="right" width="25%"></TD>
					<TD nowrap width="25%"></TD>
					<TD nowrap width="25%"></TD>
				</TR>
			</TBODY>
		</TABLE>

		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			<TBODY>
				<TR id="trintro">
<!--
// Foreign Exchange 
					<TD height="10" width="10%"></TD>
					<TD height="10" align="center" width="5%">
						<INPUT type="radio" name="CINPTOPT" value="01" onclick="document.forms[0].INPTOPT.value='01'">
					</TD>
					<TD height="10" width="15%"> FX Spot</TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="15%"></TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="15%"></TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="25%"></TD>
// End Foreign Exhange 
-->
				</TR>
				<TR id="trintro">
					<TD height="10" width="10%"></TD>
					<TD height="10" align="center" width="5%">
						<INPUT type="radio" name="CINPTOPT" value="02" onclick="document.forms[0].INPTOPT.value='02'">
					</TD>
					<TD height="10" width="15%"> Creación de Certificado de Depósito</TD>
					<TD height="10" align="center" width="5%">
						<INPUT type="radio" name="CINPTOPT" value="03" onclick="document.forms[0].INPTOPT.value='03'">
					<TD height="10" width="15%"> Renovación de Certificado de Depósito</TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="15%"></TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="25%"></TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="10%"></TD>
					<TD height="10" align="center" width="5%">
						<INPUT type="radio" name="CINPTOPT" value="04" onclick="document.forms[0].INPTOPT.value='04'">
					</TD>
					<TD height="10" width="15%"> Transferencia Interna (Mismo Cliente)</TD>
					<TD height="10" align="center" width="5%">
						<INPUT type="radio" name="CINPTOPT" value="05" onclick="document.forms[0].INPTOPT.value='05'">
					</TD>	
					<TD height="10" width="15%">Transferencia Externa</TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="15%"></TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="25%"></TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="10%"></TD>
					<TD height="10" align="center" width="5%">
					</TD>
					<TD height="10" width="15%"> </TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="15%"></TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="15%"></TD>
					<TD height="10" align="center" width="5%">
					<TD height="10" width="25%"></TD>
				</TR>

			</TBODY>
		</TABLE>
		</td>
	</tr> 
</table>

<table width="100%">		
  	<tr>
  		<td width="50%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Submit" value="Someter" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
  		<td width="50%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="NewSearch" value="Nueva Busqueda" onClick="javascript:goAction(99);">
     	  </div>	
  		</td>
  	</tr>	
</table>	

<h4> Cuentas del Cliente</h4>

<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER >
 <TR> 
    <TD NOWRAP valign="top" width="100%" >
    	<TABLE id="headTable" >
     		<TR id="trdark"> 
      			<TH ALIGN=CENTER NOWRAP>Cliente</TH>
     			<TH ALIGN=CENTER NOWRAP>Nombre</TH>
     			<TH ALIGN=CENTER NOWRAP>Oficial</TH>
     			<TH ALIGN=CENTER NOWRAP>Agencia</TH>
     			<TH ALIGN=CENTER NOWRAP>Cuenta</TH>
     			<TH ALIGN=CENTER NOWRAP>Moneda</TH>
     			<TH ALIGN=CENTER NOWRAP>Tipo</TH>
     			<TH ALIGN=CENTER NOWRAP>Producto</TH>
     			<TH ALIGN=CENTER NOWRAP>Saldo</TH>
     			<TH ALIGN=CENTER NOWRAP>Saldo en<br>Moneda Base</TH>
  	 		</TR>
		</TABLE> 
        <div id="dataDiv1" class="scbarcolor"> 
           	<table id="dataTable" >
             	<%
               		fieldList.initRow();
               		int k=1;
               		while (fieldList.getNextRow()) {
                   		if (fieldList.getFlag().equals("")) {
                   			out.println(fieldList.getRecord());
                   		k++;
                   		}        
               		}
           		%>
           	</table>
          </div>
       </td>
  	</tr>
  </table>

<SCRIPT language="JavaScript">
     function resizeDoc() {
      adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;
</SCRIPT>

</form>
</body>
</html>
