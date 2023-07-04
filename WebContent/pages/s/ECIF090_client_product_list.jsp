<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Consulta de Ejecutivos de Cuenta</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtListAgent" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "mtListProd" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPOLevel" class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
function goAction() {
    document.forms[0].submit();	
}

function GetProdDet(officer,name,product,descri,clase) {
	var param="&OFFICER="+officer+"&PRODUCT="+product+"&NAME="+name+"&DESCRI="+descri+"&CLASE="+clase;
	page = webapp + "/servlet/datapro.eibs.client.JSECIF100?SCREEN=1" + param;
	CenterWindow(page,700,500,2);
}

</SCRIPT>

</HEAD>
<BODY>

<% 
if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
    
} 
%>

<h3 align="center">Consulta de Ejecutivos de Cuenta
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_product_list.jsp,ECIF090">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF090" >
<input type=hidden name="SCREEN" value="200"> 

<%if ( mtListProd.getNoResult() ) {%> 
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No hay productos para el ejecutivo seleccionado</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
<%} else {%>
 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0">
 <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Banco:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E03INQBNK" size="3" maxlength="3" value="<%= userPOLevel.getHeader18() %>" readonly> 
				<input type="text" name="E03INQBNN" size="35" maxlength="35" value="<%= userPOLevel.getHeader19() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Ejecutivo:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E03INQOFC" size="4" maxlength="4" value="<%= userPOLevel.getHeader20() %>" readonly> 
				<input type="text" name="E03INQOFN" size="35" maxlength="35" value="<%= userPOLevel.getHeader21() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Año - Mes:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E03INQRDY" size="5" maxlength="4" value="<%= userPOLevel.getHeader22() %>" readonly> 
				<input type="text" name="E03INQRDM" size="3" maxlength="2" value="<%= userPOLevel.getHeader23() %>" readonly> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <br>     
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER >Producto</TH>
    		<TH ALIGN=CENTER >Cantidad</TH>
    		<TH ALIGN=CENTER >Principal</TH>
    		<TH ALIGN=CENTER >Intereses</TH>
    		<TH ALIGN=CENTER >Mora</TH>
    		<TH ALIGN=CENTER >Pignoraciones</TH>
  		</TR>
     	<%
        mtListProd.initRow(); 
        while (mtListProd.getNextRow()) {
           datapro.eibs.beans.ECIF09003Message msgMT = (datapro.eibs.beans.ECIF09003Message) mtListProd.getRecord();
     	%>               
        <TR>
        	<% if (msgMT.getH03FLGWK1().equals("H")) { %>
				<TD NOWRAP ALIGN="LEFT"><B><%=Util.formatCell(msgMT.getE03INQPRS())%></B></TD>
        	<% } else if (msgMT.getH03FLGWK1().equals("S")) { %>
				<TD NOWRAP ALIGN="LEFT"><A HREF="javascript:GetProdDet('<%=msgMT.getE03INQOFC()%>','<%=msgMT.getE03INQOFN()%>','<%=msgMT.getE03INQPRD()%>','<%=msgMT.getE03INQPRS()%>','<%=msgMT.getE03INQCLS()%>')">
				<%=Util.formatCell(msgMT.getE03INQPRS())%></A></TD>
			<% } else if (msgMT.getH03FLGWK1().equals("T")) { %>
				<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE03INQPRS())%></TD>
			<% } else {%>
				<TD NOWRAP ALIGN="LEFT"><A HREF="javascript:GetProdDet('<%=msgMT.getE03INQOFC()%>','<%=msgMT.getE03INQOFN()%>','<%=msgMT.getE03INQPRD()%>','<%=msgMT.getE03INQPRS()%>',' ')">
				<%=Util.formatCell(msgMT.getE03INQPRS())%></A></TD>
			<% } %>
			<% if (!msgMT.getH03FLGWK1().equals("H")) { %>
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03INQQTY())%></TD>
				<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE03INQPRB())%></TD>
				<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE03INQINB())%></TD>
				<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE03INQPIB())%></TD>
				<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE03INQHAB())%></TD>
			<% } %>
		</TR>    		
    	<%}%>    
     </TABLE>
    </TD>
   </TR>	
</TABLE>
<%}%>
<p align="center"> 
  <input id="EIBSBTN" type=submit name="Submit" value="Regresar" >
</p>
</FORM>
</BODY>
</HTML>
