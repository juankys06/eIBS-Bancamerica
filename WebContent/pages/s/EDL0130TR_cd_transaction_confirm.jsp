<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130TR?SCREEN=900'"-->
<head>
<title>Transactions Confirmation </title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="javascript">
 function finish(){
 self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
 
 }
 setTimeout("document.forms[0].submit();", 7000);
 
</SCRIPT>

</head>

<body>
<h3 align="center">Confirmaci&oacute;n Transacciones <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_transaction_confirm, EDL0130TR" ></h3>
<hr size="4">
<br>
  
<table width="100%" height="90%" class="tableinfo">
  <tr > 
      
    <td> 
      <p align="center">La operaci&oacute;n requerida para el Dep&oacute;sito 
        a Plazo Fijo n&uacute;mero <b><%= datapro.eibs.master.Util.justifyRight(userPO.getIdentifier(),9)%></b> 
        ha sido procesado satisfactoriamente .<br>
        <br>
        Por favor espere...</p>
          </td>
    </tr>
  </table>

</body>
</html>
