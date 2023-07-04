 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Negociaciones de Cartas de Creditos</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "dcPag" class= "datapro.eibs.beans.EDC006001Message"  scope="session"/>
<jsp:useBean id= "rcPag" class= "datapro.eibs.beans.EDC006002Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">
  function ValidConf(){
     if(document.forms[0].H02FLGWK1.value == "Y"){
        if(document.forms[0].GLNZERO.value == "Y"){
           alert("Error Cuenta Contable en Tabla de Comisiones");
           return false;
        }        
        if(document.forms[0].TOTDEB.value != document.forms[0].TOTCRE.value){
           alert("Debitos y Creditos no cuadran");
           return false;
        }   
     }
     return true;
  }
</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
%>
<%!
	public String fmtDouble(double d) {
		String fmt = "#,##0.00#";
		java.text.DecimalFormat df = new java.text.DecimalFormat(fmt);
		return df.format(d);		
	}
%>
</HEAD>

<BODY>
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0060" onsubmit="return ValidConf()">
<INPUT type="hidden" name="SCREEN" value="4">
<INPUT type="hidden" name="H02FLGWK1" value="">

<H3 align="center">Confirmacion Liquidacion de Cobranza<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="coll_payment_conf.jsp, EDC0060"></H3>
<HR size="4">
<BR>
<TABLE cellspacing="0" cellpadding="2" width="100%" border="0"	class="tableinfo">
	<TBODY>
		<TR id="trdark">
			<TD nowrap width="16%">
			<DIV align="right"><B>Banco :</B></DIV>
			</TD>
			<TD nowrap width="20%">
			<DIV align="left"><INPUT type="text" name="E01DCMBNK" readonly 	size="4" maxlength="2" value="<%=dcPag.getE01DCMBNK().trim()%>"></DIV>
			</TD>
			<TD nowrap width="16%">
			<DIV align="right"><B>Número de Cobranza :</B></DIV>
			</TD>
			<TD nowrap width="16%">
			<DIV align="left"><B> <INPUT type="text" name="E01DCMACC" size="12" maxlength="12" value="<%=dcPag.getE01DCMACC().trim()%>" readonly> </B></DIV>
			</TD>
		</TR>
		<TR id="trclear">
			<TD nowrap width="16%">
			<DIV align="right"><B>Cliente :</B></DIV>
			</TD>
			<TD nowrap width="20%">
			<DIV align="left"><INPUT type="text" name="E01CUSNA1" size="37" maxlength="35" value="<%=dcPag.getE01CUSNA1().trim()%>"></DIV>
			</TD>
			<TD nowrap width="16%">
			<DIV align="right"><B>Producto :</B></DIV>
			</TD>
			<TD nowrap width="16%">
			<DIV align="left"><B> <INPUT type="text" name="E01DCMPRO" size="4" 	maxlength="4" value="<%=dcPag.getE01DCMPRO().trim()%>" readonly> </B></DIV>
			</TD>
		</TR>
		<TR id="trdark">
			<TD nowrap width="16%">
			<DIV align="right"><B>Tipo de Cobranza :</B></DIV>
			</TD>
			<TD nowrap width="16%">
			<DIV align="left"><B> <INPUT type="text" name="E01DCMTYP" size="20" maxlength="16" value="<%=dcPag.getE01DCMTYP().trim()%>"> </B></DIV>
			</TD>
			<TD nowrap width="16%">
			<DIV align="right"><B>Descripcion de Producto :</B></DIV>
			</TD>
			<TD nowrap width="16%">
			<DIV align="left"><B> <INPUT type="text" name="E01DSCPRO" size="30" maxlength="30" value="<%=dcPag.getE01DSCPRO().trim()%>" readonly> </B></DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<BR>
