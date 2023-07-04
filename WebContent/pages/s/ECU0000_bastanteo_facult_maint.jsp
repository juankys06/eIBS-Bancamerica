<html> 
<head>
<title>Mantenimiento de Facultades Limitadas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgMTFac" class= "datapro.eibs.beans.ECU000007Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<%
 if (!userPO.getHeader3().equals("Y")) {
%>
<SCRIPT Language="Javascript">
    builtNewMenu(bastanteo_menu_3);
</SCRIPT>
<%}%>

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "C") { 
    	document.forms[0].SCREEN.value = 70;   
		document.forms[0].submit();
  	} 
  }
  
</SCRIPT>

</head>
<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
 
 if (!userPO.getHeader3().equals("Y")) 
	 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

<H3 align="center">Sistema de Bastanteo - Mantenimiento de Facultades Limitadas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_facult_maint,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="73">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">C&oacute;digo de Cliente :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E07CULCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
			</TD>
		</TR>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E07CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
			</TD>
		</TR>
		<% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Integrante :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E07CULMAN" size="3" maxlength="2" value="<%= userPO.getHeader18() %>" readonly>
				<INPUT type="text" name="E07CULMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
			</TD>
		</TR>
		<% } else { %>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Apoderado :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="hidden" name="E07CULMAN" size="3" maxlength="2" value="01" readonly>
				<INPUT type="text" name="E07CULMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
			</TD>
		</TR>
		<% } %>
		<% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Cargo :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E07CUMUC5" size="5" maxlength="4" value="<%= userPO.getHeader20() %>" readonly>
				<INPUT type="text" name="E07CUMUCN" size="40" maxlength="35" value="<%= userPO.getHeader21() %>" readonly> 
			</TD>
		</TR>
		<% } %>
		<tr> 
		  <td nowrap> 
		     <div align="right">Facultad: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULFAC" size="5" maxlength="4" value="<%= msgMTFac.getE07CULFAC() %>" readonly >
      	    <input type="text" name="E07CULFAN" size="40" maxlength="35" value="<%= msgMTFac.getE07CULFAN() %>" readonly >
      	  </td>     
      	</tr>
 	</TABLE>
 	</td>
   </tr>
   </table>
   <h4></h4>
   <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
          <td> 
             <div align="right">Tiene Condiciones: </div>
          </td>
          <td nowrap >  
             <input type="radio" name="E07CULCON" value="Y" <%if (msgMTFac.getE07CULCON().equals("Y")) out.print("checked"); %>>
              S&iacute; 
             <input type="radio" name="E07CULCON" value="N" <%if (msgMTFac.getE07CULCON().equals("N")) out.print("checked"); %>>
              No 
          </td>
        </tr>
        <% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
     	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CULCFI" >
                <OPTION value="0" <% if (msgMTFac.getE07CULCFI().equals("0")) out.print("SELECTED");%>>No Aplica</OPTION>
                <OPTION value="1" <% if (msgMTFac.getE07CULCFI().equals("1")) out.print("SELECTED");%>>Firma Individual</OPTION>
                <OPTION value="2" <% if (msgMTFac.getE07CULCFI().equals("2")) out.print("SELECTED");%>>Firmas Conjuntas Indistintas</OPTION>
                <OPTION value="3" <% if (msgMTFac.getE07CULCFI().equals("3")) out.print("SELECTED");%>>Firmas Conjuntas Específicas</OPTION>
            </select>
          </td>     
      	</tr>
    	<tr id=trdark> 
		  <td> 
		     <div align="right">N&uacute;mero de Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULNFI" size="4" maxlength="3" value="<%= msgMTFac.getE07CULNFI() %>">
	      </td>     
      	</tr>
      	<% } %>
      	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Autorizaciones: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CULAUT" >
                <OPTION value="1" <% if (msgMTFac.getE07CULAUT().equals("1")) out.print("SELECTED");%>>No Requiere Ningun Tipo de Autorizacion</OPTION>
                <OPTION value="2" <% if (msgMTFac.getE07CULAUT().equals("2")) out.print("SELECTED");%>>Requiere Autorizacion de la Junta Directiva</OPTION>
                <OPTION value="3" <% if (msgMTFac.getE07CULAUT().equals("3")) out.print("SELECTED");%>>Requiere Autorizacion de la Asamblea de Accionistas</OPTION>
                <OPTION value="4" <% if (msgMTFac.getE07CULAUT().equals("4")) out.print("SELECTED");%>>Requiere Autorizacion Especial</OPTION>
            </select>
          </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Monto Autorizado: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULAMT" size="20" maxlength="15" value="<%= msgMTFac.getE07CULAMT() %>">
	      </td>     
      	</tr>
     </table>
    </td>
   </tr>
  </table>
  <h4>Observaciones</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB1" size="90" maxlength="80" value="<%= msgMTFac.getE07CULOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB2" size="90" maxlength="80" value="<%= msgMTFac.getE07CULOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB3" size="90" maxlength="80" value="<%= msgMTFac.getE07CULOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB4" size="90" maxlength="80" value="<%= msgMTFac.getE07CULOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB5" size="90" maxlength="80" value="<%= msgMTFac.getE07CULOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
   </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="goAction('C')">
  </p>
  
</form>
</body>
</html>
