<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Asignacion de Pedidos Papel Valor
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">
    
  function goAction() {
        var row = document.forms[0].CURRENTROW.value;   
    	page = "<%=request.getContextPath()%>/pages/s/EVL0020_value_paper_req_assign_detail.jsp?ROW=" + row;
  		CenterNamedWindow(page,'Information',550,450,2);	
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


<h3 align="center">Asignacion de Pedidos de Papel Valor
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_req_assign.jsp,EVL0020">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0020" >

<%
	if ( appList.getNoResult() ) {
%> 

  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No hay pedidos de papel valor por asignar</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else { 
	
  %>
  
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    
 <TABLE class="tbenter" width="80%" align=center>
    <TR> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction()">Asignar</a> 
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
    		<TH ALIGN=CENTER >Tipo<BR>Papel<BR>Valor</TH>
    		<TH ALIGN=CENTER >Descripcion Tipo</TH>
    		<TH ALIGN=CENTER >Subtipo<BR>Papel<BR>Valor</TH>
    		<TH ALIGN=CENTER >Descripcion Subtipo</TH>
    		<TH ALIGN=CENTER >Cantidad<BR>Solicitada</TH>
    		<TH ALIGN=CENTER >Usuario<BR>Fecha/Hora<BR>Creacion</TH> 
  		</TR>
     <%
                appList.initRow(); 
                chk = "checked";               
                while (appList.getNextRow()) {
                   datapro.eibs.beans.EVL002001Message msgMT = (datapro.eibs.beans.EVL002001Message) appList.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= appList.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgMT.getE01REQREF()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgMT.getE01REQBNK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01REQBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01REQTIP())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE01REQTIN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01REQSUB())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE01REQSUN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01REQRQT())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01REQCRU()) + "<br>" +  Util.formatDate(msgMT.getE01REQCD1(),msgMT.getE01REQCD2(),msgMT.getE01REQCD3()) + "<br>" + Util.formatTime(msgMT.getE01REQCRT()) %></TD>
		
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
