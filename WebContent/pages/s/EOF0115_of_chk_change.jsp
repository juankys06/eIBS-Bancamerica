<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Cheques de Gerencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

 builtNewMenu(of_np_opt);

</SCRIPT>


</head>

<body  nowrap bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	  error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 


<h3 align="center">Cambio de Numeraci&oacute;n de Cheques de Gerencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="of_chk_change.jsp , EOF0115"></h3>
<hr size="4">
<p align="center">&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="10">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0" background="<%=request.getContextPath()%>/images/s/dat_check.gif">
          <tr> 
            <td nowrap rowspan="3" colspan="2" align="center" valign="middle"> 
              <div align="center"></div>
              <div align="right"></div>
            </td>
            <td nowrap width="31%"> 
              <div align="right"><font face="Arial, Helvetica, sans-serif" color="#000000"><i><b>Cheque 
                No. :</b></i></font></div>
            </td>
            <td nowrap width="44%"><%= userPO.getIdentifier().trim()%> </td>
          </tr>
          <tr > 
            <td nowrap width="31%"> 
              <div align="right"><font face="Arial, Helvetica, sans-serif" color="#000000"><b><i>Fecha 
                :</i></b></font></div>
            </td>
            <td nowrap width="44%"> <%= Util.formatDate(userPO.getHeader5().trim(),userPO.getHeader6().trim(),userPO.getHeader7().trim())%> 
            </td>
          </tr>
          <tr > 
            <td nowrap height="23" width="31%"> 
              <div align="right"><font face="Arial, Helvetica, sans-serif" color="#000000"><b><i>Monto 
                :</i></b></font></div>
            </td>
            <td nowrap height="23" width="44%"> $<%= Util.addLeftChar('*',15,Util.fcolorCCY(userPO.getHeader8().trim()))%> 
            </td>
          </tr>
          <tr > 
            <td nowrap width="23%" height="23">&nbsp;</td>
            <td nowrap height="23" colspan="3">&nbsp;</td>
          </tr>
          <tr > 
            <td nowrap width="23%" height="23"> 
              <div align="right"><font color="#000000"><b><i>Pagar a la Orden 
                de :</i></b></font></div>
            </td>
            <td nowrap height="23" colspan="3"> <%= userPO.getHeader9().trim()%> 
            </td>
          </tr>
          <tr > 
            <td nowrap width="23%" height="19"><font color="#000000"></font></td>
            <td nowrap height="19" colspan="3"><%= userPO.getHeader10().trim()%></td>
          </tr>
          <tr > 
            <td nowrap width="23%" height="19"> 
              <div align="right"><font color="#000000"><b><i>La suma de :</i></b></font></div>
            </td>
            <td nowrap height="19" colspan="3" valign="middle"> <%= userPO.getHeader11().trim()%> 
            </td>
          </tr>
          <tr > 
            <td nowrap width="23%"> 
              <div align="right"><font color="#000000"><b><i>Concepto :</i></b></font></div>
            </td>
            <td nowrap colspan="3"> <%= userPO.getHeader12().trim()%> </td>
          </tr>
          <tr > 
            <td nowrap width="23%">&nbsp;</td>
            <td nowrap colspan="2"><%= userPO.getHeader13().trim()%> </td>
            <td nowrap width="44%"> 
              <div align="center"></div>
            </td>
          </tr>
          <tr > 
            <td nowrap width="23%">&nbsp;</td>
            <td nowrap colspan="2"><%= userPO.getHeader14().trim()%> </td>
            <td nowrap width="44%"> 
              <div align="center"><font face="Times New Roman, Times, serif" size="2"><b><i>Firma</i></b></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp; </p>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr> 
            <td nowrap width="50%"> 
              <div align="right">Ingrese el Nuevo N&uacute;mero de Cheque :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01NEWCKN" size="12" maxlength="12" onKeypress="enterInteger()">
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
  <script language="JavaScript">
  document.forms[0].E01NEWCKN.focus();
  document.forms[0].E01NEWCKN.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
