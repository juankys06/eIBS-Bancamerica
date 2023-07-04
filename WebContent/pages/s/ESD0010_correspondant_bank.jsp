<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Financial Transaction</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id="pymInst" class="datapro.eibs.beans.ESD001001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
%>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
<% 
 }
%>


<h3 align="center">Mantenimiento de Banco Corresponsal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="correspondant_bank.jsp,ESD0010"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0010">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  </p>
  <h4>Información Básica</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Código Swift : </div>
            </td>
            <td nowrap width="19%">
              <input type="text" name="E01SWI" size="15" maxlength="12" value="<%= pymInst.getE01SWI()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Moneda : </div>
            </td>
            <td nowrap width="26%">
              <input type="text" name="E01CCY" size="4" maxlength="3" value="<%= pymInst.getE01CCY()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Código Swift del Corrresponsal : </div>
            </td>
            <td nowrap width="19%" height="23">
              <input type="text" name="E01SIC" size="15" maxlength="12" value="<%= pymInst.getE01SIC()%>">
              <a href="javascript:GetSwiftId('E01SIC')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Código A.B.A del Corresponsal : </div>
            </td>
            <td nowrap width="26%" height="23">
              <input type="text" name="E01ABC" size="12" maxlength="9" value="<%= pymInst.getE01ABC()%>">
              <a href="javascript:GetFedId('E01ABC')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <br>
  <div id="OtherOpt">
<table width="100%">
      <tr> 
        <td width="50%"> 
          <p align="center"> <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="submit()">
          </p>
        </td>
      </tr>
    </table>

</div>

</form>
  

</body>
</html>
