<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Lista de Saldos Papel Valor por Asesor</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtListAgent" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
     //firstTime = false;
 } 
%>

<h3 align="center">Lista de Saldos de Papel Valor por Asesor
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_agent_bal_list.jsp,EVL0060">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0060" >

<%if ( mtListAgent.getNoResult() ) {%> 
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No hay resultados que correspondan a su criterio de búsqueda</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
<%} else {%>
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">  
 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0">    
 <TABLE class="tbenter" width="60%" align=center>
    <TR> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction()">Consulta</a></div>
      </TD>
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a></div>
      </TD>
    </TR>
 </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER ></TH>
    		<TH ALIGN=CENTER >Banco</TH>
    		<TH ALIGN=CENTER >Oficina</TH>
    		<TH ALIGN=CENTER >Tipo<BR>Papel<BR>Valor</TH>
    		<TH ALIGN=CENTER >Descripcion Tipo</TH>
    		<TH ALIGN=CENTER >Subtipo<BR>Papel<BR>Valor</TH>
    		<TH ALIGN=CENTER >Descripcion Subtipo</TH>
    		<TH ALIGN=CENTER >Asesor</TH>
    		<TH ALIGN=CENTER >Serial</TH>
    		<TH ALIGN=CENTER >Documento<BR>Inicial</TH>
    		<TH ALIGN=CENTER >Documento<BR>Final</TH>
    		<TH ALIGN=CENTER >Saldo</TH>
  		</TR>
     <%
        mtListAgent.initRow(); 
		boolean firstTime = true;
		String chk = "";
        while (mtListAgent.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
           datapro.eibs.beans.EVL006002Message msgMT = (datapro.eibs.beans.EVL006002Message) mtListAgent.getRecord();
     %>               
        <TR>
            <TD NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= mtListAgent.getCurrentRow() %>" <%=chk%>>
            </TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCBNK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCTIP())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE02DOCTIN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCSUB())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE02DOCSUN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCUSR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCSER())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCINI())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCFIN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02DOCBAL())%></TD>
		</TR>    		
    <%}%>    
     </TABLE>
    </TD>
   </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  showChecked("CURRCODE");       
</SCRIPT>
<%}%>
</FORM>
</BODY>
</HTML>
