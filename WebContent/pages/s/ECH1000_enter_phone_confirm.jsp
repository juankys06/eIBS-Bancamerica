<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Split</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="phConfir" class="datapro.eibs.beans.ECH100001Message"  scope="session" />

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
  <h3>Sistema de Reserva de Cheques<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_phone_confirm.jsp,ECH1000"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH1000" >
  <h4> 
    <input type="hidden" name="SCREEN"  value="2" >
    Informaci&oacute;n B&aacute;sica </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">N&uacute;mero de Cuenta :</div>
            </td>
            <td nowrap width="36%" >
              <input type="text" name="E01CHPACC" size="12" maxlength="12" onKeypress="enterInteger()" value="<%=phConfir.getE01CHPACC() %>" >
              <a href="javascript:GetAccount('E01CHPACC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">N&uacute;mero de Cheque :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01CHMCKN" size="17" maxlength="15" value="<%= phConfir.getE01CHMCKN()%>" onKeyPress="enterInteger()">
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="17%" >
              <div align="right">Valor del Cheque :</div>
            </td>
            <td nowrap width="36%" >
              <input type="text" name="E01CHMAMT" size="15" maxlength="15" value="<%= phConfir.getE01CHMAMT()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Nombre Negocio :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01CONNAME" size="31" maxlength="30" value="<%= phConfir.getE01CONNAME()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Clave de Cheque :</div>
            </td>
            <td nowrap width="36%" >
              <input type="text" name="E01CONCLAV" readonly size="17" maxlength="15" value="<%= phConfir.getE01CONCLAV()%>" onKeyPress="enterInteger()">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>
    
    <br>
  </h4>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
