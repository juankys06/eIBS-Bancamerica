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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEOF0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  <h3 align="center">Parametros de Cheques de Gerencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_chk_enter_parameter.jsp, EOF0000"> 
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
              <div align="right">Banco :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E01OFCBNK" size="3" maxlength="2"  >
            </td>
          </tr>
          <tr> 
            <td width="18%" nowrap>&nbsp;</td>
            <td width="22%" nowrap> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E01OFCCCY" size="4" maxlength="3"  >
              <a href="javascript:GetCurrency('E01OFCCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
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
