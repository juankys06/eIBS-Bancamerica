<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Lines of Credit</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="inc" class="datapro.eibs.beans.ELN0115DSMessage"  scope="session" />

</HEAD>

<body>

<h3 align="center">Mantenimiento de Porcentaje de Incidencia en Linea Financiera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cl_maint, ELN0115"> 
</h3>
<hr>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSELN0115" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
    </p>
    <table cellspacing="0" cellpadding="2" class="tbenter" border=0   width=70% align="center" height="80%">
      <tr valign="middle"> 
        <td nowrap colspan="2" align="middle" height="131">&nbsp;</td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="24">Banco :</td>
        <td nowrap width=40% align="left" height="24" > 
          <input type="text" name="BANK" size="4" maxlength="9" onKeyPress="enterInteger()" value="<%= inc.getE01FLUBNK()%>" readonly>
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="24">Modulo :</td>
        <td nowrap width=40% align="left" height="24" > 
          <input type="text" name="MODULE" size="4" maxlength="9" value="<%= inc.getE01FLUTYP()%>" readonly>
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="24">Dias Tenor Desde/Hasta:</td>
        <td nowrap width=40% align="left" height="24" >
          <input type="text" name="TENORF" size="4" maxlength="4"  value="<%= inc.getE01FLUTNR()%>" readonly>
          / 
          <input type="text" name="TENORT" size="4" maxlength="4"  value="<%= inc.getE01FLUTNU()%>" readonly>
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="24">Porcentaje de Incidencia:</td>
        <td nowrap width=40% align="left" height="24" > 
          <input type="text" name="PERCENT" size="4" maxlength="4"  value="<%= inc.getE01FLUPER()%>">
        </td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle" height="100"> 
          <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
        </td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle">&nbsp;</td>
      </tr>
    </table>
  </CENTER>
<script language="JavaScript">
  document.forms[0].BANK.focus();
  document.forms[0].BANK.select();
</script>
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
 </FORM>
</BODY>
</HTML>
 