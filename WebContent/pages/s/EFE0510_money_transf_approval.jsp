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

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">
  var reason = '';
  
  function goAction(act) {
    if (act == "A") {
    	var row = document.forms[0].CURRENTROW.value;   
    	page = "<%=request.getContextPath()%>/pages/s/EFE0510_money_transf_app_detail.jsp?ROW=" + row;
  		CenterNamedWindow(page,'Information',650,500,2);	
  	} else {
  	   var len = reason.length;
  	   
  	   //alert("length =" + len);
  	   
  	   if (len > 80) {
  	      document.forms[0].E01REMRR1.value = reason.substring(0,40);
  	      document.forms[0].E01REMRR2.value = reason.substring(40,80);
  	   } else if (len > 40) {
  	      document.forms[0].E01REMRR1.value = reason.substring(0,40);
  	      document.forms[0].E01REMRR2.value = reason.substring(40,len);
  	   } else {
  	      document.forms[0].E01REMRR1.value = reason;
  	   }
  	   //alert("r1="+ document.forms[0].E01REMRR1.value + " r2=" + document.forms[0].E01REMRR2.value);
  	   
  	   document.forms[0].submit();
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


<h3 align="center">Aprobacion de Remesas de Efectivo en Moneda Extranjera
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transf_approval.jsp,EFE0510">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0510" >

<%
	if ( appList.getNoResult() ) {
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
  
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
 <INPUT TYPE=HIDDEN NAME="H01FLGWK1" VALUE="R"> 
 <INPUT TYPE=HIDDEN NAME="E01REMRR1" VALUE="">
 <INPUT TYPE=HIDDEN NAME="E01REMRR2" VALUE=""> 
 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    
 <TABLE class="tbenter" width="80%" align=center>
    <TR> 
      <TD class=TDBKG width="34%"> 
        <div align="center"><a href="javascript:goAction('A')">Aprobar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:enterReason('R')">Rechazar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="33%"> 
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
    		<TH ALIGN=CENTER >Monto<BR>Solicitado</TH>
    		<TH ALIGN=CENTER >Creada por</TH>
    		<TH ALIGN=CENTER >Fecha/Hora</TH>
  		</TR>
     <%
                appList.initRow(); 
                chk = "checked";               
                while (appList.getNextRow()) {
                   datapro.eibs.beans.EFE051001Message msgMT = (datapro.eibs.beans.EFE051001Message) appList.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= appList.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgMT.getE01REMREF()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgMT.getE01REMBNK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01REMBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01REMCCY())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE01REMTYN())%></TD>
			<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE01REMRAM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01REMCRU())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE01REMCD1(),msgMT.getE01REMCD2(),msgMT.getE01REMCD3()) + " - " + Util.formatTime(msgMT.getE01REMCRT())%></TD>
		
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
