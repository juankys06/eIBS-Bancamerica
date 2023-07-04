<html>
<head>
<title>Consulta de Cheques de Gerencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<h3 align="center">Consulta de Cheques de Gerencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="chk_off_inq.jsp,ETL0510"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSETL0510" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <h4>Informaci&oacute;n B&aacute;sica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Estado del Cheque :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SELSCH" size="2" maxlength="2" value="">
              <a href="javascript:GetCode('E01SELSCH','STATIC_dv_stat.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">N&uacute;mero de Cheque :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SELNCH" size="10" maxlength="9">
              <a href="javascript:GetCheck('E01SELNCH','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SELACC" size="13" maxlength="12">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%">
              <div align="right">Beneficiario :</div>
            </td>
            <td nowrap width="23%">
              <input type="text" name="E01SELBNF" size="30" maxlength="30">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Remitente :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SELAPL" size="30" maxlength="30">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
