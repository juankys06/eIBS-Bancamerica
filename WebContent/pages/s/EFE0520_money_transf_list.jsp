<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Remesas
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">
  
  function showMT() {
    var row = document.forms[0].CURRENTROW.value;
    page = "<%=request.getContextPath()%>/pages/s/EFE0520_money_transf_basic_inq.jsp?ROW=" + row;
  	CenterNamedWindow(page,'Information',850,500,2);	
  }
  
</SCRIPT>

</HEAD>

<BODY>

<% 
 boolean firstTime = true;
 String chk = "";
 	
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
     //firstTime = false;
 } 
%>


<h3 align="center">Lista de Remesas de Efectivo en Moneda Extranjera
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transf_list.jsp,EFE0520">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0520" >

<%
	if ( mtList.getNoResult() ) {
%> 

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
  <%   		
	}
	else { 
	
  %>
  
   
 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0">    
 <TABLE class="tbenter" width="60%" align=center>
    <TR> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:showMT()">Consulta</a> 
        </div>
      </TD>
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      </TD>
    </TR>
 </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER ></TH>
    		<TH ALIGN=CENTER >Referencia</TH>    
    		<TH ALIGN=CENTER >Banco</TH>
    		<TH ALIGN=CENTER >Oficina</TH>
    		<TH ALIGN=CENTER >Carrier</TH>
    		<TH ALIGN=CENTER >Moneda</TH>
    		<TH ALIGN=CENTER >Tipo</TH>
    		<TH ALIGN=CENTER >Monto<BR>Solicitado</TH>
    		<TH ALIGN=CENTER >Monto<BR>Aprobado</TH>
    		<TH ALIGN=CENTER >Status</TH>
    		<TH ALIGN=CENTER >Fecha<BR>Creacion</TH>
  		</TR>
     <%
                mtList.initRow(); 
                chk = "checked";               
                while (mtList.getNextRow()) {
                   datapro.eibs.beans.EFE052002Message msgMT = (datapro.eibs.beans.EFE052002Message) mtList.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= mtList.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgMT.getE02REMREF()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgMT.getE02REMBNK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02REMBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02REMCAN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02REMCCY())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE02REMTYN())%></TD>
			<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE02REMRAM())%></TD>
			<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE02REMAAM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02REMSTN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE02REMCD1(),msgMT.getE02REMCD2(),msgMT.getE02REMCD3())%></TD>
		</TR>    		
    <%             
    				chk = "";     
                }
    %>    
     </TABLE>
    </TD>
   </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  showChecked("ROW");       
</SCRIPT>

<%   		
	} 
 %>
</FORM>

</BODY>
</HTML>
