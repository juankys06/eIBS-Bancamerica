<html> 
<head>
<title>Consulta de Facultades Limitadas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "mtListFac" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtListFac.setCurrentRow(row);
   datapro.eibs.beans.ECU000007Message msgMT = (datapro.eibs.beans.ECU000007Message) mtListFac.getRecord();
   userPO.setHeader22(msgMT.getE07CULFAC());
   userPO.setHeader23(msgMT.getE07CULFAN());
   userPO.setHeader6(request.getParameter("ROW"));
   
%>

<%
 if (!userPO.getHeader3().equals("Y")) {
%>
<SCRIPT Language="Javascript">
    builtNewMenu(bastanteo_menu_inq_3);
</SCRIPT>
<%}%>

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "R") { 
    	page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_facult_inq_list.jsp";
    	CenterNamedWindow(page,'Information',650,500,2);
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
 	 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

<H3 align="center">Sistema de Bastanteo - Consulta de Facultades Limitadas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_facult_inq,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
 
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
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
		  <td nowrap> 
		     <div align="right">Facultad: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULFAC" size="5" maxlength="4" value="<%= msgMT.getE07CULFAC() %>" readonly >
      	    <input type="text" name="E07CULFAN" size="40" maxlength="35" value="<%= msgMT.getE07CULFAN() %>" readonly >
      	  </td>     
      	</tr>
    	<tr id="trclear"> 
          <td> 
             <div align="right">Tiene Condiciones: </div>
          </td>
          <td nowrap >
              <p> 
                 <input type="text" readonly name="E07CULCON" size="3" maxlength="2"
				 value="<% if (msgMT.getE07CULCON().equals("Y")) { out.print("Si"); }
					else if (msgMT.getE07CULCON().equals("N")) { out.print("No"); }
					else { out.print(""); } %>" >
              </p>
          </td>
        </tr>
        <% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
     	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Firmas: </div>        
		  </td>
          <td nowrap>
                  <input type="text" readonly name="E07CULCFI" size="30" maxlength="30"
                  value="<% if (msgMT.getE07CULCFI().equals("0")) { out.print("No Aplica"); }
	                    	else if (msgMT.getE07CULCFI().equals("1")) { out.print("Firma Individual"); }
							else if (msgMT.getE07CULCFI().equals("2")) { out.print("Firmas Conjuntas Indistintas"); }
							else if (msgMT.getE07CULCFI().equals("3")) { out.print("Firmas Conjuntas Específicas"); }
							else { out.print(""); } %>" >
          </td>     
      	</tr>
    	<tr id=trclear> 
		  <td> 
		     <div align="right">N&uacute;mero de Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULNFI" size="4" maxlength="3" readonly value="<%= msgMT.getE07CULNFI() %>">
	      </td>     
      	</tr>
      	<% } %>
      	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Autorizaciones: </div>        
		  </td>
          <td nowrap>
                  <input type="text" readonly name="E07CULAUT" size="55" maxlength="55"
                  value="<% if (msgMT.getE07CULAUT().equals("1")) { out.print("No Requiere Ningun Tipo de Autorizacion"); }
	                    	else if (msgMT.getE07CULAUT().equals("2")) { out.print("Requiere Autorizacion de la Junta Directiva"); }
							else if (msgMT.getE07CULAUT().equals("3")) { out.print("Requiere Autorizacion de la Asamblea de Accionistas"); }
							else if (msgMT.getE07CULAUT().equals("4")) { out.print("Requiere Autorizacion Especial"); }
							else { out.print(""); } %>" >
          </td>      
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Monto Autorizado: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULAMT" size="20" maxlength="15" readonly value="<%= msgMT.getE07CULAMT() %>">
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
              <input type="text" name="E07CULOB1" size="90" maxlength="80" readonly value="<%= msgMT.getE07CULOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB2" size="90" maxlength="80" readonly value="<%= msgMT.getE07CULOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB3" size="90" maxlength="80" readonly value="<%= msgMT.getE07CULOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB4" size="90" maxlength="80" readonly value="<%= msgMT.getE07CULOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB5" size="90" maxlength="80" readonly value="<%= msgMT.getE07CULOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
   </table>
   
   <p align="center"> 
    <input id="EIBSBTN" type="button" name="Submit" value="Regresar" onclick="goAction('R')">
   </p>
  
</form>
</body>
</html>
