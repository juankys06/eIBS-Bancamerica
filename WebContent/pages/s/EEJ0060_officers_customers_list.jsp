<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Traspaso de Productos entre Ejecutivos - Lista de Clientes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">
  
function goAction(opt) {
    
if (opt == "I") 
	{ 
	var row = document.forms[0].CURRENTROW.value;   
    page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_customers_inq.jsp?ROW=" + row;
    CenterNamedWindow(page,'Information',650,500,2);
   	}
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


<h3 align="center">Traspaso de Productos entre Ejecutivos - Lista de Clientes
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="officers_customers_list.jsp,EEJ0060">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0060" >

<%
	if ( mtList.getNoResult() ) {
%> 

<TABLE class="tbenter" width="60%" align=center>
   <TR> 
      	<TD class=TDBKG> 
        	<div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      	</TD>
   </TR>
</TABLE>
<TABLE class="tbenter" width=100% height=40%>
   	<TR>
      	<TD> 
        	<div align="center"> 
          		<p>
            		<b>No existen registros para su seleccion.</b>
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
    
 <TABLE class="tbenter" width="100%" align=center>
    <TR> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction('I')">Consultar</a> 
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
    				<TH ALIGN=CENTER >Codigo de<BR>Cliente</TH>
    				<TH ALIGN=CENTER >Nombre</TH>
    				<TH ALIGN=CENTER >Tipo de<BR>Cliente</TH>
  				</TR>
     			<%
                	mtList.initRow(); 
                	chk = "checked";               
                	while (mtList.getNextRow()) {
                   		datapro.eibs.beans.EEJ006001Message msgMT = (datapro.eibs.beans.EEJ006001Message) mtList.getRecord();
     			%>               
        		<TR>
					<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= mtList.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
					<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01CUSCUN())%></TD>
					<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE01CUSNA1())%></TD>
					<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE01CUFFIN())%></TD>
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
