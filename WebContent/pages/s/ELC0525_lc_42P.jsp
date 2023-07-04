<HTML>
<HEAD>
<TITLE>Deferred Payment Details (42P)</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg050003" class="datapro.eibs.beans.ELC050003Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(lc_apr_cc_new);
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
<H3 align="center">Detalle de Pago Diferido (42P)<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" 
alt="ELC0525_lc_42P.jsp"></H3>
<HR size="4">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500" >
  <INPUT NAME="SCREEN" TYPE=HIDDEN VALUE="35" readonly="readonly">
   
<TABLE class="tableinfo">
    <TR > 
      <TD nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Producto : </B></DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
                <INPUT type="text" name="E03LCDPRO" size="5" maxlength="4" value="<%= userPO.getProdCode() %>" readonly>
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right"></DIV>
            </TD>
            <TD nowrap width="16%"> 
            </TD>
          </TR>        
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Banco : </B></DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%= userPO.getBank() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Numero Carta de Credito : </B></DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="left"><B> 
                <INPUT type="text" readonly name="E03LCDACC" size="21" maxlength="20" value="<%= msg050003.getE03LCDACC().trim()%>">
                </B> </DIV>
            </TD>
          </TR>               
        </TABLE>
      </TD>
    </TR>
  </TABLE>
<BR>
<TABLE class="tableinfo">
    <TR > 
      <TD nowrap align="center"> 
          <INPUT name="E03LCDM01" type="text" value="<%= msg050003.getE03LCDM01().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
          <INPUT name="E03LCDM02" type="text" value="<%= msg050003.getE03LCDM02().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
          <INPUT name="E03LCDM03" type="text" value="<%= msg050003.getE03LCDM03().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
          <INPUT name="E03LCDM04" type="text" value="<%= msg050003.getE03LCDM04().trim()%>" size="45" maxlength="35" readonly="readonly"><BR>
      </TD>
    </TR>
  </TABLE>
  
</FORM>
</BODY>
</HTML>
