<html>
<head>
<title>Documents Required (46A)</title>
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
<h3 align="center">Documentos Requeridos (46A)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_46A_info.jsp, ELC0500"></h3>
<hr size="4">

<FORM METHOD="post" target="newpage" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEWP0010" >
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="46A">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="11">
  <INPUT TYPE=HIDDEN NAME="SERVLET" VALUE="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500">
</FORM>

<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2000">
  <INPUT TYPE=HIDDEN NAME="E03LCDFCD" VALUE="46A">
   
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

  <p align="center"><A href="javascript:document.forms[0].submit();"><b>Copiar Clausula 
	<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0"></b></a></p>
<table class="tableinfo">
    <tr > 
      <td nowrap align="center"> 
      <div style="height:320px; overflow-y: scroll">
          <input type="text" name="E03LCDM01" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM01().trim()%>"><br>
          <input type="text" name="E03LCDM02" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM02().trim()%>"><br>
          <input type="text" name="E03LCDM03" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM03().trim()%>"><br>
          <input type="text" name="E03LCDM04" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM04().trim()%>"><br>
          <input type="text" name="E03LCDM05" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM05().trim()%>"><br>
          <input type="text" name="E03LCDM06" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM06().trim()%>"><br>
          <input type="text" name="E03LCDM07" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM07().trim()%>"><br>
          <input type="text" name="E03LCDM08" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM08().trim()%>"><br>
          <input type="text" name="E03LCDM09" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM09().trim()%>"><br>
          <input type="text" name="E03LCDM10" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM10().trim()%>"><br>
          <input type="text" name="E03LCDM11" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM11().trim()%>"><br>
          <input type="text" name="E03LCDM12" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM12().trim()%>"><br>
          <input type="text" name="E03LCDM13" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM13().trim()%>"><br>
          <input type="text" name="E03LCDM14" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM14().trim()%>"><br>
          <input type="text" name="E03LCDM15" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM15().trim()%>"><br>
          <input type="text" name="E03LCDM16" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM16().trim()%>"><br>
          <input type="text" name="E03LCDM17" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM17().trim()%>"><br>
          <input type="text" name="E03LCDM18" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM18().trim()%>"><br>
          <input type="text" name="E03LCDM19" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM19().trim()%>"><br>
          <input type="text" name="E03LCDM20" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM20().trim()%>"><br>
          <input type="text" name="E03LCDM21" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM21().trim()%>"><br>
          <input type="text" name="E03LCDM22" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM22().trim()%>"><br>
          <input type="text" name="E03LCDM23" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM23().trim()%>"><br>
          <input type="text" name="E03LCDM24" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM24().trim()%>"><br>
          <input type="text" name="E03LCDM25" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM25().trim()%>"><br>
          <input type="text" name="E03LCDM26" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM26().trim()%>"><br>
          <input type="text" name="E03LCDM27" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM27().trim()%>"><br>
          <input type="text" name="E03LCDM28" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM28().trim()%>"><br>
          <input type="text" name="E03LCDM29" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM29().trim()%>"><br>
          <input type="text" name="E03LCDM30" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM30().trim()%>"><br>
          <input type="text" name="E03LCDM31" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM31().trim()%>"><br>
          <input type="text" name="E03LCDM32" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM32().trim()%>"><br>
          <input type="text" name="E03LCDM33" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM33().trim()%>"><br>
          <input type="text" name="E03LCDM34" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM34().trim()%>"><br>
          <input type="text" name="E03LCDM35" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM35().trim()%>"><br>
          <input type="text" name="E03LCDM36" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM36().trim()%>"><br>
          <input type="text" name="E03LCDM37" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM37().trim()%>"><br>
          <input type="text" name="E03LCDM38" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM38().trim()%>"><br>
          <input type="text" name="E03LCDM39" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM39().trim()%>"><br>
          <input type="text" name="E03LCDM40" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM40().trim()%>"><br>
          <input type="text" name="E03LCDM41" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM41().trim()%>"><br>
          <input type="text" name="E03LCDM42" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM42().trim()%>"><br>
          <input type="text" name="E03LCDM43" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM43().trim()%>"><br>
          <input type="text" name="E03LCDM44" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM44().trim()%>"><br>
          <input type="text" name="E03LCDM45" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM45().trim()%>"><br>
          <input type="text" name="E03LCDM46" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM46().trim()%>"><br>
          <input type="text" name="E03LCDM47" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM47().trim()%>"><br>
          <input type="text" name="E03LCDM48" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM48().trim()%>"><br>
          <input type="text" name="E03LCDM49" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM49().trim()%>"><br>
          <input type="text" name="E03LCDM50" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM50().trim()%>"><br>
          <input type="text" name="E03LCDM51" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM51().trim()%>"><br>
          <input type="text" name="E03LCDM52" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM52().trim()%>"><br>
          <input type="text" name="E03LCDM53" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM53().trim()%>"><br>
          <input type="text" name="E03LCDM54" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM54().trim()%>"><br>
          <input type="text" name="E03LCDM55" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM55().trim()%>"><br>
          <input type="text" name="E03LCDM56" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM56().trim()%>"><br>
          <input type="text" name="E03LCDM57" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM57().trim()%>"><br>
          <input type="text" name="E03LCDM58" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM58().trim()%>"><br>
          <input type="text" name="E03LCDM59" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM59().trim()%>"><br>
          <input type="text" name="E03LCDM60" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM60().trim()%>"><br>
          <input type="text" name="E03LCDM61" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM61().trim()%>"><br>
          <input type="text" name="E03LCDM62" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM62().trim()%>"><br>
          <input type="text" name="E03LCDM63" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM63().trim()%>"><br>
          <input type="text" name="E03LCDM64" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM64().trim()%>"><br>
          <input type="text" name="E03LCDM65" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM65().trim()%>"><br>
          <input type="text" name="E03LCDM66" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM66().trim()%>"><br>
          <input type="text" name="E03LCDM67" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM67().trim()%>"><br>
          <input type="text" name="E03LCDM68" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM68().trim()%>"><br>
          <input type="text" name="E03LCDM69" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM69().trim()%>"><br>
          <input type="text" name="E03LCDM70" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM70().trim()%>"><br>
          <input type="text" name="E03LCDM71" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM71().trim()%>"><br>
          <input type="text" name="E03LCDM72" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM72().trim()%>"><br>
          <input type="text" name="E03LCDM73" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM73().trim()%>"><br>
          <input type="text" name="E03LCDM74" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM74().trim()%>"><br>
          <input type="text" name="E03LCDM75" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM75().trim()%>"><br>
          <input type="text" name="E03LCDM76" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM76().trim()%>"><br>
          <input type="text" name="E03LCDM77" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM77().trim()%>"><br>
          <input type="text" name="E03LCDM78" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM78().trim()%>"><br>
          <input type="text" name="E03LCDM79" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM79().trim()%>"><br>
          <input type="text" name="E03LCDM80" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM80().trim()%>"><br>
          <input type="text" name="E03LCDM81" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM81().trim()%>"><br>
          <input type="text" name="E03LCDM82" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM82().trim()%>"><br>
          <input type="text" name="E03LCDM83" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM83().trim()%>"><br>
          <input type="text" name="E03LCDM84" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM84().trim()%>"><br>
          <input type="text" name="E03LCDM85" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM85().trim()%>"><br>
          <input type="text" name="E03LCDM86" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM86().trim()%>"><br>
          <input type="text" name="E03LCDM87" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM87().trim()%>"><br>
          <input type="text" name="E03LCDM88" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM88().trim()%>"><br>
          <input type="text" name="E03LCDM89" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM89().trim()%>"><br>
          <input type="text" name="E03LCDM90" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM90().trim()%>"><br>
          <input type="text" name="E03LCDM91" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM91().trim()%>"><br>
          <input type="text" name="E03LCDM92" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM92().trim()%>"><br>
          <input type="text" name="E03LCDM93" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM93().trim()%>"><br>
          <input type="text" name="E03LCDM94" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM94().trim()%>"><br>
          <input type="text" name="E03LCDM95" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM95().trim()%>"><br>
          <input type="text" name="E03LCDM96" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM96().trim()%>"><br>
          <input type="text" name="E03LCDM97" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM97().trim()%>"><br>
          <input type="text" name="E03LCDM98" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM98().trim()%>"><br>
          <input type="text" name="E03LCDM99" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM99().trim()%>"><br>
          <input type="text" name="E03LCDM00" size="66" maxlength="65" value="<%= lcAdd.getE03LCDM00().trim()%>"><br>
          </div>
      </td>
    </tr>
  </table>
  <p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
