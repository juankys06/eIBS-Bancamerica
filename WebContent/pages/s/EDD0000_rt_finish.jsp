<html>
<head>
<title>Documentación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "rtFinish" class= "datapro.eibs.beans.EDD009002Message"  scope="session" />
 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<p align="center"><font size="3"><i><b><font size="3"><i><b>Impresi&oacute;n de 
  Documentos </b></i></font><font size="3"><i><b><font size="3"><i><b><font face="Times New Roman" size="4"><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_finis.jsp,EDL0130"></font></b></i></font></b></i></font></b></i></font></p>
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="38">
  <table class="tableinfo">
    <tr  > 
      <td> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr> 
            <td width="10%" height="20">&nbsp;</td>
            <td width="90%" height="20"> 
              <div align="center"></div>
            </td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%"><font size="3">Numero de Cuenta :<i><b> <%= rtFinish.getE02ACMACC().trim()%> 
              </b></i></font></td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="10%" height="15">&nbsp;</td>
            <td width="90%" height="15"> 
              <div align="left"><font size="2"> <font size="3">A favor de :</font></font><font size="3"><i><b> 
                <%= rtFinish.getE02CUSNA1().trim()%> </b></i></font></div>
            </td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%"><font size="3">N&uacute;mero de Cliente</font>:<font size="3"><i><b><%= rtFinish.getE02ACMCUN().trim()%></b></i></font></td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%"><font size="3">Moneda :</font> <font size="3"><i><b><%= rtFinish.getE02ACMCCY().trim()%></b></i></font></td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%"><font size="3">Banco :</font> <font size="3"><i><b><%= rtFinish.getE02ACMBNK().trim()%></b></i></font> 
            </td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%"><font size="3">Agencia :<i><b><%= rtFinish.getE02ACMBRN().trim()%></b></i></font></td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%"><font size="3">Producto :<i><b><%= rtFinish.getE02ACMPRO().trim()%></b></i></font></td>
          </tr>
          <tr> 
            <td width="10%">&nbsp;</td>
            <td width="90%">&nbsp;</td>
          </tr>
        </table>
        <p align="center"></p>
      </td>
    </tr>
  </table>
  <% 
    // This goes at the end of the page
    if ( (userPO.getPurpose().equals("NEW")) || (userPO.getPurpose().equals("MAINTENANCE")) ) {
		out.println("<p>");
		out.println("  <center>");
		out.println("    <input type=\"submit\" name=\"Enviar\" value=\"Aceptar\">");
		out.println("  </center>");
		out.println("<p>");
 	 }
 	 %> 
  <div align="center"> </div>
</form>
</body>
</html>
