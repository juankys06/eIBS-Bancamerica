<html>
<head>
<title>Sender to Receiver Information (72)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lcAdd" class="datapro.eibs.beans.ELC050003Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">

builtNewMenu(lc_opening);

   builtHPopUp();

  function showPopUp(opth,field,Banco,ccy,field1,field2,opcod) {
   init(opth,field,Banco,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

</head>

<body bgcolor="#FFFFFF">


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
 }
%> 
<h3 align="center">Información de Banco a Banco (72)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_72_info.jsp, ELC0500"></h3>
<hr size="4">


 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2000">
  <INPUT TYPE=HIDDEN NAME="E03LCDFCD" VALUE="72">

<TABLE class="tableinfo" cellspacing="0" cellpadding="4" width="100%" border="0">
	<TR id="trdark">
		<TD nowrap align="right" width="10%"><B> Producto:</B></TD>
		<TD nowrap align="left" width="20%">
			<INPUT type="text" name="E03LCDPRO" size="5" maxlength="4" value="<%=lcAdd.getE03LCDPRO().trim()%>" readonly></TD>
		<TD nowrap align="right" width="10%"><B>Banco:</B></TD>
		<TD nowrap align="left" width="20%">
			<INPUT type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%=lcAdd.getE03LCDBNK().trim()%>"></TD>
		<TD nowrap width="25%" align="right"><B>Numero Carta de Credito:</B></TD>
		<TD nowrap align="left" width="15%">
			<INPUT type="text" readonly name="E03LCDACC" size="21" maxlength="20"value="<%=lcAdd.getE03LCDACC().trim()%>"></TD>
	</TR>
</TABLE>
  <p align="center"><A href="javascript:getClausula('72','11','<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510')"><b>Copiar Clausula 
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0"></b></a></p>

<BR>
<table class="tableinfo">
    <tr > 
      <td nowrap align="center"> 
          <input type="text" name="E03LCDM01" size="45" maxlength="35" value="<%= lcAdd.getE03LCDM01().trim()%>"><br>
          <input type="text" name="E03LCDM02" size="45" maxlength="35" value="<%= lcAdd.getE03LCDM02().trim()%>"><br>
          <input type="text" name="E03LCDM03" size="45" maxlength="35" value="<%= lcAdd.getE03LCDM03().trim()%>"><br>
          <input type="text" name="E03LCDM04" size="45" maxlength="35" value="<%= lcAdd.getE03LCDM04().trim()%>"><br>
          <input type="text" name="E03LCDM05" size="45" maxlength="35" value="<%= lcAdd.getE03LCDM05().trim()%>"><br>
          <input type="text" name="E03LCDM06" size="45" maxlength="35" value="<%= lcAdd.getE03LCDM06().trim()%>"><br>
      </td>
    </tr>
  </table>
  <p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
