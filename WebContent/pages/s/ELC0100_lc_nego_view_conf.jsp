 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Negociaciones de Cartas de Creditos</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "msg" class= "datapro.eibs.beans.ELC056002Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT LANGUAGE="javascript">
	builtNewMenu(lc_nego_apr);
	initMenu();
</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
 userPO.setNextPage("ELC0560_lc_negotiation_enter_maint.jsp");
%></HEAD>

<BODY>

<H3 align="center">Confirmacion de Pago/Negociación de Cartas de Crédito
<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"
alt="ELC0100_lc_nego_view_conf.jsp"></H3>
<HR size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0560" >
<INPUT type="hidden" name="SCREEN" value="8">

<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
    <TD>&nbsp;</TD>
    <TD align="center"><B>Mda</B></TD>
    <TD align="center"><B>Monto</B></TD>
    <TD align="center"><B>Equivalentes</B></TD>
    </TR>
  
	  <TR >
	    <TD align="left">Principal</TD>
	    <TD align="center"><INPUT type="text" name="E02LCMCCY" value="<%=msg.getE02LCMCCY()%>" size="15" maxlength="13" readonly></TD>	    
	    <TD align="center"><INPUT name="E02DRWAMN" type="text" value="<%=msg.getE02DRWAMN()%>" size="20" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT name="E02EQVDRW" type="text" value="<%=msg.getE02EQVDRW()%>" size="15" maxlength="13" readonly></TD>
	  </TR>
	  	  <TR>
	    <TD align="left">Comisiones Aplicante</TD>
	    <TD align="center"><INPUT type="text" name="E02DEBCY2" value="<%=msg.getE02DEBCY2()%>" size="15" maxlength="13" readonly></TD>	    
	    <TD align="center"><INPUT name="E02COMAPP" type="text" value="<%=msg.getE02COMAPP()%>" size="20" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT name="E02EQVAPP" type="text" value="<%=msg.getE02EQVAPP()%>" size="15" maxlength="13" readonly></TD>
	  	  </TR>
	  <TR>
	    <TD align="left">Comisiones Beneficiario</TD>
	    <TD align="center"><INPUT type="text" name="E02PMTCCY" value="<%=msg.getE02PMTCCY()%>" size="15" maxlength="13" readonly></TD>	    
	    <TD align="center"><INPUT name="E02COMBNF" type="text" value="<%=msg.getE02COMBNF()%>" size="20" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT name="E02EQVBNF" type="text" value="<%=msg.getE02EQVBNF()%>" size="15" maxlength="13" readonly></TD>
	  </TR>
	  <TR>
	    <TD align="left">Impuestos Aplicante</TD>
	    <TD align="center"><INPUT type="text" name="E02DEBCY2" value="<%=msg.getE02DEBCY2()%>" size="15" maxlength="13" readonly></TD>	    
	    <TD align="center"><INPUT name="E02IVAAPP" type="text" value="<%=msg.getE02IVAAPP()%>" size="20" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT name="E02IVABNF" type="text" value="<%=msg.getE02IVABNF()%>" size="15" maxlength="13" readonly></TD>
	  </TR>
<TR>
	    <TD align="left">Producto Neto</TD>
	    <TD align="center"><INPUT type="text" name="E02PMTCCY" value="<%=msg.getE02PMTCCY()%>" size="15" maxlength="13" readonly></TD>	    
	    <TD align="center"><INPUT name="E02DRFTBN" type="text" value="<%=msg.getE02DRFTBN()%>" size="20" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT name="E02EQVDRF" type="text" value="<%=msg.getE02EQVDRF()%>" size="15" maxlength="13" readonly></TD>
