<html>
<head> 
<title>Consulta de Autorizaciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<jsp:useBean id= "mtListAut" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtListAut.setCurrentRow(row);
   datapro.eibs.beans.ECU000006Message msgMT = (datapro.eibs.beans.ECU000006Message) mtListAut.getRecord();
   
%>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "R") { 
    	page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_autoriz_inq_list.jsp";
    	CenterNamedWindow(page,'Information',650,500,2);
  	} 
  }
  
</SCRIPT>

</head>
<body>

<H3 align="center">Sistema de Bastanteo - Consulta de Autorizaciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_autoriz_inq,ECU0000"></H3>
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
				<INPUT type="text" name="E06CUACUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
			</TD>
		</TR>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E06CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
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
				<INPUT type="text" name="E06CUAMAN" size="3" maxlength="2" value="<%= userPO.getHeader18() %>" readonly>
				<INPUT type="text" name="E06CUMMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
			</TD>
		</TR>
		<% } else { %>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Apoderado :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E06CUMMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
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
				<INPUT type="text" name="E06CUMUC5" size="5" maxlength="4" value="<%= userPO.getHeader20() %>" readonly>
				<INPUT type="text" name="E06CUMUCN" size="40" maxlength="35" value="<%= userPO.getHeader21() %>" readonly> 
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
      	    <input type="text" name="E06CUAFAC" size="5" maxlength="4" value="<%= msgMT.getE06CUAFAC() %>" readonly>
      	    <input type="text" name="E06CUAFAN" size="40" maxlength="35" value="<%= msgMT.getE06CUAFAN() %>" readonly >
      	  </td>     
      	</tr>
    	<tr id=trclear> 
		  <td> 
		     <div align="right">Fecha de Recepci&oacute;n: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E06CUARD1" size="2" maxlength="2" readonly value="<%= msgMT.getE06CUARD1() %>">
	      	<input type="text" name="E06CUARD2" size="2" maxlength="2" readonly value="<%= msgMT.getE06CUARD2() %>">
	      	<input type="text" name="E06CUARD3" size="2" maxlength="2" readonly value="<%= msgMT.getE06CUARD3() %>">
      	  </td>     
      	</tr>
     	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">Abogado Revisor: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E06CUAABO" size="5" maxlength="4" readonly value="<%= msgMT.getE06CUAABO() %>">
      	    <input type="text" name="E06CUAABN" size="40" maxlength="35" readonly value="<%= msgMT.getE06CUAABN() %>">
      	  </td>     
      	</tr>
      	<tr id="trclear"> 
          <td> 
             <div align="right">Status de Aprobaci&oacute;n: </div>
          </td>
          <td nowrap >
              <p> 
                 <input type="text" readonly name="E06CUASTS" size="10" maxlength="9"
				 value="<% if (msgMT.getE06CUASTS().equals("P")) { out.print("Pendiente"); }
					else if (msgMT.getE06CUASTS().equals("A")) { out.print("Aprobado"); }
					else { out.print(""); } %>" >
              </p>
          </td>
        </tr>
    	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Aprobaci&oacute;n: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E06CUAAD1" readonly size="2" maxlength="2" value="<%= msgMT.getE06CUAAD1() %>">
	      	<input type="text" name="E06CUAAD2" readonly size="2" maxlength="2" value="<%= msgMT.getE06CUAAD2() %>">
	      	<input type="text" name="E06CUAAD3" readonly size="2" maxlength="2" value="<%= msgMT.getE06CUAAD3() %>">
	      </td>     
      	</tr>
      	<tr id="trclear"> 
          <td> 
             <div align="right">Status de Autorizaci&oacute;n: </div>
          </td>
          <td nowrap >
              <p> 
                 <input type="text" readonly name="E06CUASTA" size="11" maxlength="10"
				 value="<% if (msgMT.getE06CUASTA().equals("A")) { out.print("Permanente"); }
					else if (msgMT.getE06CUASTA().equals("T")) { out.print("Temporal"); }
					else { out.print(""); } %>" >
              </p>
          </td>
        </tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Vencimiento: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E06CUAMA1" size="2" maxlength="2" readonly value="<%= msgMT.getE06CUAMA1() %>">
	      	<input type="text" name="E06CUAMA2" size="2" maxlength="2" readonly value="<%= msgMT.getE06CUAMA2() %>">
	      	<input type="text" name="E06CUAMA3" size="2" maxlength="2" readonly value="<%= msgMT.getE06CUAMA3() %>">
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
              <input type="text" name="E06CUAOB1" size="90" readonly maxlength="80" value="<%= msgMT.getE06CUAOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E06CUAOB2" size="90" readonly maxlength="80" value="<%= msgMT.getE06CUAOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E06CUAOB3" size="90" readonly maxlength="80" value="<%= msgMT.getE06CUAOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E06CUAOB4" size="90" readonly maxlength="80" value="<%= msgMT.getE06CUAOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E06CUAOB5" size="90" readonly maxlength="80" value="<%= msgMT.getE06CUAOB5().trim()%>">
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
