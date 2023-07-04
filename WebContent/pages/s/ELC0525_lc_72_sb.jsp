<HTML>
<HEAD>
<TITLE>Sender to Receiver Information (72)</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg040003" class="datapro.eibs.beans.ELC040003Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(lc_apr_standby);
	initMenu();
   
</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>

</HEAD>

<BODY bgcolor="#FFFFFF">

<H3 align="center">Información de Banco a Banco (72)<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" 
align="left" name="EIBS_GIF" alt="ELC0525_lc_72_sb.jsp"></H3>
<HR size="4">
<BR>
<table class="tableinfo" cellspacing="0" cellpadding="4" width="100%" border="0">
  <TR id="trdark">
    <TD nowrap align="right" width="10%"><B> Producto:</B></TD>
    <TD nowrap align="left" width="20%"><INPUT type="text" name="E03LCDPRO" size="5" maxlength="4" value="<%= userPO.getProdCode() %>" readonly></TD>
    <TD nowrap align="right" width="10%"><B>Banco:</B></TD>
    <TD nowrap align="left" width="20%"><INPUT type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%= userPO.getBank()  %>"></TD>
    <TD nowrap width="25%" align="right"><B>Numero Carta de Credito:</B></TD>
    <TD nowrap align="left" width="15%"><B>
      <INPUT type="text" readonly name="E03LCDACC" size="21" maxlength="20" value="<%= msg040003.getE03LCDACC().trim()%>">
    </B></TD>
  </TR>
</TABLE>
<BR>
<TABLE class="tableinfo">
    <TR > 
      <TD nowrap align="center"> 
          <INPUT name="E03LCDM01" type="text" value="<%= msg040003.getE03LCDM01().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
          <INPUT name="E03LCDM02" type="text" value="<%= msg040003.getE03LCDM02().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
          <INPUT name="E03LCDM03" type="text" value="<%= msg040003.getE03LCDM03().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
          <INPUT name="E03LCDM04" type="text" value="<%= msg040003.getE03LCDM04().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
          <INPUT name="E03LCDM05" type="text" value="<%= msg040003.getE03LCDM05().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
          <INPUT name="E03LCDM06" type="text" value="<%= msg040003.getE03LCDM06().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
      </TD>
    </TR>
</TABLE>
</BODY>
</HTML>
