<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "dataCR" class= "datapro.eibs.beans.DataCheckReject"   scope="session" />

<script language="JavaScript">
function enter(){
   <% if(!dataCR.getOption().equals("RP")){%>
    if( !(document.forms[0].E01SELOFC == null)) {
	  	if(!(document.forms[0].E01SELOFC.value.length > 0)) {	    
  			alert("Debe Ingresar Código del Oficial");
  			document.forms[0].E01SELOFC.focus();
  			return false;
  		}
  	}
  	<% }%>	
	if(!(document.forms[0].E01SELBRN == null)) {
		  	if(!(document.forms[0].E01SELBRN.value.length > 0)) {
		  		alert("Debe Ingresar Código de Oficina");
  				document.forms[0].E01SELBRN.focus();
  				return false;
  			}
	}
	if(!(document.forms[0].E01SELCAU == null)) {
		  	
	  		if(!(document.forms[0].E01SELCAU.value.length > 0)) {
	  		    alert("Debe Ingresar Código de Causal");
  			    document.forms[0].E01SELCAU.focus();
  			    return false;
		 	}
	} 
   return true;
}
</script>
<TITLE></TITLE>
</head>

<body>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<h3 align="center">Devoluci&oacute;n de Cheques<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rejection_chk_search.jsp,EDD0924"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD0924" onsubmit="return(enter())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="Pos" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="ACCNUM" VALUE="">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= dataCR.getOption() %>">
  
  <% if(dataCR.getOption().equals("NSF")){%>
  	<h4 style="text-align:center">Por Sobregiro y Canjes</h4>
  <%}else if(dataCR.getOption().equals("LI")){%>	  
  	<h4 style="text-align:center">Por Limite</h4>
  <%}else if(dataCR.getOption().equals("PI")){%>
	<h4 style="text-align:center">Cheques Procesados</h4>
  <%}else if(dataCR.getOption().equals("UI")){%>	  
	<h4 style="text-align:center">Otros Conceptos</h4>
  <%}else if(dataCR.getOption().equals("RP")){%>	  
  	<h4 style="text-align:center">Resoluci&oacute;n Protestos</h4>
  <%}else if(dataCR.getOption().equals("RR")){%>
	<h4 style="text-align:center">Revisi&oacute;n Resoluci&oacute;n de Protestos</h4>
   <%}%>
	

   <table class="tbenter" height="75%" width="100%" border="0">
    <TR>
    <TD>       
    <table class="tbenter" width="100%" border="0">
			<% if(	(dataCR.getOption().equals("NSF")) ||
					(dataCR.getOption().equals("NS1")) ||
			 		(dataCR.getOption().equals("UI")) || 
			 		(dataCR.getOption().equals("RP")) || 
			 		(dataCR.getOption().equals("RR"))){ %>
                <tr> 
                  <td NOWRAP width="50%"><div align="right"> C&oacute;digo del Oficial : </div></td>
                  <td NOWRAP width="50%"> 
                    <div align="left"> 
                      <input type="text" name="E01SELOFC" size=5 maxlength=4 
                      		value="<%if (dataCR.getOfficer() != null) out.print(dataCR.getOfficer());%>">
                      <a href="javascript:GetCodeCNOFC('E01SELOFC','15')">
                      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"  ></a></div>
                  </td>
                </tr>
			<% }
				if(	(dataCR.getOption().equals("NSF")) || 
					(dataCR.getOption().equals("NS1")) ||
					(dataCR.getOption().equals("LI")) || 
					(dataCR.getOption().equals("UI")) || 
					(dataCR.getOption().equals("RP")) || 
					(dataCR.getOption().equals("RR"))){ %>	
                <tr> 
                  <td NOWRAP width="50%"> 
                    
              <div align="right"> C&oacute;digo de Oficina : </div>
                  </td>
                  <td NOWRAP width="50%">
                    <div align="left"> 
                      <input type="text" name="E01SELBRN" size=5 maxlength=3 
                      		value="<%if (dataCR.getBranch() != null) out.print(dataCR.getBranch());%>">
                      <a href="javascript:GetBranch('E01SELBRN','<%=currUser.getE01UBK().trim()%>')">
                      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a></div>
                  </td>
                </tr>
			<% }
				if(	(dataCR.getOption().equals("UI")) || 
					(dataCR.getOption().equals("RP"))){ %>
                <tr> 
                  <td NOWRAP width="50%"> 
                    
              <div align="right"> C&oacute;digo de Causal : </div>
                  </td>
                  <td NOWRAP width="50%"> 
                    <div align="left"> 
					  <input type="text" name="E01SELCAU"  size=5 maxlength=4 
					  		value="<%if (dataCR.getCause() != null) out.print(dataCR.getCause());%>">
                      <a href="javascript:GetCasualTable('E01SELCAU','')">
                      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"  ></a>
					</div>
				  </td>
                </tr>
			<% }
				if (dataCR.getOption().equals("PI")) { %>
                <tr> 
                  <td NOWRAP width="50%"> 
                    <div align="right">N&uacute;mero de cuenta :</div>
                  
                  </td>
                  <td NOWRAP width="50%"> 
                    <div align="left"> 
					 <input type="text" name="E01SELACC" size="12" maxlength="12"  onKeypress="enterInteger()"
					 		value="<%if (dataCR.getAccNum() != null) out.print(dataCR.getAccNum());%>">
             		 <a href="javascript:GetAccount('E01SELACC','','RA','')">
             		 <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0"  ></a> 
          			</div>
				  </td>
                </tr>
			<% } 
				if(	(dataCR.getOption().equals("NS1")) ){ %>
                <tr> 
                  <td NOWRAP width="50%"> 
                    
              <div align="right"> Monto : </div>
                  </td>
                  <td NOWRAP width="50%"> 
                    <div align="left"> 
					  <input type="text" name="E01SELAMT"  size=22 maxlength=20
					  		value="<%if (dataCR.getAmount() != null) out.print(dataCR.getAmount());%>" onKeypress="enterDecimal()">
					</div>
				  </td>
                </tr>
			<% } %>
         </table>        
  <p>
    <div align="center"> 
      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
    </div>
		</TD>
    </TR>  
  </table>	
<script language="JavaScript">
   <% if(		(dataCR.getOption().equals("NSF")) || 
   				(dataCR.getOption().equals("NS1")) || 
   				(dataCR.getOption().equals("UI")) || 
   				(dataCR.getOption().equals("RP")) || 
   				(dataCR.getOption().equals("RR"))){ %>
      document.forms[0].E01SELOFC.focus();
   <% } else if((dataCR.getOption().equals("LI"))){%>
      document.forms[0].E01SELBRN.focus();
   <% } else if(dataCR.getOption().equals("PI")){%>
      document.forms[0].E01SELACC.focus();
   <% } else { %>
   	  	document.forms[0].submit();
   <% } %>
</script>
</form>
</body>
</html>