<% double TotalDeb = 0.00;
   double TotalCre = 0.00; 
   boolean glnzero = false;%>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
     <TD align="center" width="5%"><B>Agencia</B></TD>
     <TD align="center" width="5%"><B>Moneda</B></TD>
     <TD align="center" width="10%"><B>Cuenta Contable</B></TD>
     <TD align="center" width="15%"><B>No. de Cuenta</B></TD>
     <TD align="center" width="5%"><B>Cod.Trans.</B></TD>
     <TD align="center" width="40%"><B>Descripcion</B></TD>
     <TD align="center" width="15%"><B>Debito</B></TD>
     <TD align="center" width="15%"><B>Credito</B></TD>
  </TR>
 <% datapro.eibs.beans.JBDC0060_trnsgl BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS01());
    if (BDC0060.getTRNAMT() > 0){
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;
      %>
	  <TR>
  	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" align="right" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" align="right" readonly></TD>
	  </TR>
 
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS02());
   if (BDC0060.getTRNAMT()>0){
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;   
   %> 	  
  	  <TR>
        <TD align="center"><INPUT type="text" name="BRN1" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY2" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4"  align="right" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN2" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC2" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC2" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB2" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE2" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS03());
   if (BDC0060.getTRNAMT()>0){ 
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true; 
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY3" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN3" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC3" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC3" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB3" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE3" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS04());
   if (BDC0060.getTRNAMT()>0){ 
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true; 
    %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY4" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN4" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC4" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC4" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB4" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE4" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS05());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true; 
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY5" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN5" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC5" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC5" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB5" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE5" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS06());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true; 
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY6" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN6" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC6" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC6" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB6" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE6" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS07());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true; 
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY7" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN7" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC7" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC7" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB7" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE7" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS08());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY8" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN8" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC8" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC8" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB8" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE8" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS09());
   if (BDC0060.getTRNAMT()>0){ 
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
    %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY9" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN9" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC9" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC9" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB9" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE9" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS10());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY10" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN10" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC10" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC10" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB10" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE10" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS11());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY11" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN11" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC11" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC11" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB11" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE11" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS12());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY12" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN12" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC12" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC12" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB12" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE12" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
<% } 
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS13());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY13" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN13" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC13" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC13" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB13" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE13" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
<% } 
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS14());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY14" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN14" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC14" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC14" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB14" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE14" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
<% } 
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS15());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY15" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN15" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC15" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC15" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB15" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE15" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
<% } 
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS16());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY16" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN16" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC16" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC16" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB16" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE16" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
<% } 
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS17());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY17" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN17" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC17" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	  
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>  
	    <TD align="center"><INPUT type="text" name="DSC17" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB17" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE17" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>  
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS18());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY18" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN18" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC18" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC18" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB18" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS19());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY19" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN19" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC19" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC19" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB19" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE19" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS20());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
 <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS21());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
	   <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS22());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR> <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS23());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR> <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS24());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR> <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS25());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR> <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS26());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR> <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS27());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	 
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>   
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR> <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS28());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR> <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS29());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	  
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>  
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
	   <%}
    BDC0060 = new  datapro.eibs.beans.JBDC0060_trnsgl(rcPag.getE02TRNS30());
   if (BDC0060.getTRNAMT()>0){  
       TotalDeb +=  BDC0060.getTRNTYP().equals("D")?BDC0060.getTRNAMT():0;
       TotalCre +=  BDC0060.getTRNTYP().equals("C")?BDC0060.getTRNAMT():0;
       glnzero = !glnzero?Double.valueOf(BDC0060.getTRNGLN()).longValue()==0:true;        
   %> 	  
  	  <TR>
  	    <TD align="center"><INPUT type="text" name="BRN2" value="<%= BDC0060.getTRNBRN() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="CCY1" value="<%= BDC0060.getTRNCCY() %>" size="4" maxlength="4" readonly></TD>	
	    <TD align="center"><INPUT type="text" name="GLN1" value="<%= BDC0060.getTRNGLN() %>" size="17" maxlength="16" readonly></TD>	    
	    <TD align="center"><INPUT type="text" name="ACC1" value="<%= BDC0060.getTRNACC() %>" size="13" maxlength="13" readonly></TD>	  
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNTCD() %>" size="3" maxlength="3" readonly></TD>  
	    <TD align="center"><INPUT type="text" name="DSC1" value="<%= BDC0060.getTRNDSC() %>" size="30" maxlength="30" readonly></TD>
	    <TD align="center"><INPUT type="text" name="DEB1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("D") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	    <TD align="center"><INPUT type="text" name="CRE1" value="<%= fmtDouble(BDC0060.getTRNTYP().equals("C") ? BDC0060.getTRNAMT():0) %>" size="17" maxlength="17" readonly></TD>
	  </TR>
<% } %>	  
 
</TABLE>
<INPUT type="HIDDEN" name="GLNZERO" value="<%= glnzero?"Y":"N" %>" size="1">
<BR>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
     <TD align="right" width="70%"><B>Totales</B></TD>
     <TD align="center" width="15%"><B><INPUT type="text" name="TOTCRE" value="<%= fmtDouble(TotalDeb)  %>" size="17" maxlength="17" readonly></B></TD>
     <TD align="center" width="15%"><B><INPUT type="text" name="TOTDEB" value="<%= fmtDouble(TotalCre) %>" size="17" maxlength="17" readonly></B></TD>
  </TR>
</TABLE>
  
<P align="center">
  <INPUT id="EIBSBTN" type="submit" name="Submit0" value="Confirmar" onClick="document.forms[0].H02FLGWK1.value='Y'">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <INPUT id="EIBSBTN" type="submit" name="Regresar" value="Modificar" onClick="document.forms[0].H02FLGWK1.value='N'">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
</P>
</FORM>

</BODY>
</HTML>