</TR>
</TABLE>
<BR>
<% double TotalDeb = 0.00;
   double TotalCre = 0.00;
 %>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
    <TD align="center"><B>Agencia</B></TD>
    <TD align="center"><STRONG>Mda</STRONG></TD>
    <TD align="center"><B>Cuenta Contable</B></TD>
    <TD align="center"><B>Cuenta</B></TD>
    <TD align="center"><B>Descripcion</B></TD>
    <TD align="center"><B>Debito</B></TD>
    <TD align="center"><B>Credito</B></TD>
  </TR>
 <%  if (msg.getBigDecimalE02DEB01().doubleValue() > 0.00 || msg.getBigDecimalE02CRE01().doubleValue()>0.00){
       TotalDeb +=  msg.getBigDecimalE02DEB01().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE01().doubleValue();
 
   %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN01() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY01() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN01() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC01() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD01() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB01() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE01() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB02().doubleValue() > 0.00 || msg.getBigDecimalE02CRE02().doubleValue()>0.00){
       TotalDeb +=  msg.getBigDecimalE02DEB02().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE02().doubleValue();
   %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN02() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY02() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN02() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC02() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD02() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB02() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE02() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB03().doubleValue() > 0.00 || msg.getBigDecimalE02CRE03().doubleValue()>0.00){ 
        TotalDeb +=  msg.getBigDecimalE02DEB03().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE03().doubleValue(); %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN03() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY03() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN03() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC03() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD03() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB03() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE03() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB04().doubleValue() > 0.00 || msg.getBigDecimalE02CRE04().doubleValue()>0.00){  
        TotalDeb +=  msg.getBigDecimalE02DEB04().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE04().doubleValue();%>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN04() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY04() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN04() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC04() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD04() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB04() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE04() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB05().doubleValue() > 0.00 || msg.getBigDecimalE02CRE05().doubleValue()>0.00){ 
        TotalDeb +=  msg.getBigDecimalE02DEB05().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE05().doubleValue(); %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN05() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY05() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN05() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC05() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD05() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB05() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE05() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB06().doubleValue() > 0.00 || msg.getBigDecimalE02CRE06().doubleValue()>0.00){ 
        TotalDeb +=  msg.getBigDecimalE02DEB06().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE06().doubleValue(); %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN06() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY06() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN06() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC06() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD06() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB06() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE06() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> <%  if (msg.getBigDecimalE02DEB07().doubleValue() > 0.00 || msg.getBigDecimalE02CRE07().doubleValue()>0.00){ 
        TotalDeb +=  msg.getBigDecimalE02DEB07().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE07().doubleValue(); %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN07() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY07() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN07() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC07() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD07() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB07() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE07() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> <%  if (msg.getBigDecimalE02DEB08().doubleValue() > 0.00 || msg.getBigDecimalE02CRE08().doubleValue()>0.00){
        TotalDeb +=  msg.getBigDecimalE02DEB08().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE08().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN08() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY08() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN08() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC08() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD08() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB08() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE08() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> <%  if (msg.getBigDecimalE02DEB09().doubleValue() > 0.00 || msg.getBigDecimalE02CRE09().doubleValue()>0.00){  
        TotalDeb +=  msg.getBigDecimalE02DEB09().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE09().doubleValue();%>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN09() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY09() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN09() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC09() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD09() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB09() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE09() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> <%  if (msg.getBigDecimalE02DEB10().doubleValue() > 0.00 || msg.getBigDecimalE02CRE10().doubleValue()>0.00){
        TotalDeb +=  msg.getBigDecimalE02DEB10().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE10().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN10() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY10() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN10() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC10() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD10() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB10() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE10() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> <%  if (msg.getBigDecimalE02DEB11().doubleValue() > 0.00 || msg.getBigDecimalE02CRE11().doubleValue()>0.00){ 
        TotalDeb +=  msg.getBigDecimalE02DEB11().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE11().doubleValue(); %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN11() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY11() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN11() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC11() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD11() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB11() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE11() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> <%  if (msg.getBigDecimalE02DEB12().doubleValue() > 0.00 || msg.getBigDecimalE02CRE12().doubleValue()>0.00){ 
        TotalDeb +=  msg.getBigDecimalE02DEB12().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE12().doubleValue(); %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN12() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY12() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN12() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC12() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD12() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB12() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE12() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> <%  if (msg.getBigDecimalE02DEB13().doubleValue() > 0.00 || msg.getBigDecimalE02CRE13().doubleValue()>0.00){
        TotalDeb +=  msg.getBigDecimalE02DEB13().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE13().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN13() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY13() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN13() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC13() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD13() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB13() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE13() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> <%  if (msg.getBigDecimalE02DEB14().doubleValue() > 0.00 || msg.getBigDecimalE02CRE14().doubleValue()>0.00){
         TotalDeb +=  msg.getBigDecimalE02DEB14().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE14().doubleValue();  %> 
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN14() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY14() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN14() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC14() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD14() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB14() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE14() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB15().doubleValue() > 0.00 || msg.getBigDecimalE02CRE15().doubleValue()>0.00){  
         TotalDeb +=  msg.getBigDecimalE02DEB15().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE15().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN15() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY15() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN15() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC15() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD15() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB15() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE15() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> 
 <%  if (msg.getBigDecimalE02DEB16().doubleValue() > 0.00 || msg.getBigDecimalE02CRE16().doubleValue()>0.00){  
         TotalDeb +=  msg.getBigDecimalE02DEB16().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE16().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN16() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY16() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN16() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC16() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD16() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB16() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE16() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> 
  <%  if (msg.getBigDecimalE02DEB17().doubleValue() > 0.00 || msg.getBigDecimalE02CRE17().doubleValue()>0.00){ 
          TotalDeb +=  msg.getBigDecimalE02DEB17().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE17().doubleValue();  %> 
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN17() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY17() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN17() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC17() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD17() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB17() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE17() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB18().doubleValue() > 0.00 || msg.getBigDecimalE02CRE18().doubleValue()>0.00){ 
         TotalDeb +=  msg.getBigDecimalE02DEB18().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE18().doubleValue();  %> 
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN18() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY18() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN18() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC18() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD18() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB18() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE18() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> 
 <%  if (msg.getBigDecimalE02DEB19().doubleValue() > 0.00 || msg.getBigDecimalE02CRE19().doubleValue()>0.00){
         TotalDeb +=  msg.getBigDecimalE02DEB19().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE19().doubleValue();  %>  
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN19() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY19() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN19() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC19() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD19() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB19() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE19() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> 
 <%  if (msg.getBigDecimalE02DEB20().doubleValue() > 0.00 || msg.getBigDecimalE02CRE20().doubleValue()>0.00){
         TotalDeb +=  msg.getBigDecimalE02DEB20().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE20().doubleValue();  %>  
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN20() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY20() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN20() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC20() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD20() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB20() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE20() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> 
 <%  if (msg.getBigDecimalE02DEB21().doubleValue() > 0.00 || msg.getBigDecimalE02CRE21().doubleValue()>0.00){ 
         TotalDeb +=  msg.getBigDecimalE02DEB21().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE21().doubleValue();  %> 
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN21() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY21() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN21() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC21() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD21() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB21() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE21() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB22().doubleValue() > 0.00 || msg.getBigDecimalE02CRE22().doubleValue()>0.00){ 
         TotalDeb +=  msg.getBigDecimalE02DEB22().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE22().doubleValue();  %> 
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN22() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY22() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN22() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC22() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD22() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB22() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE22() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %> 
 <%  if (msg.getBigDecimalE02DEB23().doubleValue() > 0.00 || msg.getBigDecimalE02CRE23().doubleValue()>0.00){ 
         TotalDeb +=  msg.getBigDecimalE02DEB23().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE23().doubleValue();  %> 
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN23() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY23() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN23() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC23() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD23() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB23() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE23() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB24().doubleValue() > 0.00 || msg.getBigDecimalE02CRE24().doubleValue()>0.00){  
         TotalDeb +=  msg.getBigDecimalE02DEB24().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE24().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN24() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY24() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN24() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC24() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD24() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB24() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE24() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
  <%  if (msg.getBigDecimalE02DEB25().doubleValue() > 0.00 || msg.getBigDecimalE02CRE25().doubleValue()>0.00){ 
         TotalDeb +=  msg.getBigDecimalE02DEB25().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE25().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN25() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY25() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN25() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC25() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD25() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB25() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE25() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB26().doubleValue() > 0.00 || msg.getBigDecimalE02CRE26().doubleValue()>0.00){  
         TotalDeb +=  msg.getBigDecimalE02DEB26().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE26().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN26() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY26() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN26() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC26() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD26() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB26() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE26() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB27().doubleValue() > 0.00 || msg.getBigDecimalE02CRE27().doubleValue()>0.00){  
          TotalDeb +=  msg.getBigDecimalE02DEB27().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE27().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN27() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY27() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN27() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC27() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD27() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB27() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE27() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB28().doubleValue() > 0.00 || msg.getBigDecimalE02CRE28().doubleValue()>0.00){ 
         TotalDeb +=  msg.getBigDecimalE02DEB28().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE28().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN28() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY28() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN28() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC28() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD28() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB28() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE28() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB29().doubleValue() > 0.00 || msg.getBigDecimalE02CRE29().doubleValue()>0.00){  
         TotalDeb +=  msg.getBigDecimalE02DEB29().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE29().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN29() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY29() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN29() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC29() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD29() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB29() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE29() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>
 <%  if (msg.getBigDecimalE02DEB30().doubleValue() > 0.00 || msg.getBigDecimalE02CRE30().doubleValue()>0.00){     
       TotalDeb +=  msg.getBigDecimalE02DEB30().doubleValue();
       TotalCre +=  msg.getBigDecimalE02CRE30().doubleValue();  %>
	  <TR>
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= msg.getE02BRN30() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= msg.getE02CCY30() %>" size="5" maxlength="5" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= msg.getE02GLN30() %>" size="20" maxlength="20" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= msg.getE02ACC30() %>" size="15" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= msg.getE02TRD30() %>" size="32" maxlength="32" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02DEB30() %>" size="17" maxlength="17" readonly></TD>
	    <TD align="right"><INPUT type="text" name="CRE1" value="<%= msg.getE02CRE30() %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%} %>    
</TABLE>
<BR>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
     <TD align="right" width="70%"><B>Totales</B></TD>
     <TD align="center" width="15%"><B><INPUT type="text" name="TOTCRE" value="<%= Util.formatCCY(TotalDeb)  %>" size="17" maxlength="17" readonly></B></TD>
     <TD align="center" width="15%"><B><INPUT type="text" name="TOTDEB" value="<%= Util.formatCCY(TotalCre)  %>" size="17" maxlength="17" readonly></B></TD>
  </TR>
</TABLE><INPUT type="hidden" name="H01FLGWK1" value=""><P align="center"> &nbsp; &nbsp; &nbsp;  
</P>
</FORM>

</BODY>
</HTML>
