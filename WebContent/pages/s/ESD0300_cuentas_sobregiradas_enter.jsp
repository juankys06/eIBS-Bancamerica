<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1"> 

<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Consulta de Garantias</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>





<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

 <% if ( !error.getERRNUM().equals("0")  ) {
 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 %>

<h3 align="center">Consultas de Cuentas Sobregiradas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cuentas_sobregiradas_enter.jsp,ESD0300"></h3>
<hr>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0300" >
  <CENTER>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    <table cellspacing="0" cellpadding="2" class="tbenter" border=0   width=70% align="center" height="80%">
      <tr valign="middle"> 
        <td nowrap colspan="2" align="bottom" height="120">&nbsp;</td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" height="17" >Ingrese el N&uacute;mero de Oficina:</td>
        <td nowrap width=40% align="left" height="17" > 
          <input type=TEXT name="E01SELBRN" value="" size=15 maxlength=9>
          <a href="javascript:GetBranch('E01SELBRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a>  &nbsp; (En Blanco para todos.)</td>
      </tr>
      <tr valign="middle">
        <td nowrap width=40% align="right" height="8"></td>
        <td nowrap width=40% align="left" height="8" ></td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle" height="120"> 
         
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
 </FORM>
</BODY>
</HTML>
 