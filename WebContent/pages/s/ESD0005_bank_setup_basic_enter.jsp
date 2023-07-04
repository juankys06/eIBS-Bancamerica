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
    String appcode = request.getParameter("APPCODE");
    if (appcode == null) appcode = "";
%>    
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0005<%=( appcode.equals("4") ? "A":"M" )%>" > 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    <INPUT TYPE=HIDDEN NAME="APPCODE" VALUE="<%=request.getParameter("APPCODE")%>">

  <h3 align="center">Parametros Generales de Control<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="bank_setup_basic_enter.jsp,ESD0005"> 
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
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Banco :</div>
            </td>
            <td width="60%" nowrap> 
              <input type="text" name="CODBANK" size="3" maxlength="2"  >
            </td>
          </tr>  
          <tr>
            <td>&nbsp;</td>
            <td width="20%" nowrap>
              <div align="right">Tipo de Banco :</div>
            </td>
            <td width="70%" nowrap>
              
              <input type="radio" name="TYPBANK" value="N" onClick="document.forms[0].TYPBANK.value='N'"
			   >
              Nuevo
              <input type="radio" name="TYPBANK" value="E" onClick="document.forms[0].TYPBANK.value='E'"
			  checked>
              Existente</td>
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
