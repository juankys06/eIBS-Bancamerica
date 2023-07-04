<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Asignación de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECC009001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">
<!--
//-->
//-->
</script>

</head>
<body>
<h3 align="center">Asignación de Tarjetas de Débito<br>Tarjeta Adicional<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="additional_card_assign.jsp, ECC0090">
</h3>
<hr size="4">
<% 
if ( !error.getERRNUM().equals("0")  ) {
     //error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
}

%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1500">
  <INPUT TYPE=HIDDEN NAME="new" VALUE="true">

  <h4> Información del Cliente Titular</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><B>ID Cliente :</B></div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E03CCRCID" size="16" maxlength="15" value="<%= msgCD.getE01CCRCID()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03PRINA1" size="36" maxlength="35" value="<%= msgCD.getE01PRINA1()%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Número de Cliente :</b></div>
            </td>
            <td nowrap width="16%" > 
              <div align="left"> 
                <input type="text" name="E03PRICUN" size="10" maxlength="10" value="<%= msgCD.getE01PRICUN()%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Número de Tarjeta :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
              	<INPUT type="text" name="E03CCRNUM" size="21" maxlength="20" value="<%= msgCD.getE01CCRNUM()%>" readonly>
              </div>
             </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  <h4>Información del Cliente Adicional</h4>

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <TBODY><TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Número de Cliente :</B></DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="left">
              	<INPUT type="text" name="E03PRICUNA" size="10" maxlength="9" value="" onKeyPress="enterInteger()">
        		<a href="javascript:GetCustomerDescId('E03PRICUNA','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
              </DIV>
            </TD>
            
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Número de Tarjeta :</B></DIV>
            </TD><TD nowrap width="20%"> 
              <DIV align="left">
              	<INPUT type="text" name="E03CCRNUMA" size="21" maxlength="20" value="">
              </DIV>
             </TD>
            
          </TR>    
        </TBODY></TABLE>
      </td>
    </tr>
  </table>
  <BR><DIV align="center"><INPUT id="EIBSBTN" type="submit" name="Submit"
	value="Asignar"></DIV></form>
</body>
</html>
