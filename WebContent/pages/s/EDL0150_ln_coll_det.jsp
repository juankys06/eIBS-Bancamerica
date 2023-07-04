<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<%
// CONTROL DE EXPIRACION DE PÁGINAS 
response.setHeader("Pragma", "No-cache"); 
response.setDateHeader("Expires", 0); 
response.setHeader("Cache-Control", "no-cache"); 
response.setHeader("Cache-Control", "private");
%>
<html>
<head>
<title>Edición de Lista de Garantias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 

<jsp:useBean id= "coll" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body nowrap>

 <% if(request.getParameter("client") != null){ %>
    <script language="JavaScript">
       Client="<%=request.getParameter("client")%>";
    </script>
    
 <%}%>

<h3 align="center"> Lista de Garant&iacute;as<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_coll_det.jsp,EDL0150"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="506">
  <h4>Edici&oacute;n de Lista de Garant&iacute;as</h4>
  <table class=tableinfo>
    <tr> 
      <td nowrap > 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="center">Garant&iacute;a</div>
            </td>
            <td nowrap> 
              <div align="center">Tipo</div>
            </td>
            <td nowrap> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap> 
              <div align="center">Monto</div>
            </td>
            
          </tr>
          <%
                coll.initRow();
                while (coll.getNextRow()) {
	      %> 
          <tr id="trclear"> 
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="RCLACB_<%= coll.getCurrentRow() %>" size="15" maxlength="12"  value="<%= coll.getRecord(0) %>"><a href="javascript:GetAccByClient('RCLACB_<%= coll.getCurrentRow() %>','','LB','','<%=request.getParameter("client")!= null?request.getParameter("client"):"NONE"%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="RCLTYB_<%= coll.getCurrentRow() %>" size="25" maxlength="25"  value="<%= coll.getRecord(1) %>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="RCLCCY_<%= coll.getCurrentRow() %>" size="4" maxlength="3" value="<%= coll.getRecord(2) %>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="RCLAMT_<%= coll.getCurrentRow() %>" size="17" maxlength="15" value="<%= coll.getRecord(3) %>" id="txtright" onkeyPress="enterDecimal()">
              </div>
            </td>
            
          </tr>
          <%
                }
			for (int i=coll.getRow(); i < 10; i++) {
    %> 
          <tr id="trclear"> 
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="RCLACB_<%= i %>" size="15" maxlength="12"><a href="javascript:GetAccount('RCLACB_<%= i %>','','LB','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="RCLTYB_<%= i %>" size="25" maxlength="25" value="">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="RCLCCY_<%= i %>" size="4" maxlength="3" value="">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="RCLAMT_<%= i %>" size="17" maxlength="15" value="" id="txtright" onkeyPress="enterDecimal()">
              </div>
            </td>
            
          </tr>
          <%
                }
    %> 
        </table>
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </p>
  </form>
</body>
</html>
