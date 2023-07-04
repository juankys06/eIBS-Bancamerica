<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">

<head>
<title>Confirmación de la creación de un Cliente Juridico </title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT LANGUAGE="javascript">
 function finish(){
	if (document.getElementById("E01SFR").value == 'A') {
		self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
	} else {
		document.forms[0].submit();
	}
  }

 setTimeout("finish();", 7000);
 
</SCRIPT>

</head>

<body>

 <h3 align="center">Confirmación  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_personal_confirm, ESD0080" ></h3>
 <hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=1" >
  <INPUT TYPE=HIDDEN NAME="E01SFR" VALUE="<%= client.getE01SFR().trim()%>">
  <table width="100%" height="100%" border="1" bordercolor="#000000">
    <tr > 
      <td> 
        <table width="100%" height="100%">
          <tr> 
            <td align=center>
		       	<% if (client.getE01SFR().equals("A")) {%>
			  		El cliente <b><%= userPO.getHeader3()%></b> se ha aprobado con el número  <b><%= userPO.getCusNum()%></b> dentro del sistema.
				<%} else { %>
			  		El cliente <b><%= userPO.getHeader3()%></b> se ha creado satisfactoriamente y se le ha asignado el número  <b><%= userPO.getCusNum()%></b> dentro del sistema.
				<% } %>

            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 </FORM>

</body>
</html>
