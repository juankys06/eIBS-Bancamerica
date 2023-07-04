<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Productos de Certificados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="ddaProdInq" class="datapro.eibs.beans.ESD071103Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
<%
if (userPO.getPurpose().equals("INQUIRY")){
%>

<%
if ( userPO.getOption().equals("RT") ) {
%>
  builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>
<%
}
%>
</SCRIPT>

</head>

<body>
<%@ page import = "datapro.eibs.master.Util" %>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     error.setERRNUM("0");
     out.println("</SCRIPT>");
  }
  if (userPO.getPurpose().equals("INQUIRY")){ 
   	out.println("<SCRIPT> initMenu(); </SCRIPT>");
  }
%>

<h3 align="center">Consulta de Producto de Cuentas de Ahorros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_inq_dda.jsp, ESD0711"></h3>
<hr size="4">

<form>
  <p></p>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">N&uacute;mero de Banco: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCBNK" size="4" maxlength="2" value="<%= ddaProdInq.getE03APCBNK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Tipo de Producto: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCTYP" size="6" maxlength="4" value="<%= ddaProdInq.getE03APCTYP().trim()%>">
              <input type="text" readonly name="E03TYPDSC" size="37" maxlength="35" value="<%= ddaProdInq.getE03TYPDES().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">C&oacute;digo de Producto: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCCDE" size="6" maxlength="4" value="<%= ddaProdInq.getE03APCCDE().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Descripci&oacute;n: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03DESCRI" size="45" maxlength="63" value="<%= ddaProdInq.getE03DESCRI().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">Nombre de Mercadeo: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03MERCAD" size="17" maxlength="15" value="<%= ddaProdInq.getE03MERCAD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Moneda: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCCCY" size="5" maxlength="3" value="<%= ddaProdInq.getE03APCCCY().trim()%>">
              <input type="text" readonly name="E03CCYDSC" size="37" maxlength="35" value="<%= ddaProdInq.getE03CCYDSC().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">Cuenta Contable: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCGLN" size="18" maxlength="16" value="<%= ddaProdInq.getE03APCGLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Descripci&oacute;n de Cuenta Contable: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03GLMDSC" size="37" maxlength="35" value="<%= ddaProdInq.getE03GLMDSC().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">Calificaci&oacute;n de Cuentas: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCTAR" size="4" maxlength="2" value="<%= ddaProdInq.getE03APCTAR().trim()%>">
              <input type="text" readonly name="E03TARDSC" size="27" maxlength="25" value="<%= ddaProdInq.getE03TARDSC().trim()%>">
			  <a href="javascript:showDDAServCharge('<%= ddaProdInq.getE03APCBNK().trim()%>','<%= ddaProdInq.getE03APCTYP().trim()%>','<%= ddaProdInq.getE03APCTAR().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Tipo de Residencia: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCRES" size="3" maxlength="1" value="<%= ddaProdInq.getE03APCRES().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">Sobregiro Permitido: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCAMO" size="3" maxlength="1" value="<%= ddaProdInq.getE03APCAMO().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Tabla de Documentos: </div>
            </td>
            <td width="63%"> 
              <input type="text" readonly name="E03APCFTF" size="4" maxlength="2" value="<%= ddaProdInq.getE03APCFTF().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Direcciones de Acceso</h4>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">Audio : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E03APEAUD" size="82" maxlength="80" value="<%= ddaProdInq.getE03APEAUD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Video : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E03APEVID" size="82" maxlength="80" value="<%= ddaProdInq.getE03APEVID().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">HTML : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E03APEHTM" size="82" maxlength="80" value="<%= ddaProdInq.getE03APEHTM().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <p>
</p>
</form>
</body>
</html>
