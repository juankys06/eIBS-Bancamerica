<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head> 
<title>Front Office - Transaferencias Externas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 
<jsp:useBean id="msgCust" class="datapro.eibs.beans.EFO012002Message" scope="session" />   
<jsp:useBean id="msgCustA" class="datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>

<SCRIPT Language="Javascript">
<!--- hide script from old browsers

function fromAcc(acc,ccy,dsc) {
	document.getElementById("E02FESDAC").value=acc;
	document.getElementById("E02FESDCY").value=ccy;
	document.getElementById("E02FESDAD").value=dsc;
	document.getElementById("E02FESSBT").value='P';
}

 //  Process according with user selection
 function goAction(op) {
	
	var cun = "0";
	var pg = "";
    cun = document.getElementById("E02FESCUN").value;
	var formLength= document.forms[0].elements.length;
    var ok = false;
 	
	for(n=0;n<formLength;n++) {
     	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "E02FFEE") {
			if (document.forms[0].elements[n].checked == true) {
				ok = true;
        		break;
			}
      	}
    }
	if ( !ok && !(op == 3)) {
		alert("Favor seleccionar quién paga los cargos.");
		return;	 
	}
	if (document.getElementById("E02FESAMN").value <= 0 && !(op == 3)) {
		alert("Favor ingresar Monto.");
		return;	 
	}
    document.getElementById("DEBITACC").value=document.getElementById("E02FESDAC").value;
    document.getElementById("CREDITACC").value=document.getElementById("E02FESCAC").value;
   	switch (op){
	// Validate & Generate Transfer
  	case 2:  {
    	document.forms[0].SCREEN.value = '4';
		document.forms[0].submit();
       	break;
        }
	// Return to Previus Screen
	case 3:  {
    	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120?SCREEN=1&CUSTOMER=" + cun;
		window.location.href=pg;
        break;
        }
	// Validate & get rate 
  	default:  {
		document.forms[0].SCREEN.value = '3';
		document.forms[0].submit();
       	break;
		}

    }  
 }
// end hiding from old browsers -->
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
<h3>From Office - Transferencias Externas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="front_office_TR_External.jsp,EFO0120"></h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120">
<INPUT type="hidden" name="SCREEN" value="3">
<INPUT type="hidden" name="OPTION" value="05">
<INPUT type="hidden" name="CUSTOMER" value="<%= msgCust.getE02FESCUN().trim()%>">
<INPUT type="hidden" name="DEBITACC" value="<%= msgCust.getE02FESDAC().trim()%>">
<INPUT type="hidden" name="CREDITACC" value="<%= msgCust.getE02FESCAC().trim()%>">
<INPUT type="hidden" name="E02FESREF" value="<%= msgCust.getE02FESREF().trim()%>">
<INPUT type="hidden" name="E02FESDXR" value="<%= msgCust.getE02FESDXR().trim()%>">
<INPUT type="hidden" name="E02FESCXR" value="<%= msgCust.getE02FESCXR().trim()%>">
<INPUT type="hidden" name="E02FESEAM" value="<%= msgCust.getE02FESEAM().trim()%>">
<INPUT type="hidden" name="E02FESDAP" value="<%= msgCust.getE02FESDAP().trim()%>">
<INPUT type="hidden" name="E02FESCAP" value="<%= msgCust.getE02FESCAP().trim()%>">
<INPUT type="hidden" name="E02FESPRO" value="<%= msgCust.getE02FESPRO().trim()%>">
<INPUT type="hidden" name="E02FESROC" value="<%= msgCust.getE02FESROC().trim()%>">
<INPUT type="hidden" name="E02FESROY" value="<%= msgCust.getE02FESROY().trim()%>">
<INPUT type="hidden" name="E02FESCCY" value="<%= msgCust.getE02FESCCY().trim()%>">
<INPUT type="hidden" name="E02FESCAC" value="<%= msgCust.getE02FESCAC().trim()%>">
<INPUT type="hidden" name="E02FESCAD" value="<%= msgCust.getE02FESCAD().trim()%>">
<INPUT type="hidden" name="E02FESSBT" value="<%= msgCust.getE02FESSBT().trim()%>">

