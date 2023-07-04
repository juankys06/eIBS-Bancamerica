<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language = "java" %>
<%@ page import = "datapro.eibs.master.Util, java.math.BigDecimal" %>
<html>
<head>
<title>Mantenimiento Guia de Cobranza</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "collBasic" class="datapro.eibs.beans.EDL081001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT Language="Javascript">
function validate() {
document.forms[0].SCREEN.value = '1400';
}
</SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
%> 
<h3 align="center">Carta Guia de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="collection_basic.jsp,EDL0810"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01DLHCUN" size="9" maxlength="9" value="<%= collBasic.getE01DLHCUN() %>">
                <a href="javascript:GetCustomerDescId('E01DLHCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= collBasic.getE01CUSNA1() %>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Planilla :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
<%            
			  String strNroAcc = collBasic.getE01DLHNRO();
              if (collBasic.getE01DLHNRO().equals("999999999999")) {
              	strNroAcc = "NUEVA CUENTA";	  
              }
%>
              <input type="hidden" name="E01DLHNRO" size="12" maxlength="12" value="<%= collBasic.getE01DLHNRO()  %>">
              <input type="text" name="DLHNRO" size="12" maxlength="12" value="<%= strNroAcc  %>">
                
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01DLHCCY" size="4" maxlength="3" value="<%= collBasic.getE01DLHCCY() %>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Producto :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01DLHPRD" size="4" maxlength="4" value="<%= collBasic.getE01DLHPRD() %>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DLHOP1" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP1() %>" onkeypress="enterInteger()">
              <input type="text" name="E01DLHOP2" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP2() %>" onkeypress="enterInteger()">
              <input type="text" name="E01DLHOP3" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP3() %>" onkeypress="enterInteger()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">No. de Documentos :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01DLHNDO" size="3" maxlength="2" value="<%= collBasic.getE01DLHNDO() %>" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tipo Cobranza :</div>
            </td>
            <td nowrap> 
              <select name="E01DLHTCB">
		  		<option value="1" <% if (collBasic.getE01DLHTCB().equals("1")) out.print("selected"); %>>Simple</option>
                <option value="2" <% if (collBasic.getE01DLHTCB().equals("2")) out.print("selected"); %>>Garantia</option>
              </select>
            </td>
            <td nowrap> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap>
            	<INPUT type="text" name="E01DLHTAM" size="15" maxlength="15" value="<%= collBasic.getE01DLHTAM() %>" onkeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Documento:</div>
            </td>
            <td nowrap width="23%" > 
            <SELECT name="E01DLHTDO">
					<OPTION value="1"
						<% if (collBasic.getE01DLHTDO().equals("1")) out.print("selected"); %>>Letras</OPTION>
					<OPTION value="2"
						<% if (collBasic.getE01DLHTDO().equals("2")) out.print("selected"); %>>Pagares</OPTION>
					<OPTION value="3"
						<% if (collBasic.getE01DLHTDO().equals("3")) out.print("selected"); %>>Facturas</OPTION>
					<OPTION value="4"
						<% if (collBasic.getE01DLHTDO().equals("4")) out.print("selected"); %>>Otros</OPTION>
				</SELECT></td>
            <td nowrap width="25%" > 
              <div align="right">Cuenta Corriente Abono:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLHCAC" size="12" maxlength="12" value="<%= collBasic.getE01DLHCAC() %>" onkeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DLHCAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
            </td>
          </tr>
         
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Instruciones de Protesto:</div>
            </td>
            <td nowrap colspan=1> 
              <select name="E01DLHTPO">
		  		<option value="1" <% if (collBasic.getE01DLHTPO().equals("1")) out.print("selected"); %>>Con Protesto</option>
                <option value="2" <% if (collBasic.getE01DLHTPO().equals("2")) out.print("selected"); %>>Sin Protesto</option>
                <option value="3" <% if (collBasic.getE01DLHTPO().equals("3")) out.print("selected"); %>>C/Prot Por falta de Acepatcion</option>
              </select>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Cuenta Corriente Cargo:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLHDAC" size="12" maxlength="12" value="<%= collBasic.getE01DLHDAC() %>" onkeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DLHDAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Nro. Carta Guia:</div>
            </td>
            <td nowrap colspan=3> 
              <input type="text" name="E01DLHNCG" size="12" maxlength="12" value="<%=collBasic.getE01DLHNCG()%>" onkeypress="enterInteger()">
            </td>
 
          </tr>

         </table>
      </td>
    </tr>
  </table>
 
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
 
 
</form>
</body>
</html>
