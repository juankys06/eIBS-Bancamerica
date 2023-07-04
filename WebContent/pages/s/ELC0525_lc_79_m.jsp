<html>
<head>
<title>Narrative (79)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg051003" class="datapro.eibs.beans.ELC051003Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(lc_apr_cc_maint);
	initMenu();
   
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
%> 
<h3 align="center">Narrative (79)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_79_info.jsp, ELC0500"></h3>
<hr size="4">


 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2000">
  <INPUT TYPE=HIDDEN NAME="E03LCDFCD" VALUE="79">
   
<table class="tableinfo" cellspacing="0" cellpadding="4" width="100%" border="0">
	<TR id="trdark">
		<TD nowrap align="right" width="10%"><B> Producto:</B></TD>
	  <TD nowrap align="left" width="20%"><INPUT type="text" name="E03LCDPRO" size="5" maxlength="4" value="<%= userPO.getProdCode() %>" readonly></TD>
		<TD nowrap align="right" width="10%"><B>Banco:</B></TD>
	  <TD nowrap align="left" width="20%"><INPUT type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%= userPO.getBank()  %>"></TD>
		<TD nowrap width="25%" align="right"><B>Numero Carta de Credito:</B></TD>
		<TD nowrap align="left" width="15%">
			<INPUT type="text" readonly name="E03LCDACC" size="21" maxlength="20"value="<%=msg051003.getE03LCDACC().trim()%>"></TD>
	</TR>
</TABLE>

<BR>
<table class="tableinfo">
    <tr > 
      <td nowrap align="center"> 
	  <div style="height:320px; overflow-y: scroll">
          <input type="text" name="E03LCDM01" size="51" maxlength="50" value="<%= msg051003.getE03LCDM01().trim()%>"><br>
          <input type="text" name="E03LCDM02" size="51" maxlength="50" value="<%= msg051003.getE03LCDM02().trim()%>"><br>
          <input type="text" name="E03LCDM03" size="51" maxlength="50" value="<%= msg051003.getE03LCDM03().trim()%>"><br>
          <input type="text" name="E03LCDM04" size="51" maxlength="50" value="<%= msg051003.getE03LCDM04().trim()%>"><br>
          <input type="text" name="E03LCDM05" size="51" maxlength="50" value="<%= msg051003.getE03LCDM05().trim()%>"><br>
          <input type="text" name="E03LCDM06" size="51" maxlength="50" value="<%= msg051003.getE03LCDM06().trim()%>"><br>
          <input type="text" name="E03LCDM07" size="51" maxlength="50" value="<%= msg051003.getE03LCDM07().trim()%>"><br>
          <input type="text" name="E03LCDM08" size="51" maxlength="50" value="<%= msg051003.getE03LCDM08().trim()%>"><br>
          <input type="text" name="E03LCDM09" size="51" maxlength="50" value="<%= msg051003.getE03LCDM09().trim()%>"><br>
          <input type="text" name="E03LCDM10" size="51" maxlength="50" value="<%= msg051003.getE03LCDM10().trim()%>"><br>
          <input type="text" name="E03LCDM11" size="51" maxlength="50" value="<%= msg051003.getE03LCDM11().trim()%>"><br>
          <input type="text" name="E03LCDM12" size="51" maxlength="50" value="<%= msg051003.getE03LCDM12().trim()%>"><br>
          <input type="text" name="E03LCDM13" size="51" maxlength="50" value="<%= msg051003.getE03LCDM13().trim()%>"><br>
          <input type="text" name="E03LCDM14" size="51" maxlength="50" value="<%= msg051003.getE03LCDM14().trim()%>"><br>
          <input type="text" name="E03LCDM15" size="51" maxlength="50" value="<%= msg051003.getE03LCDM15().trim()%>"><br>
          <input type="text" name="E03LCDM16" size="51" maxlength="50" value="<%= msg051003.getE03LCDM16().trim()%>"><br>
          <input type="text" name="E03LCDM17" size="51" maxlength="50" value="<%= msg051003.getE03LCDM17().trim()%>"><br>
          <input type="text" name="E03LCDM18" size="51" maxlength="50" value="<%= msg051003.getE03LCDM18().trim()%>"><br>
          <input type="text" name="E03LCDM19" size="51" maxlength="50" value="<%= msg051003.getE03LCDM19().trim()%>"><br>
          <input type="text" name="E03LCDM20" size="51" maxlength="50" value="<%= msg051003.getE03LCDM20().trim()%>"><br>
          <input type="text" name="E03LCDM21" size="51" maxlength="50" value="<%= msg051003.getE03LCDM21().trim()%>"><br>
          <input type="text" name="E03LCDM22" size="51" maxlength="50" value="<%= msg051003.getE03LCDM22().trim()%>"><br>
          <input type="text" name="E03LCDM23" size="51" maxlength="50" value="<%= msg051003.getE03LCDM23().trim()%>"><br>
          <input type="text" name="E03LCDM24" size="51" maxlength="50" value="<%= msg051003.getE03LCDM24().trim()%>"><br>
          <input type="text" name="E03LCDM25" size="51" maxlength="50" value="<%= msg051003.getE03LCDM25().trim()%>"><br>
          <input type="text" name="E03LCDM26" size="51" maxlength="50" value="<%= msg051003.getE03LCDM26().trim()%>"><br>
          <input type="text" name="E03LCDM27" size="51" maxlength="50" value="<%= msg051003.getE03LCDM27().trim()%>"><br>
          <input type="text" name="E03LCDM28" size="51" maxlength="50" value="<%= msg051003.getE03LCDM28().trim()%>"><br>
          <input type="text" name="E03LCDM29" size="51" maxlength="50" value="<%= msg051003.getE03LCDM29().trim()%>"><br>
          <input type="text" name="E03LCDM30" size="51" maxlength="50" value="<%= msg051003.getE03LCDM30().trim()%>"><br>
          <input type="text" name="E03LCDM31" size="51" maxlength="50" value="<%= msg051003.getE03LCDM31().trim()%>"><br>
          <input type="text" name="E03LCDM32" size="51" maxlength="50" value="<%= msg051003.getE03LCDM32().trim()%>"><br>
          <input type="text" name="E03LCDM33" size="51" maxlength="50" value="<%= msg051003.getE03LCDM33().trim()%>"><br>
          <input type="text" name="E03LCDM34" size="51" maxlength="50" value="<%= msg051003.getE03LCDM34().trim()%>"><br>
          <input type="text" name="E03LCDM35" size="51" maxlength="50" value="<%= msg051003.getE03LCDM35().trim()%>"><br>
	    </div>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
