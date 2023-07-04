<HTML>
<HEAD>
<TITLE>Codigos Especiales</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "msgESD000002" class= "datapro.eibs.beans.ESD000002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
	builtNewMenu(lc_apr_standby);
	initMenu();
</SCRIPT>
</HEAD>
<BODY  bgcolor="#FFFFFF">
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<H3 align="center">C&oacute;digos Especiales
<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"
ALT="ELC0525_lc_special_codes_sb.jsp"></H3>
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
        <INPUT type="text" readonly name="E03LCDACC" size="21" maxlength="20" value="<%= msgESD000002.getE02ACC().trim()%>">
      </B></TD>
    </TR>
  </TABLE>
  <H4>C&oacute;digos de Clasificaci&oacute;n</H4> 
  <TABLE cellpadding=2 cellspacing=0 width="100%" border="1" bordercolor="#000000" bgcolor="#FFFFFF" >
    <TR bordercolor="#FFFFFF"> 
      <TD nowrap > 
        <TABLE cellpadding=2 cellspacing=0 width="100%" border="0">
          <TR id="trdark"> 
            <TD width="30%" align="right" nowrap> 
              Oficial Principal :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02OFC" size="5" maxlength="4" value="<%= msgESD000002.getE02OFC().trim()%>" readonly>
              <INPUT type="text" name="D02OFC" size="40" maxlength="35" value="<%= msgESD000002.getD02OFC().trim()%>" readonly>
               
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD width="30%" align="right" nowrap > 
              Oficial Secundario :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02OF2" size="5" maxlength="4" value="<%= msgESD000002.getE02OF2().trim()%>" readonly>
              <INPUT type="text" name="D02OF2" size="40" maxlength="35" value="<%= msgESD000002.getD02OF2().trim()%>" readonly>
               
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD width="30%" align="right" nowrap> 
              C&oacute;digo de Industria :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02INC" size="5" maxlength="4" value="<%= msgESD000002.getE02INC().trim()%>" readonly>
              <INPUT type="text" name="D02INC" size="40" maxlength="35" value="<%= msgESD000002.getD02INC().trim()%>" readonly>
               
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD width="3%" align="right" nowrap> 
              Linea de Negocio :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02BUC" size="5" maxlength="4" value="<%= msgESD000002.getE02BUC().trim()%>" readonly>
              <INPUT type="text" name="D02BUC" size="40" maxlength="35" value="<%= msgESD000002.getD02BUC().trim()%>" readonly>
               
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD width="30%" align="right" nowrap> 
              Pa&iacute;s de Residencia :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02GEC" size="5" maxlength="4" value="<%= msgESD000002.getE02GEC().trim()%>" readonly>
              <INPUT type="text" name="D02GEC" size="40" maxlength="35" value="<%= msgESD000002.getD02GEC().trim()%>" readonly>
               
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD width="30%" align="right" nowrap> 
              Pa&iacute;s de Riesgo :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02GRC" size="5" maxlength="4" value="<%= msgESD000002.getE02GRC().trim()%>" readonly>
              <INPUT type="text" name="D02GRC" size="40" maxlength="35" value="<%= msgESD000002.getD02GRC().trim()%>" readonly>
               
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD width="30%" align="right" nowrap> 
              C&oacute;digo de Usuario 1 :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02UC1" size="5" maxlength="4" value="<%= msgESD000002.getE02UC1().trim()%>" readonly>
              <INPUT type="text" name="D02UC1" size="40" maxlength="35" value="<%= msgESD000002.getD02UC1().trim()%>" readonly>
               
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD width="30%" align="right" nowrap> 
              C&oacute;digo de Usuario 2 :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02UC2" size="5" maxlength="4" value="<%= msgESD000002.getE02UC2().trim()%>" readonly>
              <INPUT type="text" name="D02UC2" size="40" maxlength="35" value="<%= msgESD000002.getD02UC2().trim()%>" readonly>
               
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD width="30%" align="right" nowrap> 
              C&oacute;digo de Usuario 3 :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02UC3" size="5" maxlength="4" value="<%= msgESD000002.getE02UC3().trim()%>" readonly>
              <INPUT type="text" name="D02UC3" size="40" maxlength="35" value="<%= msgESD000002.getD02UC3().trim()%>" readonly>
               
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD width="30%" align="right" nowrap> 
              C&oacute;digo de Usuario 4 :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02UC4" size="5" maxlength="4" value="<%= msgESD000002.getE02UC4().trim()%>" readonly>
              <INPUT type="text" name="D02UC4" size="40" maxlength="35" value="<%= msgESD000002.getD02UC4().trim()%>" readonly>
               
            </TD>
          </TR>
          <TR id="trdark">
            <TD width="30%" align="right" nowrap>
              Origen de los Fondos :</TD>
            <TD nowrap width="70%">
              <INPUT type="text" name="E02ORG" size="5" maxlength="4" value="<%= msgESD000002.getE02ORG().trim()%>" readonly>
              <INPUT type="text" name="D02ORG" size="40" maxlength="35" value="<%= msgESD000002.getD02ORG().trim()%>" readonly>
               
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD width="30%" align="right" nowrap> 
              Destino de los Fondos :</TD>
            <TD nowrap width="70%"> 
              <INPUT type="text" name="E02DST" size="5" maxlength="4" value="<%= msgESD000002.getE02DST().trim()%>" readonly>
              <INPUT type="text" name="D02DST" size="40" maxlength="35" value="<%= msgESD000002.getD02DST().trim()%>" readonly>
            </TD>
          </TR>
        </TABLE>
      </TD>
    </TR>
  </TABLE>
</BODY>
</HTML>

