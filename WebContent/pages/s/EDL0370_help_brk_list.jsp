<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv="Pragma" content="No-cache">
<META http-equiv="Pragma" content="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers

function enter(code) {
var form = top.opener.document.forms[0];
 form[top.opener.fieldName].value = code;
 top.close();
 } 
//-->
</script>
<TITLE></TITLE>
</HEAD>
<jsp:useBean id= "brkList" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<%@ page import="datapro.eibs.master.Util" %>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<BODY>
<h3 align="center">Lista de Deducciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="help_brk_list.jsp,EDL0370"></h3>
<hr size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0370" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="">
<%
	if ( brkList.getNoResult() ) {
%>
  <TABLE class="tbenter" width=100% height=100%>
 	<TR>
      <TD> 
        
      <div align="center"> <B><FONT size="3"> </FONT>No hay resultados
		para su b&uacute;squeda</B> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
  <h5>Seleccione la deducción</h5> 
  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="10%">Código</TH>
      <TH ALIGN=CENTER  nowrap width="40%">Descripción</TH>
      <TH ALIGN=CENTER  nowrap width="20%">Monto Deducción</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Factor</TH>
      <TH ALIGN=CENTER  nowrap width="20%">Tipo</TH>
    </TR>
    <%
                String NameSearch = (String)request.getAttribute("NameSearch");
                String FromRecord = (String)request.getAttribute("FromRecord");
                               
                int i=0;                
                brkList.initRow();
                while (brkList.getNextRow()) {
                    if (brkList.getFlag().equals("")) {
    %>
        <TR> 
			<TD ALIGN=center NOWRAP><div nowrap><a href="javascript:enter('<%= brkList.getRecord(4) %>')"><%= brkList.getRecord(4) %></a></DIV></TD>
			<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:enter('<%= brkList.getRecord(4) %>')"><%= brkList.getRecord(9) %></a></DIV></TD>      
			<TD ALIGN=center NOWRAP><div nowrap><a href="javascript:enter('<%= brkList.getRecord(4) %>')"><%= brkList.getRecord(5) %></a></DIV></TD>
			<TD ALIGN=center NOWRAP><div nowrap><a href="javascript:enter('<%= brkList.getRecord(4) %>')"><%= brkList.getRecord(6) %></a></DIV></TD>
			<TD ALIGN=center NOWRAP><div nowrap><a href="javascript:enter('<%= brkList.getRecord(4) %>')">
			 <% 	 if (brkList.getRecord(10).equals("A")) out.print("Seguro de Auto");
			    else if (brkList.getRecord(10).equals("C")) out.print("Credi Ahorros");
			    else if (brkList.getRecord(10).equals("E")) out.print("Entidades del Gobierno");
			    else if (brkList.getRecord(10).equals("G")) out.print("Administración de Fondos");
			    else if (brkList.getRecord(10).equals("I")) out.print("Seguro"); 
			    else if (brkList.getRecord(10).equals("N")) out.print("Notarios");
			    else if (brkList.getRecord(10).equals("S")) out.print("Vendedores o Comisionistas");
			    else if (brkList.getRecord(10).equals("V")) out.print("Agentes de Bolsa");
			    else if (brkList.getRecord(10).equals("T")) out.print("Recaudador de Impuestos");
			 %></a></DIV></TD>            			
     	</TR>                		
    <%  
                  	}
                }
    %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( brkList.getShowPrev() ) {
      			int pos = brkList.getFirstRec() - 21;
      			 out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0023?NameSearch=" + NameSearch + "&FromRecord=" + pos +  "\" > <img src=\""+request.getContextPath()+"/images/e/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( brkList.getShowNext() ) {
      			int pos = brkList.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0023?NameSearch=" + NameSearch + "&FromRecord=" + pos +  "\" ><img src=\""+request.getContextPath()+"/images/e/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
<%      
  }
%> 
</FORM>

</BODY>
</HTML>