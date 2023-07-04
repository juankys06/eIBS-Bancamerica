<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Solicitud de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0001DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Embossing de Plastico
<BR>Mantenimiento
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_request_maint.jsp, ECD0001"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0001" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="105">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"># Solicitud : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CDRNUM" size="15" maxlength="13" readonly value="<%= msgCD.getE01CDRNUM() %>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Estado : </div>
            </td>
            <td nowrap width="20%"  > 
              <div align="left"> 
                <input type="hidden" name="E01CDRSTS" size="2" maxlength="1" readonly value="<%= msgCD.getE01CDRSTS() %>">
				<input type="text" name="E01CDRSTD" size="17" maxlength="15" readonly value="<%= msgCD.getE01CDRSTD() %>">
              </div>
            </td>
          </tr>
	<% if(msgCD.getE01CDRSTS().equals("11")){ %>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Plastico : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="hidden" name="E01CDRTPL" size="3" maxlength="2" readonly value="<%= msgCD.getE01CDRTPL() %>">
                <input type="text" name="E01CDRNPL" size="35" maxlength="30" readonly value="<%= msgCD.getE01CDRNPL() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Proveedor : </div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="hidden" name="E01CDRVEN" size="12" maxlength="10" value="<%= msgCD.getE01CDRVEN() %>">
                <input type="text" name="E01CDRNAM" size="17" maxlength="15" readonly value="<%= msgCD.getE01CDRNAM() %>">
                 </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CDRQTY" size="8" maxlength="7" readonly value="<%= msgCD.getE01CDRQTY() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Cantidad Recibida : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CDRREQ" size="15" maxlength="13" readonly value="<%= msgCD.getE01CDRREQ() %>">
			  </div>
            </td>
          </tr>
		<% } else if (msgCD.getE01CDRSTS().equals("12")) {	%>          
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Plastico : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="hidden" name="E01CDRTPL" size="3" maxlength="2" readonly value="<%= msgCD.getE01CDRTPL() %>">
                <input type="text" name="E01CDRNPL" size="35" maxlength="30" readonly value="<%= msgCD.getE01CDRNPL() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Proveedor : </div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E01CDRVEN" size="12" maxlength="10" value="<%= msgCD.getE01CDRVEN() %>">
            	<a href="javascript:GetVendor('E01CDRVEN','1')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
                 </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CDRQTY" size="8" maxlength="7" value="<%= msgCD.getE01CDRQTY() %>" onKeypress="enterInteger()">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Cantidad Recibida : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CDRREQ" size="15" maxlength="13" readonly value="<%= msgCD.getE01CDRREQ() %>">
			  </div>
            </td>
          </tr>
		<% } else if (msgCD.getE01CDRSTS().equals("13")) {	%>          
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Plastico : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="hidden" name="E01CDRTPL" size="3" maxlength="2" readonly value="<%= msgCD.getE01CDRTPL() %>">
                <input type="text" name="E01CDRNPL" size="35" maxlength="30" readonly value="<%= msgCD.getE01CDRNPL() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Proveedor : </div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="hidden" name="E01CDRVEN" size="12" maxlength="10" value="<%= msgCD.getE01CDRVEN() %>">
                <input type="text" name="E01CDRNAM" size="17" maxlength="15" readonly value="<%= msgCD.getE01CDRNAM() %>">
                 </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CDRQTY" size="8" maxlength="7" readonly value="<%= msgCD.getE01CDRQTY() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Cantidad Recibida : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CDRREQ" size="15" maxlength="13" value="<%= msgCD.getE01CDRREQ() %>" onKeypress="enterInteger()">
			  </div>
            </td>
          </tr>
		<% } else {	%>              
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Plastico : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="hidden" name="E01CDRTPL" size="3" maxlength="2" readonly value="<%= msgCD.getE01CDRTPL() %>">
                <input type="text" name="E01CDRNPL" size="35" maxlength="30" readonly value="<%= msgCD.getE01CDRNPL() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Proveedor : </div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E01CDRVEN" size="12" maxlength="10" value="<%= msgCD.getE01CDRVEN() %>">
            	<a href="javascript:GetVendor('E01CDRVEN','1')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
                 </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CDRQTY" size="8" maxlength="7" value="<%= msgCD.getE01CDRQTY() %>" onKeypress="enterInteger()">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Cantidad Recibida : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CDRREQ" size="15" maxlength="13" value="<%= msgCD.getE01CDRREQ() %>" onKeypress="enterInteger()">
			  </div>
            </td>
          </tr>
		<% } %>    
        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </div>
  </form>
</body>
</html>
