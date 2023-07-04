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
 
 <h3 align="center">Parametros de Generales de Control<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="msg_parameters_enter.jsp,ESD0005"> 
 </h3>
 <hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0005" >
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  
  <table class="tbenter" align="center" style="width:85%; height=50%" >
    <tr> 
      <td> 
        <table class="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            
            <td width="50%" nowrap> 
              <div align="right">Banco :</div>
            </td>
            <td width="50%" nowrap> 
              <input type="text" name="E03MSGBNK" size="3" maxlength="2"  >
            </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</FORM>
</body>
</html>
