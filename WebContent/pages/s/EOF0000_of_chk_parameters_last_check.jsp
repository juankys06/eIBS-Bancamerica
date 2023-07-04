<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head>
<title>Parametros de Cheques de Gerencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id= "EOF000002Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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


<H3 align="center">Parametros de Control de Cheques de Gerencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="of_chk_parameters_last_check.jsp, EOF0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEOF0000" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1000">
  </p>
 <% if (!userPO.getHeader1().trim().equals("3")) {%>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Banco :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02OFNBNK" size="3" maxlength="2" value="<%= userPO.getBank()%>">
            </td>
            <td nowrap> 
              <div align="right">Ultimo N&uacute;mero de Cheques del Banco :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02OFNNUM" size="12" maxlength="11" value="<%= userPO.getHeader2()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%} else {%>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark">
            <th align=CENTER nowrap width="20%"><b>Agencia</b></th>
             <th align=CENTER nowrap width="40%"><b>Nombre</b></th> 
            <th align=CENTER nowrap width="40%"><b>Número de Cheque</b></th>
          </tr>
          <%
                EOF000002Help.initRow();
                while (EOF000002Help.getNextRow()) {
                 
                  datapro.eibs.beans.EOF000002Message msgList = (datapro.eibs.beans.EOF000002Message) EOF000002Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=center width=\"20%\"><%= Util.addLeftChar('0',3,msgList.getE02OFNBRN()) %></td>
			<td NOWRAP  align=left width=\"40%\"><%= msgList.getE02BRNNME() %> </td>
			<td NOWRAP  align=center width=\"40%\">
					<input type="text" name="E02OFNNUM_<%= EOF000002Help.getCurrentRow() %>" size="12" maxlength="11" value="<%= msgList.getE02OFNNUM() %>">
            </td>					
          </tr>
          <%
                }
              %>
        </table>
      
  </table>
  <%}%>
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
