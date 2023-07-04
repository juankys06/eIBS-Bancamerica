<html>
<head>
<title>Consulta Consolidada del Cliente - SICRI</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<h3 align="center">Consulta Consolidada de Cliente - SICRI<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="chk_off_inq.jsp,ETL0510"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESI0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
 
  <table class="tableinfo">
    <tr > 
      <td nowrap>
		<table cellpadding=2 cellspacing=0 width="105%" border="0" align="center">
			<tr id="trclear">
				<td nowrap width="24%">
				<div align="right">Tipo Documento</div>
				</td>
				<td nowrap width="24%">
				  <SELECT name="E01WTIPOI">
				    <OPTION value="J">RIF</OPTION>
					<OPTION value="V" selected>Cédula</OPTION>
					<OPTION value="P">Pasaporte</OPTION>
				 </SELECT>
				</td>
		     </tr>
		     <tr id="trdark">	
				<td nowrap width="24%">
				<div align="right">Identificación</div>
				</td>
		    	<td nowrap width="24%">
				  <INPUT type="text" name="E01WRIF" size="20">
				</td>
			</tr>
			
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
