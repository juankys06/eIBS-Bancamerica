<html>
<head>
<title>Description of Goods (45)</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg" class="datapro.eibs.beans.ELC040003Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </script>

<script LANGUAGE="javascript">

<%if (!userPO.getPurpose().equals("NEW")){%>

	builtNewMenu(sb_opening);

<%}%>

   
</script>

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

	String title = "Stand By Carta de Crédito " + (userPO.getPurpose().equals("NEW") ? "Nueva" : "Mantenimiento") + " ["; 
	int lines = 0;
	int chars = 0;
	
	datapro.eibs.master.SwiftStructure swift = new datapro.eibs.master.SwiftStructure();
	
	if (swift.locateField(msg.getE03LCDFCD().trim())) {
		title = title + swift.getLabel("sp");
		lines = swift.getLines();
		chars = swift.getChars();
	}
	
	title = title + " (" + msg.getE03LCDFCD().trim() + ")]";

%> 

<H3 align="center"><%= title %><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="sb_field_info.jsp, ELC0400"></h3>
<hr size="4">

<FORM METHOD="post" target="newpage" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEWP0010">
	<INPUT TYPE=HIDDEN NAME="TYPE" VALUE="<%= msg.getE03LCDFCD() %>"> <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="11">
	<INPUT TYPE=HIDDEN NAME="SERVLET" VALUE="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400"></FORM>

 <form METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2000">
  <INPUT TYPE=HIDDEN NAME="E03LCDFCD" VALUE="<%= msg.getE03LCDFCD() %>">
   
<table class="tableinfo" cellspacing="0" cellpadding="4" width="100%" border="0">
	<TR id="trdark">
		<TD nowrap align="right" width="10%"><B> Producto:</B></TD>
		<TD nowrap align="left" width="20%">
			<INPUT type="text" name="E03LCDPRO" size="5" maxlength="4" value="<%=msg.getE03LCDPRO().trim()%>" readonly></TD>
		<TD nowrap align="right" width="10%"><B>Banco:</B></TD>
		<TD nowrap align="left" width="20%">
			<INPUT type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%=msg.getE03LCDBNK().trim()%>"></TD>
		<TD nowrap width="25%" align="right"><B>Carta de Crédito Número :</B></TD>
		<TD nowrap align="left" width="15%">
			<INPUT type="text" readonly name="E03LCDACC" size="21" maxlength="20"value="<%=msg.getE03LCDACC().trim()%>"></TD>
	</TR>
</TABLE>

  <p align="center">
  	<A href="javascript:getClausula('<%= msg.getE03LCDFCD() %>','11','<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400')">
  	<b>Adicionar Clausula<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></b></a></p>
  	
<TABLE class="tableinfo">
    <TR > 
      <TD nowrap align="center"> 
      <div style="height:320px; overflow-y: scroll">
          <INPUT type="text" name="E03LCDM01" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM01().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM02" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM02().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM03" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM03().trim()%>"><BR>
      <% if (lines > 3) { %>
          <INPUT type="text" name="E03LCDM04" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM04().trim()%>"><BR>
      <% } 
         if (lines > 4) { %>
          <INPUT type="text" name="E03LCDM05" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM05().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM06" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM06().trim()%>"><BR>
      <% } 
         if (lines > 6) { %>
          <INPUT type="text" name="E03LCDM07" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM07().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM08" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM08().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM09" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM09().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM10" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM10().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM11" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM11().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM12" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM12().trim()%>"><BR>
      <% } 
         if (lines > 12) { %>
          <INPUT type="text" name="E03LCDM13" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM13().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM14" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM14().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM15" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM15().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM16" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM16().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM17" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM17().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM18" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM18().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM19" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM19().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM20" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM20().trim()%>"><BR>
      <% } 
         if (lines > 20) { %>
          <INPUT type="text" name="E03LCDM21" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM21().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM22" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM22().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM23" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM23().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM24" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM24().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM25" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM25().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM26" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM26().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM27" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM27().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM28" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM28().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM29" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM29().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM30" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM30().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM31" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM31().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM32" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM32().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM33" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM33().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM34" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM34().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM35" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM35().trim()%>"><BR>
      <% } 
         if (lines > 35) { %>
          <INPUT type="text" name="E03LCDM36" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM36().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM37" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM37().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM38" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM38().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM39" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM39().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM40" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM40().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM41" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM41().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM42" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM42().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM43" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM43().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM44" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM44().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM45" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM45().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM46" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM46().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM47" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM47().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM48" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM48().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM49" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM49().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM50" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM50().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM51" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM51().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM52" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM52().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM53" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM53().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM54" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM54().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM55" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM55().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM56" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM56().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM57" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM57().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM58" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM58().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM59" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM59().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM60" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM60().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM61" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM61().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM62" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM62().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM63" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM63().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM64" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM64().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM65" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM65().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM66" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM66().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM67" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM67().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM68" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM68().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM69" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM69().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM70" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM70().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM71" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM71().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM72" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM72().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM73" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM73().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM74" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM74().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM75" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM75().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM76" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM76().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM77" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM77().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM78" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM78().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM79" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM79().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM80" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM80().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM81" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM81().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM82" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM82().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM83" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM83().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM84" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM84().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM85" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM85().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM86" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM86().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM87" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM87().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM88" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM88().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM89" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM89().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM90" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM90().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM91" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM91().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM92" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM92().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM93" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM93().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM94" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM94().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM95" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM95().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM96" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM96().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM97" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM97().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM98" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM98().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM99" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM99().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM00" size="70" maxlength="<%= chars %>" value="<%= msg.getE03LCDM00().trim()%>"><BR>
	  <% } %>          
      </div></TD>
    </TR>
  </TABLE>
  
  <P align="center"><INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar"></P>
</form>
</body>
</html>
