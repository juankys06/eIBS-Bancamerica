<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Consulta de Documentos Varios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


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
<h3 align="center">Consulta de Documentos Varios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dv_inq_sel.jsp,ETL0510"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSETL0510" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <h4>Informaci&oacute;n B&aacute;sica</h4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Documento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SELDTY" size="2" maxlength="2" value="">
              <a href="javascript:GetCode('E01SELDTY','STATIC_dv_typ.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Status del Documento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SELSCH" size="2" maxlength="2" value="">
              <a href="javascript:GetCode('E01SELSCH','STATIC_dv_stat.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
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
              <div align="right">N&uacute;mero de Documento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SELNCH" size="10" maxlength="9">
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
              <div align="right">Tomador :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SELAPL" size="30" maxlength="30">
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
