<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Contabilidad</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="gLedger" class="datapro.eibs.beans.EGL034001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

  function verifyCancel() {
    var delOK = false;
    delOK = confirm("Esta seguro que desea realizar la operacion de Cancelacion?"); 
	return(delOK); 	
  }
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<H3 align="center">Cancelaci&oacute;n al Maestro de Contabilidad<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="gledger_basic_cancel.jsp, EGL0340"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0340" onsubmit="return(verifyCancel())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="6">
  <INPUT TYPE=HIDDEN NAME="E01GLMACD" value="<%= gLedger.getE01GLMACD().trim()%>">
<H4>Datos B&aacute;sicos</H4>
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">No. Cuenta Contable :</div>
            </td>
            <td nowrap colspan="3">                
                <input type="text" name="E01GLMBNK" size="2" maxlength="2" value="<%= gLedger.getE01GLMBNK().trim()%>" readonly>                             
                <input type="text" name="E01GLMCCY" size="3" maxlength="3" value="<%= gLedger.getE01GLMCCY().trim()%>" readonly>              
                <input type="text" name="E01GLMGLN" size="17" maxlength="16"  value="<%= gLedger.getE01GLMGLN().trim()%>" readonly>              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Descripción :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01GLMDSC" size="36" maxlength="35" value="<%= gLedger.getE01GLMDSC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Tipo Producto :</div>
            </td>
            <td nowrap width="19%">
              <INPUT type="text" name="E01GLMATY" size="5" maxlength="4" value="<%= gLedger.getE01GLMATY().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Clase de la Cuenta :</div>
            </td>
            <td nowrap width="26%">
             <input type="text" name="E01GLMCLS" size="3" maxlength="2" value="<%= gLedger.getE01GLMCLS()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Requiere Centro de Costo :</div>
            </td>
            <td nowrap width="19%">
              <INPUT type="radio" name="E01GLMCCN" value="Y" <%if(gLedger.getE01GLMCCN().equals("Y")) out.print("checked");%> disabled> S&iacute;
              <INPUT type="radio" name="E01GLMCCN" value="N" <%if(!gLedger.getE01GLMCCN().equals("Y")) out.print("checked");%> disabled> No
            </td>
            <td nowrap width="26%"> 
              <div align="right">Cuenta Reconciliable :</div>
            </td>
            <td nowrap width="26%">
              <INPUT type="radio" name="E01GLMRCL" value="Y" <%if(gLedger.getE01GLMRCL().equals("Y")) out.print("checked");%> disabled> S&iacute;
              <INPUT type="radio" name="E01GLMRCL" value="N" <%if(!gLedger.getE01GLMRCL().equals("Y")) out.print("checked");%> disabled> No
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="29%">
              <div align="right">Estado de Cuenta Diario :</div>
            </td>
            <td nowrap width="19%">
              <INPUT type="radio" name="E01GLMDST" value="1" <%if(gLedger.getE01GLMDST().equals("1")) out.print("checked");%> disabled> S&iacute;
              <INPUT type="radio" name="E01GLMDST" value="N" <%if(!gLedger.getE01GLMDST().equals("1")) out.print("checked");%> disabled> No
            </td>
            <td nowrap width="26%">
              <div align="right">Nivel de Libro Diario :</div>
            </td>
            <td nowrap width="26%">
              <INPUT type="radio" name="E01GLMRMA" value="D" <%if(gLedger.getE01GLMRMA().equals("D")) out.print("checked");%> disabled> Detalle
              <INPUT type="radio" name="E01GLMRMA" value="T" <%if(!gLedger.getE01GLMRMA().equals("D")) out.print("checked");%> disabled> Total
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Cuenta I.B.F :</div>
            </td>
            <td nowrap width="19%" height="19">
              <INPUT type="radio" name="E01GLMIBF" value="IB" <%if(gLedger.getE01GLMRMA().equals("IB")) out.print("checked");%> disabled> Sí
              <INPUT type="radio" name="E01GLMIBF" value="" <%if(!gLedger.getE01GLMRMA().equals("IB")) out.print("checked");%> disabled> No
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="right">Sensibilidad de Tasas :</div>
            </td>
            <td nowrap width="26%" height="19">
              <input type="text" name="E01GLMFR6" size="5" maxlength="5" value="<%= gLedger.getE01GLMFR6().trim()%>" disabled>
            </td>
          </tr>
		  <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Códigos de Usuario :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01GLMCIC" size="4" maxlength="4" value="<%= gLedger.getE01GLMCIC().trim()%>" readonly>
              <input type="text" name="E01GLMFR1" size="5" maxlength="5" value="<%= gLedger.getE01GLMFR1().trim()%>" readonly>
              <input type="text" name="E01GLMFR2" size="5" maxlength="5" value="<%= gLedger.getE01GLMFR2().trim()%>" readonly>
              <input type="text" name="E01GLMFR3" size="5" maxlength="5" value="<%= gLedger.getE01GLMFR3().trim()%>" readonly>
              <input type="text" name="E01GLMFR4" size="5" maxlength="5" value="<%= gLedger.getE01GLMFR4().trim()%>" readonly>
              <input type="text" name="E01GLMFR5" size="5" maxlength="5" value="<%= gLedger.getE01GLMFR5().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Reconciliación/Clientes :</div>
            </td>
            <td nowrap>
              <SELECT name="E01GLMCPF" disabled>
				<OPTION value=" " <% if (!(gLedger.getE01GLMCPF().equals("1") ||gLedger.getE01GLMCPF().equals("2")
				||gLedger.getE01GLMCPF().equals("3")||gLedger.getE01GLMCPF().equals("N")))
				out.print("selected"); %>></OPTION>
                <OPTION value="1" <%if (gLedger.getE01GLMCPF().equals("1")) out.print("selected"); %>>Reconciliación Bancaria</OPTION>
                <OPTION value="2" <%if (gLedger.getE01GLMCPF().equals("2")) out.print("selected"); %>>Requiere Numero Cliente</OPTION>
                <OPTION value="3" <%if (gLedger.getE01GLMCPF().equals("3")) out.print("selected"); %>>Las Dos Anteriores</OPTION>
                <OPTION value="N" <%if (gLedger.getE01GLMCPF().equals("N")) out.print("selected"); %>>Ninguna de las Dos</OPTION>
               </SELECT>
            </td>
            <td nowrap> 
              <div align="right">Cuenta Tesorería :</div>
            </td>
            <td nowrap>
              <INPUT type="radio" name="E01GLMMEF" value="Y" <%if(gLedger.getE01GLMMEF().equals("Y")) out.print("checked");%> disabled> S&iacute;
              <INPUT type="radio" name="E01GLMMEF" value="N" <%if(!gLedger.getE01GLMMEF().equals("Y")) out.print("checked");%> disabled> No
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nivel de Contabilización :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01GLMACL" size="2" maxlength="1" value="<%= gLedger.getE01GLMACL().trim()%>" onkeypress="enterInteger()" readonly>
            </td>
            <td nowrap> 
              <div align="right">Control Previsiones :</div>
            </td>
            <td nowrap>
              <INPUT type="radio" name="E01GLMPRV" value="Y" <%if(gLedger.getE01GLMPRV().equals("Y")) out.print("checked");%> disabled> S&iacute;
              <INPUT type="radio" name="E01GLMPRV" value="N" <%if(!gLedger.getE01GLMPRV().equals("Y")) out.print("checked");%> disabled> No
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Mant./Valor o Reajuste :</div>
            </td>
            <td nowrap>
             <SELECT name="E01GLMREV" disabled>
				<OPTION value="N" <% if (!(gLedger.getE01GLMREV().equals("1") ||gLedger.getE01GLMREV().equals("2")||gLedger.getE01GLMREV().equals("3")
				||gLedger.getE01GLMREV().equals("4")||gLedger.getE01GLMREV().equals("5")||gLedger.getE01GLMREV().equals("N")))
				out.print("selected"); %>></OPTION>
                <OPTION value="1" <%if (gLedger.getE01GLMREV().equals("1")) out.print("selected"); %>>Mant./Valor Nicaragua</OPTION>
                <OPTION value="2" <%if (gLedger.getE01GLMREV().equals("2")) out.print("selected"); %>>Reajuste UF/Chile</OPTION>
                <OPTION value="3" <%if (gLedger.getE01GLMREV().equals("3")) out.print("selected"); %>>Reajuste IPV/Chile</OPTION>
                <OPTION value="4" <%if (gLedger.getE01GLMREV().equals("4")) out.print("selected"); %>>Reajuste USD/Chile</OPTION>
                <OPTION value="5" <%if (gLedger.getE01GLMREV().equals("5")) out.print("selected"); %>>Valor Ajuste/PERU</OPTION>
                <OPTION value="N" <%if (gLedger.getE01GLMREV().equals("N")) out.print("selected"); %>>No Aplica</OPTION>
               </SELECT>
            </td>
            <td nowrap> 
              <div align="right">Cuenta Contrapartida :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01GLMXOF" size="17" maxlength="16" value="<%= gLedger.getE01GLMXOF()%>" onkeypress="enterInteger()" readonly>
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Numero de Presupuesto :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01GLMBUN" size="13" maxlength="12" value="<%= gLedger.getE01GLMBUN()%>" onkeypress="enterInteger()" readonly>            
              </td>
            <td nowrap> 
              <div align="right">Cuenta Equivalente :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01GLMHOA" size="21" maxlength="20" value="<%= gLedger.getE01GLMHOA()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Descripción Equivalente :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01GLMHDS" size="60" maxlength="60" value="<%= gLedger.getE01GLMHDS().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>  

  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
  </form>
 </body>
</html>
