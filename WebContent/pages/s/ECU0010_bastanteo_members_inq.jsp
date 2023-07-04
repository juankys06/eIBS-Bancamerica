<html> 
<head>
<title>Consulta de Integrantes de la Junta Directiva</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtListMem" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtListMem.setCurrentRow(row);
   datapro.eibs.beans.ECU001002Message msgMT = (datapro.eibs.beans.ECU001002Message) mtListMem.getRecord();
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

</head>
<body>

<H3 align="center">Sistema de Bastanteo - Consulta de Integrantes de la Junta Directiva<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_members_inq,ECU0010"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0010">
 
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
					<INPUT type="text" name="E02CUMCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E02CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
				</TD>
			</TR>
 		</TABLE>
 	  </td>
   </tr>
   <tr></tr>	
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
	      <td nowrap >
				<div align="right">Secuencia del Integrante: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E02CUMMAN" readonly size="3" maxlength="2" value="<%= msgMT.getE02CUMMAN() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Nombre: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E02CUMMA1" size="40" maxlength="35" readonly value="<%= msgMT.getE02CUMMA1() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">Cedula de Identidad: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E02CUMBNI" size="17" maxlength="15" readonly value="<%= msgMT.getE02CUMBNI() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">Cargo: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02CUMUC5" size="5" maxlength="4" readonly value="<%= msgMT.getE02CUMUC5() %>">
      	    <input type="text" name="E02CUMUCN" size="40" maxlength="35" readonly value="<%= msgMT.getE02CUMUCN() %>" readonly >
      	  </td>     
      	</tr>
    	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Designaci&oacute;n: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02CUMDD1" readonly size="2" maxlength="2" value="<%= msgMT.getE02CUMDD1() %>">
	      	<input type="text" name="E02CUMDD2" readonly size="2" maxlength="2" value="<%= msgMT.getE02CUMDD2() %>">
	      	<input type="text" name="E02CUMDD3" readonly size="2" maxlength="2" value="<%= msgMT.getE02CUMDD3() %>">
	      </td>     
      	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Fecha de Vencimiento: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02CUMD21" size="2" maxlength="2" readonly value="<%= msgMT.getE02CUMD21() %>">
	      	<input type="text" name="E02CUMD22" size="2" maxlength="2" readonly value="<%= msgMT.getE02CUMD22() %>">
	      	<input type="text" name="E02CUMD23" size="2" maxlength="2" readonly value="<%= msgMT.getE02CUMD23() %>">
      	  </td>     
      	</tr>
     	<tr id="trdark"> 
          <td> 
             <div align="right">Tiene Reeleci&oacute;n: </div>
          </td>
          <td nowrap >
              <p> 
                 <input type="text" readonly name="E02CUMFL1" size="3" maxlength="2"
				  value="<% if (msgMT.getE02CUMFL1().equals("Y")) { out.print("Si"); }
				  else if (msgMT.getE02CUMFL1().equals("N")) { out.print("No"); }
				  else { out.print(""); } %>" >
              </p>
          </td>
        </tr>
        <tr id="trdark"> 
          <td> 
             <div align="right">Facultades del Cargo: </div>
          </td>
          <td nowrap >
              <p> 
                 <input type="text" readonly name="E02CUMFL2" size="8" maxlength="7"
				  value="<% if (msgMT.getE02CUMFL2().equals("A")) { out.print("Algunas"); }
				  else if (msgMT.getE02CUMFL2().equals("T")) { out.print("Todas"); }
				  else if (msgMT.getE02CUMFL2().equals("N")) { out.print("Ninguna"); }
				  else { out.print(""); } %>" >
              </p>
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
              <input type="text" name="E02CUMOB1" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUMOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUMOB2" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUMOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUMOB3" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUMOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUMOB4" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUMOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E02CUMOB5" size="90" maxlength="80" readonly value="<%= msgMT.getE02CUMOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
  </table>
  
</form>
</body>
</html>
