<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "codes" class= "datapro.eibs.beans.ESD008003Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<SCRIPT Language="Javascript">

     <% 
   if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		builtNewMenu(client_inq_personal_opt);
  <%      
   }
   else
   {
   %>
		builtNewMenu(client_inq_corp_opt);
   <%
   }
   %>

</SCRIPT>

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

<h3 align="center">C&oacute;digos de Clasificaci&oacute;n <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_both_codes, ESD0080"></h3>
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="32">
  

<table class="tableinfo">
  <tr > 
    <td> 
      <table cellspacing="0" cellpadding="2" width="100%" class="tbhead"   align="center">
        <tr>
             <td nowrap width="10%" align="right"> Cliente: 
               </td>
          <td nowrap width="12%" align="left">
      			<%= userPO.getHeader1()%>
          </td>
            <td nowrap width="6%" align="right">ID:  
            </td>
          <td nowrap width="14%" align="left">
      			<%= userPO.getHeader2()%>
          </td>
            <td nowrap width="8%" align="right"> Nombre: 
               </td>
          <td nowrap width="50%"align="left">
      			<%= userPO.getHeader3()%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<p>&nbsp;</p><center> 
    <table class="tableinfo" >
      <tr > 
        <td >
          <table cellspacing="0" cellpadding="2" width="100%" border="0" >
            <tr id="trdark"> 
              <td width="30%"> 
                <div align="right">Oficial Principal :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03OFC" size="5" maxlength="4" value="<%= codes.getE03OFC().trim()%>">
                <input type="text" readonly name="D03OFC" size="36" maxlength="35" value="<%= codes.getD03OFC().trim()%>">
              </td>
            </tr>
            <tr  id="clear"> 
              <td width="30%"> 
                <div align="right">Oficial Secundario 
                  :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03OF2" size="5" maxlength="4" value="<%= codes.getE03OF2().trim()%>">
                <input type="text" readonly name="D03OF2" size="36" maxlength="35" value="<%= codes.getD03OF2().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td width="30%"> 
                <div align="right">C&oacute;digo de Industria :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03INC" size="5" maxlength="4" value="<%= codes.getE03INC().trim()%>">
                <input type="text" readonly name="D03INC" size="36" maxlength="35" value="<%= codes.getD03INC().trim()%>">
              </td>
            </tr>
            <tr  id="clear"> 
              <td width="3%"> 
                <div align="right">C&oacute;digo de Negocio :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03BUC" size="5" maxlength="4" value="<%= codes.getE03BUC().trim()%>">
                <input type="text" readonly name="D03BUC" size="36" maxlength="35" value="<%= codes.getD03BUC().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td width="30%" height="42"> 
                <div align="right">Pa&iacute;s de Residencia 
                  :</div>
              </td>
              <td width="70%" height="42"> 
                <input type="text" readonly name="E03GEC" size="5" maxlength="4" value="<%= codes.getE03GEC().trim()%>">
                <input type="text" readonly name="D03GEC" size="36" maxlength="35" value="<%= codes.getD03GEC().trim()%>">
              </td>
            </tr>

            <tr  id="clear"> 
              <td width="3%"> 
                <div align="right">Canal de Ventas :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03SCH" size="5" maxlength="4" value="<%= codes.getE03SCH().trim()%>">
                <input type="text" readonly name="D03SCH" size="36" maxlength="35" value="<%= codes.getD03SCH().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td width="30%" height="42"> 
                <div align="right">Fuentes de Informacion de Ventas :</div>
              </td>
              <td width="70%" height="42"> 
                <input type="text" readonly name="E03SST" size="5" maxlength="4" value="<%= codes.getE03SST().trim()%>">
                <input type="text" readonly name="D03SST" size="36" maxlength="35" value="<%= codes.getD03SST().trim()%>">
              </td>
            </tr>


            <tr  id="clear"> 
              <td width="30%"> 
                <div align="right">Tipo de Relación :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03UC1" size="5" maxlength="4" value="<%= codes.getE03UC1().trim()%>">
                <input type="text" readonly name="D03UC1" size="36" maxlength="35" value="<%= codes.getD03UC1().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td width="30%"> 
                <div align="right">Clasificaci&oacute;n :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03UC2" size="5" maxlength="4" value="<%= codes.getE03UC2().trim()%>">
                <input type="text" readonly name="D03UC2" size="36" maxlength="35" value="<%= codes.getD03UC2().trim()%>">
              </td>
            </tr>
            <tr  id="clear"> 
              <td width="30%"> 
                <div align="right">C&oacute;digo de Usuario 3 
                  :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03UC3" size="5" maxlength="4" value="<%= codes.getE03UC3().trim()%>">
                <input type="text" readonly name="D03UC3" size="36" maxlength="35" value="<%= codes.getD03UC3().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td width="30%"> 
                <div align="right">C&oacute;digo de Usuario 4
                  :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03UC4" size="6" maxlength="6" value="<%= codes.getE03UC4().trim()%>">
                <input type="text" readonly name="D03UC4" size="36" maxlength="35" value="<%= codes.getD03UC4().trim()%>">
              </td>
            </tr>
            <tr  id="clear"> 
              <td width="30%"> 
                <div align="right">C&oacute;digo de Usuario 5 
                  :</div>
              </td>
              <td width="70%"> 
                <input type="text" readonly name="E03UC5" size="5" maxlength="4" value="<%= codes.getE03UC5().trim()%>">
                <input type="text" readonly name="D03UC5" size="36" maxlength="35" value="<%= codes.getD03UC5().trim()%>">
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  
</center></form>
</body>
</html>

