<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language = "java" %>
<%@ page import = "datapro.eibs.master.Util, java.math.BigDecimal" %>
<html>
<head>
<title>Draft Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "collBasic" class="datapro.eibs.beans.EDL081001Message"  scope="session" />
<jsp:useBean id= "dftBasic" class="datapro.eibs.beans.EDL081002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT Language="Javascript">

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
                <input type="text" name="E01DLHCUN" size="9" maxlength="9" value="<%= collBasic.getE01DLHCUN() %>" readonly>
                <a href="javascript:GetCustomerDescId('E01DLHCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= collBasic.getE01CUSNA1() %>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Planilla :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
              	<input type="text" name="E01DLHNRO" size="12" maxlength="12" value="<%= collBasic.getE01DLHNRO() %>" readonly>
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
                <input type="text" name="E01DLHPRO" size="4" maxlength="4" value="<%= collBasic.getE01DLHPRO() %>" readonly>
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
            <td nowrap> 
              <div align="right"><b>No. Aceptante :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01DLHCUN" size="9" maxlength="9" value="<%= collBasic.getE01DLHCUN() %>" readonly>
                <a href="javascript:GetCustomerDescId('E01DLHCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre Aceptante :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= collBasic.getE01CUSNA1() %>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>R.U.T Aceptante :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01DLHCUN" size="9" maxlength="9" value="<%= collBasic.getE01DLHCUN() %>" readonly>
                <a href="javascript:GetCustomerDescId('E01DLHCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap> 
              <div align="right"><b>Direccion :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= collBasic.getE01CUSNA1() %>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02DLDFV1" size="2" maxlength="2" value="<%= dftBasic.getE02DLDFV1() %>" onkeypress="enterInteger()">
              <input type="text" name="E02DLDFV2" size="2" maxlength="2" value="<%= dftBasic.getE02DLDFV2() %>" onkeypress="enterInteger()">
              <input type="text" name="E02DLDFV3" size="2" maxlength="2" value="<%= dftBasic.getE02DLDFV3() %>" onkeypress="enterInteger()">
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
              <div align="right">Tipo Abono :</div>
            </td>
            <td nowrap width="23%" >
              <select name="E01DLHTP1">
		  		<option value="1" <% if (collBasic.getE01DLHTP1().equals("1")) out.print("selected"); %>>Pesos</option>
                <option value="2" <% if (collBasic.getE01DLHTP1().equals("2")) out.print("selected"); %>>U. Fomento</option>
                <option value="3" <% if (collBasic.getE01DLHTP1().equals("3")) out.print("selected"); %>>Dolares</option>
              	<option value="4" <% if (collBasic.getE01DLHTP1().equals("4")) out.print("selected"); %>>Otros</option>              
              </select> 
            </td>
            <td nowrap width="25%" > 
              <div align="right">Cuenta Corriente:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLHCAC" size="12" maxlength="12" value="<%= collBasic.getE01DLHCAC() %>" onkeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DLHCAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tipo de Documento:</div>
            </td>
            <td nowrap>
			  <select name="E01DLHTDO">
		  		<option value="1" <% if (collBasic.getE01DLHTDO().equals("1")) out.print("selected"); %>>Letras</option>
                <option value="2" <% if (collBasic.getE01DLHTDO().equals("2")) out.print("selected"); %>>Pagares</option>
                <option value="3" <% if (collBasic.getE01DLHTDO().equals("3")) out.print("selected"); %>>Facturas</option>
		        <option value="4" <% if (collBasic.getE01DLHTDO().equals("4")) out.print("selected"); %>>Otros</option>
		      </select>
            </td>
            <td nowrap>
            	<div align="right">Cuenta Cargo:</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01DLHDAC" size="12" maxlength="12" value="<%= collBasic.getE01DLHDAC() %>" onkeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DLHDAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Instruciones de Protesto:</div>
            </td>
            <td nowrap colspan=3> 
              <select name="E01DLHTPO">
		  		<option value="1" <% if (collBasic.getE01DLHTPO().equals("1")) out.print("selected"); %>>Con Protesto</option>
                <option value="2" <% if (collBasic.getE01DLHTPO().equals("2")) out.print("selected"); %>>Sin Protesto</option>
                <option value="3" <% if (collBasic.getE01DLHTPO().equals("3")) out.print("selected"); %>>C/Prot Por falta de Acepatcion</option>
              </select>
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