<TABLE class="tbenter" width="100%">
 	<tr id="trintrot"> 
    	<td colspan="7"><%=currUser.getH01USR()%></td>
        <td width="20%"> 
          <div align="right"><%=datapro.eibs.master.Util.formatDate(currUser.getE01RDM(),
							currUser.getE01RDD(), currUser.getE01RDY())%></div>
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
				<TR id="trdark">
					<TD nowrap align="right" width="25%"><B>Número de Cliente :</B></TD>
					<TD nowrap width="30%">
						<INPUT type="text" readonly name="E02FESCUN" size="15" maxlength="10" value="<%= msgCust.getE02FESCUN().trim()%>">
					</TD>
					<TD nowrap align="right" width="20%"><B>Tipo - Status :</B></TD>
					<TD nowrap width="25%">
						<%if (msgCust.getE02FESCCL().equals("1"))
				out.print("JURIDICO - ");
			else if (msgCust.getE02FESCCL().equals("2"))
				out.print("PERSONAL - ");
			else
				out.print("OTRO - ");%>
						<%if (msgCust.getE02FESSTS().equals("3"))
				out.print("Lista Negra");
			else if (msgCust.getE02FESSTS().equals("1"))
				out.print("Activo");
			else if (msgCust.getE02FESSTS().equals("2"))
				out.print("Inactivo");
			else
				out.print("");%>
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap align="right" width="25%"><B>Nombre del Cliente </B>:</TD>
					<TD nowrap width="30%">
						<INPUT type="text" readonly name="E02FESNA1" size="35" value="<%= msgCust.getE02FESNA1().trim()%>"></TD>
					<TD nowrap align="right" width="20%"><B></B> </TD>
					<TD nowrap width="25%"></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap align="right" width="25%"></TD>
					<TD nowrap width="30%"></TD>
					<TD nowrap width="20%"></TD>
					<TD nowrap width="25%"></TD>
				</TR>
			</TBODY>
		</TABLE>	
				
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			<TBODY>
				<TR id="trclear">
					<TD nowrap align="right" width="25%"><B><U>Usted esta Transfiriendo </U></B></TD>
					<TD nowrap width="30%"></TD>
					<TD height="10" align="right" width="20%">Fecha de Transacción :
					</TD><TD height="10" width="25%">
						<%=datapro.eibs.master.Util.formatDate(msgCust.getE02FESVDD(), msgCust.getE02FESVDM(), msgCust.getE02FESVDY())%> 
						<%=currUser.getE01DTF()%>
					</TD>
				</TR>
				<TR id="trdark">
					<TD height="10" width="25%" align="right">Desde :</TD>
					<TD height="10" align="left" width="30%">
						<INPUT type="text" name="E02FESDCY" size="4" value="<%= msgCust.getE02FESDCY().trim()%>" readonly>
						<INPUT type="text" name="E02FESDAC" size="13" value="<%= msgCust.getE02FESDAC().trim()%>" readonly>
						<INPUT type="text" readonly name="E02FESDAD" size="25" value="<%= msgCust.getE02FESDAD().trim()%>">
					</TD>
					<TD height="10" align="right" width="20%">Tasa de Cambio :
					</TD><TD height="10" width="25%" align="left">
						<INPUT style="font: bold" type="text" readonly name="E02FESDEX" size="15" maxlength="10" value="<%= msgCust.getE02FESDEX().trim()%>">
					</TD>
				</TR>
				<TR id="trclear">
					<TD height="10" width="25%" align="right">Valor a Transferir :</TD>
					<TD height="10" align="left" width="30%">
						<INPUT type="text" name="E02FESTCY" size="4" value="<%= msgCust.getE02FESTCY().trim()%>">
							<a href="javascript:GetCurrency('E02FESTCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0">
							</a> 
						<INPUT type="text" name="E02FESAMN" size="15" maxlength="15" value="<%= msgCust.getE02FESAMN().trim()%>" onkeypress="enterDecimal()">
					</TD>
					<TD height="10" align="right" width="20%">Valor Equivalente : </TD><TD height="10" width="25%">
						<INPUT style="font: bold" type="text" readonly name="E02FESDAM" size="15" maxlength="10" value="<%= Util.fcolorCCY(msgCust.getE02FESDAM().trim())%>">
						<%= msgCust.getE02FESDCY().trim()%>
					</TD>
				</TR>
				<TR id="trdark">
					<TD height="10" width="25%" align="right">Cargos : 

					</TD>
					<TD height="10" align="left" width="30%" valign="middle">Pagados por Cliente
						<INPUT type="radio" name="E02FFEE" value="A"
							<% if (msgCust.getE02FFEE().equals("A")) out.print(" checked"); %>
						> Beneficiario
						<INPUT type="radio" name="E02FFEE" value="B" 
						<% if (msgCust.getE02FFEE().equals("B")) out.print(" checked"); %>>
					</Td>
					<TD height="10" align="right" width="20%">Tabla de Comisión : </TD>
					<TD height="10" width="25%" align="left">
						<INPUT type="text" name="E02COMTBL" size="3" maxlength="2"
							value="<%= msgCust.getE02COMTBL().trim()%>" onkeypress="enterInteger()">
                		<INPUT type="text" name="E02PRICOM" size="15" maxlength="13" value="<%= msgCust.getE02PRICOM().trim()%>"
							onkeypress="enterDecimal()" readonly>
					</TD>
				</TR>
				<TR id="trclear">
					<TD height="10" width="25%" align="right">Comentarios :</TD><TD height="10" align="left" width="30%" colspan="3"><INPUT type="text" name="E02FESREM" size="71" value="<%= msgCust.getE02FESREM().trim()%>" maxlength="70"></TD>
				</TR>
				<TR>
					<TD height="10" width="25%" align="right"></TD>
					<TD height="10" align="left" width="30%" colspan="3"></TD>
				</TR>
			</TBODY>
		</TABLE>

		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			<TBODY>
				<TR id="trdark">
					<TD nowrap align="right" width="20%"><B><U>Información del Beneficiario </U></B></TD>
					<TD nowrap width="30%"></TD>
					<TD height="10" align="right" width="20%"></TD><TD height="10" width="30%">
					</TD>
				</TR>
				<TR id="trclear">
					<TD height="10" width="20%" align="right">Número de Cuenta :</TD>
					<TD height="10" align="left" width="30%">
					<INPUT type="text" name="E02BNFACC" size="25" value="<%= msgCust.getE02BNFACC().trim()%>" maxlength="20">
						<a href="javascript:GetBeneficiary('<%= msgCust.getE02FESCUN().trim()%>','E02BNFACC','E02BNFAD1','E02BNFAD2','E02BNFAD3','E02BNKID','E02BNKAD1','E02BNKAD2','E02BNKAD3','E02BNKAD4','E02IBNKID','E02INTAD1','E02INTAD2','E02INTAD3','E02INTAD4')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0">
						</a>
					</TD>
					<TD height="10" align="left" width="20%"><IMG
						src="<%=request.getContextPath()%>/images/Check.gif"
						alt="obligatorio" align="bottom"></TD>
					<TD height="10" width="30%" align="left">
					</TD>
				</TR>
				<TR id="trdark">
					<TD height="10" width="20%" align="right">Nombre  :</TD>
					<TD height="10" align="left" width="30%">
					<INPUT type="text" name="E02BNFAD1" size="40"
						value="<%= msgCust.getE02BNFAD1().trim()%>" maxlength="35">
						
					</TD><TD height="10" align="left" width="20%"><IMG
						src="<%=request.getContextPath()%>/images/Check.gif"
						alt="obligatorio" align="bottom"></TD>
					<TD height="10" width="30%">
					</TD>
				</TR>
				<TR id="trclear">
					<TD height="10" width="20%" align="right">Dirección : </TD>
					<TD height="10" align="left" width="30%"><INPUT type="text"
						name="E02BNFAD2" size="40"
 						value="<%= msgCust.getE02BNFAD2().trim()%>" maxlength="35"><BR>
					<INPUT type="text" name="E02BNFAD3" size="40"
						value="<%= msgCust.getE02BNFAD3().trim()%>" maxlength="35">
						
					</Td>
					<TD height="10" align="left" width="20%"><IMG
						src="<%=request.getContextPath()%>/images/Check.gif"
						alt="obligatorio" align="bottom"></TD>
					<TD height="10" width="30%" align="left"></TD>
				</TR>
				<TR id="trdark">
					<TD height="10" width="20%" align="right">  </TD>
					<TD height="10" align="left" width="30%">
					</TD>
					<TD height="10" align="right" width="20%"> Referencia :</TD>
					<TD height="10" width="30%" align="left"><INPUT type="text"
						name="E02DTP1" size="40" value="<%= msgCust.getE02DTP1().trim()%>"
						maxlength="35"><BR>
					<INPUT type="text" name="E02DTP2" size="40"
						value="<%= msgCust.getE02DTP2().trim()%>" maxlength="35">
					<BR>
					<INPUT type="text" name="E02DTP3" size="40"
						value="<%= msgCust.getE02DTP3().trim()%>" maxlength="35"></TD>
				</TR>
				<TR id="trclear">
					<TD height="10" align="right" width="20%"><B><U>Banco del Beneficiario</U></B></TD>
					<TD height="10" align="left" width="30%"></TD>
					<TD height="10" align="right" width="20%">
					<B><U>Banco Intermediario (Opcional)</U></B></TD>
					<TD height="10" width="30%" align="left"></TD>
				</TR>
				<TR id="trdark">
					<TD height="10" align="right" width="20%">Código : </TD>
					<TD height="10" align="left" width="30%"><INPUT type="text"
						name="E02BNKID" size="15"
						value="<%= msgCust.getE02BNKID().trim()%>" maxlength="12">
						<a href="javascript:GetSwiftAll('E02BNKID','E02BNKAD1','E02BNKAD2','E02BNKAD3','E02BNKAD4')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Swift" align="middle" border="0">
						</a>
						<a href="javascript:GetAbaAll('E02BNKID','E02BNKAD1','E02BNKAD2','E02BNKAD3','E02BNKAD4')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Código ABA" align="middle" border="0">
						</a>
						<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="obligatorio" align="bottom">
					</TD>
					<TD height="10" align="right" width="20%">
					 Código : </TD>
					<TD height="10" align="left" width="30%">
						<INPUT type="text" name="E02IBNKID" size="15" value="<%= msgCust.getE02IBNKID().trim()%>" maxlength="12">
						<a href="javascript:GetSwiftAll('E02IBNKID','E02INTAD1','E02INTAD2','E02INTAD3','E02INTAD4')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Swift" align="middle" border="0">
						</a>
						<a href="javascript:GetAbaAll('E02IBNKID','E02INTAD1','E02INTAD2','E02INTAD3','E02INTAD4')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Código ABA" align="middle" border="0">
						</a>
					</TD>
				</TR>
				<TR id="trclear">
					<TD height="10" align="right" width="20%">Nombre  : </TD>
					<TD height="10" align="left" width="30%">
						<INPUT type="text" name="E02BNKAD1" size="40" value="<%= msgCust.getE02BNKAD1().trim()%>" maxlength="35">
					</TD>
					<TD height="10" align="right" width="20%">Nombre  : </TD>
					<TD height="10" align="left" width="30%">
						<INPUT type="text" name="E02INTAD1" size="40" value="<%= msgCust.getE02INTAD1().trim()%>" maxlength="35">
					</TD>
				</TR>
				<TR id="trdark">
					<TD height="10" align="right" width="20%">Dirección : </TD>
					<TD height="10" align="left" width="30%">
						<INPUT type="text" name="E02BNKAD2" size="40" value="<%= msgCust.getE02BNKAD2().trim()%>" maxlength="35"><BR>
						<INPUT type="text" name="E02BNKAD3" size="40" value="<%= msgCust.getE02BNKAD3().trim()%>" maxlength="35"><BR>
						<INPUT type="text" name="E02BNKAD4" size="40" value="<%= msgCust.getE02BNKAD4().trim()%>" maxlength="35">
					</TD>
					<TD height="10" align="right" width="20%">Dirección : </TD>
					<TD height="10" align="left" width="30%">
						<INPUT type="text" name="E02INTAD2" size="40" value="<%= msgCust.getE02INTAD2().trim()%>" maxlength="35"><BR>
						<INPUT type="text" name="E02INTAD3" size="40" value="<%= msgCust.getE02INTAD3().trim()%>" maxlength="35"><BR>
						<INPUT type="text" name="E02INTAD4" size="40" value="<%= msgCust.getE02INTAD4().trim()%>" maxlength="35">
					</TD>
				</TR>
			</TBODY>
		</TABLE>


		</td>
	</tr>
