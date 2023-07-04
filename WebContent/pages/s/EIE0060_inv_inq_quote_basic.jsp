<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Inversiones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invInst" class="datapro.eibs.beans.EIE006001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }


%>
<div align="center"> 
  <h3>Cotizaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_quote_basic.jsp,EIE0060"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0060" >
  <h4>Informaci&oacute;n B&aacute;sica 
    <input type="hidden" name="SCREEN"  value="2" >
  </h4>
  <table  class="tableinfo" width="715">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">C&oacute;digo :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="hidden" name="E01QUOIIC"  value="<%= invInst.getE01QUOIIC()%>" >
              <%= invInst.getE01QUOIIC()%> - <%= invInst.getD01ISIDSC()%> </td>
            <td nowrap width="14%" > 
              <div align="right">S&iacute;mbolo :</div>
            </td>
            <td nowrap width="33%" > <%= invInst.getD01ISISYM()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap width="36%" ><%= invInst.getD01ISIPTY()%> </td>
            <td nowrap width="14%" > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="33%" ><%= invInst.getD01ISICCY()%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01QUOTE1" size="3" maxlength="2" value="<%= invInst.getE01QUOTE1()%>">
              <input type="text" readonly  name="E01QUOTE2" size="3" maxlength="2" value="<%= invInst.getE01QUOTE2()%>">
              <input type="text" readonly  name="E01QUOTE3" size="3" maxlength="2" value="<%= invInst.getE01QUOTE3()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Hora :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01QUOTET" size="15" maxlength="12" value="<%= invInst.getE01QUOTET()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Precios </h4>
  <table  class="tableinfo" width="715">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio de Apertura :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOOPP" size="15" maxlength="13" value="<%= invInst.getE01QUOOPP()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Precio Pedido :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOASK" size="15" maxlength="13" value="<%= invInst.getE01QUOASK()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio BID :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOBID" size="15" maxlength="13" value="<%= invInst.getE01QUOBID()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Precio MID :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOMID" size="15" maxlength="13" value="<%= invInst.getE01QUOMID()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio Pedido antes de Cierre :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOASP" size="15" maxlength="13" value="<%= invInst.getE01QUOASP()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Precio BID antes del Cierre :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOBIP" size="15" maxlength="13" value="<%= invInst.getE01QUOBIP()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio MID antes del Cierre :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOMIP" size="15" maxlength="13" value="<%= invInst.getE01QUOMIP()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Precio Mayor en 52 semanas :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOHIP" size="15" maxlength="13" value="<%= invInst.getE01QUOHIP()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio Menor en 52 Semanas :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOLOP" size="15" maxlength="13" value="<%= invInst.getE01QUOLOP()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Ultimo Precio de Negocio :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01QUOTPX" size="15" maxlength="13" value="<%= invInst.getE01QUOTPX()%>" 
				onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
