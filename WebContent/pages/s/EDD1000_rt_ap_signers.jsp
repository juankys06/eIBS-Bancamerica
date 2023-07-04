<html>
<head>
<title>Firmantes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "signersList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "rtFirm" class= "datapro.eibs.beans.EDD550001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/Address.js"> </SCRIPT>
<SCRIPT Language="Javascript">
<% 
 
    int row = 0;
  
    try {		  	
	  	row = Integer.parseInt(request.getParameter("ROW")); 
	  	signersList.setCurrentRow(row);
        rtFirm = (datapro.eibs.beans.EDD550001Message ) signersList.getRecord(); 
		session.setAttribute("rtFirm", rtFirm);
       
	 } 
	 catch (Exception e) {
			row = 0;	    
	 }
	
 
 
%>

</SCRIPT>

</head>

<body>
<h3 align="center">Firmantes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD1000_rt_ap_signers.jsp, EDD1000"></h3>
<hr size="4">
<FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000F" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">  
  
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  <INPUT TYPE=HIDDEN NAME="E01MAN" VALUE="<%= rtFirm.getE01MAN() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUN" VALUE="<%= rtFirm.getE01CUN() %>">
  <INPUT TYPE=HIDDEN NAME="E01RTY" VALUE="<%= rtFirm.getE01RTY() %>">
  
  <table id="mainTable" class="tableinfo">
    	<tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
               <input type="text" name="E01RCN" maxlength="12" size="12" value="<%= rtFirm.getE01RCN()%>" readonly>
            </td>
          </tr>

	     <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Nombre Legal : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01MA1" maxlength="45" size="46" value="<%= rtFirm.getE01MA1()%>" readonly>
            </td>
          </tr>

	<% String pageName = "ESD0080_address_template_country" + rtFirm.getE01CCT() + ".jsp"; %>
	<jsp:include page="<%= pageName %>" flush="true">
		<jsp:param name="messageName" value="rtFirm" />
		<jsp:param name="firstColor" value="trdark" />
		<jsp:param name="basic" value="false" />
		<jsp:param name="readOnly" value='true' />
		<jsp:param name="approval" value='false' />
	</jsp:include>

          <%
          String idrow1 = "trdark";
          String idrow2 = "trclear";
           
          if(!(currUser.getE01INT().equals("06"))){%> 
             
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Identificaci&oacute;n : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01BNI" maxlength="15" size="15" value="<%= rtFirm.getE01BNI()%>">
            </td>
          </tr>
          <%} else {
              idrow2 = "trdark";
              idrow1 = "trclear";
          }
          %>
<%--          
          <tr id="<%= idrow2 %>"> 
            <td width="13%" nowrap> 
              <div align="right">Tipo de Firma: </div>
            </td>
            <td width="23%" nowrap> 
              <select name="E01FL1">
                <option value=" " <% if (!(rtFirm.getE01FL1().equals("1") || rtFirm.getE01FL1().equals("2") || rtFirm.getE01FL1().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (rtFirm.getE01FL1().equals("1")) out.print("selected"); %>>Firma Individual</option>
                <option value="2" <% if (rtFirm.getE01FL1().equals("2")) out.print("selected"); %>>Firma Mancomunada</option>
				<option value="3" <% if (rtFirm.getE01FL1().equals("3")) out.print("selected"); %>>Firma Indistinta</option>
			  </select>
            </td>
          </tr>         
--%>          
          <tr id="<%= idrow1 %>"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01FL3" maxlength="1" size="2" value="<%= rtFirm.getE01FL3()%>">
              <a href="javascript:GetCodeCNOFC('E01FL3','FI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>	
            </td>
          </tr>
</table>  
       
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type="button" name="Submit" value="Cerrar" onclick="window.close();">
          </div>
</form>

</body>
</html>
