<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810?SCREEN=300'">

<head>
<title>Confirmaci�n de la creaci�n de una carta Gu�a de Cobranza </title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


</head>

<body>

 <h3 align="center">Confirmaci�n  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="colldraft_confirm,EDL0810" ></h3>
<hr size="4">

  <table width="100%" height="100%" border="1" bordercolor="#000000">
    <tr > 
      <td> 
        <table width="100%" height="100%">
          <tr> 
            <td align=center>
						La carta Gu�a de cobranza se ha creado satisfactoriamente y se le ha asignado el n�mero<br><br><b><%=userPO.getIdentifier()%></b> dentro del sistema.
						
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

</body>
</html>
