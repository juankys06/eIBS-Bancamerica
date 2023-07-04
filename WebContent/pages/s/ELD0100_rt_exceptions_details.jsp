<html>
<head>
<title>Consulta de Excepciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="excDetails" class="datapro.eibs.beans.ELD010003Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   
  
   
%>


<H3 align="center">Consulta de Excepciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_money, EDD0000"></H3>
<hr size="4">
<form method="post" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  </p>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Sequencia :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E03LDESEQ" size="3" maxlength="2" value="<%= excDetails.getE03LDESEQ()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Excepci&oacute;n:</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E03LDETPY" size="5" maxlength="4" value="<%= excDetails.getE03LDETPY()%>">
                <font face="Arial"><font face="Arial"><font size="2"> </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E03LDERM1" size="30" maxlength="30" value="<%= excDetails.getE03LDERM1()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E03LDERM2" size="30" maxlength="30" value="<%= excDetails.getE03LDERM2()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E03LDERM3" size="30" maxlength="30" value="<%= excDetails.getE03LDERM3()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
