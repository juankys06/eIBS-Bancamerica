<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Movimientos de Papel Valor por Oficina</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtListBranch"	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "mtListLot" 	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "mtListMov" 	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "error" 		class= "datapro.eibs.beans.ELEERRMessage"  	scope="session" />
<jsp:useBean id= "userPO" 		class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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

<h3 align="center">Movimientos de Papel Valor por Oficina
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_branch_mov_list.jsp,EVL0050">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0050" >
<input type=hidden name="SCREEN" value="300">  

<%if ( mtListMov.getNoResult() ) {%> 
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No hay movimientos de papel valor para su seleccion</b>
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
				<div align="right">Tipo Papel Valor:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E04MOVTIP" size="4" maxlength="4" value="<%= userPO.getHeader20() %>" readonly> 
				<input type="text" name="E04MOVTIN" size="35" maxlength="35" value="<%= userPO.getHeader22() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Subtipo Papel Valor:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E04MOVSUB" size="4" maxlength="4" value="<%= userPO.getHeader21() %>" readonly> 
				<input type="text" name="E04MOVSUN" size="35" maxlength="35" value="<%= userPO.getHeader23() %>" readonly> 
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
    		<TH ALIGN=CENTER >Banco</TH>
    		<TH ALIGN=CENTER >Oficina</TH>
    		<TH ALIGN=CENTER >Serial</TH>
    		<TH ALIGN=CENTER >Fecha</TH>
    		<TH ALIGN=CENTER >Tipo de<BR>Movimiento</TH>
    		<TH ALIGN=CENTER >Documento<BR>Inicial</TH>
    		<TH ALIGN=CENTER >Documento<BR>Final</TH>
    		<TH ALIGN=CENTER >Cantidad</TH>
    		<TH ALIGN=CENTER >Oficina<BR>Recibe</TH>
    		<TH ALIGN=CENTER >Asesor<BR>Recibe</TH>
  		</TR>
     	<%
        mtListMov.initRow(); 
        while (mtListMov.getNextRow()) {
           datapro.eibs.beans.EVL005004Message msgMT = (datapro.eibs.beans.EVL005004Message) mtListMov.getRecord();
     	%>               
        <TR>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVBNK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVSER())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE04MOVCD1(),msgMT.getE04MOVCD2(),msgMT.getE04MOVCD3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVOPN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVINI())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVFIN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVQTY())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVRBR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE04MOVUSR())%></TD>
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
