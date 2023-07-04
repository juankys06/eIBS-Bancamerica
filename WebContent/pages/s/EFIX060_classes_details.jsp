<html>
<head>
<title>Tablas de prevision</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="loc" class="datapro.eibs.beans.EFIX06001Message"  scope="session" />

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
<H3 align="center">Clases de Activos Fijos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="classes_details.jsp, EFIX060"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.amort.JSEFIX060" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">N&uacute;mero :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CLSNUM" size="5" maxlength="4"  value="<%= loc.getE01CLSNUM().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="20%" ><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" name="E01CLSNME" size="35"  maxlength="30" value="<%= loc.getE01CLSNME().trim()%>">
              </font></font></font></td>
          </tr>
          <!--
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Grupo :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E01CLSGRP" size="5" maxlength="4"  value="<%= loc.getE01CLSGRP().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">N&uacute;mero de Meses :</div>
            </td>
            <td nowrap width="20%" >
              <input type="text" name="E01CLSMTH" size="3" maxlength="2"  value="<%= loc.getE01CLSMTH().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Cuenta :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E01CLSATY" size="3" maxlength="1"  value="<%= loc.getE01CLSATY().trim()%>" > 
                <a href="javascript:GetCodeCNOFC('E01CLSATY','04')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              </div>
            </td>
          </tr>
          -->
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
