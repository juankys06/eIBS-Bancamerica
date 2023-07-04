<html> 
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>


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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSETX0150" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  <h3 align="center">Control de Pago de Iva Especial<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="spectax_enter.jsp,ETX0150"> 
  </h3>
  <hr size="4">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table id="tbenter" align="center" style="width:85%" border="1">
    <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
           <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Pr&eacute;stamo del Cliente :</div>
            </td>
            <td width="60%" nowrap> 
              <input type="text" name="ACCNUM" size="13" maxlength="12" onKeypress="enterInteger()"  >
              <a href="javascript:GetAccByClient('ACCNUM','','10','','')">
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>              
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
<p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  </p>
  <script language="JavaScript">
  document.forms[0].ACCNUM.focus();
  </script>
        <p>&nbsp;</p>
  <p><BR>
  </p>
  <p align="center">&nbsp; </p>
      
</form>
</body>
</html>
