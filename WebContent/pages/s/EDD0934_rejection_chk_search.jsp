<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "dataCR" class= "datapro.eibs.beans.DataCheckReject"   scope="session" />

<script language="JavaScript">
function enter(){

 	if(!(document.forms[0].E01SELOFC.value.length > 0)) {	    
  		alert("Debe Ingresar el Oficial");
  		document.forms[0].E01SELOFC.focus();
  		return false;
  	}
  	if(!(document.forms[0].E01SELBRN.value.length > 0)) {
  		alert("Debe Ingresar la Oficina");
  		document.forms[0].E01SELBRN.focus();
  		return false;
  	}
  	if(!(document.forms[0].E01SELAMT.value.length > 0)) {
  		alert("Debe Ingresar un Monto");
  		document.forms[0].E01SELAMT.focus();
  		return false;
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
<h3 align="center">Devoluci&oacute;n de Cheques<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rejection_chk_search.jsp,EDD0934"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD0934" onsubmit="return(enter())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="Pos" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="ACCNUM" VALUE="">
    
  <h4 style="text-align:center">Sobregiros Monto Mayor</h4>
  
   <table class="tbenter" height="60%" width="100%" border="0">
    <TR>
    <TD>       
    <table class="tbenter" width="100%" border="0">
			
                <tr> 
                  <td NOWRAP width="50%"><div align="right"> C&oacute;digo del Oficial : </div></td>
                  <td NOWRAP width="50%"> 
                    <div align="left"> 
                      <input type="text" name="E01SELOFC"  size=5 maxlength=4 onblur="this.value = trim(this.value)">
                      <a href="javascript:GetCodeCNOFC('E01SELOFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"  ></a></div>
                  </td>
                </tr>			   	
                <tr> 
                  <td NOWRAP width="50%">                     
              		<div align="right"> C&oacute;digo de Oficina : </div>
                  </td>
                  <td NOWRAP width="50%"> 
                    <div align="left"> 
                      <input type="text" name="E01SELBRN"  size=5 maxlength=3 onKeypress="enterInteger()">
                      <a href="javascript:GetBranch('E01SELBRN','<%=currUser.getE01UBK().trim()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a></div>
                  </td>
                </tr>
                <tr> 
                  <td NOWRAP width="50%">                     
              		<div align="right"> Monto : </div>
                  </td>
                  <td NOWRAP width="50%"> 
                    <div align="left"> 
                      <select name="E01SELREL">
                      	<option value=">" selected>Mayor o Igual</option>
                      	<option value="<">Menor o Igual</option>
                      </select>
                      <input type="text" name="E01SELAMT"  size=17 maxlength=15 onKeypress="enterDecimal()">
                    </div>
                  </td>
                </tr>
			
         </table>        
                         
	<p align="center">
          <input id="EIBSBTN" type=Submit name="Submit" value="Enviar">
	</p>
                   
		</TD>
    </TR>  
  </table>	
<script language="JavaScript">
     document.forms[0].E01SELOFC.focus();
</script>
</form>
</body>
</html>
