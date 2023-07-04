<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<script language="JavaScript">
function enter(){
	  document.forms[0].submit();
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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSELD0510" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <h3 align="center">Tablas de Perfil de Lavado de Dinero<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_profile_table.jsp, ELD0510"> 
  </h3>
  <hr size="4">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table id="tbenter" align="center" style="width:85%" border="1">
    <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td width="18%" nowrap>&nbsp;</td>
            <td width="22%" nowrap> 
              <div align="right">Código de Tabla :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E01LDPPRF" size="3" maxlength="2" >
              <input type="text" name="E01LDPDSC" size="36" readonly maxlength="35" >
              <a href="javascript:GetProfileTable('E01LDPPRF','E01LDPDSC')"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" >
              </a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
        <p>&nbsp;</p>
  <p><BR>
  </p>
  <p align="center">&nbsp; </p>
      
</form>
</body>
</html>
