<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Información Financiera</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "optList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "clientFnl" class= "datapro.eibs.beans.EDP003001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>


<body bgcolor="#FFFFFF">

<h3 align="center">Información Financiera de Clientes Jurídicos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_financial_enter,EDP00030"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDP0030" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    </p>
    <table cellspacing="0" cellpadding="2" class="tbenter"  border=0  width=70% align="center" >
      <tr valign="middle"> 
        <td nowrap colspan="2" align="middle" >&nbsp;</td>
      </tr>	  
      <tr valign="middle"> 
        <td nowrap width=40% align="right" > 
          Introduzca el N&uacute;mero de Cliente : 
        </td>
        <td nowrap width=40% align="left"> 
            <input type=TEXT name="E01IFMCUN" value="<%= clientFnl.getE01IFMCUN() %>" size=15 maxlength=9 onKeypress="enterInteger()">
            <a href="javascript:GetCustomerCorp('E01IFMCUN','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" > 
          Año / Mes : 
        </td>
        <td nowrap width=40% align="left">  
            <input type=TEXT name="E01IFMFEY" value="<%= clientFnl.getE01IFMFEY() %>" size=3 maxlength=2 onKeypress="enterInteger()">
            <input type=TEXT name="E01IFMFEM" value="<%= clientFnl.getE01IFMFEY() %>" size=3 maxlength=2 onKeypress="enterInteger()"> 
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=40% align="right" > 
          Formato de Balance : 
        </td>
        <td nowrap width=40% align="left">  
            <select name="E01IFMCFO">
             <%
                optList.initRow();
                int k=1;
                while (optList.getNextRow()) {
                    if (optList.getFlag().equals("")) {
                    		out.println(optList.getRecord());
                    k++;
                    }        
                }                 
    		%> 
            </select>
        </td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle" height="200"> 
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
 </FORM>
<script language="JavaScript">
  document.forms[0].E01IFMCUN.focus();
  document.forms[0].E01IFMCUN.select();
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
</BODY>
</HTML>
 