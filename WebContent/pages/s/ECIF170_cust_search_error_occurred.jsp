<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<style>
TH {
  background: #FFFFFF;
}
</style>

<script language="JavaScript">
function enter(){
var NameSearch = document.forms[0].NameSearch.value
var FromRecord = 0
	if (NameSearch.length > 0){
		for(var i = 0; i < document.forms[0].Type.length; i++) {
			if (document.forms[0].Type[i].checked){
			var Type = document.forms[0].Type[i].value
			}
 		}
 		document.forms[0].submit();
	}
	else {
		alert("Tiene que entrar un criterio de busqueda")
	}
}

</script>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

 <h3 align="center">Ese Código Oficial no Existe<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cust_search_error_occurred.jsp,ECIF170"></h3>

 
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF170" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="Pos" VALUE="0">
  <table id="TBHELP" align="center" width="100%" height="90%">
    <tr> 
      <td width="10%" height="405">&nbsp;</td>
      <td width="80%" height="405"> 
        <table id="TBHELP" align="center" width="100%" border="1">
          <tr> 
            <td height="218"> 
              <table id="TBHELP" align="center">
                <tr> 
                  <td width="50%" height="197"> 
                    <table  id="TBHELP" width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr> 
                        <th id="THHELP">Tipo de Búsqueda</th>
                      </tr>
                      <tr> 
                        <td id="THHELP">&nbsp;</td>
                      </tr>
                      <tr> 
                        <td> 
                          <input type="radio" name="Type" value="1" checked="checked">
                          Código Oficial</td>
                      </tr>
                                      
                    </table>
                  </td>
          
                  <td NOWRAP width="50%" height="197"> 
                    <BR>
						<div align="center"> 
                      <input type="text" name="E01SELNME"  size=14>
                      &nbsp;&nbsp; </div>
                  </td>
                  
                </tr>
              </table>
            </td>
          </tr>
        </table>
           <BR><div align="center"> 
            		<input id="EIBSBTN" type=submit name="Submit"  value="Enviar">
          		</div>
      </td>
      <td width="10%" height="405">&nbsp;</td>
    </tr>
  </table>	
</form>
</body>
</html>
