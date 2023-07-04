<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/pages/background.htm'">
<head>
<title>Loan Confirmation </title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


</head>

<body>

			<!--			if (user.getE01AUT().trim().equals("O")) {
							res.sendRedirect(super.srctx+ "/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1800&E01DLHNRO="+ collBasic.getE01DLHNRO());
						} else {
							res.sendRedirect(super.srctx+ "/servlet/datapro.eibs.products.JSEDL0800?SCREEN=400&E01DLHNRO="+ collBasic.getE01DLHNRO());
						}-->

 
<h3 align="center"><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="dft_confirm, EDL0810" ></h3>
<hr size="4">

  <table width="100%" height="100%" border="1" bordercolor="#000000">
    <tr > 
      <td> 
        <table width="100%" height="100%">
          <tr> 
          <td align=center> La planilla <b><%= userPO.getIdentifier()%></b> ha sido creada satisfactoriamente. </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

</body>
</html>
