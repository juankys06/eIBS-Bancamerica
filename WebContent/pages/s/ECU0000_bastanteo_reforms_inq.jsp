<html> 
<head>
<title>Consulta de Reformas Documento Constitutivo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "mtListRef" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtListRef.setCurrentRow(row);
   datapro.eibs.beans.ECU000004Message msgMT = (datapro.eibs.beans.ECU000004Message) mtListRef.getRecord();
   
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
    	page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_reforms_inq_list.jsp";
    	CenterNamedWindow(page,'Information',650,500,2);
  	} 
  }
  
</SCRIPT>

</head>
<body>

<H3 align="center">Sistema de Bastanteo - Consulta de Reformas Documento Constitutivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_reforms_inq,ECU0000"></H3>
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
					<INPUT type="text" name="E04CURCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E04CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
				</TD>
			</TR>
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
		     <div align="right">Fecha de la Reforma: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CURRD1" readonly size="2" maxlength="2" value="<%= msgMT.getE04CURRD1() %>">
	      	<input type="text" name="E04CURRD2" readonly size="2" maxlength="2" value="<%= msgMT.getE04CURRD2() %>">
	      	<input type="text" name="E04CURRD3" readonly size="2" maxlength="2" value="<%= msgMT.getE04CURRD3() %>">
	      </td>     
      	</tr>
    	<tr id=trclear> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Registro Mercantil: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CURRMC" size="3" maxlength="2" readonly value="<%= msgMT.getE04CURRMC() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Registro: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CURRE1" size="2" maxlength="2" readonly value="<%= msgMT.getE04CURRE1() %>">
	      	<input type="text" name="E04CURRE2" size="2" maxlength="2" readonly value="<%= msgMT.getE04CURRE2() %>">
	      	<input type="text" name="E04CURRE3" size="2" maxlength="2" readonly value="<%= msgMT.getE04CURRE3() %>">
      	  </td>     
      	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Circunscripci&oacute;n: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CURCIR" size="45" maxlength="40" readonly value="<%= msgMT.getE04CURCIR() %>"> 
		  </td>
     	</tr>    
      	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Documento: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CURDOC" size="9" maxlength="7" readonly value="<%= msgMT.getE04CURDOC() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Tomo: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CURTOM" size="10" maxlength="8" readonly value="<%= msgMT.getE04CURTOM() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Expediente: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CUREXP" size="15" maxlength="12" readonly value="<%= msgMT.getE04CUREXP() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">C&oacute;digo de Materia : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CURCOD" size="5" maxlength="4" readonly value="<%= msgMT.getE04CURCOD() %>">
      	    <input type="text" name="E04CURCON" size="40" maxlength="35" readonly value="<%= msgMT.getE04CURCON() %>">
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
              <input type="text" name="E04CUROB1" size="90" maxlength="80" readonly value="<%= msgMT.getE04CUROB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB2" size="90" maxlength="80" readonly value="<%= msgMT.getE04CUROB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB3" size="90" maxlength="80" readonly value="<%= msgMT.getE04CUROB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB4" size="90" maxlength="80" readonly value="<%= msgMT.getE04CUROB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB5" size="90" maxlength="80" readonly value="<%= msgMT.getE04CUROB5().trim()%>">
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
