<HTML>
<HEAD>
<TITLE>Instrucciones Especiales</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "msgLC" class= "datapro.eibs.beans.ESD000005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

builtNewMenu(<%= userPO.getCusType().equals("N") ? "lc_opening" : "lc_maint"%>);

</SCRIPT>

</HEAD>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<H3 align="center">Instrucciones Especiales<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ELC0510_lc_opening_export.jsp"></H3>
<HR size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="104">
  <TABLE class="tableinfo">
    <TR > 
      <TD nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Numero de Carta de Credito :</B></DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
                <INPUT type="text" name="E05ACC" size="14" maxlength="12" value="<%= msgLC.getE05ACC().trim()%>" readonly>
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Producto : </B></DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="left"><B>
                <INPUT type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>"><BR>
                </B> </DIV>
            </TD>
          </TR>
        </TABLE>
      </TD>
    </TR>
  </TABLE>
  <P>&nbsp; </P>
  <TABLE class="tableinfo">
    <TR  > 
      <TD> 
        <P align="center"> 
          <INPUT type="text" name="E15DSC" size="80" maxlength="80" value="<%= msgLC.getE15DSC().trim()%>"><BR>
          <INPUT type="text" name="E25DSC" size="80" maxlength="80" value="<%= msgLC.getE25DSC().trim()%>"><BR>
          <INPUT type="text" name="E35DSC" size="80" maxlength="80" value="<%= msgLC.getE35DSC().trim()%>"><BR>
          <INPUT type="text" name="E45DSC" size="80" maxlength="80" value="<%= msgLC.getE45DSC().trim()%>"><BR>
          <INPUT type="text" name="E55DSC" size="80" maxlength="80" value="<%= msgLC.getE55DSC().trim()%>"><BR>
          <INPUT type="text" name="E65DSC" size="80" maxlength="80" value="<%= msgLC.getE65DSC().trim()%>"><BR>
          <INPUT type="text" name="E75DSC" size="80" maxlength="80" value="<%= msgLC.getE75DSC().trim()%>"><BR>
          <INPUT type="text" name="E85DSC" size="80" maxlength="80" value="<%= msgLC.getE85DSC().trim()%>"><BR>
          <INPUT type="text" name="E95DSC" size="80" maxlength="80" value="<%= msgLC.getE95DSC().trim()%>"><BR>
          <INPUT type="text" name="E05DSC" size="80" maxlength="80" value="<%= msgLC.getE05DSC().trim()%>"><BR>
          <INPUT type="text" name="EA5DSC" size="80" maxlength="80" value="<%= msgLC.getEA5DSC().trim()%>"><BR>
          <INPUT type="text" name="EB5DSC" size="80" maxlength="80" value="<%= msgLC.getEB5DSC().trim()%>"><BR>
          <INPUT type="text" name="EC5DSC" size="80" maxlength="80" value="<%= msgLC.getEC5DSC().trim()%>"><BR>
          <INPUT type="text" name="ED5DSC" size="80" maxlength="80" value="<%= msgLC.getED5DSC().trim()%>"><BR>
          <INPUT type="text" name="EE5DSC" size="80" maxlength="80" value="<%= msgLC.getEE5DSC().trim()%>"><BR>
          <INPUT type="text" name="EF5DSC" size="80" maxlength="80" value="<%= msgLC.getEF5DSC().trim()%>"><BR>
          <INPUT type="text" name="EG5DSC" size="80" maxlength="80" value="<%= msgLC.getEG5DSC().trim()%>"><BR>
          <INPUT type="text" name="EH5DSC" size="80" maxlength="80" value="<%= msgLC.getEH5DSC().trim()%>"><BR>
          <INPUT type="text" name="EI5DSC" size="80" maxlength="80" value="<%= msgLC.getEI5DSC().trim()%>"><BR>
          <INPUT type="text" name="EJ5DSC" size="80" maxlength="80" value="<%= msgLC.getEJ5DSC().trim()%>"><BR>
        </P>
      </TD>
    </TR>
  </TABLE>
    <P>
  <DIV align="center"> 
    <INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </DIV>
  </FORM>
</BODY>
</HTML>
