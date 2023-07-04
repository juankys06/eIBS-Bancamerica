<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script> 
function cerrarse(){
	document.forms[0].submit();
	opener.location.reload(true);
	window.close();
}

function cerrarse1(){
	window.close();
} 
</script> 
<body >

<%
	if(request.getParameter("conf")!=null){
%>
		<table cellspacing=0 cellpadding=2 width="100%" border="0">
         
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="center"><b>Operacion Exitosa </b></div>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="center"><b>Cierre esta ventana para continuar </b></div>
            </td>
          </tr>
          
        </table>
<%
	}
	else{
			String deb = request.getParameter("deb");
			String cred = request.getParameter("cred");
			String ok = request.getParameter("ok");
		%>
		<h3 align="center"> Confirmacion</h3>
		<hr size="4">
		<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0060?SCREEN=700" >
		  <INPUT TYPE=HIDDEN NAME="SCREEN">
		  <table class="tableinfo">
		    <tr > 
		      <td nowrap> 
		        <table cellspacing=0 cellpadding=2 width="100%" border="0">
		         
		          <tr id="trdark">
		            <td nowrap width="23%">
		              <div align="right"><b>Total Debitos : </b></div>
		            </td>
		            <td nowrap width="32%">
		              <input type="text" readonly size="20" maxlength="16" value="<%=deb%>">
		              	</a> </td>
		          </tr>
		          <tr id="trdark">
		            <td nowrap width="23%">
		              <div align="right"><b>Total Creditos: </b></div>
		            </td>
		            <td nowrap width="32%">
		              <input type="text" readonly size="20" maxlength="16" value="<%=cred%>">
		              </a> </td>
		          </tr>
		        </table>
		      </td>
		    </tr>
		  </table>
		  <table class="tableinfo">
			  <tr>
			  
			  	<td align="center"> 
		    		<input align="center" id="EIBSBTN" type=submit name="cancel" value="Cancelar" onclick="cerrarse1()">
		  		</td>
		  		<td width="192" align="center"> 
		    		<input  align="center" id="EIBSBTN" onclick="cerrarse()" type=submit name="accept" value="Aceptar" <%if(ok.trim().equals("Nook")){%> disabled <%}%>>
		  		</td>
		  		
			  </tr>
		  </table>
  		</form>
<%
}
%>
</body>
</html>
