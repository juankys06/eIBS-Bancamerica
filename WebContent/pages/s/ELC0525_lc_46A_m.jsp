<HTML>
<HEAD>
<TITLE>Documents Required (46A)</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg051003" class="datapro.eibs.beans.ELC051003Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(lc_apr_cc_maint);
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
<H3 align="center">Documents Required (46A)<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" 
alt="ELC0525_lc_46A_m.jsp"></H3>
<HR size="4">


 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.products.JSELC0510" >
  <INPUT NAME="SCREEN" TYPE=HIDDEN VALUE="15" readonly="readonly">
  <table class="tableinfo" cellspacing="0" cellpadding="4" width="100%" border="0">
    <TR id="trdark">
      <TD nowrap align="right" width="10%"><B> Producto:</B></TD>
      <TD nowrap align="left" width="20%"><INPUT type="text" name="E03LCDPRO" size="5" maxlength="4" value="<%= userPO.getProdCode() %>" readonly></TD>
      <TD nowrap align="right" width="10%"><B>Banco:</B></TD>
      <TD nowrap align="left" width="20%"><INPUT type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%= userPO.getBank()  %>"></TD>
      <TD nowrap width="25%" align="right"><B>Numero Carta de Credito:</B></TD>
      <TD nowrap align="left" width="15%"><INPUT type="text" readonly name="E03LCDACC" size="21" maxlength="20"value="<%=msg051003.getE03LCDACC().trim()%>"></TD>
    </TR>
  </TABLE>
  <BR>
