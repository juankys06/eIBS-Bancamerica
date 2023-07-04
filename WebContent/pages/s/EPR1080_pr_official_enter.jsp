<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="JavaScript">

function enter(){
    if( !(document.forms[0].E01SELOFC == null)) {
	  	if(!(document.forms[0].E01SELOFC.value.length > 0)) {	    
  			alert("Debe Ingresar Código del Oficial");
  			document.forms[0].E01SELOFC.focus();
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

<h3 align="center">Autorización de Oficiales
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_official_enter.jsp, EPR1080"></h3>
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR1080" onsubmit="return(enter())">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="31">
<INPUT TYPE=HIDDEN NAME="ACCNUM" VALUE="">
  
<table class="tbenter" height="75%" width="100%" border="0">
	<TR>
    	<TD>       
    		<table class="tbenter" width="100%" border="0">
        		<tr> 
		          	<td NOWRAP width="50%"><div align="right"> C&oacute;digo del Oficial : </div></td>
		          	<td NOWRAP width="50%"> 
		            	<div align="left"> 
		              		<input type="text" name="E01SELOFC" size=5 maxlength=4 value="">
		              		<a href="javascript:GetCodeCNOFC('E01SELOFC','15')">
		              		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="bottom" border="0"  ></a>
		              	</div>
		          	</td>
        		</tr>
        		<tr> 
          			<td NOWRAP width="50%"><div align="right"> C&oacute;digo de Oficina : </div></td>
          			<td NOWRAP width="50%">
            			<div align="left"> 
              				<input type="text" name="E01SELBRN" size=5 maxlength=3 value="">
              				<a href="javascript:GetBranch('E01SELBRN','<%= currUser.getE01UBK().trim()%>')">
              				<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="bottom" border="0"></a>
              			</div>
          			</td>
        		</tr>
			</table>        
			
   			<div align="center"><input id="EIBSBTN" type=submit name="Submit" value="Enviar"></div>
		</TD>
	</TR>  
</table>	

</form>
</body>
</html>