</table>

<table width="100%">		
  	<tr>
  		<td width="33%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Getrate" value="Validar" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
		<td width="34%">
			<%if (msgCust.getE02FESREF().equals("999999999999")) {%>
  		  		<div align="center">
			 		<input id="EIBSBTN" type="button" name="Submit" value="Aceptar" onClick="javascript:goAction(2);">
     	  	 	</div>	
				<%msgCust.setE02FESREF("0");
			}
		%>
  		</td>
  		<td width="33%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Return" value="Retornar" onClick="javascript:goAction(3);">
     	  </div>	
  		</td>
  	</tr>	
</table>	


<h4>Cuentas del Cliente</h4>

<TABLE id="mainTable" class="tableinfo" ALIGN=CENTER>
	<TR>
		<TD NOWRAP valign="top" width="100%">
		<TABLE id="headTable">
			<TR id="trdark">
				<TH ALIGN=CENTER NOWRAP>Desde</TH>
				<TH ALIGN=CENTER NOWRAP>Descripción</TH>
				<TH ALIGN=CENTER NOWRAP>Número</TH>
				<TH ALIGN=CENTER NOWRAP>Customer<br>
				Number</TH>
				<TH ALIGN=CENTER NOWRAP>Customer Name</TH>
				<TH ALIGN=CENTER NOWRAP>Moneda</TH>
				<TH ALIGN=CENTER NOWRAP>Saldo<br>
				Disponible</TH>
			</TR>
		</TABLE>
		<div id="dataDiv1" class="scbarcolor">
		<table id="dataTable">
			<%msgCustA.initRow();
			int k = 1;
			while (msgCustA.getNextRow()) {
				if (msgCustA.getFlag().equals("")) {
					out.println(msgCustA.getRecord());
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
