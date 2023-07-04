<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Instrucciones Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


   <SCRIPT Language="Javascript">
       
     builtNewMenu(client_inq_corp_opt);
 
   </SCRIPT>


<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%> 
<h3 align="center">Instrucciones Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_both_enter_new, ESD0080"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="62">
  </p>
  <table class="tableinfo">
    <tr> 
      <td nowrap > 
        <table class=tbhead cellspacing="0" cellpadding="2" width="100%"   align="center">
          <tr> 
            <td nowrap width="10%" align="right"> Cliente: </td>
            <td nowrap width="12%" align="left"> 
              <input type=HIDDEN name="E11CUS" value="<%= userPO.getHeader1()%>">
              <%= userPO.getHeader1()%> </td>
            <td nowrap width="6%" align="right">ID: </td>
            <td nowrap width="14%" align="left"> <%= userPO.getHeader2()%> </td>
            <td nowrap width="8%" align="right"> Nombre: </td>
            <td nowrap width="50%"align="left"> <%= userPO.getHeader3()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <br>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdar"> 
            <td nowrap align="right" valign="middle" width="49%"> 
              <div align="right">Código Moneda :</div>
            </td>
            <td nowrap align="left" valign="middle" colspan="2"> 
              <input type="text" name="E11CCY" maxlength="3" size="4">
              <a href="javascript:GetCurrency('E11CCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap align="right" valign="middle" width="49%"> 
              <div align="right">Tipo :</div>
            </td>
            <td nowrap align="left" valign="middle" colspan="2">
              <select name="E11TYP">
                <option value=" "></option>
                <option value="1">Por Omisión</option>
                <option value="2">Inversiones</option>
                <option value="3">Moneda Extranjera</option>
                <option value="4">FRA</option>
              </select>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>

</form>
</body>
</html>
