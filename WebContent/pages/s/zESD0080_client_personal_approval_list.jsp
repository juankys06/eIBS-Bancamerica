<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<%
 if ( !userPO.getPurpose().equals("NEW") ) {
%>

   <SCRIPT Language="Javascript">
       
     builtNewMenu(client_personal_opt);
       
   </SCRIPT>

<%
}
%>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%>

<h3 align="center">Aprobaci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"  ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <input type="hidden" name="E01LGT" size="15" maxlength="10" value="<%= client.getE01LGT().trim()%>">
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction(4)"><b>Procesar</b></a></div>
      </td>
      <td class=TDBKG width="22%"> 
        <div align="center"><a href="javascript:goAction(4)"><b>Rechazar</b></a></div>
      </td>
      <td class=TDBKG width="28%"> 
        <div align="center"><a href="javascript:goAction(4)"><b>Consulta</b></a></div>
      </td>
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  <br>
  <div align="left">
    <table class="tableinfo" width="100%">
      <tr id="trclear"> 
        <td nowrap>&nbsp;</td>
        <td nowrap colspan=5> 
          <div align="center">Nombre</div>
        </td>
        <td nowrap> 
          <div align="center">Monto <br>
            Solicitado</div>
        </td>
        <td nowrap>
          <div align="center">Producto</div>
        </td>
        <td nowrap> 
          <div align="center">Evaluacion <br>
            de Riesgo</div>
        </td>
        <td nowrap> 
          <div align="center">Total <br>
            Ingresos</div>
        </td>
        <td nowrap> 
          <div align="center">Total <br>
            Egresos</div>
        </td>
        <td nowrap> 
          <div align="center">Antiguedad <br>
            Laboral</div>
        </td>
      </tr>
    </table>
  </div>
  </form>
</body>
</html>

