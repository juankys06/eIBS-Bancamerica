<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=900'"-->
<head>
<title>Confirmación de Transacciones </title>
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

 
<h3 align="center">Confirmaci&oacute;n de Transacciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_transaction_confirm, EDL0130" ></h3>
<hr size="4">
<br>
<form>  
<table class="tableinfo">
  <tr > 
      
    <td> 
      <p align="center">Las operaciones realizadas para el Certificado de Dep&oacute;sito 
        n&uacute;mero <b><%= datapro.eibs.master.Util.justifyRight(userPO.getIdentifier(),12)%></b> 
        han sido satisfactoriamente concluidas.<br>
        <br>
        Por favor espere ...</p>
          </td>
    </tr>
  </table>
</form>

</body>
</html>
