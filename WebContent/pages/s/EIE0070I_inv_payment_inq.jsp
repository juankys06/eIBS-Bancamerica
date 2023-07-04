<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Portafolio</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invTrade" class="datapro.eibs.beans.EIE007001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
       
      builtNewMenu(inst_inq_opt);
 
   </SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

 out.println("<SCRIPT> initMenu();  </SCRIPT>");

%>
<div align="center"> 
  <h3> Esquema de Pagos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_quote_basic.jsp,EIE0060"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0070" >
  <h4>Información Básica 
    <input type="hidden" name="SCREEN"  value="4" >
  </h4>
  <table  class="tableinfo" width="715">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Código de Instrumento :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="hidden" name="E01SCHIIC"  value="<%= invTrade.getE01SCHIIC()%>" >
              <%= invTrade.getE01SCHIIC()%> - <%= invTrade.getD01ISIDSC()%> </td>
            <td nowrap width="14%" > 
              <div align="right">Tipo de Pago :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="hidden" name="E01SCHTYP"  value="<%= invTrade.getE01SCHTYP()%>" >
              <% if(invTrade.getE01SCHTYP().equals("I")) out.print("Interest");
              				else if(invTrade.getE01SCHTYP().equals("D")) out.print("Dividends");
							else out.print("Capital");%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap width="36%" ><%= invTrade.getD01ISIPTY()%> </td>
            <td nowrap width="14%" > 
              <div align="right">Monto a Pagar :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01SCHAM1" size="15" maxlength="15" value="<%= invTrade.getE01SCHAM1()%>" 
				onKeyPress="enterDecimal()">
              <input type="radio" name="E01SCHPFO" value="F" disabled <% if (invTrade.getE01SCHPFO().equals("F")) out.print("checked"); %>>
              Fijo 
              <input type="radio" name="E01SCHPFO" disabled value="%" <% if (invTrade.getE01SCHPFO().equals("%")) out.print("checked"); %>>
              Porciento </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha Declarada :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01SCHDE1" size="3" maxlength="2" value="<%= invTrade.getE01SCHDE1()%>">
              <input type="text" readonly  name="E01SCHDE2" size="3" maxlength="2" value="<%= invTrade.getE01SCHDE2()%>">
              <input type="text" readonly  name="E01SCHDE3" size="3" maxlength="2" value="<%= invTrade.getE01SCHDE3()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Fecha de Grabación :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01SCHRE1" size="3" maxlength="2" value="<%= invTrade.getE01SCHRE1()%>" >
              <input type="text" readonly  name="E01SCHRE2" size="3" maxlength="2" value="<%= invTrade.getE01SCHRE2()%>" >
              <input type="text" readonly  name="E01SCHRE3" size="3" maxlength="2" value="<%= invTrade.getE01SCHRE3()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha de Pago :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01SCHPA1" size="3" maxlength="2" value="<%= invTrade.getE01SCHPA1()%>" >
              <input type="text" readonly  name="E01SCHPA2" size="3" maxlength="2" value="<%= invTrade.getE01SCHPA2()%>" >
              <input type="text" readonly  name="E01SCHPA3" size="3" maxlength="2" value="<%= invTrade.getE01SCHPA3()%>" >
            </td>
            <td nowrap width="14%" > 
              <div align="right">Fecha de Entrega :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01SCHDL1" size="3" maxlength="2" value="<%= invTrade.getE01SCHDL1()%>" >
              <input type="text" readonly  name="E01SCHDL2" size="3" maxlength="2" value="<%= invTrade.getE01SCHDL2()%>" >
              <input type="text" readonly  name="E01SCHDL3" size="3" maxlength="2" value="<%= invTrade.getE01SCHDL3()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01SCHSTS" size="5" maxlength="3" value="<%= invTrade.getE01SCHSTS()%>" >
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">Monto Total Pagado :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01SCHAM2" size="15" maxlength="15" value="<%= invTrade.getE01SCHAM2()%>" 
				>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Número de Pagos :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01SCHNPA" size="7" maxlength="5" value="<%= invTrade.getE01SCHNPA()%>" 
				>
            </td>
            <td nowrap > 
              <div align="right"></div>
              
              <div align="right">Tipo de Evento :</div>
            </td>
            <td nowrap >
              <input type="radio" name="E01SCHFL0" value="" disabled <% if (invTrade.getE01SCHFL0().equals("")) out.print("checked"); %>>
              Pago Normal 
              <input type="radio" name="E01SCHFL0" value="M"  disabled <% if (invTrade.getE01SCHFL0().equals("M")) out.print("checked"); %>>
              Al Vencimiento 
              <input type="radio" name="E01SCHFL0" value="C"  disabled <% if (invTrade.getE01SCHFL0().equals("C")) out.print("checked"); %>>
              Call</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
