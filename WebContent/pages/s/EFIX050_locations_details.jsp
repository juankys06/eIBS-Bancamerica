<html>
<head>
<title>Tablas de prevision</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="loc" class="datapro.eibs.beans.EFIX05001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>
<H3 align="center">Ubicaciones de Activos Fijos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="locations_details.jsp, EDFIX050"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.amort.JSEFIX050" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Localizaci&oacute;n :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01LOCLOC" size="4" maxlength="3"  value="<%= loc.getE01LOCLOC().trim()%>" >
            </td>
            <td nowrap width="16%" > 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap colspan="3" ><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" name="E01LOCNME" size="35"  maxlength="30" value="<%= loc.getE01LOCNME().trim()%>">
              </font></font></font></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <p><br>
  </p>
  <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
