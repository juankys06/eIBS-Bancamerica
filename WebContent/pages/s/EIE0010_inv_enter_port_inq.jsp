<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Portfolio</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>

<body>

<h3 align="center">Consulta de Portafolios <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_enter_port, EIE00010"></h3>
<hr>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0010" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
    </p>
    <table cellspacing="0" cellpadding="2" class="tbenter" border=0   width=70% align="center" height="80%">
      <tr valign="middle"> 
        <td nowrap colspan="2" align="middle" height="131">&nbsp;</td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="24"> Número de Cliente : </td>
        <td nowrap width=40% align="left" height="24" > 
          <INPUT type="text" name="CUSNUM" size="15" maxlength="9" onkeypress="enterInteger()">
          <input type="text" name="DESC" size="35" maxlength="35" onKeyPress="enterInteger()">
          <a href="javascript:GetCustomerDescId('CUSNUM','DESC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="28" > Número de Portafolio : </td>
        <td nowrap width=40% align="left" height="28" > 
          <INPUT type="text" name="PORTFOLIO" size="5" maxlength="4" onkeypress="enterInteger()">
            <a href="javascript:GetPortfolioNumDesc('PORTFOLIO','CUSNUM','',document.forms[0].CUSNUM.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
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
  document.forms[0].CUSNUM.focus();
  document.forms[0].CUSNUM.select();
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
 