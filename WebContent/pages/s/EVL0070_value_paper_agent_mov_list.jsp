<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Movimientos de Papel Valor por Asesor</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
function goAction() {
    document.forms[0].submit();	
}
</SCRIPT>

</HEAD>
<BODY>

<% 
 String chk = "";
 	
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
 } 
%>

<h3 align="center">Movimientos de Papel Valor por Asesor
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_agent_mov_list.jsp,EVL0070">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0070" >

<%if ( mtList.getNoResult() ) {%> 
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
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
     
 <TABLE class="tbenter" width="60%" align=center>
    <TR> 
      <TD class=TDBKG width="100%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      </TD>
    </TR>
 </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER >Banco</TH>
    		<TH ALIGN=CENTER >Oficina</TH>
    		<TH ALIGN=CENTER >Tipo<BR>Papel<BR>Valor</TH>
    		<TH ALIGN=CENTER >Descripcion Tipo</TH>
    		<TH ALIGN=CENTER >Subtipo<BR>Papel<BR>Valor</TH>
    		<TH ALIGN=CENTER >Descripcion Subtipo</TH>
    		<TH ALIGN=CENTER >Asesor</TH>
    		<TH ALIGN=CENTER >Serial</TH>
    		<TH ALIGN=CENTER >Fecha</TH>
    		<TH ALIGN=CENTER >Tipo de<BR>Movimiento</TH>
    		<TH ALIGN=CENTER >Documento<BR>Inicial</TH>
    		<TH ALIGN=CENTER >Documento<BR>Final</TH>
    		<TH ALIGN=CENTER >Cantidad</TH>
  		</TR>
     <%
        mtList.initRow(); 
        while (mtList.getNextRow()) {
           datapro.eibs.beans.EVL007003Message msgMT = (datapro.eibs.beans.EVL007003Message) mtList.getRecord();
     %>               
        <TR>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVBNK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVTIP())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE03MOVTIN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVSUB())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE03MOVSUN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVUSR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVSER())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE03MOVCD1(),msgMT.getE03MOVCD2(),msgMT.getE03MOVCD3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVOPN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVINI())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVFIN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03MOVQTY())%></TD>
		</TR>    		
    <%}%>    
     </TABLE>
    </TD>
   </TR>	
</TABLE>
<%}%>
</FORM>
</BODY>
</HTML>
