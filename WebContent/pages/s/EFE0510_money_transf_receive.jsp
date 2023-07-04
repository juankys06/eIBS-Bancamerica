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

<jsp:useBean id= "receiveList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">
  
  function goAction() {
    var row = document.forms[0].CURRENTROW.value;   
    page = "<%=request.getContextPath()%>/pages/s/EFE0510_money_transf_rec_detail.jsp?ROW=" + row;
  	CenterNamedWindow(page,'Information',850,500,2);	
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


<h3 align="center">Recepcion de Remesas de Efectivo en Moneda Extranjera
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transf_receive.jsp,EFE0510">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0510" >

<%
	if ( receiveList.getNoResult() ) {
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
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
     
 <TABLE class="tbenter" width="60%" align=center>
    <TR> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction()">Recibir</a> 
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
    		<TH ALIGN=CENTER >Moneda</TH>
    		<TH ALIGN=CENTER >Tipo</TH>
    		<TH ALIGN=CENTER >Monto<BR>Aprobado</TH>
    		<TH ALIGN=CENTER >Enviado por</TH>
    		<TH ALIGN=CENTER >Fecha-Hora</TH>
  		</TR>
     <%
                receiveList.initRow(); 
                chk = "checked";               
                while (receiveList.getNextRow()) {
                   datapro.eibs.beans.EFE051003Message msgMT = (datapro.eibs.beans.EFE051003Message) receiveList.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= receiveList.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgMT.getE03REMREF()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgMT.getE03REMBNK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03REMBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03REMCCY())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE03REMTYN())%></TD>
			<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE03REMAAM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03REMSHU())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE03REMSD1(),msgMT.getE03REMSD2(),msgMT.getE03REMSD3())  + " - " + Util.formatTime(msgMT.getE03REMSHT())%></TD>
		
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