<TABLE class="tableinfo">
    <TR > 
      <TD nowrap align="center">
      	<div style="height: 320px; overflow-y: scroll"> 
          <INPUT name="E03LCDM01" type="text" value="<%= msg051003.getE03LCDM01().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM02" type="text" value="<%= msg051003.getE03LCDM02().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM03" type="text" value="<%= msg051003.getE03LCDM03().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM04" type="text" value="<%= msg051003.getE03LCDM04().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM05" type="text" value="<%= msg051003.getE03LCDM05().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM06" type="text" value="<%= msg051003.getE03LCDM06().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM07" type="text" value="<%= msg051003.getE03LCDM07().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM08" type="text" value="<%= msg051003.getE03LCDM08().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM09" type="text" value="<%= msg051003.getE03LCDM09().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM10" type="text" value="<%= msg051003.getE03LCDM10().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM11" type="text" value="<%= msg051003.getE03LCDM11().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM12" type="text" value="<%= msg051003.getE03LCDM12().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM13" type="text" value="<%= msg051003.getE03LCDM13().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM14" type="text" value="<%= msg051003.getE03LCDM14().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM15" type="text" value="<%= msg051003.getE03LCDM15().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM16" type="text" value="<%= msg051003.getE03LCDM16().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM17" type="text" value="<%= msg051003.getE03LCDM17().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM18" type="text" value="<%= msg051003.getE03LCDM18().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM19" type="text" value="<%= msg051003.getE03LCDM19().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM20" type="text" value="<%= msg051003.getE03LCDM20().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM21" type="text" value="<%= msg051003.getE03LCDM21().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM22" type="text" value="<%= msg051003.getE03LCDM22().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM23" type="text" value="<%= msg051003.getE03LCDM23().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM24" type="text" value="<%= msg051003.getE03LCDM24().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM25" type="text" value="<%= msg051003.getE03LCDM25().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM26" type="text" value="<%= msg051003.getE03LCDM26().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM27" type="text" value="<%= msg051003.getE03LCDM27().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM28" type="text" value="<%= msg051003.getE03LCDM28().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM29" type="text" value="<%= msg051003.getE03LCDM29().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM30" type="text" value="<%= msg051003.getE03LCDM30().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM31" type="text" value="<%= msg051003.getE03LCDM31().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM32" type="text" value="<%= msg051003.getE03LCDM32().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM33" type="text" value="<%= msg051003.getE03LCDM33().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM34" type="text" value="<%= msg051003.getE03LCDM34().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM35" type="text" value="<%= msg051003.getE03LCDM35().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM36" type="text" value="<%= msg051003.getE03LCDM36().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM37" type="text" value="<%= msg051003.getE03LCDM37().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM38" type="text" value="<%= msg051003.getE03LCDM38().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM39" type="text" value="<%= msg051003.getE03LCDM39().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM40" type="text" value="<%= msg051003.getE03LCDM40().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM41" type="text" value="<%= msg051003.getE03LCDM41().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM42" type="text" value="<%= msg051003.getE03LCDM42().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM43" type="text" value="<%= msg051003.getE03LCDM43().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM44" type="text" value="<%= msg051003.getE03LCDM44().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM45" type="text" value="<%= msg051003.getE03LCDM45().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM46" type="text" value="<%= msg051003.getE03LCDM46().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM47" type="text" value="<%= msg051003.getE03LCDM47().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM48" type="text" value="<%= msg051003.getE03LCDM48().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM49" type="text" value="<%= msg051003.getE03LCDM49().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM50" type="text" value="<%= msg051003.getE03LCDM50().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM51" type="text" value="<%= msg051003.getE03LCDM51().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM52" type="text" value="<%= msg051003.getE03LCDM52().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM53" type="text" value="<%= msg051003.getE03LCDM53().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM54" type="text" value="<%= msg051003.getE03LCDM54().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM55" type="text" value="<%= msg051003.getE03LCDM55().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM56" type="text" value="<%= msg051003.getE03LCDM56().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM57" type="text" value="<%= msg051003.getE03LCDM57().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM58" type="text" value="<%= msg051003.getE03LCDM58().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM59" type="text" value="<%= msg051003.getE03LCDM59().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM60" type="text" value="<%= msg051003.getE03LCDM60().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM61" type="text" value="<%= msg051003.getE03LCDM61().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM62" type="text" value="<%= msg051003.getE03LCDM62().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM63" type="text" value="<%= msg051003.getE03LCDM63().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM64" type="text" value="<%= msg051003.getE03LCDM64().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM65" type="text" value="<%= msg051003.getE03LCDM65().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM66" type="text" value="<%= msg051003.getE03LCDM66().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM67" type="text" value="<%= msg051003.getE03LCDM67().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM68" type="text" value="<%= msg051003.getE03LCDM68().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM69" type="text" value="<%= msg051003.getE03LCDM69().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM70" type="text" value="<%= msg051003.getE03LCDM70().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM71" type="text" value="<%= msg051003.getE03LCDM71().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM72" type="text" value="<%= msg051003.getE03LCDM72().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM73" type="text" value="<%= msg051003.getE03LCDM73().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM74" type="text" value="<%= msg051003.getE03LCDM74().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM75" type="text" value="<%= msg051003.getE03LCDM75().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM76" type="text" value="<%= msg051003.getE03LCDM76().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM77" type="text" value="<%= msg051003.getE03LCDM77().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM78" type="text" value="<%= msg051003.getE03LCDM78().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM79" type="text" value="<%= msg051003.getE03LCDM79().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM80" type="text" value="<%= msg051003.getE03LCDM80().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM81" type="text" value="<%= msg051003.getE03LCDM81().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM82" type="text" value="<%= msg051003.getE03LCDM82().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM83" type="text" value="<%= msg051003.getE03LCDM83().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM84" type="text" value="<%= msg051003.getE03LCDM84().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM85" type="text" value="<%= msg051003.getE03LCDM85().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM86" type="text" value="<%= msg051003.getE03LCDM86().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM87" type="text" value="<%= msg051003.getE03LCDM87().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM88" type="text" value="<%= msg051003.getE03LCDM88().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM89" type="text" value="<%= msg051003.getE03LCDM89().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM90" type="text" value="<%= msg051003.getE03LCDM90().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM91" type="text" value="<%= msg051003.getE03LCDM91().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM92" type="text" value="<%= msg051003.getE03LCDM92().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM93" type="text" value="<%= msg051003.getE03LCDM93().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM94" type="text" value="<%= msg051003.getE03LCDM94().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM95" type="text" value="<%= msg051003.getE03LCDM95().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM96" type="text" value="<%= msg051003.getE03LCDM96().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM97" type="text" value="<%= msg051003.getE03LCDM97().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM98" type="text" value="<%= msg051003.getE03LCDM98().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM99" type="text" value="<%= msg051003.getE03LCDM99().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
          <INPUT name="E03LCDM00" type="text" value="<%= msg051003.getE03LCDM00().trim()%>" size="66" maxlength="65" readonly="readonly"><BR>
      	</div>
      </TD>
    </TR>
  </TABLE>
  
</FORM>
</BODY>
</HTML>
