<html> 
<head>
<title>Mantenimiento de Firmas Conjuntas Especificas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgMTSig" class= "datapro.eibs.beans.ECU000008Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "C") { 
    	document.forms[0].SCREEN.value = 80;   
		document.forms[0].submit();
  	} 
  }
  
</SCRIPT>

</head>
<body>

<H3 align="center">Sistema de Bastanteo - Mantenimiento de Firmas Conjuntas Especificas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_signatures_maint,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="83">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">C&oacute;digo de Cliente :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E08CUFCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E08CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Integrante :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E08CUFMAN" size="3" maxlength="2" value="<%= userPO.getHeader18() %>" readonly>
					<INPUT type="text" name="E08CUFMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Cargo :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E08CUMUC5" size="5" maxlength="4" value="<%= userPO.getHeader20() %>" readonly>
					<INPUT type="text" name="E08CUMUCN" size="40" maxlength="35" value="<%= userPO.getHeader21() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Facultad :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E08CUFFAC" size="5" maxlength="4" value="<%= userPO.getHeader22() %>" readonly>
					<INPUT type="text" name="E08CUFFAN" size="40" maxlength="35" value="<%= userPO.getHeader23() %>" readonly> 
				</TD>
			</TR>
			<TR> 
		  		<TD nowrap width="40%"> 
		     		<DIV align="right">Secuencia del Acompañante :</DIV>        
		  		</TD>
      	  		<TD nowrap width="60%"> 
      	    		<INPUT type="text" name="E08CUFSEQ"  readonly size="3" maxlength="2" value="<%= msgMTSig.getE08CUFSEQ() %>">
      	    		<INPUT type="text" name="E08CUFACN"  readonly size="40" maxlength="35" value="<%= msgMTSig.getE08CUFACN() %>">
      	 		</TD>     
      		</TR>
 		</TABLE>
 	  </td>
   </tr>

  </table>
  <h4>Observaciones</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E08CUFOB1" size="90" maxlength="80" value="<%= msgMTSig.getE08CUFOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E08CUFOB2" size="90" maxlength="80" value="<%= msgMTSig.getE08CUFOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E08CUFOB3" size="90" maxlength="80" value="<%= msgMTSig.getE08CUFOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E08CUFOB4" size="90" maxlength="80" value="<%= msgMTSig.getE08CUFOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E08CUFOB5" size="90" maxlength="80" value="<%= msgMTSig.getE08CUFOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
   </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="goAction('C')">
  </p>
  
</form>
</body>
</html>
