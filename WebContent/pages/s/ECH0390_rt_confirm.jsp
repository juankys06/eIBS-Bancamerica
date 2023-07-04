<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta http-equiv="Refresh" CONTENT="7;url='javascript:document.forms[0].submit();">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="rtBasic" class="datapro.eibs.beans.ECH039002Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_confirm, ECH39001"> 
<hr size="4">
<center>

 <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSE0CH390" onsubmit="return(enviar());">
 <INPUT type="hidden" name="SCREEN" value="1">
 <input type="hidden" name="E01ACMACC" value=<%=rtBasic.getE02CHMACC()%> >
 <%if (rtBasic.getH02OPECOD().equals("0000"))
 { %>
  <table class="tbenter" cellspacing=0 cellpadding=2 border="0">
    <tr >
            <td align="center">La informacion  ha sido procesada satisfactoriamente. </td>
        </tr>
  </table>
<% }
else
{
String mensaje = "";
  if (rtBasic.getH02OPECOD().equals("0001"))
  	  mensaje = "Número de Cuenta Corriente No existe";
  else
    if (rtBasic.getH02OPECOD().equals("0002"))
  	  mensaje = "Chequera seleccionada, ya esta anulada";
  	else
      if (rtBasic.getH02OPECOD().equals("0003"))
     	  mensaje = "Chequera no puede ser restaurada. Debe estar anulada.";
      else
         if (rtBasic.getH02OPECOD().equals("0004"))
     	    mensaje = "Chequera no puede ser Anuldad. (Entregada al Cliente)";
	  	else
 	     if (rtBasic.getH02OPECOD().equals("0005"))
  		   	  mensaje = "Chequera no puede ser restaurada. (Entregada al Cliente)";
     	  %>
     	  
    <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b><%=mensaje%></b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
  

<%
}
%>
  <div align="center"> 
    <p>&nbsp;</p>
    </div>
</form>
</body>
</html>
