<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1"> 
<TITLE>N/M LC</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<body>

<h3 align="center">Eliminacion de Lineas de Credito <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cl_enter_delete, ELN0000"></h3>
<hr>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
    </p>
    <table cellspacing="0" cellpadding="2" class="tbenter" border=0   width=70% align="center" height="80%">
      <tr valign="middle"> 
        <td nowrap colspan="2" align="middle" height="131">&nbsp;</td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="24"> Ingrese el N&uacute;mero 
          de Cliente : </td>
        <td nowrap width=40% align="left" height="24" > 
          <input type=TEXT name="CUSNUM" value="" size=15 maxlength=9 onKeypress="enterInteger()">
            <a href="javascript:GetCustomer('CUSNUM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="28" > Ingrese el N&uacute;mero 
          de Linea de Crédito : </td>
        <td nowrap width=40% align="left" height="28" > 
          <input type=TEXT name="LNENUM" value="" size=15 maxlength=9 onKeypress="enterInteger()">
            <a href="javascript:GetCreditLine('LNENUM',document.forms[0].CUSNUM.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
        </td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle" height="100"> 
          <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
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
 