<HTML>
<HEAD>
<TITLE>Description of Goods (45A)</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lcAdd" class="datapro.eibs.beans.ELC051003Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
builtNewMenu(<%= lcAdd.getE03LCMOPT().trim().equals("N") ? "lc_opening" : "lc_maint"%>);
initMenu();
</SCRIPT>

</HEAD>

<BODY bgcolor="#FFFFFF">


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> <H3 align="center">Narrativa para el Banco Pagador (77A)<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_45A_info.jsp, ELC0510"></H3>
<HR size="4">
<FORM METHOD="post" target="_blank" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEWP0010" >
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="77A">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="11">
  <INPUT TYPE=HIDDEN NAME="SERVLET" VALUE="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510">
</FORM>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2000">
  <INPUT TYPE=HIDDEN NAME="E03LCDFCD" VALUE="77A">

  <table class="tableinfo" cellspacing="0" cellpadding="4" width="100%" border="0">
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

  <p align="center"><A href="javascript:getClausula('77A','11','<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510')"><b>Copiar Clausula 
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0"></b></a></p>
<TABLE class="tableinfo">
    <TR > 
      <TD nowrap align="center"> 
      <div style="height:320px; overflow-y: scroll">
          <INPUT type="text" name="E03LCDM01" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM01().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM02" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM02().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM03" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM03().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM04" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM04().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM05" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM05().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM06" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM06().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM07" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM07().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM08" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM08().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM09" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM09().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM10" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM10().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM11" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM11().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM12" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM12().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM13" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM13().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM14" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM14().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM15" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM15().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM16" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM16().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM17" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM17().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM18" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM18().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM19" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM19().trim()%>"><BR>
          <INPUT type="text" name="E03LCDM20" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM20().trim()%>"><BR>
      </div></TD>
    </TR>
  </TABLE>
  <P align="center"> 
   <INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </P>
</FORM>
</BODY>
</HTML>
